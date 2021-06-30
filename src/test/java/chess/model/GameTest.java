package chess.model;

import chess.Attributes;
import chess.view.Cli;
import chess.controller.CliController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * class Game Test
 */
public class GameTest {
    Cli view = new Cli();
    CliController cli = new CliController(view, true, true);
    private static final String EMPTY_FEN = "8/8/8/8/8/8/8/8";
    Board board = new Board();
    Player player = new HumanPlayer(Attributes.Color.WHITE);
    Player playerB = new HumanPlayer(Attributes.Color.BLACK);
    Game game = new Game(cli, board, player, playerB);

    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    /**
     * to get System Out from Console.
     */
    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    /**
     * check Game Status when the Players King in Check.
     */
    @Test
    public void checkGameStatus() {
        board.setPiecesOnBoard(EMPTY_FEN);
        Piece kingW = new King(1, Attributes.Color.WHITE, board);
        Piece rookB = new Rook(2, Attributes.Color.BLACK, board);
        kingW.isFirstMove = false;
        board.setPiece(kingW);
        board.setPiece(rookB);
        playerB.addToPlayersPieces(rookB);
        player.addToPlayersPieces(kingW);
        game.checkGameStatus();
        assertEquals("White Player's king is in check.", outputStreamCaptor.toString()
                .trim());
    }

    /**
     * check Game Status when the Players King Checkmate.
     */
    @Test
    public void checkGameStatusCheckMate() {
        Piece kingCheckMate = new King(63, Attributes.Color.WHITE, board);
        Piece rook = new Rook(7, Attributes.Color.BLACK, board);
        Piece queen = new Queen(46, Attributes.Color.BLACK, board);
        kingCheckMate.isFirstMove = false;
        board.setPiecesOnBoard(EMPTY_FEN);
        board.setPiece(kingCheckMate);
        board.setPiece(queen);
        board.setPiece(rook);
        player.addToPlayersPieces(kingCheckMate);
        playerB.addToPlayersPieces(rook);
        playerB.addToPlayersPieces(queen);
        game.checkGameStatus();
        assertEquals("Black player has won the game!", outputStreamCaptor.toString()
                .trim());
    }

    /**
     * test white player
     */
    @Test
    public void getWhitePlayer() {
        assertEquals("White Player", Game.getWhitePlayer().toString());
    }

    /**
     * test black player
     */
    @Test
    public void getBlackPlayer() {
        assertEquals("Black player", Game.getBlackPlayer().toString());
    }
}
