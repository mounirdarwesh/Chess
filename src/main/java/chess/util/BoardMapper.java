package chess.util;

import java.util.Map;

import static java.util.Map.entry;

/**
 * class that mapping the board
 * assign each tile with a relevant sign
 * @author Gr.45
 */
public class BoardMapper {

    public final static Map<String, Integer> mapper = Map.ofEntries(entry("a1", 0),
            entry("a2", 8),
            entry("a3", 16),
            entry("a4", 24),
            entry("a5", 32),
            entry("a6", 40),
            entry("a7", 48),
            entry("a8", 56),
            entry("b1", 1),
            entry("b2", 9),
            entry("b3", 17),
            entry("b4", 25),
            entry("b5", 33),
            entry("b6", 41),
            entry("b7", 49),
            entry("b8", 57),
            entry("c1", 2),
            entry("c2", 10),
            entry("c3", 18),
            entry("c4", 26),
            entry("c5", 34),
            entry("c6", 42),
            entry("c7", 50),
            entry("c8", 58),
            entry("d1", 3),
            entry("d2", 11),
            entry("d3", 19),
            entry("d4", 27),
            entry("d5", 35),
            entry("d6", 43),
            entry("d7", 51),
            entry("d8", 59),
            entry("e1", 4),
            entry("e2", 12),
            entry("e3", 20),
            entry("e4", 28),
            entry("e5", 36),
            entry("e6", 44),
            entry("e7", 52),
            entry("e8", 60),
            entry("f1", 5),
            entry("f2", 13),
            entry("f3", 21),
            entry("f4", 29),
            entry("f5", 37),
            entry("f6", 45),
            entry("f7", 53),
            entry("f8", 61),
            entry("g1", 6),
            entry("g2", 14),
            entry("g3", 22),
            entry("g4", 30),
            entry("g5", 38),
            entry("g6", 46),
            entry("g7", 54),
            entry("g8", 62),
            entry("h1", 7),
            entry("h2", 15),
            entry("h3", 23),
            entry("h4", 31),
            entry("h5", 39),
            entry("h6", 47),
            entry("h7", 55),
            entry("h8", 63)
    );

    /**
     * get for each Tile name an Index
     * @param val Tile name
     * @return the Index
     */
    public static int mapChessNotationToPosition(String val) {
        return mapper.get(val.toLowerCase());
    }

    /**
     * get for each Index a tile name
     * @param key the position
     * @return Tile name
     */
    public static String mapPositionToChessNotation(int key) {
        for (Map.Entry<String, Integer> entry: mapper.entrySet()) {
            if(entry.getValue().equals(key)) {
                return entry.getKey();
            }
        }
        return null;
    }
}
