package chess.model.player;

import chess.Attributes;
import chess.controller.CliController;
import chess.model.pieces.Pawn;
import chess.view.cli.Cli;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test AI Moves
 */
public class AITest{
    Cli cli = new Cli();
    CliController cliController = new CliController(cli,true,false);

    private static final String EMPTY_FEN = "8/8/8/8/8/8/8/8";

    /**
     * AI Normal Move
     */
    @Test
    public void evaluateNormal() {
        cli.getGame().getBoard().setBoardFromFEN(EMPTY_FEN);
        AI aiP = new AI(Attributes.Color.WHITE,cli.getGame());
        aiP.getPlayerPieces().add(new Pawn(20, Attributes.Color.WHITE,cli.getGame().getBoard()));
        assertEquals("e3-e4",aiP.evaluate().toString());
    }
}