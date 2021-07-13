package chess.view.gui.startMenuView;

import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.VBox;

public class ClockChoicePanel {

    /**
     * The parent
     */
    private StartMenuView startMenuView;


    /**
     * The panel
     */
    private VBox clockChoicePanel;

    /**
     * timer option
     */
    private ChoiceBox<String> timerOptions;

    /**
     *start game button
     */
    private Button startGameButton;

    /**
     * constructor of the class
     * @param startMenuView view of the start menu
     */
    public ClockChoicePanel(StartMenuView startMenuView) {
        this.startMenuView = startMenuView;
        clockChoicePanel = new VBox();
        startMenuView.getParent().setPanelStyle(clockChoicePanel);

        startMenuView.getParent().createTitleText("Choose A Clock Time", 20, clockChoicePanel);

        timerOptions = new ChoiceBox<>();
        timerOptions.getItems().addAll("None", "1","2","3","4","5","10","15","20","30","40","50","60");
        clockChoicePanel.getChildren().add(timerOptions);

        startGameButton = startMenuView.getParent().createButton("Start Game", clockChoicePanel);
        startGameButton.setDisable(true);
    }

    /**
     * getter of the timerOption
     * @return ChoiceBox
     */
    public ChoiceBox<String> getTimerOptions() {
        return timerOptions;
    }

    /**
     * getter of the startGameButton
     * @return Button
     */
    public Button getStartGameButton() {
        return startGameButton;
    }

    /**
     * choice panel of the clock
     * @return VBox
     */
    public VBox asPanel() {
        return clockChoicePanel;
    }
}
