package chess.model;
import java.util.ArrayList;
import chess.Attributes.Color;
import chess.controller.Move;

/**
 * @author Ahmad Mohammad
 * The Piece class 
 */
public abstract class Piece {
	
	/**
	 *  The position of the piece on the board
	 */
	protected int position;

	/**
	 *  The color of the piece
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
	 * @param  position  The position of the piece 
	 * @param  type  The type of the piece
	 * @param board  The board 
	 */
	Piece(int postion, Color color, Board board){
		this.position = postion;
		this.color = color;
		this.board = board;
		
	}
	
	/**
	 * A method to calculate all of the pieces legal moves
	 * @param  Board  The Board, on which the piece is on
	 */
	public abstract void calculateLegalMoves();
	
	/**
	 * Getter of the position of the piece
	 * @return the position
	 */
	public int getPosition() {
		return position;
	}

	/**
	 * Setter of the position of the piece
	 * @param position the position to set
	 */
	public void setPosition(int position) {
		this.position = position;
	}
	
	/**
	 * Getter for the type of the piece
	 * @return the type
	 */
	public Color getColor() {
		return color;
	}
	
	/**
	 * Getter for the first move boolean
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
	
	/*
	 * A method to return true if the new or
	 * the current position is on the board
	 */
	public boolean isPositionInBounds(int position) {
		return position >= 0 && position < 64;
	}
}
