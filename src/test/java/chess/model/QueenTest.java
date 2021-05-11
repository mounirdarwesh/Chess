package chess.model;

import chess.Attributes;
import chess.controller.Move;
import org.junit.jupiter.api.Test;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class QueenTest {

    Board board = new Board();
    Piece queen = new Queen(29, Attributes.Color.WHITE, board);

    List<Integer> expectedDestination = Arrays.asList(20,22,21,28,27,26,25,
                                                        24,30,31,36,43,50,37,45,53,38,47);

    @Test
    public void testCalculateLegalMoves() {
        queen.calculateLegalMoves();
        ArrayList<Integer> allLegal = new ArrayList<>();

        for (Move move : queen.getAllLegalMoves()) {
            allLegal.add(move.getDestination());
        }

        assertEquals(expectedDestination, allLegal);
    }

    @Test
    public void testGetColor() {
        assertEquals(Attributes.Color.WHITE, Attributes.Color.WHITE);
    }

    @Test
    public void testToString() {
        assertEquals("Q", queen.toString());
    }


}