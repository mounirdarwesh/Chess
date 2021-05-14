package chess.model;

import chess.Attributes;
import chess.controller.Move;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test of the class Bishop
 *
 * @author Gruppe 45
 */
public class BishopTest {
    //Empty board
    private static final String EMPTY_FEN = "8/8/8/8/8/8/8/8";
    Board board = new Board();
    Piece bishopW = new Bishop(27, Attributes.Color.WHITE, board);
    Piece bishopB = new Bishop(27, Attributes.Color.BLACK, board);

    /**
     * Test if white bishop shown with uppercase "B"
     */
    @Test
    public void testToStringW() {
        assertEquals("B", bishopW.toString());
    }

    /**
     * Test if black bishop shown with lowercase "b"
     */
    @Test
    public void testToStringB() {
        assertEquals("b", bishopB.toString());
    }

    /**
     * Test all the legal moves of a white bishop in a position in the middle of the board
     */
    @Test
    public void testCalculateLegalMovesMiddleW() {

        ArrayList<Move> expected = new ArrayList<>();
        Piece bishopMiddleW = new Bishop(27, Attributes.Color.WHITE, board);
        Move m1 = new Move.NormalMove(board, bishopMiddleW, 18);
        expected.add(m1);
        Move m2 = new Move.NormalMove(board, bishopMiddleW, 9);
        expected.add(m2);
        Move m3 = new Move.NormalMove(board, bishopMiddleW, 0);
        expected.add(m3);
        Move m4 = new Move.NormalMove(board, bishopMiddleW, 20);
        expected.add(m4);
        Move m5 = new Move.NormalMove(board, bishopMiddleW, 13);
        expected.add(m5);
        Move m6 = new Move.NormalMove(board, bishopMiddleW, 6);
        expected.add(m6);
        Move m7 = new Move.NormalMove(board, bishopMiddleW, 34);
        expected.add(m7);
        Move m8 = new Move.NormalMove(board, bishopMiddleW, 41);
        expected.add(m8);
        Move m9 = new Move.NormalMove(board, bishopMiddleW, 48);
        expected.add(m9);
        Move m10 = new Move.NormalMove(board, bishopMiddleW, 36);
        expected.add(m10);
        Move m11 = new Move.NormalMove(board, bishopMiddleW, 45);
        expected.add(m11);
        Move m12 = new Move.NormalMove(board, bishopMiddleW, 54);
        expected.add(m12);
        Move m13 = new Move.NormalMove(board, bishopMiddleW, 63);
        expected.add(m13);
        board.setPiecesOnBoard(EMPTY_FEN);
        board.setPiece(bishopMiddleW);
        bishopMiddleW.calculateLegalMoves();
        assertEquals(expected.toString(), bishopMiddleW.getAllLegalMoves().toString());
    }

    /**
     * Test all the legal moves of a black bishop in a position in the middle of the board
     */
    @Test
    public void testCalculateLegalMovesMiddleB() {

        ArrayList<Move> expected = new ArrayList<>();
        Piece bishopMiddleB = new Bishop(27, Attributes.Color.BLACK, board);
        Move m1 = new Move.NormalMove(board, bishopMiddleB, 18);
        expected.add(m1);
        Move m2 = new Move.NormalMove(board, bishopMiddleB, 9);
        expected.add(m2);
        Move m3 = new Move.NormalMove(board, bishopMiddleB, 0);
        expected.add(m3);
        Move m4 = new Move.NormalMove(board, bishopMiddleB, 20);
        expected.add(m4);
        Move m5 = new Move.NormalMove(board, bishopMiddleB, 13);
        expected.add(m5);
        Move m6 = new Move.NormalMove(board, bishopMiddleB, 6);
        expected.add(m6);
        Move m7 = new Move.NormalMove(board, bishopMiddleB, 34);
        expected.add(m7);
        Move m8 = new Move.NormalMove(board, bishopMiddleB, 41);
        expected.add(m8);
        Move m9 = new Move.NormalMove(board, bishopMiddleB, 48);
        expected.add(m9);
        Move m10 = new Move.NormalMove(board, bishopMiddleB, 36);
        expected.add(m10);
        Move m11 = new Move.NormalMove(board, bishopMiddleB, 45);
        expected.add(m11);
        Move m12 = new Move.NormalMove(board, bishopMiddleB, 54);
        expected.add(m12);
        Move m13 = new Move.NormalMove(board, bishopMiddleB, 63);
        expected.add(m13);
        board.setPiecesOnBoard(EMPTY_FEN);
        board.setPiece(bishopMiddleB);
        bishopMiddleB.calculateLegalMoves();
        assertEquals(expected.toString(), bishopMiddleB.getAllLegalMoves().toString());
    }

