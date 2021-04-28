package chess.model;
import java.util.*;

import chess.Attributes;
import chess.PGN.FenUtilities;
import chess.view.View;

/**
 * @author Ahmad Mohammad
 * The Board class
 */
public class Board {
	
	/**
	 *  The pieces on the board
	 */
	private ArrayList<Piece> piecesOnBoard;
	
	/**
	 *  White beaten pieces
	 */
	private ArrayList<Piece> whiteBeaten = new ArrayList<Piece>();
	
	/**
	 *  Black beaten pieces
	 */
	private ArrayList<Piece> blackBeaten = new ArrayList<Piece>();
	
	/**
	 *  The start FEN of the board
	 */
	private static final String START_FEN = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR";
	
	
	/**
	 * The constructor of the board class
	 * @param  game  the game 
	 */
	public Board() {
		// Initializing the board
		this.piecesOnBoard = FenUtilities.loadBoardFromFEN(START_FEN, this);
	}
 	
	/**
	 * Getter of the piece on the board
	 * @param index  the index of the searched piece
	 */
	public Piece getPiece(int index) {
		return this.piecesOnBoard.get(index);
	}
	
	/**
	 * Setter of the board of the game
	 * @param board the board to set
	 */
	public void setPiece(Piece piece) {
		this.piecesOnBoard.set(piece.getPosition(), piece);
	}
	
	/**
	 * 
	 * @param from
	 * @param to
	 */
	public void movePiece(int from, int to) {
		Piece from_piece = piecesOnBoard.set(from, null);
		from_piece.setPosition(to);
		from_piece.setFirstMove(false);
		Piece to_piece = piecesOnBoard.set(to, from_piece);
		if(to_piece != null) {
			if(to_piece.getType().isWhite()) {
				whiteBeaten.add(to_piece);
			} else  blackBeaten.add(to_piece);
		}
	}
	
	
	/**
	 * Getter for all the pieces on the board
	 * @return the pieces on the board
	 */
	public ArrayList<Piece> getPiecesOnBoard(){
		return this.piecesOnBoard;
	}
	
	
	@Override
	public String toString() {
		
		String board_ = "";
		
		for(int rank = 8; rank > 0; rank--) {
			board_ += "" + rank;
			for(int file = 0; file < 8; file++) {
				Piece piece = piecesOnBoard.get((rank-1) * 8 + file);
				if(piece == null) {
					board_ += "  ";
				} else board_ += " " + piece.toString();
			}
			board_ += "\n";
		}
		board_ += "  a b c d e f g h";
		return board_;
	}


	/**
	 * Updating the board
	 */
	public void update() {
		System.out.println(this);
	}

}
