package chess.player;

import chess.Attributes;
import chess.controller.Move;

/**
 * @author Ahmad Mohammad
 *
 */
public class HumanPlayer extends Player{
	
	
	/**
	 * The constructor of the class HumanPlayer
	 * @param color  the color that the player chooses to play with
	 */
	public HumanPlayer(Attributes color) {
		super(color);
	}

	@Override
	public void makeMove(Move move) {
		move.excute();
	}

}
