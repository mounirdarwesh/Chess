package chess.model.player;

import chess.Constants;
import chess.model.Move;

/**
 * @author Ahmad Mohammad
 *
 */
public class HumanPlayer extends Player{
	
	
	/*
	 * The constructor of the class HumanPlayer
	 * @param color  the color that the player chooses to play with
	 */
	
	public HumanPlayer(Constants color) {
		super(color);
		// TODO
	}

	@Override
	public void makeMove(Move move) {
		move.excute();
		// TODO
		
	}


}
