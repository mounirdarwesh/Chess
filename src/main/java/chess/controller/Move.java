package chess.controller;


import chess.model.Board;
import chess.model.pieces.*;
import chess.util.BoardMapper;

/**
 * move class
 * @author Gr.45
 */
public abstract class Move extends MoveController{

    /**
     * The board on which the move is performed
     */
    protected Board board;

    /**
     * The position where the move starts
     */
    protected Piece piece;

    /**
     * The position where the move ends
     */
    protected int destination;

    /**
     * Piece Source Location.
     */
    protected int source;

    /**
     * The enemy's EnPassant
     */
    protected Piece enemyEnPassantPiece;


    /**
     * The constructor for the move class
     *
     * @param board       The board on which the move is performed
     * @param piece       The moved piece
     * @param destination To where the piece should move
     */
    public Move(Board board, Piece piece, int destination) {
        this.board = board;
        this.piece = piece;
        this.destination = destination;
        this.source = piece.getPosition();
    }

    /**
     * An abstract method to execute the move
     */
    public abstract void execute();

    /**
     * An abstract method to undo the move
     */
    public abstract void undo();

    /**
     * Getter of the destination of the move
     *
     * @return the destination of the move
     */
    public int getDestination() {
        return this.destination;
    }

    @Override
    public String toString() {
        String from = BoardMapper.mapPositionToChessNotation(source);
        String to = BoardMapper.mapPositionToChessNotation(destination);
        return from + "-" + to;
    }

    /**
     * Here where the piece make a normal move
     *
     * @author Gr. 45
     */
    public static class NormalMove extends Move {

        /**
         * Temporary variable for the moved piece
         */
        private Piece fromPiece;

        /**
         * Temporary variable for the position of the moved piece
         */
        private int fromPiecePosition;

        /**
         * Temporary variable for the status of the piece first move
         */
        private boolean wasFirstMove;

        /**
         * A constructor for the Normal Move class
         *
         * @param board       The board on which the move is executed
         * @param piece       The piece that performs the move
         * @param destination The desired location of the new position
         */
        public NormalMove(Board board, Piece piece, int destination) {
            super(board, piece, destination);
        }

        @Override
        public void execute() {

            //Saving the previous position of the piece
            fromPiecePosition = piece.getPosition();

            //Delete the piece on the old position
            fromPiece = piece;
            board.getPiecesOnBoard().set(piece.getPosition(), null);

            // And update it in the list of pieces on the board
            board.setPiece(piece, destination);

            // Set the first move to false, because this is it's first move
            wasFirstMove = piece.isFirstMoveOnBoard();
            if (wasFirstMove) {
                piece.setFirstMove(false);
            }

            // If the player didn't preform an En Passant then he lost the
            // chance to do so
            enemyEnPassantPiece = game.getCurrentPlayer().getEnPassantPieceToCapture();
            game.getCurrentPlayer().setEnPassantPieceToCapture(null);

        }

        @Override
        public void undo() {
            // Resitting the preformed move
            board.getPiecesOnBoard().set(destination, null);
            board.getPiecesOnBoard().set(fromPiecePosition, fromPiece);
            board.setPiece(fromPiece, fromPiecePosition);
            fromPiece.setFirstMove(wasFirstMove);
            game.getCurrentPlayer().setEnPassantPieceToCapture(enemyEnPassantPiece);
        }

    }

    /**
     * Castling move
     */
    public static class CastlingMove extends Move {

        /**
         * Temporary variable for the moved king piece
         */
        private Piece fromKingPiece;

        /**
         * Temporary variable for the moved rook piece
         */
        private Piece fromRookPiece;

        /**
         * Temporary variable for the position of the moved piece
         */
        private int fromKingPiecePosition;

        /**
         * Temporary variable for the position of the moved piece
         */
        private int fromRookPiecePosition;

        /**
         * Temporary variable for the status of the king first move
         */
        private boolean wasKingFirstMove;

        /**
         * Temporary variable for the status of the rook first move
         */
        private boolean wasRookFirstMove;

        /**
         * The specified destination of the rook
         */
        private int destinationRook;

        /**
         * The rook
         */
        private Piece pieceRook;


        /**
         * A constructor for the Castling Move class
         *
         * @param board       The board on which the move is executed
         * @param piece       The King that performs the move
         * @param destination The desired location of the new position for the King
         * @param pieceRook   The Rook that performs the move
         */
        public CastlingMove(Board board, Piece piece, int destination, Piece pieceRook) {
            super(board, piece, destination);
            this.pieceRook = pieceRook;
            // the destination of Rook
            if (destination == 6 || destination == 62) {
                this.destinationRook = destination - 1;
            } else if (destination == 2 || destination == 58) {
                this.destinationRook = destination + 1;
            }

        }

