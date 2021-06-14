package chess.controller;

import chess.Attributes;
import chess.model.*;
import chess.view.gui.GameView;
import chess.view.gui.Gui;
import chess.view.gui.PromotionPopUp;
import chess.view.gui.TileView;
import java.util.ArrayList;
import java.util.List;

/**
 * The Controller class for the Gui Interface
 */
public class GuiController extends Controller {

    /**
     * contain if the Move Allowed
     */
    boolean wasALegalMove;
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
    private boolean gameAgainstComputer = false;

    /**
     *
     */
    private boolean allowReselect = true;

    /**
     * @param guiView The view that is connected to this controller
     */
    public GuiController(Gui guiView) {
        super(guiView);
        this.guiView = guiView;
    }

    /**
     * Whenever the user interacts with the start screen menu
     */
    public void gameModeOnAction(Attributes.GameMode gameMode) {
        if (gameMode == Attributes.GameMode.HUMAN) {
            gameAgainstComputer = false;
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
     * @param color color of the player on action
     */
    public void colorChoiceOnAction(Attributes.Color color) {
        if (color == Attributes.Color.WHITE) {
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
     * @param tiles tiles of the board
     * @param tile  the clicked tile
     */
    public void handleClickOnTileAction(List<TileView> tiles, TileView tile) {
        Piece piece = game.getBoard().getPiece(tile.getTileID());
        // If the player clicks on a tile or the game has ended then do noting
        if (game.isFINISHED()) {
            return;
        }
        // If the player puts the mouse on an enemy piece, capture
        if (movePieceActionException(piece)) {
            movePieceAction(toMovePiece, tile);
            return;
        }
        // If there is highlights on the board then delete them
        if (!highlightedTiles.isEmpty() &&
                (allowReselect || game.getCurrentPlayer().isFirstClick())) {
            tiles.get(tile.getTileID()).deHighlight(highlightedTiles);
        }

        // If the player wants to move a piece on the board
        if(allowReselect || game.getCurrentPlayer().isFirstClick()) {
            game.getCurrentPlayer().calculatePlayerMoves();
            highlightedTiles = new ArrayList<>();
            for (Move move : piece.getAllLegalMoves()) {
                tiles.get(move.destination).highlight(GameView.highlightVisibility.isSelected());
                highlightedTiles.add(tiles.get(move.destination));
                toMovePiece = piece;
            }
            game.getCurrentPlayer().setFirstClick(false);
        }
    }

    /**
     *
     * @param piece
     * @return
     */
    private boolean movePieceActionException(Piece piece) {
        return toMovePiece != null && piece == null
                || piece != null
                && piece.getColor() != game.getCurrentPlayer().getColor();
    }

    /**
     * @param toMovePiece the selected piece
     * @param tile the selected tile
     */
    public void movePieceAction(Piece toMovePiece, TileView tile) {
        if (toMovePiece != null) {
            // check if there is chance to Promote.
            handlePromote(toMovePiece);
            this.wasALegalMove = game.isMoveAllowed(toMovePiece.getPosition(), tile.getTileID());
            if (wasALegalMove) {
                game.getCurrentPlayer().makeMove(game.getAllowedMove());
                updateGameView();
                updateGame();
                updateGameView();
            }
        }
        if(allowReselect || game.getCurrentPlayer().isFirstClick()) {
            tile.deHighlight(highlightedTiles);
        }
    }

    /**
     *
     */
    private void updateGame() {
        game.setCurrentPlayer(game.getOpponent(game.getCurrentPlayer()));
        game.getCurrentPlayer().setFirstClick(true);
        game.checkGameStatus();
        if (!game.isFINISHED() && gameAgainstComputer) {
            letComputerPlay();
        }
        game.notifyObservers();
    }

    /**
     *
     */
    private void updateGameView() {
        guiView.getGameView().showHistory();
        guiView.getGameView().showBeaten();
        guiView.getGameView().notification();
        if (GameView.rotate.isSelected())
            guiView.getGameView().getBoard().rotate(game.getCurrentPlayer().getColor());
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
        if (gameAgainstComputer && opponent.getColor().isWhite()) {
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
            if (game.isMoveAllowed(move_from, move_to)) {
                game.getCurrentPlayer().makeMove(computerMove);
                break;
            }
        }
        game.setCurrentPlayer(game.getOpponent(game.getCurrentPlayer()));
    }


    @Override
    public void processInputFromPlayer() {
    }

    @Override
    public void notifyView(Attributes.GameStatus status, Player player) {
        guiView.notifyUser(status, player);
    }

    /**
     * contains if the last move was a Legal move
     * @return true, if legal.
     */
    public boolean wasLegalMove() {
        return wasALegalMove;
    }

    /**
     * check if the Pawn can Promote.
     * @param color    of the Pawn
     * @param position of the Pawn
     * @return true, if he can promote.
     */
    public boolean canPromote(Attributes.Color color, int position) {
        return color.isWhite() && 48 <= position && position <= 55 ||
                color.isBlack() && 8 <= position && position <= 15;
    }

    /**
     * when a Pawn can Promote, show a PopUp Window to select the promoted Piece.
     * @param piece of the Pawn.
     */
    public void handlePromote(Piece piece) {
        if (piece instanceof Pawn && canPromote(piece.getColor(), piece.getPosition())) {
            PromotionPopUp.displayPopUp();
            game.setCharToPromote(PromotionPopUp.promote);
        }
    }

    /**
     *
     * @param allowReselect
     */
    public void setAllowReselect(boolean allowReselect) {
        this.allowReselect = allowReselect;
        game.getCurrentPlayer().setFirstClick(true);
    }
}
