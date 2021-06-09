package chess.controller;

import chess.Attributes;
import chess.gui.Gui;
import chess.model.*;

import java.util.List;

public class GuiController extends Controller {
    /**
     * Source Tile
     */
    int source;
    /**
     * Destination Tile
     */
    int destination;
    /**
     * contain if the Move Allowed
     */
    boolean wasLegalMove;

    /**
     * The constructor expects a view to construct itself.
     *
     * @param view The view that is connected to this controller
     */
    public GuiController(Gui view) {
        super(view);
        view.assignController(this);
        onActionPreformed();
    }

    /**
     * This will be called when someone interacts with the Command Line Interface
     */
    private void onActionPreformed() {
        // Create a new game
        game = new Game(this,
                new Board(),
                new HumanPlayer(Attributes.Color.WHITE),
                new HumanPlayer(Attributes.Color.BLACK));

        // Set the game to the CLI view
        view.setGame(game);

        // Start the game
        //game.run();
    }

    @Override
    public void processInputFromPlayer() {
        Player currentPlayer = game.getCurrentPlayer();
        this.wasLegalMove = game.isMoveAllowed(source, destination);
        if (wasLegalMove) {
            Move allowedMove = game.getAllowedMove();
            if (allowedMove != null) {
                currentPlayer.makeMove(allowedMove);
                game.setCurrentPlayer(game.getOpponent(currentPlayer));
                //game.getOpponent(game.getCurrentPlayer());
                //game.getBoard().getPiecesOnBoard().set(source, null);
            }
        } else System.out.println("not allowed");
    }

    /**
     * getter for the Source Tile from GameView.
     *
     * @param source source
     */
    public void setSource(int source) {
        this.source = source;
        System.out.println("Source Tile " + source);
    }

    /**
     * getter for the Destination Tile from GameView.
     *
     * @param destination Destination
     */
    public void setDestination(int destination) {
        this.destination = destination;
        System.out.println("Destination Tile " + destination);
    }

    /**
     * check if the selected Piece belongs to the PLayer
     * and the Tile not Empty.
     *
     * @param tileSourceId
     * @return
     */
    public boolean validSelection(int tileSourceId) {
        return game.getBoard().getPiece(tileSourceId) != null &&
                game.getCurrentPlayer().getColor()
                        == game.getBoard().getPiece(tileSourceId).getColor();
    }

    /**
     * Getter for the Game
     *
     * @return
     */
    public Game getGame() {
        return game;
    }

    /**
     * get if last Move was Legal.
     *
     * @return
     */
    public boolean wasLegalMove() {
        return wasLegalMove;
    }

    @Override
    public List<Piece> getBeatenPieces() {
        return game.getCurrentPlayer().getColor().isWhite() ?
                game.getWhitePlayer().getBeaten() : game.getBlackPlayer().getBeaten();
    }

    @Override
    public void notifyView(Attributes.GameStatus status, Player player) {

    }


}
