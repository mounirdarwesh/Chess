package chess.player;
import chess.Attributes;
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
	protected Attributes color;
	
	/**
	 *  The game
	 */
	protected Game game;
	
	
	/**
	 * The constructor of the class Player
	 * @param color  the color that the player chooses to play with
	 */
	public Player(Attributes color) {
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
	public Attributes getColor() {
		return color;
	}
	
	/**
	 * Setter for the color of the computer
	 * @param color the color to set
	 */
	public void setColor(Attributes color) {
		this.color = color;
	}
	
}
