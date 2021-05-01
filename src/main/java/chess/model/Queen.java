package chess.model;
import chess.Attributes.Color;

/**
 * @author Ahmad Mohammad
 *
 */
public class Queen extends Piece {
	
	/**
	 *  The name of the piece 
	 */
	protected String name = "Q";
	
	/**
	 * 
	 * @param position
	 * @param color
	 * @param board
	 */
	public Queen(int position, Color color, Board board) {
		super(position, color, board);
	}

	@Override
	public String toString() {
		return this.color.isWhite() ? this.name : this.name.toLowerCase();
	}

	@Override
	public void calculateLegalMoves() {
	}

}
