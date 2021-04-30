/**
 * 
 */
package chess.model;

import chess.Attributes.Color;

/**
 * @author TBD
 *
 */
public class Knight extends Piece{
	
	/**
	 *  The name of the piece 
	 */
	protected String name = "N";

	/**
	 * 
	 * @param postion
	 * @param color
	 * @param board
	 */
	public Knight(int postion, Color color, Board board) {
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
