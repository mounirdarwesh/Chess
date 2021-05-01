package chess.model;

import chess.Attributes;
import chess.controller.Move;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BishopTest {

    Board board = new Board();
    Piece bishop = new Bishop(27, Attributes.Color.WHITE, board);
    @Test
    void testToString() {
        assertEquals("N", bishop.toString());
    }

    @Test
    void calculateLegalMoves() {
        bishop.calculateLegalMoves();
        ArrayList<Move> allLegal = bishop.getAllLegalMoves();
        for (Move move : allLegal) {
            System.out.println(move.getDestination());
        }
    }
}