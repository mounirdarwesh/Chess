package chess.pgn;

import chess.controller.CliController;
import chess.model.Board;
import chess.view.cli.Cli;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * FEN Notation Test
 */
public class FenUtilitiesTest {
    Cli view = new Cli();
    CliController cli = new CliController(view, true, false);
    Board board = new Board();

    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    /**
     * to get System Out from Console.
     */
    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    /**
     * Test if we need to save Board Status.
     */
    @Test
    public void loadFENFromBoard() {
        assertEquals("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR"
                , FenUtilities.loadFENFromBoard(board));
    }

    /**
     * create fen notation from Board
     */
    @Test
    public void createFENFromGame(){
        System.out.println(FenUtilities.createFENFromGame(cli.getGame()));
        assertEquals("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w - -",
                outputStreamCaptor.toString().trim());
    }

    /**
     * Load Castle info. from Game
     */
    @Test
    public void loadCastleInformation(){
        // there no castle move has been done
        assertEquals("-",FenUtilities.loadCastleInformation(cli.getGame()));
    }

    /**
     * Load EnPassant info. from Game
     */
    @Test
    public void loadEnPassantInformation(){
        // there no EnPassant move has been done
        assertEquals("-",FenUtilities.loadEnPassantInformation(cli.getGame()));
    }
}