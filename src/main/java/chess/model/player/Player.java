package chess.model.player;
import java.util.ArrayList;

import chess.Constants;
import chess.model.Game;
import chess.model.Move;
import chess.model.pieces.Piece;

/**
 * @author TBD
 *
 */
public abstract class Player {
	
	// The color of the pieces 
	protected Constants color;
	
	// The game
	protected Game game;
	
	// The pieces of the player
	protected ArrayList<Piece> playersPieces;
	
	// List of all available moves
	private ArrayList<Move> allAvailableMoves;
	
	
	/*
	 * The constructor of the class Player
	 * @param color  the color that the player chooses to play with
	 */
	public Player(Constants color) {
		this.color = color;
	}

	/*
	 * Add the controller to the player
	 * @param game  The controller
	 */
	public void addGame(Game game) {
		this.game = game;
	}

	
	/*
	 * Making the move
	 */
	public abstract void makeMove(Move move);

	/*
	 * loading the players pieces
	 */
	public void loadPlayerPieces() {
		playersPieces = new ArrayList<Piece>();
		for (Piece piece : this.game.getBoard().getPiecesOnBoard()) {
			if(piece != null && piece.getType() == this.color) {
				playersPieces.add(piece);
			} else continue;
		}
	}
	
	
	/*
	 * Checking if the move is allowed
	 */
	public boolean isMoveAllowed(Move move) {
		allAvailableMoves = loadPlayerAvailableMoves();
		return allAvailableMoves.contains(move);
	}

	/*
	 * Loading all of the player's legal moves
	 */
	private ArrayList<Move> loadPlayerAvailableMoves() {
		ArrayList<Move> moves = new ArrayList<Move>();
		for (Piece piece : this.playersPieces) {
			if(piece == null) {
				continue;
			}else {
				moves.addAll(piece.calculateLegalMoves(this.game.getBoard()));
			}
			
		}
		return moves;
	}
	
	/**
	 * Getter of the players color to play
	 * @return the color
	 */
	public Constants getColor() {
		return color;
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
