package chess.model;

import chess.Attributes.Color;
import chess.controller.Move;

import java.util.ArrayList;

/**
 * @author Ahmad Mohammad
 */
public class Queen extends Piece {

    /**
     * The name of the piece
     */
    protected String name = "Q";

    /**
     * The possible offsets of the queen
     */
    private static final int[] MOVE_OFFSETS = {-9, -7, -8, -1, 1, 7, 8, 9};

    /**
     * The constructor of the Queen Class
     *
     * @param position The position of the Pawn
     * @param color    The type of the Pawn
     * @param board    The board
     */
    public Queen(int position, Color color, Board board) {
        super(position, color, board);
    }

    @Override
    public void calculateLegalMoves() {
        allLegalMoves = new ArrayList<>();

        // Iterating through the possible offsets
        for (int i : MOVE_OFFSETS) {
            int destination = this.position;
            // Always making sure that the Queen is staying within the board
            while (true) {

                if ((isInFirstColumn(destination) && (i == -9 || i == -1 || i == 7)) ||
                        (isInLastColumn(destination) && (i == -7 || i == 1 || i == 9))) {
                    break;
                }
                destination += i;
                if (!isPositionInBounds(destination)) {
                    break;
                } else {
                    if (board.getPiece(destination) == null) {
                        allLegalMoves.add(new Move.NormalMove(board, this, destination));
                    } else {
                        if (board.getPiece(destination).getColor() != this.color) {
                            allLegalMoves.add(new Move.CaptureMove(board, this, destination));
                        }
                        break;
                    }
                }
            }
        }
    }

    @Override
    public String toString() {
        return this.color.isWhite() ? this.name : this.name.toLowerCase();
    }

}

