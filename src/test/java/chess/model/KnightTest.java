package chess.model;

import chess.Attributes;
import chess.controller.Move;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class KnightTest {
    Board board = new Board();
    Piece knight = new Knight(44, Attributes.Color.WHITE, board);


    @Test
    void testToString() {
        assertEquals("N", knight.toString());
    }

    @Test
    void calculateLegalMoves() {
        knight.calculateLegalMoves();
        ArrayList<Move> allLegal = knight.getAllLegalMoves();
        for (Move move : allLegal) {
            System.out.println(move.getDestination() + " " + move.toString());
        }
    }
}