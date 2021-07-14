package chess.view.gui.startmenuview;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

/**
 *  class of the panel join host
 *  @author Gr.45
 */
public class JoinHostPanel {

    /**
     * panel of the join host
     */
    private VBox joinHostPanelContainer;

    /**
     * text field for the ip address
     */
    private TextField ip;

    /**
     * join game button
     */
    private Button joinGame;

    /**
     * constructor of the class
     * @param startMenuView view of the start menu
     */
    public JoinHostPanel(StartMenuView startMenuView) {
        joinHostPanelContainer = new VBox();
        startMenuView.getParent().setPanelStyle(joinHostPanelContainer);

        startMenuView.getParent().createTitleText("Enter LAN Settings", 20, joinHostPanelContainer);

        ip = startMenuView.createTextField("IP Address", joinHostPanelContainer);
        joinGame = startMenuView.getParent().createButton("Join Game", joinHostPanelContainer);
        joinGame.setDisable(true);

    }

    /**
     * getter of the joinGameButton
     * @return Button
     */
    public Button getJoinGameButton() {
        return joinGame;
    }

    /**
     * getter of the ipNrField
     * @return TextField
     */
    public TextField getIpNrField() {
        return ip;
    }

    /**
     * panel of the join host
     * @return VBox
     */
    public VBox asPanel() {
        return joinHostPanelContainer;
    }
}
