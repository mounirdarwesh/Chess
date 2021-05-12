package chess.model;

import java.util.*;

import chess.Attributes;
import chess.controller.Move;

/**
 * @author Gruppe 45
 */
public class Knight extends Piece {

    // The name of the piece
    protected String name = "N";

    /**
     * all possible Knight move offset
     */
    private final static int[] MOVE_OFFSET = {-17, -15, -10, -6, 6, 10, 15, 17};

    /**
     * constructor of the Knight
     *
     * @param position his Position
     * @param color    his Color
     * @param board    on which he stand
     */
    public Knight(int position, Attributes.Color color, Board board) {
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
            if ((isInFirstColumn(this.position) && (i == -17 || i == -10 || i == 6 || i == 15)) ||
                    (isInLastColumn(this.position) && (i == -15 || i == -6 || i == 10 || i == 17)) ||
                    (isInSecondColumn(this.position) && (i == -10 || i == 6)) ||
                    (isInSeventhColumn(this.position) && (i == -6 || i == 10))
                    || !isPositionInBounds(destination)) {
                continue;
            }
            if (!isFriendAtTheDestination(destination))
                allLegalMoves.add(new Move.CaptureMove(board, this, destination));
            else if (isDestinationEmpty(destination))
                allLegalMoves.add(new Move.NormalMove(board, this, destination));
        }
    }
}