        @Override
        public void execute() {
            //Saving the previous position of the king
            fromKingPiecePosition = piece.getPosition();
            //Delete the King on the old position
            fromKingPiece = board.getPiecesOnBoard().set(piece.getPosition(), null);

            // And update it in the list of pieces on the board
            board.setPiece(piece, destination);

            wasKingFirstMove = piece.isFirstMoveOnBoard();
            if (wasKingFirstMove) {
                piece.setFirstMove(false);
            }

            //Saving the previous position of the rook
            fromRookPiecePosition = pieceRook.getPosition();
            //Delete the Rook on the old position
            fromRookPiece = board.getPiecesOnBoard().set(pieceRook.getPosition(), null);

            // And update it in the list of pieces on the board
            board.setPiece(pieceRook, destinationRook);

            // Set the first move to false, because this is it's first move
            wasRookFirstMove = pieceRook.isFirstMoveOnBoard();
            if (wasRookFirstMove) {
                pieceRook.setFirstMove(false);
            }

            // If the player didn't preform an En Passant then he lost the
            // chance to do so
            enemyEnPassantPiece = game.getCurrentPlayer().getEnPassantPieceToCapture();
            game.getCurrentPlayer().setEnPassantPieceToCapture(enemyEnPassantPiece);
        }

        @Override
        public void undo() {
            //Resetting the preformed move for the King
            board.getPiecesOnBoard().set(destination, null);
            board.getPiecesOnBoard().set(fromKingPiecePosition, fromKingPiece);
            board.setPiece(fromKingPiece, fromKingPiecePosition);
            fromKingPiece.setFirstMove(wasKingFirstMove);

            //Resetting the preformed move for the Rook
            board.getPiecesOnBoard().set(destinationRook, null);
            board.getPiecesOnBoard().set(fromRookPiecePosition, fromRookPiece);
            board.setPiece(fromRookPiece, fromRookPiecePosition);
            fromRookPiece.setFirstMove(wasRookFirstMove);

            game.getCurrentPlayer().setEnPassantPieceToCapture(enemyEnPassantPiece);
        }

    }

    /**
     * Here where the pawn can make double step
     *
     * @author Gr. 45
     */
    public static class DoublePawnMove extends Move {

        /**
         * Temporary variable for the moved piece
         */
        private Piece fromPiece;

        /**
         * Temporary variable for the position of the moved piece
         */
        private int fromPiecePosition;

        /**
         * Temporary variable for the status of the piece first move
         */
        private boolean wasFirstMove;

        /**
         * A constructor for the Double Pawn Move class
         *
         * @param board       The board on which the move is executed
         * @param piece       The piece that performs the move
         * @param destination The desired location of the new position
         */
        public DoublePawnMove(Board board, Piece piece, int destination) {
            super(board, piece, destination);
        }

        @Override
        public void execute() {

            //Set the game EnPassant piece
            game.setEnPassantPawn(piece);

            //Saving the previous position of the piece
            fromPiecePosition = piece.getPosition();

            //Delete the piece on the old position
            fromPiece = board.getPiecesOnBoard().set(piece.getPosition(), null);

            // And update it in the list of pieces on the board
            board.setPiece(piece, destination);

            // Set the first move to false, because this is it's first move
            wasFirstMove = piece.isFirstMoveOnBoard();
            if (wasFirstMove) {
                piece.setFirstMove(false);
            }

            // Allow the enemy to preform an En Passant
            game.getOpponent().setEnPassantPieceToCapture(piece);
        }

        @Override
        public void undo() {
            // Resitting the preformed move
            board.getPiecesOnBoard().set(destination, null);
            board.getPiecesOnBoard().set(fromPiecePosition, fromPiece);
            fromPiece.setPosition(fromPiecePosition);
            board.setPiece(fromPiece, fromPiecePosition);
            fromPiece.setFirstMove(wasFirstMove);
            game.getOpponent().setEnPassantPieceToCapture(null);
        }
    }


    /**
     * Here where the piece perform a capture move
     *
     * @author Gr. 45
     */
    public static class CaptureMove extends Move {

        /**
         * Temporary variable for the moved piece
         */
        private Piece fromPiece;

        /**
         * Temporary variable for the position of the moved piece
         */
        private int fromPiecePosition;

        /**
         * The captured piece
         */
        private Piece captured;

        /**
         * store if the Piece to first time move.
         */
        private boolean wasFirstMove;

        /**
         * A constructor for the Capture Move class
         *
         * @param board       The board on which the move is executed
         * @param piece       The piece that performs the move
         * @param destination The desired location of the new position
         */
        public CaptureMove(Board board, Piece piece, int destination) {
            super(board, piece, destination);
        }

