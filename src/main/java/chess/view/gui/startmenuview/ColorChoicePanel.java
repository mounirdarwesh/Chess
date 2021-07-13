package chess.view.gui.startmenuview;

import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

/**
 * Panel to choice color
 */
public class ColorChoicePanel {

    /**
     * The parent
     */
    private StartMenuView startMenuView;

    /**
     * The panel
     */
    private VBox colorChoicePanel;

    /**
     * The White pieces button
     */
    private Button whiteButton;

    /**
     * The Black pieces button
     */
    private Button blackButton;

    /**
     * The color choice panel constructor
     * @param startMenuView view of the start menu
     */
    public ColorChoicePanel(StartMenuView startMenuView) {
        this.startMenuView = startMenuView;
        colorChoicePanel = new VBox();
        startMenuView.getParent().setPanelStyle(colorChoicePanel);

        startMenuView.getParent().createTitleText("Choose The Color of Your Pieces", 20, colorChoicePanel);

        whiteButton = startMenuView.getParent().createButton("White", colorChoicePanel);
        blackButton = startMenuView.getParent().createButton("Black", colorChoicePanel);
    }

    /**
     * getter of the whiteButton
     * @return Button
     */
    public Button getWhiteButton() {
        return whiteButton;
    }

    /**
     * getter of the blackButton
     * @return Button
     */
    public Button getBlackButton() {
        return blackButton;
    }

    /**
     * choice panel of color
     * @return VBox
     */
    public VBox asPanel() {
        return colorChoicePanel;
    }

}
