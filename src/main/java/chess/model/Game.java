package chess.model;

import java.util.*;

import chess.controller.Controller;
import chess.player.Player;
import chess.view.View;

/**
 * @author Ahmad Mohammad
 * The class that controls the game
 */
public class Game {

    /**
     * The controller of the game
     */
    private Controller controller;

    /**
     * A list of observer objects
     */
    private List<View> observers = new ArrayList<View>();

    /**
     * The first player
     */
    private Player playerOne;

    /**
     * The second player
     */
    private Player playerTwo;

    /**
     * The current player of the game
     */
    private Player currentPlayer;

    /**
     * The board to the game
     */
    private Board board;

    /**
     * The status of the game
     */
    private static boolean FINISHED = false;

    /**
     * Promoted character
     */
    protected static char charToPromote;


    /**
     * The constructor of the game class. it creates a new game instance with the given Controller
     *
     * @param controller The controller to manage this instance (MVC-patter)
     * @param playerOne  The first player of the game
     * @param playerTwo  The opponent
     * @param board      The board of the game
     */
    public Game(Controller controller, Board board, Player playerOne, Player playerTwo) {
        this.controller = controller;
        this.board = board;
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
        this.currentPlayer = this.playerOne;
    }

    /**
     * Getter for the Controller
     *
     * @return controller The controller of this game
     */
    public Controller getController() {
        return controller;
    }

    /// ----------- MVC ----------------- ///
    public void addObserver(View observer) {
        observers.add(observer);
    }

    public void removeObserver(View observer) {
        observers.remove(observer);
    }

    public void notifyObservers() {
        for (View observer : observers)
            observer.modelChanged(this);
    }
    /// ------------------------------- ///


    /**
     * The brain of the game
     */
    public void run() {
        notifyObservers();

        while (!FINISHED) {
            controller.processtInputFromPlayer();
            notifyObservers();
            currentPlayer = getOpponent(currentPlayer);
        }
    }


    /**
     * Getter for the first player
     *
     * @return the player_one
     */
    public Player getPlayerOne() {
        return playerOne;
    }

    /**
     * Getter for the second player
     *
     * @return the player_two
     */
    public Player getPlayerTwo() {
        return playerTwo;
    }


    /**
     * Getter for the current player of the game
     *
     * @return the currentPlayer
     */
    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    /**
     * Getting the opponent of the current player
     *
     * @param currentPlayer The current player of the game
     */
    public Player getOpponent(Player currentPlayer) {
        return this.currentPlayer.getColor() == this.playerOne.getColor() ? this.playerTwo : this.playerOne;
    }

    /**
     * @return the board
     */
    public Board getBoard() {
        return board;
    }

    /**
     * @param charToPromote the charToPromote to set
     */
    public void setCharToPromote(char charToPromote) {
        Game.charToPromote = charToPromote;
    }

}