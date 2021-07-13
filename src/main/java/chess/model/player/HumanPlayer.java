package chess.model.player;

import chess.Attributes;
import chess.controller.Move;

/**
 * class of human player
 * @author Gr.45
 */
public class HumanPlayer extends Player{
    /**
     * The constructor of the class Player
     * @param color the color that the player chooses to play with
     */
    public HumanPlayer(Attributes.Color color) {
        super(color);
    }

    @Override
    public void makeMove(Move move) {
        move.execute();
    }
}
