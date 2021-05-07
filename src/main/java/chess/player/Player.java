package chess.player;

import chess.Attributes.Color;
import chess.controller.Move;
import chess.model.Game;
import chess.model.Piece;

import java.util.ArrayList;

/**
 * @author TBD
 */
public abstract class Player {

    /**
     * The color of the pieces
     */
    protected Color color;

    /**
     * The game
     */
    protected Game game;


    /**
     * The constructor of the class Player
     *
     * @param color the color that the player chooses to play with
     */
    public Player(Color color) {
        this.color = color;
    }


    /*
     * Making the move
     */
    public abstract void makeMove(Move move);


    /**
     * Getter of the players color to play
     *
     * @return the color
     */
    public Color getColor() {
        return color;
    }

    /**
     * calclate all legal Moves of a Player
     *
     * @param pieces to his Pieces
     * @return list of legal Moves
     */
    public abstract ArrayList<Move> calculateEnemyLegalMoves(ArrayList<Piece> pieces);

    /**
     * checks if the King is in danger.
     *
     * @param kingPosition king Position
     * @param enemyMoves   all possible enemies Moves
     * @return true if he is in Danger
     */
    public abstract boolean isKingInCheck(int kingPosition, ArrayList<Move> enemyMoves);

    /**
     * get the King Piece of a Player.
     *
     * @param myPieces aList of Board Pieces
     * @return The King
     */
    public abstract Piece getKing(ArrayList<Piece> myPieces);

    /**
     * check if a Move can Protect the King, otherwise should not be allowed
     *
     * @param piece       to be moved
     * @param destination his destination
     * @param game        the game
     * @return true if he can.
     */
    public abstract boolean canProtectKing(Piece piece, int destination, Game game);

}
