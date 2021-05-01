package chess.model;

import chess.Attributes;
import chess.controller.Move;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class QueenTest {

    Board board = new Board();
    Piece queen = new Queen(5, Attributes.Color.WHITE, board);

    @Test
    public void testCalculateLegalMoves() {
        queen.calculateLegalMoves();
        ArrayList<Move> allLegal = queen.getAllLegalMoves();
        for (Move move : allLegal) {
            System.out.println(move.getDestination());
        }
    }

    @Test
    public void testToString() {
        assertEquals("Q", queen.toString());
    }

}