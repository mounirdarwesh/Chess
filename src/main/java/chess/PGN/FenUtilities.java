package chess.PGN;

import java.util.*;
import chess.Attributes;
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
 *
 */
public class FenUtilities {
	
	/*
	 * The constructor of the FenUtilities class
	 */
	private FenUtilities() {
		throw new RuntimeException("I am NOT instantiable!");
	}
	
	
	/*
	 * This is to load the board from a FEN string
	 * @param fen  the FEN string 
	 * @return board  the board 
	 */
	public static ArrayList<Piece> loadBoardFromFEN(String fen, Board board){
		ArrayList<Piece> piecesOnBoard = new ArrayList<Piece>(Collections.nCopies(64, null));
		int file = 0;
		int rank = 7;
		
		for (char c : fen.toCharArray()) {
			if(c == '/') {
				file = 0;
				rank--;
			} else {
				if(Character.isDigit(c)) {
					file += (int) Character.getNumericValue(c);
				} else {
					int position = rank * 8 + file;
					switch(c) {
						case 'r':
							piecesOnBoard.set(position, new Rook(position, Attributes.BLACK, board));
							break;
						case 'n':
							piecesOnBoard.set(position, new Knight(position, Attributes.BLACK, board));
							break;
						case 'b':
							piecesOnBoard.set(position, new Bishop(position, Attributes.BLACK, board));
							break;
						case 'q':
							piecesOnBoard.set(position, new Queen(position, Attributes.BLACK, board));
							break;
						case 'k':
							piecesOnBoard.set(position, new King(position, Attributes.BLACK, board));
							break;
						case 'p':
							piecesOnBoard.set(position, new Pawn(position, Attributes.BLACK, board));
							break;
						case 'R':
							piecesOnBoard.set(position, new Rook(position, Attributes.WHITE, board));
							break;
						case 'N':
							piecesOnBoard.set(position, new Knight(position, Attributes.WHITE, board));
							break;
						case 'B':
							piecesOnBoard.set(position, new Bishop(position, Attributes.WHITE, board));
							break;
						case 'Q':
							piecesOnBoard.set(position, new Queen(position, Attributes.WHITE, board));
							break;
						case 'K':
							piecesOnBoard.set(position, new King(position, Attributes.WHITE, board));
							break;
						case 'P':
							piecesOnBoard.set(position, new Pawn(position, Attributes.WHITE, board));
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
	
	
	/*
	 * This is to create a FEN string from the current board
	 * @param board  The current board
	 * @return fen  The FEN representation of the board
	 */
	public static String loadFENFromBoard(Board board) {
		String fen = "";
		
		for(int rank = 7; rank >= 0; rank--) {
			int emptyFile = 0;
			for(int file = 0; file < 8; file++) {
				int index = rank * 8 + file;
				if(board.getPiece(index) != null) {
					if(emptyFile != 0) {
						fen += String.valueOf(emptyFile);
						emptyFile = 0;
					}
					fen += board.getPiece(index).toString();
				} else emptyFile++;
			}
			if(emptyFile != 0) {
				fen += String.valueOf(emptyFile);
			}
			if(rank != 0) {
				fen += "/";
			}
		}
		return fen;
	}

}
