package chess.controller;

import chess.Attributes;
import chess.model.*;
import chess.model.game.CliGame;
import chess.model.game.Game;
import chess.model.pieces.*;
import chess.view.cli.Cli;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test the class Move
 *
 * @author Gruppe45
 */
public class MoveTest {
    //Empty board
    private static final String EMPTY_FEN = "8/8/8/8/8/8/8/8";
    Cli view = new Cli();
    CliController cli = new CliController(view, true, true);
    Board board = new Board();
    Game game = new CliGame(cli);


    Piece pawnW48 = new Pawn(48, Attributes.Color.WHITE, board);
    Piece rookB = new Rook(57, Attributes.Color.BLACK, board);
    Piece pawnW53 = new Pawn(53, Attributes.Color.WHITE, board);
    Piece knightB = new Knight(60, Attributes.Color.BLACK, board);
    Piece bishopB = new Bishop(62, Attributes.Color.BLACK, board);

    /**
     * add Pieces to Beaten
     */
    public void addPieces(){
        game.setCurrentPlayer(game.getWhitePlayer());
        game.getCurrentPlayer().getBeaten().add(new Queen(3, Attributes.Color.WHITE, board));
        game.getCurrentPlayer().getBeaten().add(new Rook(4, Attributes.Color.WHITE, board));
        game.getCurrentPlayer().getBeaten().add(new Knight(5, Attributes.Color.WHITE, board));
        game.getCurrentPlayer().getBeaten().add(new Bishop(6, Attributes.Color.WHITE, board));

        // if a Promoted Figure was beaten the remove, they must removed
        cli.getGame().getWhitePlayer().getBeaten().add(new Bishop(6, Attributes.Color.WHITE, board));
        cli.getGame().getWhitePlayer().getBeaten().add(new Queen(3, Attributes.Color.WHITE, board));
        cli.getGame().getWhitePlayer().getBeaten().add(new Rook(4, Attributes.Color.WHITE, board));
        cli.getGame().getWhitePlayer().getBeaten().add(new Knight(5, Attributes.Color.WHITE, board));
    }

    /**
     * Test the promotion of a pawn
     */
    @Test
    public void PromotionMove() {
        List<Move> expected48 = new ArrayList<>();
        List<Move> expected55 = new ArrayList<>();
        int fromPosition = pawnW48.getPosition();
        assertEquals(48, fromPosition);
        // 'Q'
        Move m1 = new Move.PromotionMove(board, pawnW48, 56);
        expected48.add(m1);
        //'N'
        Move m2 = new Move.PromotionMove(board, pawnW48, 57);
        expected48.add(m2);
        //' '
        Move m3 = new Move.PromotionMove(board, pawnW53, 61);
        expected55.add(m3);
        //'R'
        Move m4 = new Move.PromotionMove(board, pawnW53, 60);
        expected55.add(m4);
        //'B'
        Move m5 = new Move.PromotionMove(board, pawnW53, 62);

        expected55.add(m5);
        board.setBoardFromFEN(EMPTY_FEN);
        board.setPiece(pawnW48, pawnW48.getPosition());
        board.setPiece(pawnW53, pawnW53.getPosition());
        board.setPiece(rookB, rookB.getPosition());
        board.setPiece(bishopB, bishopB.getPosition());
        board.setPiece(knightB, knightB.getPosition());

        pawnW48.calculateLegalMoves();
        pawnW53.calculateLegalMoves();
        addPieces();

        cli.getGame().setCharToPromote('Q');
        game.getWhitePlayer().makeMove(m1);
        cli.getGame().setCharToPromote('N');
        game.getWhitePlayer().makeMove(m2);
        cli.getGame().setCharToPromote(' ');
        game.getWhitePlayer().makeMove(m3);
        cli.getGame().setCharToPromote('R');
        game.getWhitePlayer().makeMove(m4);
        cli.getGame().setCharToPromote('B');
        game.getWhitePlayer().makeMove(m5);
        m5.undo();
        assertEquals(expected48.toString(), pawnW48.getAllLegalMoves().toString());
        assertEquals(expected55.toString(), pawnW53.getAllLegalMoves().toString());
    }

