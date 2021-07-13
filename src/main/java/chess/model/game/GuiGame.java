package chess.model.game;

import chess.controller.Move;
import chess.controller.guiController.GameViewController;
import chess.controller.guiController.GuiController;
import chess.model.player.AI;
import chess.pgn.FenUtilities;
import chess.util.BoardMapper;
import chess.view.gui.gameView.GameView;
import javafx.application.Platform;
import javafx.concurrent.Task;

/**
 * class of the gui game
 * @author Gr.45
 */
public class GuiGame extends Game {

    /**
     * controller of the view of the gui
     */
    private GameViewController gameViewController;

    /**
     * The constructor creates a new game instance with the given Controller
     * @param gameViewController The controller to manage this instance
     */
    public GuiGame(GameViewController gameViewController) {
        super(gameViewController);
        this.gameViewController = gameViewController;
        initPlayers();
    }

    @Override
    public void run() {
        currentPlayer = whitePlayer;
        notifyObservers();

        if(chessClock != null) {
            chessClock.start();
        }

        gameTask = new Task() {
            @Override
            protected Void call() throws InterruptedException {
                while (!FINISHED) {
                    Thread.sleep(100);
                    if (allowedMove != null) {
                        currentPlayer.makeMove(allowedMove);

                        // Save the move
                        allListOfMoves.add(allowedMove);
                        // Switch the turns
                        currentPlayer = getOpponent();

                        // Check the status of the game
                        checkGameStatus();

                        Platform.runLater(() -> {
                            notifyObservers();
                            // Reset the allowed move
                            allowedMove = null;
                        });
                    }
                    if(currentPlayer.hasPlayerRedidAMove()) {
                        currentPlayer.setHasPlayerRedidAMove(false);
                        currentPlayer = getOpponent();
                        // Check the status of the game
                        checkGameStatus();
                        Platform.runLater(() -> {
                            notifyObservers();
                        });
                    }
                    if(gameViewController.getGameSettings()[4].equals("1")
                            && currentPlayer instanceof AI) {
                        Move evaluatedMove = ((AI) currentPlayer).evaluate();
                        int from = BoardMapper.mapChessNotationToPosition(
                                evaluatedMove.toString().substring(0,2)
                        );
                        int to = BoardMapper.mapChessNotationToPosition(
                                evaluatedMove.toString().substring(3,5)
                        );
                        processMoveFromPlayer(board.getPiece(from), to);
                    }
                }
                return null;
            }
        };
        new Thread(gameTask).start();
    }

    @Override
    public void updateClock() {
        Platform.runLater(() ->
            gameViewController.getGameView().showClock());
    }
}
