package chess.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MapBoardTest {

    @Test
    public void map() {
        MapBoard mapBoard = new MapBoard();
        assertEquals(0,mapBoard.map("a1"));
        assertEquals(10,mapBoard.map("c2"));
        assertEquals(23,mapBoard.map("h3"));
        assertEquals(-1,mapBoard.map("k5"));
    }
}