    /**
     * Test of the castling move King side (right side)
     */
    @Test
    public void CastlingMoveKingSide() {
        ArrayList<Move> expectedK = new ArrayList<>();
        ArrayList<Move> expectedR = new ArrayList<>();
        Piece kingW4 = new King(4, Attributes.Color.WHITE, board);
        Piece rookW7 = new Rook(7, Attributes.Color.WHITE, board);
        Piece queenW3 = new Queen(3, Attributes.Color.WHITE, board);
        Piece pawnW15 = new Pawn(15, Attributes.Color.WHITE, board);
        Piece pawnW14 = new Pawn(14, Attributes.Color.WHITE, board);
        Piece pawnW13 = new Pawn(13, Attributes.Color.WHITE, board);
        Piece bishopW12 = new Bishop(12, Attributes.Color.WHITE, board);
        Piece pawnW11 = new Pawn(11, Attributes.Color.WHITE, board);

        Move mk1 = new Move.NormalMove(board, kingW4, 5);
        expectedK.add(mk1);
        Move mk2 = new Move.CastlingMove(board, kingW4, 6, rookW7);
        expectedK.add(mk2);
        Move mr1 = new Move.NormalMove(board, rookW7, 6);
        expectedR.add(mr1);
        Move mr2 = new Move.NormalMove(board, rookW7, 5);
        expectedR.add(mr2);

        kingW4.setFirstMove(true);
        rookW7.setFirstMove(true);
        board.setBoardFromFEN(EMPTY_FEN);
        board.setPiece(kingW4, kingW4.getPosition());
        board.setPiece(rookW7, rookW7.getPosition());
        board.setPiece(bishopW12, bishopW12.getPosition());
        board.setPiece(pawnW11, pawnW11.getPosition());
        board.setPiece(pawnW13, pawnW13.getPosition());
        board.setPiece(pawnW14, pawnW14.getPosition());
        board.setPiece(pawnW15, pawnW15.getPosition());
        board.setPiece(queenW3, queenW3.getPosition());
        kingW4.calculateLegalMoves();
        rookW7.calculateLegalMoves();
        game.getWhitePlayer().makeMove(mk2);

        assertEquals(expectedK.toString(), kingW4.getAllLegalMoves().toString());
        assertEquals(expectedR.toString(), rookW7.getAllLegalMoves().toString());
        mk2.undo();
    }


    /**
     * Test of the castling move Queen side (left side)
     */
    @Test
    public void CastlingMoveQueenSide() {
        ArrayList<Move> expectedK = new ArrayList<>();
        ArrayList<Move> expectedR = new ArrayList<>();
        Piece kingW4 = new King(4, Attributes.Color.WHITE, board);
        Piece rookW0 = new Rook(0, Attributes.Color.WHITE, board);
        Piece queenW5 = new Queen(5, Attributes.Color.WHITE, board);
        Piece pawnW8 = new Pawn(8, Attributes.Color.WHITE, board);
        Piece pawnW9 = new Pawn(9, Attributes.Color.WHITE, board);
        Piece pawnW10 = new Pawn(10, Attributes.Color.WHITE, board);
        Piece bishopW11 = new Bishop(11, Attributes.Color.WHITE, board);
        Piece pawnW12 = new Pawn(12, Attributes.Color.WHITE, board);
        Piece pawnW13 = new Pawn(13, Attributes.Color.WHITE, board);

        Move mk1 = new Move.NormalMove(board, kingW4, 3);
        expectedK.add(mk1);
        Move mk2 = new Move.CastlingMove(board, kingW4, 2, rookW0);
        expectedK.add(mk2);
        Move mr1 = new Move.NormalMove(board, rookW0, 1);
        expectedR.add(mr1);
        Move mr2 = new Move.NormalMove(board, rookW0, 2);
        expectedR.add(mr2);
        Move mr3 = new Move.NormalMove(board, rookW0, 3);
        expectedR.add(mr3);

        kingW4.setFirstMove(true);
        rookW0.setFirstMove(true);
        board.setBoardFromFEN(EMPTY_FEN);
        board.setPiece(kingW4, kingW4.getPosition());
        board.setPiece(rookW0, rookW0.getPosition());
        board.setPiece(bishopW11, bishopW11.getPosition());
        board.setPiece(pawnW8, pawnW8.getPosition());
        board.setPiece(pawnW13, pawnW13.getPosition());
        board.setPiece(pawnW12, pawnW12.getPosition());
        board.setPiece(pawnW10, pawnW10.getPosition());
        board.setPiece(pawnW9, pawnW9.getPosition());
        board.setPiece(queenW5, queenW5.getPosition());
        kingW4.calculateLegalMoves();
        rookW0.calculateLegalMoves();
        game.getWhitePlayer().makeMove(mk2);

        assertEquals(expectedK.toString(), kingW4.getAllLegalMoves().toString());
        assertEquals(expectedR.toString(), rookW0.getAllLegalMoves().toString());

    }

