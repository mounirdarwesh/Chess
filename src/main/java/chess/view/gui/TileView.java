package chess.view.gui;

import chess.model.Board;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;

import java.util.List;

/**
 * the Tile class on the Chess Board.
 */
public class TileView extends StackPane {

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
     * get for the Tile Piece if exists
     *
     * @param chessBoard the Board on which the Tiles and Pieces located.
     */
    public void putPiece(Board chessBoard) {
        if (chessBoard.getPiece(tileID) != null) {
            Label symbol = new Label(chessBoard.getPiecesOnBoard().get(tileID).getSymbol());
            symbol.setFont(new Font(50));
            this.getChildren().add(symbol);
            StackPane.setAlignment(symbol, Pos.CENTER);
        } else{ this.getChildren().clear();}
    }

    /**
     * Getter for the tileID
     * @return tileID
     */
    public int getTileID() {
        return tileID;
    }

    /**
     * highlight a tile
     * @param visibility set the Visibility of the Highlights.
     */
    public void highlight(boolean visibility) {
        Circle high = new Circle(0, 0, 5, Color.BLACK);
        if (!visibility)
            high.setVisible(false);
        getChildren().add(high);
    }

    /**
     * highlighted tiles
     * @param highlightedTiles highlights tiles
     */
    public void deHighlight(List<TileView> highlightedTiles) {
        for (TileView tile : highlightedTiles) {
            if (tile.getChildren().size() > 1) {
                tile.getChildren().remove(1);
                continue;
            }
            tile.getChildren().clear();
        }
        highlightedTiles.clear();
    }
}
