package chess.model;
import org.junit.jupiter.api.Test;

import chess.Attributes;
import chess.model.Piece;
import chess.model.Rook;

import static org.junit.jupiter.api.Assertions.*;


public class PawnTest {
	
	Board board = new Board();
	
	
	@Test
	public void testEqual() {
		
		assertEquals(12, board.getPiece(12).getPosition());
		
	}

}
