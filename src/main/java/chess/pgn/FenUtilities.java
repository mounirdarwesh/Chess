package chess.pgn;

import java.util.*;

import chess.Attributes.Color;
import chess.model.Bishop;
import chess.model.Board;
import chess.model.King;
import chess.model.Knight;
import chess.model.Pawn;
import chess.model.Piece;
import chess.model.Queen;
import chess.model.Rook;

/**
 * @author Ahmad Mohammad
 */
public class FenUtilities {

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
     * @return board  the board
     */
    public static List<Piece> loadBoardFromFEN(String fen, Board board) {
        List<Piece> piecesOnBoard = new ArrayList<>(Collections.nCopies(64, null));
        int file = 0;
        int rank = 7;

        for (char c : fen.toCharArray()) {
            if (c == '/') {
                file = 0;
                rank--;
            } else {
                //checks if a character is a digit
                if (Character.isDigit(c)) {
                    file += (int) Character.getNumericValue(c);
                } else {
                    int position = rank * 8 + file;
                    //All the possibilities
                    switch (c) {
                        // if it is a black rook
                        case 'r':
                            piecesOnBoard.set(position, new Rook(position, Color.BLACK, board));
                            break;
                        // if it is a black knight
                        case 'n':
                            piecesOnBoard.set(position, new Knight(position, Color.BLACK, board));
                            break;
                        // if it is a black bishop
                        case 'b':
                            piecesOnBoard.set(position, new Bishop(position, Color.BLACK, board));
                            break;
                        // if it is a black queen
                        case 'q':
                            piecesOnBoard.set(position, new Queen(position, Color.BLACK, board));
                            break;
                        // if it is a black king
                        case 'k':
                            piecesOnBoard.set(position, new King(position, Color.BLACK, board));
                            break;
                        // if it is a black pawn
                        case 'p':
                            piecesOnBoard.set(position, new Pawn(position, Color.BLACK, board));
                            break;
                        // if it is a white rook
                        case 'R':
                            piecesOnBoard.set(position, new Rook(position, Color.WHITE, board));
                            break;
                        // if it is a white knight
                        case 'N':
                            piecesOnBoard.set(position, new Knight(position, Color.WHITE, board));
                            break;
                        // if it is a white bishop
                        case 'B':
                            piecesOnBoard.set(position, new Bishop(position, Color.WHITE, board));
                            break;
                        // if it is a white queen
                        case 'Q':
                            piecesOnBoard.set(position, new Queen(position, Color.WHITE, board));
                            break;
                        // if it is a white king
                        case 'K':
                            piecesOnBoard.set(position, new King(position, Color.WHITE, board));
                            break;
                        // if it is a white pawn
                        case 'P':
                            piecesOnBoard.set(position, new Pawn(position, Color.WHITE, board));
                            break;
                        default:
                            System.out.println("Error in the FEN string");
                    }
                    file++;
                }
            }
        }
        return piecesOnBoard;
    }


    /**
     * This is to create a FEN string from the current board
     *
     * @param board The current board
     * @return fen  The FEN representation of the board
     */
    public static String loadFENFromBoard(Board board) {
        String fen = "";

        for (int rank = 7; rank >= 0; rank--) {
            int emptyFile = 0;
            for (int file = 0; file < 8; file++) {
                int index = rank * 8 + file;
                if (board.getPiece(index) != null) {
                    if (emptyFile != 0) {
                        fen += String.valueOf(emptyFile);
                        emptyFile = 0;
                    }
                    fen += board.getPiece(index).toString();
                } else {
                    emptyFile++;
                }
            }
            if (emptyFile != 0) {
                fen += String.valueOf(emptyFile);
            }
            if (rank != 0) {
                fen += "/";
            }
        }
        return fen;
    }

}
