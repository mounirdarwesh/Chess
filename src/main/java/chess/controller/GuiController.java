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
     * the GUI view
     */
    private Gui guiView;
    /**
     * The opponent
     */
    private Player opponent;
    /**
     * shows the color of the player
     */
    private Attributes.Color playerColor;
    /**
     * list of the highlighted tiles
     */
    private List<TileView> highlightedTiles = new ArrayList<>();
    /**
     * the piece that player clicked on it
     */
    private Piece toMovePiece = null;

    /**
     * if the game is against AI or not
     */
    private boolean gameAgainstComputer = false;

    /**
     * allows player to reselect a piece
     */
    private boolean allowReselect = true;
    /**
     * Computer Move
     */
    private Move computerMove;
    /**
     * the last legal Human Move.
     */
    private Move allowedMoveHuman;

    /**
     * constructor of the class
     *
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
     * color of the player against AI
     *
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
     * handles the click on a tile of the board
     *
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
        if (allowReselect || game.getCurrentPlayer().isFirstClick()) {
            game.getCurrentPlayer().calculatePlayerMoves();
            highlightedTiles = new ArrayList<>();
            for (Move move : piece.getAllLegalMoves()) {
                tiles.get(move.destination).highlight(GameView.highlightVisibility.isSelected());
                highlightedTiles.add(tiles.get(move.destination));
                toMovePiece = piece;
            }
            game.getCurrentPlayer().setFirstClick(false);
        }
        // check if there is chance to Promote.
        handlePromote(toMovePiece);
    }

    /**
     * condition to make a move for a selected piece
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
     * make a move for a selected piece
     *
     * @param toMovePiece the selected piece
     * @param tile        the selected tile
     */
    public void movePieceAction(Piece toMovePiece, TileView tile) {
        if (toMovePiece != null) {
            this.wasALegalMove = game.isMoveAllowed(toMovePiece.getPosition(), tile.getTileID());
            this.allowedMoveHuman = game.getAllowedMove();
            if (wasALegalMove) {
                game.getCurrentPlayer().makeMove(game.getAllowedMove());
                updateGame();
                updateGameView();
            }
        }
        if (allowReselect || game.getCurrentPlayer().isFirstClick()) {
            tile.deHighlight(highlightedTiles);
        }
    }

    /**
     * update game board and player
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
     * update all the panels
     */
    private void updateGameView() {
        guiView.getGameView().showHistory();
        guiView.getGameView().showBeaten();
        guiView.getGameView().notification();
        if (GameView.rotate.isSelected())
            guiView.getGameView().getBoard().rotate(game.getCurrentPlayer().getColor());
        if (gameAgainstComputer)
            guiView.getGameView().showHistoryComp();
    }

    /**
     * create a game
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
     * let AI to make a move
     */
    private void letComputerPlay() {
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
     *
     * @return true, if legal.
     */
    public boolean wasLegalMove() {
        return wasALegalMove;
    }

    /**
     * check if the Pawn can Promote.
     *
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
     *
     * @param piece of the Pawn.
     */
    public void handlePromote(Piece piece) {
        piece.calculateLegalMoves();
        if (piece instanceof Pawn && canPromote(piece.getColor(), piece.getPosition())
                && piece.getAllLegalMoves() != null) {
            PromotionPopUp.displayPopUp();
            game.setCharToPromote(PromotionPopUp.promote);
        }
    }

    /**
     * allows to reselect another piece
     *
     * @param allowReselect true, if Figure reselect allowed.
     */
    public void setAllowReselect(boolean allowReselect) {
        this.allowReselect = allowReselect;
        game.getCurrentPlayer().setFirstClick(true);
    }

    /**
     * Getter Human last legal Move
     *
     * @return last legal Move
     */
    public Move getAllowedMoveHuman() {
        return allowedMoveHuman;
    }

    /**
     * Getter last Computer Move.
     *
     * @return last Computer Move.
     */
    public Move getComputerMove() {
        return computerMove;
    }
}
