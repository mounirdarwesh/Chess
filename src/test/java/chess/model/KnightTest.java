package chess.model;

import chess.Attributes;
import chess.controller.Move;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test of the class Knight
 *
 * @author Gruppe 45
 */
public class KnightTest {
    //Empty board
    private static final String EMPTY_FEN = "8/8/8/8/8/8/8/8";
    Board board = new Board();
    Piece knightW = new Knight(44, Attributes.Color.WHITE, board);
    Piece knightB = new Knight(44, Attributes.Color.BLACK, board);

    /**
     * Test if white knight shown with uppercase "N"
     */
    @Test
    public void testToStringW() {
        assertEquals("N", knightW.toString());
    }

    /**
     * Test if black knight shown with lower "n"
     */
    @Test
    public void testToStringB() {
        assertEquals("n", knightB.toString());
    }

    /**
     * Test all the legal moves of a white knight in a position in the middle of the board
     */
    @Test
    public void testCalculateLegalMovesMiddleW() {

        ArrayList<Move> expected = new ArrayList<>();
        Piece knightMiddleW = new Knight(27, Attributes.Color.WHITE, board);
        Move m1 = new Move.NormalMove(board, knightMiddleW, 10);
        expected.add(m1);
        Move m2 = new Move.NormalMove(board, knightMiddleW, 12);
        expected.add(m2);
        Move m3 = new Move.NormalMove(board, knightMiddleW, 17);
        expected.add(m3);
        Move m4 = new Move.NormalMove(board, knightMiddleW, 21);
        expected.add(m4);
        Move m5 = new Move.NormalMove(board, knightMiddleW, 33);
        expected.add(m5);
        Move m6 = new Move.NormalMove(board, knightMiddleW, 37);
        expected.add(m6);
        Move m7 = new Move.NormalMove(board, knightMiddleW, 42);
        expected.add(m7);
        Move m8 = new Move.NormalMove(board, knightMiddleW, 44);
        expected.add(m8);
        board.setPiecesOnBoard(EMPTY_FEN);
        board.setPiece(knightMiddleW);
        knightMiddleW.calculateLegalMoves();
        assertEquals(expected.toString(), knightMiddleW.getAllLegalMoves().toString());
    }

    /**
     * Test all the legal moves of a black knight in a position in the middle of the board
     */
    @Test
    public void testCalculateLegalMovesMiddleB() {

        ArrayList<Move> expected = new ArrayList<>();
        Piece knightMiddleB = new Knight(27, Attributes.Color.BLACK, board);
        Move m1 = new Move.NormalMove(board, knightMiddleB, 10);
        expected.add(m1);
        Move m2 = new Move.NormalMove(board, knightMiddleB, 12);
        expected.add(m2);
        Move m3 = new Move.NormalMove(board, knightMiddleB, 17);
        expected.add(m3);
        Move m4 = new Move.NormalMove(board, knightMiddleB, 21);
        expected.add(m4);
        Move m5 = new Move.NormalMove(board, knightMiddleB, 33);
        expected.add(m5);
        Move m6 = new Move.NormalMove(board, knightMiddleB, 37);
        expected.add(m6);
        Move m7 = new Move.NormalMove(board, knightMiddleB, 42);
        expected.add(m7);
        Move m8 = new Move.NormalMove(board, knightMiddleB, 44);
        expected.add(m8);
        board.setPiecesOnBoard(EMPTY_FEN);
        board.setPiece(knightMiddleB);
        knightMiddleB.calculateLegalMoves();
        assertEquals(expected.toString(), knightMiddleB.getAllLegalMoves().toString());
    }


    /**
     * Test all the legal moves of a white knight in a position on the edge of the board
     */
    @Test
    public void testCalculateLegalMovesEdgeW() {

        ArrayList<Move> expected = new ArrayList<>();
        Piece knightEdgeW = new Knight(0, Attributes.Color.WHITE, board);
        Move m1 = new Move.NormalMove(board, knightEdgeW, 10);
        expected.add(m1);
        Move m2 = new Move.NormalMove(board, knightEdgeW, 17);
        expected.add(m2);

        board.setPiecesOnBoard(EMPTY_FEN);
        board.setPiece(knightEdgeW);
        knightEdgeW.calculateLegalMoves();
        assertEquals(expected.toString(), knightEdgeW.getAllLegalMoves().toString());
    }

    /**
     * Test all the legal moves of a black knight in a position on the edge of the board
     */
    @Test
    public void testCalculateLegalMovesEdgeB() {

        ArrayList<Move> expected = new ArrayList<>();
        Piece knightEdgeB = new Knight(63, Attributes.Color.BLACK, board);
        Move m1 = new Move.NormalMove(board, knightEdgeB, 46);
        expected.add(m1);
        Move m2 = new Move.NormalMove(board, knightEdgeB, 53);
        expected.add(m2);

        board.setPiecesOnBoard(EMPTY_FEN);
        board.setPiece(knightEdgeB);
        knightEdgeB.calculateLegalMoves();
        assertEquals(expected.toString(), knightEdgeB.getAllLegalMoves().toString());
    }

