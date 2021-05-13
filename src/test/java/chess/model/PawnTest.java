package chess.model;

import chess.Attributes;
import chess.controller.Move;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class PawnTest {

	private static final String EMPTY_FEN = "8/8/8/8/8/8/8/8";
	Board board = new Board();
	Piece pawn = new Pawn(12, Attributes.Color.WHITE, board);

	@Test
	public void testToString() {assertEquals("P", pawn.toString());	}

	@Test
	public void testCalculateLegalMovesStartPosition() {

		ArrayList<Move> expected = new ArrayList<>();
		Piece pawnStartPosition = new Pawn(12, Attributes.Color.WHITE, board);
		Move m1 = new Move.NormalMove(board,pawnStartPosition,20);
		expected.add(m1);
		Move m2 = new Move.DoublePawnMove(board,pawnStartPosition,28);
		expected.add(m2);


		board.setPiecesOnBoard(EMPTY_FEN);
		board.setPiece(pawnStartPosition);
		pawnStartPosition.calculateLegalMoves();
		ArrayList<Move> allLegal = pawnStartPosition.getAllLegalMoves();
		assertEquals(expected.toString(), pawnStartPosition.getAllLegalMoves().toString());
	}

	@Test
	public void testCalculateLegalMovesMiddle() {

		ArrayList<Move> expected = new ArrayList<>();
		Piece pawnMiddle = new Pawn(28, Attributes.Color.WHITE, board);
		Move m1 = new Move.NormalMove(board,pawnMiddle,36);
		expected.add(m1);
		pawnMiddle.isFirstMove = false;
		board.setPiecesOnBoard(EMPTY_FEN);
		board.setPiece(pawnMiddle);
		pawnMiddle.calculateLegalMoves();
		ArrayList<Move> allLegal = pawnMiddle.getAllLegalMoves();
		assertEquals(expected.toString(), pawnMiddle.getAllLegalMoves().toString());
	}

	@Test
	public void testCalculateLegalMovesBlockByTeammate() {
		ArrayList<Move> expected = new ArrayList<>();
		Piece pawnBlockByTeammate = new Pawn(28, Attributes.Color.WHITE, board);
		Piece bishop = new Bishop(36, Attributes.Color.WHITE,board);

		board.setPiecesOnBoard(EMPTY_FEN);
		board.setPiece(bishop);
		board.setPiece(pawnBlockByTeammate);
		pawnBlockByTeammate.calculateLegalMoves();
		ArrayList<Move> allLegal = pawnBlockByTeammate.getAllLegalMoves();
		assertEquals(expected.toString(), pawnBlockByTeammate.getAllLegalMoves().toString());
	}

	@Test
	public void testCalculateLegalMovesCaptureEnemy() {
		ArrayList<Move> expected = new ArrayList<>();
		Piece pawnCaptureEnemy = new Pawn(35, Attributes.Color.WHITE, board);
		Piece bishop = new Bishop(42, Attributes.Color.BLACK,board);
		Piece knight = new Knight(43, Attributes.Color.BLACK,board);
		Piece rook = new Rook(44, Attributes.Color.BLACK,board);

		Move m1 = new Move.CaptureMove(board,pawnCaptureEnemy,42);
		expected.add(m1);
		Move m2 = new Move.CaptureMove(board,pawnCaptureEnemy,44);
		expected.add(m2);

		board.setPiecesOnBoard(EMPTY_FEN);
		board.setPiece(knight);
		board.setPiece(bishop);
		board.setPiece(rook);
		board.setPiece(pawnCaptureEnemy);
		pawnCaptureEnemy.calculateLegalMoves();
		ArrayList<Move> allLegal = pawnCaptureEnemy.getAllLegalMoves();
		assertEquals(expected.toString(), pawnCaptureEnemy.getAllLegalMoves().toString());
	}

	@Test
	public void testCalculateLegalMovesPassant() {
/*		ArrayList<Move> expected = new ArrayList<>();
		Piece pawnPassant = new Pawn(35, Attributes.Color.WHITE, board);
		Piece pawn = new Pawn(34, Attributes.Color.BLACK,board);


		Move m1 = new Move.NormalMove(board,pawnPassant,43);
		expected.add(m1);
		Move m2 = new Move.EnPassantMove(board,pawnPassant,42,34);
		expected.add(m2);

		board.setPiecesOnBoard(EMPTY_FEN);
		board.setPiece(pawn);
		board.setPiece(pawnPassant);
		pawnPassant.calculateLegalMoves();
		ArrayList<Move> allLegal = pawnPassant.getAllLegalMoves();
		assertEquals(expected.toString(), pawnPassant.getAllLegalMoves().toString());*/
	}
}
