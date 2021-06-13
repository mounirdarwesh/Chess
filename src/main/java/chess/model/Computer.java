package chess.model;

import chess.Attributes.Color;
import chess.controller.Move;
import java.util.List;


/**
 * @author Ahmad Mohammad
 */
public class Computer extends Player {

    boolean noMoves;

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
        noMoves = false;
        int bestValue = 0;
        Move optimalMove = null;
        for (Move move : this.calculatePlayerMoves()) {
            Piece piece = Game.getBoard().getPiece(move.getDestination());
            if(!Game.makeTempMoveAndCheck(move)){
                noMoves = true;
                continue;
            }
            noMoves = false;
            if (piece != null) {
                int currentValue = piece.getValue();
                if (currentValue >= bestValue) {
                    bestValue = currentValue;
                    optimalMove = move;
                    noMoves = false;
                }
            }
        }
        if(optimalMove == null || noMoves) {
            List<Move> compMoves = this.calculatePlayerMoves();
            optimalMove = compMoves.get((int) (Math.random() * compMoves.size()));
        }
        System.out.println(this.calculatePlayerMoves());
        System.out.println(noMoves);
        System.out.println(optimalMove);
        return optimalMove;
    }

    public boolean isNoMoves() {
        return noMoves;
    }
}
