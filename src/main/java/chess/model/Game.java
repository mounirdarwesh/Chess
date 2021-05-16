package chess.model;

import java.util.*;

import chess.Attributes;
import chess.controller.Controller;
import chess.controller.Move;
import chess.view.View;

/**
 * @author Gruppe 45
 * The class that controls the game
 */
public class Game {

    /**
     * Promoted character
     */
    protected static char charToPromote;
    /**
     * The first player
     */
    private static Player playerOne;
    /**
     * The second player
     */
    private static Player playerTwo;
    /**
     * The current player of the game
     */
    private static Player currentPlayer;
    /**
     * The board to the game
     */
    private static Board board;
    /**
     * The status of the game
     */
    private static boolean FINISHED = false;
    /**
     * The controller of the game
     */
    private Controller controller;
    /**
     * A list of observer objects
     */
    private List<View> observers = new ArrayList<>();
    /**
     * The allowed move of the player
     */
    private Move allowedMove;

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
        Game.board = board;
        Game.playerOne = playerOne;
        Game.playerTwo = playerTwo;
        Game.currentPlayer = Game.playerOne;
    }

    /// ----------- MVC ----------------- ///

    /**
     * add Observer to view
     *
     * @param observer to observe the view
     */
    public void addObserver(View observer) {
        observers.add(observer);
    }

    /**
     * remove Observer form View
     *
     * @param observer that should be removed
     */
    public void removeObserver(View observer) {
        observers.remove(observer);
    }

    /**
     * tell the observer that something is changed.
     */
    public void notifyObservers() {
        for (View observer : observers) {
            observer.modelChanged(this);
        }
    }
    /// ------------------------------- ///

    /**
     * Loading the players pieces
     */
    protected static void loadPlayerPieces() {
        for (Piece piece : board.getPiecesOnBoard()) {
            if (piece == null) {
                continue;
            } else {
                if (piece.getColor() == playerOne.getColor()) {
                    playerOne.addToPlayersPieces(piece);
                } else {
                    playerTwo.addToPlayersPieces(piece);
                }
            }
        }
    }

    /**
     * Add the captured piece to the list of the player's beaten pieces
     *
     * @param captured The captured pieces
     */
    public static void addToBeaten(Piece captured) {
        if (captured.getColor().isWhite()) {
            playerOne.getBeaten().add(captured);
        } else {
            playerTwo.getBeaten().add(captured);
        }

        //And delete the piece from the players available pieces
        getOpponent(currentPlayer).removeFromPlayersPieces(captured);
    }

    /**
     * Remove the captured piece to the list of the player's beaten pieces
     *
     * @param captured The captured pieces
     */
    public static void removeFromBeaten(Piece captured) {
        if (captured.getColor().isWhite()) {
            playerOne.getBeaten().remove(captured);
        } else {
            playerTwo.getBeaten().remove(captured);
        }

        //And delete the piece from the players available pieces
        getOpponent(currentPlayer).addToPlayersPieces(captured);

    }

    /**
     * The brain of the game
     */
    public void run() {
        // Notify the observer at the start of the game
        notifyObservers();
        // Loading the players pieces
        loadPlayerPieces();

        // Loop to the game
        while (!FINISHED) {
            // Get the input from the player and analyze it
            controller.processInputFromPlayer();

            // Switch the player
            currentPlayer = getOpponent(currentPlayer);

            // And then notify the observer
            notifyObservers();

            // Checking the status of the game after each move
            checkGameStatus();
        }
    }

    /**
     * Checking if the gives move is allowed by the game
     *
     * @param move_from The position where the move is starting
     * @param move_to   The position where the move is ending
     * @return True if the move is allowed, false otherwise
     */
    public boolean isMoveAllowed(int move_from, int move_to) {

        // Will the move be allowed?
        boolean allow;

        // Reset the move from the previous round
        this.allowedMove = null;

        // Get the piece that will be moved
        Piece piece = board.getPiece(move_from);

        // If there is no such piece, i.e. the player chooses an empty square,
        // then ask for a new input
        if (piece == null) {
            return false;
        }

        // Check if the player chooses his own piece
        if (!(piece.getColor() == currentPlayer.getColor())) {
            return false;
        }

        //Calculate the selected piece legal moves
        piece.calculateLegalMoves();

        //Iterate through the moves
        for (Move move : piece.getAllLegalMoves()) {
            if (move.getDestination() == move_to) {
                this.allowedMove = move;
                break;
            }
        }
        // If no such move is found, then ask for another input
        if (allowedMove == null) return false;

        // If the current player's king is in check or might be in
        // check if the move was executed, then preform the move
        // temporarily and check if it saves or protects the King
        allowedMove.execute();
        allow = !currentPlayer.isKingInCheck();
        allowedMove.undo();

        return allow;
    }

    /**
     * Check the status of the game at each round
     */
    public void checkGameStatus() {
        if (currentPlayer.isKingInCheck()) {
            controller.notifyView(Attributes.GameStatus.KING_IN_CHECK, currentPlayer);
        }
        if (hasGameEnded(currentPlayer)) {
            controller.notifyView(Attributes.GameStatus.ENDED, getOpponent(currentPlayer));
            FINISHED = true;
        }
    }

    /**
     * Checks if the player has no moves left and thus he lost the game
     * or the King is in Checkmate.
     *
     * @param player The player that might have lost
     * @return true if the player has no moves left, false otherwise
     */
    private boolean hasGameEnded(Player player) {
        return player.checkMate();
    }

    /**
     * Getter for the current player of the game
     *
     * @return the currentPlayer
     */
    public static Player getCurrentPlayer() {
        return currentPlayer;
    }

    /**
     * Getting the opponent of the current player
     *
     * @param player The current player of the game
     */
    public static Player getOpponent(Player player) {
        return player.getColor() == playerOne.getColor() ? playerTwo : playerOne;
    }

    /**
     * Getter for the board of the game
     *
     * @return the board
     */
    public static Board getBoard() {
        return board;
    }

    /**
     * Getter for the Controller
     *
     * @return controller The controller of this game
     */
    public Controller getController() {
        return controller;
    }

    /**
     * Getter for the allowed move
     *
     * @return if the Move is allowed.
     */
    public Move getAllowedMove() {
        return this.allowedMove;
    }

    /**
     * @param charToPromote the charToPromote to set
     */
    public void setCharToPromote(char charToPromote) {
        Game.charToPromote = charToPromote;
    }

    /**
     * to set game Status, for the sake of Tests.
     *
     * @param FINISHED Game Status
     */
    public void setFINISHED(boolean FINISHED) {
        Game.FINISHED = FINISHED;
    }

    /**
     * get playerOne
     *
     * @return playerOne
     */
    public static Player getPlayerOne() {
        return playerOne;
    }

    /**
     * get playerTwo
     *
     * @return playerTwo
     */
    public static Player getPlayerTwo() {
        return playerTwo;
    }
}