package chess.model;

import java.util.*;

import chess.Attributes;
import chess.controller.Move;

/**
 * @author Gruppe 45
 *
 */
public class Bishop extends Piece {

	/**
	 * all possible Bishop move offset
	 */
	private static final int[] MOVE_OFFSET = {-9, -7, 7, 9};

	// The name of the piece
	protected String name = "B";

	/**
	 * constructor of the Bishop
	 *
	 * @param position his Position
	 * @param color his Color
	 * @param board on which he stand
	 */
	public Bishop(int position, Attributes.Color color, Board board) {
		super(position, color, board);
		// TODO Auto-generated constructor stub
	}


	@Override
	public String toString() {
		return this.color.isWhite() ? this.name : this.name.toLowerCase();
	}


	@Override
	public void calculateLegalMoves() {
		allLegalMoves = new ArrayList<>();
		for (int i : MOVE_OFFSET) {
			int destination = this.position;
			while (!isOutOfTheBoard(destination)) {
				if ((isInFirstColumn(destination) && (i == -9 || i == 7)) ||
						(isInLastColumn(destination) && (i == 9 || i == -7))) {
					break;
				}
				destination += i;
				if (!isOutOfTheBoard(destination)) {
					if (!isFriendAtTheDestination(destination)) {
						allLegalMoves.add(new Move.CaptureMove(board, this, destination));
						break;
					}
					else if (isDestinationEmpty(destination))
						allLegalMoves.add(new Move.NormalMove(board, this, destination));
				}
			}
		}
	}
}