package chess.gui;

import chess.model.Board;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.List;


public class GameView extends BorderPane {

    BorderPane layout;
    BoardView board;

    public GameView(Stage primaryStage) {
        layout = new BorderPane();
        board = new BoardView();
        Menu menu = new Menu("Game");
        MenuItem mainScreen = new MenuItem("Main Menu");
        mainScreen.setOnAction(event -> new StartMenuView(primaryStage));
        menu.getItems().add(mainScreen);
        Menu setting = new Menu("Settings");
        setting.getItems().add(new CheckMenuItem("Rotate Field"));
        setting.getItems().add(new CheckMenuItem("Reselect Piece"));
        setting.getItems().add(new CheckMenuItem("Check Status Notification"));
        MenuBar gameMenu = new MenuBar();
        gameMenu.getMenus().addAll(menu, setting);
        layout.setTop(gameMenu);
        layout.setCenter(board);
        TableView<String> history = new TableView<>();

        TableColumn<String, String> whiteBeaten = new TableColumn<>("White");
        TableColumn<String, String> blackBeaten = new TableColumn<>("Black");
        whiteBeaten.setResizable(true);
        history.getColumns().add(blackBeaten);
        history.getColumns().add(whiteBeaten);
        layout.setRight(history);
        primaryStage.setScene(new Scene(layout, 630, 505));
    }
}

class BoardView extends GridPane {
    Board chessBoard = new Board();
    final List<TileView> tiles;

    public BoardView() {
        tiles = new ArrayList<>();
        int id =0;
        for (int row = 8; row > 0; row--) {
            for (int column = 8; column >0; column--) {
                TileView tile = new TileView(id);
                tile.setMaxSize(60, 60);
                tile.setMinSize(60, 60);
                tile.setBackground(fillTile(row, column));
                tiles.add(id, tile);
                add(tile,column,row);
                tile.piecesInit(chessBoard);
                id++;
            }
        }
    }

    private Background fillTile(int row, int column) {
        return (row + column) % 2 != 0 ?
                new Background(new BackgroundFill(Color.web("#D2691E"), CornerRadii.EMPTY, Insets.EMPTY)) :
                new Background(new BackgroundFill(Color.web("#FFEBCD"), CornerRadii.EMPTY, Insets.EMPTY));
    }
}

class TileView extends StackPane {
    private final int tileID;

    public TileView(int tileID) {
        this.tileID = tileID;

    }

    public void piecesInit(Board chessBoard) {
        if (chessBoard.getPiece(tileID) != null) {
            Label symbol = new Label(chessBoard.getPiece(tileID).getSymbol());
            symbol.setFont(new Font(50));
            this.getChildren().add(symbol);
            StackPane.setAlignment(symbol, Pos.CENTER);
        }
    }
}
