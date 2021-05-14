package chess.model;

import chess.Attributes;
import chess.controller.Move;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
    public void testToStringW() {assertEquals("N", knightW.toString());}

    /**
     * Test if black knight shown with lower "n"
     */
    @Test
    public void testToStringB() {assertEquals("n", knightB.toString());}

    /**
     * Test all the legal moves of a white knight in a position in the middle of the board
     */
    @Test
    public void testCalculateLegalMovesMiddleW() {

        ArrayList<Move> expected = new ArrayList<>();
        Piece knightMiddle = new Knight(27, Attributes.Color.WHITE, board);
        Move m1 = new Move.NormalMove(board,knightMiddle,10);
        expected.add(m1);
        Move m2 = new Move.NormalMove(board,knightMiddle,12);
        expected.add(m2);
        Move m3 = new Move.NormalMove(board,knightMiddle,17);
        expected.add(m3);
        Move m4 = new Move.NormalMove(board,knightMiddle,21);
        expected.add(m4);
        Move m5 = new Move.NormalMove(board,knightMiddle,33);
        expected.add(m5);
        Move m6 = new Move.NormalMove(board,knightMiddle,37);
        expected.add(m6);
        Move m7 = new Move.NormalMove(board,knightMiddle,42);
        expected.add(m7);
        Move m8 = new Move.NormalMove(board,knightMiddle,44);
        expected.add(m8);
        board.setPiecesOnBoard(EMPTY_FEN);
        board.setPiece(knightMiddle);
        knightMiddle.calculateLegalMoves();
        assertEquals(expected.toString(), knightMiddle.getAllLegalMoves().toString());
    }

    /**
     * Test all the legal moves of a black knight in a position in the middle of the board
     */
    @Test
    public void testCalculateLegalMovesMiddleB() {

        ArrayList<Move> expected = new ArrayList<>();
        Piece knightMiddle = new Knight(27, Attributes.Color.BLACK, board);
        Move m1 = new Move.NormalMove(board,knightMiddle,10);
        expected.add(m1);
        Move m2 = new Move.NormalMove(board,knightMiddle,12);
        expected.add(m2);
        Move m3 = new Move.NormalMove(board,knightMiddle,17);
        expected.add(m3);
        Move m4 = new Move.NormalMove(board,knightMiddle,21);
        expected.add(m4);
        Move m5 = new Move.NormalMove(board,knightMiddle,33);
        expected.add(m5);
        Move m6 = new Move.NormalMove(board,knightMiddle,37);
        expected.add(m6);
        Move m7 = new Move.NormalMove(board,knightMiddle,42);
        expected.add(m7);
        Move m8 = new Move.NormalMove(board,knightMiddle,44);
        expected.add(m8);
        board.setPiecesOnBoard(EMPTY_FEN);
        board.setPiece(knightMiddle);
        knightMiddle.calculateLegalMoves();
        assertEquals(expected.toString(), knightMiddle.getAllLegalMoves().toString());
    }


    /**
     * Test all the legal moves of a white knight in a position on the edge of the board
     */
    @Test
    public void testCalculateLegalMovesEdgeW() {

        ArrayList<Move> expected = new ArrayList<>();
        Piece knightEdge = new Knight(0, Attributes.Color.WHITE, board);
        Move m1 = new Move.NormalMove(board,knightEdge,10);
        expected.add(m1);
        Move m2 = new Move.NormalMove(board,knightEdge,17);
        expected.add(m2);

        board.setPiecesOnBoard(EMPTY_FEN);
        board.setPiece(knightEdge);
        knightEdge.calculateLegalMoves();
        assertEquals(expected.toString(), knightEdge.getAllLegalMoves().toString());
    }

    /**
     * Test all the legal moves of a black knight in a position on the edge of the board
     */
    @Test
    public void testCalculateLegalMovesEdgeB() {

        ArrayList<Move> expected = new ArrayList<>();
        Piece knightEdge = new Knight(63, Attributes.Color.BLACK, board);
        Move m1 = new Move.NormalMove(board,knightEdge,46);
        expected.add(m1);
        Move m2 = new Move.NormalMove(board,knightEdge,53);
        expected.add(m2);

        board.setPiecesOnBoard(EMPTY_FEN);
        board.setPiece(knightEdge);
        knightEdge.calculateLegalMoves();
        assertEquals(expected.toString(), knightEdge.getAllLegalMoves().toString());
    }

    /**
     * Test which legal moves are exist if a white knight blocked by other white pieces
     */
    @Test
    public void testCalculateLegalMovesBlockByTeammateW() {
        ArrayList<Move> expected = new ArrayList<>();
        Piece knightBlockByTeammate = new Knight(54, Attributes.Color.WHITE, board);
        Piece rook = new Rook(60, Attributes.Color.WHITE,board);
        Piece pawn = new Pawn(44, Attributes.Color.WHITE,board);
        Piece queen = new Queen(37, Attributes.Color.WHITE, board);
        Move m1 = new Move.NormalMove(board,knightBlockByTeammate,39);
        expected.add(m1);
        board.setPiecesOnBoard(EMPTY_FEN);
        board.setPiece(rook);
        board.setPiece(knightBlockByTeammate);
        board.setPiece(pawn);
        board.setPiece(queen);
        knightBlockByTeammate.calculateLegalMoves();
        assertEquals(expected.toString(), knightBlockByTeammate.getAllLegalMoves().toString());
    }

    /**
     * Test which legal moves are exist if a black knight blocked by other black pieces
     */
    @Test
    public void testCalculateLegalMovesBlockByTeammateB() {
        ArrayList<Move> expected = new ArrayList<>();
        Piece knightBlockByTeammate = new Knight(54, Attributes.Color.BLACK, board);
        Piece rook = new Rook(60, Attributes.Color.BLACK,board);
        Piece pawn = new Pawn(44, Attributes.Color.BLACK,board);
        Piece queen = new Queen(37, Attributes.Color.BLACK, board);
        Move m1 = new Move.NormalMove(board,knightBlockByTeammate,39);
        expected.add(m1);
        board.setPiecesOnBoard(EMPTY_FEN);
        board.setPiece(rook);
        board.setPiece(knightBlockByTeammate);
        board.setPiece(pawn);
        board.setPiece(queen);
        knightBlockByTeammate.calculateLegalMoves();
        assertEquals(expected.toString(), knightBlockByTeammate.getAllLegalMoves().toString());
    }

    /**
     * Test if a white knight can capture a black piece
     */
    @Test
    public void testCalculateLegalMovesCaptureEnemyW() {
        ArrayList<Move> expected = new ArrayList<>();
        Piece knightCaptureEnemy = new Knight(54, Attributes.Color.WHITE, board);
        Piece rook = new Rook(60, Attributes.Color.BLACK,board);
        Piece pawn = new Pawn(44, Attributes.Color.BLACK,board);
        Piece queen = new Queen(37, Attributes.Color.BLACK, board);
        Move m1 = new Move.CaptureMove(board,knightCaptureEnemy,37);
        expected.add(m1);
        Move m2 = new Move.NormalMove(board,knightCaptureEnemy,39);
        expected.add(m2);
        Move m3 = new Move.CaptureMove(board,knightCaptureEnemy,44);
        expected.add(m3);
        Move m4 = new Move.CaptureMove(board,knightCaptureEnemy,60);
        expected.add(m4);

        board.setPiecesOnBoard(EMPTY_FEN);
        board.setPiece(rook);
        board.setPiece(knightCaptureEnemy);
        board.setPiece(pawn);
        board.setPiece(queen);
        knightCaptureEnemy.calculateLegalMoves();
        assertEquals(expected.toString(), knightCaptureEnemy.getAllLegalMoves().toString());
    }

    /**
     * Test if a black knight can capture a white piece
     */
    @Test
    public void testCalculateLegalMovesCaptureEnemyB() {
        ArrayList<Move> expected = new ArrayList<>();
        Piece knightCaptureEnemy = new Knight(54, Attributes.Color.BLACK, board);
        Piece rook = new Rook(60, Attributes.Color.WHITE,board);
        Piece pawn = new Pawn(44, Attributes.Color.WHITE,board);
        Piece queen = new Queen(37, Attributes.Color.WHITE, board);
        Move m1 = new Move.CaptureMove(board,knightCaptureEnemy,37);
        expected.add(m1);
        Move m2 = new Move.NormalMove(board,knightCaptureEnemy,39);
        expected.add(m2);
        Move m3 = new Move.CaptureMove(board,knightCaptureEnemy,44);
        expected.add(m3);
        Move m4 = new Move.CaptureMove(board,knightCaptureEnemy,60);
        expected.add(m4);

        board.setPiecesOnBoard(EMPTY_FEN);
        board.setPiece(rook);
        board.setPiece(knightCaptureEnemy);
        board.setPiece(pawn);
        board.setPiece(queen);
        knightCaptureEnemy.calculateLegalMoves();
        assertEquals(expected.toString(), knightCaptureEnemy.getAllLegalMoves().toString());
    }
}