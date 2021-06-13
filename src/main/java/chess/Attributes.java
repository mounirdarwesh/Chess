package chess;

/**
 * @author Gr.45
 * The Attributes of the game
 */
public class Attributes {

    public static final int BOARD_SIZE = 64;

    /**
     * The available colors of the game
     */
    public enum Color {
        WHITE() {
            /**
             * Color of a piece
             * @return true if the piece is white
             */
            public boolean isWhite() {
                return true;
            }

            /**
             * Color of a piece
             * @return false if the piece is black
             */
            public boolean isBlack() {
                return false;
            }

            @Override
            public int getDirection() {
                return 1;
            }
        },

        BLACK() {
            /**
             * Color of a piece
             * @return false if the piece is white
             */
            public boolean isWhite() {
                return false;
            }

            /**
             * Color of a piece
             * @return true if the piece is black
             */
            public boolean isBlack() {
                return true;
            }

            @Override
            public int getDirection() {
                return -1;
            }
        };

        /**
         * Color of a piece: white
         */
        public abstract boolean isWhite();

        /**
         * Color of a piece: black
         */
        public abstract boolean isBlack();

        /**
         * get the direction of a piece
         */
        public abstract int getDirection();
    }

    /**
     * The Game status
     */
    public enum GameStatus {
        KING_IN_CHECK,
        ENDED_IN_WIN,
        ENDED_IN_DRAW
    }

    /**
     * The Game Mode
     */
    public enum GameMode {
        HUMAN,
        COMPUTER
    }
}


