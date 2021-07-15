package chess.controller.guicontroller;

import chess.Attributes;
import chess.controller.Move;
import chess.model.pieces.Pawn;
import chess.model.pieces.Piece;
import chess.model.player.Player;
import chess.view.gui.SettingsView;
import chess.view.gui.gameview.BoardView;
import chess.view.gui.gameview.GameView;
import chess.view.gui.startmenuview.StartMenuView;
import javafx.event.Event;
import javafx.event.EventTarget;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.List;

/**
 * class of the game view controller of Gui
 *
 * @author Gr.45
 */
public class GameViewController extends GuiController {

    /**
     * The view that is connected to this controller
     */
    private GameView gameView;

    /**
     * The piece that player clicked to make a move
     */
    private Piece pieceToMove = null;

    /**
     * List of the the highlighted pieces
     */
    private List<BoardView.Tile> allTilesHighlighted = new ArrayList<>();

    /**
     * Controller of view of the game
     *
     * @param gameView view of the game
     */
    public GameViewController(GameView gameView) {
        this.gameView = gameView;

        //Assigning the Listeners
        assignActionListener();
    }

    /**
     *
     */
    private void assignActionListener() {
        gameView.getReturnToStartMenu().setOnAction(this);
        gameView.getGameMenuSettings().setOnAction(this);
    }

    /**
     * assign Action Listener
     */
    public void assignActionListenerToGameComponents() {
        gameView.getBoardView().assignControllerToTileAction(this);
        gameView.getHistoryView().getUndo().setOnAction(this);
        gameView.getHistoryView().getRedo().setOnAction(this);
    }

    /**
     * handle event of the game such as undo, redo
     *
     * @param event handle click of options
     */
    @Override
    public void handle(Event event) {
        if (gameView.getReturnToStartMenu().equals(event.getSource())) {
            // Create a new Instance of the start menu view
            StartMenuView startMenuView = new StartMenuView(gameView.getParent());
            // And assign back its controller
            new StartMenuController(startMenuView);
            setScene(gameView.asScene(), startMenuView.asScene());
        } else if (gameView.getGameMenuSettings().equals(event.getSource())) {
            SettingsView settingsView = new SettingsView(gameView.getParent());
            setScene(gameView.asScene(), settingsView.asScene());
            assignSettingsActionHandler(settingsView);
        } else if (gameView.getHistoryView().getUndo().equals(event.getSource())) {
            if (game.isFINISHED()) return;
            game.getCurrentPlayer().setHasPlayerUndidAMove(true);
            game.getController().undoMove(game.getAllListOfMoves().size() - 1);
            gameView.getHistoryView().grayOutOneUndidMove();
        } else if (gameView.getHistoryView().getRedo().equals(event.getSource())) {
            if (game.isFINISHED()) return;
            game.getCurrentPlayer().setHasPlayerRedidAMove(true);
            game.getController().redoMove(1);
        } else {
            assignActionHandlerToClickOnTile(event);
        }
    }

    /**
     * handels action on setting such as enable highlights
     *
     * @param settingsView setting of the game
     */
    private void assignSettingsActionHandler(SettingsView settingsView) {
        String yes = "Yes";
        settingsView.getBackToGameViewButton().setOnAction(e -> {
            setScene(settingsView.asScene(), gameView.asScene());
        });

        settingsView.getConfirmSettingsButton().setOnAction(e -> {
            setScene(settingsView.asScene(), gameView.asScene());
        });

        settingsView.getEnableHighlightingSetting().setOnAction(e -> {
            setGameSettings(8,
                    settingsView.getEnableHighlightingSetting()
                            .getValue().equals(yes) ? "1" : "0");
        });

        settingsView.getEnableNotificationSetting().setOnAction(e -> {
            setGameSettings(7,
                    settingsView.getEnableNotificationSetting()
                            .getValue().equals(yes) ? "1" : "0");
        });

        settingsView.getEnableReselectionSetting().setOnAction(e -> {
            setGameSettings(6,
                    settingsView.getEnableReselectionSetting()
                            .getValue().equals(yes) ? "1" : "0");
        });

        settingsView.getEnableRotationSetting().setOnAction(e -> {
            setGameSettings(5,
                    settingsView.getEnableRotationSetting()
                            .getValue().equals(yes) ? "1" : "0");
        });
    }


    /**
     * This method will be invoked as soon as the player
     * clicks on a tile
     *
     * @param event tile click
     */
    private void assignActionHandlerToClickOnTile(Event event) {
        BoardView.Tile tile = (BoardView.Tile) event.getSource();
        Piece clickedPiece = game.getBoard().getPiece(tile.getID());

        // Move to that tile
        movingToTile(event, clickedPiece, tile);

        if (!gameView.getBoardView().getHighlightedTiles().isEmpty()) {
            deHighlightAllLegalTiles(gameView.getBoardView().getHighlightedTiles());
        }
        // Check Exceptions
        if (clickedOnEmptyWithNoPieceToMove(event.getTarget())
                || reselectAClickedPieceIsNotAllowed()
                || clickedOnEnemyPiece(clickedPiece)
                || game.isFINISHED()) {
            return;
        }
        if (highlightingExceptionCases(clickedPiece)) {
            highlightAllLegalTiles(clickedPiece);
        }
        // Update the piece to be the piece the player wants to move
        pieceToMove = clickedPiece;

    }

