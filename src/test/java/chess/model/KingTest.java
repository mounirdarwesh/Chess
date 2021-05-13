package chess.model;

import chess.Attributes;
import chess.controller.Move;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class KingTest {

    private static final String EMPTY_FEN = "8/8/8/8/8/8/8/8";
    Board board = new Board();
    Piece king = new King(44, Attributes.Color.WHITE, board);

    @Test
    public void testToString() {
        assertEquals("K", king.toString());
    }

    @Test
    public void testCalculateLegalMovesMiddle() {

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

    @Test
    public void testCalculateLegalMovesBlockByTeammate() {
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

    @Test
    public void testCalculateLegalMovesCaptureEnemy() {
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

    @Test
    public void castling(){

    }
}