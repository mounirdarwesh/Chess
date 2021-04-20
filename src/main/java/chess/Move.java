package chess;

import chess.utilities.Board;
import chess.utilities.Piece;

/**
 * @author TBD
 *
 */
public class Move {
	
	// The board on which the move is performed
	private Board board;
	
	// The position where the move starts
	private Piece piece;
	
	// The position where the move ends 
	private int destination;
	
	
	/*
	 * The constructor for the move class
	 * @param board  The board on which the move is performed
	 * @param piece  The moved piece
	 * @param destination  To where the piece should move
	 */
	public Move(Board board, Piece piece, int destination) {
		this.board = board;
		this.piece = piece;
		this.destination = destination;
	}
	
	/*
	 * 
	 */
	public void excute() {
		
		// Removing the piece from it's position
		this.board.getPiecesOnBoard().remove(this.piece.getPosition());
		
		// Adding the piece to the destination
		this.board.getPiecesOnBoard().add(destination, this.piece);

	}

}
