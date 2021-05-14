package chess.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test of the class MapBoard
 *
 * @author Gruppe 45
 */
public class MapBoardTest {

    /**
     * Test map board
     */
    @Test
    public void map() {
        MapBoard mapBoard = new MapBoard();
        assertEquals(0, mapBoard.map("a1"));
        assertEquals(10, mapBoard.map("c2"));
        assertEquals(23, mapBoard.map("h3"));
        assertEquals(-1, mapBoard.map("k5"));
    }
}