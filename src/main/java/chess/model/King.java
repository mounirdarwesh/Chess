/**
 * 
 */
package chess.model;

import chess.Attributes.Color;

/**
 * @author TBD
 *
 */
public class King extends Piece{
	
	/**
	 *  The name of the piece 
	 */
	protected String name = "K";

	/**
	 * 
	 * @param postion
	 * @param color
	 * @param board
	 */
	public King(int postion, Color color, Board board) {
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
