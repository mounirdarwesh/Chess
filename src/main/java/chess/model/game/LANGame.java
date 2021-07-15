package chess.model.game;

import chess.controller.LANGameController;
import chess.controller.Move;
import chess.model.player.Player;
import chess.util.MoveMapper;
import javafx.application.Platform;

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
     * Runnable of the updater
     */
    private Runnable updater;

    /**
     *
     */
    private Thread lanGameThread;


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

        lanGameThread = new Thread(() -> {
            updater = () -> {
                notifyObservers();
                allowedMove = null;
            };
            while (!FINISHED) {
                try {
                    Thread.sleep(50);
                    if (currentPlayer != currentLANPlayer) {
                        lanGameController.receiveData();
                        playerReceivingMove();
                    } else {
                        playerSendingMove();
                    }
                    sendingUndoMoves();
                    receivingUndoMoves();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        // don't let thread prevent JVM shutdown
        lanGameThread.setDaemon(true);
        lanGameThread.start();
    }

    private void receivingUndoMoves() {
        if(lanGameController.hasLanUndo()) {
            lanGameController.undoLANMove(lanGameController.getLanUndoIndex());
            lanGameController.setLanUndo(false);
            // Switch the turns
            currentLANPlayer = lanGameController.getUndidMovePlayer();
            lanGameController.setUndidMovePlayer(null);
            Platform.runLater(updater);
        }
    }

    private void sendingUndoMoves() {
        if(lanGameController.getUndidMovePlayer() != null) {
            lanGameController.sendData("undo" + (
                    (lanGameController.getLanUndoIndex() != 1) ?
                    " " + lanGameController.getLanUndoIndex() : ""));
            currentLANPlayer = lanGameController.getUndidMovePlayer();
            lanGameController.setUndidMovePlayer(null);
            Platform.runLater(updater);
        }
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
            // Update the game
            Platform.runLater(updater);
        }
    }

    /**
     *  Send a Move from the other Participant
     */
    private void playerSendingMove() {
        if (allowedMove != null) {
            currentPlayer.makeMove(allowedMove);
            if (allowedMove instanceof Move.PromotionMove) {
                lanGameController.sendData("move "
                        + allowedMove.toString() + charToPromote);
            }else {
                lanGameController.sendData("move " + allowedMove.toString());
            }
            // Save the move
            allListOfMoves.add(allowedMove);
            // Switch the turns
            currentLANPlayer = getOpponent();
            // Check the status of the game
            checkGameStatus();
            // Update the game
            Platform.runLater(updater);
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
