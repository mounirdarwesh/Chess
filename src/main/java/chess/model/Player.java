package chess.model;

import chess.Attributes.Color;
import chess.controller.Move;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Gr.45
 * The player Class, where everything the player can do
 */
public abstract class Player {

    /**
     * beaten pieces
     */
    private static List<Piece> beaten = new ArrayList<>();

    /**
     * The color of the player's chosen pieces
     */
    protected Color color;

    /**
     * All players pieces
     */
    protected List<Piece> playerPieces;

    /**
     * To check if the pawn is allowed to do En Passant
     */
    private boolean allowEnPassant = false;

    /**
     * boolean that shows if it is a first click
     */
    private boolean firstClick = true;

    /**
     * boolean that if a player undo a move
     */
    protected boolean hasPlayerUndoAMove = false;

    /**
     * boolean if a player redid a move
     */
    protected boolean hasPlayerRedidAMove = false;

    /**
     * The player's timer
     */
    protected int timer;

    /**
     * The constructor of the class Player
     *
     * @param color the color that the player chooses to play with
     */
    public Player(Color color) {
        this.color = color;
        this.playerPieces = new ArrayList<>();
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
     * get Player Captured Pieces.
     *
     * @return Captured Pieces.
     */
    public List<Piece> getBeaten() {
        return beaten;
    }

    /**
     * Here where all of player's legal moves are calculated
     *
     * @return legalMoves All of the enemy's legal moves
     */
    public List<Move> calculatePlayerMoves() {
        List<Move> legalMoves = new ArrayList<>();
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
        List<Move> enemyLegalMoves = Game.getOpponent(this).calculatePlayerMoves();
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
     * check all Player Moves
     *
     * @return true, if the player has no moves to preform
     */
    public boolean isCheckMate() {
        boolean CheckMate = true;
        if (this.isKingInCheck()) {
            for (Move move : this.calculatePlayerMoves()) {
                move.execute();
                if (!this.isKingInCheck()) {
                    CheckMate = false;
                    move.undo();
                    break;
                }
                move.undo();
            }
        } else {
            CheckMate = false;
        }
        return CheckMate;
    }

    /**
     * Getter to the player's king
     *
     * @return piece
     */
    public King getKing() {
        for (Piece piece : this.playerPieces) {
            if (piece instanceof King) {
                return (King) piece;
            }
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
     * check if king side castling is allowed
     *
     * @return boolean
     */
    public boolean isKingSideCastleAllowed() {
        this.getKing().calculateLegalMoves();
        return this.getKing().isKingSideCastle();
    }

    /**
     * check if the queen side castling is allowed
     *
     * @return boolean
     */
    public boolean isQueenSideCastleAllowed() {
        this.getKing().calculateLegalMoves();
        return this.getKing().isQueenSideCastle();
    }

    /**
     * check if EnPassant is allowed
     *
     * @return boolean
     */
    public boolean isAllowEnPassant() {
        return allowEnPassant;
    }

    /**
     * setter for allowEnPassant
     *
     * @param allowEnPassant allows EnPassant
     */
    public void setAllowEnPassant(boolean allowEnPassant) {
        this.allowEnPassant = allowEnPassant;
    }

    /**
     * Getter to the player's pieces
     *
     * @return List of pieces
     */
    public List<Piece> getPlayerPieces() {
        return playerPieces;
    }

    /**
     * check if it the first click
     *
     * @return boolean
     */
    public boolean isFirstClick() {
        return firstClick;
    }

    /**
     * setter for firstClick
     *
     * @param firstClick
     */
    public void setFirstClick(boolean firstClick) {
        this.firstClick = firstClick;
    }

    /**
     * checks if the player undo a move
     *
     * @return boolean
     */
    public boolean hasPlayerUndidAMove() {
        return hasPlayerUndoAMove;
    }

    /**
     * setter for hasPlayerUndidAMove
     *
     * @param hasPlayerUndidAMove
     */
    public void setHasPlayerUndidAMove(boolean hasPlayerUndidAMove) {
        this.hasPlayerUndoAMove = hasPlayerUndidAMove;
    }

    /**
     *
     * @return
     */
    public boolean hasPlayerRedidAMove() {
        return hasPlayerRedidAMove;
    }

    /**
     *
     * @param hasPlayerRedidAMove
     */
    public void setHasPlayerRedidAMove(boolean hasPlayerRedidAMove) {
        this.hasPlayerRedidAMove = hasPlayerRedidAMove;
    }

    /**
     *
     * @return
     */
    public int getTimer() {
        return timer;
    }

    /**
     *
     * @param timer
     */
    public void setTimer(int timer) {
        this.timer = timer;
    }

    @Override
    public String toString() {
        return this.color.isWhite() ? "White Player" : "Black player";
    }
}
