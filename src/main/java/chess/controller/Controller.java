package chess.controller;
import chess.model.Game;
import chess.view.View;

/**
 * An abstract class of the controller
 * @author Gr.45
 */
public abstract class Controller {
	
	/**
	 * The game as model that is connected to this controller
	 */
	protected Game game;
	
	/**
	 * The view that is connected to this controller
	 */
	protected View view;
	
	/**
	 * The constructor expects a view to construct itself.
	 * @param view The view that is connected to this controller
	 */
	public Controller(View view) {
		this.view = view;
	}

	public abstract void processtInputFromPlayer();

}
