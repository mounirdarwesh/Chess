package chess.model;

import java.util.ArrayList;

import chess.Attributes;
import chess.Attributes.Color;
import chess.controller.Move;

/**
 * @author TBD
 */
public class Pawn extends Piece {

    /**
     * Pawn move offset
     */
    private static final int[] MOVE_OFFSET = {8, 16, 7, 9};

    /**
     * The constructor of the Pawn Class
     *
     * @param position The position of the Pawn
     * @param color    The type of the Pawn
     * @param board    The board
     */
    public Pawn(int position, Color color, Board board) {
        super("P", 100, position, color, board);
    }


    @Override
    public void calculateLegalMoves() {
        allLegalMoves = new ArrayList<>();

        // Iterating throw the possible offsets
        for (int i : MOVE_OFFSET) {

            // The destination to which the pawn is moving to
            // If the piece is a white piece the getDirection method will return
            // the number 1, and -1 if the piece is a black piece
            // This will tell the pawn in which direction on the board it should move
            int destination = position + i * color.getDirection();

            // If the destination is not on the board then continue the loop
            // and get the next offset
            if (!isPositionInBounds(destination)) {
                continue;
            }

            // Get the piece on that destination position
            Piece pieceAtDest = board.getPiece(destination);

            // Switch through the cases
            switch (i) {
                case 8:
                    // Add if applicable a one step move to the legal moves
                    oneStepForward(pieceAtDest, destination);
                    continue;
                case 16:
                    // Add if applicable a two step move to the legal moves
                    twoStepsForward(pieceAtDest, destination);
                    continue;
                case 7:
                    leftUpStep(pieceAtDest, destination);
                    continue;
                case 9:
                    // The pawn should not be on one of the sides depending on it's color
                    rightUpStep(pieceAtDest, destination);
            }
        }
    }

    /**
     * If the player chooses to move the pawn one step forward
     *
     * @param pieceAtDest The piece at that destination
     * @param destination The destination of the move
     */
    private void oneStepForward(Piece pieceAtDest, int destination) {

        // If there is no piece on the destination
        if (pieceAtDest == null) {

            // Check if the pawn can promote itself
            if (canPromote()) {
                allLegalMoves.add(new Move.PromotionMove(board, this, destination, Game.charToPromote));
            }

            // If not, then add a normal move
            else {
                allLegalMoves.add(new Move.NormalMove(board, this, destination));
            }
        }

        // If there is a piece on that destination then don't do anything
    }

    /**
     * If the player chooses to move the pawn two steps forward
     *
     * @param pieceAtDest The piece at that destination
     * @param destination The destination of the move
     */
    private void twoStepsForward(Piece pieceAtDest, int destination) {

        // Checking if there is no piece at desired destination and
        // if it is the first move of the pawn
        if (isFirstMove && pieceAtDest == null) {

            // The position between the pawn and it's desired destination
            int blockedSquare = position + 8 * color.getDirection();
            // The piece at that position
            Piece blockedPiece = board.getPiece(blockedSquare);

            // If there is no piece in between
            if (blockedPiece == null) {
                allLegalMoves.add(new Move.DoublePawnMove(board, this, destination));
            }
        }
        // If the above criteria is not fulfilled, do nothing
    }


    /**
     * If the player chooses to move the pawn to capture
     *
     * @param pieceAtDest The piece at that destination
     * @param destination The destination of the move
     */
    private void leftUpStep(Piece pieceAtDest, int destination) {

        // The pawn should not be on one of the sides depending on it's color
        if (leftUpColumnException()) return;

        // If there is an enemy piece on the destination, then capture it
        if (leftEnPassant() && pieceAtDest == null && Game.getCurrentPlayer().isAllowEnPassant()) {
            allLegalMoves.add(new Move.EnPassantMove(board, this, destination, position - color.getDirection()));
        } else if (pieceAtDest != null && pieceAtDest.color != this.color) {
            // If the pawn can capture and promote itself then let it do it
            if (canPromote()) {
                allLegalMoves.add(new Move.PromotionMove(board, this, destination, Game.charToPromote));
            } // else just capture the enemy piece
            else {
                allLegalMoves.add(new Move.CaptureMove(board, this, destination));
            }
        }
    }

    /**
     * If the player chooses to move the pawn to capture
     *
     * @param pieceAtDest The piece at that destination
     * @param destination The destination of the move
     */
    private void rightUpStep(Piece pieceAtDest, int destination) {

        // The pawn should not be on one of the sides depending on it's color
        if (rightUpColumnException()) return;

        // If there is an enemy piece on the destination, then capture it
        if (rightEnPassant() && pieceAtDest == null && Game.getCurrentPlayer().isAllowEnPassant()) {
            allLegalMoves.add(new Move.EnPassantMove(board, this, destination, position + color.getDirection()));
        } else if (pieceAtDest != null && pieceAtDest.color != this.color) {
            // If the pawn can capture and promote itself then let it do it
            if (canPromote()) {
                allLegalMoves.add(new Move.PromotionMove(board, this, destination, Game.charToPromote));
            } // else just capture the enemy piece
            else {
                allLegalMoves.add(new Move.CaptureMove(board, this, destination));
            }
        }
    }

    /**
     * check if the pawn can promote
     *
     * @return true if it can promote to another piece, false otherwise
     */
    private boolean canPromote() {
        return color.isWhite() && 48 <= position && position <= 55 ||
                color.isBlack() && 8 <= position && position <= 15;
    }

    /**
     * Check of the pawn can perform En Passant
     *
     * @return true if it can, false otherwise
     */
    private boolean leftEnPassant() {
        Piece opponentPawn = board.getPiece(position - color.getDirection());
        return opponentPawn instanceof Pawn
                && opponentPawn.getColor() != this.color;
    }

    /**
     * Check of the pawn can perform En Passant
     *
     * @return true if it can, false otherwise
     */
    private boolean rightEnPassant() {
        Piece opponentPawn = board.getPiece(position + color.getDirection());
        return opponentPawn instanceof Pawn
                && opponentPawn.getColor() != this.color;
    }

    /**
     * Check if the pawn on a certain column depending on its color
     *
     * @return true if the pawn on a certain column that will not make him make the move, false otherwise
     */
    private boolean leftUpColumnException() {
        return color.isWhite() && isInFirstColumn(this.position) ||
                color.isBlack() && isInLastColumn(this.position);
    }

    /**
     * Check if the pawn on a certain column depending on its color
     *
     * @return true if the pawn on a certain column that will not make him make the move, false otherwise
     */
    private boolean rightUpColumnException() {
        return color.isWhite() && isInLastColumn(this.position) ||
                color.isBlack() && isInFirstColumn(this.position);
    }

    /**
     * GUI Symbol
     *
     * @return the Symbol of the Piece
     */
    @Override
    public String getSymbol() {
        String symbol;
        if (color == Attributes.Color.BLACK) {
            symbol = "♟";
        } else {
            symbol = "♙";
        }
        return symbol;
    }
}
