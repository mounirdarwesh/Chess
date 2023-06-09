package chess.view.gui.startmenuview;

import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

/**
 * class of the panel Normal game
 * @author Gr.45
 */
public class NormalGamePanel {

    /**
     * The Panel
     */
    private VBox normalGamePanelContainer;

    /**
     * A button to let the user choose a human player as an opponent
     */
    private Button humanButton;

    /**
     * A button to let the user choose an AI as an opponent
     */
    private Button aiButton;


    /**
     * The constructor for the normal game Panel
     * @param startMenuView startmenuview
     */
    public NormalGamePanel(StartMenuView startMenuView) {
        normalGamePanelContainer = new VBox();
        startMenuView.getParent().setPanelStyle(normalGamePanelContainer);

        // Creating the message of the panel
        startMenuView.getParent().createTitleText("Choose Your Opponent", 20, normalGamePanelContainer);

        // Creating the buttons of the panel
        humanButton = startMenuView.getParent().createButton("Human", normalGamePanelContainer);
        aiButton = startMenuView.getParent().createButton("AI", normalGamePanelContainer);
    }

    /**
     * A getter for humanButton
     * @return The humanButton
     */
    public Button getHumanGameButton() {
        return humanButton;
    }

    /**
     * A getter for aiButton
     * @return The aiButton
     */
    public Button getAIGameButton() {
        return aiButton;
    }

    /**
     * panel of the normal game
     * @return VBox
     */
    public VBox asPanel() {
        return normalGamePanelContainer;
    }
}
