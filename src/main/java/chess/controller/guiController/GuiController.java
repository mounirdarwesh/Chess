package chess.controller.guiController;

import chess.Attributes;
import chess.controller.Controller;
import chess.model.player.Player;
import chess.view.gui.Gui;
import chess.view.gui.gameView.GameView;
import chess.view.gui.gameView.NotificationView;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;


/**
 * The GUI Controller Class
 * @author Gr.45
 */
public class GuiController extends Controller implements EventHandler {

    /**
     * view for the controller
     */
    protected Gui view;
    /**
     * controller of the gui
     */
    public GuiController() {}

    /**
     * The constructor expects a view to construct itself.
     * @param view The view that is connected to this controller
     */
    public GuiController(Gui view) {
        this.view = view;
    }

    /**
     * A method to get the specified game Setting
     * @param index the index of the specified setting
     * @param str the new setting to replace
     */
    public void setGameSettings(int index, String str) {
        gameSettings[index] = str;
    }

    @Override
    public void notifyView(Attributes.GameStatus status, Player player) {}

    /**
     * Setting the scene to the window
     * @param oldScene the old scene
     * @param newScene the new scene
     */
    public void setScene(Scene oldScene, Scene newScene) {
        Stage stage = (Stage) oldScene.getWindow();
        stage.setScene(newScene);
        newScene.getWindow().centerOnScreen();
        newScene.getWindow().sizeToScene();
    }

    @Override
    public void handle(Event event) {}
}
