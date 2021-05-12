package chess.model;

import chess.Attributes;
import chess.cli.Cli;
import chess.controller.CliController;
import chess.controller.Move;
import chess.view.View;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {

    View view = new Cli();
    CliController cli = new CliController(view, true);
    private static final String EMPTY_FEN = "8/8/8/8/8/8/8/8";
    Board board = new Board();
    Player player = new HumanPlayer(Attributes.Color.BLACK);
    Player player2 = new HumanPlayer(Attributes.Color.WHITE);
    Game game = new Game(cli,board,player,player2);

    @Test
   public void checkMate() {
        /*Piece kingCheckMate = new King(63, Attributes.Color.WHITE, board);
        Piece rook = new Rook (7, Attributes.Color.BLACK, board);
        Piece queen = new Queen (46, Attributes.Color.BLACK, board);
        board.setPiecesOnBoard(EMPTY_FEN);
        board.setPiece(kingCheckMate);
        board.setPiece(queen);
        board.setPiece(rook);
        System.out.println(board.toString());
        assertTrue(player2.checkMate());*/

    }
}