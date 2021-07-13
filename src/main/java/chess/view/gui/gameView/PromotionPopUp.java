package chess.view.gui.gameView;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * class for popup question which piece to promote to
 */
public class PromotionPopUp {

    /**
     * Promotion char
     */
    private char promote = ' ';
    /**
     * the PopUp window
     */
    private Stage promotion;

    /**
     * configure the PopUP with the Figures.
     */
    public void displayPopUp() {
        promotion = new Stage();
        promotion.setTitle("Select the Promoted Piece");
        promotion.initModality(Modality.APPLICATION_MODAL);
        promotion.setMinWidth(100);
        promotion.setMinHeight(80);

        Label queen = new Label("♕");
        Label bishop = new Label("♗");
        Label rook = new Label("♖");
        Label knight = new Label("♘");
        queen.setFont(new Font(40));
        bishop.setFont(new Font(40));
        rook.setFont(new Font(40));
        knight.setFont(new Font(40));
        rook.setOnMouseClicked(event -> {
            promote = 'r';
            promotion.close();
        });
        knight.setOnMouseClicked(event -> {
            promote = 'k';
            promotion.close();
        });
        queen.setOnMouseClicked(event -> {
            promote = 'q';
            promotion.close();
        });
        bishop.setOnMouseClicked(event -> {
            promote = 'b';
            promotion.close();
        });

        HBox promotedPieces = new HBox();
        promotedPieces.setSpacing(10);
        promotedPieces.getChildren().addAll(rook, knight, bishop, queen);
        promotedPieces.setAlignment(Pos.CENTER);

        promotion.setScene(new Scene(promotedPieces, 400, 150));
        promotion.showAndWait();
    }

    /**
     * Getter the Promote char
     * @return char promote
     */
    public char getPromote() {
        return this.promote;
    }

}

