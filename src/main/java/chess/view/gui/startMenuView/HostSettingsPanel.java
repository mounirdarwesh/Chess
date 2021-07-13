package chess.view.gui.startMenuView;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.VBox;

import java.util.List;

/**
 *  class of the host settings panel
 *  @author Gr.45
 */
public class HostSettingsPanel {

    /**
     * The parent
     */
    private StartMenuView startMenuView;

    /**
     * host settings panel box
     */
    private VBox hostSettingsPanel;

    /**
     * combo box for color
     */
    private ComboBox color;

    /**
     * combo box for redo
     */
    private ComboBox redo;

    /**
     * combo box for time
     */
    private ComboBox<String> time;

    /**
     * start button
     */
    private Button start;

    /**
     * constructor of the class
     * @param startMenuView view of the start menu
     */
    public HostSettingsPanel(StartMenuView startMenuView) {
        this.startMenuView = startMenuView;
        hostSettingsPanel = new VBox();
        startMenuView.getParent().setPanelStyle(hostSettingsPanel);

        startMenuView.getParent().createTitleText("Choose Game Settings", 20, hostSettingsPanel);

        color = startMenuView.getParent().createComboBox("Pieces' Color",
                List.of("White", "Black"), hostSettingsPanel);
        redo = startMenuView.getParent().createComboBox("Enable Redo",
                List.of("Yes", "No"), hostSettingsPanel);
        time = startMenuView.getParent().createComboBox("Enable Time",
                List.of("Yes", "No"), hostSettingsPanel);
        start = startMenuView.getParent().createButton("Start Game", hostSettingsPanel);
        start.setDisable(true);
    }

    /**
     * getter of the chooseColorButton
     * @return ComboBox
     */
    public ComboBox getChooseColorButton() {
        return color;
    }

    /**
     * getter of the enableRedoButton
     * @return ComboBox
     */
    public ComboBox getEnableRedoButton() {
        return redo;
    }

    /**
     * getter of the enableTimeButton
     * @return ComboBox
     */
    public ComboBox<String> getEnableTimeButton() {
        return time;
    }

    /**
     * getter of the startLANGameButton
     * @return Button
     */
    public Button getStartLANGameButton() {
        return start;
    }

    /**
     * panel of the host settings
     * @return VBox
     */
    public VBox asPanel() {
        return hostSettingsPanel;
    }
}
