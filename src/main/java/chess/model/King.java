package chess.model;

import java.util.*;

import chess.Attributes;
import chess.controller.Move;

/**
 * @author Gruppe 45
 *
 */
public class King extends Piece {

	/**
	 * all possible King move offset
	 */
	private static final int[] MOVE_OFFSET = {-9, -8, -7, -1, 1, 7, 8, 9};
	private static final int[] MOVE_OFFSETCASTLING = {-2, 2};
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

		int destinationKingSideCastlingKing = this.position + 2;
		int destinationKingSideCastlingRook = board.getPiecesOnBoard().get(7).position - 2;
		int destinationQueenSideCastlingKing = this.position - 2;
		int destinationQueenSideCastlingRook = board.getPiecesOnBoard().get(0).position + 3;
		if(this.isFirstMove && board.getPiecesOnBoard().get(7).isFirstMove){
			if(board.getPiecesOnBoard().get(5) == null && board.getPiecesOnBoard().get(6) == null){
				allLegalMoves.add(new Move.CastlingMove(board, this, destinationKingSideCastlingKing));
				allLegalMoves.add(new Move.CastlingMove(board, board.getPiecesOnBoard().get(7), destinationKingSideCastlingRook));
			}
		}else if(this.isFirstMove && board.getPiecesOnBoard().get(0).isFirstMove){
			if(board.getPiecesOnBoard().get(1) == null && board.getPiecesOnBoard().get(2) == null
					&& board.getPiecesOnBoard().get(3) == null){
				allLegalMoves.add(new Move.CastlingMove(board, this, destinationQueenSideCastlingKing));
				allLegalMoves.add(new Move.CastlingMove(board, board.getPiecesOnBoard().get(7), destinationQueenSideCastlingRook));
			}
		}

	}


}

