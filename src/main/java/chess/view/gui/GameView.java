package chess.view.gui;

import chess.model.Game;
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
    public static HBox report;
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
     * Move numbering
     */
    int numMove = 1;
    static HBox clock;
    /**
     * reselect a piece
     */
    private CheckMenuItem reselect;
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
     * the main screen
     */
    private MenuItem mainScreen;

    static HBox bottomNotification;

    /**
     * Construct Game View Basis Elements.
     *
     * @param gui View
     */
    public GameView(Gui gui) {
        this.gui = gui;

        // Creating the Menu Bar
        configureMenuBar();

        // Creating the board
        board = new BoardView(gui);
        setCenter(board);

        //Notification panel
        report = new HBox();
        report.setAlignment(Pos.CENTER);
        //setBottom(report);

        clock = new HBox();
        clock.setAlignment(Pos.CENTER);

        bottomNotification = new HBox();
        bottomNotification.setAlignment(Pos.CENTER);
        bottomNotification.setSpacing(5);
        setBottom(bottomNotification);
        bottomNotification.getChildren().addAll(report, clock);

        // Creating a history panel
        configureHistoryPanel();

        //Creating a panel for the beaten pieces
        configureBeatenPiecesPanel();

    }

    /**
     * show the timer of players
     *
     * @param leftTime the left Time
     */
    public static void showClock(String leftTime) {
        clock.getChildren().clear();
        Label time = new Label();
        time.setFont(new Font(20));
        time.setText(leftTime);
        GameView.clock.getChildren().add(time);
    }

    /**
     * Beaten Pieces Section.
     */
    private void configureBeatenPiecesPanel() {
        beaten = new VBox();
        beaten.setAlignment(Pos.TOP_CENTER);
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setMinWidth(100);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setContent(beaten);

        setLeft(scrollPane);
    }

    /**
     * History panel section
     */
    private void configureHistoryPanel() {
        // history Table
        history = new VBox();
        history.setAlignment(Pos.TOP_CENTER);
        ScrollPane historyScroll = new ScrollPane();
        historyScroll.setMinWidth(100);
        historyScroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        historyScroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        historyScroll.setContent(history);

        Button undo = new Button("undo");
        Button redo = new Button("redo");
        history.getChildren().addAll(undo, redo);

        undo.setOnAction(e -> {
            gui.guiController.undoMove();
            gui.guiController.clearUndidHistory();
            gui.guiController.setHistoryCleared(true);
        });
        redo.setOnAction(e -> {
            gui.guiController.redoMove();
            gui.guiController.addUndidHistory();
            gui.guiController.setHistoryCleared(false);
        });

        setRight(historyScroll);
    }

    /**
     * configure the menu bar in Gui
     */
    private void configureMenuBar() {
        gameMenu = new MenuBar();
        Menu gameOptions = new Menu("Game");
        mainScreen = new MenuItem("Main Screen");
        gameOptions.getItems().add(mainScreen);
        Menu setting = new Menu("Settings");
        rotate = new CheckMenuItem("Rotate Field");
        setting.getItems().add(rotate);
        reselect = new CheckMenuItem("Reselect Piece");
        reselect.setSelected(true);
        setting.getItems().add(reselect);
        notificationSetting = new CheckMenuItem("Check Status Notification");
        notificationSetting.setSelected(true);
        setting.getItems().add(notificationSetting);
        highlightVisibility = new CheckMenuItem("Show Highlighting");
        setting.getItems().add(highlightVisibility);
        highlightVisibility.setSelected(true);

        gameMenu.getMenus().addAll(gameOptions, setting);

        setTop(gameMenu);

        addActionListeners();
    }

    /**
     * Listener for the reselect Menu Item
     */
    private void addActionListeners() {
        reselect.setOnAction(Event -> {
            if (reselect.isSelected()) {
                gui.guiController.setAllowReselect(true);
            } else {
                gui.guiController.setAllowReselect(false);
            }
        });

        mainScreen.setOnAction(Event -> {
            gui.backToMainMenu();
            // delete old game Beaten.
            gui.guiController.getBeatenPieces().clear();
            Game.setFINISHED(false);
            if (gui.guiController.isClockRunning())
                gui.guiController.getChessClock().cancel();
        });
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
        if (gui.guiController.isHistoryCleared()) {
            numMove = history.getChildren().size() - 1;
        }
        Label move = new Label(numMove + ": " + gui.guiController.getAllowedMoveHuman().toString());
        move.setFont(new Font(15));
        GameView.history.getChildren().add(move);
        move.setOnMouseClicked(e -> {
            gui.guiController.undoMoveFromHistory(history.getChildren().indexOf(move));
        });
        numMove++;
    }

    /**
     * show every Move done by the Computer.
     */
    public void showHistoryComp() {
        Label history = new Label(numMove + ": " + gui.guiController.getComputerMove().toString() + " AI");
        history.setFont(new Font(15));
        GameView.history.getChildren().add(history);
        numMove++;
    }

    /**
     * show when each Player turn.
     */
    public void notification() {
        if (notificationSetting.isSelected()) {
            report.getChildren().clear();
            Label notification = new Label(gui.game.getCurrentPlayer().toString());
            notification.setFont(new Font(20));
            GameView.report.getChildren().add(notification);
            gui.game.checkGameStatus();
        }
    }

    /**
     * getter of the board
     *
     * @return board
     */
    public BoardView getBoard() {
        return board;
    }

    /**
     * setter of the board
     *
     * @param board the Board
     */
    public void setBoard(BoardView board) {
        this.board = board;
    }

}

