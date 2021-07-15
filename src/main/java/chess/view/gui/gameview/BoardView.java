package chess.view.gui.gameview;


import chess.Attributes;
import chess.controller.guicontroller.GameViewController;
import chess.model.game.Game;
import chess.model.pieces.Piece;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

import java.util.ArrayList;
import java.util.List;

/**
 * the class of viewing the board
 *
 * @author Gr.45
 */
public class BoardView {

    /**
     * Width of the tile
     */
    private static final int WIDTH = 75;

    /**
     * Height of the tile
     */
    private static final int HEIGHT = 75;

    /**
     * root GridPane
     */
    private final GridPane root;

    /**
     * tiles of the board
     */
    private List<Tile> tiles = new ArrayList<>();

    /**
     * highlighted Tiles
     */
    private List<Tile> highlightedTiles = new ArrayList<>();

    /**
     * piece of the game
     */
    private Piece piece;

    /**
     * Rectangle of tiles
     */
    private Rectangle rect;

    /**
     * tiles of the board
     */
    private Tile tile;

    /**
     * game
     */
    private Game game;
    /**
     * Counter to Files
     */
    int fileCounter = 0;

    /**
     * view of the board
     *
     * @param game game
     */
    public BoardView(Game game) {
        super();
        this.game = game;
        root = new GridPane();
        root.setAlignment(Pos.CENTER);
        drawBoard();
    }

    /**
     * draw the board
     */
    public void drawBoard() {

        int id = 0;
        for (int rank = 7; rank >= 0; rank--) {
            for (int file = 0; file < 8; file++) {
                piece = game.getBoard().getPiece(id);
                tile = new Tile(id);
                rect = new Rectangle(rank, file, WIDTH, HEIGHT);
                rect.setFill(colorTile(rank, file));

                if (piece != null) {
                    putPieceOnTile(rect, piece.getSymbol(), tile);
                } else {
                    putPieceOnTile(rect, "", tile);
                }

                tiles.add(id, tile);
                root.add(tile, file, rank);
                drawFileRank(id);
                id++;
            }
        }
    }

    /**
     * update the board
     */
    public void updateBoard() {
        int index = 0;
        for (Piece piece : game.getBoard().getPiecesOnBoard()) {
            if (piece == null) {
                tiles.get(index).getChildren().remove(1);
                tiles.get(index).getChildren().add(1, new Label(""));
            }
            if (piece != null) {
                movePiece(piece.getPosition(), piece.getSymbol());
            }
            index++;
        }
        if (game.getController().getGameSettings()[5].equals("1")) {
            rotate(game.getCurrentPlayer().getColor());
        }
    }

    /**
     * this method rotate the board
     *
     * @param color color of pieces
     */
    public void rotate(Attributes.Color color) {
        if (color == Attributes.Color.BLACK) {
            root.setScaleY(-1);
            for (int i = 0; i < Attributes.BOARD_SIZE; i++) {
                tiles.get(i).setRotate(180);
                tiles.get(i).setScaleX(-1);
            }
        } else {
            root.setScaleY(1);
            for (int i = 0; i < Attributes.BOARD_SIZE; i++) {
                tiles.get(i).setRotate(0);
                tiles.get(i).setScaleX(1);
            }
        }
    }

    /**
     * draw File and Rank on the Board
     * @param id Tile Id
     */
    private void drawFileRank(int id) {
        String[] rankOfBoard = {"1", "2", "3", "4", "5", "6", "7", "8"};
        String[] fileOfBoard = {"a", "b", "c", "d", "e", "f", "g", "h"};
        if (id >= 0 && id <= 7) {
            putRankFile(fileOfBoard[id], tiles.get(id), false);
        }
        if (id % 8 == 0) {
            putRankFile(rankOfBoard[fileCounter], tiles.get(id), true);
            fileCounter++;
        }
    }

    /**
     * Put Rank and File on Tile
     * @param symbol rand or file
     * @param tileWithPiece the Tile
     * @param file true, if is file
     */
    private void putRankFile(String symbol, StackPane tileWithPiece, boolean file) {
        Label pieceSymbol = new Label(symbol);
        pieceSymbol.setFont(new Font(20));
        if (file) {
            StackPane.setAlignment(pieceSymbol, Pos.TOP_LEFT);
        } else {
            StackPane.setAlignment(pieceSymbol, Pos.TOP_RIGHT);
        }
        tileWithPiece.getChildren().add(pieceSymbol);
    }

    /**
     * put pieces on the tiles of the board
     *
     * @param rect          rectangle
     * @param symbol        symbol of every piece
     * @param tileWithPiece tile with piece
     */
    private void putPieceOnTile(Rectangle rect, String symbol, StackPane tileWithPiece) {
        Label pieceSymbol = new Label(symbol);
        pieceSymbol.setFont(new Font(50));
        StackPane.setAlignment(pieceSymbol, Pos.CENTER);
        tileWithPiece.getChildren().addAll(rect, pieceSymbol);
    }

    /**
     * move a piece
     *
     * @param position position of the piece
     * @param symbol   symbole of the piece
     */
    private void movePiece(int position, String symbol) {
        Label movedPiece = new Label(symbol);
        movedPiece.setFont(new Font(50));
        StackPane.setAlignment(movedPiece, Pos.CENTER);
        tiles.get(position).getChildren().set(1, movedPiece);
    }

    /**
     * the color of the tile
     *
     * @param rank horizontal
     * @param file vertical
     * @return Color
     */
    private Color colorTile(int rank, int file) {
        return (rank + file) % 2 == 0 ?
                Color.web("#9F452D")
                : Color.web("#F5E194");
    }

    /**
     * root as a node GridPane
     *
     * @return GridPane
     */
    public GridPane asNode() {
        return root;
    }

    /**
     * assign controller to a tile action
     *
     * @param gameViewController gameViewController
     */
    public void assignControllerToTileAction(GameViewController gameViewController) {
        for (Tile tile : tiles) {
            try {
                tile.setOnMouseClicked(gameViewController);
            } catch (Exception ignored) {
            }
        }
    }

    /**
     * getter of the tiles
     *
     * @return List
     */
    public List<Tile> getTiles() {
        return tiles;
    }

    /**
     * getter of the highlightedTiles
     *
     * @return List
     */
    public List<Tile> getHighlightedTiles() {
        return highlightedTiles;
    }

    /**
     * setter of the highlightedTiles
     *
     * @param highlightedTiles highlightedTiles
     */
    public void setHighlightedTiles(List<Tile> highlightedTiles) {
        this.highlightedTiles = highlightedTiles;
    }

    /**
     * The tile which holds the piece
     *
     * @author Gr.45
     */
    public class Tile extends StackPane {

        /**
         * ID of the tile
         */
        protected int id;

        /**
         * The original fill of the tile
         */
        private Paint original;

        /**
         * id for each tile
         *
         * @param id id of a tile
         */
        public Tile(int id) {
            this.id = id;
        }

        /**
         * Highlight the tile
         */
        public void highlight() {
            for (Node node : getChildren()) {
                if (node instanceof Rectangle) {
                    original = ((Rectangle) node).getFill();
                    ((Rectangle) node).setFill(Color.web("#E1F668"));
                }
            }
        }

        /**
         * De-highlight the tile
         */
        public void deHighlight() {
            for (Node node : getChildren()) {
                if (node instanceof Rectangle) {
                    ((Rectangle) node).setFill(original);
                }
            }
        }

        /**
         * getter of the id
         *
         * @return id for the tile
         */
        public int getID() {
            return this.id;
        }
    }
}
