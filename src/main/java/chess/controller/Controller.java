package chess.controller;

import chess.model.Game;
import chess.view.View;

/**
 * @author TBD
 *
 */
public class Controller {
	
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
	 * 
	 * @param view The view that is connected to this controller
	 */
	public Controller(View view) {
		this.view = view;
	}

	public void processtInputFromPlayer() {
		// TODO Auto-generated method stub
		
	}




}
