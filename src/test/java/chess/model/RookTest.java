package chess.model;

import chess.Attributes;
import chess.controller.Move;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RookTest {

    Board board = new Board();
    Piece rook = new Rook(16, Attributes.Color.BLACK, board);

    @Test
    public void testToString() {
        assertEquals("r", rook.toString());
    }

    @Test
    public void testCalculateLegalMoves() {
        rook.calculateLegalMoves();
        ArrayList<Move> allLegal = rook.getAllLegalMoves();
        for (Move move : allLegal) {
            System.out.println(move.getDestination()+" "+move.toString());
        }
    }
}