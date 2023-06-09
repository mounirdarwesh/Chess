package chess.model.pieces;

import java.util.*;
import chess.Attributes;
import chess.controller.Move;
import chess.model.Board;

/**
 * @author Gruppe 45
 */
public class King extends Piece {

    /**
     * all possible King move offset
     */
    private static final int[] MOVE_OFFSETS = {-9, -8, -7, -1, 1, 7, 8, 9};


    /**
     * Boolean to check if the king can preform a queen side castle
     */
    private boolean queenSideCastle;



    /**
     * Boolean to check if the king can preform a king side castle
     */
    private boolean kingSideCastle;

    /**
     * constructor of the King
     *
     * @param position his Position
     * @param color    his Color
     * @param board    on which he stand
     */
    public King(int position, Attributes.Color color, Board board) {
        super(10000, position, color, board);
        this.name = "K";
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
        if (this.isFirstMove) {
            handleKingSideCastle();
            handleQueenSideCastle();
        }
    }

    /**
     * King side Castling
     */
    private void handleKingSideCastle() {
        int castlingDestination = this.position + 2;
        Piece rock = board.getPiece(this.position + 3);
        if (!(rock instanceof Rook) || rock == null) {
            kingSideCastle = false;
            return;
        } else if (rock.isFirstMove
                && rock.getColor() == this.color
                && board.getPiece(this.position + 1) == null
                && board.getPiece(this.position + 2) == null) {
            kingSideCastle = true;
            allLegalMoves.add(new Move.CastlingMove(board, this, castlingDestination, rock));
        }
    }

    /**
     * Queen side Castling
     */
    private void handleQueenSideCastle() {
        int castlingDestination = this.position - 2;
        Piece rock = board.getPiece(this.position - 4);
        if (!(rock instanceof Rook) || rock == null) {
            kingSideCastle = false;
            return;
        } else if (rock.isFirstMove
                && rock.getColor() == this.color
                && board.getPiece(this.position - 1) == null
                && board.getPiece(this.position - 2) == null
                && board.getPiece(this.position - 3) == null) {
            queenSideCastle = true;
            allLegalMoves.add(new Move.CastlingMove(board, this, castlingDestination, rock));
        }
    }

    /**
     * check if King done King side Castle
     * @return true, if already done
     */
    public boolean isKingSideCastle() {
        return kingSideCastle;
    }

    /**
     * check if King done Queen side Castle
     * @return true, if already done
     */
    public boolean isQueenSideCastle() {
        return queenSideCastle;
    }

    /**
     * set King side Castle
     * @param kingSideCastle kingSideCastle
     */
    public void setKingSideCastle(boolean kingSideCastle) {
        this.kingSideCastle = kingSideCastle;
    }

    /**
     * set Queen side Castle
     * @param queenSideCastle queenSideCastle
     */
    public void setQueenSideCastle(boolean queenSideCastle) {
        this.queenSideCastle = queenSideCastle;
    }

    /**
     * GUI Symbol
     *
     * @return the Symbol of the Piece
     */
    @Override
    public String getSymbol() {
        return color.isWhite() ? "♔" : "♚";
    }
}
