package chess;

/**
 * @author Gr.45
 * The Attributes of the game
 */
public class Attributes {

    /**
     * The available colors of the game
     */
    public enum Color {
        WHITE() {
            public boolean isWhite() {
                return true;
            }

            public boolean isBlack() {
                return false;
            }

            @Override
            public int getDirection() {
                return 1;
            }
        },

        BLACK() {
            public boolean isWhite() {
                return false;
            }

            public boolean isBlack() {
                return true;
            }

            @Override
            public int getDirection() {
                return -1;
            }
        };

        public abstract boolean isWhite();

        public abstract boolean isBlack();

        public abstract int getDirection();
    }

    /**
     * The Game status
     */
    public enum GameStatus {

        ENDED, KING_IN_CHECK

    }
}


