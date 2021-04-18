package chess.player;

import java.util.ArrayList;
import java.util.Scanner;
import chess.Constants;
import chess.Game;
import chess.utilities.Piece;

/**
 * @author Ahmad Mohammad
 *
 */
public class HumanPlayer implements Player{
	
	// The color of the pieces 
	private Constants color;
	
	// The game
	private Game game;
	
	// The pieces of the player
	private ArrayList<Piece> playersPieces;
	
	/*
	 * The constructor of the class HumanPlayer
	 * @param color  the color that the player chooses to play with
	 */
	public HumanPlayer(Constants color) {
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
		// TODO
		
	}

	@Override
	public void addGame(Game game) {
		this.game = game;
		
	}

	/**
	 * Getter of the players color to play
	 * @return the color
	 */
	public Constants getColor() {
		return color;
	}

	/**
	 * Setter of the players color to play
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
