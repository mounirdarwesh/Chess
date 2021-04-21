/**
 * 
 */
package chess.model.pieces;

import java.util.List;

import chess.Constants;
import chess.model.Board;
import chess.model.Move;

/**
 * @author TBD
 *
 */
public class Bishop extends Piece{
	
	// The name of the piece 
	protected String name = "B";

	public Bishop(int postion, Constants type) {
		super(postion, type);
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Move> calculateLegalMoves(Board board) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public String toString() {
		return this.type.isWhite() ? this.name : this.name.toLowerCase();
	}

}
