package chess.view;

import chess.controller.CliController;
import chess.controller.Controller;
import chess.model.Game;

/**
 * @author TBD
 *
 */
public abstract class View {
	

	/**
	 * The connected game
	 */
	protected Game game;
	
	/**
	 * The CLIController
	 */
	protected Controller controller;
	
    /**
     * The constructor of the abstract View class.
     */
	public View() {

	}

	/**
	 * @return the game
	 */
	public Game getGame() {
		return game;
	}

	/**
	 * @param game the game to set
	 */
	public void setGame(Game game) {
		this.game = game;
		game.addObserver(this);
	}
	

	
	/**
	 * 
	 * @param game The game to observe
	 */
	public void modelChanged(Game game) {
		this.game = game;
		game.getBoard().update();
	}

	public abstract void assignController(CliController cliController);

	public abstract void readInputFromPlayer();

}
