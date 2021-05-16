package chess.controller;

import chess.Attributes;
import chess.cli.Cli;
import chess.model.*;
import chess.view.View;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

/**
 *Test the class Move
 * @author Gruppe45
 */
public class MoveTest {
    //Empty board
    private static final String EMPTY_FEN = "8/8/8/8/8/8/8/8";
    View view = new Cli();
    CliController cli = new CliController(view, true);
    Board board = new Board();
    Player player = new HumanPlayer(Attributes.Color.BLACK);
    Player player2 = new HumanPlayer(Attributes.Color.WHITE);
    Game game = new Game(cli, board, player, player2);

    /**
     * Test the promotion of a pawn
     */
    @Test
    public void PromotionMove() {
        ArrayList<Move> expected48 = new ArrayList<>();
        ArrayList<Move> expected55 = new ArrayList<>();
        Piece pawnW48 = new Pawn(48, Attributes.Color.WHITE, board);
        Piece rookB = new Rook(57, Attributes.Color.BLACK,board);
        Piece pawnW53 = new Pawn(53, Attributes.Color.WHITE,board);
        Piece knightB = new Knight(60, Attributes.Color.BLACK,board);
        Piece bishopB = new Bishop(62, Attributes.Color.BLACK,board);
        int fromPosition = pawnW48.getPosition();
        assertEquals(48,fromPosition);
        Move m1 = new Move.PromotionMove(board, pawnW48, 56,'Q');
        expected48.add(m1);
        Move m2 = new Move.PromotionMove(board, pawnW48, 57,'N');
        expected48.add(m2);
        Move m3 = new Move.PromotionMove(board,pawnW53,61,' ');
        expected55.add(m3);
        Move m4 = new Move.PromotionMove(board,pawnW53,60,'R');
        expected55.add(m4);
        Move m5 = new Move.PromotionMove(board,pawnW53,62,'B');
        expected55.add(m5);
        board.setPiecesOnBoard(EMPTY_FEN);
        board.setPiece(pawnW48);
        board.setPiece(pawnW53);
        board.setPiece(rookB);
        board.setPiece(bishopB);
        board.setPiece(knightB);

        pawnW48.calculateLegalMoves();
        pawnW53.calculateLegalMoves();

        player.makeMove(m1);
        player.makeMove(m2);
        player.makeMove(m3);
        player.makeMove(m4);
        player.makeMove(m5);
        assertEquals(expected48.toString(), pawnW48.getAllLegalMoves().toString());
        assertEquals(expected55.toString(), pawnW53.getAllLegalMoves().toString());
    }

    /**
     * Test of the castling move King side (right side)
     */
    @Test
    public void CastlingMoveKingSide(){
        ArrayList<Move> expectedK = new ArrayList<>();
        ArrayList<Move> expectedR = new ArrayList<>();
        Piece kingW4 = new King(4, Attributes.Color.WHITE,board);
        Piece rookW7 = new Rook(7, Attributes.Color.WHITE,board);
        Piece queenW3 = new Queen(3, Attributes.Color.WHITE,board);
        Piece pawnW15 = new Pawn(15, Attributes.Color.WHITE,board);
        Piece pawnW14 = new Pawn(14, Attributes.Color.WHITE,board);
        Piece pawnW13 = new Pawn(13, Attributes.Color.WHITE,board);
        Piece bishopW12 = new Bishop(12, Attributes.Color.WHITE,board);
        Piece pawnW11 = new Pawn(11, Attributes.Color.WHITE,board);

        Move mk1 = new Move.NormalMove(board,kingW4,5);
        expectedK.add(mk1);
        Move mk2 = new Move.CastlingMove(board,kingW4,6,rookW7,5);
        expectedK.add(mk2);
        Move mr1 = new Move.NormalMove(board, rookW7,6);
        expectedR.add(mr1);
        Move mr2 = new Move.NormalMove(board, rookW7,5);
        expectedR.add(mr2);

        kingW4.setFirstMove(true);
        rookW7.setFirstMove(true);
        board.setPiecesOnBoard(EMPTY_FEN);
        board.setPiece(kingW4);
        board.setPiece(rookW7);
        board.setPiece(bishopW12);
        board.setPiece(pawnW11);
        board.setPiece(pawnW13);
        board.setPiece(pawnW14);
        board.setPiece(pawnW15);
        board.setPiece(queenW3);
        kingW4.calculateLegalMoves();
        rookW7.calculateLegalMoves();
        player.makeMove(mk2);

        assertEquals(expectedK.toString(), kingW4.getAllLegalMoves().toString());
        assertEquals(expectedR.toString(), rookW7.getAllLegalMoves().toString());
    }


