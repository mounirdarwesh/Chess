package chess.model.pieces;

import chess.Attributes;
import chess.controller.Move;
import chess.model.Board;
import java.util.List;

public abstract class Piece {

    /**
     * The value of the piece
     */
    protected final int value;

    /**
     * The position of the piece on the board
     */
    protected int position;

    /**
     * The color of the piece
     */
    protected Attributes.Color color;

    /**
     * The Name of the Piece
     */
    protected String name = "";

    /**
     * The Board of the game
     */
    protected Board board;

    /**
     * Legal moves of the piece
     */
    protected List<Move> allLegalMoves;

    /**
     * boolean to check if the piece has moved before
     */
    protected boolean isFirstMove = true;

    /**
     * The constructor of the Piece Class
     * @param position The position of the piece
     * @param color    The type of the piece
     * @param board    The board
     */
    public Piece(int value, int position, Attributes.Color color, Board board) {
        this.value = value;
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
    public Attributes.Color getColor() {
        return color;
    }

    /**
     * Getter for the first move boolean
     *
     * @return the isFirstMove
     */
    public boolean isFirstMoveOnBoard() {
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
    public List<Move> getAllLegalMoves() {
        return allLegalMoves;
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
     *
     * @return
     */
    public boolean isOnSeventhRow() {
        return position >= 48 && position <= 55;
    }

    /**
     *
     * @return
     */
    public boolean inOnSecondRow() {
        return position >= 8 && position <= 15;
    }
    /**
     * check if the Piece is in the first Column.
     *
     * @param position to check
     * @return true, is in the first Column, otherwise not.
     */
    public boolean isInFirstColumn(int position) {
        return position % 8 == 0;
    }

    /**
     * check if the Piece is in the last Column.
     *
     * @param position to check
     * @return true, is in the last Column, otherwise not.
     */
    public boolean isInLastColumn(int position) {
        return position % 8 == 7;
    }

    /**
     * check if the Piece is in the Second Column.
     *
     * @param position to check
     * @return true, is in the Second Column, otherwise not.
     */
    public boolean isInSecondColumn(int position) {
        return position % 8 == 1;
    }

    /**
     * check if the Piece is in the Seventh Column.
     *
     * @param position to check
     * @return true, is in the Seventh Column, otherwise not.
     */
    public boolean isInSeventhColumn(int position) {
        return position % 8 == 6;
    }

    /**
     * Getter of the value of the piece
     */
    public int getValue() {
        return this.color.isWhite() ? this.value : -1 * this.value;
    }

    @Override
    public String toString() {
        return this.color.isWhite() ? this.name : this.name.toLowerCase();
    }

    /**
     * Getter of the symbol of the piece
     */
    public abstract String getSymbol();

}
