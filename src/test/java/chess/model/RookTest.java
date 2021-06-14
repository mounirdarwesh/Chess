package chess.model;

import chess.Attributes;
import chess.controller.Move;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;


/**
 * Test of the class Rook
 *
 * @author Gruppe 45
 */
public class RookTest {
    //Empty board
    private static final String EMPTY_FEN = "8/8/8/8/8/8/8/8";
    Board board = new Board();
    Piece rookW = new Rook(16, Attributes.Color.WHITE, board);
    Piece rookB = new Rook(16, Attributes.Color.BLACK, board);

    /**
     * Test if white rook shown with uppercase "R"
     */
    @Test
    public void testToStringW() {
        assertEquals("R", rookW.toString());
    }

    /**
     * Test if black rook shown with lowercase "b"
     */
    @Test
    public void testToStringB() {
        assertEquals("r", rookB.toString());
    }

    /**
     * Test all the legal moves of a white bishop in a position in the middle of the board
     */
    @Test
    public void testCalculateLegalMovesMiddleW() {
        ArrayList<Move> expected = new ArrayList<>();
        Piece rookMiddleW = new Rook(35, Attributes.Color.WHITE, board);
        Move m1 = new Move.NormalMove(board, rookMiddleW, 27);
        expected.add(m1);
        Move m2 = new Move.NormalMove(board, rookMiddleW, 19);
        expected.add(m2);
        Move m3 = new Move.NormalMove(board, rookMiddleW, 11);
        expected.add(m3);
        Move m4 = new Move.NormalMove(board, rookMiddleW, 3);
        expected.add(m4);
        Move m5 = new Move.NormalMove(board, rookMiddleW, 34);
        expected.add(m5);
        Move m6 = new Move.NormalMove(board, rookMiddleW, 33);
        expected.add(m6);
        Move m7 = new Move.NormalMove(board, rookMiddleW, 32);
        expected.add(m7);
        Move m8 = new Move.NormalMove(board, rookMiddleW, 36);
        expected.add(m8);
        Move m9 = new Move.NormalMove(board, rookMiddleW, 37);
        expected.add(m9);
        Move m10 = new Move.NormalMove(board, rookMiddleW, 38);
        expected.add(m10);
        Move m11 = new Move.NormalMove(board, rookMiddleW, 39);
        expected.add(m11);
        Move m12 = new Move.NormalMove(board, rookMiddleW, 43);
        expected.add(m12);
        Move m13 = new Move.NormalMove(board, rookMiddleW, 51);
        expected.add(m13);
        Move m14 = new Move.NormalMove(board, rookMiddleW, 59);
        expected.add(m14);
        board.setPiecesOnBoard(EMPTY_FEN);
        board.setPiece(rookMiddleW);
        rookMiddleW.calculateLegalMoves();
        assertEquals(expected.toString(), rookMiddleW.getAllLegalMoves().toString());
    }

    /**
     * Test all the legal moves of a black rook in a position in the middle of the board
     */
    @Test
    public void testCalculateLegalMovesMiddleB() {

        ArrayList<Move> expected = new ArrayList<>();
        Piece rookMiddleB = new Rook(35, Attributes.Color.BLACK, board);
        Move m1 = new Move.NormalMove(board, rookMiddleB, 27);
        expected.add(m1);
        Move m2 = new Move.NormalMove(board, rookMiddleB, 19);
        expected.add(m2);
        Move m3 = new Move.NormalMove(board, rookMiddleB, 11);
        expected.add(m3);
        Move m4 = new Move.NormalMove(board, rookMiddleB, 3);
        expected.add(m4);
        Move m5 = new Move.NormalMove(board, rookMiddleB, 34);
        expected.add(m5);
        Move m6 = new Move.NormalMove(board, rookMiddleB, 33);
        expected.add(m6);
        Move m7 = new Move.NormalMove(board, rookMiddleB, 32);
        expected.add(m7);
        Move m8 = new Move.NormalMove(board, rookMiddleB, 36);
        expected.add(m8);
        Move m9 = new Move.NormalMove(board, rookMiddleB, 37);
        expected.add(m9);
        Move m10 = new Move.NormalMove(board, rookMiddleB, 38);
        expected.add(m10);
        Move m11 = new Move.NormalMove(board, rookMiddleB, 39);
        expected.add(m11);
        Move m12 = new Move.NormalMove(board, rookMiddleB, 43);
        expected.add(m12);
        Move m13 = new Move.NormalMove(board, rookMiddleB, 51);
        expected.add(m13);
        Move m14 = new Move.NormalMove(board, rookMiddleB, 59);
        expected.add(m14);
        board.setPiecesOnBoard(EMPTY_FEN);
        board.setPiece(rookMiddleB);
        rookMiddleB.calculateLegalMoves();
        assertEquals(expected.toString(), rookMiddleB.getAllLegalMoves().toString());
    }

