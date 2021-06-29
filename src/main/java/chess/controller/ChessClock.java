package chess.controller;

import chess.Attributes;
import chess.model.Game;
import java.util.Timer;
import java.util.TimerTask;

/**
 * The Class for Chess Clock of the game
 */
public class ChessClock {
    Controller controller;
    long leftTimeWhite;
    long leftTimeBlack;

    /**
     * constructor of the class
     * @param controller
     * @param time
     */
    public ChessClock(Controller controller, long time) {
        this.controller = controller;
        this.leftTimeWhite = time * 60000;
        this.leftTimeBlack = time * 60000;
    }

    /**
     * timer of the game for each player
     */
    Timer timer = new Timer();
    /**
     * task that runs the definiert timer for each player
     * when to start the timer and when to stop it
     */
    TimerTask task = new TimerTask() {
        public void run() {
            if (Game.getCurrentPlayer().getColor() == Attributes.Color.WHITE) {
                if (leftTimeWhite != 0) {
                    leftTimeWhite -= 1000;
                    if (controller instanceof GuiController) {
                        controller.showTime("White player time left: " + leftTimeWhite / 1000);
                    }
                } else {
                    controller.showTime("Player White has lost");
                    Game.setFINISHED(true);
                    timer.cancel();
                }
            } else {
                if (leftTimeBlack != 0) {
                    leftTimeBlack -= 1000;
                    if (controller instanceof GuiController) {
                        controller.showTime("Black player time left: " + leftTimeBlack / 1000);
                    }
                } else {
                    controller.showTime("Player Black has lost");
                    Game.setFINISHED(true);
                    timer.cancel();
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

    public void cancel(){
        timer.cancel();
    }

    /**
     * Getter for the lefte Time foreach Player.
     * @param color Player´s color
     * @return the reset Time.
     */
    public String getLeftTime(Attributes.Color color){
        if(color == Attributes.Color.WHITE)
            return "White player time left: " + leftTimeWhite / 1000;
        else return "Black player time left: " + leftTimeBlack / 1000;
    }
}
