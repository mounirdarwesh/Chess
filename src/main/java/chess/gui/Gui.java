package chess.gui;

import chess.Attributes;
import chess.controller.GuiController;
import chess.model.Game;
import chess.model.Player;
import chess.view.View;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Starting point of the JavaFX GUI
 */
public class Gui extends Application implements View {

    /**
     * The connected game
     */
    protected Game game;

    /**
     * Gui Controller
     */
    GuiController guiController;

    /**
     * GUI Interface Constructor
     */
    public Gui() {
        guiController = new GuiController(this);
    }

    /**
     * This method is called by the Application to start the GUI.
     *
     * @param primaryStage The initial root stage of the application.
     */
    @Override
    public void start(Stage primaryStage) {
        new StartMenuView(primaryStage, guiController);
    }


    /**
     * The entry point of the GUI application.
     *
     * @param args The command line arguments passed to the application
     */
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public Game getGame() {
        return game;
    }

    @Override
    public void setGame(Game game) {
    }

    @Override
    public void modelChanged(Game game) {
    }

    public void assignController(GuiController Controller) {
        this.guiController = Controller;
    }

    @Override
    public void readInputFromHuman() {
    }

    @Override
    public boolean readInputFromComputer(String input) {
        return false;
    }

    @Override
    public void notifyUser(Attributes.GameStatus status, Player player) {
    }
}
