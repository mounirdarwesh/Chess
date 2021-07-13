package chess.model.player;

import java.util.ArrayList;
import java.util.List;
import chess.Attributes.Color;
import chess.controller.Move;
import chess.model.pieces.King;
import chess.model.pieces.Piece;

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
    protected List<Piece> playerPieces;

    /**
     * boolean that if a player undo a move
     */
    protected boolean hasPlayerUndoAMove = false;

    /**
     * boolean if a player redid a move
     */
    protected boolean hasPlayerRedidAMove = false;

    /**
     * beaten pieces
     */
    private List<Piece> beaten = new ArrayList<>();

    /**
     *
     */
    protected long timeLeft;


    /**
     * The constructor of the class Player
     * @param color the color that the player chooses to play with
     */
    public Player(Color color) {
        this.color = color;
        this.playerPieces = new ArrayList<>();
    }

    /**
     * tell the Player to make a Move
     * @param move the Move has to be done
     */
    public abstract void makeMove(Move move);

    /**
     * calculate the Player all legal Moves
     * @return list of all legal move
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
     * Getter to the player's king
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

    @Override
    public String toString() {
        return this.color.isWhite() ? "White Player" : "Black player";
    }

    /**
     * Getter of the players color to play
     * @return the color
     */
    public Color getColor() {
        return color;
    }

    /**
     * get Player Captured Pieces.
     *
     * @return Captured Pieces.
     */
    public List<Piece> getBeaten() {
        return this.beaten;
    }

    /**
     * Getter to the player's pieces
     * @return List of pieces
     */
    public List<Piece> getPlayerPieces() {
        return playerPieces;
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
     * @param hasPlayerUndidAMove hasPlayerUndidAMove
     */
    public void setHasPlayerUndidAMove(boolean hasPlayerUndidAMove) {
        this.hasPlayerUndoAMove = hasPlayerUndidAMove;
    }

    /**
     * check if a player redid a move
     * @return boolean
     */
    public boolean hasPlayerRedidAMove() {
        return hasPlayerRedidAMove;
    }

    /**
     * setter of the hasPlayerRedidAMove
     * @param hasPlayerRedidAMove boolean hasPlayerRedidAMove
     */
    public void setHasPlayerRedidAMove(boolean hasPlayerRedidAMove) {
        this.hasPlayerRedidAMove = hasPlayerRedidAMove;
    }

    /**
     * Getter for the left Time
     * @return left Time
     */
    public long getTimeLeft() {
        return timeLeft;
    }

    /**
     * Setter the left Time
     * @param timeLeft left time
     */
    public void setTimeLeft(long timeLeft) {
        this.timeLeft = timeLeft;
    }
}
