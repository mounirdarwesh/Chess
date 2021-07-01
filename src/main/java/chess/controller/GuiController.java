package chess.controller;

import chess.Attributes;
import chess.model.*;
import chess.pgn.FenUtilities;
import chess.view.gui.GameView;
import chess.view.gui.Gui;
import chess.view.gui.PromotionPopUp;
import chess.view.gui.TileView;

import javafx.scene.control.Label;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
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
     * Game Clock
     */
    ChessClock chessClock;
    /**
     * to get if the Timer Running
     */
    boolean isClkRunning = false;
    /**
     * the GUI view
     */
    private Gui guiView;
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
     * create a game
     */
    public void createGame() {
        game = new Game(this,
                new Board(),
                new HumanPlayer(playerColor),
                opponent);
        game.getGameFENStrings().add("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR");
        game.loadPlayerPieces();
        guiView.setGame(game);
        guiView.createGameView();
        if (gameAgainstComputer && opponent.getColor().isWhite()) {
            letComputerPlay();
        }
    }

    /**
     * update game board and player
     */
    public void updateGame() {
        game.getGameFENStrings().add(FenUtilities.loadFENFromBoard(Game.getBoard()));
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
        updateGameView();
    }

    /**
     *
     * @param time
     */
    public void assignTimer(String time) {
        chessClock = new ChessClock(this, Long.parseLong(time));
        chessClock.start();
        isClkRunning = true;
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
        // If the player puts the mouse on an enemy piece or on an empty square
        // to move there
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
        if (piece != null) {
            if (allowReselect || game.getCurrentPlayer().isFirstClick()) {
                piece.calculateLegalMoves();
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
                String move = "move " + game.getAllowedMove();
                //dos.write(move.getBytes(StandardCharsets.UTF_8));
                Game.getCurrentPlayer().setHasPlayerUndidAMove(false);
                updateGame();
                updateGameView();
                historyCleared = false;
            }
        }
        if (allowReselect || game.getCurrentPlayer().isFirstClick()) {
            tile.deHighlight(highlightedTiles);
        }
    }

    /**
     * clearing history
     */
    public void clearUndidHistory() {
        if (guiView.getGameView().history.getChildren().size() <= 3
                || historyCleared) return;
        redidHistory = new ArrayList<>();
        redidHistory.add(guiView.getGameView().history.getChildren().get(
                guiView.getGameView().history.getChildren().size() - 2));
        redidHistory.add(guiView.getGameView().history.getChildren().get(
                guiView.getGameView().history.getChildren().size() - 1));

        guiView.getGameView().history.getChildren().remove(
                guiView.getGameView().history.getChildren().size() - 2,
                guiView.getGameView().history.getChildren().size()
        );
        if (allowedMoveHuman instanceof Move.CaptureMove) {
            beatenPieces = getBeatenPieces().remove(getBeatenPieces().size() - 1);
            guiView.getGameView().showBeaten();
        }
    }

    /**
     * add to history
     */
    public void addUndidHistory() {
        try {
            guiView.getGameView().history.getChildren().addAll(redidHistory);
            if (allowedMoveHuman instanceof Move.CaptureMove) {
                getBeatenPieces().add(beatenPieces);
                guiView.getGameView().showBeaten();
            }
        } catch (Exception e) {
            return;
        }
    }

    /**
     * undoing a move in History
     * @param index
     */
    public void undoMoveFromHistory(int index) {
        Game.getBoard().setPiecesOnBoard(game.getGameFENStrings().get(index - 1));
        if(Game.getCurrentPlayer().getColor().isWhite() && index % 2 == 0) {
            Game.getBoard().setPiecesOnBoard(game.getGameFENStrings().get(index-2));
            guiView.getGameView().changeMoveHistoryColor(index, 0);
        }
        else if(Game.getCurrentPlayer().getColor().isBlack() && index % 2 == 1) {
            Game.getBoard().setPiecesOnBoard(game.getGameFENStrings().get(index-2));
            guiView.getGameView().changeMoveHistoryColor(index, 1);
        }
        else {
            return;
        }
        Game.getCurrentPlayer().setHasPlayerUndidAMove(true);
        game.notifyObservers();
    }

    /**
     *
     * @param index
     */
    public void redidMoveFromHistory(int index) {
        game.getGameFENStrings().subList(index, game.getGameFENStrings().size()).clear();
        for (int i = index - 1; i<=game.getGameFENStrings().size()-1; i++) {
            try {
                guiView.getGameView().history.getChildren().remove(i+2);
            } catch (Exception e) {
                break;
            }
        }
        Game.getBoard().setPiecesOnBoard(game.getGameFENStrings().get(
                game.getGameFENStrings().size()-1
        ));
        Game.getCurrentPlayer().setHasPlayerUndidAMove(false);
        Game.setCurrentPlayer(Game.getOpponent(Game.getCurrentPlayer()));
        game.checkGameStatus();
        game.notifyObservers();
    }

    @Override
    public void processInputFromPlayer() {
    }

    @Override
    public void notifyView(Attributes.GameStatus status, Player player) {
        guiView.notifyUser(status, player);
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


    /**
     * notify Player left time
     *
     * @param time rest time
     */
    @Override
    public void showTime(String time) {
        guiView.notifyClock(time);
    }

    /**
     * Getter for Clock
     *
     * @return the clock
     */
    public ChessClock getChessClock() {
        return chessClock;
    }

    /**
     * Getter if the Timer Running
     *
     * @return true, if Running
     */
    public boolean isClockRunning() {
        return isClkRunning;
    }

    /**
     *
     * @param opponent
     */
    public void setOpponent(Player opponent) {
        this.opponent = opponent;
    }

    /**
     *
     * @param gameAgainstComputer
     */
    public void setGameAgainstComputer(boolean gameAgainstComputer) {
        this.gameAgainstComputer = gameAgainstComputer;
    }

    /**
     *
     * @param playerColor
     */
    public void setPlayerColor(Attributes.Color playerColor) {
        this.playerColor = playerColor;
    }
}
