package chess.view.guiView;

import chess.Attributes;
import chess.controller.GuiController;
import chess.model.Game;
import chess.util.Observer;
import chess.view.View;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Starting point of the JavaFX GUI
 */
public class Gui extends Application implements View, Observer {

    /**
     * The game that is connected to the GUI
     */
    protected Game game;

    /**
     * The start menu of the GUI
     */
    protected StartMenuView mainMenu;

    /**
     * The game view
     */
    protected GameView gameView;

    /**
     * The stage of the GUI
     */
    protected Stage primaryStage;

    /**
     * The scene
     */
    protected Scene scene;



    /**
     * The controller
     */
    public GuiController guiController;

    /**
     * This method is called by the Application to start the GUI.
     * @param primaryStage The initial root stage of the application.
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;

        // Creating the controller for the GUI
        this.guiController = new GuiController(this);

        //Creating basic interface
        ConfigureBasics(primaryStage);

        //Creating the start menu
        mainMenu = new StartMenuView(this);
        scene = new Scene(mainMenu.asParent(), 650, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     *
     * @param primaryStage
     */
    private void ConfigureBasics(Stage primaryStage) throws IOException {
        //Disable resizability
        primaryStage.setResizable(false);

        // Setting the title of the program
        primaryStage.setTitle("Chess");

        // Setting the icon for the program
        InputStream icon = Files.newInputStream(Paths.get("src/main/res/icon.png"));
        Image icImg = new Image(icon);
        icon.close();
        primaryStage.getIcons().add(icImg);
    }

    /**
     * The entry point of the GUI application.
     * @param args The command line arguments passed to the application
     */
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void setGame(Game game) {
        this.game = game;
        game.addObserver(this);
    }

    @Override
    public void update() {
        gameView.getBoard().drawBoard();
        primaryStage.show();
    }

    /**
     * Creating a game view for the user
     */
    public void createGameView() {
        gameView = new GameView(this);
        primaryStage.setScene(new Scene(gameView, 950, 700));
    }

    /**
     * Getter for the start screen menu
     * @return
     */
    public StartMenuView getMainMenu() {
        return mainMenu;
    }

    /**
     * Getter for the game view
     * @return
     */
    public GameView getGameView() {
        return gameView;
    }

    /**
     *
     * @param scene
     */
    public void setScene(Scene scene) {
        this.scene = scene;
        primaryStage.setScene(scene);
    }

    /**
     *
     * @return
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }
}
