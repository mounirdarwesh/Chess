package chess.model;

import chess.Attributes;
import chess.controller.Move;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test of the class Queen
 *
 * @author Gruppe 45
 */
public class QueenTest {
    //Empty board
    private static final String EMPTY_FEN = "8/8/8/8/8/8/8/8";
    Board board = new Board();
    Piece queenW = new Queen(29, Attributes.Color.WHITE, board);
    Piece queenB = new Queen(28, Attributes.Color.BLACK, board);

    /**
     * Test if white queen shown with uppercase "Q"
     */
    @Test
    public void testToStringW() {
        assertEquals("Q", queenW.toString());
    }

    /**
     * Test if black queen shown with lowercase "q"
     */
    @Test
    public void testToStringB() {
        assertEquals("q", queenB.toString());
    }

    /**
     * Test all the legal moves of a white queen in a position in the middle of the board
     */
    @Test
    public void testCalculateLegalMovesMiddleW() {

        ArrayList<Move> expected = new ArrayList<>();
        Piece queenMiddleW = new Queen(35, Attributes.Color.WHITE, board);

        board.setPiecesOnBoard(EMPTY_FEN);
        board.setPiece(queenMiddleW);
        queenMiddleW.calculateLegalMoves();
        expected.addAll(0, queenMiddleW.getAllLegalMoves());
        assertEquals(expected.toString(), queenMiddleW.getAllLegalMoves().toString());
    }

    /**
     * Test all the legal moves of a black queen in a position in the middle of the board
     */
    @Test
    public void testCalculateLegalMovesMiddleB() {

        ArrayList<Move> expected = new ArrayList<>();
        Piece queenMiddleB = new Queen(35, Attributes.Color.BLACK, board);

        board.setPiecesOnBoard(EMPTY_FEN);
        board.setPiece(queenMiddleB);
        queenMiddleB.calculateLegalMoves();
        expected.addAll(0, queenMiddleB.getAllLegalMoves());
        assertEquals(expected.toString(), queenMiddleB.getAllLegalMoves().toString());
    }

    /**
     * Test all the legal moves of a white queen in a position on the edge of the board
     */
    @Test
    public void testCalculateLegalMovesEdgeW() {

        ArrayList<Move> expected = new ArrayList<>();
        Piece queenEdgeW = new Queen(0, Attributes.Color.WHITE, board);

        board.setPiecesOnBoard(EMPTY_FEN);
        board.setPiece(queenEdgeW);
        queenEdgeW.calculateLegalMoves();
        expected.addAll(0, queenEdgeW.getAllLegalMoves());
        assertEquals(expected.toString(), queenEdgeW.getAllLegalMoves().toString());
    }

    /**
     * Test all the legal moves of a black queen in a position on the edge of the board
     */
    @Test
    public void testCalculateLegalMovesEdgeB() {

        ArrayList<Move> expected = new ArrayList<>();
        Piece queenEdgeB = new Queen(0, Attributes.Color.BLACK, board);

        board.setPiecesOnBoard(EMPTY_FEN);
        board.setPiece(queenEdgeB);
        queenEdgeB.calculateLegalMoves();
        expected.addAll(0, queenEdgeB.getAllLegalMoves());
        assertEquals(expected.toString(), queenEdgeB.getAllLegalMoves().toString());
    }

    /**
     * Test which legal moves are exist if a white queen blocked by other white pieces
     */
    @Test
    public void testCalculateLegalMovesBlockByTeammateW() {
        ArrayList<Move> expected = new ArrayList<>();
        Piece queenBlockByTeammateW = new Queen(36, Attributes.Color.WHITE, board);
        Piece bishop = new Bishop(35, Attributes.Color.WHITE, board);
        Piece knight = new Knight(37, Attributes.Color.WHITE, board);
        Piece pawn = new Pawn(28, Attributes.Color.WHITE, board);
        Piece rook = new Rook(52, Attributes.Color.WHITE, board);
        Piece king = new King(43, Attributes.Color.WHITE, board);
        Piece knight2 = new Knight(45, Attributes.Color.WHITE, board);
        Piece rook2 = new Rook(29, Attributes.Color.WHITE, board);
        Piece bishop2 = new Bishop(27, Attributes.Color.WHITE, board);

        Move m1 = new Move.NormalMove(board, queenBlockByTeammateW, 44);
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
        board.setPiece(queenBlockByTeammateW);
        queenBlockByTeammateW.calculateLegalMoves();
        assertEquals(expected.toString(), queenBlockByTeammateW.getAllLegalMoves().toString());
    }

