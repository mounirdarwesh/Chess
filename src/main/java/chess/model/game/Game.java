package chess.model.game;

import chess.Attributes;
import chess.controller.Controller;
import chess.controller.Move;
import chess.model.Board;
import chess.model.pieces.Piece;
import chess.model.player.AI;
import chess.model.player.HumanPlayer;
import chess.model.player.Player;
import chess.util.ChessClock;
import chess.util.Observable;
import javafx.concurrent.Task;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This class contains information about the game
 * @author Gr.45
 */
public abstract class Game extends Observable {

    /**
     * The status of the game
     */
    protected boolean FINISHED = false;

    /**
     * The controller of the game
     */
    protected Controller controller;

    /**
     * The player with the white pieces
     */
    protected Player whitePlayer;

    /**
     * The player with the black pieces
     */
    protected Player blackPlayer;

    /**
     * The current player of the game
     */
    protected Player currentPlayer;

    /**
     * The board of the round
     */
    protected Board board;

    /**
     * The allowed move
     */
    protected Move allowedMove = null;

    /**
     * Promoted character
     */
    protected char charToPromote;

    /**
     * The EnPassant Pawn of the game
     */
    protected Piece enPassantPawn = null;

    /**
     * list of all the moves
     */
    protected List<Move> allListOfMoves = new ArrayList<>();

    /**
     * Clock of the Chess
     */
    protected ChessClock chessClock;

    /**
    * The task of the game
    */
    protected Task gameTask;


    /**
     * The constructor creates a new game instance with the given Controller
     * @param controller The controller to manage this instance
     */
    public Game(Controller controller) {
        this.controller = controller;
        this.board = new Board();


        // Check if the player enabled time
        if(!controller.getGameSettings()[2].equals("0")) {
            chessClock = new ChessClock(this);
        }
    }

    /**
     * Initialize the players
     */
    protected void initPlayers() {
        String[] gameSettings = controller.getGameSettings();

        // A normal game
        if(gameSettings[3].equals("0")) {

            // AI Mode is off
            if (gameSettings[4].equals("0")) {
                whitePlayer = new HumanPlayer(Attributes.Color.WHITE);
                blackPlayer = new HumanPlayer(Attributes.Color.BLACK);
            }
            // AI Mode is on
            else {
                if(gameSettings[0].equals("1")) {
                    whitePlayer = new HumanPlayer(Attributes.Color.WHITE);
                    blackPlayer = new AI(Attributes.Color.BLACK, this);
                } else {
                    whitePlayer = new AI(Attributes.Color.WHITE, this);
                    blackPlayer = new HumanPlayer(Attributes.Color.BLACK);
                }
            }
        }
        // LAN Game Mode is on
        else {
            whitePlayer = new HumanPlayer(Attributes.Color.WHITE);
            blackPlayer = new HumanPlayer(Attributes.Color.BLACK);
            currentPlayer = gameSettings[0].equals("1") ? whitePlayer : blackPlayer;
        }

        if(chessClock != null) {
            long duration = Long.parseLong(controller.getGameSettings()[2]);
            whitePlayer.setTimeLeft(duration * 60000);
            blackPlayer.setTimeLeft(duration * 60000);
        }

        // Load the player pieces
        loadPlayerPieces();
    }

    /**
     * Loading the players pieces
     */
    public void loadPlayerPieces() {
        for (Piece piece : board.getPiecesOnBoard()) {
            if(piece == null) continue;
            if(piece.getColor().isWhite()) {
                whitePlayer.addToPlayersPieces(piece);
            }else {
                blackPlayer.addToPlayersPieces(piece);
            }
        }
    }

    /**
     * Add the captured piece to the list of the player's beaten pieces
     * @param captured The captured pieces
     */
    public void addToBeaten(Piece captured) {
        if (captured.getColor().isWhite()) {
            whitePlayer.getBeaten().add(captured);
        } else {
            blackPlayer.getBeaten().add(captured);
        }
        //And delete the piece from the players available pieces
        getOpponent().removeFromPlayersPieces(captured);
    }

    /**
     * Remove the captured piece to the list of the player's beaten pieces
     *
     * @param captured The captured pieces
     */
    public void removeFromBeaten(Piece captured) {
        if (captured.getColor().isWhite()) {
            whitePlayer.getBeaten().remove(captured);
        } else {
            blackPlayer.getBeaten().remove(captured);
        }
        //And delete the piece from the players available pieces
        getOpponent().addToPlayersPieces(captured);

    }

    /**
     * The brain of the game
     */
    public abstract void run();


    /**
     * The game checks if the player wanted move is allowed
     * @param pieceToMove the piece the player wants to move
     * @param destination the destination where the piece should move
     */
    public void processMoveFromPlayer(Piece pieceToMove, int destination) {
        // Calculate the piece legal moves
        pieceToMove.calculateLegalMoves();

        for (Move move : pieceToMove.getAllLegalMoves()) {
            if (move.getDestination() == destination && makeTempMoveAndCheck(move)) {
                allowedMove = move;
                break;
            }
        }
    }

