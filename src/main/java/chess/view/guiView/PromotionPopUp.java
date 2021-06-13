package chess.view.guiView;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class PromotionPopUp {
    /**
     * Promotion char
     */
    public static char promote = 'q';
    /**
     * the PopUp window
     */
    static Stage promotion;

    /**
     * configure the PopUP with the Figures.
     */
    public static void displayPopUp() {
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
        rook.setOnMouseClicked(event -> setPromoteChar('r'));
        knight.setOnMouseClicked(event -> setPromoteChar('n'));
        queen.setOnMouseClicked(event -> setPromoteChar('q'));
        bishop.setOnMouseClicked(event -> setPromoteChar('b'));

        HBox promotedPieces = new HBox();
        promotedPieces.setSpacing(10);
        promotedPieces.getChildren().addAll(rook, knight, bishop, queen);
        promotedPieces.setAlignment(Pos.CENTER);

        promotion.setScene(new Scene(promotedPieces, 400, 150));
        promotion.showAndWait();
    }

    /**
     * set the right char to Promote
     *
     * @param promoted the desired char
     */
    public static void setPromoteChar(char promoted) {
        promote = promoted;
        promotion.close();
    }

}