    /**
     * Test which legal moves are exist if a white knight blocked by other white pieces
     */
    @Test
    public void testCalculateLegalMovesBlockByTeammateW() {
        ArrayList<Move> expected = new ArrayList<>();
        Piece knightBlockByTeammateW = new Knight(54, Attributes.Color.WHITE, board);
        Piece rook = new Rook(60, Attributes.Color.WHITE, board);
        Piece pawn = new Pawn(44, Attributes.Color.WHITE, board);
        Piece queen = new Queen(37, Attributes.Color.WHITE, board);
        Move m1 = new Move.NormalMove(board, knightBlockByTeammateW, 39);
        expected.add(m1);
        board.setPiecesOnBoard(EMPTY_FEN);
        board.setPiece(rook);
        board.setPiece(knightBlockByTeammateW);
        board.setPiece(pawn);
        board.setPiece(queen);
        knightBlockByTeammateW.calculateLegalMoves();
        assertEquals(expected.toString(), knightBlockByTeammateW.getAllLegalMoves().toString());
    }

    /**
     * Test which legal moves are exist if a black knight blocked by other black pieces
     */
    @Test
    public void testCalculateLegalMovesBlockByTeammateB() {
        ArrayList<Move> expected = new ArrayList<>();
        Piece knightBlockByTeammateB = new Knight(54, Attributes.Color.BLACK, board);
        Piece rook = new Rook(60, Attributes.Color.BLACK, board);
        Piece pawn = new Pawn(44, Attributes.Color.BLACK, board);
        Piece queen = new Queen(37, Attributes.Color.BLACK, board);
        Move m1 = new Move.NormalMove(board, knightBlockByTeammateB, 39);
        expected.add(m1);
        board.setPiecesOnBoard(EMPTY_FEN);
        board.setPiece(rook);
        board.setPiece(knightBlockByTeammateB);
        board.setPiece(pawn);
        board.setPiece(queen);
        knightBlockByTeammateB.calculateLegalMoves();
        assertEquals(expected.toString(), knightBlockByTeammateB.getAllLegalMoves().toString());
    }

    /**
     * Test if a white knight can capture a black piece
     */
    @Test
    public void testCalculateLegalMovesCaptureEnemyW() {
        ArrayList<Move> expected = new ArrayList<>();
        Piece knightCaptureEnemyW = new Knight(54, Attributes.Color.WHITE, board);
        Piece rook = new Rook(60, Attributes.Color.BLACK, board);
        Piece pawn = new Pawn(44, Attributes.Color.BLACK, board);
        Piece queen = new Queen(37, Attributes.Color.BLACK, board);
        Move m1 = new Move.CaptureMove(board, knightCaptureEnemyW, 37);
        expected.add(m1);
        Move m2 = new Move.NormalMove(board, knightCaptureEnemyW, 39);
        expected.add(m2);
        Move m3 = new Move.CaptureMove(board, knightCaptureEnemyW, 44);
        expected.add(m3);
        Move m4 = new Move.CaptureMove(board, knightCaptureEnemyW, 60);
        expected.add(m4);

        board.setPiecesOnBoard(EMPTY_FEN);
        board.setPiece(rook);
        board.setPiece(knightCaptureEnemyW);
        board.setPiece(pawn);
        board.setPiece(queen);
        knightCaptureEnemyW.calculateLegalMoves();
        assertEquals(expected.toString(), knightCaptureEnemyW.getAllLegalMoves().toString());
    }

    /**
     * Test if a black knight can capture a white piece
     */
    @Test
    public void testCalculateLegalMovesCaptureEnemyB() {
        ArrayList<Move> expected = new ArrayList<>();
        Piece knightCaptureEnemyB = new Knight(54, Attributes.Color.BLACK, board);
        Piece rook = new Rook(60, Attributes.Color.WHITE, board);
        Piece pawn = new Pawn(44, Attributes.Color.WHITE, board);
        Piece queen = new Queen(37, Attributes.Color.WHITE, board);
        Move m1 = new Move.CaptureMove(board, knightCaptureEnemyB, 37);
        expected.add(m1);
        Move m2 = new Move.NormalMove(board, knightCaptureEnemyB, 39);
        expected.add(m2);
        Move m3 = new Move.CaptureMove(board, knightCaptureEnemyB, 44);
        expected.add(m3);
        Move m4 = new Move.CaptureMove(board, knightCaptureEnemyB, 60);
        expected.add(m4);

        board.setPiecesOnBoard(EMPTY_FEN);
        board.setPiece(rook);
        board.setPiece(knightCaptureEnemyB);
        board.setPiece(pawn);
        board.setPiece(queen);
        knightCaptureEnemyB.calculateLegalMoves();
        assertEquals(expected.toString(), knightCaptureEnemyB.getAllLegalMoves().toString());
    }
}