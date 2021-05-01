package chess.model;
import chess.Attributes.Color;
import chess.controller.Move;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author Ahmad Mohammad
 *
 */
public class Queen extends Piece {
	
	/**
	 *  The name of the piece 
	 */
	protected String name = "Q";

	/**
	 * Possible offsets for the queen
	 */
	private static final int[] MOVE_OFFSET = {-9, -8, -7, -1, 1, 7, 8, 9};


	/**
	 * The constructor of the Pawn Class
	 * @param  position  The position of the Pawn
	 * @param  color  The type of the Pawn
	 * @param board  The board of the game
	 */
	public Queen(int position, Color color, Board board) {
		super(position, color, board);
	}

	@Override
	public void calculateLegalMoves() {
		allLegalMoves = new ArrayList<Move>();

		// Iterating through the possible offsets
		for (int i : MOVE_OFFSET) {

			// The position where the piece is moving to
			int destination = position + i;

			// If the destination is not on the board then continue the loop
			// and get the next offset
			if(!isPositionInBounds(destination)) {
				continue;
			}

			// Get the piece on that destination position
			Piece pieceAtDest = board.getPiece(destination);

			// If the queen on the right edge of the board, then certain
			// offsets won't be applicable
			if(i == -7 || i == 9 && onRightEdge()){

			}

		}

	}

	@Override
	public String toString() {
		return this.color.isWhite() ? this.name : this.name.toLowerCase();
	}
}
