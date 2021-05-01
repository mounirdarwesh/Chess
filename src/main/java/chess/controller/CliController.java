package chess.controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import chess.Attributes.Color;
import chess.model.Board;
import chess.model.Game;
import chess.model.Piece;
import chess.model.mapBoard;
import chess.player.HumanPlayer;
import chess.view.View;

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
    private static final mapBoard MAPPER = new mapBoard();;
    
    /**
     * The move to be performed on the piece
     */
    private Move move;
	
	/**
	 * The constructor expects a view to construct itself.
	 * @param view The view that is connected to this controller
	 */
	public CliController(View view) {
		super(view);
		
		// Assigning the controller
		view.assignController(this);
		
		// When a player inputs something to the console
		onActionPreformed();
	}
	

	/**
	 * This will be called when someone interacts with the Command Line Interface
	 */
	private void onActionPreformed() {
		// Create a new game
		game = new Game(this,
						new Board(),
						new HumanPlayer(Color.WHITE),
						new HumanPlayer(Color.BLACK));
		
		// Set the game to the CLI view
		view.setGame(game);
		
		// Start the game
		game.run();
	}
	
	
	/**
	 * The controller process the input from the player
	 */
	public void processtInputFromPlayer() {
		// Read the input from the view
		view.readInputFromPlayer();
		
		// The the controller checks for certain criteria, 
		// and when all criteria meet, then tell the game to perform the move
		game.getCurrentPlayer().makeMove(move);
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
	 * Check if the input from the player is a valid move
	 * @param input The input from the player
	 * @return true if the input is a valid move, false otherwise
	 */
	public boolean isValidMove(String input) {
		
		// If the player adds an extra character for pawn promotion
		checkForPromotedChar(input);

		System.out.println(Game.charToPromote);
		
		// Calculate from where the move is performed
		int move_from = getMoveFromPosition(input);
		// Calculate to where the move is performed
		int move_to = getMoveToPosition(input);
		
		// Get the piece that will be moved
		Piece piece = game.getBoard().getPiece(move_from);
		
		// If there is no such piece, i.e. the player chooses an empty square,
		// then ask for a new input
		if(piece == null) {
			return false;
		}
		
		// If the player chooses one of the opponent's pieces, the ask for 
		// another input
		else if(piece.getColor() != game.getCurrentPlayer().getColor()) {
			return false;
		}
		
		// If everything is OK, then calculate all legal moves form the selected
		// piece based on it's current position
		piece.calculateLegalMoves();

		// Loop through all the available moves
		for (Move move : piece.getAllLegalMoves()) {
			
			// If one of the moves destination matches with what the player
			// inputed as a destination, then return true
			if(move.getDestination() == move_to) {
				this.move = move;
				return true;
			}
			// else just continue looping through the list
			else continue;
		}
		
		// If something other that the above happened, then the is an input error,
		// so return false
		return false;
	}
	
	/**
	 * Checking if the player inputs an extra character to promote the pawn
	 * @param input
	 */
	private void checkForPromotedChar(String input) {
		try {
			game.setCharToPromote(input.charAt(5));
		} catch (IndexOutOfBoundsException e) {
			game.setCharToPromote('P');
	        return;
	    }
	}

	/**
	 * Here where we calculate the position where the piece should reside
	 * @param input The input form the player
	 * @return The index of the selected piece
	 */
    private int getMoveFromPosition(String input) {
        Matcher matcher = VALID_INPUT.matcher(input);
        matcher.matches();
        String fromIn = matcher.group(1);
        return MAPPER.map(fromIn);
    }

	/**
	 * Here where we calculate the position to where the piece should move
	 * @param input The input form the player
	 * @return The index of the destination
	 */
    private int getMoveToPosition(String input) {
        Matcher matcher = VALID_INPUT.matcher(input);
        matcher.matches();
        String toIn = matcher.group(3);
        return MAPPER.map(toIn);
    }

}
