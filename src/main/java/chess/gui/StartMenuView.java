package chess.gui;

import chess.controller.GuiController;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * Main Screen Layout.
 * @author Gruppe 45
 */
public class StartMenuView extends GridPane {
    /**
     * layout that contains all Elements
     */
    GridPane root;
    /**
     * Game Mode versus Human
     */
    RadioButton vsHuman;
    /**
     * Game Mode versus Computer
     */
    RadioButton vsComp;
    /**
     * Black Figures just versus Computer
     */
    RadioButton black;
    /**
     * White Figures just versus Computer
     */
    RadioButton white;
    /**
     * Button to Start the Game.
     */
    Button start;
    /**
     * Game Controller.
     */
    GuiController guiController;

    /**
     * Constructor the Main Screen Layout.
     * @param primaryStage The Window
     * @param guiController Game Controller.
     */
    public StartMenuView(Stage primaryStage, GuiController guiController) {
        this.guiController = guiController;
        primaryStage.setTitle("Chess");
        // RadioButton to choose game Mode.
        Label mode = new Label("Select your Opponent");
        vsHuman = new RadioButton("Vs. Human");
        vsComp = new RadioButton("Vs. Computer");
        ToggleGroup gameMode = new ToggleGroup();
        vsComp.setToggleGroup(gameMode);
        vsHuman.setToggleGroup(gameMode);
        // if versus Computer, the Player can choose a Color.
        Label colorPiece = new Label("Select your Color");
        black = new RadioButton("Black");
        white = new RadioButton("White");
        ToggleGroup color = new ToggleGroup();
        black.setToggleGroup(color);
        white.setToggleGroup(color);
        // Color is disabled in the Start.
        black.setDisable(true);
        white.setDisable(true);
        // if vs. Computer chosen, color is available.
        vsComp.setOnAction(event -> gameMode(false));
        vsHuman.setOnAction(event -> gameMode(true));
        // Game Start Button
        start = new Button("Start The Game");
        start.setDisable(true);
        start.setOnAction(event -> new GameView(primaryStage, guiController));
        // adding all Elements to the root Layout
        root = new GridPane();
        root.setVgap(10);
        root.setAlignment(Pos.CENTER);
        root.add(mode, 0, 0);
        root.add(vsComp, 0, 1);
        root.add(vsHuman, 0, 2);
        root.add(colorPiece, 0, 3);
        root.add(black, 0, 4);
        root.add(white, 0, 5);
        root.add(start, 0, 6);

        primaryStage.setScene(new Scene(root, 300, 250));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    /**
     * Disable and Enable Color Option
     * @param mode Game Mode
     */
    public void gameMode(boolean mode){
        black.setDisable(mode);
        white.setDisable(mode);
        start.setDisable(false);
    }
}
