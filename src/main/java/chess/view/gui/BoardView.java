package chess.view.gui;

import chess.Attributes;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

/**
 * Chess Board for Gui
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
     * constructor
     *
     * @param gui gui of the game
     */
    public BoardView(Gui gui) {
        this.gui = gui;
        setAlignment(Pos.CENTER);
        drawBoard();
    }

    /**
     * draw the board tile and fetch every Piece.
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
     * listener for each tile
     *
     * @param tile tile of the board
     */
    private void addListenersToTile(TileView tile) {
        tile.setOnMouseClicked(Event -> {
            gui.guiController.handleClickOnTileAction(tiles, tile);
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

    /**
     * this method rotate the board
     *
     * @param color color of pieces
     */
    public void rotate(Attributes.Color color) {
        if (color == Attributes.Color.BLACK) {
            this.setScaleY(-1);
            for (int i = 0; i < Attributes.BOARD_SIZE; i++) {
                tiles.get(i).setRotate(180);
                tiles.get(i).setScaleX(-1);
            }
        } else {
            this.setScaleY(1);
            for (int i = 0; i < Attributes.BOARD_SIZE; i++) {
                tiles.get(i).setRotate(0);
                tiles.get(i).setScaleX(1);
            }
        }
    }
}
