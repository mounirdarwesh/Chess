package chess.controller;

import chess.model.*;

import java.util.Map;

/**
 * @author Gr.45
 */
public abstract class Move {

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

    /**
     * Getting the string from the position
     *
     * @return
     */
    public <K, V> K getKey(Map<K, V> map, V value) {
        for (Map.Entry<K, V> entry : map.entrySet()) {
            if (entry.getValue().equals(value)) {
                return entry.getKey();
            }
        }
        return null;
    }

    @Override
    public String toString() {
        String from = getKey(MapBoard.mapper, source);
        String to = getKey(MapBoard.mapper, destination);
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
         * Temporary variable for the status of the En passant
         */
        private boolean wasEnPassant;

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
            fromPiece = board.getPiecesOnBoard().set(piece.getPosition(), null);
            // Set it's new destination
            piece.setPosition(destination);
            // And update it in the list of pieces on the board
            board.setPiece(piece);

            // Set the first move to false, because this is it's first move
            wasFirstMove = piece.isFirstMoved();
            if (wasFirstMove) {
                piece.setFirstMove(false);
            }

            // If the player didn't preform an En Passant then he lost the
            // chance to do so
            wasEnPassant = Game.getCurrentPlayer().isAllowEnPassant();
            Game.getCurrentPlayer().setAllowEnPassant(false);

        }

        @Override
        public void undo() {
            // Resitting the preformed move
            board.getPiecesOnBoard().set(destination, null);
            board.getPiecesOnBoard().set(fromPiecePosition, fromPiece);
            fromPiece.setPosition(fromPiecePosition);
            board.setPiece(fromPiece);
            fromPiece.setFirstMove(wasFirstMove);
            Game.getCurrentPlayer().setAllowEnPassant(wasEnPassant);
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
         * Temporary variable for the status of the En passant
         */
        private boolean wasEnPassant;

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
            // Set King's new destination
            piece.setPosition(destination);
            // And update it in the list of pieces on the board
            board.setPiece(piece);

            wasKingFirstMove = piece.isFirstMoved();
            if (wasKingFirstMove) {
                piece.setFirstMove(false);
            }

            //Saving the previous position of the rook
            fromRookPiecePosition = pieceRook.getPosition();
            //Delete the Rook on the old position
            fromRookPiece = board.getPiecesOnBoard().set(pieceRook.getPosition(), null);
            // Set Rook's new destination
            pieceRook.setPosition(destinationRook);
            // And update it in the list of pieces on the board
            board.setPiece(pieceRook);

            // Set the first move to false, because this is it's first move
            wasRookFirstMove = pieceRook.isFirstMoved();
            if (wasRookFirstMove) {
                pieceRook.setFirstMove(false);
            }

            // If the player didn't preform an En Passant then he lost the
            // chance to do so
            wasEnPassant = Game.getCurrentPlayer().isAllowEnPassant();
            Game.getCurrentPlayer().setAllowEnPassant(false);
        }

        @Override
        public void undo() {
            //Resetting the preformed move for the King
            board.getPiecesOnBoard().set(destination, null);
            board.getPiecesOnBoard().set(fromKingPiecePosition, fromKingPiece);
            fromKingPiece.setPosition(fromKingPiecePosition);
            board.setPiece(fromKingPiece);
            fromKingPiece.setFirstMove(wasKingFirstMove);

            //Resetting the preformed move for the Rook
            board.getPiecesOnBoard().set(destinationRook, null);
            board.getPiecesOnBoard().set(fromRookPiecePosition, fromRookPiece);
            fromRookPiece.setPosition(fromRookPiecePosition);
            board.setPiece(fromRookPiece);
            fromRookPiece.setFirstMove(wasRookFirstMove);

            Game.getCurrentPlayer().setAllowEnPassant(wasEnPassant);
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
         * Temporary variable for the status of the En passant
         */
        private boolean wasEnPassant;

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

            //Saving the previous position of the piece
            fromPiecePosition = piece.getPosition();

            //Delete the piece on the old position
            fromPiece = board.getPiecesOnBoard().set(piece.getPosition(), null);

            // Set it's new destination
            piece.setPosition(destination);
            // And update it in the list of pieces on the board
            board.setPiece(piece);

            // Set the first move to false, because this is it's first move
            wasFirstMove = piece.isFirstMoved();
            if (wasFirstMove) {
                piece.setFirstMove(false);
            }

            // Allow the enemy to preform an En Passant
            wasEnPassant = Game.getCurrentPlayer().isAllowEnPassant();
            Game.getOpponent(Game.getCurrentPlayer()).setAllowEnPassant(true);
        }