    /**
     * Test all the legal moves of a white rook in a position on the edge of the board
     */
    @Test
    public void testCalculateLegalMovesEdgeW() {

        ArrayList<Move> expected = new ArrayList<>();
        Piece rookEdgeW = new Rook(0, Attributes.Color.WHITE, board);
        Move m1 = new Move.NormalMove(board, rookEdgeW, 1);
        expected.add(m1);
        Move m2 = new Move.NormalMove(board, rookEdgeW, 2);
        expected.add(m2);
        Move m3 = new Move.NormalMove(board, rookEdgeW, 3);
        expected.add(m3);
        Move m4 = new Move.NormalMove(board, rookEdgeW, 4);
        expected.add(m4);
        Move m5 = new Move.NormalMove(board, rookEdgeW, 5);
        expected.add(m5);
        Move m6 = new Move.NormalMove(board, rookEdgeW, 6);
        expected.add(m6);
        Move m7 = new Move.NormalMove(board, rookEdgeW, 7);
        expected.add(m7);
        Move m8 = new Move.NormalMove(board, rookEdgeW, 8);
        expected.add(m8);
        Move m9 = new Move.NormalMove(board, rookEdgeW, 16);
        expected.add(m9);
        Move m10 = new Move.NormalMove(board, rookEdgeW, 24);
        expected.add(m10);
        Move m11 = new Move.NormalMove(board, rookEdgeW, 32);
        expected.add(m11);
        Move m12 = new Move.NormalMove(board, rookEdgeW, 40);
        expected.add(m12);
        Move m13 = new Move.NormalMove(board, rookEdgeW, 48);
        expected.add(m13);
        Move m14 = new Move.NormalMove(board, rookEdgeW, 56);
        expected.add(m14);
        board.setPiecesOnBoard(EMPTY_FEN);
        board.setPiece(rookEdgeW);
        rookEdgeW.calculateLegalMoves();
        assertEquals(expected.toString(), rookEdgeW.getAllLegalMoves().toString());
    }

    /**
     * Test all the legal moves of a black bishop in a position on the edge of the board
     */
    @Test
    public void testCalculateLegalMovesEdgeB() {

        ArrayList<Move> expected = new ArrayList<>();
        Piece rookEdgeB = new Rook(0, Attributes.Color.BLACK, board);
        Move m1 = new Move.NormalMove(board, rookEdgeB, 1);
        expected.add(m1);
        Move m2 = new Move.NormalMove(board, rookEdgeB, 2);
        expected.add(m2);
        Move m3 = new Move.NormalMove(board, rookEdgeB, 3);
        expected.add(m3);
        Move m4 = new Move.NormalMove(board, rookEdgeB, 4);
        expected.add(m4);
        Move m5 = new Move.NormalMove(board, rookEdgeB, 5);
        expected.add(m5);
        Move m6 = new Move.NormalMove(board, rookEdgeB, 6);
        expected.add(m6);
        Move m7 = new Move.NormalMove(board, rookEdgeB, 7);
        expected.add(m7);
        Move m8 = new Move.NormalMove(board, rookEdgeB, 8);
        expected.add(m8);
        Move m9 = new Move.NormalMove(board, rookEdgeB, 16);
        expected.add(m9);
        Move m10 = new Move.NormalMove(board, rookEdgeB, 24);
        expected.add(m10);
        Move m11 = new Move.NormalMove(board, rookEdgeB, 32);
        expected.add(m11);
        Move m12 = new Move.NormalMove(board, rookEdgeB, 40);
        expected.add(m12);
        Move m13 = new Move.NormalMove(board, rookEdgeB, 48);
        expected.add(m13);
        Move m14 = new Move.NormalMove(board, rookEdgeB, 56);
        expected.add(m14);
        board.setPiecesOnBoard(EMPTY_FEN);
        board.setPiece(rookEdgeB);
        rookEdgeB.calculateLegalMoves();
        assertEquals(expected.toString(), rookEdgeB.getAllLegalMoves().toString());
    }

    /**
     * Test which legal moves are exist if a white rook blocked by other white pieces
     */
    @Test
    public void testCalculateLegalMovesBlockByTeammateW() {
        ArrayList<Move> expected = new ArrayList<>();
        Piece rookBlockByTeammateW = new Rook(36, Attributes.Color.WHITE, board);
        Piece bishop = new Bishop(35, Attributes.Color.WHITE, board);
        Piece knight = new Knight(37, Attributes.Color.WHITE, board);
        Piece pawn = new Pawn(28, Attributes.Color.WHITE, board);
        Piece queen = new Queen(60, Attributes.Color.WHITE, board);
        Move m1 = new Move.NormalMove(board, rookBlockByTeammateW, 44);
        expected.add(m1);
        Move m2 = new Move.NormalMove(board, rookBlockByTeammateW, 52);
        expected.add(m2);
        board.setPiecesOnBoard(EMPTY_FEN);
        board.setPiece(rookBlockByTeammateW);
        board.setPiece(knight);
        board.setPiece(pawn);
        board.setPiece(bishop);
        board.setPiece(queen);
        rookBlockByTeammateW.calculateLegalMoves();
        assertEquals(expected.toString(), rookBlockByTeammateW.getAllLegalMoves().toString());
    }

