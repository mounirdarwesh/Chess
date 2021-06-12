package chess.controller;

import chess.Attributes;
import chess.model.*;
import chess.view.View;
import chess.view.guiView.Gui;
import chess.view.guiView.TileView;
import javafx.event.Event;
import javafx.scene.Scene;

import java.util.ArrayList;
import java.util.List;

public class GuiController extends Controller{
    /**
     *
     */
    private Gui guiView;

    /**
     * The opponent
     */
    private Player opponent;

    /**
     *
     */
    private Attributes.Color playerColor;

    /**
     *
     */
    private List<TileView> highlightedTiles = new ArrayList<>();

    /**
     *
     */
    private Piece toMovePiece = null;

    /**
     *
     */
    private Move validMove = null;

    /**
     *
     */
    private boolean gameAgainstComputer = false;

    /**
     * @param guiView
     */
    public GuiController(Gui guiView) {
        super(guiView);
        this.guiView = guiView;
    }

    /**
     *Whenever the user interacts with the start screen menu
     */
    public void gameModeOnAction(Attributes.GameMode gameMode) {
        if(gameMode == Attributes.GameMode.HUMAN){
            opponent = new HumanPlayer(Attributes.Color.BLACK);
            playerColor = Attributes.Color.WHITE;
            createGame();
            guiView.createGameView();
        } else {
            guiView.getMainMenu().showColorChoiceWindow();
            gameAgainstComputer = true;
        }

    }

    /**
     *
     * @param color
     */
    public void colorChoiceOnAction(Attributes.Color color) {
        if(color == Attributes.Color.WHITE) {
            playerColor = Attributes.Color.WHITE;
            opponent = new Computer(Attributes.Color.BLACK);
        } else {
            playerColor = Attributes.Color.BLACK;
            opponent = new Computer(Attributes.Color.WHITE);
        }
        guiView.getMainMenu().getColorChoice().close();
        createGame();
        guiView.createGameView();
    }

    /**
     *
     * @param tiles
     * @param tileID
     */
    public void handleClickOnTileToHighlight(List<TileView> tiles, int tileID) {
        Piece piece = game.getBoard().getPiece(tileID);
        if(piece.getColor() != game.getCurrentPlayer().getColor()) {
            handleClickOnTileToMovePiece(tiles.get(tileID));
            return;
        }
        toMovePiece = null;
        if(!highlightedTiles.isEmpty()) {
            tiles.get(tileID).deHighlight(highlightedTiles);
            return;
        }
        game.getCurrentPlayer().calculatePlayerMoves();
        highlightedTiles = new ArrayList<>();
        for (Move move : piece.getAllLegalMoves()) {
            tiles.get(move.destination).highlight();
            highlightedTiles.add(tiles.get(move.destination));
            toMovePiece = piece;
        }
    }

    /**
     *
     * @param tile
     */
    public void handleClickOnTileToMovePiece(TileView tile) {
        if (toMovePiece != null) {
            if (game.isMoveAllowed(toMovePiece.getPosition(), tile.getTileID())) {
                game.getCurrentPlayer().makeMove(game.getAllowedMove());
                updateGame();
            }
        }
        tile.deHighlight(highlightedTiles);
        toMovePiece = null;
    }

    /**
     *
     */
    private void updateGame() {
        game.setCurrentPlayer(game.getOpponent(game.getCurrentPlayer()));
        if(gameAgainstComputer) {
            letComputerPlay();
        }
        game.checkGameStatus();
        game.notifyObservers();
    }

    /**
     *
     */
    public void createGame() {
        game = new Game(this,
                new Board(),
                new HumanPlayer(playerColor),
                opponent);
        game.loadPlayerPieces();
        guiView.setGame(game);
        if(gameAgainstComputer && opponent.getColor().isWhite()) {
            letComputerPlay();
        }
    }

    /**
     *
     */
    private void letComputerPlay() {
        Move computerMove;
        while (true) {
            computerMove = ((Computer) game.getCurrentPlayer()).evaluate();
            // Calculate from where the move is performed
            int move_from = getMoveFromPosition(computerMove.toString());
            // Calculate to where the move is performed
            int move_to = getMoveToPosition(computerMove.toString());
            if(game.isMoveAllowed(move_from, move_to)) break;
        }
        game.getCurrentPlayer().makeMove(computerMove);
        game.setCurrentPlayer(game.getOpponent(game.getCurrentPlayer()));
    }

    @Override
    public void processInputFromPlayer() {
        // The current player of the game
        Player currentPlayer = game.getCurrentPlayer();

        // Read the input from the view
        if(currentPlayer instanceof HumanPlayer && validMove != null) {
            currentPlayer.makeMove(validMove);
        } else if(currentPlayer instanceof Computer) {
            Move computerMove;
            computerMove = ((Computer) currentPlayer).evaluate();
            currentPlayer.makeMove(computerMove);
        }
    }

    @Override
    public void notifyView(Attributes.GameStatus status, Player player) {

    }
}