    /**
     * The exception to highlight
     *
     * @param clickedPiece the clicked piece
     * @return true if everything is ok
     */
    private boolean highlightingExceptionCases(Piece clickedPiece) {
        return gameSettings[8].equals("1")
                && allTilesHighlighted.isEmpty()
                && !clickedOnEnemyPiece(clickedPiece)
                && clickedPiece != null;
    }

    /**
     * Handling the action to move to that tile
     *
     * @param event        the event
     * @param clickedPiece the clicked piece
     * @param tile         the tile
     */
    private void movingToTile(Event event, Piece clickedPiece, BoardView.Tile tile) {
        if (clickedOnEmptyTileToMoveAPiece(event.getTarget())
                || clickedOnEnemyPieceToCapture(clickedPiece)) {
            checkForPromotion(pieceToMove);
            game.processMoveFromPlayer(pieceToMove, tile.getID());
            pieceToMove = null;
        }
    }

    /**
     * check if the Pawn in Position where he can Promote
     *
     * @param possiblePawnPromoted the Pawn to Promote
     */
    private void checkForPromotion(Piece possiblePawnPromoted) {
        if (possiblePawnPromoted == null) return;
        boolean whitePawnAndOnSeventhColumn = possiblePawnPromoted.getColor().isWhite()
                && possiblePawnPromoted.isOnSeventhRow();
        boolean blackPawnAndOnSecondColumn = possiblePawnPromoted.getColor().isBlack()
                && possiblePawnPromoted.inOnSecondRow();
        if (possiblePawnPromoted instanceof Pawn
                && (whitePawnAndOnSeventhColumn || blackPawnAndOnSecondColumn)) {
            gameView.showGameViewPopUp();
            char promotedChar = gameView.getPromotionPopUp().getPromote();
            game.setCharToPromote(promotedChar);
        }
    }

    /**
     * Checks if the player clicked on an empty tile with no
     * selected piece to move to that tile
     *
     * @param target the target that contains the information
     * @return true, if the player just clicks on an empty tile without
     * the intention to move a piece, false otherwise
     */
    private boolean clickedOnEmptyWithNoPieceToMove(EventTarget target) {
        return target instanceof Rectangle
                && pieceToMove == null;
    }

    /**
     * Checks if the setting if not allowing reelecting a piece
     * is enabled and the player already selected a piece
     *
     * @return if the reselection is not allowed
     */
    private boolean reselectAClickedPieceIsNotAllowed() {
        return gameSettings[6].equals("0") && pieceToMove != null;
    }

    /**
     * Checks if the player has clicked on a piece and clicked again
     * to capture an enemy piece
     *
     * @param potentialEnemyPiece The clicked piece that might be an enemy
     * @return true if the player clicked on an enemy piece to capture
     * it and has already clicked before on his own piece
     */
    private boolean clickedOnEnemyPieceToCapture(Piece potentialEnemyPiece) {
        if (potentialEnemyPiece == null) return false;
        return potentialEnemyPiece.getColor() != game.getCurrentPlayer().getColor()
                && pieceToMove != null;
    }

    /**
     * Checks if the player clicked on empty tile and has already clicked
     * before on his own piece to move to that empty tile
     *
     * @param target the target that holds the information
     * @return true if a player has made a correct click
     */
    private boolean clickedOnEmptyTileToMoveAPiece(EventTarget target) {
        return target instanceof Rectangle
                && pieceToMove != null;
    }

    /**
     * Checks if the player clicked on an enemy piece
     *
     * @param clickedPiece the piece clicked
     * @return true if the player clicked on an enemy piece
     */
    private boolean clickedOnEnemyPiece(Piece clickedPiece) {
        if (clickedPiece == null) return false;
        return game.getCurrentPlayer().getColor() != clickedPiece.getColor()
                && pieceToMove == null;
    }

    /**
     * @param highlightedTiles the highlighted tiles
     */
    private void deHighlightAllLegalTiles(List<BoardView.Tile> highlightedTiles) {
        for (BoardView.Tile tile : highlightedTiles) {
            tile.deHighlight();
        }
        allTilesHighlighted.clear();
    }

    /**
     * Highlight all the tiles that the player can move to
     *
     * @param clickedPiece the clicked piece on the board
     */
    private void highlightAllLegalTiles(Piece clickedPiece) {
        clickedPiece.calculateLegalMoves();
        for (Move move : clickedPiece.getAllLegalMoves()) {
            if (game.makeTempMoveAndCheck(move)) {
                gameView.getBoardView().getTiles().get(move.getDestination()).highlight();
                allTilesHighlighted.add(gameView.getBoardView().getTiles().get(move.getDestination()));
            }
        }
        gameView.getBoardView().setHighlightedTiles(allTilesHighlighted);
    }

    /**
     * getter of the GameView
     *
     * @return GameView
     */
    public GameView getGameView() {
        return gameView;
    }

    /**
     * Notify the Game View over the last changes
     *
     * @param status the new status
     * @param player which player has done the new changes
     */
    @Override
    public void notifyView(Attributes.GameStatus status, Player player) {
        gameView.getNotificationView().notifyPlayer(game, status, player);
    }

}
