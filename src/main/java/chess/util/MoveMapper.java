package chess.util;

import chess.controller.Move;
import chess.model.game.Game;
import chess.model.pieces.Piece;

/**
 * A class that maps a message from the client/server to
 * a move
 * @author Gr.45
 */
public class MoveMapper {

    /**
     * Map a string from the client to a valid move
     * @param moveString the request that the client/server has sent
     * @param game the current LAN game
     * @return a valid move
     */
    public static Move map(String moveString, Game game) {
        Move mappedMove = null;
        String[] coordinates = moveString.split("-");

        int from = BoardMapper.mapChessNotationToPosition(coordinates[0]);
        int to = BoardMapper.mapChessNotationToPosition(coordinates[1]);
        Piece toMovePiece = game.getBoard().getPiece(from);
        toMovePiece.calculateLegalMoves();

        for (Move move : toMovePiece.getAllLegalMoves()) {
            if (move.getDestination() == to) {
                mappedMove = move;
                break;
            }
        }
        return mappedMove;
    }
}
