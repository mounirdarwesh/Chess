package chess.view.gui.gameView;

import chess.view.gui.Gui;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;

/**
 * the class of viewing the game
 *
 * @author Gr.45
 */
public class GameView {
    /**
     * Width of the game view
     */
    private static final int GAME_VIEW_WIDTH = 1200;
    /**
     * Height of the game view
     */
    private static final int GAME_VIEW_HEIGHT = 750;
    /**
     * The root of the scene
     */
    protected BorderPane root;
    /**
     * The ROOT Stage
     */
    private Gui parent;
    /**
     * scene of the game
     */
    private Scene gameScene;
    /**
     * return to start menu item of the menu
     */
    private MenuItem returnToStartMenu;

    /**
     * game menu setting item of the menu
     */
    private MenuItem gameMenuSettings;

    /**
     * view of the board
     */
    private BoardView boardView;

    /**
     * view the history
     */
    private HistoryView historyView;

    /**
     * view the beaten pieces
     */
    private BeatenPiecesView beatenPiecesView;

    /**
     * Notification View
     */
    private NotificationView notificationView;

    /**
     * Promotion Pop up
     */
    private PromotionPopUp promotionPopUp;


    /**
     * view of the game
     *
     * @param parent parent
     */
    public GameView(Gui parent) {
        this.parent = parent;
        this.root = new BorderPane();
        this.gameScene = new Scene(root, 1200, 750);

        root.setBackground(new Background(
                new BackgroundFill(new LinearGradient(
                        0, 0, 1, 1, true,
                        CycleMethod.NO_CYCLE,
                        new Stop(0, Color.web("#81c483")),
                        new Stop(1, Color.web("#fcc200"))),
                        CornerRadii.EMPTY, Insets.EMPTY)));

        // Creating the menu bar
        createGameMenuBar();
    }

    /**
     * Creating the menu bar of the game
     */
    private void createGameMenuBar() {
        MenuBar menuBar = new MenuBar();

        Menu menu = new Menu("Game");
        returnToStartMenu = new MenuItem("Start Menu");
        gameMenuSettings = new MenuItem("Settings");

        menu.getItems().addAll(returnToStartMenu, gameMenuSettings);
        menuBar.getMenus().add(menu);

        root.setTop(menuBar);
    }

    /**
     * Initializing the Game View Components
     */
    public void initGameComponents() {
        // Create the view for the board
        boardView = new BoardView(parent.getGame());
        root.setCenter(boardView.asNode());

        // Create the view for the beaten pieces
        beatenPiecesView = new BeatenPiecesView(parent.getGame());
        root.setLeft(beatenPiecesView.asNode());

        // Create the view for the History
        historyView = new HistoryView(parent.getGame());
        root.setRight(historyView.asNode());

        // Create the view for the Notification
        notificationView = new NotificationView(parent.getGame());
        root.setBottom(notificationView.asNode());
    }

    /**
     * update state of board history and beaten pieces
     */
    public void updateState() {
        boardView.updateBoard();
        historyView.showHistoryMoves();
        beatenPiecesView.showBeatenPiecesView();
        notificationView.showNotification();
    }

    /**
     * show the Clock on the Notification View
     */
    public void showClock() {
        notificationView.showTimeRemaining();
    }

    /**
     * show Promotion Pop Up
     */
    public void showGameViewPopUp() {
        promotionPopUp = new PromotionPopUp();
        promotionPopUp.displayPopUp();
    }

    /**
     * getter of the returnToStartMenu
     *
     * @return MenuItem
     */
    public MenuItem getReturnToStartMenu() {
        return returnToStartMenu;
    }

    /**
     * getter of the GameMenuSettings
     *
     * @return MenuItem
     */
    public MenuItem getGameMenuSettings() {
        return gameMenuSettings;
    }

    /**
     * getter of the historyView
     *
     * @return HistoryView
     */
    public HistoryView getHistoryView() {
        return historyView;
    }

    /**
     * getter of the boardView
     *
     * @return BoardView
     */
    public BoardView getBoardView() {
        return boardView;
    }

    /**
     * getter of the parent
     *
     * @return Gui
     */
    public Gui getParent() {
        return parent;
    }

    /**
     * scene of the game
     *
     * @return Scene
     */
    public Scene asScene() {
        return gameScene;
    }

    /**
     * Getter for the Notification View
     *
     * @return Notification View
     */
    public NotificationView getNotificationView() {
        return notificationView;
    }

    /**
     * Getter for the Promotion Pop Up
     *
     * @return Promotion Pop Up
     */
    public PromotionPopUp getPromotionPopUp() {
        return promotionPopUp;
    }

}
