/**
 * 
 */
package chess.model;

import chess.Attributes.Color;

/**
 * @author TBD
 *
 */
public class Bishop extends Piece{
	
	/**
	 *  The name of the piece 
	 */
	protected String name = "B";

	/**
	 * 
	 * @param postion
	 * @param color
	 * @param board
	 */
	public Bishop(int postion, Color color, Board board) {
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
