package chess.controller;

import chess.Attributes;
import chess.model.Game;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * The Class for Chess Clock of the game
 */
public class ChessClock {
    /**
     * the Connected Controller
     */
    Controller controller;
    /**
     * left time for White Player
     */
    long leftTimeWhite;
    /**
     * left time for Black Player
     */
    long leftTimeBlack;
    /**
     * timer of the game for each player
     */
    Timer timer = new Timer();
    /**
     * task that runs the define timer for each player
     * when to start the timer and when to stop it
     */
    TimerTask task = new TimerTask() {
        @Override
        public void run() {
            if (Game.getCurrentPlayer().getColor() == Attributes.Color.WHITE) {
                if (leftTimeWhite != 0) {
                    leftTimeWhite -= 1000;
                    if (controller instanceof GuiController) {
                        controller.showTime(convertTime(leftTimeWhite));
                    }
                } else {
                    controller.showTime("Time is up, you have Lost");
                    Game.setFINISHED(true);
                    timer.cancel();
                }
            } else {
                if (leftTimeBlack != 0) {
                    leftTimeBlack -= 1000;
                    if (controller instanceof GuiController) {
                        controller.showTime(convertTime(leftTimeBlack));
                    }
                } else {
                    controller.showTime("Time is up, you have Lost");
                    Game.setFINISHED(true);
                    timer.cancel();
                }
            }
        }
    };

    /**
     * constructor of the class
     *
     * @param controller controller
     * @param time       the Duration of the Game
     */
    public ChessClock(Controller controller, long time) {
        this.controller = controller;
        this.leftTimeWhite = time * 60000;
        this.leftTimeBlack = time * 60000;
    }

    /**
     * start method which has a 1 second delay and will updated every 1 second
     */
    public void start() {
        timer.scheduleAtFixedRate(task, 1000, 1000);
    }

    /**
     * cancel the Timer if needed
     */
    public void cancel() {
        timer.cancel();
    }

    /**
     * Getter for the left Time foreach Player.
     *
     * @param color Player´s color
     * @return the reset Time.
     */
    public String getLeftTime(Attributes.Color color) {
        if (color == Attributes.Color.WHITE) {
            return "White player time left: " + convertTime(leftTimeWhite);
        } else {
            return "Black player time left: " + convertTime(leftTimeBlack);
        }
    }

    /**
     * convert the millisecond to propre Countdown
     *
     * @param leftTime in millisecond
     * @return in minutes Format
     */
    public String convertTime(long leftTime) {
        DateFormat simple = new SimpleDateFormat("mm:ss");

        Date result = new Date(leftTime);

        return simple.format(result);
    }
}