    /**
     * Test all the legal moves of a white bishop in a position on the edge of the board
     */
    @Test
    public void testCalculateLegalMovesEdgeW() {

        ArrayList<Move> expected = new ArrayList<>();
        Piece bishopEdgeW = new Bishop(0, Attributes.Color.WHITE, board);
        Move m1 = new Move.NormalMove(board, bishopEdgeW, 9);
        expected.add(m1);
        Move m2 = new Move.NormalMove(board, bishopEdgeW, 18);
        expected.add(m2);
        Move m3 = new Move.NormalMove(board, bishopEdgeW, 27);
        expected.add(m3);
        Move m4 = new Move.NormalMove(board, bishopEdgeW, 36);
        expected.add(m4);
        Move m5 = new Move.NormalMove(board, bishopEdgeW, 45);
        expected.add(m5);
        Move m6 = new Move.NormalMove(board, bishopEdgeW, 54);
        expected.add(m6);
        Move m7 = new Move.NormalMove(board, bishopEdgeW, 63);
        expected.add(m7);
        board.setPiecesOnBoard(EMPTY_FEN);
        board.setPiece(bishopEdgeW);
        bishopEdgeW.calculateLegalMoves();
        assertEquals(expected.toString(), bishopEdgeW.getAllLegalMoves().toString());
    }

    /**
     * Test all the legal moves of a black bishop in a position on the edge of the board
     */
    @Test
    public void testCalculateLegalMovesEdgeB() {

        ArrayList<Move> expected = new ArrayList<>();
        Piece bishopEdgeB = new Bishop(63, Attributes.Color.BLACK, board);
        Move m1 = new Move.NormalMove(board, bishopEdgeB, 54);
        expected.add(m1);
        Move m2 = new Move.NormalMove(board, bishopEdgeB, 45);
        expected.add(m2);
        Move m3 = new Move.NormalMove(board, bishopEdgeB, 36);
        expected.add(m3);
        Move m4 = new Move.NormalMove(board, bishopEdgeB, 27);
        expected.add(m4);
        Move m5 = new Move.NormalMove(board, bishopEdgeB, 18);
        expected.add(m5);
        Move m6 = new Move.NormalMove(board, bishopEdgeB, 9);
        expected.add(m6);
        Move m7 = new Move.NormalMove(board, bishopEdgeB, 0);
        expected.add(m7);
        board.setPiecesOnBoard(EMPTY_FEN);
        board.setPiece(bishopEdgeB);
        bishopEdgeB.calculateLegalMoves();
        assertEquals(expected.toString(), bishopEdgeB.getAllLegalMoves().toString());
    }

    /**
     * Test which legal moves are exist if a white bishop blocked by other white pieces
     */
    @Test
    public void testCalculateLegalMovesBlockByTeammateW() {
        ArrayList<Move> expected = new ArrayList<>();
        Piece bishopBlockByTeammateW = new Bishop(54, Attributes.Color.WHITE, board);
        Piece rook = new Rook(63, Attributes.Color.WHITE, board);
        Piece knight = new Knight(47, Attributes.Color.WHITE, board);
        Piece pawn = new Pawn(45, Attributes.Color.WHITE, board);
        Move m1 = new Move.NormalMove(board, bishopBlockByTeammateW, 61);
        expected.add(m1);
        board.setPiecesOnBoard(EMPTY_FEN);
        board.setPiece(rook);
        board.setPiece(knight);
        board.setPiece(pawn);
        board.setPiece(bishopBlockByTeammateW);
        bishopBlockByTeammateW.calculateLegalMoves();
        assertEquals(expected.toString(), bishopBlockByTeammateW.getAllLegalMoves().toString());
    }

