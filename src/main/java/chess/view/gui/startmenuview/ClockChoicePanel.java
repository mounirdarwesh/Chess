package chess.view.gui.startmenuview;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

/**
 * Panel to choice Panel
 */
public class ClockChoicePanel {

    /**
     * The panel
     */
    private VBox clockChoicePanelContainer;

    /**
     * White timer option
     */
    private TextField whiteTimerOptions;

    /**
     * Black timer option
     */
    private TextField blackTimerOptions;

    /**
     *start game button
     */
    private Button startGameButton;

    /**
     * constructor of the class
     * @param startMenuView view of the start menu
     */
    public ClockChoicePanel(StartMenuView startMenuView) {
        clockChoicePanelContainer = new VBox();
        startMenuView.getParent().setPanelStyle(clockChoicePanelContainer);

        startMenuView.getParent().createTitleText("Choose A Clock Time", 20, clockChoicePanelContainer);

        whiteTimerOptions = new TextField();
        whiteTimerOptions.setPromptText("White Player Time in sec");
        clockChoicePanelContainer.getChildren().add(whiteTimerOptions);


        blackTimerOptions = new TextField();
        blackTimerOptions.setPromptText("Black Player Time in sec");
        clockChoicePanelContainer.getChildren().add(blackTimerOptions);

        startGameButton = startMenuView.getParent().createButton("Start Game", clockChoicePanelContainer);
    }

    /**
     * getter of the timerOption
     * @return ChoiceBox
     */
    public TextField getWhiteTimerOptions() {
        return whiteTimerOptions;
    }

    /**
     * getter of the timerOption
     * @return ChoiceBox
     */
    public TextField getBlackTimerOptions() {
        return blackTimerOptions;
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
        return clockChoicePanelContainer;
    }
}
