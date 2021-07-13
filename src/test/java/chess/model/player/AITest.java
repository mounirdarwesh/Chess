package chess.model.player;

import chess.Attributes;
import chess.controller.CliController;
import chess.controller.Move;
import chess.model.game.CliGame;
import chess.model.game.Game;
import chess.model.pieces.King;
import chess.model.pieces.Piece;
import chess.model.pieces.Queen;
import chess.view.cli.Cli;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test AI Moves
 */
public class AITest{
    Cli cli = new Cli();
    CliController cliController = new CliController(cli,true,false);
    Game aiGame = new CliGame(cliController);

    private static final String EMPTY_FEN = "8/8/8/8/8/8/8/8";

    /**
     * AI move checker
     */
    @Test
    public void evaluate() {
        cliController.setGameSettings(3,"0");
        cliController.setGameSettings(0,"1");
        ArrayList<Move> expected = new ArrayList<>();

        Piece kingB = new King(55, Attributes.Color.BLACK, cli.getGame().getBoard());
        Piece queenB = new Queen(36, Attributes.Color.BLACK, cli.getGame().getBoard());
        Piece kingW = new King(1, Attributes.Color.WHITE, cli.getGame().getBoard());
        Piece queenW = new Queen(27, Attributes.Color.WHITE, cli.getGame().getBoard());


        cli.getGame().getBoard().setBoardFromFEN(EMPTY_FEN);
        cli.getGame().getBoard().setPiece(kingB, kingB.getPosition());
        cli.getGame().getBoard().setPiece(kingW, kingW.getPosition());
        cli.getGame().getBoard().setPiece(queenB, queenB.getPosition());
        cli.getGame().getBoard().setPiece(queenW, queenW.getPosition());
    }
}