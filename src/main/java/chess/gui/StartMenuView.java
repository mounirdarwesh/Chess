package chess.gui;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class StartMenuView extends GridPane {

    GridPane root;
    Scene startScene;
    RadioButton vsHuman;
    RadioButton vsComp;
    RadioButton black;
    RadioButton white;

    public StartMenuView(Stage primaryStage) {
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
        Button start = new Button("begin the Game");
        // if vs. Computer chosen, color is available.
        vsComp.setOnAction(event -> {
            black.setDisable(false);
            white.setDisable(false);
            start.setDisable(false);
        });
        vsHuman.setOnAction(event -> {
            black.setDisable(true);
            white.setDisable(true);
            start.setDisable(false);
        });
        root = new GridPane();
        root.setVgap(5);
        root.setAlignment(Pos.CENTER);
        root.add(mode, 0, 0);
        root.add(vsComp, 0, 1);
        root.add(vsHuman, 0, 2);
        root.add(colorPiece, 0, 3);
        root.add(black, 0, 4);
        root.add(white, 0, 5);

        start.setDisable(true);
        start.setOnAction(event -> new GameView(primaryStage));
        root.add(start, 0, 6);
        startScene = new Scene(root, 300, 250);
        primaryStage.setScene(startScene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public void startMenu(Stage primaryStage) {

    }
}
