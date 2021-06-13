package chess.view.guiView;

import chess.Attributes;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Main Screen Layout.
 * @author Gruppe 45
 */
public class StartMenuView {

    /**
     * The main layout
     */
    private GridPane root;

    /**
     *
     */
    private Button againstHuman;

    /**
     *
     */
    private Button againstAI;

    /**
     * Button to choose the white pieces
     */
    private Button white;

    /**
     * Button to choose the black pieces
     */
    private Button black;

    /**
     * Popup window when the player chooses to play against an AI
     */
    private Stage colorChoice;

    /**
     * The original GUI Object
     */
    private Gui gui;

    /**
     * The constructor of the start menu class
     * @throws Exception exception
     */
    public StartMenuView(Gui gui) throws Exception {
        this.gui = gui;

        //The root
        root = new GridPane();
        root.setAlignment(Pos.CENTER);

        //Setting the background image
        InputStream background = Files.newInputStream(Paths.get("src/main/res/background.jpg"));
        Image bgImg = new Image(background);
        background.close();
        root.setBackground(new Background(new BackgroundImage(bgImg,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(1.0, 1.0,
                        true, true, false, false)
                )));

        // Creating the Game mode panel
        configureGameModePanel();
    }

    private void configureGameModePanel() {
        //Creating a new Grid
        GridPane gameModePanel = new GridPane();
        gameModePanel.setAlignment(Pos.CENTER);
        gameModePanel.setPadding(new Insets(20,20,20,20));
        gameModePanel.setVgap(10);
        gameModePanel.setHgap(10);

        Label mode = new Label("Select your opponent");
        mode.setFont(new Font("Sans-serif", 20));
        mode.setStyle("-fx-font-weight: bold;");
        mode.setTextFill(Color.BLACK);

        againstHuman = new Button("Human");
        GridPane.setConstraints(againstHuman, 0, 1);
        againstAI = new Button("AI");
        againstAI.setMinWidth(55);
        GridPane.setConstraints(againstAI, 1, 1);

        gameModePanel.getChildren().addAll(mode, againstHuman, againstAI);
        root.getChildren().add(gameModePanel);

        AddListenersToGameMode();

    }

    private void AddListenersToGameMode() {
        againstHuman.setOnAction(Event -> {
            gui.guiController.gameModeOnAction(Attributes.GameMode.HUMAN);
        });

        againstAI.setOnAction(Event -> {
            gui.guiController.gameModeOnAction(Attributes.GameMode.COMPUTER);
        });
    }

    /**
     * this methode return a node as parent
     * @return root
     */
    public Parent asParent() {
        return root;
    }

    public Button getAgainstAI() {
        return againstAI;
    }

    public Button getAgainstHuman() {
        return againstHuman;
    }

    /**
     *
     */
    public void showColorChoiceWindow() {
        colorChoice = new Stage();
        colorChoice.setTitle("Choose a color");
        colorChoice.setResizable(false);
        GridPane colorChoicePane = new GridPane();
        colorChoicePane.setAlignment(Pos.CENTER);
        Label message = new Label("Choose the color of your pieces");
        white = new Button("White");
        GridPane.setConstraints(white, 0, 1);
        black = new Button("Black");
        GridPane.setConstraints(black, 1, 1);
        colorChoicePane.getChildren().addAll(message, white, black);
        Scene scene = new Scene(colorChoicePane, 300, 200);
        colorChoice.setScene(scene);
        colorChoice.show();

        AddListenersToColorChoice();
    }

    private void AddListenersToColorChoice() {
        white.setOnAction(Event -> {
            gui.guiController.colorChoiceOnAction(Attributes.Color.WHITE);
        });
        black.setOnAction(Event -> {
            gui.guiController.colorChoiceOnAction(Attributes.Color.BLACK);
        });
    }

    /**
     *
     * @return black
     */
    public Button getBlack() {
        return black;
    }

    /**
     *
     * @return white
     */
    public Button getWhite() {
        return white;
    }

    /**
     *
     * @return colorChoice
     */
    public Stage getColorChoice() {
        return colorChoice;
    }
}
