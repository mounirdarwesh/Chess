package chess.view.guiView;

import chess.controller.GuiController;
import chess.controller.Move;
import chess.model.Board;
import chess.model.Piece;
import javafx.event.Event;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * Chess Board
 */
public class BoardView extends GridPane {

    /**
     * The original GUI
     */
    private Gui gui;

    /**
     * list of Tile on the Board
     */
    private List<TileView> tiles;

    /**
     * A tile
     */
    private TileView tile;


    /**
     * @param gui
     */
    public BoardView(Gui gui) {
        this.gui = gui;
        setAlignment(Pos.CENTER);
        drawBoard();
    }

    /**
     *
     */
    public void drawBoard() {
        tiles = new ArrayList<>();
        // Create the chess Board layout with initial Location of Pieces.
        int id = 0;
        for (int row = 8; row > 0; row--) {
            for (int column = 0; column < 8; column++) {
                tile = new TileView(id);
                tile.setMaxSize(60, 60);
                tile.setMinSize(60, 60);
                tile.setBackground(fillTile(row, column));
                tiles.add(id, tile);
                add(tile, column, row);
                tile.putPiece(gui.game.getBoard());
                addListenersToTile(tile);
                id++;
            }
        }
    }

    /**
     * @param tile
     */
    private void addListenersToTile(TileView tile) {
        tile.setOnMouseClicked(Event -> {
            if (tile.getChildren().isEmpty()) {
                return;
            } else if (tile.getChildren().get(0) instanceof Circle) {
                gui.guiController.handleClickOnTileToMovePiece(tile);
            } else {
                gui.guiController.handleClickOnTileToHighlight(tiles, tile.getTileID());
            }
        });
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
