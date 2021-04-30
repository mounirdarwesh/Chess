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
	 * @param postion
	 * @param color
	 * @param board
	 */
	public Queen(int postion, Color color, Board board) {
		super(postion, color, board);
	}

	@Override
	public String toString() {
		return this.color.isWhite() ? this.name : this.name.toLowerCase();
	}

	@Override
	public void calculateLegalMoves() {
	}

}
