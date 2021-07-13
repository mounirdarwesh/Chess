package chess.model;

import chess.Attributes;
import chess.model.pieces.Piece;
import chess.model.pieces.Queen;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test of the class Board
 *
 * @author Gruppe45
 */
public class BoardTest {
    Board board = new Board();
    Piece queen = new Queen(18, Attributes.Color.WHITE, board);

    /**
     * Test if the board shows the right Alphabet
     */
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
                "  a b c d e f g h", board.toString());
    }

    /**
     * Test get a position of a piece on board
     */
    @Test
    public void getPiece() {
        board.setPiece(queen, queen.getPosition());
        assertEquals(queen, board.getPiece(18));
    }

    /**
     * Test set a piece on board
     */
    @Test
    public void setPiece() {
        board.setPiece(queen, queen.getPosition());
        assertEquals("8 r n b q k b n r\n" +
                "7 p p p p p p p p\n" +
                "6                \n" +
                "5                \n" +
                "4                \n" +
                "3     Q          \n" +
                "2 P P P P P P P P\n" +
                "1 R N B Q K B N R\n" +
                "  a b c d e f g h", board.toString());
    }
}