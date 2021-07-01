package chess.view.gui;

import chess.Attributes;
import chess.model.Computer;
import chess.model.HumanPlayer;
import javafx.concurrent.Task;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

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
     * The original GUI Object
     */
    private Gui gui;
    /**
     *
     */
    private boolean timeSelected = false;
    /**
     *
     */
    private String time;
    /**
     *
     */
    private AtomicBoolean validDuration = new AtomicBoolean(false);
    /**
     *
     */
    private ComboBox<String> redo;
    /**
     *
     */
    private ComboBox<String> color;


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
        VBox gameMode = new VBox();
        gameMode.setMinSize(170, 200);
        gameMode.setAlignment(Pos.CENTER);
        gameMode.setStyle("-fx-padding: 30 10 30 10;" +
                "-fx-spacing: 20;" +
                "-fx-border-width: 2;" +
                "-fx-border-insets: 5;" +
                "-fx-border-radius: 5;" +
                "-fx-border-color: white;");

        createTitleText("Choose Your Game", 14, gameMode);

        Button normal = createButton("Normal Game", gameMode);
        Button lan = createButton("LAN Game", gameMode);

        root.getChildren().add(gameMode);

        normal.setOnAction(event -> {
            configureNormalGamePanel(gameMode);
        });

        lan.setOnAction(event -> {
            configureLANGamePanel(gameMode);
        });
    }

    private void configureLANGamePanel(VBox gameMode) {
        gameMode.getChildren().clear();

        Button host = createButton("Host Game", gameMode);
        Button join = createButton("Join Game", gameMode);
        Button back = createButton("Back", gameMode);

        host.setOnAction(e -> {
            configureHostSettingPanel(gameMode);
        });

        join.setOnAction(e -> {
            configureJoinHostPanel(gameMode);
        });

        back.setOnAction(e -> {
            gameMode.getChildren().clear();
            configureGameModePanel();
        });
    }

    /**
     *
     * @param gameMode
     */
    private void configureJoinHostPanel(VBox gameMode) {
        gameMode.getChildren().clear();

        createTitleText("Enter LAN Settings", 14, gameMode);

        TextField ip = createTextField("IP Address", gameMode);
        TextField port = createTextField("Port", gameMode);
        Button joinGame = createButton("Join Game", gameMode);
        joinGame.setDisable(true);
        Button back = createButton("Back", gameMode);

        ip.setOnKeyTyped(e -> {
            joinGame.setDisable(false);
        });

        port.setOnKeyTyped(e -> {
            joinGame.setDisable(false);
        });

        joinGame.setOnAction(e -> {
            if(gui.guiController.checkJoinLANSettings(port.getText())) {
                gui.guiController.joinHostServer(ip.getText(), port.getText());
            }
        });

        back.setOnAction(e -> {
            gameMode.getChildren().clear();
            configureLANGamePanel(gameMode);
        });
    }

    /**
     *
     * @param gameMode
     */
    private void configureHostSettingPanel(VBox gameMode) {
        gameMode.getChildren().clear();

        createTitleText("Choose Game Settings", 14, gameMode);
        color = createComboBox("Pieces' Color",
                List.of("White", "Black"), gameMode);
        redo = createComboBox("Enable Redo",
                List.of("Yes", "No"), gameMode);
        ComboBox<String> time = createComboBox("Enable Time",
                List.of("Yes", "No"), gameMode);
        Button start = createButton("Start Game", gameMode);
        start.setDisable(true);
        Button back = createButton("Back", gameMode);

        color.setOnAction(e -> {
            if(color.getValue() != null
                    && redo.getValue() != null
                    && validDuration.get()) {
                start.setDisable(false);
            }
        });
        redo.setOnAction(e -> {
            if(color.getValue() != null
                    && redo.getValue() != null
                    && validDuration.get()) {
                start.setDisable(false);
            }
        });
        TextField duration = new TextField();
        time.setOnAction(e -> {
            clockTimeEventHandler(time, gameMode, start, duration);
            if(color.getValue() != null
                    && redo.getValue() != null
                    && validDuration.get()) {
                start.setDisable(false);
            }
        });
        back.setOnAction(e -> {
            gameMode.getChildren().clear();
            configureLANGamePanel(gameMode);
        });
        start.setOnAction(e -> {
            gui.guiController.initServer();
            gui.guiController.initLANGameSettings(color.getValue(), redo.getValue(),
                    time.getValue());
            configureWaitForResponseMessage(gameMode);
            if (gui.guiController.listenForServerRequest()) {
                gameMode.getChildren().clear();
                gui.guiController.createGame();
            }
        });

    }

    private void configureWaitForResponseMessage(VBox gameMode) {
        gameMode.getChildren().clear();

        ProgressIndicator progress = new ProgressIndicator();
        createTitleText("Waiting for someone to join the Server", 13, gameMode);
        gameMode.getChildren().add(progress);
        Task<LineChart> task = new Task<>() {

            @Override
            protected LineChart call() {
                for (int i = 0; i < 10; i++) {
                    try {
                        // do some work
                        Thread.sleep(500);
                    } catch (InterruptedException ignored) {
                    }
                    updateProgress(10 * i, 100);
                }
                updateProgress(100, 100);
                return new LineChart(new NumberAxis(), new NumberAxis());
            }
        };
        progress.progressProperty().bind(task.progressProperty());
    }

    /**
     * @param time
     * @param gameMode
     * @param start
     * @param duration
     */
    private void clockTimeEventHandler(ComboBox<String> time, VBox gameMode, Button start, TextField duration) {
        if (time.getValue().equals("Yes")) {
            duration.setPromptText("Time in seconds");
            duration.setMinSize(50, 18);
            VBox.setMargin(duration, new Insets(0, 0, 0, 0));
            gameMode.getChildren().add(4, duration);
        }
        else {
            gameMode.getChildren().remove(duration);
        }
        duration.setOnKeyTyped(e -> {
            validDuration.set(true);
            String d = duration.getText();
            if (d.matches("[0-9]+") && color.getValue() != null
                    && redo.getValue() != null) {
                start.setDisable(false);
            }
        });
    }

    /**
     *
     * @param gameMode
     */
    private void configureNormalGamePanel(VBox gameMode) {
        gameMode.getChildren().clear();

        createTitleText("Choose Your Opponent", 14, gameMode);
        Button human = createButton("Human", gameMode);
        Button ai = createButton("AI", gameMode);
        Button back = createButton("Back", gameMode);

        human.setOnAction(e -> {
            gui.guiController.setOpponent(new HumanPlayer(Attributes.Color.BLACK));
            gui.guiController.setGameAgainstComputer(false);
            gui.guiController.setPlayerColor(Attributes.Color.WHITE);
            configureClockPanel(gameMode);
        });

        ai.setOnAction(e -> {
            gui.guiController.setGameAgainstComputer(true);
            configureColorChoicePanel(gameMode);
        });

        back.setOnAction(e -> {
            gameMode.getChildren().clear();
            configureGameModePanel();
        });
    }

    private void configureColorChoicePanel(VBox gameMode) {
        gameMode.getChildren().clear();

        createTitleText("Choose The Colour of Your Pieces", 13, gameMode);

        Button white = createButton("White", gameMode);
        Button black = createButton("Black", gameMode);
        Button back = createButton("Back", gameMode);

        white.setOnAction(e -> {
            gui.guiController.setPlayerColor(Attributes.Color.WHITE);
            gui.guiController.setOpponent(new Computer(Attributes.Color.BLACK));
            configureClockPanel(gameMode);
        });

        black.setOnAction(e -> {
            gui.guiController.setPlayerColor(Attributes.Color.BLACK);
            gui.guiController.setOpponent(new Computer(Attributes.Color.WHITE));
            configureClockPanel(gameMode);
        });

        back.setOnAction(e -> {
            gameMode.getChildren().clear();
            configureNormalGamePanel(gameMode);
        });
    }

    /**
     *
     * @param gameMode
     */
    private void configureClockPanel(VBox gameMode) {
        gameMode.getChildren().clear();

        createTitleText("Choose A Clock Time", 14, gameMode);

        ChoiceBox<String> choiceBox = new ChoiceBox<>();
        choiceBox.getItems().addAll("None", "1","2","3","4","5","10","15","20","30","40","50","60");
        gameMode.getChildren().add(choiceBox);

        Button start = createButton("Start Game", gameMode);
        start.setDisable(true);
        Button back = createButton("Back", gameMode);


        choiceBox.setOnAction(e -> {
            start.setDisable(false);
            if (!choiceBox.getValue().equals("None")) {
                timeSelected = true;
            }
            time = choiceBox.getValue();
        });

        start.setOnAction(e -> {
            if (timeSelected) gui.guiController.assignTimer(time);
            gui.guiController.createGame();
        });

        back.setOnAction(e -> {
            gameMode.getChildren().clear();
            configureGameModePanel();
        });
    }

    /**
     *
     * @param title
     * @param size
     * @param vbox
     */
    private void createTitleText(String title, int size, VBox vbox) {
        Text text = new Text(title);
        text.setFont(Font.font("Arial", FontWeight.BOLD, size));
        text.setFill(Color.WHITE);
        vbox.getChildren().add(text);
    }

    /**
     *
     * @param title
     * @param vbox
     * @return
     */
    private Button createButton(String title, VBox vbox) {
        Button btn = new Button(title);
        btn.setMinSize(70,18);
        VBox.setMargin(btn, new Insets(0, 0, 0, 0));
        vbox.getChildren().add(btn);

        return btn;
    }

    /**
     *
     * @param title
     * @param choices
     * @param vbox
     * @return
     */
    private ComboBox<String> createComboBox (String title, List<String> choices, VBox vbox) {
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
     *
     * @param prompt
     * @param vBox
     * @return
     */
    private TextField createTextField(String prompt, VBox vBox) {
        TextField textField = new TextField();
        textField.setPromptText(prompt);
        textField.setMinSize(50, 18);
        VBox.setMargin(textField, new Insets(0, 0, 0, 0));
        vBox.getChildren().add(textField);
        return textField;
    }

    /**
     * this methode return a node as parent
     * @return root
     */
    public Parent asParent() {
        return root;
    }
}
