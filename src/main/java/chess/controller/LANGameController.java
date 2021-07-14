package chess.controller;

import chess.Attributes;
import chess.model.game.LANGame;
import chess.model.pieces.Piece;
import chess.model.player.Player;
import chess.network.Client;
import chess.network.Server;
import chess.util.BoardMapper;
import chess.view.gui.gameview.GameView;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;

/**
 * controller of the LANGame class
 * @author Gr.45
 */
public class LANGameController extends Controller {

    /**
     * host(server) of the game
     */
    protected Server server;

    /**
     * client (to join to host) of the game
     */
    protected Client client;

    /**
     *
     */
    protected String lanSettings = "settings ";

    /**
     * settings of the LAN
     */
    protected String lanMove;

    /**
     * Undo Index of the network game
     */
    protected int lanUndoIndex;

    /**
     * redo Index of the network game
     */
    protected int lanRedoIndex;

    /**
     * undo of the network game
     */
    protected boolean lanUndo;

    /**
     * redo of the network game
     */
    protected boolean lanRedo;

    /**
     * The view that is connected to this controller
     */
    private GameView gameView;

    /**
     * The player that undid a move
     */
    private Player undidMovePlayer;

    /**
     * controller of the class
     * @param gameView the game view
     */
    public LANGameController(GameView gameView) {
        this.gameView = gameView;
    }

    /**
     * create a LANGame
     * @param portNr port number
     */
    public void createLANGame(int portNr) {
        initHostSettings();
        server = new Server(portNr);
        server.connectController(this);
        server.run();
        game = new LANGame(this);
    }


    /**
     * join a LAN game
     * @param ip ip address
     */
    public void joinLANGame(AtomicReference<String> ip) {
        client = new Client(ip.toString());
        client.connectController(this);
        client.run();
        game = new LANGame(this);
    }

    /**
     * initiate host settings
     */
    private void initHostSettings() {
        lanSettings += gameSettings[0] + " "
                + gameSettings[1] + " "
                + gameSettings[2] + " "
                + gameSettings[9];
    }

    /**
     * send data to the another Participant
     * @param msg move
     */
    public void sendData(String msg) {
        if(server != null) {
            server.sendData(msg);
        } else {
            client.sendData(msg);
        }
    }

