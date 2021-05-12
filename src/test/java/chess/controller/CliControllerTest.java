package chess.controller;


import chess.cli.Cli;
import chess.view.View;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CliControllerTest {
    View view = new Cli();
    CliController cli = new CliController(view, true);

    @Test
    public void isValidInput() {
        assertTrue(cli.isValidInput("a2-a4"));
        assertTrue(cli.isValidInput("a2-a4Q"));
        assertFalse(cli.isValidInput("b1f3"));

    }

    @Test
    public void getMoveFromPosition() {
        assertEquals(8, cli.getMoveFromPosition("a2-a3"));
    }

    @Test
    public void getMoveToPosition() {
        assertEquals(16, cli.getMoveToPosition("a2-a3"));
    }

    @Test
    public void isValidMove() {
        // The King in the start of the Game has no Moves to do.
        assertFalse(cli.isValidMove("e1-e2"));
        // Pawn can move tow Steps forward in the Initial Status.
        assertTrue(cli.isValidMove("a2-a4"));
        // Knight Move
        assertTrue(cli.isValidMove("g1-h3"));
    }
}