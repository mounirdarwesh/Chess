package chess.controller;

import chess.model.Bishop;
import chess.model.Board;
import chess.model.Knight;
import chess.model.Piece;
import chess.model.Queen;
import chess.model.Rook;

/**
 * @author TBD
 *
 */
public abstract class Move {
	
	/**
	 *  The board on which the move is performed
	 */
	protected Board board;
	
	/**
	 *  The position where the move starts
	 */
	protected Piece piece;
	
	/**
	 *  The position where the move ends 
	 */
	protected int destination;
	

	/**
	 * The constructor for the move class
	 * @param board  The board on which the move is performed
	 * @param piece  The moved piece
	 * @param destination  To where the piece should move
	 */
	public Move(Board board, Piece piece, int destination) {
		this.board = board;
		this.piece = piece;
		this.destination = destination;
	}
	
	/**
	 * An abstract method to execute the move
	 */
	public abstract void execute();	
	
	/**
	 * Getter of the destination of the move
	 * @return the destination of the move
	 */
	public int getDestination() {
		return this.destination;
	}
	
	@Override
	public String toString() {
		return this.getClass() + " " + destination;
	}
	
	/**
	 * Here where the piece make a normal move
	 * @author Gr. 45
	 */
	public static class NormalMove extends Move {


		/**
		 * A constructor for the Normal Move class 
		 * @param board The board on which the move is executed
		 * @param piece The piece that performs the move
		 * @param destination The desired location of the new position
		 */
		public NormalMove(Board board, Piece piece, int destination) {
			super(board, piece, destination);
		}

		@Override
		public void execute() {
			//Delete the piece on the old position
			board.getPiecesOnBoard().set(piece.getPosition(), null);
			// Set it's new destination
			piece.setPosition(destination);
			// And update it in the list of pieces on the board
			board.setPiece(piece);
			
			// Set the first move to false, because this is it's first move
			if(piece.isFirstMove()) {
				piece.setFirstMove(false);
			}
		}
		
	}

	public static class CastlingMove extends Move {

		int destinationRook;

		Piece pieceRook ;

		/**
		 * A constructor for the Castling Move class
		 * @param board The board on which the move is executed
		 * @param pieceKing The King that performs the move
		 * @param destinationKing The desired location of the new position for the King
		 * @param pieceRook The Rook that performs the move
		 * @param destinationRook The desired location of the new position for the Rook
		 */
		public CastlingMove(Board board, Piece piece, int destination, Piece pieceRook, int destinationRook) {
			super(board, piece, destination);
			this.pieceRook = pieceRook;
			this.destinationRook = destinationRook;

		}

		@Override
		public void execute() {
			//Delete the King on the old position
			board.getPiecesOnBoard().set(piece.getPosition(), null);
			// Set King's new destination
			piece.setPosition(destination);
			// And update it in the list of pieces on the board
			board.setPiece(piece);
			if(piece.isFirstMove()) {
				piece.setFirstMove(false);
			}

			//Delete the Rook on the old position
			board.getPiecesOnBoard().set(pieceRook.getPosition(), null);
			// Set Rook's new destination
			pieceRook.setPosition(destinationRook);
			// And update it in the list of pieces on the board
			board.setPiece(pieceRook);

			// Set the first move to false, because this is it's first move
			if(pieceRook.isFirstMove()) {
				pieceRook.setFirstMove(false);
			}
		}

	}
	/**
	 * Here where the pawn can make double step
	 * @author Gr. 45
	 */
	public static class DoublePawnMove extends Move {

		/**
		 * A constructor for the Double Pawn Move class 
		 * @param board The board on which the move is executed
		 * @param piece The piece that performs the move
		 * @param destination The desired location of the new position
		 */
		public DoublePawnMove(Board board, Piece piece, int destination) {
			super(board, piece, destination);
		}

		@Override
		public void execute() {
			
			//Delete the piece on the old position
			board.getPiecesOnBoard().set(piece.getPosition(), null);
			
			// Set it's new destination
			piece.setPosition(destination);
			// And update it in the list of pieces on the board
			board.setPiece(piece);
			
			// Set the first move to false, because this is it's first move
			if(piece.isFirstMove()) {
				piece.setFirstMove(false);
			}
			
			// When the move is executed, connect the boolean to the moved pawn 
			piece.setEnPassant(true);
		}
		
	}
	
	
	/**
	 * Here where the piece perform a capture move
	 * @author Gr. 45
	 */
	public static class CaptureMove extends Move {

		/**
		 * A constructor for the Capture Move class 
		 * @param board The board on which the move is executed
		 * @param piece The piece that performs the move
		 * @param destination The desired location of the new position
		 */
		public CaptureMove(Board board, Piece piece, int destination) {
			super(board, piece, destination);
		}