    /**
     * Test which legal moves are exist if a black bishop blocked by other black pieces
     */
    @Test
    public void testCalculateLegalMovesBlockByTeammateB() {
        ArrayList<Move> expected = new ArrayList<>();
        Piece bishopBlockByTeammateB = new Bishop(54, Attributes.Color.BLACK, board);
        Piece rook = new Rook(63, Attributes.Color.BLACK, board);
        Piece knight = new Knight(47, Attributes.Color.BLACK, board);
        Piece pawn = new Pawn(45, Attributes.Color.BLACK, board);
        Move m1 = new Move.NormalMove(board, bishopBlockByTeammateB, 61);
        expected.add(m1);
        board.setPiecesOnBoard(EMPTY_FEN);
        board.setPiece(rook);
        board.setPiece(knight);
        board.setPiece(pawn);
        board.setPiece(bishopBlockByTeammateB);
        bishopBlockByTeammateB.calculateLegalMoves();
        assertEquals(expected.toString(), bishopBlockByTeammateB.getAllLegalMoves().toString());
    }

    /**
     * Test if a white bishop can capture a black piece
     */
    @Test
    public void testCalculateLegalMovesCaptureEnemyW() {
        ArrayList<Move> expected = new ArrayList<>();
        Piece bishopCaptureEnemyW = new Bishop(14, Attributes.Color.WHITE, board);
        Piece rook = new Rook(7, Attributes.Color.BLACK, board);
        Piece knight = new Knight(28, Attributes.Color.BLACK, board);
        Move m1 = new Move.NormalMove(board, bishopCaptureEnemyW, 5);
        expected.add(m1);
        Move m2 = new Move.CaptureMove(board, bishopCaptureEnemyW, 7);
        expected.add(m2);
        Move m3 = new Move.NormalMove(board, bishopCaptureEnemyW, 21);
        expected.add(m3);
        Move m4 = new Move.CaptureMove(board, bishopCaptureEnemyW, 28);
        expected.add(m4);
        Move m5 = new Move.NormalMove(board, bishopCaptureEnemyW, 23);
        expected.add(m5);
        board.setPiecesOnBoard(EMPTY_FEN);
        board.setPiece(rook);
        board.setPiece(knight);
        board.setPiece(bishopCaptureEnemyW);
        bishopCaptureEnemyW.calculateLegalMoves();
        assertEquals(expected.toString(), bishopCaptureEnemyW.getAllLegalMoves().toString());
    }

    /**
     * Test if a black bishop can capture a white piece
     */
    @Test
    public void testCalculateLegalMovesCaptureEnemyB() {
        ArrayList<Move> expected = new ArrayList<>();
        Piece bishopCaptureEnemyB = new Bishop(14, Attributes.Color.BLACK, board);
        Piece rook = new Rook(7, Attributes.Color.WHITE, board);
        Piece knight = new Knight(28, Attributes.Color.WHITE, board);
        Move m1 = new Move.NormalMove(board, bishopCaptureEnemyB, 5);
        expected.add(m1);
        Move m2 = new Move.CaptureMove(board, bishopCaptureEnemyB, 7);
        expected.add(m2);
        Move m3 = new Move.NormalMove(board, bishopCaptureEnemyB, 21);
        expected.add(m3);
        Move m4 = new Move.CaptureMove(board, bishopCaptureEnemyB, 28);
        expected.add(m4);
        Move m5 = new Move.NormalMove(board, bishopCaptureEnemyB, 23);
        expected.add(m5);
        board.setPiecesOnBoard(EMPTY_FEN);
        board.setPiece(rook);
        board.setPiece(knight);
        board.setPiece(bishopCaptureEnemyB);
        bishopCaptureEnemyB.calculateLegalMoves();
        assertEquals(expected.toString(), bishopCaptureEnemyB.getAllLegalMoves().toString());
    }
}