package chess.util;

import chess.Attributes;
import chess.model.game.Game;
import chess.model.game.GuiGame;
import chess.model.game.LANGame;
import javafx.application.Platform;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Chess Clock
 * @author Gr.45
 */
public class ChessClock {

    /**
     * the Connected Controller
     */
    private Game game;

    /**
     * timer of the game for each player
     */
    private Timer timer = new Timer();

    /**
     * constructor of the class
     * @param game controller
     */
    public ChessClock(Game game) {
        this.game = game;
    }

    /**
     * task that runs the define timer for each player
     * when to start the timer and when to stop it
     */
    TimerTask task = new TimerTask() {
        @Override
        public void run() {
            long playerRemainingTime = game.getCurrentPlayer().getTimeLeft();
            if(playerRemainingTime != 0) {
                game.getCurrentPlayer().setTimeLeft(
                        playerRemainingTime - 1000
                );
                game.updateClock();
            }
            else {
                timer.cancel();
                game.getController().notifyView(Attributes.GameStatus.TIME_OUT, game.getCurrentPlayer());
                if(game instanceof LANGame || game instanceof GuiGame) {
                    Platform.runLater(() -> {
                        game.notifyObservers();
                        game.getGameTask().cancel();
                    });
                } else {
                    game.setFINISHED(true);
                    // close the program when the game ends
                    System.exit(1);
                }
            }
        }
    };

    /**
     * start method which has a 1 second delay and will updated every 1 second
     */
    public void start() {
        timer.scheduleAtFixedRate(task, 1000, 1000);
    }
}
