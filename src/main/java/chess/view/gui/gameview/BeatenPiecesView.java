package chess.view.gui.gameview;

import chess.model.game.Game;
import chess.model.pieces.Piece;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
/**
 * the class to view the beaten pieces
 * @author Gr.45
 */
public class BeatenPiecesView {

    /**
     * box that shows the beaten pieces
     */
    private VBox beatenPiecesViewContainer;

    /**
     * game
     */
    private Game game;

    /**
     * box that shows the beaten pieces of the player white
     */
    private VBox whiteBeatenView;

    /**
     * box that shows the beaten pieces of the player black
     */
    private VBox blackBeatenView;


    /**
     * show the beaten pieces
     * @param game the current game
     */
    public BeatenPiecesView(Game game) {
        this.game = game;
        beatenPiecesViewContainer = new VBox();
        beatenPiecesViewContainer.setMaxWidth(300);
        beatenPiecesViewContainer.setMinWidth(300);
        whiteBeatenView = new VBox();
        blackBeatenView = new VBox();
        ScrollPane whiteScrollPane = new ScrollPane();
        whiteScrollPane.setMinHeight(320);
        whiteScrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        whiteScrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        whiteScrollPane.setContent(whiteBeatenView);
        ScrollPane blackScrollPane = new ScrollPane();
        blackScrollPane.setMinHeight(320);
        blackScrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        blackScrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        blackScrollPane.setContent(blackBeatenView);

        beatenPiecesViewContainer.getChildren().addAll(whiteScrollPane, blackScrollPane);
    }

    /**
     * show the beaten pieces
     */
    public void showBeatenPiecesView() {
        if(game.getCurrentPlayer().getBeaten().isEmpty()) return;
        if(game.getCurrentPlayer().getColor().isWhite()) {
            whiteBeatenView.getChildren().clear();
            for (Piece beaten : game.getWhitePlayer().getBeaten()) {
                Label history = new Label(beaten.getSymbol());
                history.setFont(new Font(30));
                whiteBeatenView.getChildren().add(history);
            }
        }
        if(game.getCurrentPlayer().getColor().isBlack()) {
            blackBeatenView.getChildren().clear();
            for (Piece beaten : game.getBlackPlayer().getBeaten()) {
                Label history = new Label(beaten.getSymbol());
                history.setFont(new Font(30));
                blackBeatenView.getChildren().add(history);
            }
        }
    }

    /**
     * shows the container of beaten pieces as a node
     * @return VBox
     */
    public VBox asNode() {
        return beatenPiecesViewContainer;
    }

}
