package chess.model.pieces;
import org.junit.jupiter.api.Test;

import chess.Constants;
import chess.model.pieces.Piece;
import chess.model.pieces.Rook;

import static org.junit.jupiter.api.Assertions.*;


public class PieceTest {
	

	
	@Test
	public void testEqual() {
		Piece piece = new Rook(0, Constants.WHITE);
		assertEquals(0, piece.getPosition());
		
	}

}