		@Override
		public void execute() {
			
			//Delete the piece on the old position
			board.getPiecesOnBoard().set(piece.getPosition(), null);
			
			//Delete the piece on the destination
			Piece captured = board.getPiecesOnBoard().set(destination, null);
			
			// And add it to player's beaten list
			if(captured.getColor().isWhite()) {
				board.getWhiteBeaten().add(captured);
			} else board.getBlackBeaten().add(captured);
			
			// Update the piece's position
			piece.setPosition(destination);
			// Update the board of pieces
			board.setPiece(piece);
		}
	}
	
	/**
	 * Here where to pereform the classic En passant move
	 * @author Gr. 45
	 *
	 */
	public static class EnPassantMove extends Move {
		
		/**
		 * The position of the pawn to capture
		 */
		private int pawnCapturedEnPassant;
		
		
		/**
		 * A constructor for the En Passant Move class 
		 * @param board The board on which the move is executed
		 * @param piece The piece that performs the move
		 * @param destination The desired location of the new position
		 * @param pawnCapturedEnPassant The position of the pawn captured
		 */
		public EnPassantMove(Board board, Piece piece, int destination, int pawnCapturedEnPassant) {
			super(board, piece, destination);
			this.pawnCapturedEnPassant = pawnCapturedEnPassant;
		}

		@Override
		public void execute() {
			
			//Delete the piece on the old position
			board.getPiecesOnBoard().set(piece.getPosition(), null);
			
			//Delete the piece on the destination
			Piece pawnCaptured = board.getPiecesOnBoard().set(pawnCapturedEnPassant, null);
			// And add it to player's beaten list
			if(pawnCaptured.getColor().isWhite()) {
				board.getWhiteBeaten().add(pawnCaptured);
			} else board.getBlackBeaten().add(pawnCaptured);

			// Update the piece's position
			piece.setPosition(destination);
			// Update the board of pieces
			board.setPiece(piece);
		}
		
	}
	
	/**
	 * Here where the Promotion to a new piece is happening
	 * @author Gr.45
	 */
	public static class PromotionMove extends Move {

		/**
		 * The piece to promote to
		 */
		private char promoted;
		
		/**
		 * A constructor for the Promotion Move class 
		 * @param board The board on which the move is executed
		 * @param piece The piece that performs the move
		 * @param destination The desired location of the new position
		 * @param promoted The representation of the piece to promote
		 */
		public PromotionMove(Board board, Piece piece, int destination, char promoted) {
			super(board, piece, destination);
			this.promoted = promoted;
		}

		@Override
		public void execute() {
			
			// Check if the pawn captured a piece to promote
			if(board.getPiecesOnBoard().get(destination) != null) {
				// Then add it to beaten figures
				if(board.getPiecesOnBoard().get(destination).getColor().isWhite()) {
					board.getWhiteBeaten().add(board.getPiecesOnBoard().get(destination));
				} else board.getBlackBeaten().add(board.getPiecesOnBoard().get(destination));
			}

			// Remove the piece from the board
			board.getPiecesOnBoard().set(piece.getPosition(), null);
			
			// The promoted piece
			Piece promotedPiece;
			
			// Switch through the possible promotion
			switch (Character.toLowerCase(promoted)) {

				// The player didn't choose to promote the pawn so it's a normal move
				case 'p':
					// Set it's new destination
					piece.setPosition(destination);
					// And update it in the list of pieces on the board
					board.setPiece(piece);
				// The player chooses to promote to a new Queen
				case 'q':
					// Create a new  Queen
					promotedPiece = new Queen(destination, piece.getColor(), board);
					// And add it to the list of pieces on the board
					board.setPiece(promotedPiece);
					break;
					
				// The player chooses to promote to a new Rook
				case 'r':
					// Create a new  Rook
					promotedPiece = new Rook(destination, piece.getColor(), board);
					// And add it to the list of pieces on the board
					board.setPiece(promotedPiece);
					break;
					
				// The player chooses to promote to a new Knight
				case 'n':
					// Create a new  Knight
					promotedPiece = new Knight(destination, piece.getColor(), board);
					// And add it to the list of pieces on the board
					board.setPiece(promotedPiece);
					break;
					
				// The player chooses to promote to a new Bishop
				case 'b':
					// Create a new Bishop
					promotedPiece = new Bishop(destination, piece.getColor(), board);
					// And add it to the list of pieces on the board
					board.setPiece(promotedPiece);
					break;
				default:
					break;
			}
		}
	}

}


