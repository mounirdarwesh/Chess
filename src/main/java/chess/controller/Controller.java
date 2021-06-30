package chess.controller;

import chess.Attributes;
import chess.model.Game;
import chess.model.MapBoard;
import chess.model.Piece;
import chess.model.Player;
import chess.pgn.FenUtilities;
import chess.view.View;
import javafx.scene.Node;
import javafx.scene.control.Label;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * An abstract class of the controller
 *
 * @author Gr.45
 */
public abstract class Controller {

    /**
     * The game as model that is connected to this controller
     */
    protected Game game;

    /**
     * The CLI view that is connected to this controller
     */
    protected View view;

    /**
     * A board mapper, to map each input to its respective index
     */
    protected static final MapBoard MAPPER = new MapBoard();

    /**
     * Pattern to verify the syntax of the User Input
     */
    protected static final Pattern VALID_INPUT = Pattern.compile("([a-h][1-8])([-])([a-h][1-8])([QRNBqrnb]?)", Pattern.CASE_INSENSITIVE);

    /**
     *
     */
    protected List<String> redidFENs;

    /**
     * The mode of the game
     */
    protected Attributes.GameMode gameMode;

    /**
     *
     */
    protected List<Node> redidHistory;


    /**
     *
     */
    protected boolean historyCleared = false;

    /**
     *
     */
    protected Piece beatenPieces;

    /**
     * The constructor expects a CLI view to construct itself.
     *
     * @param view The view that is connected to this controller
     */
    public Controller(View view) {
        this.view = view;
    }

    /**
     * Processing the input from the player
     */
    public abstract void processInputFromPlayer();

    /**
     * Returning the beaten pieces of the player
     *
     * @return beaten pieces of the player
     */
    public List<Piece> getBeatenPieces() {
        return game.getCurrentPlayer().getColor().isWhite() ?
                game.getWhitePlayer().getBeaten() : game.getBlackPlayer().getBeaten();
    }

    /**
     * Method to notify the user of the status of the game
     *
     * @param status The status of the game
     * @param player The player
     */
    public abstract void notifyView(Attributes.GameStatus status, Player player);

    /**
     * Here where we calculate the position where the piece should reside
     *
     * @param input The input form the player
     * @return The index of the selected piece
     */
    public int getMoveFromPosition(String input) {
        Matcher matcher = VALID_INPUT.matcher(input);
        matcher.matches();
        String fromIn = matcher.group(1);
        return MAPPER.map(fromIn);
    }

    /**
     * Here where we calculate the position to where the piece should move
     *
     * @param input The input form the player
     * @return The index of the destination
     */
    public int getMoveToPosition(String input) {
        Matcher matcher = VALID_INPUT.matcher(input);
        matcher.matches();
        String toIn = matcher.group(3);
        return MAPPER.map(toIn);
    }

    /**
     *
     */
    public void undoMove() {
        int fullFENsSize = game.getGameFENStrings().size();
        if (fullFENsSize <= 2 || Game.getCurrentPlayer().hasPlayerUndidAMove()) {
            return;
        }
        redidFENs = new ArrayList<>();
        redidFENs.add(
                game.getGameFENStrings().remove(game.getGameFENStrings().size() - 2)
        );
        redidFENs.add(
                game.getGameFENStrings().remove(game.getGameFENStrings().size() - 1)
        );
        Game.getBoard().setPiecesOnBoard(game.getGameFENStrings().get(
                game.getGameFENStrings().size() - 1
        ));
        Game.getCurrentPlayer().setHasPlayerUndidAMove(true);
        game.notifyObservers();

    }

    /**
     *
     */
    public void redoMove() {
        if (!Game.getCurrentPlayer().hasPlayerUndidAMove()) {
            return;
        }
        game.getGameFENStrings().addAll(redidFENs);
        Game.getCurrentPlayer().setHasPlayerUndidAMove(false);
        Game.getBoard().setPiecesOnBoard(game.getGameFENStrings().get(
                game.getGameFENStrings().size() - 1));
        game.notifyObservers();
    }

    /**
     * @return
     */
    public boolean isHistoryCleared() {
        return historyCleared;
    }

    /**
     * @return
     */
    public void setHistoryCleared(boolean historyCleared) {
        this.historyCleared = historyCleared;
    }

    public abstract void showTime(String time);
}
