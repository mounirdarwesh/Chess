package chess.model;

import chess.Attributes;
import chess.controller.Move;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class RookTest {

    Board board = new Board();
    Piece rook = new Rook(51, Attributes.Color.WHITE, board);

    @Test
    void testToString() {
    }

    @Test
    void calculateLegalMoves() {
        rook.calculateLegalMoves();
        ArrayList<Move> allLegal = rook.getAllLegalMoves();
        for (Move move : allLegal) {
            System.out.println(move.getDestination());
        }
    }
}