package chess.model;

import chess.Attributes;
import chess.cli.Cli;
import chess.controller.CliController;
import chess.view.View;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test of the class Player
 *
 * @author Gruppe45
 */
public class PlayerTest {

    Cli view = new Cli();
    CliController cli = new CliController(view, true, true);
    private static final String EMPTY_FEN = "8/8/8/8/8/8/8/8";
    Board board = new Board();
    Player player = new HumanPlayer(Attributes.Color.WHITE);
    Player playerB = new HumanPlayer(Attributes.Color.BLACK);
    Game game = new Game(cli, board, player, playerB);

    /**
     * Test checkmate
     */
    @Test
    public void checkMate() {
        Piece kingCheckMate = new King(63, Attributes.Color.BLACK, board);
        Piece rook = new Rook(7, Attributes.Color.WHITE, board);
        Piece queen = new Queen(46, Attributes.Color.WHITE, board);
        kingCheckMate.isFirstMove = false;
        board.setPiecesOnBoard(EMPTY_FEN);
        board.setPiece(kingCheckMate);
        board.setPiece(queen);
        board.setPiece(rook);
        player.addToPlayersPieces(rook);
        player.addToPlayersPieces(queen);
        playerB.addToPlayersPieces(kingCheckMate);
        assertTrue(playerB.isCheckMate());
    }

    /**
     * Test if a king is in check
     */
    @Test
    public void isKingInCheck() {
        Piece kingCheckMate = new King(63, Attributes.Color.BLACK, board);
        Piece rook = new Rook(7, Attributes.Color.WHITE, board);
        kingCheckMate.isFirstMove = false;
        board.setPiecesOnBoard(EMPTY_FEN);
        board.setPiece(kingCheckMate);
        board.setPiece(rook);
        player.addToPlayersPieces(rook);
        playerB.addToPlayersPieces(kingCheckMate);
        assertTrue(playerB.isKingInCheck());

    }
}