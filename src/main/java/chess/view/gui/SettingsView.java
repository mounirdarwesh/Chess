package chess.view.gui;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.layout.*;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * the class of viewing the settings
 * @author Gr.45
 */
public class SettingsView {

    /**
     * The ROOT Stage
     */
    private Gui parent;

    /**
     * scene of the setting
     */
    private Scene settingsScene;

    /**
     * The root
     */
    private GridPane root;

    /**
     * enables the rotation option
     */
    private ComboBox enableRotation;

    /**
     * enable the reselection option
     */
    private ComboBox enableReselection;

    /**
     * enables notifications option
     */
    private ComboBox enableNotification;

    /**
     * enable highlighting option
     */
    private ComboBox enableHighlighting;


    /**
     * button to confirm the chosen settings
     */
    private Button confirmSettings;

    /**
     * button to back to the gameView
     */
    private Button backToGameView;

    /**
     * constructor of the class
     * @param gui gui
     */
    public SettingsView(Gui gui) {
        this.parent = gui;
        this.root = new GridPane();
        settingsScene = new Scene(root);
        VBox settingsPanel = new VBox();

        root.setAlignment(Pos.CENTER);

        addBackgroundImage();

        parent.setPanelStyle(settingsPanel);

        parent.createTitleText("Choose Game Settings", 20, settingsPanel);

        enableRotation = parent.createComboBox("Enable Board Rotation",
                List.of("Yes", "No"), settingsPanel);
        enableReselection = parent.createComboBox("Enable Reselection of Piece",
                List.of("Yes", "No"), settingsPanel);
        enableNotification = parent.createComboBox("Enable Notification",
                List.of("Yes", "No"), settingsPanel);
        enableHighlighting = parent.createComboBox("Enable Highlighting",
                List.of("Yes", "No"), settingsPanel);
        confirmSettings = parent.createButton("Confirm Settings", settingsPanel);
        backToGameView = parent.createButton("Back", settingsPanel);

        root.getChildren().add(settingsPanel);

    }

    /**
     * Adding a background Image to the Scene
     */
    private void addBackgroundImage() {
        try {
            InputStream background = Files.newInputStream(Paths.get("src/main/res/settings.jpg"));
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
     * enables the reselection setting
     * @return ComboBox
     */
    public ComboBox getEnableReselectionSetting() {
        return enableReselection;
    }

    /**
     * enables the rotation settings
     * @return ComboBox
     */
    public ComboBox getEnableRotationSetting() {
        return enableRotation;
    }

    /**
     * enables the notification setting
     * @return ComboBox
     */
    public ComboBox getEnableNotificationSetting() {
        return enableNotification;
    }

    /**
     * enables the highlighting setting
     * @return ComboBox
     */
    public ComboBox getEnableHighlightingSetting() {
        return enableHighlighting;
    }

    /**
     * button to confirm settings
     * @return Button
     */
    public Button getConfirmSettingsButton() {
        return confirmSettings;
    }

    /**
     * button to back to the gameView
     * @return Button
     */
    public Button getBackToGameViewButton() {
        return backToGameView;
    }

    /**
     * scene for settings
     * @return Scene
     */
    public Scene asScene() {
        return settingsScene;
    }
}
