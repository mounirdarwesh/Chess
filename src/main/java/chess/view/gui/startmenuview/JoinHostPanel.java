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
     * The parent
     */
    private StartMenuView startMenuView;

    /**
     * panel of the join host
     */
    private VBox joinHostPanel;

    /**
     * text field for the ip address
     */
    private TextField ip;

    /**
     * text field for the port number
     */
    private TextField port;

    /**
     * join game button
     */
    private Button joinGame;

    /**
     * constructor of the class
     * @param startMenuView view of the start menu
     */
    public JoinHostPanel(StartMenuView startMenuView) {
        this.startMenuView = startMenuView;
        joinHostPanel = new VBox();
        startMenuView.getParent().setPanelStyle(joinHostPanel);

        startMenuView.getParent().createTitleText("Enter LAN Settings", 20, joinHostPanel);

        ip = startMenuView.createTextField("IP Address", joinHostPanel);
        port = startMenuView.createTextField("Port", joinHostPanel);
        joinGame = startMenuView.getParent().createButton("Join Game", joinHostPanel);
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
     * getter of the portNrField
     * @return TextField
     */
    public TextField getPortNrField() {
        return port;
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
        return joinHostPanel;
    }
}
