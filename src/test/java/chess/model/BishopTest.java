package chess.model;

import chess.Attributes;
import chess.controller.Move;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class BishopTest {
    private static final String EMPTY_FEN = "8/8/8/8/8/8/8/8";
    Board board = new Board();
    Piece bishop = new Bishop(27, Attributes.Color.WHITE, board);
    @Test
    public void testToString() {
        assertEquals("B", bishop.toString());
    }

    @Test
    public void testCalculateLegalMovesMiddle() {
     /*   bishop.calculateLegalMoves();
        ArrayList<Move> allLegal = bishop.getAllLegalMoves();
        for (Move move : allLegal) {
            System.out.println(move.getDestination());
        }*/
        ArrayList<Move> expected = new ArrayList<>();
        Piece bishopMiddle = new Bishop(27, Attributes.Color.WHITE, board);
        Move m1 = new Move.NormalMove(board,bishopMiddle,18);
        expected.add(m1);
        Move m2 = new Move.NormalMove(board,bishopMiddle,9);
        expected.add(m2);
        Move m3 = new Move.NormalMove(board,bishopMiddle,0);
        expected.add(m3);
        Move m4 = new Move.NormalMove(board,bishopMiddle,20);
        expected.add(m4);
        Move m5 = new Move.NormalMove(board,bishopMiddle,13);
        expected.add(m5);
        Move m6 = new Move.NormalMove(board,bishopMiddle,6);
        expected.add(m6);
        Move m7 = new Move.NormalMove(board,bishopMiddle,34);
        expected.add(m7);
        Move m8 = new Move.NormalMove(board,bishopMiddle,41);
        expected.add(m8);
        Move m9 = new Move.NormalMove(board,bishopMiddle,48);
        expected.add(m9);
        Move m10 = new Move.NormalMove(board,bishopMiddle,36);
        expected.add(m10);
        Move m11 = new Move.NormalMove(board,bishopMiddle,45);
        expected.add(m11);
        Move m12 = new Move.NormalMove(board,bishopMiddle,54);
        expected.add(m12);
        Move m13 = new Move.NormalMove(board,bishopMiddle,63);
        expected.add(m13);
        board.setPiecesOnBoard(EMPTY_FEN);
        board.setPiece(bishopMiddle);
        bishopMiddle.calculateLegalMoves();
        ArrayList<Move> allLegal = bishopMiddle.getAllLegalMoves();
        assertEquals(expected.toString(), bishopMiddle.getAllLegalMoves().toString());
    }
    @Test
    public void testCalculateLegalMovesBlockByTeammate() {
        ArrayList<Move> expected = new ArrayList<>();
        Piece bishopBlockByTeammate = new Bishop(54, Attributes.Color.WHITE, board);
        Piece rook = new Rook(63, Attributes.Color.WHITE,board);
        Piece knight = new Knight(47, Attributes.Color.WHITE,board);
        Piece pawn = new Pawn(45, Attributes.Color.WHITE,board);
        Move m1 = new Move.NormalMove(board,bishopBlockByTeammate,61);
        expected.add(m1);
        board.setPiecesOnBoard(EMPTY_FEN);
        board.setPiece(rook);
        board.setPiece(knight);
        board.setPiece(pawn);
        board.setPiece(bishopBlockByTeammate);
        bishopBlockByTeammate.calculateLegalMoves();
        ArrayList<Move> allLegal = bishopBlockByTeammate.getAllLegalMoves();
        assertEquals(expected.toString(), bishopBlockByTeammate.getAllLegalMoves().toString());
    }

    @Test
    public void testCalculateLegalMovesCaptureEnemy() {
        ArrayList<Move> expected = new ArrayList<Move>();
        Piece bishopCaptureEnemy = new Bishop(14, Attributes.Color.WHITE, board);
        Piece rook = new Rook(7, Attributes.Color.BLACK,board);
        Piece knight = new Knight(28, Attributes.Color.BLACK,board);
        Move m1 = new Move.NormalMove(board,bishopCaptureEnemy,5);
        expected.add(m1);
        Move m2 = new Move.CaptureMove(board,bishopCaptureEnemy,7);
        expected.add(m2);
        Move m3 = new Move.NormalMove(board,bishopCaptureEnemy,21);
        expected.add(m3);
        Move m4 = new Move.CaptureMove(board,bishopCaptureEnemy,28);
        expected.add(m4);
        Move m5 = new Move.NormalMove(board,bishopCaptureEnemy,23);
        expected.add(m5);
        board.setPiecesOnBoard(EMPTY_FEN);
        board.setPiece(rook);
        board.setPiece(knight);
        board.setPiece(bishopCaptureEnemy);
        bishopCaptureEnemy.calculateLegalMoves();
        ArrayList<Move> allLegal = bishopCaptureEnemy.getAllLegalMoves();
        assertEquals(expected.toString(),bishopCaptureEnemy.getAllLegalMoves().toString());
    }
}