package chess.model;

import chess.Attributes;
import chess.controller.Move;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Gruppe 45
 */
public class PawnTest {
	//Empty board
	private static final String EMPTY_FEN = "8/8/8/8/8/8/8/8";
	Board board = new Board();
	Piece pawnW = new Pawn(12, Attributes.Color.WHITE, board);
	Piece pawnB = new Pawn(14, Attributes.Color.BLACK,board);
	/**
	 *  Test if white pawn shown with uppercase "P"
	 */
	@Test
	public void testToStringW() {assertEquals("P", pawnW.toString());	}

	/**
	 * test if black pawn shown with lowercase "p"
	 */
	@Test
	public void testToStringB(){assertEquals("p", pawnB.toString());}

	/**
	 * Test all the legal moves of a white pawn in a start position
	 */
	@Test
	public void testCalculateLegalMovesStartPositionW() {

		ArrayList<Move> expected = new ArrayList<>();
		Piece pawnStartPosition = new Pawn(12, Attributes.Color.WHITE, board);
		Move m1 = new Move.NormalMove(board,pawnStartPosition,20);
		expected.add(m1);
		Move m2 = new Move.DoublePawnMove(board,pawnStartPosition,28);
		expected.add(m2);

		pawnStartPosition.isFirstMove = true;
		board.setPiecesOnBoard(EMPTY_FEN);
		board.setPiece(pawnStartPosition);
		pawnStartPosition.calculateLegalMoves();
		ArrayList<Move> allLegal = pawnStartPosition.getAllLegalMoves();
		assertEquals(expected.toString(), pawnStartPosition.getAllLegalMoves().toString());
	}

	/**
	 * Test all the legal moves of a black pawn in a start position
	 */
	@Test
	public void testCalculateLegalMovesStartPositionB(){
		ArrayList<Move> expected = new ArrayList<>();
		Piece pawnStartPosition = new Pawn(52, Attributes.Color.BLACK, board);
		Move m1 = new Move.NormalMove(board,pawnStartPosition,44);
		expected.add(m1);
		Move m2 = new Move.DoublePawnMove(board,pawnStartPosition,36);
		expected.add(m2);

		pawnStartPosition.isFirstMove = true;
		board.setPiecesOnBoard(EMPTY_FEN);
		board.setPiece(pawnStartPosition);
		pawnStartPosition.calculateLegalMoves();
		ArrayList<Move> allLegal = pawnStartPosition.getAllLegalMoves();
		assertEquals(expected.toString(), pawnStartPosition.getAllLegalMoves().toString());
	}

	/**
	 * Test all the legal moves of a white pawn in a position in the middle of the board
	 */
	@Test
	public void testCalculateLegalMovesMiddleW() {

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

	/**
	 * Test all the legal moves of a white pawn in a position in the middle of the board
	 */
	@Test
	public void testCalculateLegalMovesMiddleB() {

		ArrayList<Move> expected = new ArrayList<>();
		Piece pawnMiddle = new Pawn(34, Attributes.Color.BLACK, board);
		Move m1 = new Move.NormalMove(board,pawnMiddle,26);
		expected.add(m1);
		pawnMiddle.isFirstMove = false;
		board.setPiecesOnBoard(EMPTY_FEN);
		board.setPiece(pawnMiddle);
		pawnMiddle.calculateLegalMoves();
		ArrayList<Move> allLegal = pawnMiddle.getAllLegalMoves();
		assertEquals(expected.toString(), pawnMiddle.getAllLegalMoves().toString());
	}

	/**
	 * Test which legal moves are exist if a white pawn blocked by other white pieces
	 */
	@Test
	public void testCalculateLegalMovesBlockByTeammateW() {
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

	/**
	 * Test which legal moves are exist if a black pawn blocked by other black pieces
	 */
	@Test
	public void testCalculateLegalMovesBlockByTeammateB() {
		ArrayList<Move> expected = new ArrayList<>();
		Piece pawnBlockByTeammate = new Pawn(38, Attributes.Color.BLACK, board);
		Piece bishop = new Bishop(30, Attributes.Color.BLACK,board);

		board.setPiecesOnBoard(EMPTY_FEN);
		board.setPiece(bishop);
		board.setPiece(pawnBlockByTeammate);
		pawnBlockByTeammate.calculateLegalMoves();
		ArrayList<Move> allLegal = pawnBlockByTeammate.getAllLegalMoves();
		assertEquals(expected.toString(), pawnBlockByTeammate.getAllLegalMoves().toString());
	}

	/**
	 * Test if a white pawn can capture a black piece
	 */
	@Test
	public void testCalculateLegalMovesCaptureEnemyW() {
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

	/**
	 * Test if a black pawn can capture a white piece
	 */
	@Test
	public void testCalculateLegalMovesCaptureEnemyB() {
		ArrayList<Move> expected = new ArrayList<>();
		Piece pawnCaptureEnemy = new Pawn(27, Attributes.Color.BLACK, board);
		Piece bishop = new Bishop(18, Attributes.Color.WHITE,board);
		Piece knight = new Knight(20, Attributes.Color.WHITE,board);
		Piece rook = new Rook(19, Attributes.Color.WHITE,board);

		Move m1 = new Move.CaptureMove(board,pawnCaptureEnemy,20);
		expected.add(m1);
		Move m2 = new Move.CaptureMove(board,pawnCaptureEnemy,18);
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
/*	@Test
	public void testCalculateLegalMovesPassant() {
		ArrayList<Move> expected = new ArrayList<>();
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
		assertEquals(expected.toString(), pawnPassant.getAllLegalMoves().toString());
	}*/
}
