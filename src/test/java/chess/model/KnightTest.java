package chess.model;

import chess.Attributes;
import chess.controller.Move;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class KnightTest {
    private static final String EMPTY_FEN = "8/8/8/8/8/8/8/8";
    Board board = new Board();
    Piece knight = new Knight(44, Attributes.Color.WHITE, board);
    @Test
    public void testToString() {assertEquals("N", knight.toString());}


    @Test
    public void testCalculateLegalMovesMiddle() {

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
        ArrayList<Move> allLegal = knightMiddle.getAllLegalMoves();
        assertEquals(expected.toString(), knightMiddle.getAllLegalMoves().toString());
    }

    @Test
    public void testCalculateLegalMovesBlockByTeammate() {
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
        ArrayList<Move> allLegal = knightBlockByTeammate.getAllLegalMoves();
        assertEquals(expected.toString(), knightBlockByTeammate.getAllLegalMoves().toString());
    }

    @Test
    public void testCalculateLegalMovesCaptureEnemy() {
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
        ArrayList<Move> allLegal = knightCaptureEnemy.getAllLegalMoves();
        assertEquals(expected.toString(), knightCaptureEnemy.getAllLegalMoves().toString());
    }
}