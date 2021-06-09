package chess.gui;

import chess.controller.GuiController;
import chess.controller.Move;
import chess.model.Board;
import chess.model.Piece;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

/**
 * Gui class that contains chess Board and Useful Elements
 *
 * @author Grupper 45
 */
public class GameView extends BorderPane {
    /**
     * the Layout of the Game Interface.
     */
    BorderPane layout;
    /**
     * Board of the Game
     */
    BoardView board;
    /**
     * Controller of the Game
     */
    GuiController guiController;

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
     *
     * @param primaryStage  the Window
     * @param guiController Controller
     */
    public GameView(Stage primaryStage, GuiController guiController) {
        this.guiController = guiController;
        layout = new BorderPane();
        board = new BoardView(guiController);
        // Menu Bar
        Menu gameOptions = new Menu("Game");
        MenuItem mainScreen = new MenuItem("Main Screen");
        // return to Main Screen
        mainScreen.setOnAction(event -> new StartMenuView(primaryStage, guiController));
        gameOptions.getItems().add(mainScreen);
        // Setting of the Game
        Menu setting = new Menu("Settings");
        setting.getItems().add(new CheckMenuItem("Rotate Field"));
        setting.getItems().add(new CheckMenuItem("Reselect Piece"));
        setting.getItems().add(new CheckMenuItem("Check Status Notification"));
        MenuBar gameMenu = new MenuBar();
        gameMenu.getMenus().addAll(gameOptions, setting);


        history = new VBox();
        history.setMinWidth(50);
        history.setAlignment(Pos.TOP_CENTER);
        ScrollPane historyScroll = new ScrollPane();
        historyScroll.setMinWidth(55);
        historyScroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        historyScroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        historyScroll.setContent(history);

        beaten = new VBox();
        beaten.setMinWidth(50);
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setMinWidth(55);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setContent(beaten);

        notification = new HBox();
        notification.setAlignment(Pos.CENTER);
        layout.setBottom(notification);
        layout.setLeft(scrollPane);
        layout.setTop(gameMenu);
        layout.setCenter(board);
        layout.setRight(historyScroll);
        primaryStage.setScene(new Scene(layout, 591, 530));
    }
}

/**
 * Chess Board
 */
class BoardView extends GridPane {
    /**
     * list of Tile on the Board
     */
    final List<TileView> tiles;
    /**
     * list of highlight Circle on each Tile.
     */
    final List<HighlightView> highlightViews;
    /**
     * Controller of the Game.
     */
    GuiController guiController;
    /**
     * first Click of Mouse.
     */
    boolean firstClick = true;
    /**
     * Source Tile
     */
    TileView tileSource;
    /**
     * Destination Tile
     */
    TileView tileDestination;

    /**
     * constructor of the initial Chess Board
     *
     * @param guiController Controller of the Game
     */
    public BoardView(GuiController guiController) {
        this.guiController = guiController;
        tiles = new ArrayList<>();
        highlightViews = new ArrayList<>();
        // Create the chess Board layout with initial Location of Pieces.
        int id = 0;
        for (int row = 8; row > 0; row--) {
            for (int column = 0; column < 8; column++) {
                TileView tile = new TileView(id);
                tile.setOnMouseClicked(event -> makeGuiMove(tile));
                tile.setMaxSize(60, 60);
                tile.setMinSize(60, 60);
                tile.setBackground(fillTile(row, column));
                tiles.add(id, tile);
                add(tile, column, row);
                tile.putPiece(guiController.getGame().getBoard());
                id++;
            }
        }
    }

    /**
     * send Move info to the Controller.
     *
     * @param tile Clicked Tile
     */
    public void makeGuiMove(TileView tile) {
        GameView.notification.getChildren().clear();
        if (firstClick) {
            if (guiController.validSelection(tile.getTileID())) {
                guiController.setSource(tile.getTileID());
                firstClick = false;
                tileSource = tile;
                highlight(tile.getTileID());
            } else {
                System.out.println("Not your Piece or Empty");
                return;
            }
        } else {
            guiController.setDestination(tile.getTileID());
            guiController.processInputFromPlayer();
            firstClick = true;
            this.tileDestination = tile;
            if (guiController.wasLegalMove())
                tileSource.getChildren().clear();
            if (tileDestination.getChildren().size() != 0) {
                tileDestination.getChildren().clear();
            }
            deHighlight();
            showBeaten();
            showHistory();
            notification();
            tile.putPiece(guiController.getGame().getBoard());
        }
    }

