/**
 * 
 */
package chess.utilities;

import java.util.List;

import chess.Constants;
import chess.Move;

/**
 * @author TBD
 *
 */
public class Knight extends Piece{
	
	// The name of the piece 
	protected String name = "N";

	public Knight(int postion, Constants type) {
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
