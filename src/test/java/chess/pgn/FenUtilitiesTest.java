package chess.pgn;

import chess.model.Board;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * FEN Notation Test
 */
public class FenUtilitiesTest {

    Board board = new Board();

    /**
     * Test if we need to save Board Status.
     */
    @Test
    public void loadFENFromBoard() {
        assertEquals("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR"
                , FenUtilities.loadFENFromBoard(board));
    }

}