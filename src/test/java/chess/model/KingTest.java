package chess.model;

import chess.Attributes;
import chess.controller.Move;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test of the class King
 *
 * @author Gruppe 45
 */
public class KingTest {
    //Empty board
    private static final String EMPTY_FEN = "8/8/8/8/8/8/8/8";
    Board board = new Board();
    Piece kingW = new King(44, Attributes.Color.WHITE, board);
    Piece kingB = new King(44, Attributes.Color.BLACK, board);

    /**
     * Test if white king shown with uppercase "K"
     */
    @Test
    public void testToStringW() {
        assertEquals("K", kingW.toString());
    }

    /**
     * Test if black king shown with lowercase "k"
     */
    @Test
    public void testToStringB() {
        assertEquals("k", kingB.toString());
    }

    /**
     * Test all the legal moves of a white king in a position in the middle of the board
     */
    @Test
    public void testCalculateLegalMovesMiddleW() {

        ArrayList<Move> expected = new ArrayList<>();
        Piece kingMiddleW = new King(35, Attributes.Color.WHITE, board);
        Move m1 = new Move.NormalMove(board, kingMiddleW, 26);
        expected.add(m1);
        Move m2 = new Move.NormalMove(board, kingMiddleW, 27);
        expected.add(m2);
        Move m3 = new Move.NormalMove(board, kingMiddleW, 28);
        expected.add(m3);
        Move m4 = new Move.NormalMove(board, kingMiddleW, 34);
        expected.add(m4);
        Move m5 = new Move.NormalMove(board, kingMiddleW, 36);
        expected.add(m5);
        Move m6 = new Move.NormalMove(board, kingMiddleW, 42);
        expected.add(m6);
        Move m7 = new Move.NormalMove(board, kingMiddleW, 43);
        expected.add(m7);
        Move m8 = new Move.NormalMove(board, kingMiddleW, 44);
        expected.add(m8);

        board.setPiecesOnBoard(EMPTY_FEN);
        board.setPiece(kingMiddleW);
        kingMiddleW.calculateLegalMoves();
        assertEquals(expected.toString(), kingMiddleW.getAllLegalMoves().toString());
    }

    /**
     * Test all the legal moves of a black king in a position in the middle of the board
     */
    @Test
    public void testCalculateLegalMovesMiddleB() {

        ArrayList<Move> expected = new ArrayList<>();
        Piece kingMiddleB = new King(35, Attributes.Color.BLACK, board);
        Move m1 = new Move.NormalMove(board, kingMiddleB, 26);
        expected.add(m1);
        Move m2 = new Move.NormalMove(board, kingMiddleB, 27);
        expected.add(m2);
        Move m3 = new Move.NormalMove(board, kingMiddleB, 28);
        expected.add(m3);
        Move m4 = new Move.NormalMove(board, kingMiddleB, 34);
        expected.add(m4);
        Move m5 = new Move.NormalMove(board, kingMiddleB, 36);
        expected.add(m5);
        Move m6 = new Move.NormalMove(board, kingMiddleB, 42);
        expected.add(m6);
        Move m7 = new Move.NormalMove(board, kingMiddleB, 43);
        expected.add(m7);
        Move m8 = new Move.NormalMove(board, kingMiddleB, 44);
        expected.add(m8);

        board.setPiecesOnBoard(EMPTY_FEN);
        board.setPiece(kingMiddleB);
        kingMiddleB.calculateLegalMoves();
        assertEquals(expected.toString(), kingMiddleB.getAllLegalMoves().toString());
    }

    /**
     * Test all the legal moves of a white king in a position on the edge of the board
     */
    @Test
    public void testCalculateLegalMovesEdgeW() {

        ArrayList<Move> expected = new ArrayList<>();
        Piece kingEdgeW = new King(7, Attributes.Color.WHITE, board);
        Move m1 = new Move.NormalMove(board, kingEdgeW, 6);
        expected.add(m1);
        Move m2 = new Move.NormalMove(board, kingEdgeW, 14);
        expected.add(m2);
        Move m3 = new Move.NormalMove(board, kingEdgeW, 15);
        expected.add(m3);

        board.setPiecesOnBoard(EMPTY_FEN);
        board.setPiece(kingEdgeW);
        kingEdgeW.calculateLegalMoves();
        assertEquals(expected.toString(), kingEdgeW.getAllLegalMoves().toString());
    }