    /**
     * Test of the castling move Queen side (left side)
     */
    @Test
    public void CastlingMoveQueenSide(){
        ArrayList<Move> expectedK = new ArrayList<>();
        ArrayList<Move> expectedR = new ArrayList<>();
        Piece kingW4 = new King(4, Attributes.Color.WHITE,board);
        Piece rookW0 = new Rook(0, Attributes.Color.WHITE,board);
        Piece queenW5 = new Queen(5, Attributes.Color.WHITE,board);
        Piece pawnW8 = new Pawn(8, Attributes.Color.WHITE,board);
        Piece pawnW9 = new Pawn(9, Attributes.Color.WHITE,board);
        Piece pawnW10 = new Pawn(10, Attributes.Color.WHITE,board);
        Piece bishopW11 = new Bishop(11, Attributes.Color.WHITE,board);
        Piece pawnW12 = new Pawn(12, Attributes.Color.WHITE,board);
        Piece pawnW13 = new Pawn(13, Attributes.Color.WHITE,board);

        Move mk1 = new Move.NormalMove(board,kingW4,3);
        expectedK.add(mk1);
        Move mk2 = new Move.CastlingMove(board,kingW4,2,rookW0,3);
        expectedK.add(mk2);
        Move mr1 = new Move.NormalMove(board, rookW0,1);
        expectedR.add(mr1);
        Move mr2 = new Move.NormalMove(board, rookW0,2);
        expectedR.add(mr2);
        Move mr3 = new Move.NormalMove(board, rookW0,3);
        expectedR.add(mr3);

        kingW4.setFirstMove(true);
        rookW0.setFirstMove(true);
        board.setPiecesOnBoard(EMPTY_FEN);
        board.setPiece(kingW4);
        board.setPiece(rookW0);
        board.setPiece(bishopW11);
        board.setPiece(pawnW8);
        board.setPiece(pawnW13);
        board.setPiece(pawnW12);
        board.setPiece(pawnW10);
        board.setPiece(pawnW9);
        board.setPiece(queenW5);
        kingW4.calculateLegalMoves();
        rookW0.calculateLegalMoves();
        player.makeMove(mk2);

        assertEquals(expectedK.toString(), kingW4.getAllLegalMoves().toString());
        assertEquals(expectedR.toString(), rookW0.getAllLegalMoves().toString());

    }

    /**
     * Test of the EnPassant move
     */
    @Test
    public void EnPassantMove(){
        ArrayList<Move> expected = new ArrayList<>();
        Piece pawnPassant = new Pawn(35, Attributes.Color.WHITE, board);
        Piece pawn = new Pawn(50, Attributes.Color.BLACK,board);
        pawn.setFirstMove(true);
        Move m0 = new Move.DoublePawnMove(board,pawn,34);
        player2.makeMove(m0);

        Move m1 = new Move.NormalMove(board,pawnPassant,43);
        expected.add(m1);
        Move m2 = new Move.EnPassantMove(board,pawnPassant,42,34);
        expected.add(m2);

        pawnPassant.setFirstMove(false);
        board.setPiecesOnBoard(EMPTY_FEN);
        board.setPiece(pawn);
        board.setPiece(pawnPassant);
        player.addToPlayersPieces(pawn);
        player2.addToPlayersPieces(pawnPassant);
        player.setAllowEnPassant(true);
        pawnPassant.calculateLegalMoves();
        player.makeMove(m2);
        assertEquals(expected.toString(), pawnPassant.getAllLegalMoves().toString());
    }


    /**
     * Test of the capture move
     */
    @Test
    public void CaptureMove(){
        ArrayList<Move> expected = new ArrayList<>();
        Piece rookW = new Rook(35, Attributes.Color.WHITE, board);
        Piece bishop = new Bishop(36, Attributes.Color.BLACK, board);
        Piece knight = new Knight(34, Attributes.Color.BLACK, board);
        Piece pawn = new Pawn(43, Attributes.Color.BLACK, board);
        Piece queen = new Queen(27, Attributes.Color.BLACK,board);

        Move m1 = new Move.CaptureMove(board, rookW, 27);
        expected.add(m1);
        Move m2 = new Move.CaptureMove(board, rookW, 34);
        expected.add(m2);
        Move m3 = new Move.CaptureMove(board, rookW, 36);
        expected.add(m3);
        Move m4 = new Move.CaptureMove(board, rookW, 43);
        expected.add(m4);

        board.setPiecesOnBoard(EMPTY_FEN);
        board.setPiece(bishop);
        board.setPiece(pawn);
        board.setPiece(knight);
        board.setPiece(rookW);
        board.setPiece(queen);
        rookW.calculateLegalMoves();
        player.makeMove(m1);
        assertEquals(expected.toString(), rookW.getAllLegalMoves().toString());
    }

}