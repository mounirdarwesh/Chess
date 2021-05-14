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
public abstract class View {


    /**
     * The connected game
     */
    protected Game game;

    /**
     * The CLIController
     */
    protected Controller controller;

    /**
     * The constructor of the abstract View class.
     */
    public View() {

    }

    /**
     * @return the game
     */
    public Game getGame() {
        return game;
    }

    /**
     * @param game the game to set
     */
    public void setGame(Game game) {
        this.game = game;
        game.addObserver(this);
    }

    /**
     * @param game The game to observe
     */
    public void modelChanged(Game game) {
        this.game = game;
        System.out.println(game.getBoard());
    }

    /**
     * Assign controller
     *
     * @param cliController
     */
    public abstract void assignController(CliController cliController);

    /**
     * read input from the player
     */
    public abstract void readInputFromPlayer();

    /**
     * notify user
     *
     * @param status
     * @param player
     */
    public abstract void notifyUser(Attributes.GameStatus status, Player player);
}