    /**
     * Test all the legal moves of a black king in a position on the edge of the board
     */
    @Test
    public void testCalculateLegalMovesEdgeB() {

        ArrayList<Move> expected = new ArrayList<>();
        Piece kingEdgeB = new King(7, Attributes.Color.BLACK, board);
        Move m1 = new Move.NormalMove(board, kingEdgeB, 6);
        expected.add(m1);
        Move m2 = new Move.NormalMove(board, kingEdgeB, 14);
        expected.add(m2);
        Move m3 = new Move.NormalMove(board, kingEdgeB, 15);
        expected.add(m3);

        board.setPiecesOnBoard(EMPTY_FEN);
        board.setPiece(kingEdgeB);
        kingEdgeB.calculateLegalMoves();
        assertEquals(expected.toString(), kingEdgeB.getAllLegalMoves().toString());
    }

    /**
     * Test which legal moves are exist if a white king blocked by other white pieces
     */
    @Test
    public void testCalculateLegalMovesBlockByTeammateW() {
        ArrayList<Move> expected = new ArrayList<>();
        Piece kingBlockByTeammateW = new King(36, Attributes.Color.WHITE, board);
        Piece bishop = new Bishop(35, Attributes.Color.WHITE, board);
        Piece knight = new Knight(37, Attributes.Color.WHITE, board);
        Piece pawn = new Pawn(28, Attributes.Color.WHITE, board);
        Piece rook = new Rook(29, Attributes.Color.WHITE, board);
        Piece king = new King(43, Attributes.Color.WHITE, board);
        Piece knight2 = new Knight(45, Attributes.Color.WHITE, board);
        Piece bishop2 = new Bishop(27, Attributes.Color.WHITE, board);

        Move m1 = new Move.NormalMove(board, kingBlockByTeammateW, 44);
        expected.add(m1);

        board.setPiecesOnBoard(EMPTY_FEN);
        board.setPiece(rook);
        board.setPiece(knight);
        board.setPiece(knight2);
        board.setPiece(pawn);
        board.setPiece(bishop);
        board.setPiece(bishop2);
        board.setPiece(king);
        board.setPiece(kingBlockByTeammateW);
        kingBlockByTeammateW.calculateLegalMoves();
        assertEquals(expected.toString(), kingBlockByTeammateW.getAllLegalMoves().toString());
    }

    /**
     * Test which legal moves are exist if a black king blocked by other black pieces
     */
    @Test
    public void testCalculateLegalMovesBlockByTeammateB() {
        ArrayList<Move> expected = new ArrayList<>();
        Piece kingBlockByTeammateB = new King(35, Attributes.Color.BLACK, board);
        Piece bishop = new Bishop(34, Attributes.Color.BLACK, board);
        Piece knight = new Knight(36, Attributes.Color.BLACK, board);
        Piece pawn = new Pawn(27, Attributes.Color.BLACK, board);
        Piece rook = new Rook(28, Attributes.Color.BLACK, board);
        Piece king = new King(42, Attributes.Color.BLACK, board);
        Piece knight2 = new Knight(44, Attributes.Color.BLACK, board);
        Piece bishop2 = new Bishop(26, Attributes.Color.BLACK, board);

        Move m1 = new Move.NormalMove(board, kingBlockByTeammateB, 43);
        expected.add(m1);

        board.setPiecesOnBoard(EMPTY_FEN);
        board.setPiece(rook);
        board.setPiece(knight);
        board.setPiece(knight2);
        board.setPiece(pawn);
        board.setPiece(bishop);
        board.setPiece(bishop2);
        board.setPiece(king);
        board.setPiece(kingBlockByTeammateB);
        kingBlockByTeammateB.calculateLegalMoves();
        assertEquals(expected.toString(), kingBlockByTeammateB.getAllLegalMoves().toString());
    }

