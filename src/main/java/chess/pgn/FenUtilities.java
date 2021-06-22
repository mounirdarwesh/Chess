package chess.pgn;

import java.util.*;
import chess.Attributes.Color;
import chess.model.*;

/**
 * @author Ahmad Mohammad
 * The class where it handles the Portable Game Notation
 */
public class FenUtilities {

    /**
     * The pieces on the board
     */
    private static List<Piece> piecesOnBoard;

    /**
     * The constructor of the FenUtilities class
     */
    private FenUtilities() {
        throw new RuntimeException("I am NOT instantiable!");
    }

    /**
     * This is to load the board from a FEN string
     * @param fen the FEN string
     * @return board  the board
     */
    public static List<Piece> loadBoardFromFEN(String fen, Board board) {
        piecesOnBoard = new ArrayList<>(Collections.nCopies(64, null));
        int file = 0;
        int rank = 7;
        for (char c : fen.toCharArray()) {
            if (c == '/') {
                file = 0;
                rank--;
            } else {
                //checks if a character is a digit
                if (Character.isDigit(c)) {
                    file += Character.getNumericValue(c);
                } else {
                    int position = rank * 8 + file;
                    if(Character.isUpperCase(c)) {
                        initBoardWhitePieces(board, position, c);
                    } else {
                        initBoardBlackPieces(board, position, c);
                    }
                    file++;
                }
            }
        }
        return piecesOnBoard;
    }

    /**
     * Here where the white pieces will get initialized for the board
     * @param board The board of the game
     * @param position The position of the piece
     * @param c The char, aka, the string representation of the piece
     */
    private static void initBoardWhitePieces(Board board, int position, char c) {
        switch (c) {
            case 'R':
                piecesOnBoard.set(position, new Rook(position, Color.WHITE, board));
                break;
            case 'N':
                piecesOnBoard.set(position, new Knight(position, Color.WHITE, board));
                break;
            case 'B':
                piecesOnBoard.set(position, new Bishop(position, Color.WHITE, board));
                break;
            case 'Q':
                piecesOnBoard.set(position, new Queen(position, Color.WHITE, board));
                break;
            case 'K':
                piecesOnBoard.set(position, new King(position, Color.WHITE, board));
                break;
            case 'P':
                piecesOnBoard.set(position, new Pawn(position, Color.WHITE, board));
                break;
        }
    }

    /**
     * Here where the black pieces will get initialized for the board
     * @param board The board of the game
     * @param position The position of the piece
     * @param c The char, aka, the string representation of the piece
     */
    private static void initBoardBlackPieces(Board board, int position, char c) {
        switch (c) {
            case 'r':
                piecesOnBoard.set(position, new Rook(position, Color.BLACK, board));
                break;
            case 'n':
                piecesOnBoard.set(position, new Knight(position, Color.BLACK, board));
                break;
            case 'b':
                piecesOnBoard.set(position, new Bishop(position, Color.BLACK, board));
                break;
            case 'q':
                piecesOnBoard.set(position, new Queen(position, Color.BLACK, board));
                break;
            case 'k':
                piecesOnBoard.set(position, new King(position, Color.BLACK, board));
                break;
            case 'p':
                piecesOnBoard.set(position, new Pawn(position, Color.BLACK, board));
                break;
        }
    }

    /**
     *
     * @param board
     * @return
     */
    public static String createFENFromGame(Board board) {
        return loadFENFromBoard(board) + " " +
                loadPlayerText() + " " +
                loadCastleInformation() + " " +
                loadEnPassantInformation() + " ";
    }

    /**
     *
     * @return
     */
    private static String loadEnPassantInformation() {
        Piece enPassantPawn = Game.getEnPassantPawn();
        if(enPassantPawn != null) {
            for (Map.Entry<String, Integer> entry : MapBoard.mapper.entrySet()) {
                if (entry.getValue().equals(enPassantPawn.getPosition() +
                        8 * - enPassantPawn.getColor().getDirection())) {
                    return entry.getKey();
                }
            }
        }
        return "-";
    }

    /**
     *
     * @return
     */
    private static String loadCastleInformation() {
        StringBuilder castleInfo = new StringBuilder();

        if(Game.getWhitePlayer().isKingSideCastleAllowed()){
            castleInfo.append("K");
        }
        if(Game.getWhitePlayer().isQueenSideCastleAllowed()){
            castleInfo.append("Q");
        }
        if(Game.getBlackPlayer().isKingSideCastleAllowed()){
            castleInfo.append("k");
        }
        if(Game.getBlackPlayer().isQueenSideCastleAllowed()){
            castleInfo.append("q");
        }

        return castleInfo.toString().isEmpty() ? "-" : castleInfo.toString();
    }

    /**
     *
     * @return
     */
    private static String loadPlayerText() {
        return Game.getCurrentPlayer().toString().substring(0, 1).toLowerCase();
    }

    /**
     * This is to create a FEN string from the current board
     * @param board The current board
     * @return fen  The FEN representation of the board
     */
    public static String loadFENFromBoard(Board board) {
        StringBuilder fen = new StringBuilder();

        for (int rank = 7; rank >= 0; rank--) {
            int emptyFile = 0;
            for (int file = 0; file < 8; file++) {
                int index = rank * 8 + file;
                if (board.getPiece(index) != null) {
                    if (emptyFile != 0) {
                        fen.append(emptyFile);
                        emptyFile = 0;
                    }
                    fen.append(board.getPiece(index).toString());
                } else {
                    emptyFile++;
                }
            }
            if (emptyFile != 0) {
                fen.append(emptyFile);
            }
            if (rank != 0) {
                fen.append("/");
            }
        }
        return fen.toString();
    }
}