        @Override
        public void execute() {
            //Saving the previous position of the piece
            fromPiecePosition = piece.getPosition();


            //Delete the piece on the old position
            fromPiece = board.getPiecesOnBoard().set(piece.getPosition(), null);

            // if his first time, should not be anymore.
            wasFirstMove = piece.isFirstMoveOnBoard();
            if (wasFirstMove)
                piece.setFirstMove(false);

            //Delete the piece on the destination
            captured = board.getPiecesOnBoard().set(destination, null);

            // And add it to player's beaten list
            game.addToBeaten(captured);

            // Update the board of pieces
            board.setPiece(piece, destination);

            // If the player didn't preform an En Passant then he lost the
            // chance to do so
            enemyEnPassantPiece = game.getCurrentPlayer().getEnPassantPieceToCapture();
            game.getCurrentPlayer().setEnPassantPieceToCapture(enemyEnPassantPiece);
        }

        @Override
        public void undo() {
            // Resitting the preformed move
            board.getPiecesOnBoard().set(destination, captured);
            board.setPiece(captured, destination);
            board.getPiecesOnBoard().set(fromPiecePosition, fromPiece);
            board.setPiece(fromPiece, fromPiecePosition);
            game.removeFromBeaten(captured);
            fromPiece.setFirstMove(wasFirstMove);
            game.getCurrentPlayer().setEnPassantPieceToCapture(enemyEnPassantPiece);
        }
    }

    /**
     * Here where to perform the classic En passant move
     *
     * @author Gr. 45
     */
    public static class EnPassantMove extends Move {

        /**
         * The position of the pawn to capture
         */
        private int pawnCapturedEnPassant;

        /**
         * Temporary variable for the moved piece
         */
        private Piece fromPiece;

        /**
         * Temporary variable for the position of the moved piece
         */
        private int fromPiecePosition;

        /**
         * The captured pawn
         */
        private Piece pawnCaptured;

        /**
         * A constructor for the En Passant Move class
         *
         * @param board                 The board on which the move is executed
         * @param piece                 The piece that performs the move
         * @param destination           The desired location of the new position
         * @param pawnCapturedEnPassant The position of the pawn captured
         */
        public EnPassantMove(Board board, Piece piece, int destination, int pawnCapturedEnPassant) {
            super(board, piece, destination);
            this.pawnCapturedEnPassant = pawnCapturedEnPassant;
        }

        @Override
        public void execute() {

            //Saving the previous position of the piece
            fromPiecePosition = piece.getPosition();

            //Delete the piece on the old position
            fromPiece = board.getPiecesOnBoard().set(piece.getPosition(), null);

            //Delete the piece on the destination
            pawnCaptured = board.getPiecesOnBoard().set(pawnCapturedEnPassant, null);

            // And add it to player's beaten list
            game.addToBeaten(pawnCaptured);

            // Update the board of pieces
            board.setPiece(piece, destination);

            enemyEnPassantPiece = game.getCurrentPlayer().getEnPassantPieceToCapture();
            game.getCurrentPlayer().setEnPassantPieceToCapture(null);
        }

        @Override
        public void undo() {
            // Resitting the preformed move
            board.getPiecesOnBoard().set(destination, null);
            board.getPiecesOnBoard().set(fromPiecePosition, fromPiece);
            board.setPiece(fromPiece, fromPiecePosition);
            board.getPiecesOnBoard().set(pawnCapturedEnPassant, pawnCaptured);
            board.setPiece(pawnCaptured, pawnCapturedEnPassant);
            game.removeFromBeaten(pawnCaptured);
            game.getCurrentPlayer().setEnPassantPieceToCapture(enemyEnPassantPiece);
        }

    }

    /**
     * Here where the Promotion to a new piece is happening
     *
     * @author Gr.45
     */
    public static class PromotionMove extends Move {

        /**
         * Temporary variable for the moved piece
         */
        private Piece fromPiece;

        /**
         * Temporary variable for the position of the moved piece
         */
        private int fromPiecePosition;

        /**
         * The captured piece
         */
        private Piece captured;

        /**
         * The promoted piece
         */
        private Piece promotedPiece;

        /**
         * A constructor for the Promotion Move class
         *
         * @param board       The board on which the move is executed
         * @param piece       The piece that performs the move
         * @param destination The desired location of the new position
         */
        public PromotionMove(Board board, Piece piece, int destination) {
            super(board, piece, destination);
        }