    /**
     * Test if a white king can capture a black piece
     */
    @Test
    public void testCalculateLegalMovesCaptureEnemyW() {
        ArrayList<Move> expected = new ArrayList<>();
        Piece kingCaptureEnemyW = new King(36, Attributes.Color.WHITE, board);
        Piece bishop = new Bishop(35, Attributes.Color.BLACK, board);
        Piece knight = new Knight(37, Attributes.Color.BLACK, board);
        Piece pawn = new Pawn(28, Attributes.Color.BLACK, board);
        Piece rook = new Rook(29, Attributes.Color.BLACK, board);
        Piece king = new King(43, Attributes.Color.BLACK, board);
        Piece knight2 = new Knight(45, Attributes.Color.BLACK, board);
        Piece bishop2 = new Bishop(27, Attributes.Color.BLACK, board);

        Move m1 = new Move.CaptureMove(board, kingCaptureEnemyW, 27);
        expected.add(m1);
        Move m2 = new Move.CaptureMove(board, kingCaptureEnemyW, 28);
        expected.add(m2);
        Move m3 = new Move.CaptureMove(board, kingCaptureEnemyW, 29);
        expected.add(m3);
        Move m4 = new Move.CaptureMove(board, kingCaptureEnemyW, 35);
        expected.add(m4);
        Move m5 = new Move.CaptureMove(board, kingCaptureEnemyW, 37);
        expected.add(m5);
        Move m6 = new Move.CaptureMove(board, kingCaptureEnemyW, 43);
        expected.add(m6);
        Move m7 = new Move.NormalMove(board, kingCaptureEnemyW, 44);
        expected.add(m7);
        Move m8 = new Move.CaptureMove(board, kingCaptureEnemyW, 45);
        expected.add(m8);

        board.setPiecesOnBoard(EMPTY_FEN);
        board.setPiece(rook);
        board.setPiece(knight);
        board.setPiece(knight2);
        board.setPiece(pawn);
        board.setPiece(bishop);
        board.setPiece(bishop2);
        board.setPiece(king);
        board.setPiece(kingCaptureEnemyW);
        kingCaptureEnemyW.calculateLegalMoves();
        assertEquals(expected.toString(), kingCaptureEnemyW.getAllLegalMoves().toString());
    }

    /**
     * Test if a black king can capture a white piece
     */
    @Test
    public void testCalculateLegalMovesCaptureEnemyB() {
        ArrayList<Move> expected = new ArrayList<>();
        Piece kingCaptureEnemyB = new King(37, Attributes.Color.BLACK, board);
        Piece bishop = new Bishop(36, Attributes.Color.WHITE, board);
        Piece knight = new Knight(38, Attributes.Color.WHITE, board);
        Piece pawn = new Pawn(29, Attributes.Color.WHITE, board);
        Piece rook = new Rook(30, Attributes.Color.WHITE, board);
        Piece king = new King(44, Attributes.Color.WHITE, board);
        Piece knight2 = new Knight(46, Attributes.Color.WHITE, board);
        Piece bishop2 = new Bishop(28, Attributes.Color.WHITE, board);

        Move m1 = new Move.CaptureMove(board, kingCaptureEnemyB, 28);
        expected.add(m1);
        Move m2 = new Move.CaptureMove(board, kingCaptureEnemyB, 29);
        expected.add(m2);
        Move m3 = new Move.CaptureMove(board, kingCaptureEnemyB, 30);
        expected.add(m3);
        Move m4 = new Move.CaptureMove(board, kingCaptureEnemyB, 36);
        expected.add(m4);
        Move m5 = new Move.CaptureMove(board, kingCaptureEnemyB, 38);
        expected.add(m5);
        Move m6 = new Move.CaptureMove(board, kingCaptureEnemyB, 44);
        expected.add(m6);
        Move m7 = new Move.NormalMove(board, kingCaptureEnemyB, 45);
        expected.add(m7);
        Move m8 = new Move.CaptureMove(board, kingCaptureEnemyB, 46);
        expected.add(m8);

        board.setPiecesOnBoard(EMPTY_FEN);
        board.setPiece(rook);
        board.setPiece(knight);
        board.setPiece(knight2);
        board.setPiece(pawn);
        board.setPiece(bishop);
        board.setPiece(bishop2);
        board.setPiece(king);
        board.setPiece(kingCaptureEnemyB);
        kingCaptureEnemyB.calculateLegalMoves();
        assertEquals(expected.toString(), kingCaptureEnemyB.getAllLegalMoves().toString());
    }

    /**
     * test if the system shows the correct symbol for king
     */
    @Test
    public void getSymbolTest(){
        Piece kingW = new King(44,Attributes.Color.WHITE,board);
        assertEquals("♔", kingW.getSymbol());
        Piece kingB = new King(10, Attributes.Color.BLACK,board);
        assertEquals("♚", kingB.getSymbol());
    }

    /**
     * test if king has the right value
     */
    @Test
    public void getValueTest(){
        Piece kingW = new King(40,Attributes.Color.WHITE,board);
        Piece kingB = new King(30, Attributes.Color.BLACK,board);
        assertEquals(10000, kingW.getValue());
        assertEquals(-10000,kingB.getValue());
    }
}