package chess.view.guiView;

import chess.Attributes;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Gui class that contains chess Board and Useful Elements
 *
 * @author Grupper 45
 */
public class GameView extends BorderPane {

    /**
     * the Layout of the Game Interface.
     */
    private BorderPane root;

    /**
     * Menu of the Game
     */
    private MenuBar gameMenu;

    public BoardView getBoard() {
        return board;
    }

    public void setBoard(BoardView board) {
        this.board = board;
    }

    /**
     * The board view
     */
    private BoardView board;

    /**
     * The original gui
     */
    private Gui gui;

    /**
     * Area to show the Beaten Pieces.
     */
    public static VBox beaten;

    /**
     * Area to show History.
     */
    public static VBox history;

    /**
     *
     */
    public static HBox notification;

    /**
     * Construct Game View Basis Elements.
     */
    public GameView(Gui gui) {
        this.gui = gui;

        // Creating the Menu Bar
        configureMenuBar();

        // Creating the board
        board = new BoardView(gui);
        setCenter(board);

        //Notification panel
        notification = new HBox();
        notification.setAlignment(Pos.CENTER);
        setBottom(notification);

        // Creating a history panel
        configureHistoryPanel();

        //Creating a panel for the beaten pieces
        configureBeatenPiecesPanel();
    }

    /**
     *
     */
    private void configureBeatenPiecesPanel() {
        beaten = new VBox();
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setMinWidth(200);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setContent(beaten);

        setLeft(scrollPane);
    }

    /**
     *
     */
    private void configureHistoryPanel() {
        // history Table
        history = new VBox();
        history.setAlignment(Pos.TOP_CENTER);
        ScrollPane historyScroll = new ScrollPane();
        historyScroll.setMinWidth(200);
        historyScroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        historyScroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        historyScroll.setContent(history);

        setRight(historyScroll);
    }

    /**
     *
     */
    private void configureMenuBar() {
        gameMenu = new MenuBar();
        Menu gameOptions = new Menu("Game");
        MenuItem mainScreen = new MenuItem("Main Screen");
        gameOptions.getItems().add(mainScreen);
        Menu setting = new Menu("Settings");
        setting.getItems().add(new CheckMenuItem("Rotate Field"));
        setting.getItems().add(new CheckMenuItem("Reselect Piece"));
        setting.getItems().add(new CheckMenuItem("Check Status Notification"));
        setting.getItems().add(new CheckMenuItem("Highlight Field"));
        gameMenu.getMenus().addAll(gameOptions, setting);

        setTop(gameMenu);
    }
}