        @Override
        public void execute() {

            //Save the previous position of the piece
            fromPiecePosition = piece.getPosition();

            // If there is a captured piece then save it in a variable
            captured = board.getPiecesOnBoard().get(destination);

            // Check if the pawn captured a piece to promote
            if (captured != null) {
                // Then add it to beaten figures
                game.addToBeaten(board.getPiecesOnBoard().get(destination));
            }

            // Remove the piece from the board
            fromPiece = board.getPiecesOnBoard().set(piece.getPosition(), null);

            // Switch through the possible promotion
            switch (Character.toLowerCase(game.getCharToPromote())) {
                case 'q':
                case ' ':
                    promoteQueen();
                    break;
                // The player chooses to promote to a new Rook
                case 'r':
                    promoteRook();
                    break;
                // The player chooses to promote to a new Knight
                case 'n':
                    promoteKnight();
                    break;
                // The player chooses to promote to a new Bishop
                case 'b':
                    promoteBishop();
                    break;
                default:
                    break;
            }

            // If the player didn't preform an En Passant then he lost the
            // chance to do so
            enemyEnPassantPiece = game.getCurrentPlayer().getEnPassantPieceToCapture();
            game.getCurrentPlayer().setEnPassantPieceToCapture(null);
        }

        /**
         * promote Pawn to Queen.
         */
        private void promoteQueen() {
            // Create a new  Queen
            promotedPiece = new Queen(destination, piece.getColor(), board);
            // add it to the list of pieces on the board
            board.setPiece(promotedPiece, destination);
            // Add update the available pieces for the current player
            game.getCurrentPlayer().getPlayerPieces().add(promotedPiece);
            game.getCurrentPlayer().getPlayerPieces().remove(piece);
            // remove the Promoted Piece from Beaten.
            for (Piece piece : game.getCurrentPlayer().getBeaten()) {
                if (piece instanceof Queen && this.piece.getColor() == piece.getColor()) {
                    game.getCurrentPlayer().getBeaten().remove(piece);
                    break;
                }
            }
        }

        /**
         * promote Pawn to Rook.
         */
        private void promoteRook() {
            // Create a new  Rook
            promotedPiece = new Rook(destination, piece.getColor(), board);
            // And add it to the list of pieces on the board
            board.setPiece(promotedPiece, destination);
            // Add update the available pieces for the current player
            game.getCurrentPlayer().getPlayerPieces().add(promotedPiece);
            game.getCurrentPlayer().getPlayerPieces().remove(piece);
            // remove the Promoted Piece from Beaten.
            for (Piece piece : game.getCurrentPlayer().getBeaten()) {
                if (piece instanceof Rook && this.piece.getColor() == piece.getColor()) {
                    game.getCurrentPlayer().getBeaten().remove(piece);
                    break;
                }
            }
        }

        /**
         * promote Pawn to Knight.
         */
        private void promoteKnight() {
            // Create a new  Knight
            promotedPiece = new Knight(destination, piece.getColor(), board);
            // And add it to the list of pieces on the board
            board.setPiece(promotedPiece, destination);
            // Add update the available pieces for the current player
            game.getCurrentPlayer().getPlayerPieces().add(promotedPiece);
            game.getCurrentPlayer().getPlayerPieces().remove(piece);
            // remove the Promoted Piece from Beaten.
            for (Piece piece : game.getCurrentPlayer().getBeaten()) {
                if (piece instanceof Knight && this.piece.getColor() == piece.getColor()) {
                    game.getCurrentPlayer().getBeaten().remove(piece);
                    break;
                }
            }
        }

        /**
         * promote Pawn to Bishop.
         */
        private void promoteBishop() {
            // Create a new Bishop
            promotedPiece = new Bishop(destination, piece.getColor(), board);
            // And add it to the list of pieces on the board
            board.setPiece(promotedPiece, destination);
            // Add update the available pieces for the current player
            game.getCurrentPlayer().getPlayerPieces().add(promotedPiece);
            game.getCurrentPlayer().getPlayerPieces().remove(piece);
            // remove the Promoted Piece from Beaten.
            for (Piece piece : game.getCurrentPlayer().getBeaten()) {
                if (piece instanceof Bishop && this.piece.getColor() == piece.getColor()) {
                    game.getCurrentPlayer().getBeaten().remove(piece);
                    break;
                }
            }
        }

        @Override
        public void undo() {
            if (captured != null) {
                game.removeFromBeaten(captured);
            }
            board.getPiecesOnBoard().set(fromPiecePosition, fromPiece);
            board.setPiece(fromPiece, fromPiecePosition);
            board.getPiecesOnBoard().set(destination, captured);
            game.getCurrentPlayer().removeFromPlayersPieces(promotedPiece);
            game.getCurrentPlayer().addToPlayersPieces(fromPiece);
            game.getCurrentPlayer().setEnPassantPieceToCapture(enemyEnPassantPiece);
        }
    }

}
