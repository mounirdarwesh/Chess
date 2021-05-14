package chess.model;

import chess.Attributes;
import chess.controller.Move;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
    public void testToStringW() {assertEquals("K", kingW.toString()); }

    /**
     * Test if black king shown with lowercase "k"
     */
    @Test
    public void testToStringB() {assertEquals("k", kingB.toString()); }

    /**
     * Test all the legal moves of a white king in a position in the middle of the board
     */
    @Test
    public void testCalculateLegalMovesMiddleW() {

        ArrayList<Move> expected = new ArrayList<>();
        Piece kingMiddle = new King(35, Attributes.Color.WHITE, board);
        Move m1 = new Move.NormalMove(board,kingMiddle,26);
        expected.add(m1);
        Move m2 = new Move.NormalMove(board,kingMiddle,27);
        expected.add(m2);
        Move m3 = new Move.NormalMove(board,kingMiddle,28);
        expected.add(m3);
        Move m4 = new Move.NormalMove(board,kingMiddle,34);
        expected.add(m4);
        Move m5 = new Move.NormalMove(board,kingMiddle,36);
        expected.add(m5);
        Move m6 = new Move.NormalMove(board,kingMiddle,42);
        expected.add(m6);
        Move m7 = new Move.NormalMove(board,kingMiddle,43);
        expected.add(m7);
        Move m8 = new Move.NormalMove(board,kingMiddle,44);
        expected.add(m8);

        board.setPiecesOnBoard(EMPTY_FEN);
        board.setPiece(kingMiddle);
        kingMiddle.calculateLegalMoves();
        ArrayList<Move> allLegal = kingMiddle.getAllLegalMoves();
        assertEquals(expected.toString(), kingMiddle.getAllLegalMoves().toString());
    }

    /**
     * Test all the legal moves of a black king in a position in the middle of the board
     */
    @Test
    public void testCalculateLegalMovesMiddleB() {

        ArrayList<Move> expected = new ArrayList<>();
        Piece kingMiddle = new King(35, Attributes.Color.BLACK, board);
        Move m1 = new Move.NormalMove(board,kingMiddle,26);
        expected.add(m1);
        Move m2 = new Move.NormalMove(board,kingMiddle,27);
        expected.add(m2);
        Move m3 = new Move.NormalMove(board,kingMiddle,28);
        expected.add(m3);
        Move m4 = new Move.NormalMove(board,kingMiddle,34);
        expected.add(m4);
        Move m5 = new Move.NormalMove(board,kingMiddle,36);
        expected.add(m5);
        Move m6 = new Move.NormalMove(board,kingMiddle,42);
        expected.add(m6);
        Move m7 = new Move.NormalMove(board,kingMiddle,43);
        expected.add(m7);
        Move m8 = new Move.NormalMove(board,kingMiddle,44);
        expected.add(m8);

        board.setPiecesOnBoard(EMPTY_FEN);
        board.setPiece(kingMiddle);
        kingMiddle.calculateLegalMoves();
        ArrayList<Move> allLegal = kingMiddle.getAllLegalMoves();
        assertEquals(expected.toString(), kingMiddle.getAllLegalMoves().toString());
    }

    /**
     * Test all the legal moves of a white king in a position on the edge of the board
     */
    @Test
    public void testCalculateLegalMovesEdgeW() {

        ArrayList<Move> expected = new ArrayList<>();
        Piece kingEdge = new King(7, Attributes.Color.WHITE, board);
        Move m1 = new Move.NormalMove(board,kingEdge,6);
        expected.add(m1);
        Move m2 = new Move.NormalMove(board,kingEdge,14);
        expected.add(m2);
        Move m3 = new Move.NormalMove(board,kingEdge,15);
        expected.add(m3);

        board.setPiecesOnBoard(EMPTY_FEN);
        board.setPiece(kingEdge);
        kingEdge.calculateLegalMoves();
        ArrayList<Move> allLegal = kingEdge.getAllLegalMoves();
        assertEquals(expected.toString(), kingEdge.getAllLegalMoves().toString());
    }

    /**
     * Test all the legal moves of a black king in a position on the edge of the board
     */
    @Test
    public void testCalculateLegalMovesEdgeB() {

        ArrayList<Move> expected = new ArrayList<>();
        Piece kingEdge = new King(7, Attributes.Color.BLACK, board);
        Move m1 = new Move.NormalMove(board,kingEdge,6);
        expected.add(m1);
        Move m2 = new Move.NormalMove(board,kingEdge,14);
        expected.add(m2);
        Move m3 = new Move.NormalMove(board,kingEdge,15);
        expected.add(m3);

        board.setPiecesOnBoard(EMPTY_FEN);
        board.setPiece(kingEdge);
        kingEdge.calculateLegalMoves();
        ArrayList<Move> allLegal = kingEdge.getAllLegalMoves();
        assertEquals(expected.toString(), kingEdge.getAllLegalMoves().toString());
    }

    /**
     *Test which legal moves are exist if a white king blocked by other white pieces
     */
    @Test
    public void testCalculateLegalMovesBlockByTeammateW() {
        ArrayList<Move> expected = new ArrayList<>();
        Piece kingBlockByTeammate = new King(36, Attributes.Color.WHITE, board);
        Piece bishop = new Bishop(35, Attributes.Color.WHITE,board);
        Piece knight = new Knight(37, Attributes.Color.WHITE,board);
        Piece pawn = new Pawn(28, Attributes.Color.WHITE,board);
        Piece rook = new Rook (29, Attributes.Color.WHITE, board);
        Piece king = new King (43, Attributes.Color.WHITE, board);
        Piece knight2 = new Knight (45, Attributes.Color.WHITE, board);
        Piece bishop2 = new Bishop(27, Attributes.Color.WHITE,board);

        Move m1 = new Move.NormalMove(board,kingBlockByTeammate,44);
        expected.add(m1);

        board.setPiecesOnBoard(EMPTY_FEN);
        board.setPiece(rook);
        board.setPiece(knight);
        board.setPiece(knight2);
        board.setPiece(pawn);
        board.setPiece(bishop);
        board.setPiece(bishop2);
        board.setPiece(king);
        board.setPiece(kingBlockByTeammate);
        kingBlockByTeammate.calculateLegalMoves();
        ArrayList<Move> allLegal = kingBlockByTeammate.getAllLegalMoves();
        assertEquals(expected.toString(), kingBlockByTeammate.getAllLegalMoves().toString());
    }

    /**
     *Test which legal moves are exist if a black king blocked by other black pieces
     */
    @Test
    public void testCalculateLegalMovesBlockByTeammateB() {
        ArrayList<Move> expected = new ArrayList<>();
        Piece kingBlockByTeammate = new King(36, Attributes.Color.BLACK, board);
        Piece bishop = new Bishop(35, Attributes.Color.BLACK,board);
        Piece knight = new Knight(37, Attributes.Color.BLACK,board);
        Piece pawn = new Pawn(28, Attributes.Color.BLACK,board);
        Piece rook = new Rook (29, Attributes.Color.BLACK, board);
        Piece king = new King (43, Attributes.Color.BLACK, board);
        Piece knight2 = new Knight (45, Attributes.Color.BLACK, board);
        Piece bishop2 = new Bishop(27, Attributes.Color.BLACK,board);

        Move m1 = new Move.NormalMove(board,kingBlockByTeammate,44);
        expected.add(m1);

        board.setPiecesOnBoard(EMPTY_FEN);
        board.setPiece(rook);
        board.setPiece(knight);
        board.setPiece(knight2);
        board.setPiece(pawn);
        board.setPiece(bishop);
        board.setPiece(bishop2);
        board.setPiece(king);
        board.setPiece(kingBlockByTeammate);
        kingBlockByTeammate.calculateLegalMoves();
        ArrayList<Move> allLegal = kingBlockByTeammate.getAllLegalMoves();
        assertEquals(expected.toString(), kingBlockByTeammate.getAllLegalMoves().toString());
    }

    /**
     * Test if a white king can capture a black piece
     */
    @Test
    public void testCalculateLegalMovesCaptureEnemyW() {
        ArrayList<Move> expected = new ArrayList<>();
        Piece kingCaptureEnemy = new King(36, Attributes.Color.WHITE, board);
        Piece bishop = new Bishop(35, Attributes.Color.BLACK,board);
        Piece knight = new Knight(37, Attributes.Color.BLACK,board);
        Piece pawn = new Pawn(28, Attributes.Color.BLACK,board);
        Piece rook = new Rook (29, Attributes.Color.BLACK, board);
        Piece king = new King (43, Attributes.Color.BLACK, board);
        Piece knight2 = new Knight (45, Attributes.Color.BLACK, board);
        Piece bishop2 = new Bishop(27, Attributes.Color.BLACK,board);

        Move m1 = new Move.CaptureMove(board,kingCaptureEnemy,27);
        expected.add(m1);
        Move m2 = new Move.CaptureMove(board,kingCaptureEnemy,28);
        expected.add(m2);
        Move m3 = new Move.CaptureMove(board,kingCaptureEnemy,29);
        expected.add(m3);
        Move m4 = new Move.CaptureMove(board,kingCaptureEnemy,35);
        expected.add(m4);
        Move m5 = new Move.CaptureMove(board,kingCaptureEnemy,37);
        expected.add(m5);
        Move m6 = new Move.CaptureMove(board,kingCaptureEnemy,43);
        expected.add(m6);
        Move m7 = new Move.NormalMove(board,kingCaptureEnemy,44);
        expected.add(m7);
        Move m8 = new Move.CaptureMove(board,kingCaptureEnemy,45);
        expected.add(m8);

        board.setPiecesOnBoard(EMPTY_FEN);
        board.setPiece(rook);
        board.setPiece(knight);
        board.setPiece(knight2);
        board.setPiece(pawn);
        board.setPiece(bishop);
        board.setPiece(bishop2);
        board.setPiece(king);
        board.setPiece(kingCaptureEnemy);
        kingCaptureEnemy.calculateLegalMoves();
        ArrayList<Move> allLegal = kingCaptureEnemy.getAllLegalMoves();
        assertEquals(expected.toString(), kingCaptureEnemy.getAllLegalMoves().toString());
    }

    /**
     * Test if a black king can capture a white piece
     */
    @Test
    public void testCalculateLegalMovesCaptureEnemyB() {
        ArrayList<Move> expected = new ArrayList<>();
        Piece kingCaptureEnemy = new King(36, Attributes.Color.BLACK, board);
        Piece bishop = new Bishop(35, Attributes.Color.WHITE,board);
        Piece knight = new Knight(37, Attributes.Color.WHITE,board);
        Piece pawn = new Pawn(28, Attributes.Color.WHITE,board);
        Piece rook = new Rook (29, Attributes.Color.WHITE, board);
        Piece king = new King (43, Attributes.Color.WHITE, board);
        Piece knight2 = new Knight (45, Attributes.Color.WHITE, board);
        Piece bishop2 = new Bishop(27, Attributes.Color.WHITE,board);

        Move m1 = new Move.CaptureMove(board,kingCaptureEnemy,27);
        expected.add(m1);
        Move m2 = new Move.CaptureMove(board,kingCaptureEnemy,28);
        expected.add(m2);
        Move m3 = new Move.CaptureMove(board,kingCaptureEnemy,29);
        expected.add(m3);
        Move m4 = new Move.CaptureMove(board,kingCaptureEnemy,35);
        expected.add(m4);
        Move m5 = new Move.CaptureMove(board,kingCaptureEnemy,37);
        expected.add(m5);
        Move m6 = new Move.CaptureMove(board,kingCaptureEnemy,43);
        expected.add(m6);
        Move m7 = new Move.NormalMove(board,kingCaptureEnemy,44);
        expected.add(m7);
        Move m8 = new Move.CaptureMove(board,kingCaptureEnemy,45);
        expected.add(m8);

        board.setPiecesOnBoard(EMPTY_FEN);
        board.setPiece(rook);
        board.setPiece(knight);
        board.setPiece(knight2);
        board.setPiece(pawn);
        board.setPiece(bishop);
        board.setPiece(bishop2);
        board.setPiece(king);
        board.setPiece(kingCaptureEnemy);
        kingCaptureEnemy.calculateLegalMoves();
        ArrayList<Move> allLegal = kingCaptureEnemy.getAllLegalMoves();
        assertEquals(expected.toString(), kingCaptureEnemy.getAllLegalMoves().toString());
    }

/*    @Test
    public void checkMate(){

        ArrayList<Move> expected = new ArrayList<>();
        Piece kingCheckMate = new King(63, Attributes.Color.BLACK, board);
        Piece rook = new Rook (7, Attributes.Color.WHITE, board);
        Piece queen = new Queen (46, Attributes.Color.WHITE, board);
        board.setPiece(kingCheckMate);
        board.setPiece(queen);
        board.setPiece(rook);

        kingCheckMate.calculateLegalMoves();
        assertEquals(expected.toString(), kingCheckMate.getAllLegalMoves().toString());
    }*/

/*    @Test
    public void castling(){

    }*/
}