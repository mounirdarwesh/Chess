package chess.view.gui;

import chess.Attributes;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Main Screen Layout.
 *
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

    RadioButton withTimer;

    TextField duration;

    /**
     * The constructor of the start menu class
     *
     * @param gui View.
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

    /**
     * welcome screen for the Gui
     */
    private void configureGameModePanel() {
        //Creating a new Grid
        GridPane gameModePanel = new GridPane();
        gameModePanel.setAlignment(Pos.CENTER);
        gameModePanel.setPadding(new Insets(20, 20, 20, 20));
        gameModePanel.setVgap(20);
        gameModePanel.setHgap(20);

        Label mode = new Label("Select your opponent");
        mode.setFont(new Font("Sans-serif", 20));
        mode.setStyle("-fx-font-weight: bold;");
        mode.setTextFill(Color.BLACK);

        againstHuman = new Button("Human");
        GridPane.setConstraints(againstHuman, 0, 1);
        againstAI = new Button("AI");
        againstAI.setMinWidth(55);
        GridPane.setConstraints(againstAI, 1, 1);

        withTimer = new RadioButton();
        withTimer.setFont(new Font("Sans-serif", 12));
        withTimer.setStyle("-fx-font-weight: bold;");
        withTimer.setTextFill(Color.BLACK);
        withTimer.setText("Game with Timer?");
        GridPane.setConstraints(withTimer, 0, 2);

        duration = new TextField();
        duration.setPromptText("just numeric Value!");
        inputFilter();
        duration.setDisable(true);
        GridPane.setConstraints(duration, 0, 3);
        timerChoice();

        gameModePanel.getChildren().addAll(mode, againstHuman, againstAI, withTimer, duration);
        root.getChildren().add(gameModePanel);

        AddListenersToGameMode();

    }

    /**
     * choose a game with timer option
     */
    public void timerChoice() {
        withTimer.setOnAction(Event -> {
            duration.setDisable(!withTimer.isSelected());
        });
    }

    /**
     *  force the field to be numeric only
     */
    public void inputFilter(){
        duration.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    duration.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
    }

    /**
     * listener for the welcome screen
     */
    private void AddListenersToGameMode() {
        againstHuman.setOnAction(Event -> {
            if (withTimer.isSelected()) {
                if (!duration.getText().equals(""))
                    gui.guiController.gameModeOnAction(Attributes.GameMode.HUMAN_TIMER, duration.getText());
                else duration.setPromptText("Please enter a Number Value!");
            } else gui.guiController.gameModeOnAction(Attributes.GameMode.HUMAN, null);
        });

        againstAI.setOnAction(Event -> {
            if (withTimer.isSelected()) {
                gui.guiController.gameModeOnAction(Attributes.GameMode.COMPUTER_TIMER, duration.getText());
            }
            gui.guiController.gameModeOnAction(Attributes.GameMode.COMPUTER, null);
        });
    }

    /**
     * this methode return a node as parent
     *
     * @return root
     */
    public Parent asParent() {
        return root;
    }

    /**
     * shows the popup menu to choose the color (white or black)
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

    /**
     * listener for the choose color popup
     */
    private void AddListenersToColorChoice() {
        white.setOnAction(Event -> {
            gui.guiController.colorChoiceOnAction(Attributes.Color.WHITE);
        });
        black.setOnAction(Event -> {
            gui.guiController.colorChoiceOnAction(Attributes.Color.BLACK);
        });
    }

    /**
     * Getter for the black button in choose color popup
     *
     * @return black
     */
    public Button getBlack() {
        return black;
    }

    /**
     * Getter for the white button in choose color popup
     *
     * @return white
     */
    public Button getWhite() {
        return white;
    }

    /**
     * stage of choose the color
     *
     * @return colorChoice
     */
    public Stage getColorChoice() {
        return colorChoice;
    }
}