    /**
     * Test which legal moves are exist if a black rook blocked by other black pieces
     */
    @Test
    public void testCalculateLegalMovesBlockByTeammateB() {
        ArrayList<Move> expected = new ArrayList<>();
        Piece rookBlockByTeammateB = new Rook(36, Attributes.Color.BLACK, board);
        Piece bishop = new Bishop(35, Attributes.Color.BLACK, board);
        Piece knight = new Knight(37, Attributes.Color.BLACK, board);
        Piece pawn = new Pawn(28, Attributes.Color.BLACK, board);
        Piece queen = new Queen(60, Attributes.Color.BLACK, board);
        Move m1 = new Move.NormalMove(board, rookBlockByTeammateB, 44);
        expected.add(m1);
        Move m2 = new Move.NormalMove(board, rookBlockByTeammateB, 52);
        expected.add(m2);
        board.setPiecesOnBoard(EMPTY_FEN);
        board.setPiece(rookBlockByTeammateB);
        board.setPiece(knight);
        board.setPiece(pawn);
        board.setPiece(bishop);
        board.setPiece(queen);
        rookBlockByTeammateB.calculateLegalMoves();
        assertEquals(expected.toString(), rookBlockByTeammateB.getAllLegalMoves().toString());
    }

    /**
     * Test if a white rook can capture a black piece
     */
    @Test
    public void testCalculateLegalMovesCaptureEnemyW() {
        ArrayList<Move> expected = new ArrayList<>();
        Piece rookCaptureEnemyW = new Rook(49, Attributes.Color.WHITE, board);
        Piece bishop = new Bishop(50, Attributes.Color.BLACK, board);
        Piece knight = new Knight(33, Attributes.Color.BLACK, board);
        Piece pawn = new Pawn(48, Attributes.Color.BLACK, board);
        Move m1 = new Move.NormalMove(board, rookCaptureEnemyW, 41);
        expected.add(m1);
        Move m2 = new Move.CaptureMove(board, rookCaptureEnemyW, 33);
        expected.add(m2);
        Move m3 = new Move.CaptureMove(board, rookCaptureEnemyW, 48);
        expected.add(m3);
        Move m4 = new Move.CaptureMove(board, rookCaptureEnemyW, 50);
        expected.add(m4);
        Move m5 = new Move.NormalMove(board, rookCaptureEnemyW, 57);
        expected.add(m5);
        board.setPiecesOnBoard(EMPTY_FEN);
        board.setPiece(bishop);
        board.setPiece(pawn);
        board.setPiece(knight);
        board.setPiece(rookCaptureEnemyW);
        rookCaptureEnemyW.calculateLegalMoves();
        assertEquals(expected.toString(), rookCaptureEnemyW.getAllLegalMoves().toString());
    }

    /**
     * Test if a black rook can capture a white piece
     */
    @Test
    public void testCalculateLegalMovesCaptureEnemyB() {
        ArrayList<Move> expected = new ArrayList<>();
        Piece rookCaptureEnemyB = new Rook(49, Attributes.Color.BLACK, board);
        Piece bishop = new Bishop(50, Attributes.Color.WHITE, board);
        Piece knight = new Knight(33, Attributes.Color.WHITE, board);
        Piece pawn = new Pawn(48, Attributes.Color.WHITE, board);
        Move m1 = new Move.NormalMove(board, rookCaptureEnemyB, 41);
        expected.add(m1);
        Move m2 = new Move.CaptureMove(board, rookCaptureEnemyB, 33);
        expected.add(m2);
        Move m3 = new Move.CaptureMove(board, rookCaptureEnemyB, 48);
        expected.add(m3);
        Move m4 = new Move.CaptureMove(board, rookCaptureEnemyB, 50);
        expected.add(m4);
        Move m5 = new Move.NormalMove(board, rookCaptureEnemyB, 57);
        expected.add(m5);
        board.setPiecesOnBoard(EMPTY_FEN);
        board.setPiece(bishop);
        board.setPiece(pawn);
        board.setPiece(knight);
        board.setPiece(rookCaptureEnemyB);
        rookCaptureEnemyB.calculateLegalMoves();
        assertEquals(expected.toString(), rookCaptureEnemyB.getAllLegalMoves().toString());
    }

    @Test
    public void getSymbolTest(){
        Piece rookW = new Rook(44,Attributes.Color.WHITE,board);
        assertEquals("♖", rookW.getSymbol());
        Piece rookB = new Rook(10, Attributes.Color.BLACK,board);
        assertEquals("♜", rookB.getSymbol());
    }

    @Test
    public void getValueTest(){
        Piece rookW = new Rook(46,Attributes.Color.WHITE,board);
        Piece rookB = new Rook(16, Attributes.Color.BLACK,board);
        assertEquals(525, rookW.getValue());
        assertEquals(-525,rookB.getValue());
    }
}
