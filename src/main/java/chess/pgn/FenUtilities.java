package chess.pgn;

import java.util.*;
import chess.Attributes.Color;
import chess.util.BoardMapper;
import chess.model.*;
import chess.model.game.Game;
import chess.model.pieces.*;

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
     *
     * @param fen the FEN string
     * @param board the Board
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
                    if (Character.isUpperCase(c)) {
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
     *
     * @param board    The board of the game
     * @param position The position of the piece
     * @param c        The char, aka, the string representation of the piece
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
     *
     * @param board    The board of the game
     * @param position The position of the piece
     * @param c        The char, aka, the string representation of the piece
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
     * @param game the current game
     * @return A FEN representation of the current game
     */
    public static String createFENFromGame(Game game) {
        return loadFENFromBoard(game.getBoard()) + " " +
                loadPlayerText(game) + " " +
                loadCastleInformation(game) + " " +
                loadEnPassantInformation(game);
    }

    /**
     * load EnPassant information
     * @return String
     * @param game game
     */
    private static String loadEnPassantInformation(Game game) {
        Piece enPassantPawn = game.getEnPassantPawn();
        if(enPassantPawn != null) {
            return BoardMapper.mapPositionToChessNotation(enPassantPawn.getPosition());
        }
        return "-";
    }

    /**
     * load the castling information
     * @return String
     * @param game game
     */
    public static String loadCastleInformation(Game game) {
        StringBuilder castleInfo = new StringBuilder();

        if (game.getWhitePlayer().isKingSideCastleAllowed()) {
            castleInfo.append("K");
        }
        if (game.getWhitePlayer().isQueenSideCastleAllowed()) {
            castleInfo.append("Q");
        }
        if (game.getBlackPlayer().isKingSideCastleAllowed()) {
            castleInfo.append("k");
        }
        if (game.getBlackPlayer().isQueenSideCastleAllowed()) {
            castleInfo.append("q");
        }

        return castleInfo.toString().isEmpty() ? "-" : castleInfo.toString();
    }

    /**
     * load text of player
     * @return String
     * @param game game
     */
    private static String loadPlayerText(Game game) {
        return game.getCurrentPlayer().toString().substring(0, 1).toLowerCase();
    }

    /**
     * This is to create a FEN string from the current board
     *
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
