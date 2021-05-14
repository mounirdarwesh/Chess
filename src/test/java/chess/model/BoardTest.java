package chess.model;

import chess.Attributes;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BoardTest {
    Board board = new Board();
    Piece queen = new Queen(18, Attributes.Color.WHITE,board);
    @Test
    public void testToString() {
        assertEquals("8 r n b q k b n r\n" +
                "7 p p p p p p p p\n" +
                "6                \n" +
                "5                \n" +
                "4                \n" +
                "3                \n" +
                "2 P P P P P P P P\n" +
                "1 R N B Q K B N R\n" +
                "  a b c d e f g h",board.toString());
    }

    @Test
    public void getPiece() {
        board.setPiece(queen);
        assertEquals(queen,board.getPiece(18));
    }

    @Test
    public void setPiece() {
        board.setPiece(queen);
        assertEquals("8 r n b q k b n r\n" +
                "7 p p p p p p p p\n" +
                "6                \n" +
                "5                \n" +
                "4                \n" +
                "3     Q          \n" +
                "2 P P P P P P P P\n" +
                "1 R N B Q K B N R\n" +
                "  a b c d e f g h",board.toString());
    }
}