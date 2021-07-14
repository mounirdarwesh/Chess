package chess.model;

import chess.Attributes;
import chess.model.pieces.King;
import chess.model.pieces.Piece;
import chess.model.pieces.Queen;
import chess.model.pieces.Rook;
import chess.controller.CliController;
import chess.view.cli.Cli;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test of the class Player
 *
 * @author Gruppe45
 */
public class PlayerTest {

    Cli view = new Cli();
    CliController cli = new CliController(view, true, false);
    private static final String EMPTY_FEN = "8/8/8/8/8/8/8/8";



    /**
     * Test checkmate
     */
    @Test
    public void checkMate() {
        Piece kingMate = new King(63, Attributes.Color.BLACK, cli.getGame().getBoard());
        Piece rookM = new Rook(7, Attributes.Color.WHITE, cli.getGame().getBoard());
        Piece queenM = new Queen(46, Attributes.Color.WHITE, cli.getGame().getBoard());
        kingMate.setFirstMove(false);
        cli.getGame().getBoard().setBoardFromFEN(EMPTY_FEN);
        cli.getGame().getBoard().setPiece(kingMate, kingMate.getPosition());
        cli.getGame().getBoard().setPiece(queenM, queenM.getPosition());
        cli.getGame().getBoard().setPiece(rookM, rookM.getPosition());
        cli.getGame().getWhitePlayer().getPlayerPieces().clear();
        cli.getGame().getBlackPlayer().getPlayerPieces().clear();
        cli.getGame().getWhitePlayer().addToPlayersPieces(rookM);
        cli.getGame().getWhitePlayer().addToPlayersPieces(queenM);
        cli.getGame().getBlackPlayer().addToPlayersPieces(kingMate);
        cli.getGame().setCurrentPlayer(cli.getGame().getBlackPlayer());
        System.out.println(cli.getGame().getBoard());
        assertTrue(cli.getGame().isCurrentPlayersKingInCheckMate());
    }

    /**
     * Test if a king is in check
     */
    @Test
    public void isKingInCheck() {
        Piece kingCheckMate = new King(63, Attributes.Color.BLACK, cli.getGame().getBoard());
        Piece rook = new Rook(7, Attributes.Color.WHITE, cli.getGame().getBoard());
        kingCheckMate.setFirstMove(false);
        cli.getGame().getBoard().setBoardFromFEN(EMPTY_FEN);
        cli.getGame().getBoard().setPiece(kingCheckMate, kingCheckMate.getPosition());
        cli.getGame().getBoard().setPiece(rook, rook.getPosition());
        cli.getGame().getWhitePlayer().getPlayerPieces().clear();
        cli.getGame().getBlackPlayer().getPlayerPieces().clear();
        cli.getGame().getWhitePlayer().addToPlayersPieces(rook);
        cli.getGame().getBlackPlayer().addToPlayersPieces(kingCheckMate);
        cli.getGame().setCurrentPlayer(cli.getGame().getBlackPlayer());
        //System.out.println(game.getBoard());
        assertTrue(cli.getGame().isCurrentPlayersKingInCheck());

    }

    /**
     * check if king side Castle is Allowed
     */
    @Test
    public void isKingSideCastleAllowed() {
        assertFalse(cli.getGame().getCurrentPlayer().isKingSideCastleAllowed());
    }

    /**
     * check if Queen side Castle is Allowed
     */
    @Test
    public void isQueenSideCastleAllowed() {
        assertFalse(cli.getGame().getCurrentPlayer().isQueenSideCastleAllowed());
    }

    /**
     * check get the left Time
     */
    @Test
    public void getTimeLeft() {
        cli.getGame().getCurrentPlayer().setTimeLeft(10);
        assertEquals(10,cli.getGame().getCurrentPlayer().getTimeLeft());
    }

    /**
     * check is the Player undid a move
     */
    @Test
    public void hasPlayerUndidAMove() {
        cli.getGame().getCurrentPlayer().setHasPlayerUndidAMove(false);
        assertFalse(cli.getGame().getCurrentPlayer().hasPlayerUndidAMove());
    }

    /**
     * check is the Player redid a move
     */
    @Test
    public void hasPlayerRedidAMove() {
        cli.getGame().getCurrentPlayer().setHasPlayerRedidAMove(true);
        assertTrue(cli.getGame().getCurrentPlayer().hasPlayerRedidAMove());
    }
}