    /**
     * receive data that contains the moved piece
     */
    public void receiveData() {
        try {
            if(server != null) {
                server.processIncomingData();
            } else {
                client.processIncomingData();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Undo a Move
     * @param index the index
     */
    @Override
    public void undoMove(int index) {
        if(gameSettings[1].equals("0")) {
            game.getCurrentPlayer().setHasPlayerUndidAMove(false);
            return;
        }
        // if there is no moves to undo, do nothing
        if(game.getAllListOfMoves().isEmpty()) return;

        //Clear the list of undid moves
        if(!undidMoves.isEmpty()) undidMoves.clear();

        //Add the moves that should be undone to the list of
        //undid moves
        undidMoves.addAll(game.getAllListOfMoves().subList(
                index, game.getAllListOfMoves().size()
        ));

        // And undo each move in that list
        for (Move toUndoMove : undidMoves) {
            toUndoMove.undo();
            game.getAllListOfMoves().remove(toUndoMove);
        }
        lanUndoIndex = undidMoves.size()-1 > 1 ? index : 1;

        // Finding out who made the undo
        int undidPiecePosition = BoardMapper.mapChessNotationToPosition(
                undidMoves.get(undidMoves.size()-1)
                .toString().substring(0, 2));
        Piece undidPiece = game.getBoard().getPiece(undidPiecePosition);

        undidMovePlayer = game.getCurrentPlayer().getColor() == undidPiece.getColor() ?
                            game.getCurrentPlayer() : game.getOpponent();

    }

    /**
     * redo a Move
     * @param index  the index of the move
     */
    @Override
    public void redoMove(int index) {
        if(gameSettings[1].equals("0")) {
            game.getCurrentPlayer().setHasPlayerRedidAMove(false);
            return;
        }
        if(undidMoves.isEmpty()) return;
        for(int i = 0; i<index; i++) {
            Move undidMove = undidMoves.get(i);
            undidMove.execute();
            game.getAllListOfMoves().add(undidMove);
        }
        undidMoves.subList(0, index).clear();
        lanRedoIndex = index;
        game.getCurrentPlayer().setHasPlayerRedidAMove(true);

    }

    /**
     * Undo a Move
     * @param index the index
     */
    public void undoLANMove(int index) {
        int lanIndex = index == 1 ?
                game.getAllListOfMoves().size()-1 : index;
        // if there is no moves to undo, do nothing
        if (game.getAllListOfMoves().isEmpty()) return;

        //Clear the list of undid moves
        if (!undidMoves.isEmpty()) undidMoves.clear();

        //Add the moves that should be undone to the list of
        //undid moves
        undidMoves.addAll(game.getAllListOfMoves().subList(
                lanIndex, game.getAllListOfMoves().size()
        ));
        // And undo each move in that list
        for (Move toUndoMove : undidMoves) {
            toUndoMove.undo();
            game.getAllListOfMoves().remove(toUndoMove);
        }
        // Finding out who made the undo
        int undidPiecePosition = BoardMapper.mapChessNotationToPosition(
                undidMoves.get(undidMoves.size()-1)
                        .toString().substring(0, 2));
        Piece undidPiece = game.getBoard().getPiece(undidPiecePosition);

        undidMovePlayer = game.getCurrentPlayer().getColor() == undidPiece.getColor() ?
                game.getCurrentPlayer() : game.getOpponent();
    }

    /**
     * getter of the Lan settings
     * @return String
     */
    public String getLanSettings() {
        return lanSettings;
    }

    /**
     * getter of the move in LAN
     * @return String
     */
    public String getLanMove() {
        return lanMove;
    }

    /**
     * setter of the moved in network game
     * @param lanMove a moved data in network game
     */
    public void setLanMove(String lanMove) {
        this.lanMove = lanMove;
    }

    /**
     * getter of Lan undo index
     * @return integer
     */
    public int getLanUndoIndex() {
        return lanUndoIndex;
    }

    /**
     * setter of lan undo index
     * @param lanUndoIndex lan undo index
     */
    public void setLanUndoIndex(int lanUndoIndex) {
        this.lanUndoIndex = lanUndoIndex;
    }

    /**
     * getter of lan redo index
     * @return integer
     */
    public int getLanRedoIndex() {
        return lanRedoIndex;
    }

    /**
     * setter of lan redo index
     * @param lanRedoIndex lan redo index
     */
    public void setLanRedoIndex(int lanRedoIndex) {
        this.lanRedoIndex = lanRedoIndex;
    }

    /**
     * Getter for Game View
     * @return gameview
     */
    public GameView getGameView() {
        return gameView;
    }

    /**
     * Getter has already undo
     * @return lanUndo
     */
    public boolean hasLanUndo() {
        return lanUndo;
    }

    /**
     * Setter for Undo
     * @param lanUndo true, if player undid a move
     */
    public void setLanUndo(boolean lanUndo) {
        this.lanUndo = lanUndo;
    }

    /**
     * Getter if a move redid
     * @return id a lan player redo
     */
    public boolean hasLanRedo() {
        return lanRedo;
    }

    /**
     * Setter for Redo
     * @param lanRedo the lan player redo
     */
    public void setLanRedo(boolean lanRedo) {
        this.lanRedo = lanRedo;
    }

    /**
     * Getter for the player that undid a move
     * @return the player that undid a move
     */
    public Player getUndidMovePlayer() {
        return undidMovePlayer;
    }

    /**
     * Setter for the player that undid a move
     * @param undidMovePlayer the player that undid a move
     */
    public void setUndidMovePlayer(Player undidMovePlayer) {
        this.undidMovePlayer = undidMovePlayer;
    }

    @Override
    public void notifyView(Attributes.GameStatus status, Player player) {
        gameView.getNotificationView().notifyPlayer(game, status, player);
    }
}
