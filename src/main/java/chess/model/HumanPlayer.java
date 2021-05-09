package chess.model;

import chess.Attributes.Color;
import chess.controller.Move;
import chess.model.Game;
import chess.model.King;
import chess.model.Piece;
import chess.model.Player;

import java.util.ArrayList;

/**
 * @author Ahmad Mohammad
 */
public class HumanPlayer extends Player {


    /**
     * The constructor of the class HumanPlayer
     *
     * @param color the color that the player chooses to play with
     */
    public HumanPlayer(Color color) {
        super(color);
    }

    @Override
    public void makeMove(Move move) {
        move.execute();
    }

}
