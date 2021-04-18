package chess.player;
import chess.Constants;
import chess.Game;

/**
 * @author TBD
 *
 */
public interface Player {

	Constants getColor();

	abstract void makeMove();

	abstract void addGame(Game game);


}
