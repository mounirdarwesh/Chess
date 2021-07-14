package chess.model.game;

import chess.controller.CliController;
import chess.controller.Move;
import chess.model.player.AI;
import chess.util.BoardMapper;

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

        if (chessClock != null) {
            chessClock.start();
        }

        while (!FINISHED) {
            // Process the input from the player
            cliController.processInputFromPlayer();

            if (allowedMove != null) {

                //Add the allowed move to the list of allowed moves
                allListOfMoves.add(allowedMove);

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
            if (cliController.getGameSettings()[4].equals("1")
                    && currentPlayer instanceof AI) {
                Move evaluatedMove = ((AI) currentPlayer).evaluate();
                int from = BoardMapper.mapChessNotationToPosition(
                        evaluatedMove.toString().substring(0, 2)
                );
                int to = BoardMapper.mapChessNotationToPosition(
                        evaluatedMove.toString().substring(3, 5)
                );
                processMoveFromPlayer(board.getPiece(from), to);
            }
        }
    }

    @Override
    public void updateClock() {}
}