    /**
     * Test of the EnPassant move
     */
    @Test
    public void EnPassantMove() {
        cli.getGame().getBoard().setBoardFromFEN(EMPTY_FEN);
        ArrayList<Move> expected = new ArrayList<>();
        Piece pawnPassant = new Pawn(35, Attributes.Color.WHITE, cli.getGame().getBoard());
        pawnPassant.setFirstMove(false);
        cli.getGame().getBoard().setPiece(pawnPassant, pawnPassant.getPosition());
        cli.getGame().getBlackPlayer().addToPlayersPieces(pawnPassant);

        Piece pawn = new Pawn(50, Attributes.Color.BLACK, cli.getGame().getBoard());
        cli.getGame().getBoard().setPiece(pawn, pawn.getPosition());
        cli.getGame().getWhitePlayer().addToPlayersPieces(pawn);

        pawn.setFirstMove(true);
        Move m0 = new Move.DoublePawnMove(cli.getGame().getBoard(), pawn, 34);
        cli.getGame().getWhitePlayer().makeMove(m0);

        Move m1 = new Move.NormalMove(cli.getGame().getBoard(), pawnPassant, 43);
        expected.add(m1);
        Move m2 = new Move.EnPassantMove(cli.getGame().getBoard(), pawnPassant, 42, 34);
        expected.add(m2);

        cli.getGame().setCurrentPlayer(cli.getGame().getBlackPlayer());
        //game.getBlackPlayer().setAllowEnPassant(true);
        cli.getGame().setEnPassantPawn(pawnPassant);
        cli.getGame().getWhitePlayer().setEnPassantPieceToCapture(pawn);
        pawnPassant.calculateLegalMoves();
        assertEquals(expected.toString(), pawnPassant.getAllLegalMoves().toString());
        cli.getGame().getWhitePlayer().makeMove(m2);
        m2.undo();
    }

    /**
     * Test of the capture move
     */
    @Test
    public void CaptureMove() {
        ArrayList<Move> expected = new ArrayList<>();
        Piece rookW = new Rook(35, Attributes.Color.WHITE, board);
        Piece bishop = new Bishop(36, Attributes.Color.BLACK, board);
        Piece knight = new Knight(34, Attributes.Color.BLACK, board);
        Piece pawn = new Pawn(43, Attributes.Color.BLACK, board);
        Piece queen = new Queen(27, Attributes.Color.BLACK, board);

        Move m1 = new Move.CaptureMove(board, rookW, 27);
        expected.add(m1);
        Move m2 = new Move.CaptureMove(board, rookW, 34);
        expected.add(m2);
        Move m3 = new Move.CaptureMove(board, rookW, 36);
        expected.add(m3);
        Move m4 = new Move.CaptureMove(board, rookW, 43);
        expected.add(m4);

        board.setBoardFromFEN(EMPTY_FEN);
        board.setPiece(bishop, bishop.getPosition());
        board.setPiece(pawn, pawn.getPosition());
        board.setPiece(knight, knight.getPosition());
        board.setPiece(rookW, rookW.getPosition());
        board.setPiece(queen, queen.getPosition());
        rookW.calculateLegalMoves();
        game.getWhitePlayer().makeMove(m1);
        m1.undo();
        assertEquals(expected.toString(), rookW.getAllLegalMoves().toString());
    }

}