    /**
     * Test which legal moves are exist if a black queen blocked by other black pieces
     */
    @Test
    public void testCalculateLegalMovesBlockByTeammateB() {
        ArrayList<Move> expected = new ArrayList<>();
        Piece queenBlockByTeammateB = new Queen(35, Attributes.Color.BLACK, board);
        Piece bishop = new Bishop(34, Attributes.Color.BLACK, board);
        Piece knight = new Knight(36, Attributes.Color.BLACK, board);
        Piece pawn = new Pawn(27, Attributes.Color.BLACK, board);
        Piece rook = new Rook(51, Attributes.Color.BLACK, board);
        Piece king = new King(42, Attributes.Color.BLACK, board);
        Piece knight2 = new Knight(44, Attributes.Color.BLACK, board);
        Piece rook2 = new Rook(28, Attributes.Color.BLACK, board);
        Piece bishop2 = new Bishop(26, Attributes.Color.BLACK, board);

        Move m1 = new Move.NormalMove(board, queenBlockByTeammateB, 43);
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
        board.setPiece(queenBlockByTeammateB);
        queenBlockByTeammateB.calculateLegalMoves();
        assertEquals(expected.toString(), queenBlockByTeammateB.getAllLegalMoves().toString());
    }

    /**
     * Test if a white queen can capture a black piece
     */
    @Test
    public void testCalculateLegalMovesCaptureEnemyW() {
        ArrayList<Move> expected = new ArrayList<>();
        Piece queenCaptureEnemyW = new Queen(34, Attributes.Color.WHITE, board);
        Piece bishop = new Bishop(33, Attributes.Color.BLACK, board);
        Piece knight = new Knight(35, Attributes.Color.BLACK, board);
        Piece pawn = new Pawn(26, Attributes.Color.BLACK, board);
        Piece rook = new Rook(50, Attributes.Color.BLACK, board);
        Piece king = new King(41, Attributes.Color.BLACK, board);
        Piece knight2 = new Knight(43, Attributes.Color.BLACK, board);
        Piece rook2 = new Rook(27, Attributes.Color.BLACK, board);
        Piece bishop2 = new Bishop(25, Attributes.Color.BLACK, board);

        board.setPiecesOnBoard(EMPTY_FEN);
        board.setPiece(rook);
        board.setPiece(rook2);
        board.setPiece(knight);
        board.setPiece(knight2);
        board.setPiece(pawn);
        board.setPiece(bishop);
        board.setPiece(bishop2);
        board.setPiece(king);
        board.setPiece(queenCaptureEnemyW);
        queenCaptureEnemyW.calculateLegalMoves();
        expected.addAll(0, queenCaptureEnemyW.getAllLegalMoves());
        assertEquals(expected.toString(), queenCaptureEnemyW.getAllLegalMoves().toString());
    }

    /**
     * Test if a black queen can capture a white piece
     */
    @Test
    public void testCalculateLegalMovesCaptureEnemyB() {
        ArrayList<Move> expected = new ArrayList<>();
        Piece queenCaptureEnemyB = new Queen(19, Attributes.Color.BLACK, board);
        Piece bishop = new Bishop(18, Attributes.Color.WHITE, board);
        Piece knight = new Knight(20, Attributes.Color.WHITE, board);
        Piece pawn = new Pawn(11, Attributes.Color.WHITE, board);
        Piece rook = new Rook(35, Attributes.Color.WHITE, board);
        Piece king = new King(26, Attributes.Color.WHITE, board);
        Piece knight2 = new Knight(28, Attributes.Color.WHITE, board);
        Piece rook2 = new Rook(12, Attributes.Color.WHITE, board);
        Piece bishop2 = new Bishop(10, Attributes.Color.WHITE, board);

        board.setPiecesOnBoard(EMPTY_FEN);
        board.setPiece(rook);
        board.setPiece(rook2);
        board.setPiece(knight);
        board.setPiece(knight2);
        board.setPiece(pawn);
        board.setPiece(bishop);
        board.setPiece(bishop2);
        board.setPiece(king);
        board.setPiece(queenCaptureEnemyB);
        queenCaptureEnemyB.calculateLegalMoves();
        expected.addAll(0, queenCaptureEnemyB.getAllLegalMoves());
        assertEquals(expected.toString(), queenCaptureEnemyB.getAllLegalMoves().toString());
    }

    @Test
    public void getSymbolTest(){
        Piece queenW = new Queen(44,Attributes.Color.WHITE,board);
        assertEquals("♕", queenW.getSymbol());
        Piece queenB = new Queen(10, Attributes.Color.BLACK,board);
        assertEquals("♛", queenB.getSymbol());
    }

    @Test
    public void getValueTest(){
        Piece queenW = new Queen(44,Attributes.Color.WHITE,board);
        Piece queenB = new Queen(10, Attributes.Color.BLACK,board);
        assertEquals(1000, queenW.getValue());
        assertEquals(-1000,queenB.getValue());
    }
}