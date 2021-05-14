package chess.model;

import chess.Attributes;
import chess.controller.Move;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RookTest {
    //Empty board
    private static final String EMPTY_FEN = "8/8/8/8/8/8/8/8";
    Board board = new Board();
    Piece rookW = new Rook(16, Attributes.Color.WHITE, board);
    Piece rookB = new Rook(16, Attributes.Color.BLACK, board);

    /**
     *  Test if white rook shown with uppercase "R"
     */
    @Test
    public void testToStringW() {
        assertEquals("R", rookW.toString());
    }

    /**
     *  Test if black rook shown with lowercase "b"
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
        Piece rookMiddle = new Rook(35, Attributes.Color.WHITE, board);
        Move m1 = new Move.NormalMove(board,rookMiddle,27);
        expected.add(m1);
        Move m2 = new Move.NormalMove(board,rookMiddle,19);
        expected.add(m2);
        Move m3 = new Move.NormalMove(board,rookMiddle,11);
        expected.add(m3);
        Move m4 = new Move.NormalMove(board,rookMiddle,3);
        expected.add(m4);
        Move m5 = new Move.NormalMove(board,rookMiddle,34);
        expected.add(m5);
        Move m6 = new Move.NormalMove(board,rookMiddle,33);
        expected.add(m6);
        Move m7 = new Move.NormalMove(board,rookMiddle,32);
        expected.add(m7);
        Move m8 = new Move.NormalMove(board,rookMiddle,36);
        expected.add(m8);
        Move m9 = new Move.NormalMove(board,rookMiddle,37);
        expected.add(m9);
        Move m10 = new Move.NormalMove(board,rookMiddle,38);
        expected.add(m10);
        Move m11 = new Move.NormalMove(board,rookMiddle,39);
        expected.add(m11);
        Move m12 = new Move.NormalMove(board,rookMiddle,43);
        expected.add(m12);
        Move m13 = new Move.NormalMove(board,rookMiddle,51);
        expected.add(m13);
        Move m14 = new Move.NormalMove(board,rookMiddle,59);
        expected.add(m14);
        board.setPiecesOnBoard(EMPTY_FEN);
        board.setPiece(rookMiddle);
        rookMiddle.calculateLegalMoves();
        assertEquals(expected.toString(), rookMiddle.getAllLegalMoves().toString());
    }

    /**
     * Test all the legal moves of a black rook in a position in the middle of the board
     */
    @Test
    public void testCalculateLegalMovesMiddleB() {

        ArrayList<Move> expected = new ArrayList<>();
        Piece rookMiddle = new Rook(35, Attributes.Color.BLACK, board);
        Move m1 = new Move.NormalMove(board,rookMiddle,27);
        expected.add(m1);
        Move m2 = new Move.NormalMove(board,rookMiddle,19);
        expected.add(m2);
        Move m3 = new Move.NormalMove(board,rookMiddle,11);
        expected.add(m3);
        Move m4 = new Move.NormalMove(board,rookMiddle,3);
        expected.add(m4);
        Move m5 = new Move.NormalMove(board,rookMiddle,34);
        expected.add(m5);
        Move m6 = new Move.NormalMove(board,rookMiddle,33);
        expected.add(m6);
        Move m7 = new Move.NormalMove(board,rookMiddle,32);
        expected.add(m7);
        Move m8 = new Move.NormalMove(board,rookMiddle,36);
        expected.add(m8);
        Move m9 = new Move.NormalMove(board,rookMiddle,37);
        expected.add(m9);
        Move m10 = new Move.NormalMove(board,rookMiddle,38);
        expected.add(m10);
        Move m11 = new Move.NormalMove(board,rookMiddle,39);
        expected.add(m11);
        Move m12 = new Move.NormalMove(board,rookMiddle,43);
        expected.add(m12);
        Move m13 = new Move.NormalMove(board,rookMiddle,51);
        expected.add(m13);
        Move m14 = new Move.NormalMove(board,rookMiddle,59);
        expected.add(m14);
        board.setPiecesOnBoard(EMPTY_FEN);
        board.setPiece(rookMiddle);
        rookMiddle.calculateLegalMoves();
        assertEquals(expected.toString(), rookMiddle.getAllLegalMoves().toString());
    }

    /**
     * Test all the legal moves of a white rook in a position on the edge of the board
     */
    @Test
    public void testCalculateLegalMovesEdgeW() {

        ArrayList<Move> expected = new ArrayList<>();
        Piece rookEdge = new Rook(0, Attributes.Color.WHITE, board);
        Move m1 = new Move.NormalMove(board,rookEdge,1);
        expected.add(m1);
        Move m2 = new Move.NormalMove(board,rookEdge,2);
        expected.add(m2);
        Move m3 = new Move.NormalMove(board,rookEdge,3);
        expected.add(m3);
        Move m4 = new Move.NormalMove(board,rookEdge,4);
        expected.add(m4);
        Move m5 = new Move.NormalMove(board,rookEdge,5);
        expected.add(m5);
        Move m6 = new Move.NormalMove(board,rookEdge,6);
        expected.add(m6);
        Move m7 = new Move.NormalMove(board,rookEdge,7);
        expected.add(m7);
        Move m8 = new Move.NormalMove(board,rookEdge,8);
        expected.add(m8);
        Move m9 = new Move.NormalMove(board,rookEdge,16);
        expected.add(m9);
        Move m10 = new Move.NormalMove(board,rookEdge,24);
        expected.add(m10);
        Move m11 = new Move.NormalMove(board,rookEdge,32);
        expected.add(m11);
        Move m12 = new Move.NormalMove(board,rookEdge,40);
        expected.add(m12);
        Move m13 = new Move.NormalMove(board,rookEdge,48);
        expected.add(m13);
        Move m14 = new Move.NormalMove(board,rookEdge,56);
        expected.add(m14);
        board.setPiecesOnBoard(EMPTY_FEN);
        board.setPiece(rookEdge);
        rookEdge.calculateLegalMoves();
        assertEquals(expected.toString(), rookEdge.getAllLegalMoves().toString());
    }

    /**
     * Test all the legal moves of a black bishop in a position on the edge of the board
     */
    @Test
    public void testCalculateLegalMovesEdgeB() {

        ArrayList<Move> expected = new ArrayList<>();
        Piece rookEdge = new Rook(0, Attributes.Color.BLACK, board);
        Move m1 = new Move.NormalMove(board,rookEdge,1);
        expected.add(m1);
        Move m2 = new Move.NormalMove(board,rookEdge,2);
        expected.add(m2);
        Move m3 = new Move.NormalMove(board,rookEdge,3);
        expected.add(m3);
        Move m4 = new Move.NormalMove(board,rookEdge,4);
        expected.add(m4);
        Move m5 = new Move.NormalMove(board,rookEdge,5);
        expected.add(m5);
        Move m6 = new Move.NormalMove(board,rookEdge,6);
        expected.add(m6);
        Move m7 = new Move.NormalMove(board,rookEdge,7);
        expected.add(m7);
        Move m8 = new Move.NormalMove(board,rookEdge,8);
        expected.add(m8);
        Move m9 = new Move.NormalMove(board,rookEdge,16);
        expected.add(m9);
        Move m10 = new Move.NormalMove(board,rookEdge,24);
        expected.add(m10);
        Move m11 = new Move.NormalMove(board,rookEdge,32);
        expected.add(m11);
        Move m12 = new Move.NormalMove(board,rookEdge,40);
        expected.add(m12);
        Move m13 = new Move.NormalMove(board,rookEdge,48);
        expected.add(m13);
        Move m14 = new Move.NormalMove(board,rookEdge,56);
        expected.add(m14);
        board.setPiecesOnBoard(EMPTY_FEN);
        board.setPiece(rookEdge);
        rookEdge.calculateLegalMoves();
        assertEquals(expected.toString(), rookEdge.getAllLegalMoves().toString());
    }

    /**
     * Test which legal moves are exist if a white rook blocked by other white pieces
     */
    @Test
    public void testCalculateLegalMovesBlockByTeammateW() {
        ArrayList<Move> expected = new ArrayList<>();
        Piece rookBlockByTeammate = new Rook(36, Attributes.Color.WHITE, board);
        Piece bishop = new Bishop(35, Attributes.Color.WHITE,board);
        Piece knight = new Knight(37, Attributes.Color.WHITE,board);
        Piece pawn = new Pawn(28, Attributes.Color.WHITE,board);
        Piece queen = new Queen(60, Attributes.Color.WHITE, board);
        Move m1 = new Move.NormalMove(board,rookBlockByTeammate,44);
        expected.add(m1);
        Move m2 = new Move.NormalMove(board,rookBlockByTeammate,52);
        expected.add(m2);
        board.setPiecesOnBoard(EMPTY_FEN);
        board.setPiece(rookBlockByTeammate);
        board.setPiece(knight);
        board.setPiece(pawn);
        board.setPiece(bishop);
        board.setPiece(queen);
        rookBlockByTeammate.calculateLegalMoves();
        assertEquals(expected.toString(), rookBlockByTeammate.getAllLegalMoves().toString());
    }

    /**
     * Test which legal moves are exist if a black rook blocked by other black pieces
     */
    @Test
    public void testCalculateLegalMovesBlockByTeammateB() {
        ArrayList<Move> expected = new ArrayList<>();
        Piece rookBlockByTeammate = new Rook(36, Attributes.Color.BLACK, board);
        Piece bishop = new Bishop(35, Attributes.Color.BLACK,board);
        Piece knight = new Knight(37, Attributes.Color.BLACK,board);
        Piece pawn = new Pawn(28, Attributes.Color.BLACK,board);
        Piece queen = new Queen(60, Attributes.Color.BLACK, board);
        Move m1 = new Move.NormalMove(board,rookBlockByTeammate,44);
        expected.add(m1);
        Move m2 = new Move.NormalMove(board,rookBlockByTeammate,52);
        expected.add(m2);
        board.setPiecesOnBoard(EMPTY_FEN);
        board.setPiece(rookBlockByTeammate);
        board.setPiece(knight);
        board.setPiece(pawn);
        board.setPiece(bishop);
        board.setPiece(queen);
        rookBlockByTeammate.calculateLegalMoves();
        assertEquals(expected.toString(), rookBlockByTeammate.getAllLegalMoves().toString());
    }

    /**
     * Test if a white rook can capture a black piece
     */
    @Test
    public void testCalculateLegalMovesCaptureEnemyW() {
        ArrayList<Move> expected = new ArrayList<>();
        Piece rookCaptureEnemy = new Rook(49, Attributes.Color.WHITE, board);
        Piece bishop = new Bishop(50, Attributes.Color.BLACK,board);
        Piece knight = new Knight(33, Attributes.Color.BLACK,board);
        Piece pawn = new Pawn(48, Attributes.Color.BLACK, board);
        Move m1 = new Move.NormalMove(board,rookCaptureEnemy,41);
        expected.add(m1);
        Move m2 = new Move.CaptureMove(board,rookCaptureEnemy,33);
        expected.add(m2);
        Move m3 = new Move.CaptureMove(board,rookCaptureEnemy,48);
        expected.add(m3);
        Move m4 = new Move.CaptureMove(board,rookCaptureEnemy,50);
        expected.add(m4);
        Move m5 = new Move.NormalMove(board,rookCaptureEnemy,57);
        expected.add(m5);
        board.setPiecesOnBoard(EMPTY_FEN);
        board.setPiece(bishop);
        board.setPiece(pawn);
        board.setPiece(knight);
        board.setPiece(rookCaptureEnemy);
        rookCaptureEnemy.calculateLegalMoves();
        assertEquals(expected.toString(),rookCaptureEnemy.getAllLegalMoves().toString());
    }

    /**
     * Test if a black rook can capture a white piece
     */
    @Test
    public void testCalculateLegalMovesCaptureEnemyB() {
        ArrayList<Move> expected = new ArrayList<>();
        Piece rookCaptureEnemy = new Rook(49, Attributes.Color.BLACK, board);
        Piece bishop = new Bishop(50, Attributes.Color.WHITE,board);
        Piece knight = new Knight(33, Attributes.Color.WHITE,board);
        Piece pawn = new Pawn(48, Attributes.Color.WHITE, board);
        Move m1 = new Move.NormalMove(board,rookCaptureEnemy,41);
        expected.add(m1);
        Move m2 = new Move.CaptureMove(board,rookCaptureEnemy,33);
        expected.add(m2);
        Move m3 = new Move.CaptureMove(board,rookCaptureEnemy,48);
        expected.add(m3);
        Move m4 = new Move.CaptureMove(board,rookCaptureEnemy,50);
        expected.add(m4);
        Move m5 = new Move.NormalMove(board,rookCaptureEnemy,57);
        expected.add(m5);
        board.setPiecesOnBoard(EMPTY_FEN);
        board.setPiece(bishop);
        board.setPiece(pawn);
        board.setPiece(knight);
        board.setPiece(rookCaptureEnemy);
        rookCaptureEnemy.calculateLegalMoves();
        assertEquals(expected.toString(),rookCaptureEnemy.getAllLegalMoves().toString());
    }
}