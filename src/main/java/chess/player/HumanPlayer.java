package chess.player;

import chess.Attributes.Color;
import chess.controller.Move;
import chess.model.Game;
import chess.model.King;
import chess.model.Piece;

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

    /**
     * calculate all Enemy legal Moves of a Player
     *
     * @param pieces to his Pieces
     * @return list of legal Moves
     */
    @Override
    public ArrayList<Move> calculateEnemyLegalMoves(ArrayList<Piece> pieces) {
        ArrayList<Move> legalMoves = new ArrayList<>();
        for (Piece piece : pieces) {
            if (piece == null || piece.getColor() == this.getColor()) {
                continue;
            }
            //TODO just capture Move should be added.
            piece.calculateLegalMoves();
            legalMoves.addAll(piece.getAllLegalMoves());
        }
        return legalMoves;
    }

    /**
     * checks if the King is in danger.
     *
     * @param kingPosition king Position
     * @param enemyMoves   all possible enemies Moves
     * @return true if he is in Danger
     */
    @Override
    public boolean isKingInCheck(int kingPosition, ArrayList<Move> enemyMoves) {
        boolean isInCheck = false;
        for (Move move : enemyMoves) {
            isInCheck = kingPosition == move.getDestination()
                    && (move instanceof Move.CaptureMove || move instanceof Move.PromotionMove);
            if (isInCheck)
                break;
        }
        return isInCheck;
    }

    /**
     * get the King Piece of a Player.
     *
     * @param myPieces aList of Board Pieces
     * @return The King
     */
    @Override
    public Piece getKing(ArrayList<Piece> myPieces) {
        Piece king = null;
        for (Piece theKing : myPieces) {
            if (theKing instanceof King && this.color == theKing.getColor()) {
                king = theKing;
                break;
            }
        }
        return king;
    }


    /**
     * check if a Move can Protect the King, otherwise should not be allowed
     *
     * @param piece       to be moved
     * @param destination his destination
     * @param game        the game
     * @return true if he can.
     */
    @Override
    public boolean canProtectKing(Piece piece, int destination, Game game) {
        boolean canProtectKing = false;
        int oldDestination = piece.getPosition();
        Piece temp = null;
        if (game.getBoard().getPiece(destination) != null)
            temp = game.getBoard().getPiece(destination);
        game.getBoard().getPiecesOnBoard().set(oldDestination, null);
        piece.setPosition(destination);
        game.getBoard().setPiece(piece);
        if (!this.isKingInCheck(this.getKing(game.getBoard().getPiecesOnBoard()).getPosition(),
                this.calculateEnemyLegalMoves(game.getBoard().getPiecesOnBoard()))) {
            canProtectKing = true;
        }
        game.getBoard().getPiecesOnBoard().set(destination, temp);
        piece.setPosition(oldDestination);
        game.getBoard().setPiece(piece);
        return canProtectKing;
    }

}
