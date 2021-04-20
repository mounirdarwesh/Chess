package chess.utilities;
import java.util.*;
import chess.Constants;
import chess.Move;

/**
 * @author Ahmad Mohammad
 *
 */
public class Queen extends Piece {
	
	// The name of the piece 
	protected String name = "Q";
	
	public Queen(int postion, Constants type) {
		super(postion, type);
	}

	@Override
	public List<Move> calculateLegalMoves(Board board) {
		return null;
	}
	
	@Override
	public String toString() {
		return this.type.isWhite() ? this.name : this.name.toLowerCase();
	}

}
