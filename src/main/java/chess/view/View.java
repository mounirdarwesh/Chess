package chess.view;

import chess.Attributes;
import chess.controller.CliController;
import chess.controller.Controller;
import chess.model.Game;
import chess.model.Player;

/**
 * The View Class, where the user interacts
 *
 * @author Gr.45
 */
public interface View {
    /**
     * @return the game
     */
    Game getGame();

    /**
     * @param game the game to set
     */
    void setGame(Game game);

    /**
     * @param game The game to observe
     */
    void modelChanged(Game game);

    /**
     * read input from the player
     */
    void readInputFromHuman();

    /**
     * read input from the computer
     *
     * @param input
     */
    boolean readInputFromComputer(String input);

    /**
     * notify user
     *
     * @param status
     * @param player
     */
    void notifyUser(Attributes.GameStatus status, Player player);
}
