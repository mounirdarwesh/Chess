package chess.model;

import chess.Attributes;
import chess.controller.Move;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class KingTest {
    Board board = new Board();
    Piece king = new King(44, Attributes.Color.WHITE, board);

    @Test
    void testToString() {
        assertEquals("K", king.toString());
    }

    @Test
    void calculateLegalMoves() {
        king.calculateLegalMoves();
        ArrayList<Move> allLegal = king.getAllLegalMoves();
        for (Move move : allLegal) {
            System.out.println(move.getDestination() + " "+move.toString());
        }
    }
}