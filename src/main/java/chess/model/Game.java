package chess.model;

import chess.Attributes;
import chess.controller.ChessClock;
import chess.controller.Controller;
import chess.controller.Move;
import chess.pgn.FenUtilities;
import chess.util.Observable;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Gruppe 45
 * The class that controls the game
 */
public class Game extends Observable {

    /**
     * Promoted character
     */
    protected static char charToPromote;
    /**
     * The first player
     */
    private static Player whitePlayer;
    /**
     * The second player
     */
    private static Player blackPlayer;
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
     * The allowed move of the player
     */
    private Move allowedMove;

    /**
     * The EnPassant pawn
     */
    private static Piece enPassantPawn;


    /**
     * List of all the game's FEN Strings
     */
    private List<String> gameFENStrings;


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
        this.gameFENStrings = new ArrayList<>();
        Game.board = board;
        Game.whitePlayer = playerOne.getColor().isWhite() ? playerOne : playerTwo;
        Game.blackPlayer = playerTwo.getColor().isBlack() ? playerTwo : playerOne;
        Game.currentPlayer = Game.whitePlayer;
    }


    /**
     * Loading the players pieces
     */
    public static void loadPlayerPieces() {
        for (Piece piece : board.getPiecesOnBoard()) {
            if (piece == null) {
                continue;
            } else {
                if (piece.getColor().isWhite()) {
                    whitePlayer.addToPlayersPieces(piece);
                } else {
                    blackPlayer.addToPlayersPieces(piece);
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
            whitePlayer.getBeaten().add(captured);
        } else {
            blackPlayer.getBeaten().add(captured);
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
            whitePlayer.getBeaten().remove(captured);
        } else {
            blackPlayer.getBeaten().remove(captured);
        }

        //And delete the piece from the players available pieces
        getOpponent(currentPlayer).addToPlayersPieces(captured);

    }

    /**
     * Make a temporary move and then check if it affects the players king
     *
     * @param allowedMove The temporary move
     * @return true if there is no threat to the players king, false otherwise
     */
    public static boolean makeTempMoveAndCheck(Move allowedMove) {
        boolean allowAfterTempMove;
        allowedMove.execute();
        allowAfterTempMove = !currentPlayer.isKingInCheck();
        allowedMove.undo();
        return allowAfterTempMove;
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
     * setter for the Current Player
     *
     * @param currentPlayer Current Player
     */
    public static void setCurrentPlayer(Player currentPlayer) {
        Game.currentPlayer = currentPlayer;
    }

    /**
     * Getting the opponent of the current player
     *
     * @param player The current player of the game
     */
    public static Player getOpponent(Player player) {
        return player.getColor().isWhite() ? blackPlayer : whitePlayer;
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
     * get playerOne
     *
     * @return playerOne
     */
    public static Player getWhitePlayer() {
        return whitePlayer;
    }

    /**
     * get playerTwo
     *
     * @return playerTwo
     */
    public static Player getBlackPlayer() {
        return blackPlayer;
    }

    /**
     * The brain of the game
     */
    public void run() {
        gameFENStrings.add("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR");
        // Notify the observer at the start of the game
        notifyObservers();
        // Loading the players pieces
        loadPlayerPieces();

        //Loop to the game
        while (!FINISHED) {
            // Get the input from the player and analyze it
            controller.processInputFromPlayer();

            gameFENStrings.add(FenUtilities.loadFENFromBoard(board));

            // Switch the player
            currentPlayer = getOpponent(currentPlayer);

            // And then notify the observer
            notifyObservers();

            // Checking the status of the game after each move
            checkGameStatus();
        }

        // End the game when we are out of the loop
        //System.exit(1);
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
        allow = makeTempMoveAndCheck(allowedMove);

        return allow;
    }

    /**
     * Check the status of the game at each round
     */
    public void checkGameStatus() {
        if (hasGameEndedInWin()) {
            controller.notifyView(Attributes.GameStatus.ENDED_IN_WIN, getOpponent(currentPlayer));
            FINISHED = true;
            return;
        }
        if (hasGameEndedInDraw()) {
            controller.notifyView(Attributes.GameStatus.ENDED_IN_DRAW, currentPlayer);
            FINISHED = true;
            return;
        }
        if (currentPlayer.isKingInCheck()) {
            controller.notifyView(Attributes.GameStatus.KING_IN_CHECK, currentPlayer);
        }
    }

    /**
     * Checks if the player has no moves left and thus he lost the game
     * or the King is in Checkmate.
     *
     * @return true if the player has no moves left, false otherwise
     */
    private boolean hasGameEndedInWin() {
        return currentPlayer.isCheckMate();
    }

    /**
     * Check if the game has ended with no winner
     *
     * @return true if the game has ended in a draw, false otherwise
     */
    private boolean hasGameEndedInDraw() {
        // First check if the player has any legal moves
        for (Move move : currentPlayer.calculatePlayerMoves()) {
            if (makeTempMoveAndCheck(move)) {
                return false;
            }
        }
        // If no legal move is found, then check if the king is in check
        return !currentPlayer.isKingInCheck();
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
     * @return
     */
    public static Piece getEnPassantPawn() {
        return enPassantPawn;
    }

    /**
     * @param enPassantPawn
     */
    public static void setEnPassantPawn(Piece enPassantPawn) {
        Game.enPassantPawn = enPassantPawn;
    }

    /**
     * @param charToPromote the charToPromote to set
     */
    public static void setCharToPromote(char charToPromote) {
        Game.charToPromote = charToPromote;
    }

    /**
     * to set game Status, for the sake of Tests.
     *
     * @param FINISHED Game Status
     */
    public static void setFINISHED(boolean FINISHED) {
        Game.FINISHED = FINISHED;
    }

    /**
     * @return
     */
    public static boolean isFINISHED() {
        return FINISHED;
    }

    /**
     * @return
     */
    public List<String> getGameFENStrings() {
        return gameFENStrings;
    }

    /**
     * @param gameFENStrings
     */
    public void setGameFENStrings(List<String> gameFENStrings) {
        this.gameFENStrings = gameFENStrings;
    }


}