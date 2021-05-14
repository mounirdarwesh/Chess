package chess.model;

import chess.Attributes;
import chess.controller.Move;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class QueenTest {
    //Empty board
    private static final String EMPTY_FEN = "8/8/8/8/8/8/8/8";
    Board board = new Board();
    Piece queenW = new Queen(29, Attributes.Color.WHITE, board);
    Piece queenB = new Queen(28, Attributes.Color.BLACK, board);

    /**
     *  Test if white queen shown with uppercase "Q"
     */
    @Test
    public void testToStringW() {assertEquals("Q", queenW.toString());}

    /**
     *  Test if black queen shown with lowercase "q"
     */
    @Test
    public void testToStringB() {assertEquals("q", queenB.toString());}

    /**
     * Test all the legal moves of a white queen in a position in the middle of the board
     */
    @Test
    public void testCalculateLegalMovesMiddleW() {

        ArrayList<Move> expected = new ArrayList<>();
        Piece queenMiddle = new Queen(35, Attributes.Color.WHITE, board);
        Move m1 = new Move.NormalMove(board,queenMiddle,26);
        expected.add(m1);
        Move m2 = new Move.NormalMove(board,queenMiddle,17);
        expected.add(m2);
        Move m3 = new Move.NormalMove(board,queenMiddle,8);
        expected.add(m3);
        Move m4 = new Move.NormalMove(board,queenMiddle,28);
        expected.add(m4);
        Move m5 = new Move.NormalMove(board,queenMiddle,21);
        expected.add(m5);
        Move m6 = new Move.NormalMove(board,queenMiddle,14);
        expected.add(m6);
        Move m7 = new Move.NormalMove(board,queenMiddle,7);
        expected.add(m7);
        Move m8 = new Move.NormalMove(board,queenMiddle,27);
        expected.add(m8);
        Move m9 = new Move.NormalMove(board,queenMiddle,19);
        expected.add(m9);
        Move m10 = new Move.NormalMove(board,queenMiddle,11);
        expected.add(m10);
        Move m11 = new Move.NormalMove(board,queenMiddle,3);
        expected.add(m11);
        Move m12 = new Move.NormalMove(board,queenMiddle,34);
        expected.add(m12);
        Move m13 = new Move.NormalMove(board,queenMiddle,33);
        expected.add(m13);
        Move m14 = new Move.NormalMove(board,queenMiddle,32);
        expected.add(m14);
        Move m15 = new Move.NormalMove(board,queenMiddle,36);
        expected.add(m15);
        Move m16 = new Move.NormalMove(board,queenMiddle,37);
        expected.add(m16);
        Move m17 = new Move.NormalMove(board,queenMiddle,38);
        expected.add(m17);
        Move m18 = new Move.NormalMove(board,queenMiddle,39);
        expected.add(m18);
        Move m19 = new Move.NormalMove(board,queenMiddle,42);
        expected.add(m19);
        Move m20 = new Move.NormalMove(board,queenMiddle,49);
        expected.add(m20);
        Move m21 = new Move.NormalMove(board,queenMiddle,56);
        expected.add(m21);
        Move m22 = new Move.NormalMove(board,queenMiddle,43);
        expected.add(m22);
        Move m23 = new Move.NormalMove(board,queenMiddle,51);
        expected.add(m23);
        Move m24 = new Move.NormalMove(board,queenMiddle,59);
        expected.add(m24);
        Move m25 = new Move.NormalMove(board,queenMiddle,44);
        expected.add(m25);
        Move m26 = new Move.NormalMove(board,queenMiddle,53);
        expected.add(m26);
        Move m27 = new Move.NormalMove(board,queenMiddle,62);
        expected.add(m27);
        board.setPiecesOnBoard(EMPTY_FEN);
        board.setPiece(queenMiddle);
        queenMiddle.calculateLegalMoves();
        assertEquals(expected.toString(), queenMiddle.getAllLegalMoves().toString());
    }

    /**
     * Test all the legal moves of a black queen in a position in the middle of the board
     */
    @Test
    public void testCalculateLegalMovesMiddleB() {

        ArrayList<Move> expected = new ArrayList<>();
        Piece queenMiddle = new Queen(35, Attributes.Color.BLACK, board);
        Move m1 = new Move.NormalMove(board,queenMiddle,26);
        expected.add(m1);
        Move m2 = new Move.NormalMove(board,queenMiddle,17);
        expected.add(m2);
        Move m3 = new Move.NormalMove(board,queenMiddle,8);
        expected.add(m3);
        Move m4 = new Move.NormalMove(board,queenMiddle,28);
        expected.add(m4);
        Move m5 = new Move.NormalMove(board,queenMiddle,21);
        expected.add(m5);
        Move m6 = new Move.NormalMove(board,queenMiddle,14);
        expected.add(m6);
        Move m7 = new Move.NormalMove(board,queenMiddle,7);
        expected.add(m7);
        Move m8 = new Move.NormalMove(board,queenMiddle,27);
        expected.add(m8);
        Move m9 = new Move.NormalMove(board,queenMiddle,19);
        expected.add(m9);
        Move m10 = new Move.NormalMove(board,queenMiddle,11);
        expected.add(m10);
        Move m11 = new Move.NormalMove(board,queenMiddle,3);
        expected.add(m11);
        Move m12 = new Move.NormalMove(board,queenMiddle,34);
        expected.add(m12);
        Move m13 = new Move.NormalMove(board,queenMiddle,33);
        expected.add(m13);
        Move m14 = new Move.NormalMove(board,queenMiddle,32);
        expected.add(m14);
        Move m15 = new Move.NormalMove(board,queenMiddle,36);
        expected.add(m15);
        Move m16 = new Move.NormalMove(board,queenMiddle,37);
        expected.add(m16);
        Move m17 = new Move.NormalMove(board,queenMiddle,38);
        expected.add(m17);
        Move m18 = new Move.NormalMove(board,queenMiddle,39);
        expected.add(m18);
        Move m19 = new Move.NormalMove(board,queenMiddle,42);
        expected.add(m19);
        Move m20 = new Move.NormalMove(board,queenMiddle,49);
        expected.add(m20);
        Move m21 = new Move.NormalMove(board,queenMiddle,56);
        expected.add(m21);
        Move m22 = new Move.NormalMove(board,queenMiddle,43);
        expected.add(m22);
        Move m23 = new Move.NormalMove(board,queenMiddle,51);
        expected.add(m23);
        Move m24 = new Move.NormalMove(board,queenMiddle,59);
        expected.add(m24);
        Move m25 = new Move.NormalMove(board,queenMiddle,44);
        expected.add(m25);
        Move m26 = new Move.NormalMove(board,queenMiddle,53);
        expected.add(m26);
        Move m27 = new Move.NormalMove(board,queenMiddle,62);
        expected.add(m27);
        board.setPiecesOnBoard(EMPTY_FEN);
        board.setPiece(queenMiddle);
        queenMiddle.calculateLegalMoves();
        assertEquals(expected.toString(), queenMiddle.getAllLegalMoves().toString());
    }

    /**
     * Test all the legal moves of a white queen in a position on the edge of the board
     */
    @Test
    public void testCalculateLegalMovesEdgeW() {

        ArrayList<Move> expected = new ArrayList<>();
        Piece queenEdge = new Queen(0, Attributes.Color.WHITE, board);
        Move m1 = new Move.NormalMove(board,queenEdge,1);
        expected.add(m1);
        Move m2 = new Move.NormalMove(board,queenEdge,2);
        expected.add(m2);
        Move m3 = new Move.NormalMove(board,queenEdge,3);
        expected.add(m3);
        Move m4 = new Move.NormalMove(board,queenEdge,4);
        expected.add(m4);
        Move m5 = new Move.NormalMove(board,queenEdge,5);
        expected.add(m5);
        Move m6 = new Move.NormalMove(board,queenEdge,6);
        expected.add(m6);
        Move m7 = new Move.NormalMove(board,queenEdge,7);
        expected.add(m7);
        Move m8 = new Move.NormalMove(board,queenEdge,8);
        expected.add(m8);
        Move m9 = new Move.NormalMove(board,queenEdge,16);
        expected.add(m9);
        Move m10 = new Move.NormalMove(board,queenEdge,24);
        expected.add(m10);
        Move m11 = new Move.NormalMove(board,queenEdge,32);
        expected.add(m11);
        Move m12 = new Move.NormalMove(board,queenEdge,40);
        expected.add(m12);
        Move m13 = new Move.NormalMove(board,queenEdge,48);
        expected.add(m13);
        Move m14 = new Move.NormalMove(board,queenEdge,56);
        expected.add(m14);
        Move m15 = new Move.NormalMove(board,queenEdge,9);
        expected.add(m15);
        Move m16 = new Move.NormalMove(board,queenEdge,18);
        expected.add(m16);
        Move m17 = new Move.NormalMove(board,queenEdge,27);
        expected.add(m17);
        Move m18 = new Move.NormalMove(board,queenEdge,36);
        expected.add(m18);
        Move m19 = new Move.NormalMove(board,queenEdge,45);
        expected.add(m19);
        Move m20 = new Move.NormalMove(board,queenEdge,54);
        expected.add(m20);
        Move m21 = new Move.NormalMove(board,queenEdge,63);
        expected.add(m21);

        board.setPiecesOnBoard(EMPTY_FEN);
        board.setPiece(queenEdge);
        queenEdge.calculateLegalMoves();
        assertEquals(expected.toString(), queenEdge.getAllLegalMoves().toString());
    }

    /**
     * Test all the legal moves of a black queen in a position on the edge of the board
     */
    @Test
    public void testCalculateLegalMovesEdgeB() {

        ArrayList<Move> expected = new ArrayList<>();
        Piece queenEdge = new Queen(0, Attributes.Color.BLACK, board);
        Move m1 = new Move.NormalMove(board,queenEdge,1);
        expected.add(m1);
        Move m2 = new Move.NormalMove(board,queenEdge,2);
        expected.add(m2);
        Move m3 = new Move.NormalMove(board,queenEdge,3);
        expected.add(m3);
        Move m4 = new Move.NormalMove(board,queenEdge,4);
        expected.add(m4);
        Move m5 = new Move.NormalMove(board,queenEdge,5);
        expected.add(m5);
        Move m6 = new Move.NormalMove(board,queenEdge,6);
        expected.add(m6);
        Move m7 = new Move.NormalMove(board,queenEdge,7);
        expected.add(m7);
        Move m8 = new Move.NormalMove(board,queenEdge,8);
        expected.add(m8);
        Move m9 = new Move.NormalMove(board,queenEdge,16);
        expected.add(m9);
        Move m10 = new Move.NormalMove(board,queenEdge,24);
        expected.add(m10);
        Move m11 = new Move.NormalMove(board,queenEdge,32);
        expected.add(m11);
        Move m12 = new Move.NormalMove(board,queenEdge,40);
        expected.add(m12);
        Move m13 = new Move.NormalMove(board,queenEdge,48);
        expected.add(m13);
        Move m14 = new Move.NormalMove(board,queenEdge,56);
        expected.add(m14);
        Move m15 = new Move.NormalMove(board,queenEdge,9);
        expected.add(m15);
        Move m16 = new Move.NormalMove(board,queenEdge,18);
        expected.add(m16);
        Move m17 = new Move.NormalMove(board,queenEdge,27);
        expected.add(m17);
        Move m18 = new Move.NormalMove(board,queenEdge,36);
        expected.add(m18);
        Move m19 = new Move.NormalMove(board,queenEdge,45);
        expected.add(m19);
        Move m20 = new Move.NormalMove(board,queenEdge,54);
        expected.add(m20);
        Move m21 = new Move.NormalMove(board,queenEdge,63);
        expected.add(m21);
        board.setPiecesOnBoard(EMPTY_FEN);
        board.setPiece(queenEdge);
        queenEdge.calculateLegalMoves();
        assertEquals(expected.toString(), queenEdge.getAllLegalMoves().toString());
    }

    /**
     * Test which legal moves are exist if a white queen blocked by other white pieces
     */
    @Test
    public void testCalculateLegalMovesBlockByTeammateW() {
        ArrayList<Move> expected = new ArrayList<>();
        Piece queenBlockByTeammate = new Queen(36, Attributes.Color.WHITE, board);
        Piece bishop = new Bishop(35, Attributes.Color.WHITE,board);
        Piece knight = new Knight(37, Attributes.Color.WHITE,board);
        Piece pawn = new Pawn(28, Attributes.Color.WHITE,board);
        Piece rook = new Rook (52, Attributes.Color.WHITE, board);
        Piece king = new King (43, Attributes.Color.WHITE, board);
        Piece knight2 = new Knight (45, Attributes.Color.WHITE, board);
        Piece rook2 = new Rook (29, Attributes.Color.WHITE, board);
        Piece bishop2 = new Bishop(27, Attributes.Color.WHITE,board);

        Move m1 = new Move.NormalMove(board,queenBlockByTeammate,44);
        expected.add(m1);

        board.setPiecesOnBoard(EMPTY_FEN);
        board.setPiece(rook);
        board.setPiece(rook2);
        board.setPiece(knight);
        board.setPiece(knight2);
        board.setPiece(pawn);
        board.setPiece(bishop);
        board.setPiece(bishop2);
        board.setPiece(king);
        board.setPiece(queenBlockByTeammate);
        queenBlockByTeammate.calculateLegalMoves();
        assertEquals(expected.toString(), queenBlockByTeammate.getAllLegalMoves().toString());
    }

    /**
     * Test which legal moves are exist if a black queen blocked by other black pieces
     */
    @Test
    public void testCalculateLegalMovesBlockByTeammateB() {
        ArrayList<Move> expected = new ArrayList<>();
        Piece queenBlockByTeammate = new Queen(36, Attributes.Color.BLACK, board);
        Piece bishop = new Bishop(35, Attributes.Color.BLACK,board);
        Piece knight = new Knight(37, Attributes.Color.BLACK,board);
        Piece pawn = new Pawn(28, Attributes.Color.BLACK,board);
        Piece rook = new Rook (52, Attributes.Color.BLACK, board);
        Piece king = new King (43, Attributes.Color.BLACK, board);
        Piece knight2 = new Knight (45, Attributes.Color.BLACK, board);
        Piece rook2 = new Rook (29, Attributes.Color.BLACK, board);
        Piece bishop2 = new Bishop(27, Attributes.Color.BLACK,board);

        Move m1 = new Move.NormalMove(board,queenBlockByTeammate,44);
        expected.add(m1);

        board.setPiecesOnBoard(EMPTY_FEN);
        board.setPiece(rook);
        board.setPiece(rook2);
        board.setPiece(knight);
        board.setPiece(knight2);
        board.setPiece(pawn);
        board.setPiece(bishop);
        board.setPiece(bishop2);
        board.setPiece(king);
        board.setPiece(queenBlockByTeammate);
        queenBlockByTeammate.calculateLegalMoves();
        assertEquals(expected.toString(), queenBlockByTeammate.getAllLegalMoves().toString());
    }

    /**
     * Test if a white queen can capture a black piece
     */
    @Test
    public void testCalculateLegalMovesCaptureEnemyW() {
        ArrayList<Move> expected = new ArrayList<>();
        Piece queenCaptureEnemy = new Queen(36, Attributes.Color.WHITE, board);
        Piece bishop = new Bishop(35, Attributes.Color.BLACK,board);
        Piece knight = new Knight(37, Attributes.Color.BLACK,board);
        Piece pawn = new Pawn(28, Attributes.Color.BLACK,board);
        Piece rook = new Rook (52, Attributes.Color.BLACK, board);
        Piece king = new King (43, Attributes.Color.BLACK, board);
        Piece knight2 = new Knight (45, Attributes.Color.BLACK, board);
        Piece rook2 = new Rook (29, Attributes.Color.BLACK, board);
        Piece bishop2 = new Bishop(27, Attributes.Color.BLACK,board);

        Move m1 = new Move.CaptureMove(board,queenCaptureEnemy,27);
        expected.add(m1);
        Move m2 = new Move.CaptureMove(board,queenCaptureEnemy,29);
        expected.add(m2);
        Move m3 = new Move.CaptureMove(board,queenCaptureEnemy,28);
        expected.add(m3);
        Move m4 = new Move.CaptureMove(board,queenCaptureEnemy,35);
        expected.add(m4);
        Move m5 = new Move.CaptureMove(board,queenCaptureEnemy,37);
        expected.add(m5);
        Move m6 = new Move.CaptureMove(board,queenCaptureEnemy,43);
        expected.add(m6);
        Move m7 = new Move.NormalMove(board,queenCaptureEnemy,44);
        expected.add(m7);
        Move m8 = new Move.CaptureMove(board,queenCaptureEnemy,52);
        expected.add(m8);
        Move m9 = new Move.CaptureMove(board,queenCaptureEnemy,45);
        expected.add(m9);
        board.setPiecesOnBoard(EMPTY_FEN);
        board.setPiece(rook);
        board.setPiece(rook2);
        board.setPiece(knight);
        board.setPiece(knight2);
        board.setPiece(pawn);
        board.setPiece(bishop);
        board.setPiece(bishop2);
        board.setPiece(king);
        board.setPiece(queenCaptureEnemy);
        queenCaptureEnemy.calculateLegalMoves();
        assertEquals(expected.toString(), queenCaptureEnemy.getAllLegalMoves().toString());
    }

    /**
     * Test if a black queen can capture a white piece
     */
    @Test
    public void testCalculateLegalMovesCaptureEnemyB() {
        ArrayList<Move> expected = new ArrayList<>();
        Piece queenCaptureEnemy = new Queen(36, Attributes.Color.BLACK, board);
        Piece bishop = new Bishop(35, Attributes.Color.WHITE,board);
        Piece knight = new Knight(37, Attributes.Color.WHITE,board);
        Piece pawn = new Pawn(28, Attributes.Color.WHITE,board);
        Piece rook = new Rook (52, Attributes.Color.WHITE, board);
        Piece king = new King (43, Attributes.Color.WHITE, board);
        Piece knight2 = new Knight (45, Attributes.Color.WHITE, board);
        Piece rook2 = new Rook (29, Attributes.Color.WHITE, board);
        Piece bishop2 = new Bishop(27, Attributes.Color.WHITE,board);

        Move m1 = new Move.CaptureMove(board,queenCaptureEnemy,27);
        expected.add(m1);
        Move m2 = new Move.CaptureMove(board,queenCaptureEnemy,29);
        expected.add(m2);
        Move m3 = new Move.CaptureMove(board,queenCaptureEnemy,28);
        expected.add(m3);
        Move m4 = new Move.CaptureMove(board,queenCaptureEnemy,35);
        expected.add(m4);
        Move m5 = new Move.CaptureMove(board,queenCaptureEnemy,37);
        expected.add(m5);
        Move m6 = new Move.CaptureMove(board,queenCaptureEnemy,43);
        expected.add(m6);
        Move m7 = new Move.NormalMove(board,queenCaptureEnemy,44);
        expected.add(m7);
        Move m8 = new Move.CaptureMove(board,queenCaptureEnemy,52);
        expected.add(m8);
        Move m9 = new Move.CaptureMove(board,queenCaptureEnemy,45);
        expected.add(m9);
        board.setPiecesOnBoard(EMPTY_FEN);
        board.setPiece(rook);
        board.setPiece(rook2);
        board.setPiece(knight);
        board.setPiece(knight2);
        board.setPiece(pawn);
        board.setPiece(bishop);
        board.setPiece(bishop2);
        board.setPiece(king);
        board.setPiece(queenCaptureEnemy);
        queenCaptureEnemy.calculateLegalMoves();
        assertEquals(expected.toString(), queenCaptureEnemy.getAllLegalMoves().toString());
    }
}