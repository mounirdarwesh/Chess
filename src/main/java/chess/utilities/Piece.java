package chess.utilities;
import java.util.List;
import chess.Constants;
import chess.Move;

/**
 * @author Ahmad Mohammad
 * The Piece class 
 */
public abstract class Piece {
	
	// The position of the piece on the board
	protected int position;

	// The type of the piece
	protected Constants type;
	

	/*
	 * The constructor of the Piece Class
	 * @param  position  The position of the piece 
	 * @param  type  The type of the piece
	 */
	Piece(int postion, Constants type){
		this.position = postion;
		this.type = type;
		
	}
	
	/*
	 * A method to calculate all of the pieces legal moves
	 * @param  Board  The Board, on which the piece is on
	 */
	public abstract List<Move> calculateLegalMoves(Board board);
	
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
	public Constants getType() {
		return type;
	}
}
