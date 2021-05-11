package chess.model;

import java.util.*;

import chess.PGN.FenUtilities;

/**
 * @author Ahmad Mohammad
 * The Board class
 */
public class Board {

    /**
     * The pieces on the board
     */
    private ArrayList<Piece> piecesOnBoard;

    /**
     * White beaten pieces
     */
    private ArrayList<Piece> whiteBeaten = new ArrayList<Piece>();

    /**
     * Black beaten pieces
     */
    private ArrayList<Piece> blackBeaten = new ArrayList<Piece>();

    /**
     * The start FEN of the board
     */
    private static final String START_FEN = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR";


    /**
     * The constructor of the board class
     */
    public Board() {
        // Initializing the board
        this.piecesOnBoard = FenUtilities.loadBoardFromFEN(START_FEN, this);
    }

    /**
     * Getter of the piece on the board
     *
     * @param index the index of the searched piece
     */
    public Piece getPiece(int index) {
        return this.piecesOnBoard.get(index);
    }

    /**
     * Setter of the board of the game
     *
     * @param piece the board to set
     */
    public void setPiece(Piece piece) {
        this.piecesOnBoard.set(piece.getPosition(), piece);
    }


    /**
     * Getter for all the pieces on the board
     *
     * @return the pieces on the board
     */
    public ArrayList<Piece> getPiecesOnBoard() {
        return this.piecesOnBoard;
    }

    public void setPiecesOnBoard(String piecesOnBoard) {
        this.piecesOnBoard = FenUtilities.loadBoardFromFEN(piecesOnBoard,this);
    }


    /**
     * @return the whiteBeaten
     */
    public ArrayList<Piece> getWhiteBeaten() {
        return whiteBeaten;
    }

    /**
     * @return the blackBeaten
     */
    public ArrayList<Piece> getBlackBeaten() {
        return blackBeaten;
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
                } else board_.append(" ").append(piece);
            }
            board_.append("\n");
        }
        board_.append("  a b c d e f g h");
        return board_.toString();
    }
}
