package chess.controller;


import chess.Attributes;
import chess.model.*;
import chess.model.pieces.*;
import chess.view.cli.Cli;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;


import static org.junit.jupiter.api.Assertions.*;

/**
 * Test of the class CliController
 *
 * @author Gruppe45
 */
public class CliControllerTest {
    Cli view = new Cli();
    CliController cli = new CliController(view, true, true);
    Board board = new Board();


    /**
     * Test of a valid input
     */
    @Test
    public void isValidInput() {
        assertTrue(cli.isValidInput("a2-a4"));
        assertTrue(cli.isValidInput("a2-a4Q"));
        assertFalse(cli.isValidInput("b1f3"));

    }

   /* *//**
     * Test a move from position(source position)
     *//*
    @Test
    public void getMoveFromPosition() {
        assertEquals(8, cli.getMoveFromPosition("a2-a3"));
    }

    *//**
     * Test a move to position(destination position)
     *//*
    @Test
    public void getMoveToPosition() {
        assertEquals(16, cli.getMoveToPosition("a2-a3"));
    }*/

    /**
     * Test if a move is valid
     */
    @Test
    public void isValidMove() {
        // The King in the start of the Game has no Moves to do.
        assertFalse(cli.isValidMove("e1-e2"));
        // Pawn can move tow Steps forward in the Initial Status.
        assertTrue(cli.isValidMove("a2-a4"));
        // Knight Move
        assertTrue(cli.isValidMove("g1-h3"));
    }

    /**
     * get captured Pieces test
     */
    @Test
    public void getBeatenPiecesBlack() {
        List<Piece> expected = new ArrayList<>();
        Piece pawn = new Pawn(50, Attributes.Color.BLACK, board);
        cli.getBeatenPieces().add(0,pawn);
        expected.add(pawn);
        assertEquals(expected.toString(), cli.getBeatenPieces().toString());
    }

    /**
     * get captured Pieces test
     */
    @Test
    public void getBeatenPiecesWhite() {
        List<Piece> expected = new ArrayList<>();
        Piece pawn = new Pawn(50, Attributes.Color.WHITE, board);
        cli.getBeatenPieces().add(0,pawn);
        expected.add(pawn);
        assertEquals(expected.toString(), cli.getBeatenPieces().toString());
    }
}
