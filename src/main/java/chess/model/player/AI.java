package chess.model.player;

import chess.Attributes.Color;
import chess.controller.Move;
import chess.model.game.Game;
import chess.model.pieces.Piece;
import chess.model.player.Player;

import java.util.List;


/**
 * class of the AI
 * @author Gr.45
 */
public class AI extends Player {

    /**
     * the connected game
     */
    private Game game;

    /**
     * The constructor of the computer class
     * @param game that plays on
     * @param color the color that the computer is left with
     */
    public AI(Color color, Game game) {
        super(color);
        this.game = game;
    }

    @Override
    public void makeMove(Move move) {
        move.execute();
    }

    /**
     * evaluate a move
     *
     * @return optimalMove
     */
    public Move evaluate() {
        boolean noMoves = false;
        int bestValue = 0;
        Move optimalMove = null;
        for (Move move : this.calculatePlayerMoves()) {
            Piece piece = game.getBoard().getPiece(move.getDestination());
            if (!game.makeTempMoveAndCheck(move)) {
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
        if (optimalMove == null || noMoves) {
            List<Move> compMoves = this.calculatePlayerMoves();
            optimalMove = compMoves.get((int) (Math.random() * compMoves.size()));
        }
        if (optimalMove instanceof Move.PromotionMove) {
            game.setCharToPromote('q');
        }
        return optimalMove;
    }
}
