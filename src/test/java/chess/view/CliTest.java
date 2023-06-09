package chess.view;

import chess.Attributes;
import chess.controller.CliController;
import chess.view.cli.Cli;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * test of the class Cli
 */
public class CliTest {
    Cli cli = new Cli();
    CliController controller = new CliController(cli,true,false);

    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    /**
     * to get System Out from Console.
     */
    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    /**
     * test for welcome screen show in console
     */
    @Test
    public void showWelcomeScreen() {
        cli.showWelcomeScreen();
        assertEquals("*****************************************************\n" +
                        "* Welcome to the beta version of the chess program! *\n" +
                        "*****************************************************\n" +
                        "*               Choose your opponent                *\n" +
                        "*                                                   *\n" +
                        "*     1. Human                       2. Computer    *\n" +
                        "*                                                   *\n" +
                        "*****************************************************\n" +
                        "*****************************************************",
                outputStreamCaptor.toString().trim());
    }

    /**
     * read Input from the Computer check
     */
    @Test
    public void readInputFromComputer(){
        assertTrue(cli.readInputFromComputer("a2-a4"));
    }

    /**
     *  show notification when Player has won the Game
     */
    @Test
    public void notifyUserWIN(){
        cli.notifyUser(Attributes.GameStatus.ENDED_IN_WIN,
                cli.getGame().getWhitePlayer());
        assertEquals("White Player has won the game!",outputStreamCaptor.toString().trim());
    }

    /**
     * show notification when king in check
     */
    @Test
    public void notifyUserCHECK(){
        cli.notifyUser(Attributes.GameStatus.KING_IN_CHECK,
                cli.getGame().getWhitePlayer());
        assertEquals("White Player's king is in check.",outputStreamCaptor.toString().trim());
    }


    /**
     * notify the User when Game is ended in Draw
     */
    @Test
    public void notifyUserDRAW(){
        cli.notifyUser(Attributes.GameStatus.ENDED_IN_DRAW,
                cli.getGame().getWhitePlayer());
        assertEquals("Game Ended in a draw.",outputStreamCaptor.toString().trim());
    }
}
