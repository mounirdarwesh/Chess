package chess.model;

import chess.Attributes.Color;
import chess.controller.Move;

import java.util.ArrayList;
import java.util.List;


/**
 * @author Ahmad Mohammad
 */
public class Computer extends Player {

    /**
     * List of all the pieces that the computer can capture
     */
    private List<Piece> availableToCapture;

    /**
     * The constructor of the computer class
     *
     * @param color the color that the computer is left with
     */
    public Computer(Color color) {
        super(color);
    }

    @Override
    public void makeMove(Move move) {
        move.execute();
    }

    public Move evaluate() {
        int bestValue = 0;
        Move optimalMove = null;
        for (Move move : this.calculatePlayerMoves()) {
            Piece piece = Game.getBoard().getPiece(move.getDestination());
            if (piece != null) {
                int currentValue = piece.getValue();
                if (currentValue >= bestValue) {
                    bestValue = currentValue;
                    optimalMove = move;
                }
            }
        }
        if(optimalMove == null) {
            List<Move> compMoves = this.calculatePlayerMoves();
            optimalMove = compMoves.get((int) (Math.random() * compMoves.size()));
        }
        return optimalMove;
    }
}
