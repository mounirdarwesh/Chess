package chess.view.gui;

import chess.controller.guicontroller.GameViewController;
import chess.controller.guicontroller.StartMenuController;
import chess.util.Observer;
import chess.view.View;
import chess.view.gui.gameview.GameView;
import chess.view.gui.startmenuview.StartMenuView;
import chess.model.game.Game;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * Starting point of the JavaFX GUI
 * @author Gr.45
 */
public class Gui extends Application implements View, Observer {

    /**
     * The primary stage of the game
     */
    protected Stage primaryStage;

    /**
     * The scene of the current stage
     */
    protected Scene scene;

    /**
     * The game that is connected to the GUI
     */
    public Game game;

    /**
     * The Width of the window
     */
    protected static final int WIDTH = 900;
    /**
     * The Height of the window
     */
    protected static final int HEIGHT = 750;

    /**
     * start menu
     */
    protected StartMenuView startMenu;

    /**
     * view of the game
     */
    protected GameView gameView;



    /**
     * the controller of the game view
     */
    protected GameViewController gameViewController;


    /**
     * This method is called by the Application to start the GUI.
     * @param primaryStage The initial root stage of the application.
     */
    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        // Configuring the basics of the window
        primaryStage.setResizable(false);
        primaryStage.setTitle("Chess (BETA)");
        primaryStage.setMinHeight(HEIGHT);
        primaryStage.setMinWidth(WIDTH);
        primaryStage.centerOnScreen();
        primaryStage.setOnCloseRequest(e -> System.exit(1));

        try {
            InputStream icon = Files.newInputStream(Paths.get("src/main/res/icon.png"));
            Image icImg = new Image(icon);
            icon.close();
            primaryStage.getIcons().add(icImg);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Creating the scenes
        createAllScenes();

        // Setting the start menu Scene to the window
        primaryStage.setScene(startMenu.asScene());

        // Show the window
        primaryStage.show();
    }

    /**
     * create all the scene of the gui
     */
    private void createAllScenes() {
        startMenu = new StartMenuView(this);
        //and create its controller
        new StartMenuController(startMenu);
        // Creating a Game View
        gameView = new GameView(this);
        // then create the game and pass it the controller
        gameViewController = new GameViewController(gameView);
    }

    /**
     * Creating a button for the GUI
     * @param title, The title of the button
     * @param vbox, The panel that holds the button
     * @return A button
     */
    public Button createButton(String title, VBox vbox) {
        Button btn = new Button(title);
        btn.setMinSize(70,18);
        VBox.setMargin(btn, new Insets(0, 0, 0, 0));
        vbox.getChildren().add(btn);
        return btn;
    }

    /**
     * Creating a title for the GUI
     * @param title, The title of the button
     * @param size, The size of the text
     * @param vbox, The panel that holds the button
     */
    public void createTitleText(String title, int size, VBox vbox) {
        Text text = new Text(title);
        text.setFont(Font.font("Arial", FontWeight.BOLD, size));
        text.setFill(Color.WHITE);
        vbox.getChildren().add(text);
    }

    /**
     * Creating a ComboBox for the start menu scene
     * @param title The title of the ComboBox
     * @param choices The available choices
     * @param vbox The carrying panel
     * @return ComboBox
     */
    public ComboBox<String> createComboBox(String title, List<String> choices, VBox vbox) {
        ComboBox<String> combo = new ComboBox<>();
        combo.setPromptText(title);
        for (String choice : choices) {
            combo.getItems().add(choice);
        }
        combo.setMinSize(70,18);
        VBox.setMargin(combo, new Insets(0, 0, 0, 0));
        vbox.getChildren().add(combo);
        return combo;
    }

    /**
     * Setting the style for the panel
     * @param panel The panel
     */
    public void setPanelStyle(VBox panel) {
        panel.setMinSize(170, 300);
        panel.setAlignment(Pos.CENTER);
        panel.setStyle("-fx-padding: 30 10 30 10;" +
                "-fx-spacing: 20;" +
                "-fx-border-width: 2;" +
                "-fx-border-insets: 5;" +
                "-fx-border-radius: 5;" +
                "-fx-border-color: white;");
    }

    /**
     * The entry point of the GUI application.
     * @param args The command line arguments passed to the application
     */
    public static void main(String[] args) {

        launch(args);
    }

    /**
     * getter of the primaryStage
     * @return Stage
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }

    /**
     * Set the stage to the new View instance
     * @param previousScene the previous scene to get the stage from
     */
    public void setStage(Scene previousScene) {
        this.primaryStage = (Stage) previousScene.getWindow();
    }

    /**
     * getter of the startMenu
     * @return StartMenuView
     */
    public StartMenuView getStartMenu() {
        return startMenu;
    }

    /**
     * setter of the startMenu
     * @param startMenu startMenu
     */
    public void setStartMenu(StartMenuView startMenu) {
        this.startMenu = startMenu;
    }

    /**
     * getter for the game view
     * @return the game view
     */
    public GameView getGameView() {
        return gameView;
    }

    /**
     * setter of the gameview
     * @param gameView the Game View
     */
    public void setGameView(GameView gameView) {
        this.gameView = gameView;
    }

    /**
     * Getter for the game
     * @return game
     */
    public Game getGame() {
        return game;
    }

    /**
     * Setter for the game
     * @param game The current game
     */
    public void setGame(Game game) {
        // set the game as model
        this.game = game;
        game.addObserver(this);
    }

    /**
     * getter of the gameViewController
     * @return GameViewController
     */
    public GameViewController getGameViewController() {
        return gameViewController;
    }

    @Override
    public void update() {
        gameView.updateState();
        primaryStage.setScene(gameView.asScene());
        primaryStage.centerOnScreen();
    }
}
