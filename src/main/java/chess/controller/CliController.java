/**
 * 
 */
package chess.controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import chess.Attributes;
import chess.model.Board;
import chess.model.Game;
import chess.model.Piece;
import chess.model.mapBoard;
import chess.player.HumanPlayer;
import chess.view.View;

/**
 * @author TBD
 *
 */
public class CliController extends Controller {
	
    /**
     * Pattern to verify the syntax of the User Input
     */
    private static final Pattern VALID_INPUT = Pattern.compile("([a-h][1-8])([-])([a-h][1-8])", Pattern.CASE_INSENSITIVE);
    
    /**
     * A board mapper, to map each input to its respective index
     */
    private static final mapBoard MAPPER = new mapBoard();;
    
    /**
     * 
     */
    private Move move;
	
	/**
	 * The constructor expects a view to construct itself.
	 * @param cli The view that is connected to this controller
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
						new HumanPlayer(Attributes.WHITE),
						new HumanPlayer(Attributes.BLACK));
		
		// Set the game to the CLI view
		view.setGame(game);
		
		game.run();
	}
	
	
	/**
	 * 
	 */
	public void processtInputFromPlayer() {
		view.readInputFromPlayer();
		
		game.getCurrentPlayer().makeMove(move);
	}
	
	
	/**
	 * 
	 * @param input
	 * @return
	 */
	public boolean isValidInput(String input) {
		Matcher matcher = VALID_INPUT.matcher(input);
		return matcher.matches();
	}
	

	/**
	 * 
	 * @param input
	 * @return
	 */
	public boolean isValidMove(String input) {
		int move_from = getMoveFromPosition(input);
		int move_to = getMoveToPosition(input);
		Piece piece = game.getBoard().getPiece(move_from);
		if(piece == null) {
			return false;
		}
		else if(piece.getType() != game.getCurrentPlayer().getColor()) {
			return false;
		}
		piece.calculateLegalMoves();
		for (Move move : piece.getAllLegalMoves()) {
			if(move.getDestination() == move_to) {
				this.move = move;
				return true;
			} else continue;
		}
		return false;
	}
	
	/**
	 * 
	 * @param from
	 * @return
	 */
    private int getMoveFromPosition(String from) {
        Matcher matcher = VALID_INPUT.matcher(from);
        matcher.matches();
        String fromIn = matcher.group(1);
        return MAPPER.map(fromIn);

    }

    /**
     * 
     * @param to
     * @return
     */
    private int getMoveToPosition(String to) {
        Matcher matcher = VALID_INPUT.matcher(to);
        matcher.matches();
        String toIn = matcher.group(3);
        return MAPPER.map(toIn);
    }




}
