package chess.controller;

import chess.view.cli.Cli;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Controller test
 */
public class ControllerTest {

    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    Cli cli = new Cli();
    CliController cliController = new CliController(cli, true, false);

    /**
     * to get System Out from Console.
     */
    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    /**
     * test Undo Move to Initial status
     */
    @Test
    public void undoMove() {
        Move move1 = new Move.NormalMove(cli.getGame().getBoard(),
                cli.getGame().getBoard().getPiece(8), 24);
        cli.getGame().getAllListOfMoves().add(0, move1);
        cli.getGame().getWhitePlayer().makeMove(move1);
        Move move2 = new Move.NormalMove(cli.getGame().getBoard(),
                cli.getGame().getBoard().getPiece(55), 39);
        cli.getGame().getAllListOfMoves().add(1, move2);
        cli.getGame().getBlackPlayer().makeMove(move2);
        cli.getGame().getController().undoMove(0);
        assertEquals("8 r n b q k b n r\n" +
                "7 p p p p p p p p\n" +
                "6                \n" +
                "5                \n" +
                "4                \n" +
                "3                \n" +
                "2 P P P P P P P P\n" +
                "1 R N B Q K B N R\n" +
                "  a b c d e f g h", outputStreamCaptor.toString().trim());
    }

    /**
     * Timer converter
     */
    @Test
    public void convertTime() {
        assertEquals("01:00", cliController.convertTime(60000));
    }

    /**
     * test redo Move after Undo
     */
    @Test
    public void redoMove() {
        Move move1 = new Move.NormalMove(cli.getGame().getBoard(),
                cli.getGame().getBoard().getPiece(8), 24);
        cli.getGame().getAllListOfMoves().add(0, move1);
        cli.getGame().getWhitePlayer().makeMove(move1);
        Move move2 = new Move.NormalMove(cli.getGame().getBoard(),
                cli.getGame().getBoard().getPiece(55), 39);
        cli.getGame().getAllListOfMoves().add(1, move2);
        cli.getGame().getBlackPlayer().makeMove(move2);
        cli.getGame().getController().undoMove(1);
        cli.getGame().getController().redoMove(1);
        assertEquals("8 r n b q k b n r\n" +
                "7 p p p p p p p  \n" +
                "6                \n" +
                "5               p\n" +
                "4 P              \n" +
                "3                \n" +
                "2   P P P P P P P\n" +
                "1 R N B Q K B N R\n" +
                "  a b c d e f g h", cli.getGame().getBoard().toString());
    }
}