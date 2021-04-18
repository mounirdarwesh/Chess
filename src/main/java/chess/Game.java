package chess;

import java.util.Scanner;

import chess.player.HumanPlayer;
import chess.player.Player;
import chess.utilities.Board;

/**
 * @author Ahmad Mohammad
 * The class that controls the game 
 */
public class Game {
	
	// The first player
	private Player playerOne;
	
	// The second player
	private Player playerTwo;
	
	// The current player of the game
	private Player currentPlayer;
	
	// The board of the current game
	private Board board;


	// The status of the game
	private static boolean FINISHED = true;
	
	/*
	 * The constructor of the game class
	 * @param  player_one  the first player
	 * @param  player_two  the second player
	 */
	public Game(Player playerOne, Player playerTwo){
		this.board = new Board(this);
		this.playerOne = playerOne;
		this.playerTwo = playerTwo;
		playerOne.addGame(this);
		playerOne.addGame(this);
	}

	// THE BRAIN OF THE GAME
	public void run() {
		
		// Print the board
		System.out.println(this.board);
		
		// White player is first to move
		currentPlayer = this.playerOne.getColor().isWhite() ? this.playerOne : this.playerTwo;
		
		// The scanner to read the players input
		Scanner scanner = new Scanner(System.in);
		
		// A while of the game 
		while (!FINISHED) {
			// The input of the player
			String input = scanner.nextLine();
			verifyMove(input);
			break;
			
		}
	}
	
	/*
	 * A method to verify the players input
	 * @param input  the input from the player
	 */
	public void verifyMove(String input) {
		
		// Check if move is syntactically correct

		
		// Check if move is not allowed
		
		
		// Check if move is correct
	}

	/**
	 * Getter for the first player
	 * @return the player_one
	 */
	public Player getPlayerOne() {
		return playerOne;
	}
	
	/**
	 * Getter for the second player
	 * @return the player_two
	 */
	public Player getPlayerTwo() {
		return playerTwo;
	}
	
	
	/**
	 * Getter for the games board
	 * @return the board
	 */
	public Board getBoard() {
		return board;
	}

	/**
	 * Getter for the current player of the game
	 * @return the currentPlayer
	 */
	public Player getCurrentPlayer() {
		return currentPlayer;
	}

	/**
	 * Setter for the current player of the game
	 * @param currentPlayer the currentPlayer to set
	 */
	public void setCurrentPlayer(Player currentPlayer) {
		this.currentPlayer = currentPlayer;
	}

	
}
