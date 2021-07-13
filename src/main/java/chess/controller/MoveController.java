package chess.controller;

import chess.model.game.Game;

/**
 * controller of the move class
 * @author Gr.45
 */
public class MoveController {

    /**
     * The Game
     */
    protected static Game game;


    /**
     * getter of the game
     * @return Game
     */
    public static Game getGame() {
        return game;
    }

    /**
     * setter of the game
     * @param game game
     */
    public void setGame(Game game) {
        MoveController.game = game;
    }

}
