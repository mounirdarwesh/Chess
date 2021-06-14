package chess.view;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public class CliTest {
    Cli cli = new Cli();

    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    /**
     * to get System Out from Console.
     */
    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }
    @Test
    public void showWelcomeScreen() {
        cli.showWelcomeScreen();
        assertEquals("*****************************************************\n" +
                "* Welcome to the beta version of the chess program! *\n" +
                "*****************************************************\n" +
                "*               Choose your opponent                *\n" +
                "*                                                   *\n" +
                "*     1. Human                       2. Computer    *\n" +
                "*****************************************************\n" +
                "*****************************************************",
                outputStreamCaptor.toString().trim());
    }
}