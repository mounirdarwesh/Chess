package chess.model;

import chess.Attributes;
import chess.controller.Move;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BishopTest {

    Board board = new Board();
    Piece bishop = new Bishop(5, Attributes.Color.WHITE, board);
    @Test
    public void testToString() {
        assertEquals("B", bishop.toString());
    }

    @Test
    public void testCalculateLegalMoves() {
        bishop.calculateLegalMoves();
        ArrayList<Move> allLegal = bishop.getAllLegalMoves();
        for (Move move : allLegal) {
            System.out.println(move.getDestination());
        }
    }
}