package chess.controller;

import chess.model.Board;
import chess.model.Piece;

/**
 * @author TBD
 *
 */
public class Move {
	
	/**
	 *  The board on which the move is performed
	 */
	private Board board;
	
	/**
	 *  The position where the move starts
	 */
	private Piece piece;
	
	/**
	 *  The position where the move ends 
	 */
	private int destination;
	


	/**
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
	
	/**
	 * 
	 */
	public void excute() {
		board.movePiece(piece.getPosition(), destination);
	}
	
	
	/**
	 * @return the destination
	 */
	public int getDestination() {
		return destination;
	}

}
