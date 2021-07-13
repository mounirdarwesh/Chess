package chess.model.game;

import chess.controller.CliController;

/**
 * class of the console basierte game
 * @author Gr.45
 */
public class CliGame extends Game {

    /**
     * controller of console basierte game
     */
    private CliController cliController;

    /**
     * The constructor creates a new game instance with the given Controller
     * @param cliController The controller to manage this instance
     */
    public CliGame(CliController cliController) {
        super(cliController);
        this.cliController = cliController;
        initPlayers();
    }

    @Override
    public void run() {
        notifyObservers();
        currentPlayer = whitePlayer;

        while (!FINISHED) {
            // Process the input from the player
            cliController.processInputFromPlayer();

            if (allowedMove != null) {

                // Make the move
                currentPlayer.makeMove(allowedMove);

                // Switch the turns
                currentPlayer = getOpponent();

                // Check the status of the game
                checkGameStatus();

                // Reset the allowed move
                allowedMove = null;

                // And then notify the observer
                notifyObservers();
            }
        }

    }

    @Override
    public void updateClock() {}
}
