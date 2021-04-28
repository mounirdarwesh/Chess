package chess.model;
import java.util.*;
import chess.Attributes;
import chess.controller.Move;

/**
 * @author Ahmad Mohammad
 *
 */
public class Queen extends Piece {
	
	// The name of the piece 
	protected String name = "Q";
	
	public Queen(int postion, Attributes type, Board board) {
		super(postion, type, board);
	}

	@Override
	public String toString() {
		return this.type.isWhite() ? this.name : this.name.toLowerCase();
	}

	@Override
	public void calculateLegalMoves() {
	}

}
