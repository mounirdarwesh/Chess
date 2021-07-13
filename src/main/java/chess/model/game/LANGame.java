package chess.model.game;

import chess.controller.LANGameController;
import chess.controller.Move;
import chess.model.player.Player;
import chess.pgn.FenUtilities;
import chess.util.MoveMapper;
import chess.view.gui.gameView.GameView;
import javafx.application.Platform;
import javafx.concurrent.Task;

/**
 * class of the LAN game
 * @author Gr.45
 */
public class LANGame extends Game {

    /**
     * controller of the lan game
     */
    private LANGameController lanGameController;

    /**
     * The current player of the LAN game
     */
    private Player currentLANPlayer;



    /**
     * The constructor creates a new game instance with the given Controller
     * @param lanGameController The controller to manage this instance
     */
    public LANGame(LANGameController lanGameController) {
        super(lanGameController);
        this.lanGameController = lanGameController;
        initPlayers();
    }


    @Override
    public void run() {
        currentLANPlayer = whitePlayer;
        notifyObservers();
        if(chessClock != null) {
            chessClock.start();
        }
        gameTask = new Task() {
            @Override
            protected Void call() throws Exception {
                while (!FINISHED) {
                    Thread.sleep(50);
                    if (currentPlayer != currentLANPlayer) {
                        lanGameController.receiveData();
                        playerReceivingMove();
                        playerReceivingUndoMove();
                        playerReceivingRedoMove();
                    }
                    else {
                        playerSendingMove();
                        playerSendingUndoMove();
                        playerSendingRedoMove();
                    }
                }
                return null;
            }
        };
        new Thread(gameTask).start();
    }

    /**
     * Receive a Move from the other Participant
     */
    private void playerReceivingMove() {
        if(lanGameController.getLanMove() != null) {
            allowedMove = MoveMapper.map(lanGameController.getLanMove(), this);
            currentLANPlayer.makeMove(allowedMove);
            // Save the move
            allListOfMoves.add(allowedMove);
            //resitting
            lanGameController.setLanMove(null);
            // Switch the turns
            currentLANPlayer = currentPlayer;
            // Check the status of the game
            checkGameStatus();
            Platform.runLater(() -> {
                notifyObservers();
                allowedMove = null;
            });
        }
    }

    /**
     *  Receive a Undo from the other Participant
     */
    private void playerReceivingUndoMove() {
        if(lanGameController.hasLanUndo()) {
            lanGameController.undoMove(allListOfMoves.size()-2);
            lanGameController.setLanUndo(false);
            Platform.runLater(() -> {
                notifyObservers();
            });
        }

    }

    /**
     *  Receive a Redo from the other Participant
     */
    private void playerReceivingRedoMove() {
        if(lanGameController.hasLanRedo()) {
            lanGameController.redoMove(2);
            lanGameController.setLanRedo(false);
            Platform.runLater(() -> {
                notifyObservers();
            });
        }
    }

    /**
     *  Send a Move from the other Participant
     */
    private void playerSendingMove() {
        if (allowedMove != null) {
            currentPlayer.makeMove(allowedMove);
            lanGameController.sendData("move " + allowedMove.toString());
            // Save the move
            allListOfMoves.add(allowedMove);
            // Switch the turns
            currentLANPlayer = getOpponent();
            // Check the status of the game
            checkGameStatus();
            Platform.runLater(() -> {
                notifyObservers();
                // Reset the allowed move
                allowedMove = null;
            });
        }
    }

    /**
     *  send a Undo from the other Participant
     */
    private void playerSendingUndoMove() {
        if(currentPlayer.hasPlayerUndidAMove()) {
            lanGameController.sendData("undo");
            currentPlayer.setHasPlayerUndidAMove(false);
            Platform.runLater(() -> {
                notifyObservers();
            });
        }
    }

    /**
     *  Send a Redo from the other Participant
     */
    private void playerSendingRedoMove() {
        if(currentPlayer.hasPlayerRedidAMove()) {
            lanGameController.sendData("redo");
            currentPlayer.setHasPlayerRedidAMove(false);
            Platform.runLater(() -> {
                notifyObservers();
            });
        }
    }
    @Override
    public void updateClock() {
        Platform.runLater( () -> {
            lanGameController.getGameView().showClock();
        });
    }

    /**
     * Getter for the Current Player
     * @return current Player
     */
    public Player getCurrentLANPlayer() {
        return currentLANPlayer;
    }
}
