package chess.controller;

import chess.Attributes;
import chess.model.game.Game;
import chess.model.pieces.Piece;
import chess.model.player.Player;
import chess.view.View;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * abstract controller class
 * @author Gr.45
 */
public abstract class Controller {

    /**
     * The game as model that is connected to this controller
     */
    protected Game game;

    /**
     * The view that is connected to this controller
     */
    public View view;

    /**
     * List for the deleted Beaten Pieces
     */
    protected List<Piece> deletedBeatenPieces = new ArrayList<>();

    /**
     * The settings string, where 1 is for enabling a setting and 0 for disabling
     * 1.Str = 1:White, 0:Black
     * 2.Str = undo/redo
     * 3.Str = time (0 disable, number for duration)
     * 4.Str = LAN Mode
     * 5.Str = AI Mode
     * 6.Str = Board Rotation
     * 7.Str Reselection of piece
     * 8.Str Notification
     * 9.Str Highlighting
     */
    protected String[] gameSettings = {"1","1","0","0","0","0","1","1","1"};

    /**
     * A List of the Undid moves
     */
    protected List<Move> undidMoves = new ArrayList<>();

    /**
     * The getter for the game settings
     * @return the settings of the game
     */
    public String[] getGameSettings() {
        return gameSettings;
    }

    /**
     * Returning the beaten pieces of the player
     * @return beaten pieces of the player
     */
    public List<Piece> getBeatenPieces() {
        return game.getCurrentPlayer().getColor().isWhite() ?
                game.getWhitePlayer().getBeaten() : game.getBlackPlayer().getBeaten();
    }

    /**
     * convert the millisecond to propre Countdown
     * @param leftTime in millisecond
     * @return in minutes Format
     */
    public String convertTime(long leftTime) {
        DateFormat simple = new SimpleDateFormat("mm:ss");
        Date result = new Date(leftTime);
        return simple.format(result);
    }

    /**
     * Undo a Move
     * @param index the index
     */
    public void undoMove(int index) {
        if(game.getAllListOfMoves().isEmpty()) return;
        if(!undidMoves.isEmpty()) undidMoves.clear();
        undidMoves.addAll(game.getAllListOfMoves().subList(
                index, game.getAllListOfMoves().size()
        ));
        for (Move toUndoMove : undidMoves) {
            toUndoMove.undo();
            game.getAllListOfMoves().remove(toUndoMove);
        }
        game.setCurrentPlayer(game.getOpponent());
        game.notifyObservers();
    }

    /**
     * remove the beaten piece of the board
     * @param piece piece of the game
     */
    protected void removeBeatenPiece(Piece piece) {
        if(piece.getColor().isWhite()) {
            deletedBeatenPieces.add(
                    game.getWhitePlayer().getBeaten().remove(
                            game.getBlackPlayer().getBeaten().size()-1
                    )
            );
        } else {
            deletedBeatenPieces.add(
                    game.getBlackPlayer().getBeaten().remove(
                            game.getBlackPlayer().getBeaten().size()-1
                    )
            );
        }
    }

    /**
     * redo a Move
     * @param index  the index of the move
     */
    public void redoMove(int index) {
        if(undidMoves.isEmpty()) return;
        for(int i = 0; i<index; i++) {
            Move undidMove = undidMoves.get(i);
            undidMove.execute();
            game.getAllListOfMoves().add(undidMove);
        }
        undidMoves.subList(0, index).clear();
        game.setCurrentPlayer(game.getOpponent());
        game.notifyObservers();
    }

    /**
     * re-add beaten Pieces in case of redo
     */
    protected void reAddBeatenPiece() {
        if(deletedBeatenPieces.isEmpty()) return;
        for (Piece deletedBeaten : deletedBeatenPieces) {
            if(deletedBeaten.getColor().isWhite()
                    && !game.getWhitePlayer().getBeaten().contains(deletedBeaten)) {
                game.getWhitePlayer().getBeaten().add(deletedBeaten);
            }
            if(deletedBeaten.getColor().isBlack()
                    && !game.getBlackPlayer().getBeaten().contains(deletedBeaten)) {
                game.getBlackPlayer().getBeaten().add(deletedBeaten);
            }
        }
        deletedBeatenPieces.clear();
    }

    /**
     * A method to get the specified game Setting
     * @param index the index of the specified setting
     * @param str the new setting to replace
     */
    public void setGameSettings(int index, String str) {
        gameSettings[index] = str;
    }

    /**
     * Getter for the View
     * @return View
     */
    public View getView() {
        return view;
    }

    /**
     * getter of the game
     * @return Game
     */
    public Game getGame() {
        return game;
    }

    /**
     * setter of the game
     * @param game game
     */
    public void setGame(Game game) {
        this.game = game;
    }

    /**
     * Notify the Player over the Status of the Game
     * @param kingInCheck status of the game
     * @param currentPlayer the Current Player
     */
    public abstract void notifyView(Attributes.GameStatus kingInCheck, Player currentPlayer);

}
