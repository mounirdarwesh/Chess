package chess.model;

import chess.model.game.Game;
import chess.model.pieces.Piece;
import chess.pgn.FenUtilities;
import java.util.List;

/**
 * @author Gr.45
 * The Board class
 */
public class Board {

    /**
     * The start FEN of the board
     */
    private static final String START_FEN = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR";

    /**
     * The pieces on the board
     */
    private List<Piece> piecesOnBoard;

    /**
     * The game that being played on the board
     */
    private Game game;

    /**
     * The constructor of the board class
     */
    public Board() {
        // Initializing the board
        this.piecesOnBoard = FenUtilities.loadBoardFromFEN(START_FEN, this);
    }

    /**
     * Getter of the piece on the board
     * @param position the index of the searched piece
     * @return get specific piece form Board
     */
    public Piece getPiece(int position) {
        return this.piecesOnBoard.get(position);
    }

    /**
     * Setter of the board of the game
     * @param piece the board to set
     * @param position of piece
     */
    public void setPiece(Piece piece, int position) {
        if (piece != null) {
            piece.setPosition(position);
        }
        this.piecesOnBoard.set(position, piece);
    }

    /**
     * setter for the board
     * @param piecesOnBoard FEN String, contains the Pieces.
     */
    public void setPiecesOnBoard(List<Piece> piecesOnBoard) {
        this.piecesOnBoard = piecesOnBoard;
    }

    /**
     * Setting the board as the FEN String
     * @param fen the fen notation ot set
     */
    public void setBoardFromFEN(String fen) {
        this.piecesOnBoard = FenUtilities.loadBoardFromFEN(fen, this);
    }

    /**
     * getter for all Pieces on Board
     * @return pieces on the Board
     */
    public List<Piece> getPiecesOnBoard() {
        return this.piecesOnBoard;
    }

    @Override
    public String toString() {

        StringBuilder board_ = new StringBuilder();

        for (int rank = 8; rank > 0; rank--) {
            board_.append(rank);
            for (int file = 0; file < 8; file++) {
                Piece piece = piecesOnBoard.get((rank - 1) * 8 + file);
                if (piece == null) {
                    board_.append("  ");
                } else {
                    board_.append(" ").append(piece);
                }
            }
            board_.append("\n");
        }
        board_.append("  a b c d e f g h");
        return board_.toString();
    }

    /**
     * Getter for the game
     * @return the game
     */
    public Game getGame() {
        return game;
    }

    /**
     * Setter for the game
     * @param game the game
     */
    public void setGame(Game game) {
        this.game = game;
    }
}
