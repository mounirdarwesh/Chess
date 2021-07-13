package chess.controller;

import chess.Attributes;
import chess.model.game.LANGame;
import chess.model.player.Player;
import chess.network.Client;
import chess.network.Server;
import chess.view.gui.gameView.GameView;
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
     * @param port port number
     */
    public void joinLANGame(AtomicReference<String> ip, AtomicReference<String> port) {
        client = new Client(ip.toString(), Integer.parseInt(port.toString()));
        client.connectController(this);
        client.run();
        game = new LANGame(this);
    }

    /**
     * initiate host settings
     */
    private void initHostSettings() {
        lanSettings += gameSettings[0] + " " + gameSettings[1] + " " + "0" + " " + gameSettings[2];
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
     * @return gameView
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

    @Override
    public void notifyView(Attributes.GameStatus status, Player player) {
        gameView.getNotificationView().notifyPlayer(game, status, player);
    }
}