    public void showHistory() {
        if (guiController.wasLegalMove())
            GameView.history.getChildren().add(new Label(guiController.getGame().getAllowedMove().toString()));
    }

    public void notification() {
        GameView.notification.getChildren().add(new Label(guiController.getGame().getCurrentPlayer().toString()));
    }

    public void showBeaten() {
        GameView.beaten.getChildren().clear();
        if (guiController.getBeatenPieces().size() != 0)
            for (Piece piece : guiController.getBeatenPieces()) {
                Label beatenPiece = new Label(piece.getSymbol());
                beatenPiece.setFont(new Font(40));
                GameView.beaten.getChildren().add(beatenPiece);
            }
    }

    /**
     * highlight every possible move for a Piece.
     *
     * @param source the Piece source.
     */
    public void highlight(int source) {
        guiController.getGame().getBoard().getPiece(source).calculateLegalMoves();
        for (Move move : guiController.getGame().getBoard().getPiece(source).getAllLegalMoves()) {
            HighlightView high = new HighlightView();
            highlightViews.add(high);
            tiles.get(move.getDestination()).getChildren().add(high);
            high.highlightInit(tiles.get(move.getDestination()));
        }
    }

    /**
     * remove highlight Circle.
     */
    public void deHighlight() {
        for (int i = 0; i < 64; i++) {
            if (tiles.get(i).getChildren().size() > 1) {
                tiles.get(i).getChildren().clear();
                tiles.get(i).putPiece(guiController.getGame().getBoard());
            }
            // remove the Piece symbol if there is no Actual Piece on the Board.
            if (guiController.getGame().getBoard().getPiecesOnBoard().get(i) == null)
                tiles.get(i).getChildren().clear();
        }
        highlightViews.clear();
    }

    /**
     * Paint each Tile on the Board with the right Color.
     *
     * @param row    on which the Tile located
     * @param column on which the Tile located
     * @return the right color
     */
    private Background fillTile(int row, int column) {
        return (row + column) % 2 != 0 ?
                new Background(new BackgroundFill(Color.web("#D2691E"), CornerRadii.EMPTY, Insets.EMPTY)) :
                new Background(new BackgroundFill(Color.web("#FFEBCD"), CornerRadii.EMPTY, Insets.EMPTY));
    }
}

/**
 * the Tile class on the Chess Board.
 */
class TileView extends StackPane {
    /**
     * Tile ID
     */
    private final int tileID;

    /**
     * Constructor of the Tile
     *
     * @param tileID Tile Identifier
     */
    public TileView(int tileID) {
        this.tileID = tileID;
    }

    /**
     * Getter for the Tile Id.
     *
     * @return Tile Id
     */
    public int getTileID() {
        return tileID;
    }

    /**
     * get for the Tile Piece if exists
     *
     * @param chessBoard the Board on which the Tiles and Pieces located.
     */
    public void putPiece(Board chessBoard) {
        if (chessBoard.getPiece(tileID) != null) {
            Label symbol = new Label(chessBoard.getPiece(tileID).getSymbol());
            symbol.setFont(new Font(50));
            this.getChildren().add(symbol);
            StackPane.setAlignment(symbol, Pos.CENTER);
        } else this.getChildren().clear();
    }
}

/**
 * class to highlight every possible move Destination.
 */
class HighlightView extends Circle {

    /**
     * Constructor of the Highlighter
     */
    public HighlightView() {

    }


    /**
     * put for each Tile on the Board a Highlighter.
     *
     * @param tile ID
     */
    public void highlightInit(TileView tile) {
        Circle highCircle = new Circle(0, 0, 5, Color.BLACK);
        tile.getChildren().add(highCircle);
        highCircle.setVisible(true);
    }
}