package chess.model;
import java.util.ArrayList;
import java.util.List;
import chess.Attributes;
import chess.controller.Move;

/**
 * @author Ahmad Mohammad
 * The Piece class 
 */
public abstract class Piece {
	
	// The position of the piece on the board
	protected int position;

	// The type of the piece
	protected Attributes type;
	
	/**
	 * The Board of the game 
	 */
	protected Board board;
	
	/**
	 * Legal moves of the piece
	 */
	protected ArrayList<Move> allLegalMoves;
	
	/**
	 * 
	 */
	protected boolean isFirstMove = true;

	/**
	 * The constructor of the Piece Class
	 * @param  position  The position of the piece 
	 * @param  type  The type of the piece
	 */
	Piece(int postion, Attributes type, Board board){
		this.position = postion;
		this.type = type;
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
	public Attributes getType() {
		return type;
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
}
