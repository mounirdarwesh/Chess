/**
 * 
 */
package chess.model;


import java.util.ArrayList;

import chess.Attributes;
import chess.controller.Move;

/**
 * @author TBD
 *
 */
public class Pawn extends Piece {
	
	/**
	 *  The name of the piece 
	 */
	protected String name = "P";
	
	/**
	 *  Pawn move offset
	 */
	private static final int[] MOVE_OFFSET = {8, 16, 7, 9};

	/**
	 * 
	 * @param postion
	 * @param type
	 * @param board
	 */
	public Pawn(int postion, Attributes type, Board board) {
		super(postion, type, board);
	}


	@Override
	public void calculateLegalMoves() {
		allLegalMoves = new ArrayList<Move>();
		for (int i : MOVE_OFFSET) {
			int destination = position + i * type.getDirection();
			
			Piece pieceAtDest = board.getPiece(destination);
			
			switch (i) {
				case 8:
					System.out.println(position);
					if(pieceAtDest == null) {
						allLegalMoves.add(new Move(board, this, destination));
					}
					else continue;
				case 16:
					if(isFirstMove && pieceAtDest == null) {
						int blockedSquare = position + 8 * type.getDirection();
						Piece blockedPiece = board.getPiece(blockedSquare);
						if(blockedPiece == null) {
							allLegalMoves.add(new Move(board, this, destination));
						}
					}
					else continue;
				case 7:
				case 9:
					if(pieceAtDest != null && pieceAtDest.type != this.type) {
						allLegalMoves.add(new Move(board, this, destination));
					}
					else continue;
				default:
					continue;
			}
		}
	}
	
	@Override
	public String toString() {
		return this.type.isWhite() ? this.name : this.name.toLowerCase();
	}

}
