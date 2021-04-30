package chess.view;

import chess.controller.CliController;
import chess.controller.Controller;
import chess.model.Game;

/**
 * The View Class, where the user interacts
 * @author Gr.45
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
		System.out.println(game.getBoard());
	}

	public abstract void assignController(CliController cliController);

	public abstract void readInputFromPlayer();

}
