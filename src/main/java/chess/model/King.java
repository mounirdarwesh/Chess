package chess.model;

import java.util.*;

import chess.Attributes;
import chess.controller.Move;

/**
 * @author TBD
 *
 */
public class King extends Piece {

	/**
	 * all possible King move offset
	 */
	private static final int[] MOVE_OFFSET = {-9, -8, -7, -1, 1, 7, 8, 9};

	// The name of the piece
	protected String name = "K";

	/**
	 * constructor of the King
	 *
	 * @param position his Position
	 * @param color his Color
	 * @param board on which he stand
	 */
	public King(int position, Attributes.Color color, Board board) {
		super(position, color, board);
	}


	@Override
	public String toString() {
		return this.color.isWhite() ? this.name : this.name.toLowerCase();
	}

	@Override
	public void calculateLegalMoves() {
		allLegalMoves = new ArrayList<>();
		for (int i : MOVE_OFFSET) {
			int destination = this.position + i;
			if ((isInFirstColumn(this.position) && (i == -9 || i == -1 || i == 7)) ||
					(isInLastColumn(this.position) && (i == -7 || i == 1 || i == 9))
					||isOutOfTheBoard(destination)) {
				continue;
			}
			if (!isFriendAtTheDestination(destination))
				allLegalMoves.add(new Move.CaptureMove(board, this, destination));
			else if (isDestinationEmpty(destination))
				allLegalMoves.add(new Move.NormalMove(board, this, destination));
		}
	}
}

