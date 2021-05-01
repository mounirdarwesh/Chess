package chess.model;

import java.util.ArrayList;

import chess.Attributes;
import chess.Attributes.Color;
import chess.controller.Move;

/**
 * @author Gruppe 45
 * The Piece class
 */
public abstract class Piece {

    /**
     * The position of the piece on the board
     */
    protected int position;

    /**
     * The color of the piece
     */
    protected Color color;

    /**
     * The Board of the game
     */
    protected Board board;

    /**
     * Legal moves of the piece
     */
    protected ArrayList<Move> allLegalMoves;

    /**
     * boolean to check if the piece has moved before
     */
    protected boolean isFirstMove = true;


    /**
     * To check if En Passant is valid
     */
    protected boolean enPassant = false;


    /**
     * The constructor of the Piece Class
     *
     * @param position The position of the piece
     * @param color    The type of the piece
     * @param board    The board
     */
    Piece(int position, Color color, Board board) {
        this.position = position;
        this.color = color;
        this.board = board;

    }

    /**
     * A method to calculate all of the pieces legal moves
     */
    public abstract void calculateLegalMoves();

    /**
     * Getter of the position of the piece
     *
     * @return the position
     */
    public int getPosition() {
        return position;
    }

    /**
     * Setter of the position of the piece
     *
     * @param position the position to set
     */
    public void setPosition(int position) {
        this.position = position;
    }

    /**
     * Getter for the type of the piece
     *
     * @return the type
     */
    public Color getColor() {
        return color;
    }

    /**
     * Getter for the first move boolean
     *
     * @return the isFirstMove
     */
    public boolean isFirstMove() {
        return isFirstMove;
    }

    /**
     * @param isFirstMove the isFirstMove to set
     */
    public void setFirstMove(boolean isFirstMove) {
        this.isFirstMove = isFirstMove;
    }

    /**
     * @return the allLegalMoves
     */
    public ArrayList<Move> getAllLegalMoves() {
        return allLegalMoves;
    }

    /**
     * @param allLegalMoves the allLegalMoves to set
     */
    public void setAllLegalMoves(ArrayList<Move> allLegalMoves) {
        this.allLegalMoves = allLegalMoves;
    }

    /**
     * @param enPassant the enPassant to set
     */
    public void setEnPassant(boolean enPassant) {
        this.enPassant = this instanceof Pawn ? enPassant : null;
    }

    /**
     * A method to return true if the new or
     * the current position is on the board
     */
    public boolean isPositionInBounds(int position) {
        return position >= 0 && position <= 63;
    }

    /**
     * check if the Destination of a Piece Occupied from friendly Piece
     *
     * @param position of the Piece
     * @return if is a friendly Piece
     */
    public boolean isFriendAtTheDestination(int position) {
        Piece pieceAtDestination = board.getPiece(position);
        return pieceAtDestination == null || pieceAtDestination.color == this.color;
    }

    /**
     * check if the Destination Tile is empty
     *
     * @param position to check
     * @return true if it's empty
     */
    public boolean isDestinationEmpty(int position) {
        Piece pieceAtDestination = board.getPiece(position);
        return pieceAtDestination == null;
    }

    /**
     * check if the Piece is in the first Column.
     *
     * @param position to check
     * @return true, is in the first Column, otherwise not.
     */
    public static boolean isInFirstColumn(int position) {
        return position % 8 == 0;
    }

    /**
     * check if the Piece is in the last Column.
     *
     * @param position to check
     * @return true, is in the last Column, otherwise not.
     */
    public static boolean isInLastColumn(int position) {
        return position % 8 == 7;
    }

    /**
     * check if the Piece is in the Second Column.
     *
     * @param position to check
     * @return true, is in the Second Column, otherwise not.
     */
    public static boolean isInSecondColumn(int position) {
        return position % 8 == 1;
    }

    /**
     * check if the Piece is in the Seventh Column.
     *
     * @param position to check
     * @return true, is in the Seventh Column, otherwise not.
     */
    public static boolean isInSeventhColumn(int position) {
        return position % 8 == 6;
    }
}
