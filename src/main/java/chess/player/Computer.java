/**
 * 
 */
package chess.player;

import java.util.ArrayList;

import chess.Constants;
import chess.Game;
import chess.utilities.Piece;

/**
 * @author Ahmad Mohammad
 *
 */
public class Computer implements Player{
	
	// The color of the pieces
	private Constants color;
	
	// The game
	private Game game;

	// The pieces of the computer
	private ArrayList<Piece> playersPieces;
	
	/*
	 * The constructor of the computer class
	 * @param color  the color that the computer is left with
	 */
	public Computer(Constants color) {
		this.color = color;
	}

	/* Initializing all of the players pieces
	 * @return allPlayersPieces  All of the pieces that this player can control
	*/
	private ArrayList<Piece> loadPlayerPieces() {
		ArrayList<Piece> allPlayersPieces = new ArrayList<Piece>();
		for (Piece piece : this.game.getBoard().getAllPieces()) {
			if(piece.getType() == this.color) {
				allPlayersPieces.add(piece);
			} else continue;
		}
		return allPlayersPieces;
	}

	@Override
	public void makeMove() {
		//TODO
	}

	@Override
	public void addGame(Game game) {
		this.game = game;
	}

	/**
	 * Getter for the color of the computer
	 */
	public Constants getColor() {
		return this.color;
	}
	
	/**
	 * Setter for the color of the computer
	 * @param color the color to set
	 */
	public void setColor(Constants color) {
		this.color = color;
	}
	
	/**
	 * Getter for all pieces of the player
	 * @return the playersPieces
	 */
	public ArrayList<Piece> getPlayersPieces() {
		return playersPieces;
	}

	/**
	 * Setter for all the pieces of the player
	 * @param playersPieces the playersPieces to set
	 */
	public void setPlayersPieces(ArrayList<Piece> playersPieces) {
		this.playersPieces = playersPieces;
	}


}
