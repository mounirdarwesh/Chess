package chess.model;

import java.util.*;

import chess.Attributes;
import chess.controller.Move;

/**
 * @author TBD
 */
public class Rook extends Piece {

    /**
     * all possible Rook move offset
     */
    private static final int[] MOVE_OFFSET = {-8, -1, 1, 8};

    /**
     * constructor of the Rook
     *
     * @param position his Position
     * @param color    his Color
     * @param board    on which he stand
     */
    public Rook(int position, Attributes.Color color, Board board) {
        super("R", position, color, board);
    }

    @Override
    public void calculateLegalMoves() {
        allLegalMoves = new ArrayList<>();
        for (int i : MOVE_OFFSET) {
            int destination = this.position;
            while (isPositionInBounds(destination)) {
                if ((isInFirstColumn(destination) && (i == -1) ||
                        (isInLastColumn(destination) && (i == 1)))) {
                    break;
                }
                destination += i;
                if (isPositionInBounds(destination)) {
                    if (!isFriendAtTheDestination(destination)) {
                        allLegalMoves.add(new Move.CaptureMove(board, this, destination));
                        break;
                    } else if (isDestinationEmpty(destination)) {
                        allLegalMoves.add(new Move.NormalMove(board, this, destination));
                    } else if (isFriendAtTheDestination(destination)) {
                        break;
                    }
                }
            }
        }
    }
}