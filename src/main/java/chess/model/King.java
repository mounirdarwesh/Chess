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

    // The name of the piece
    protected String name = "K";

    /**
     * constructor of the King
     *
     * @param position his Position
     * @param color    his Color
     * @param board    on which he stand
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

        //Handling normal and capture moves

        for (int i : MOVE_OFFSETS) {
            int destination = this.position + i;
            if ((isInFirstColumn(this.position) && (i == -9 || i == -1 || i == 7)) ||
                    (isInLastColumn(this.position) && (i == -7 || i == 1 || i == 9))
                    || !isPositionInBounds(destination)) {
                continue;
            }
            if (!isFriendAtTheDestination(destination))
                allLegalMoves.add(new Move.CaptureMove(board, this, destination));
            else if (isDestinationEmpty(destination))
                allLegalMoves.add(new Move.NormalMove(board, this, destination));
        }

        // Handling Castling moves
        for (int j: CASTLING_OFFSETS) {
            int castlingDestination = this.position + j;
            // King side
            if(j == 2) {
                Piece rock = board.getPiece(this.position + 3);
                if(rock == null) continue;
                else if(this.isFirstMove
                        && rock.isFirstMove
                        && rock.getColor() == this.color
                        && board.getPiece(this.position + 1) == null
                        && board.getPiece(this.position + 2) == null){
                    allLegalMoves.add(new Move.CastlingMove(board, this, castlingDestination, rock, castlingDestination-1));
                    continue;
                } else continue;
            }
            // Queen side
            if (j == -2){
                Piece rock = board.getPiece(this.position - 4);

                if(this.isFirstMove
                        && rock.isFirstMove
                        && rock.getColor() == this.color
                        && board.getPiece(this.position - 1) == null
                        && board.getPiece(this.position - 2) == null
                        && board.getPiece(this.position - 3) == null){
                    allLegalMoves.add(new Move.CastlingMove(board, this, castlingDestination, rock, castlingDestination+1));
                    continue;
                } else continue;
            }
        }
    }
}