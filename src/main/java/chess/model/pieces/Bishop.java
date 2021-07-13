package chess.model.pieces;

import java.util.*;
import chess.Attributes;
import chess.controller.Move;
import chess.model.Board;

/**
 * @author Gruppe 45
 */
public class Bishop extends Piece {

    /**
     * all possible Bishop move offset
     */
    private static final int[] MOVE_OFFSET = {-9, -7, 7, 9};


    /**
     * constructor of the Bishop
     * @param position his Position
     * @param color    his Color
     * @param board    on which he stand
     */
    public Bishop(int position, Attributes.Color color, Board board) {
        super( 350, position, color, board);
        this.name = "B";
    }


    @Override
    public void calculateLegalMoves() {
        allLegalMoves = new ArrayList<>();
        for (int i : MOVE_OFFSET) {
            int destination = this.position;
            while (isPositionInBounds(destination)) {
                boolean firstCol = isInFirstColumn(destination) && (i == -9 || i == 7);
                boolean lastCol = isInLastColumn(destination) && (i == 9 || i == -7);
                if (firstCol || lastCol) {
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
        return color.isWhite() ? "♗" : "♝";
    }
}
