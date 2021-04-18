package chess.PGN;

import java.util.*;
import chess.Constants;
import chess.utilities.Bishop;
import chess.utilities.Board;
import chess.utilities.King;
import chess.utilities.Knight;
import chess.utilities.Pawn;
import chess.utilities.Piece;
import chess.utilities.Queen;
import chess.utilities.Rook;

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
	public static ArrayList<Piece> loadBoardFromFEN(String fen){
		ArrayList<Piece> board = new ArrayList<Piece>(Collections.nCopies(64, null));
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
							board.set(position, new Rook(position, Constants.BLACK));
							break;
						case 'n':
							board.set(position, new Knight(position, Constants.BLACK));
							break;
						case 'b':
							board.set(position, new Bishop(position, Constants.BLACK));
							break;
						case 'q':
							board.set(position, new Queen(position, Constants.BLACK));
							break;
						case 'k':
							board.set(position, new King(position, Constants.BLACK));
							break;
						case 'p':
							board.set(position, new Pawn(position, Constants.BLACK));
							break;
						case 'R':
							board.set(position, new Rook(position, Constants.WHITE));
							break;
						case 'N':
							board.set(position, new Knight(position, Constants.WHITE));
							break;
						case 'B':
							board.set(position, new Bishop(position, Constants.WHITE));
							break;
						case 'Q':
							board.set(position, new Queen(position, Constants.WHITE));
							break;
						case 'K':
							board.set(position, new King(position, Constants.WHITE));
							break;
						case 'P':
							board.set(position, new Pawn(position, Constants.WHITE));
							break;
						default:
							System.out.println("Error in the FEN string");
					}
					file++;
				}
			}
		}
		return board;
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
