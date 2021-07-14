package chess.view.gui.startmenuview;

import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

/**
 * class of the panel LANGame
 * @author Gr.45
 */
public class LANGamePanel {

    /**
     * The Panel
     */
    private VBox lanGamePanelContainer;

    /**
     * The Host button, so the user can host a LAN game
     */
    private Button host;


    /**
     * The join button, so the user can join a LAN game
     */
    private Button join;

    /**
     * The constructor of the LAN Game panel
     * @param startMenuView view of the start Menu
     */
    public LANGamePanel(StartMenuView startMenuView) {
        lanGamePanelContainer = new VBox();
        startMenuView.getParent().setPanelStyle(lanGamePanelContainer);

        // Creating the buttons
        host = startMenuView.getParent().createButton("Host Game", lanGamePanelContainer);
        join = startMenuView.getParent().createButton("Join Game", lanGamePanelContainer);
    }

    /**
     * A getter for the host button
     * @return the button
     */
    public Button getHostButton() {
        return host;
    }

    /**
     * A getter for the join button
     * @return the button
     */
    public Button getJoinButton() {
        return join;
    }

    /**
     * panel of the lan game
     * @return  VBox
     */
    public VBox asPanel() {
        return lanGamePanelContainer;
    }
}
