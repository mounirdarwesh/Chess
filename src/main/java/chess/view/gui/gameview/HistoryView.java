package chess.view.gui.gameview;

import chess.controller.LANGameController;
import chess.model.game.Game;
import chess.model.game.LANGame;
import chess.model.player.AI;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import java.util.ArrayList;
import java.util.List;
/**
 * Class for viewing history moves
 * @author Gr.45
 */
public class HistoryView {

    /**
     * game
     */
    private Game game;

    /**
     * box that contains history, undo and redo
     */
    private VBox historyUndoRedoView;

    /**
     * undo button
     */
    private Button undo;

    /**
     * redo button
     */
    private Button redo;

    /**
     * history of the moves
     */
    private List<String> historyMoves = new ArrayList<>();

    /**
     * box of the historyView
     */
    private VBox historyViewContainer;

    /**
     * The undid labels
     */
    private List<Label> undidLabeled;


    /**
     * view of the history
     * @param game game
     */
    public HistoryView(Game game) {
        this.game = game;
        historyUndoRedoView = new VBox();
        historyUndoRedoView.setMinWidth(300);
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setMinHeight(574);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        // A container for the history of moves
        historyViewContainer = new VBox();
        historyViewContainer.setAlignment(Pos.CENTER);
        scrollPane.setContent(historyViewContainer);

        // A container for the undo redo buttons
        HBox redoUndoContainer = new HBox();
        redoUndoContainer.setAlignment(Pos.BOTTOM_CENTER);

        undo = new Button("Undo");
        redo = new Button("Redo");
        undo.setMinSize(110, 30);
        redo.setMinSize(110, 30);
        redoUndoContainer.getChildren().addAll(undo, redo);

        redoUndoContainer.setStyle("-fx-padding: 10 10 10 10;" +
                "-fx-spacing: 10;" +
                "-fx-border-width: 2;" +
                "-fx-border-insets: 5;" +
                "-fx-border-radius: 5;" +
                "-fx-border-color: white;");

        historyUndoRedoView.getChildren().addAll(redoUndoContainer, scrollPane);
    }

    /**
     * shows the history of the moves
     */
    public void showHistoryMoves() {
        if (game.getAllowedMove() == null) return;
        historyMoves.add(game.getAllowedMove().toString());
        if(game.getOpponent() instanceof AI) {
            addHistoryForAI();
            return;
        }
        addHistoryForPlayer();
    }

    private void addHistoryForPlayer() {
        Label history = new Label(game.getAllowedMove().toString());
        historyViewContainer.getChildren().add(history);
        history.setFont(new Font(17));
        history.setTextFill(Color.BLACK);
        history.setOnMouseClicked(event -> {
            if(game.getController().getGameSettings()[1].contains("0")) return;
            int indexOfHistoryMove = historyViewContainer.getChildren()
                    .indexOf(history);
            if(game instanceof LANGame) {
                ((LANGameController) game.getController()).undoMove(indexOfHistoryMove);
            } else {
                game.getController().undoMove(indexOfHistoryMove);
            }
            game.getCurrentPlayer().setHasPlayerUndidAMove(true);
            grayOutUndidHistoryMoves(indexOfHistoryMove);
        });

    }

    private void addHistoryForAI() {
        Label aiHistory = new Label("AI:  " + game.getAllowedMove().toString());
        aiHistory.setFont(new Font(17));
        aiHistory.setTextFill(Color.BLACK);
        historyViewContainer.getChildren().add(aiHistory);
    }

    /**
     * gray out the undid Move in the History
     */
    private void grayOutUndidHistoryMoves(int undidMoveIndex) {
        undidLabeled = new ArrayList<>();
        int index = undidMoveIndex;
        int color = index % 2 == 0 ? 0 : 1;
        while (index < historyViewContainer.getChildren().size()) {
            try {
                Label historyMove = (Label) historyViewContainer.getChildren().get(index * 2 + color);
                historyMove.setTextFill(Color.web("#A9A9A9"));
                undidLabeled.add(historyMove);
                assignActionHandlersToRedidHistory(historyMove, undidLabeled);
            } catch (Exception e) {
                break;
            }
            index++;
        }
    }

    /**
     * Graying out one undid move (The last one)
     */
    public void grayOutOneUndidMove() {
        undidLabeled = new ArrayList<>();
        Label lastHistoryMove = (Label) historyViewContainer.getChildren().get(
                historyViewContainer.getChildren().size()-1
        );
        lastHistoryMove.setTextFill(Color.web("#A9A9A9"));
        undidLabeled.add(lastHistoryMove);
        assignActionHandlersToRedidHistory(lastHistoryMove, undidLabeled);

    }

    /**
     * assigning action handlers to the undid labels
     * @param historyMove the undid label
     * @param undidLabeled list of all undid labels
     */
    private void assignActionHandlersToRedidHistory(Label historyMove, List<Label> undidLabeled) {
        historyMove.setOnMouseClicked(e -> {
            int indexOfToRedo = historyViewContainer.getChildren()
                    .indexOf(historyMove)+1;

            if(game instanceof LANGame) {
                ((LANGameController) game.getController()).redoMove(indexOfToRedo);
            }
            else {
                game.getController().redoMove(indexOfToRedo);
            }
            game.getCurrentPlayer().setHasPlayerRedidAMove(true);
            historyViewContainer.getChildren().remove(indexOfToRedo,
                    historyViewContainer.getChildren().size());
            for (Label label : undidLabeled) {
                label.setTextFill(Color.BLACK);
            }
            undidLabeled.clear();
        });
    }

    /**
     * view history undo and redo as node
     * @return VBox
     */
    public VBox asNode() {
        return historyUndoRedoView;
    }

    /**
     * getter of the undo button
     * @return Button
     */
    public Button getUndo() {
        return undo;
    }

    /**
     * getter of the redo button
     * @return Button
     */
    public Button getRedo() {
        return redo;
    }

    /**
     * Getter for the container
     * @return the vbox container
     */
    public VBox getHistoryUndoRedoView() {
        return historyUndoRedoView;
    }
}
