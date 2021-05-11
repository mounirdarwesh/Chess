package chess.model;

import chess.Attributes;
import chess.controller.Move;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RookTest {
    private static final String EMPTY_FEN = "8/8/8/8/8/8/8/8";
    Board board = new Board();
    Piece rook = new Rook(16, Attributes.Color.BLACK, board);

    @Test
    public void testToString() {
        assertEquals("r", rook.toString());
    }

    @Test
    public void testCalculateLegalMovesMiddle() {
        /*rook.calculateLegalMoves();
        ArrayList<Move> allLegal = rook.getAllLegalMoves();
        for (Move move : allLegal) {
            System.out.println(move.getDestination()+" "+move.toString());
        }*/
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
        ArrayList<Move> allLegal = rookMiddle.getAllLegalMoves();
        assertEquals(expected.toString(), rookMiddle.getAllLegalMoves().toString());
    }

    @Test
    public void testCalculateLegalMovesBlockByTeammate() {
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
        board.setPiece(rook);
        board.setPiece(knight);
        board.setPiece(pawn);
        board.setPiece(bishop);
        board.setPiece(queen);
        board.setPiece(rookBlockByTeammate);
        rookBlockByTeammate.calculateLegalMoves();
        ArrayList<Move> allLegal = rookBlockByTeammate.getAllLegalMoves();
        assertEquals(expected.toString(), rookBlockByTeammate.getAllLegalMoves().toString());
    }

    @Test
    public void testCalculateLegalMovesCaptureEnemy() {
        ArrayList<Move> expected = new ArrayList<Move>();
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
        ArrayList<Move> allLegal = rookCaptureEnemy.getAllLegalMoves();
        assertEquals(expected.toString(),rookCaptureEnemy.getAllLegalMoves().toString());
    }
}