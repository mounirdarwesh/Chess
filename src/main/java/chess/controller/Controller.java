package chess.controller;

import chess.Attributes;
import chess.model.Game;
import chess.model.Piece;
import chess.model.Player;
import chess.view.View;

import java.util.ArrayList;

/**
 * An abstract class of the controller
 *
 * @author Gr.45
 */
public abstract class Controller {

    /**
     * The game as model that is connected to this controller
     */
    protected Game game;

    /**
     * The view that is connected to this controller
     */
    protected View view;

    /**
     * The constructor expects a view to construct itself.
     *
     * @param view The view that is connected to this controller
     */
    public Controller(View view) {
        this.view = view;
    }

    /**
     * Processing the input from the player
     */
    public abstract void processInputFromPlayer();

    /**
     * Returning the beaten pieces of the player
     * @return beaten pieces of the player
     */
    public abstract ArrayList<Piece> getBeatenPieces();

    /**
     * Method to notify the user of the status of the game
     * @param status The status of the game
     * @param player The player
     */
    public abstract void notifyView(Attributes.GameStatus status, Player player);
}
