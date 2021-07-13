package chess.util;

import chess.controller.CliController;
import chess.controller.Move;
import chess.view.cli.Cli;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * give to every Tile String a index
 */
public class MoveMapperTest {
    Cli cli = new Cli();
    CliController cliController = new CliController(cli,true,false);

    /**
     * test the Board Mapper
     */
    @Test
    public void map() {
        Move doubleMove = new Move.DoublePawnMove(cli.getGame().getBoard(),
                cli.getGame().getBoard().getPiece(8),24);
        assertEquals(doubleMove.toString(),MoveMapper.map("a2-a4",cli.getGame()).toString());

    }
}