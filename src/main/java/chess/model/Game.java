package chess.model;

import java.util.*;
import chess.Attributes;
import chess.controller.Controller;
import chess.controller.Move;
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
    private List<View> observers = new ArrayList<>();

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
     * Promoted character
     */
    protected static char charToPromote;

    /**
     * White beaten pieces
     */
    private static ArrayList<Piece> whiteBeaten = new ArrayList<>();

    /**
     * Black beaten pieces
     */
    private static ArrayList<Piece> blackBeaten = new ArrayList<>();

    /**
     * The allowed move of the player
     */
    private Move allowedMove;



    /**
     * The constructor of the game class. it creates a new game instance with the given Controller
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
        // Notify the observer at the start of the game
        notifyObservers();
        // Loading the players pieces
        loadPlayerPieces();

        // Loop to the game
        while (!FINISHED) {
            // Get the input from the player and analyze it
            controller.processInputFromPlayer();

            // Checking the status of the game after each move
            checkGameStatus();

            // Switch the player
            currentPlayer = getOpponent(currentPlayer);

            // And then notify the observer
            notifyObservers();
        }
    }

    /**
     * Checking if the gives move is allowed by the game
     * @param move_from The position where the move is starting
     * @param move_to The position where the move is ending
     * @return True if the move is allowed, false otherwise
     */
    public boolean isMoveAllowed(int move_from, int move_to) {

        // Reset the move from the previous round
        this.allowedMove = null;

        // Get the piece that will be moved
        Piece piece = board.getPiece(move_from);

        // If there is no such piece, i.e. the player chooses an empty square,
        // then ask for a new input
        if(piece == null) {
            return false;
        }

        // Check if the player chooses his own piece
        if(!(piece.getColor() == currentPlayer.getColor())) {
            return false;
        }

        //Calculate the selected piece legal moves
        piece.calculateLegalMoves();

        //Iterate through the moves
        for (Move move : piece.getAllLegalMoves()) {
            if(move.getDestination() == move_to) {
                this.allowedMove = move;
                break;
            }
        }
        // If no such move is found, then ask for another input
        if(allowedMove == null) return false;

        // If the piece is king, then some conditions should apply
        if(piece instanceof King) {
            return ((King) piece).checkForEnemyKing(move_to) && isStillInCheck(piece, move_from, move_to);
        }

        // Is the player's king in check, then make sure the piece
        // can move only to uncheck the king
        if(currentPlayer.isKingInCheck()) {
            return isStillInCheck(piece, move_from, move_to);
        }

        // Check if the piece moved, the king is still safe
        // If everything is by the roles make the move
        return !canThreatenKing(move_from, move_to);
    }

    /**
     * Makes sure that if the player makes the move, the king will still be save
     * @param piece The piece to move
     * @param move_from The from position of the move
     * @param move_to The to position of the move
     * @return true if still the king in check after the move is preformed, false otherwise
     */
    private boolean isStillInCheck(Piece piece, int move_from, int move_to) {
        boolean inCheck = false;

        // Make temporarily adjustments to see if the check still exist
        Piece toPiece = board.getPiecesOnBoard().set(move_to, piece);
        Piece fromPiece = board.getPiecesOnBoard().set(move_from, null);
        fromPiece.setPosition(move_to);
        board.setPiece(fromPiece);
        if(toPiece != null) getOpponent(currentPlayer).removeFromPlayersPieces(toPiece);

        // Is still the king in check after the move is preformed, then return true
        if(!currentPlayer.isKingInCheck()) {
            inCheck = true;
        }

        // Reset the temporary adjustment made before
        board.getPiecesOnBoard().set(move_to, toPiece);
        board.getPiecesOnBoard().set(move_from, fromPiece);
        fromPiece.setPosition(move_from);
        board.setPiece(fromPiece);
        if(toPiece != null) getOpponent(currentPlayer).addToPlayersPieces(toPiece);
        return inCheck;
    }

    /**
     * Check if the piece can threaten its own king if it moves
     * @return true if the king can be threatened, false otherwise
     */
    private boolean canThreatenKing(int move_from, int move_to) {
        // Make temporarily adjustments to see if the check still exist
        Piece fromPiece = board.getPiecesOnBoard().set(move_from, null);
        Piece toPiece = board.getPiecesOnBoard().set(move_to, fromPiece);
        if(toPiece != null) getOpponent(currentPlayer).removeFromPlayersPieces(toPiece);

        // Is still the king in check after the move is preformed, then return true
        if(currentPlayer.isKingInCheck()) {
            // Reset the temporary adjustment made before
            board.getPiecesOnBoard().set(move_to, toPiece);
            board.getPiecesOnBoard().set(move_from, fromPiece);
            if(toPiece != null) getOpponent(currentPlayer).addToPlayersPieces(toPiece);
            return true;
        }

        // Reset the temporary adjustment made before
        board.getPiecesOnBoard().set(move_to, toPiece);
        board.getPiecesOnBoard().set(move_from, fromPiece);
        if(toPiece != null) getOpponent(currentPlayer).addToPlayersPieces(toPiece);
        return false;
    }

    /**
     * Check the status of the game at each round
     */
    private void checkGameStatus() {
        if(getOpponent(currentPlayer).isKingInCheck()) {
            controller.notifyView(Attributes.GameStatus.KING_IN_CHECK, getOpponent(currentPlayer));
        }
        if(hasGameEnded(getOpponent(currentPlayer))) {
            controller.notifyView(Attributes.GameStatus.ENDED, currentPlayer);
            FINISHED = true;
        }
    }

    /**
     * Checks if the player has no moves left and thus he lost the game
     * @param player The player that might have lost
     * @return true if the player has no moves left, false otherwise
     */
    private boolean hasGameEnded(Player player) {
        return player.calculatePlayerMoves().isEmpty();
    }

    /**
     * Loading the players pieces
     */
    protected static void loadPlayerPieces() {
        for (Piece piece : board.getPiecesOnBoard()) {
            if(piece == null) continue;
            else {
                if (piece.getColor() == playerOne.getColor()){
                    playerOne.addToPlayersPieces(piece);
                } else playerTwo.addToPlayersPieces(piece);
            }
        }
    }

    /**
     * Add the captured piece to the list of the player's beaten pieces
     * @param captured The captured pieces
     */
    public static void addToBeaten(Piece captured) {
        if(captured.getColor().isWhite()) {
            whiteBeaten.add(captured);
        } else blackBeaten.add(captured);

        //And delete the piece from the players available pieces
        getOpponent(currentPlayer).removeFromPlayersPieces(captured);
    }

    /**
     * Getter for the Controller
     * @return controller The controller of this game
     */
    public Controller getController() {
        return controller;
    }

    /**
     * Getter for the current player of the game
     * @return the currentPlayer
     */
    public static Player getCurrentPlayer() {
        return currentPlayer;
    }

    /**
     * Getting the opponent of the current player
     * @param currentPlayer The current player of the game
     */
    public static Player getOpponent(Player currentPlayer) {
        return currentPlayer.getColor() == playerOne.getColor() ? playerTwo : playerOne;
    }

    /**
     * Getter for the board of the game
     * @return the board
     */
    public static Board getBoard() {
        return board;
    }

    /**
     * Getter for the allowed move
     * @return
     */
    public Move getAllowedMove() {
        return this.allowedMove;
    }

    /**
     * Getter for the white beaten pieces
     * @return white beaten pieces
     */
    public static ArrayList<Piece> getWhiteBeaten() {
        return whiteBeaten;
    }

    /**
     * Getter for the black beaten pieces
     * @return black beaten pieces
     */
    public static ArrayList<Piece> getBlackBeaten() {
        return blackBeaten;
    }

    /**
     * @param charToPromote the charToPromote to set
     */
    public void setCharToPromote(char charToPromote) {
        Game.charToPromote = charToPromote;
    }

}