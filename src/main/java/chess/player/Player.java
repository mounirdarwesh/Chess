package chess.player;
import chess.Attributes.Color;
import chess.controller.Move;
import chess.model.Game;

/**
 * @author TBD
 *
 */
public abstract class Player {
	
	/**
	 *  The color of the pieces 
	 */
	protected Color color;
	
	/**
	 *  The game
	 */
	protected Game game;
	
	
	/**
	 * The constructor of the class Player
	 * @param color  the color that the player chooses to play with
	 */
	public Player(Color color) {
		this.color = color;
	}

	
	/*
	 * Making the move
	 */
	public abstract void makeMove(Move move);

	
	/**
	 * Getter of the players color to play
	 * @return the color
	 */
	public Color getColor() {
		return color;
	}
	
}
