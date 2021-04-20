package chess.utilities;
import java.util.*;
import chess.Game;
import chess.PGN.FenUtilities;

/**
 * @author Ahmad Mohammad
 * The Board class
 */
public class Board {
	
	// The game that is associated with the board
	private Game game;
	
	// The pieces on the board
	private ArrayList<Piece> board;
	
	// The start FEN of the board
	private static final String START_FEN = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR";
	
	/*
	 * The constructor of the board class
	 * @param  game  the game 
	 */
	public Board(Game game) {
		this.game = game;
		this.board = FenUtilities.loadBoardFromFEN(START_FEN);
	}


	/**
	 * The getter of the game
	 * @return the game
	 */
	public Game getGame() {
		return game;
	}
	
	/*
	 * Getter of the piece on the board
	 * @param index  the index of the searched piece
	 */
	public Piece getPiece(int index) {
		return this.board.get(index);
	}
	
	/**
	 * Setter of the board of the game
	 * @param board the board to set
	 */
	public void setPiece(Piece piece) {
		this.board.set(piece.getPosition(), piece);
	}
	
	
	/*
	 * Getter for all the pieces on the board
	 * @return the pieces on the board
	 */
	public ArrayList<Piece> getPiecesOnBoard(){
		return this.board;
	}
	
	/*
	 * Updating the board after a move is performed
	 */
	public void updateBoard() {
		String fen = FenUtilities.loadFENFromBoard(this);
		this.board = FenUtilities.loadBoardFromFEN(fen);
	}

	@Override
	public String toString() {
		
		String board_ = "";
		
		for(int rank = 8; rank > 0; rank--) {
			board_ += "" + rank;
			for(int file = 0; file < 8; file++) {
				Piece piece = board.get((rank-1) * 8 + file);
				if(piece == null) {
					board_ += "  ";
				} else board_ += " " + piece.toString();
			}
			board_ += "\n";
		}
		board_ += "  a b c d e f g h";
		return board_;
	}



	
}
