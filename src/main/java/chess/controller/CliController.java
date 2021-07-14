package chess.controller;

import chess.Attributes;
import chess.model.game.CliGame;
import chess.model.pieces.Piece;
import chess.model.player.AI;
import chess.model.player.HumanPlayer;
import chess.model.player.Player;
import chess.util.BoardMapper;
import chess.view.cli.Cli;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * controller of cli class
 * @author Gr.45
 */
public class CliController extends Controller {

    /**
     * view of the cli controller
     */
    private Cli cliView;

    /**
     * the status of the game for the tests
     */
    private boolean finished;

    /**
     * Pattern to verify the syntax of the User Input
     */
    protected static final Pattern VALID_INPUT = Pattern.compile(
            "([a-h][1-8])([-])([a-h][1-8])([QRNBqrnb]?)", Pattern.CASE_INSENSITIVE);

    /**
     * The constructor expects a view to construct itself.
     * @param cliView The view that is connected to this controller
     * @param finished boolean if the game is finished
     * @param simpleGame if a normal game against two players
     */
    public CliController(Cli cliView, boolean finished, boolean simpleGame) {
        this.cliView = cliView;
        this.finished = finished;

        //Connecting the controller to the view
        cliView.setController(this);

        if (!simpleGame) {
            // Showing the welcome screen
            cliView.showWelcomeScreen();
            // Getting the game mode
            cliView.gameMode(finished);
        }

        createGame();
    }

    /**
     *
     */
    private void createGame() {
        // Creating a new Game
        game = new CliGame(this);
        game.setFINISHED(finished);
        cliView.setGame(game);
        new MoveController().setGame(game);
        game.run();
    }


    /**
     * Here where the controller checks if the user inputed
     * a syntactically valid input
     * @param input The input form the player
     * @return true, if the input is syntactically correct, false otherwise
     */
    public boolean isValidInput(String input) {
        Matcher matcher = VALID_INPUT.matcher(input);
        return matcher.matches();
    }

    /**
     * Process the User Inputs and handle the player Type
     */
    public void processInputFromPlayer() {

        // The current player of the game
        Player currentPlayer = game.getCurrentPlayer();

        // Read the input from the view
        if (currentPlayer instanceof HumanPlayer) {
            cliView.readInputFromHuman();
            return;
        }

        if(currentPlayer.hasPlayerUndidAMove()
                || currentPlayer.hasPlayerRedidAMove()) return;

        if (currentPlayer instanceof AI) {
            Move computerMove;
            do {
                computerMove = ((AI) currentPlayer).evaluate();
            } while (!cliView.readInputFromComputer(computerMove.toString()));
            currentPlayer.makeMove(computerMove);
        }
    }

    /**
     * Check if the input from the player is a valid move
     *
     * @param input The input from the player
     * @return true if the input is a valid move, false otherwise
     */
    public boolean isValidMove(String input) {
        // If the player adds an extra character for pawn promotion
        checkForPromotedChar(input);

        String[] inputMove = input.split("-");

        // Calculate from where the move is performed
        int move_from = BoardMapper.mapChessNotationToPosition(inputMove[0]);
        //Get the piece on that position
        Piece piece = game.getBoard().getPiece(move_from);
        if(piece == null) return false;
        // Calculate to where the move is performed
        int move_to = BoardMapper.mapChessNotationToPosition(inputMove[1]);

        if(piece.getColor() != game.getCurrentPlayer().getColor()) {
            return false;
        }
        // Check if the game allows the move from the current player
        game.processMoveFromPlayer(piece, move_to);

        // If the game verifies the move than notify the player
        return game.getAllowedMove() != null;
    }

    /**
     * Checking if the player inputs an extra character to promote the pawn
     * @param input User Input
     */
    private void checkForPromotedChar(String input) {
        try {
            game.setCharToPromote(input.charAt(5));
        } catch (IndexOutOfBoundsException e) {
            game.setCharToPromote(' ');
        }
    }

    /**
     * @param status The status of the game
     * @param player The player
     */
    @Override
    public void notifyView(Attributes.GameStatus status, Player player) {
        cliView.notifyUser(status, player);
    }
}
