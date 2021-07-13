package chess.view.gui.gameview;

import chess.Attributes;
import chess.model.game.Game;
import chess.model.game.GuiGame;
import chess.model.game.LANGame;
import chess.model.player.Player;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
/**
 * class for viewing notifications
 * @author Gr.45
 */
public class NotificationView {

    /**
     * box to view the notifications
     */
    private HBox notificationView;

    /**
     * the connected game
     */
    private Game game;

    /**
     * if game ended in win
     */
    private String endedInWin;

    /**
     *  if game ended in Draw
     */
    private String endedInDraw;

    /**
     * if King in check
     */
    private String kingInCheck;

    /**
     * if Time is Out
     */
    private String playerTimerOut;

    /**
     * Container for the Timer
     */
    HBox timeRemainingContainer = new HBox();

    /**
     * constructor of the class
     * @param game the current game
     */
    public NotificationView(Game game) {
        this.game = game;
        notificationView = new HBox();
        notificationView.setAlignment(Pos.CENTER);
        notificationView.setMinHeight(50);
    }

    /**
     * show the Notification on the View
     */
    public void showNotification(){
        if(!notificationView.getChildren().isEmpty()) notificationView.getChildren().clear();
        String player = game instanceof GuiGame ?
                game.getCurrentPlayer().toString()
                : ((LANGame) game).getCurrentLANPlayer().toString();
        Label notification = new Label(player + "'s turn to play");
        notification.setFont(new Font(30));
        if(endedInWin != null) {
            notification.setText(endedInWin);
            endedInWin = null;
        }
        if(endedInDraw != null) {
            notification.setText(endedInDraw);
            endedInDraw = null;
        }
        if(kingInCheck != null) {
            notification.setText(kingInCheck);
            kingInCheck = null;
        }
        if(playerTimerOut != null) {
            notification.setText(playerTimerOut);
            playerTimerOut = null;
        }
        notificationView.getChildren().add(notification);
    }

    /**
     * shoe the Remaining Time
     */
    public void showTimeRemaining() {
        HBox.setMargin(timeRemainingContainer, new Insets(0, 0, 0, 20));
        if(!timeRemainingContainer.getChildren().isEmpty()) {
            timeRemainingContainer.getChildren().clear();
        }
        if(notificationView.getChildren().contains(timeRemainingContainer)) {
            notificationView.getChildren().remove(timeRemainingContainer);
        }
        timeRemainingContainer.setAlignment(Pos.CENTER);
        Label duration = new Label();
        duration.setFont(new Font(30));
        String timeLeft = game.getController().convertTime(
                game.getCurrentPlayer().getTimeLeft()
        );
        duration.setText(timeLeft);
        timeRemainingContainer.getChildren().add(duration);
        notificationView.getChildren().add(timeRemainingContainer);
    }

    /**
     * horizontal box to view the notification
     * @return HBox
     */
    public HBox asNode() {
        return notificationView;
    }

    /**
     * Notify the Player on Interface
     * @param game the game
     * @param status the status
     * @param player the player
     */
    public void notifyPlayer(Game game, Attributes.GameStatus status, Player player) {
        switch (status) {
            case ENDED_IN_WIN:
                endedInWin = game instanceof GuiGame ?
                        player + " has won the game!" :
                        ((LANGame) game).getCurrentLANPlayer().toString() + " has won the game!";
                break;
            case KING_IN_CHECK:
                kingInCheck = game instanceof GuiGame ?
                        player + "'s king is in check." :
                        ((LANGame) game).getCurrentLANPlayer().toString() + "'s king is in check.";
                break;
            case ENDED_IN_DRAW:
                endedInDraw = "Game Ended in a draw.";
                break;
            case TIME_OUT:
                playerTimerOut = player + "'s time is out!!";
        }
    }
}
