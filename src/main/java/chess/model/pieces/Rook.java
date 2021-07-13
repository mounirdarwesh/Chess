package chess.model.pieces;

import java.util.*;
import chess.Attributes;
import chess.controller.Move;
import chess.model.Board;

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
        super(525, position, color, board);
        this.name = "R";
    }

    @Override
    public void calculateLegalMoves() {
        allLegalMoves = new ArrayList<>();
        for (int i : MOVE_OFFSET) {
            int destination = this.position;
            while (isPositionInBounds(destination)) {
                boolean firstCol = isInFirstColumn(destination) && i == -1;
                boolean secondCol = isInLastColumn(destination) && i == 1;
                if (firstCol || secondCol) {
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

    /**
     * GUI Symbol
     * @return the Symbol of the Piece
     */
    @Override
    public String getSymbol() {
        return color.isWhite() ? "♖" : "♜";
    }
}

