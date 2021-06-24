package chess.controller;
import java.util.regex.Matcher;
import chess.Attributes;
import chess.Attributes.Color;
import chess.model.*;
import chess.view.Cli;

/**
 * The Controller class for the Command Line Interface
 * @author Gr.45
 */
public class CliController extends Controller {

    /**
     * The move to be performed on the piece
     */
    private Move move;

    /**
     *
     */
    private Cli view;


    /**
     * The constructor expects a view to construct itself.
     *  @param view The view that is connected to this controller
     * @param FINISHED game status for the sake of TEST
     */
    public CliController(Cli view, boolean FINISHED, boolean simpleGame) {
        super(view);
        this.view = view;
        // Assigning the controller
        view.assignController(this);

        if(!simpleGame) {
            // Showing the welcome screen
            view.showWelcomeScreen();
            // Getting the game mode
            view.gameMode();
        }

        // Start screen for the CLI
        startScreen(FINISHED);
    }

    /**
     * This will be called when someone interacts with the Command Line Interface
     * @param FINISHED game status
     */
    private void startScreen(boolean FINISHED) {
        Player opponent;
        // Create a new game
        if (gameMode == Attributes.GameMode.COMPUTER) {
            opponent = new Computer(Color.BLACK);
        } else {
            opponent = new HumanPlayer(Color.BLACK);
        }
        game = new Game(this,
                new Board(),
                new HumanPlayer(Color.WHITE),
                opponent);

        // Set the game to the CLI view
        view.setGame(game);

        // set initial status of the game
        game.setFINISHED(FINISHED);

        // Start the game
        game.run();
    }


    /**
     * The controller process the input from the player
     */
    @Override
    public void processInputFromPlayer() {

        // The current player of the game
        Player currentPlayer = game.getCurrentPlayer();

        // Read the input from the view
        if(currentPlayer instanceof HumanPlayer) {
            view.readInputFromHuman();
        }

        // The controller checks for certain criteria,
        // and when all criteria meet, then tell the game to perform the move
        if(currentPlayer instanceof HumanPlayer) {
            currentPlayer.makeMove(move);
        } else if(currentPlayer instanceof Computer) {
            Move computerMove;
            do {
                computerMove = ((Computer) currentPlayer).evaluate();
            } while (!view.readInputFromComputer(computerMove.toString()));
            currentPlayer.makeMove(computerMove);
        }
    }

    /**
     *
     * @param status The status of the game
     * @param player The player
     */
    @Override
    public void notifyView(Attributes.GameStatus status, Player player) {
        view.notifyUser(status, player);
    }



    /**
     * Here where the controller checks if the user inputed
     * a syntactically valid input
     *
     * @param input The input form the player
     * @return true, if the input is syntactically correct, false otherwise
     */
    public boolean isValidInput(String input) {
        Matcher matcher = VALID_INPUT.matcher(input);
        return matcher.matches();
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

        // Calculate from where the move is performed
        int move_from = getMoveFromPosition(input);
        // Calculate to where the move is performed
        int move_to = getMoveToPosition(input);


        // Check if the game allows the move from the current player
        if (!game.isMoveAllowed(move_from, move_to)) {
            return false;
        }

        this.move = game.getAllowedMove();

        // If the game verifies the move than notify the player
        return true;
    }

    /**
     * Checking if the player inputs an extra character to promote the pawn
     *
     * @param input User Input
     */
    private void checkForPromotedChar(String input) {
        try {
            game.setCharToPromote(input.charAt(5));
        } catch (IndexOutOfBoundsException e) {
            game.setCharToPromote(' ');
            return;
        }
    }



    /**
     * Setter for the game mode
     * @param gameMode
     */
    public void setGameMode(Attributes.GameMode gameMode) {
        this.gameMode = gameMode;
    }
}
