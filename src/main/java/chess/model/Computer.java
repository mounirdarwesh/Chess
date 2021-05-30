package chess.model;

import chess.Attributes.Color;
import chess.controller.Move;

import java.util.List;


/**
 * @author Ahmad Mohammad
 */
public class Computer extends Player {


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
        //TODO
    }

    public void aiComp(){
        List<Move> compMoves = calculatePlayerMoves();
        compMoves.get(2).execute();
    }

}