        @Override
        public void undo() {
            // Resitting the preformed move
            board.getPiecesOnBoard().set(destination, null);
            board.getPiecesOnBoard().set(fromPiecePosition, fromPiece);
            fromPiece.setPosition(fromPiecePosition);
            board.setPiece(fromPiece);
            fromPiece.setFirstMove(wasFirstMove);
            Game.getCurrentPlayer().setAllowEnPassant(wasEnPassant);
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
         * Temporary variable for the status of the En passant
         */
        private boolean wasEnPassant;
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
            wasFirstMove = piece.isFirstMoved();
            if (wasFirstMove)
                piece.setFirstMove(false);

            //Delete the piece on the destination
            captured = board.getPiecesOnBoard().set(destination, null);

            // And add it to player's beaten list
            Game.addToBeaten(captured);

            // Update the piece's position
            piece.setPosition(destination);
            // Update the board of pieces
            board.setPiece(piece);

            // If the player didn't preform an En Passant then he lost the
            // chance to do so
            wasEnPassant = Game.getCurrentPlayer().isAllowEnPassant();
            Game.getCurrentPlayer().setAllowEnPassant(false);
        }

        @Override
        public void undo() {
            // Resitting the preformed move
            board.getPiecesOnBoard().set(destination, captured);
            captured.setPosition(destination);
            board.setPiece(captured);
            board.getPiecesOnBoard().set(fromPiecePosition, fromPiece);
            fromPiece.setPosition(fromPiecePosition);
            board.setPiece(fromPiece);
            Game.removeFromBeaten(captured);
            fromPiece.setFirstMove(wasFirstMove);
            Game.getCurrentPlayer().setAllowEnPassant(wasEnPassant);
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
         * Temporary variable for the status of the En passant
         */
        private boolean wasEnPassant;


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
            Game.addToBeaten(pawnCaptured);

            // Update the piece's position
            piece.setPosition(destination);
            // Update the board of pieces
            board.setPiece(piece);

            // If the player didn't preform an En Passant then he lost the
            // chance to do so
            wasEnPassant = Game.getCurrentPlayer().isAllowEnPassant();
            Game.getCurrentPlayer().setAllowEnPassant(false);
        }

