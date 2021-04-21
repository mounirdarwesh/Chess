package chess.model;
import java.util.*;

import chess.model.Move;
import chess.model.mapBoard;
import chess.model.player.Player;
import chess.model.Board;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
	
	// The move from position
	private int move_from;
	
	// The move to position
	private int move_to;
	
	// The status of the game
	private static boolean FINISHED = false;

	// Pattern to verify the syntax of the User Input
	private final static Pattern validMove = Pattern.compile("([a-hA-H][1-8])([-])([a-hA-H][1-8])", Pattern.CASE_INSENSITIVE);
	private final mapBoard mapper;

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
		playerTwo.addGame(this);
		mapper = new mapBoard();
	}

	// THE BRAIN OF THE GAME
	public void run() {
		
		// Print the board
		System.out.println(this.board);
		
		// Initialize the players pieces
		this.playerOne.loadPlayerPieces();
		this.playerTwo.loadPlayerPieces();
		
		// White player is first to move
		currentPlayer = this.playerOne.getColor().isWhite() ? this.playerOne : this.playerTwo;
		
		// The scanner to read the players input
		Scanner scanner = new Scanner(System.in);
		
		// A while of the game 
		while (!FINISHED) {

			// The input of the player
			String input = scanner.nextLine();
			
			// Keep looping until the player gives a valid input
			while(!verifyMove(input)) {
				input = scanner.nextLine();
			}
			
			// perform the move
			this.currentPlayer.makeMove(new Move(this.board, this.board.getPiece(move_from), move_to));
			
			// Updating the board
			this.board.updateBoard();
			
			// Show the board
			System.out.println(this.board);
			
			// Check the game status after every turn
			checkGameStatus();
			
			// Switch the player 
			currentPlayer = getOpponent(this.currentPlayer);
			
		}
		
		// Closing the input
		scanner.close();
	}
	
	/*
	 * Getting the opponent of the current player
	 * @param currentPlayer  The current player of the game
	 */
	public Player getOpponent(Player currentPlayer) {
		return this.currentPlayer.getColor() == this.playerOne.getColor() ? this.playerOne : this.playerTwo;
	}

	/*
	 * Here were the game checks the status of the current game
	 */
	private void checkGameStatus() {
		// TODO 
	}

	/*
	 * A method to verify the players input
	 * @param input  the input from the player
	 */
	public boolean verifyMove(String input) {
		
		// Check if move is syntactically not correct
		Matcher matcher = validMove.matcher(input);
		if (matcher.matches()){
			System.out.println("Valid Syntax");
		}else System.out.println("not Valid Move");
		
		String from = "";
		String to = "";
		move_from = getMoveFromPosition(input);
		move_to = getMoveToPosition(input);
		
		// Check if move is not allowed
		if(!this.currentPlayer.isMoveAllowed(new Move(this.board, this.board.getPiece(move_from), move_to))) {
			System.out.println("!Move not allowed");
			return false;
		}
		
		// Otherwise the move is correct. Then output the move
		else System.out.println("!" + input);
		return true;
	}

	private int getMoveFromPosition(String from) {
		Matcher matcher = validMove.matcher(from);
		matcher.matches();
		String fromIn = matcher.group(1);
		return mapper.map(fromIn);

	}

	private int getMoveToPosition(String to) {
		Matcher matcher = validMove.matcher(to);
		matcher.matches();
		String toIn = matcher.group(3);
		return mapper.map(toIn);
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
