/**
 * 
 */
package chess.model;

import java.util.*;
import chess.Attributes;
import chess.controller.Move;

/**
 * @author TBD
 *
 */
public class King extends Piece{
	
	// The name of the piece 
	protected String name = "K";

	public King(int postion, Attributes type, Board board) {
		super(postion, type, board);
		// TODO Auto-generated constructor stub
	}

	
	@Override
	public String toString() {
		return this.type.isWhite() ? this.name : this.name.toLowerCase();
	}


	@Override
	public void calculateLegalMoves() {
		// TODO Auto-generated method stub
	}

}
