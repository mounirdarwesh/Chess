package chess.model;

import java.util.*;

import chess.Attributes;
import chess.controller.Move;

/**
 * @author Gruppe 45
 */
public class King extends Piece {

    /**
     * all possible King move offset
     */
    private static final int[] MOVE_OFFSETS = {-9, -8, -7, -1, 1, 7, 8, 9};

    /**
     * all possible King castling offsets
     */
    private static final int[] CASTLING_OFFSETS = {-2, 2};


    /**
     * constructor of the King
     *
     * @param position his Position
     * @param color    his Color
     * @param board    on which he stand
     */
    public King(int position, Attributes.Color color, Board board) {
        super("K", position, color, board);
    }


    @Override
    public void calculateLegalMoves() {
        allLegalMoves = new ArrayList<>();

        //Handling normal and capture moves
        for (int i : MOVE_OFFSETS) {
            boolean firstCol = isInFirstColumn(this.position) && (i == -9 || i == -1 || i == 7);
            boolean lastCol = isInLastColumn(this.position) && (i == -7 || i == 1 || i == 9);
            int destination = this.position + i;
            if (firstCol || lastCol || !isPositionInBounds(destination)) {
                continue;
            }
            if (!isFriendAtTheDestination(destination)) {
                allLegalMoves.add(new Move.CaptureMove(board, this, destination));
            } else if (isDestinationEmpty(destination)) {
                allLegalMoves.add(new Move.NormalMove(board, this, destination));
            }
        }


        // Handling Castling moves

        if (!this.isFirstMove) {
            return;
        }
        for (int j : CASTLING_OFFSETS) {
            int castlingDestination = this.position + j;
            // King side
            if (j == 2) {
                Piece rock = board.getPiece(this.position + 3);
                if (!(rock instanceof Rook) || rock == null) {
                    continue;
                } else if (rock.isFirstMove
                        && rock.getColor() == this.color
                        && board.getPiece(this.position + 1) == null
                        && board.getPiece(this.position + 2) == null) {
                    allLegalMoves.add(new Move.CastlingMove(board, this, castlingDestination, rock));
                    continue;
                } else {
                    continue;
                }
            }
            // Queen side
            else if (j == -2) {
                Piece rock = board.getPiece(this.position - 4);
                if (!(rock instanceof Rook) || rock == null) {
                    continue;
                } else if (rock.isFirstMove
                        && rock.getColor() == this.color
                        && board.getPiece(this.position - 1) == null
                        && board.getPiece(this.position - 2) == null
                        && board.getPiece(this.position - 3) == null) {
                    allLegalMoves.add(new Move.CastlingMove(board, this, castlingDestination, rock));
                    continue;
                } else {
                    continue;
                }
            }
        }
    }
}