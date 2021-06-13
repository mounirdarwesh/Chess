package chess.view.guiView;

import chess.model.Piece;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

/**
 * Gui class that contains chess Board and Useful Elements
 *
 * @author Grupper 45
 */
public class GameView extends BorderPane {
    /**
     * Area to show the Beaten Pieces.
     */
    public static VBox beaten;
    /**
     * Area to show History.
     */
    public static VBox history;
    /**
     * Area to show the Notification.
     */
    public static HBox notification;
    /**
     * enable or disable notification.
     */
    public static CheckMenuItem notificationSetting;
    /**
     * Highlight visibility.
     */
    public static CheckMenuItem highlightVisibility;
    /**
     * Board Rotation
     */
    public static CheckMenuItem rotate;
    /**
     * numbering the Moves.
     */
    int numMove = 1;

    /**
     * Menu of the Game
     */
    private MenuBar gameMenu;
    /**
     * The board view
     */
    private BoardView board;
    /**
     * The original gui
     */
    private Gui gui;

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
        rotate = new CheckMenuItem("Rotate Field");
        setting.getItems().add(rotate);
        setting.getItems().add(new CheckMenuItem("Reselect Piece"));
        notificationSetting = new CheckMenuItem("Check Status Notification");
        notificationSetting.setSelected(true);
        setting.getItems().add(notificationSetting);
        highlightVisibility = new CheckMenuItem("Show Highlighting");
        setting.getItems().add(highlightVisibility);
        highlightVisibility.setSelected(true);
        gameMenu.getMenus().addAll(gameOptions, setting);

        setTop(gameMenu);
    }

    /**
     * show the Beaten Piece of the Game.
     */
    public void showBeaten() {
        GameView.beaten.getChildren().clear();
        if (gui.guiController.getBeatenPieces().size() != 0)
            for (Piece piece : gui.guiController.getBeatenPieces()) {
                Label beatenPiece = new Label(piece.getSymbol());
                beatenPiece.setFont(new Font(40));
                GameView.beaten.getChildren().add(beatenPiece);
            }
    }

    /**
     * show every Move done by the Players.
     */
    public void showHistory() {
        if (gui.guiController.wasLegalMove()) {
            Label history = new Label(numMove + ": " + gui.game.getAllowedMove().toString());
            history.setFont(new Font(15));
            GameView.history.getChildren().add(history);
        }
        numMove++;
    }

    /**
     * show when each Player turn.
     */
    public void notification() {
        if (notificationSetting.isSelected()) {
            notification.getChildren().clear();
            Label notification = new Label(gui.game.getCurrentPlayer().toString());
            notification.setFont(new Font(20));
            GameView.notification.getChildren().add(notification);
            gui.game.checkGameStatus();
        }
    }

    public BoardView getBoard() {
        return board;
    }

    public void setBoard(BoardView board) {
        this.board = board;
    }

}

