/**
 * 
 */
package chess.model;

import chess.Attributes.Color;

/**
 * @author TBD
 *
 */
public class Rook extends Piece {
	
	/**
	 *  The name of the piece 
	 */
	protected String name = "R";

	/**
	 * 
	 * @param postion
	 * @param color
	 * @param board
	 */
	public Rook(int postion, Color color, Board board) {
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