        @Override
        public void undo() {
            // Resitting the preformed move
            board.getPiecesOnBoard().set(destination, null);
            board.getPiecesOnBoard().set(fromPiecePosition, fromPiece);
            fromPiece.setPosition(fromPiecePosition);
            board.setPiece(fromPiece);
            board.getPiecesOnBoard().set(pawnCapturedEnPassant, pawnCaptured);
            pawnCaptured.setPosition(pawnCapturedEnPassant);
            board.setPiece(pawnCaptured);
            Game.removeFromBeaten(pawnCaptured);
            Game.getCurrentPlayer().setAllowEnPassant(wasEnPassant);
        }

    }

    /**
     * Here where the Promotion to a new piece is happening
     *
     * @author Gr.45
     */
    public static class PromotionMove extends Move {

        /**
         * The piece to promote to
         */
        private char promoted;

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
         * @param promoted    The representation of the piece to promote
         */
        public PromotionMove(Board board, Piece piece, int destination, char promoted) {
            super(board, piece, destination);
            this.promoted = promoted;
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
                Game.addToBeaten(board.getPiecesOnBoard().get(destination));
            }

            // Remove the piece from the board
            fromPiece = board.getPiecesOnBoard().set(piece.getPosition(), null);

            // Switch through the possible promotion
            switch (Character.toLowerCase(promoted)) {
                case 'q':
                case ' ':
                    // Create a new  Queen
                    promotedPiece = new Queen(destination, piece.getColor(), board);
                    // add it to the list of pieces on the board
                    board.setPiece(promotedPiece);
                    // Add update the available pieces for the current player
                    Game.getCurrentPlayer().getPlayerPieces().add(promotedPiece);
                    Game.getCurrentPlayer().getPlayerPieces().remove(piece);
                    // remove the Promoted Piece from Beaten.
                    for (Piece piece : Game.getCurrentPlayer().getBeaten())
                        if (piece instanceof Queen && this.piece.getColor() == piece.getColor()) {
                            Game.getCurrentPlayer().getBeaten().remove(piece);
                            break;
                        }
                    break;

                // The player chooses to promote to a new Rook
                case 'r':
                    // Create a new  Rook
                    promotedPiece = new Rook(destination, piece.getColor(), board);
                    // And add it to the list of pieces on the board
                    board.setPiece(promotedPiece);
                    // Add update the available pieces for the current player
                    Game.getCurrentPlayer().getPlayerPieces().add(promotedPiece);
                    Game.getCurrentPlayer().getPlayerPieces().remove(piece);
                    // remove the Promoted Piece from Beaten.
                    for (Piece piece : Game.getCurrentPlayer().getBeaten())
                        if (piece instanceof Rook && this.piece.getColor() == piece.getColor()) {
                            Game.getCurrentPlayer().getBeaten().remove(piece);
                            break;
                        }
                    break;

                // The player chooses to promote to a new Knight
                case 'n':
                    // Create a new  Knight
                    promotedPiece = new Knight(destination, piece.getColor(), board);
                    // And add it to the list of pieces on the board
                    board.setPiece(promotedPiece);
                    // Add update the available pieces for the current player
                    Game.getCurrentPlayer().getPlayerPieces().add(promotedPiece);
                    Game.getCurrentPlayer().getPlayerPieces().remove(piece);
                    // remove the Promoted Piece from Beaten.
                    for (Piece piece : Game.getCurrentPlayer().getBeaten())
                        if (piece instanceof Knight && this.piece.getColor() == piece.getColor()) {
                            Game.getCurrentPlayer().getBeaten().remove(piece);
                            break;
                        }
                    break;

                // The player chooses to promote to a new Bishop
                case 'b':
                    // Create a new Bishop
                    promotedPiece = new Bishop(destination, piece.getColor(), board);
                    // And add it to the list of pieces on the board
                    board.setPiece(promotedPiece);
                    // Add update the available pieces for the current player
                    Game.getCurrentPlayer().getPlayerPieces().add(promotedPiece);
                    Game.getCurrentPlayer().getPlayerPieces().remove(piece);
                    // remove the Promoted Piece from Beaten.
                    for (Piece piece : Game.getCurrentPlayer().getBeaten())
                        if (piece instanceof Bishop && this.piece.getColor() == piece.getColor()) {
                            Game.getCurrentPlayer().getBeaten().remove(piece);
                            break;
                        }
                    break;
                default:
                    break;
            }

            // If the player didn't preform an En Passant then he lost the
            // chance to do so
            Game.getCurrentPlayer().setAllowEnPassant(false);
        }

        @Override
        public void undo() {
            if (captured != null) {
                Game.removeFromBeaten(captured);
            }
            board.getPiecesOnBoard().set(fromPiecePosition, fromPiece);
            fromPiece.setPosition(fromPiecePosition);
            board.setPiece(fromPiece);
            board.getPiecesOnBoard().set(destination, captured);
            Game.getCurrentPlayer().removeFromPlayersPieces(promotedPiece);
            Game.getCurrentPlayer().addToPlayersPieces(fromPiece);
        }
    }

}
