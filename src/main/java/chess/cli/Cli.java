package chess.cli;

import chess.Constants;
import chess.Game;
import chess.player.HumanPlayer;

/**
 * Starting point of the command line interface
 */
public class Cli {
		
	
    /**
     * The entry point of the CLI application.
     *
     * @param args The command line arguments passed to the application
     */
    public static void main(String[] args) {
    	
    	// Create the game
    	Game game = new Game(new HumanPlayer(Constants.WHITE), new HumanPlayer(Constants.BLACK));
    	
    	// Run the game
    	game.run();
    	
    	
    }
}
