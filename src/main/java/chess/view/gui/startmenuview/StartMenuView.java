package chess.view.gui.startmenuview;

import chess.view.gui.Gui;
import javafx.concurrent.Task;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * The Start Menu Scene of the GUI
 * @author Gr.45
 */
public class StartMenuView {

    /**
     * start menu scene
     */
    private Scene startMenuScene;

    /**
     * The ROOT Stage
     */
    private Gui parent;

    /**
     * The root of the scene
     */
    private GridPane root;

    /**
     * The Start Menu Panel for the Scene
     */
    private VBox gameModePanel;

    /**
     * The Normal game mode button
     */
    private Button normal;

    /**
     * The LAN game mode button
     */
    private Button lan;

    /**
     * Start menu Panels
     */
    private List<VBox> startMenuPanels;

    /**
     * back button
     */
    private Button back = new Button("back");


    /**
     * constructor of the class
     * @param parent parent
     */
    public StartMenuView(Gui parent) {
        this.root = new GridPane();
        this.parent = parent;
        startMenuScene = new Scene(root);

        //Setting the basics of the scene
        root.setAlignment(Pos.CENTER);
        addBackgroundImage();

        // Initializing the list of panels
        startMenuPanels = new ArrayList<>();

        // Creating The Game Mode Option
        gameModePanel = new VBox();
        startMenuPanels.add(gameModePanel);
        parent.setPanelStyle(gameModePanel);
        parent.createTitleText("Choose Your Game", 20, gameModePanel);

        normal = parent.createButton("Normal Game", gameModePanel);
        lan = parent.createButton("LAN Game", gameModePanel);

        // Adding the panel to the scene
        root.getChildren().add(gameModePanel);

    }

    /**
     * Adding a background Image to the Scene
     */
    private void addBackgroundImage() {
        try {
            InputStream background = Files.newInputStream(Paths.get("src/main/res/background.jpg"));
            Image bgImg = new Image(background);
            root.setBackground(new Background(new BackgroundImage(bgImg,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundPosition.CENTER,
                    new BackgroundSize(1.0, 1.0,
                            true, true, false, false)
            )));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Creating a showing a message while someone tries to join the LAN server
     */
    private void showWaitForResponseMessage() {
        gameModePanel.getChildren().clear();
        ProgressIndicator progress = new ProgressIndicator();
        parent.createTitleText("Waiting for someone to join the Server", 20, gameModePanel);
        gameModePanel.getChildren().add(progress);
        Task<LineChart> task = new Task<>() {

            @Override
            protected LineChart call() {
                for (int i = 0; i < 10; i++) {
                    try {
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
     * Creating a textField for the start menu panel
     * @param prompt The prompt of the text field
     * @param vBox The carrying panel
     * @return TextField
     */
    protected TextField createTextField(String prompt, VBox vBox) {
        TextField textField = new TextField();
        textField.setPromptText(prompt);
        textField.setMinSize(50, 18);
        VBox.setMargin(textField, new Insets(0, 0, 0, 0));
        vBox.getChildren().add(textField);
        return textField;
    }

    /**
     * Changing the panel of the scene
     * @param panel The panel to add
     */
    public void setScenePanel(VBox panel) {
        //Adding the panel to the list of panels
        if (!startMenuPanels.contains(panel)) {
            startMenuPanels.add(panel);
        }
        //Clearing the current panel
        root.getChildren().clear();
        //Adding the new Panel
        root.getChildren().add(panel);

        //Adding a back button to go back to previous scenes
        if (panel != gameModePanel) {
            back.setMinSize(70,18);
            VBox.setMargin(back, new Insets(0, 0, 0, 0));
            panel.getChildren().add(back);
        }
    }

    /**
     * getter of the normalGameOption
     * @return Button
     */
    public Button getNormalGameOption() {
        return normal;
    }

    /**
     * getter of the lanGameOption
     * @return Button
     */
    public Button getLanGameOption() {
        return lan;
    }

    /**
     * getter of the startMenuPanels
     * @return List
     */
    public List<VBox> getStartMenuPanels() {
        return startMenuPanels;
    }

    /**
     * getter of the backButton
     * @return Button
     */
    public Button getBackButton() {
        return back;
    }

    /**
     * getter of the parent
     * @return Gui
     */
    public Gui getParent() {
        return parent;
    }

    /**
     * scene of the start menu
     * @return Scene
     */
    public Scene asScene() {
        return startMenuScene;
    }

}
