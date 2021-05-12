package chess.model;

import chess.Attributes.Color;
import chess.controller.Move;


import java.util.ArrayList;

/**
 * @author Gr.45
 * The player Class, where everything the player can do
 */
public abstract class Player {

    /**
     * The color of the player's chosen pieces
     */
    protected Color color;

    /**
     * All players pieces
     */
    protected ArrayList<Piece> playerPieces;

    /**
     * To check if the pawn is allowed to do En Passant
     */
    private boolean allowEnPassant = false;

    /**
     * The constructor of the class Player
     *
     * @param color the color that the player chooses to play with
     */
    public Player(Color color) {
        this.color = color;
        playerPieces = new ArrayList<>();
    }

    /**
     * Making the move
     */
    public abstract void makeMove(Move move);

    /**
     * Adding to the list of player's available pieces
     *
     * @param piece The piece to add
     */
    public void addToPlayersPieces(Piece piece) {
        playerPieces.add(piece);
    }

    /**
     * Removing from the player's available pieces
     *
     * @param piece The piece to remove
     */
    public void removeFromPlayersPieces(Piece piece) {
        playerPieces.remove(piece);
    }

    /**
     * Here where all of player's legal moves are calculated
     *
     * @return legalMoves All of the enemy's legal moves
     */
    protected ArrayList<Move> calculatePlayerMoves() {
        ArrayList<Move> legalMoves = new ArrayList<>();
        for (Piece piece : playerPieces) {
            piece.calculateLegalMoves();
            legalMoves.addAll(piece.getAllLegalMoves());
        }
        return legalMoves;
    }

    /**
     * Checks if the players King is in check
     *
     * @return true if the king is in check, false otherwise
     */
    public boolean isKingInCheck() {
        boolean kingInCheck = false;
        // Calculate a list of enemy legal moves
        ArrayList<Move> enemyLegalMoves = Game.getOpponent(Game.getCurrentPlayer()).calculatePlayerMoves();
        // Iterate through all of the enemy's legal moves. If a move is found that can threaten
        // the current player's king, return true
        for (Move move : enemyLegalMoves) {
            if (move.getDestination() == getKing().getPosition()
                    && (move instanceof Move.CaptureMove || move instanceof Move.PromotionMove)) {
                kingInCheck = true;
                break;
            }
        }
        return kingInCheck;
    }

    /**
     * check all King Moves, if he can Escape.
     *
     * @return true, if the King has no Escape
     */
    boolean checkMate(Piece king) {
        boolean isCheckMate = true;
        if (this.isKingInCheck()) {
            king.calculateLegalMoves();
            for (Move move : king.getAllLegalMoves()) {
                move.execute();
                if (!isKingInCheck()) {
                    isCheckMate = false;
                    move.undo();
                    break;
                }
                move.undo();
            }
        } else isCheckMate = false;
        return isCheckMate;
    }

    /**
     * Getter to the player's king
     *
     * @return
     */
    public Piece getKing() {
        for (Piece piece : playerPieces) {
            if (piece instanceof King) return piece;
            else continue;
        }
        return null;
    }

    /**
     * Getter of the players color to play
     *
     * @return the color
     */
    public Color getColor() {
        return color;
    }

    /**
     * @return
     */
    public boolean isAllowEnPassant() {
        return allowEnPassant;
    }

    /**
     * @param allowEnPassant
     */
    public void setAllowEnPassant(boolean allowEnPassant) {
        this.allowEnPassant = allowEnPassant;
    }

    /**
     * Getter to the player's pieces
     *
     * @return
     */
    public ArrayList<Piece> getPlayerPieces() {
        return playerPieces;
    }

    @Override
    public String toString() {
        return this.color.isWhite() ? "White Player" : "Black player";
    }

}