    /**
     * Make a temporary move and then check if it affects the players king
     * @param tmpMove The temporary move
     * @return true if there is no threat to the players king, false otherwise
     */
    public boolean makeTempMoveAndCheck(Move tmpMove) {
        boolean allowAfterTempMove;
        tmpMove.execute();
        allowAfterTempMove = !isCurrentPlayersKingInCheck();
        tmpMove.undo();
        return allowAfterTempMove;
    }

    /**
     * check if the king of the current player in check
     * @return true, if he in check
     */
    public boolean isCurrentPlayersKingInCheck() {
        boolean kingInCheck = false;
        // Calculate a list of enemy legal moves
        List<Move> enemyLegalMoves = getOpponent().calculatePlayerMoves();
        // Iterate through all of the enemy's legal moves. If a move is found that can threaten
        // the current player's king, return true
        for (Move move : enemyLegalMoves) {
            if (move.getDestination() == currentPlayer.getKing().getPosition()
                    && (move instanceof Move.CaptureMove || move instanceof Move.PromotionMove)) {
                kingInCheck = true;
                break;
            }
        }
        return kingInCheck;
    }

    /**
     * check if the king of the current player in checkmate
     * @return true, if the king mate
     */
    public boolean isCurrentPlayersKingInCheckMate() {
        boolean CheckMate = true;
        if (isCurrentPlayersKingInCheck()) {
            for (Move move : currentPlayer.calculatePlayerMoves()) {
                move.execute();
                if (!isCurrentPlayersKingInCheck()) {
                    CheckMate = false;
                    move.undo();
                    break;
                }
                move.undo();
            }
        } else {
            CheckMate = false;
        }
        return CheckMate;
    }

    /**
     * Check the status of the game at each round
     */
    public void checkGameStatus() {
        if (hasGameEndedInWin()) {
            controller.notifyView(Attributes.GameStatus.ENDED_IN_WIN, getOpponent());
            FINISHED = true;
            return;
        }
        if (hasGameEndedInDraw()) {
            controller.notifyView(Attributes.GameStatus.ENDED_IN_DRAW, currentPlayer);
            FINISHED = true;
            return;
        }
        if (isCurrentPlayersKingInCheck()) {
            controller.notifyView(Attributes.GameStatus.KING_IN_CHECK, currentPlayer);
        }
    }

    /**
     * Checks if the player has no moves left and thus he lost the game
     * or the King is in Checkmate.
     * @return true if the player has no moves left, false otherwise
     */
    private boolean hasGameEndedInWin() {
        return isCurrentPlayersKingInCheckMate();
    }

    /**
     * Check if the game has ended with no winner
     * @return true if the game has ended in a draw, false otherwise
     */
    private boolean hasGameEndedInDraw() {
        //First check if the player has any legal moves
        for (Move move : currentPlayer.calculatePlayerMoves()) {
            if (makeTempMoveAndCheck(move)) {
                return false;
            }
        }
        // If no legal move is found, then check if the king is in check
        return !isCurrentPlayersKingInCheck();
    }

    private boolean hasGameEnded() {
        return hasGameEndedInWin() || hasGameEndedInDraw();
    }


    /**
     * Getter for the Controller
     * @return controller The controller of this game
     */
    public Controller getController() {
        return controller;
    }

    /**
     * getter of the board
     * @return board
     */
    public Board getBoard() {
        return board;
    }

    /**
     * getter of the opponent
     * @return player
     */
    public Player getOpponent() {
        return currentPlayer.getColor().isWhite() ? blackPlayer : whitePlayer;
    }

    /**
     * getter of the current player
     * @return player
     */
    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    /**
     * getter of the white player
     * @return player
     */
    public Player getWhitePlayer() {
        return whitePlayer;
    }

    /**
     * getter of the black player
     * @return player
     */
    public Player getBlackPlayer() {
        return blackPlayer;
    }

    /**
     * getter of char to promote to
     * @return char
     */
    public char getCharToPromote() {
        return charToPromote;
    }

    /**
     * setter of the char to promote to
     * @param charToPromote the charToPromote to set
     */
    public void setCharToPromote(char charToPromote) {
        this.charToPromote = charToPromote;
    }

    /**
     * getter of allowed move
     * @return move
     */
    public Move getAllowedMove() {
        return allowedMove;
    }

    /**
     * getter of the EnPassant pawn
     * @return piece
     */
    public Piece getEnPassantPawn() {
        return enPassantPawn;
    }

    /**
     * setter of the EnPassant pawn
     * @param enPassantPawn EnPassant pawn
     */
    public void setEnPassantPawn(Piece enPassantPawn) {
        this.enPassantPawn = enPassantPawn;
    }

    /**
     * check if the game finished
     * @return boolean
     */
    public  boolean isFINISHED() {
        return FINISHED;
    }

    /**
     * getter of all list of moves
     * @return  the list of all moves
     */
    public List<Move> getAllListOfMoves() {
        return allListOfMoves;
    }

    /**
     * setter of the current player
     * @param currentPlayer current player
     */
    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    /**
     * setter of the boolean finished
     * @param FINISHED setter
     */
    public void setFINISHED(boolean FINISHED){
        this.FINISHED = FINISHED;
    }

    /**
     * Update the Time
     */
    public abstract void updateClock();

    /**
     * Getter for the game Task
     * @return game Task
     */
    public Task getGameTask() {
        return gameTask;
    }
}
