package chess.model;

import chess.Attributes;
import chess.model.pieces.King;
import chess.model.pieces.Piece;
import chess.model.pieces.Queen;
import chess.model.pieces.Rook;
import chess.controller.CliController;
import chess.view.cli.Cli;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * class Game Test
 */
public class GameTest {
    Cli view = new Cli();
    CliController cli = new CliController(view, true, true);
    private static final String EMPTY_FEN = "8/8/8/8/8/8/8/8";

    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    /**
     * to get System Out from Console.
     */
    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    /**
     * check Game Status when the Players King in Check.
     */
    @Test
    public void checkGameStatus() {
        cli.getGame().getBoard().setBoardFromFEN(EMPTY_FEN);
        Piece kingW = new King(1, Attributes.Color.WHITE, cli.getGame().getBoard());
        Piece rookB = new Rook(2, Attributes.Color.BLACK, cli.getGame().getBoard());
        kingW.setFirstMove(false);
        cli.getGame().getBoard().setPiece(kingW, kingW.getPosition());
        cli.getGame().getBoard().setPiece(rookB, rookB.getPosition());
        cli.getGame().getWhitePlayer().getPlayerPieces().clear();
        cli.getGame().getBlackPlayer().getPlayerPieces().clear();
        cli.getGame().getBlackPlayer().addToPlayersPieces(rookB);
        cli.getGame().getWhitePlayer().addToPlayersPieces(kingW);
        cli.getGame().setCurrentPlayer(cli.getGame().getWhitePlayer());
        cli.getGame().checkGameStatus();
        assertEquals("White Player's king is in check.", outputStreamCaptor.toString()
                .trim());
    }

    /**
     * check Game Status when the Players King Checkmate.
     */
    @Test
    public void checkGameStatusCheckMate() {
        Piece kingCheckMate = new King(63, Attributes.Color.WHITE, cli.getGame().getBoard());
        Piece rook = new Rook(7, Attributes.Color.BLACK, cli.getGame().getBoard());
        Piece queen = new Queen(46, Attributes.Color.BLACK, cli.getGame().getBoard());
        kingCheckMate.setFirstMove(false);
        cli.getGame().getBoard().setBoardFromFEN(EMPTY_FEN);
        cli.getGame().getBoard().setPiece(kingCheckMate, kingCheckMate.getPosition());
        cli.getGame().getBoard().setPiece(queen, queen.getPosition());
        cli.getGame().getBoard().setPiece(rook, rook.getPosition());
        cli.getGame().getWhitePlayer().getPlayerPieces().clear();
        cli.getGame().getBlackPlayer().getPlayerPieces().clear();
        cli.getGame().getWhitePlayer().addToPlayersPieces(kingCheckMate);
        cli.getGame().getBlackPlayer().addToPlayersPieces(rook);
        cli.getGame().getBlackPlayer().addToPlayersPieces(queen);

        cli.getGame().setCurrentPlayer(cli.getGame().getWhitePlayer());
        cli.getGame().checkGameStatus();
        assertEquals("Black player has won the game!", outputStreamCaptor.toString().trim());
    }

    /**
     * test white player
     */
    @Test
    public void getWhitePlayer() {
        assertEquals("White Player", cli.getGame().getWhitePlayer().toString());
    }

    /**
     * test black player
     */
    @Test
    public void getBlackPlayer() {
        assertEquals("Black player", cli.getGame().getBlackPlayer().toString());
    }

    /**
     * add a Piece to Beaten
     */
    @Test
    public void addToBeaten(){
        ArrayList<Piece> expected = new ArrayList<>();
        Piece piece = cli.getGame().getBoard().getPiecesOnBoard().get(4);
        expected.add(piece);
        cli.getGame().addToBeaten(piece);
        assertEquals(expected.toString(),cli.getBeatenPieces().toString());
    }

    /**
     * remove a Piece from Beaten
     */
    @Test
    public void removeFromBeaten(){
        ArrayList<Piece> expected = new ArrayList<>();
        Piece piece = cli.getGame().getBoard().getPiecesOnBoard().get(4);
        Piece piece1 = cli.getGame().getBoard().getPiecesOnBoard().get(10);
        cli.getGame().addToBeaten(piece1);
        cli.getGame().addToBeaten(piece);
        expected.add(piece1);
        cli.getGame().removeFromBeaten(piece);
        assertEquals(expected.toString(),cli.getBeatenPieces().toString());
    }
}
