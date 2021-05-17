package chess.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test of the class MapBoard
 *
 * @author Gruppe 45
 */
public class MapBoardTest {
    // Instance of MapBoard.
    MapBoard mapBoard = new MapBoard();

    /**
     * Test first column of the map board
     */
    @Test
    public void map1Column() {
        Integer[] mapA = new Integer[]{mapBoard.map("a1"), mapBoard.map("a2"),
                mapBoard.map("a3"), mapBoard.map("a4"),
                mapBoard.map("a5"), mapBoard.map("a6"),
                mapBoard.map("a7"), mapBoard.map("a8")};
        Integer[] listA = new Integer[]{0, 8, 16, 24, 32, 40, 48, 56};
        assertArrayEquals(listA, mapA);
    }

    /**
     * Test second column of the map board
     */
    @Test
    public void map2Column() {
        Integer[] mapB = new Integer[]{mapBoard.map("b1"), mapBoard.map("b2"),
                mapBoard.map("b3"), mapBoard.map("b4"),
                mapBoard.map("b5"), mapBoard.map("b6"),
                mapBoard.map("b7"), mapBoard.map("b8")};
        Integer[] listB = new Integer[]{1, 9, 17, 25, 33, 41, 49, 57};
        assertArrayEquals(listB, mapB);
    }

    /**
     * Test third column of the map board
     */
    @Test
    public void map3Column() {
        Integer[] mapC = new Integer[]{mapBoard.map("c1"), mapBoard.map("c2"),
                mapBoard.map("c3"), mapBoard.map("c4"),
                mapBoard.map("c5"), mapBoard.map("c6"),
                mapBoard.map("c7"), mapBoard.map("c8")};
        Integer[] listC = new Integer[]{2, 10, 18, 26, 34, 42, 50, 58};
        assertArrayEquals(listC, mapC);
    }

    /**
     * Test forth column of the map board
     */
    @Test
    public void map4Column() {
        Integer[] mapD = new Integer[]{mapBoard.map("d1"), mapBoard.map("d2"),
                mapBoard.map("d3"), mapBoard.map("d4"),
                mapBoard.map("d5"), mapBoard.map("d6"),
                mapBoard.map("d7"), mapBoard.map("d8")};
        Integer[] listD = new Integer[]{3, 11, 19, 27, 35, 43, 51, 59};
        assertArrayEquals(listD, mapD);
    }

    /**
     * Test fifth column of the map board
     */
    @Test
    public void map5Column() {
        Integer[] mapE = new Integer[]{mapBoard.map("e1"), mapBoard.map("e2"),
                mapBoard.map("e3"), mapBoard.map("e4"),
                mapBoard.map("e5"), mapBoard.map("e6"),
                mapBoard.map("e7"), mapBoard.map("e8")};
        Integer[] listE = new Integer[]{4, 12, 20, 28, 36, 44, 52, 60};
        assertArrayEquals(listE, mapE);
    }

    /**
     * Test sixth column of the map board
     */
    @Test
    public void map6Column() {
        Integer[] mapF = new Integer[]{mapBoard.map("f1"), mapBoard.map("f2"),
                mapBoard.map("f3"), mapBoard.map("f4"),
                mapBoard.map("f5"), mapBoard.map("f6"),
                mapBoard.map("f7"), mapBoard.map("f8")};
        Integer[] listF = new Integer[]{5, 13, 21, 29, 37, 45, 53, 61};
        assertArrayEquals(listF, mapF);
    }

    /**
     * Test seventh column of the map board
     */
    @Test
    public void map7Column() {
        Integer[] mapG = new Integer[]{mapBoard.map("g1"), mapBoard.map("g2"),
                mapBoard.map("g3"), mapBoard.map("g4"),
                mapBoard.map("g5"), mapBoard.map("g6"),
                mapBoard.map("g7"), mapBoard.map("g8")};
        Integer[] listG = new Integer[]{6, 14, 22, 30, 38, 46, 54, 62};
        assertArrayEquals(listG, mapG);
    }

    /**
     * Test eight column of the map board
     */
    @Test
    public void map8Column() {
        Integer[] mapH = new Integer[]{mapBoard.map("h1"), mapBoard.map("h2"),
                mapBoard.map("h3"), mapBoard.map("h4"),
                mapBoard.map("h5"), mapBoard.map("h6"),
                mapBoard.map("h7"), mapBoard.map("h8")};
        Integer[] listH = new Integer[]{7, 15, 23, 31, 39, 47, 55, 63};
        assertArrayEquals(listH, mapH);
    }
}