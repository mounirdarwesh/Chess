package chess.controller;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import chess.Attributes;
import chess.Attributes.Color;
import chess.cli.Cli;
import chess.model.*;

/**
 * The Controller class for the Command Line Interface
 * @author Gr.45
 */
public class CliController extends Controller {

    /**
     * Pattern to verify the syntax of the User Input
     */
    private static final Pattern VALID_INPUT = Pattern.compile("([a-h][1-8])([-])([a-h][1-8])([QRNBqrnb]?)", Pattern.CASE_INSENSITIVE);

    /**
     * A board mapper, to map each input to its respective index
     */
    private static final MapBoard MAPPER = new MapBoard();

    /**
     * The move to be performed on the piece
     */
    private Move move;

    /**
     * The mode of the game
     */
    private Attributes.GameMode gameMode;

    /**
     * The constructor expects a view to construct itself.
     *
     * @param view     The view that is connected to this controller
     * @param FINISHED game status for the sake of TEST
     */
    public CliController(Cli view, boolean FINISHED) {
        super(view);

        // Assigning the controller
        view.assignController(this);

        // Showing the welcome screen
        view.showWelcomeScreen();

        // Getting the game mode
        view.gameMode();

        // When a player inputs something to the console
        onActionPreformed(FINISHED);
    }


    /**
     * This will be called when someone interacts with the Command Line Interface
     *
     * @param FINISHED game status
     */
    private void onActionPreformed(boolean FINISHED) {
        Player opponent;
        // Create a new game
        switch (gameMode) {
            case HUMAN:
                opponent = new HumanPlayer(Color.BLACK);
                break;
            case COMPUTER:
                opponent = new Computer(Color.BLACK);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + gameMode);
        }
        game = new Game(this,
                new Board(),
                new Computer(Color.WHITE),
                new Computer(Color.BLACK));

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
            while (true) {
                computerMove = ((Computer) currentPlayer).evaluate();
                if(view.readInputFromComputer(computerMove.toString())) break;
            }
            currentPlayer.makeMove(computerMove);
        }
    }

    @Override
    public List<Piece> getBeatenPieces() {
        return game.getCurrentPlayer().getColor().isWhite() ?
                game.getWhitePlayer().getBeaten() : game.getBlackPlayer().getBeaten();
    }

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
     * Getter for the game mode
     * @return
     */
    public Attributes.GameMode getGameMode() {
        return gameMode;
    }

    /**
     * Setter for the game mode
     * @param gameMode
     */
    public void setGameMode(Attributes.GameMode gameMode) {
        this.gameMode = gameMode;
    }
}
