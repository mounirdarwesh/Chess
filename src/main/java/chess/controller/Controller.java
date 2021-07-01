package chess.controller;

import chess.Attributes;
import chess.model.*;
import chess.view.View;
import javafx.scene.Node;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * An abstract class of the controller
 *
 * @author Gr.45
 */
public abstract class Controller {

    /**
     * The game as model that is connected to this controller
     */
    protected Game game;

    /**
     * The CLI view that is connected to this controller
     */
    protected View view;

    /**
     * A board mapper, to map each input to its respective index
     */
    protected static final MapBoard MAPPER = new MapBoard();

    /**
     * Pattern to verify the syntax of the User Input
     */
    protected static final Pattern VALID_INPUT = Pattern.compile("([a-h][1-8])([-])([a-h][1-8])([QRNBqrnb]?)", Pattern.CASE_INSENSITIVE);

    /**
     *
     */
    protected List<String> redidFENs;

    /**
     * The mode of the game
     */
    protected Attributes.GameMode gameMode;

    /**
     *
     */
    protected List<Node> redidHistory;

    /**
     *
     */
    protected boolean historyCleared = false;

    /**
     *
     */
    protected Piece beatenPieces;
    /**
     *
     */
    protected Socket socket;
    /**
     *
     */
    protected DataOutputStream dos;
    /**
     *
     */
    protected DataInputStream dis;
    /**
     *
     */
    protected ServerSocket serverSocket;

    /**
     * The opponent
     */
    protected Player opponent;
    /**
     * shows the color of the player
     */
    protected Attributes.Color playerColor;
    /**
     *
     */
    protected boolean allowRedo;
    /**
     *
     */
    protected boolean allowTimer;


    /**
     * The constructor expects a CLI view to construct itself.
     *
     * @param view The view that is connected to this controller
     */
    public Controller(View view) {
        this.view = view;
    }

    /**
     * Processing the input from the player
     */
    public abstract void processInputFromPlayer();

    /**
     * Returning the beaten pieces of the player
     *
     * @return beaten pieces of the player
     */
    public List<Piece> getBeatenPieces() {
        return game.getCurrentPlayer().getColor().isWhite() ?
                game.getWhitePlayer().getBeaten() : game.getBlackPlayer().getBeaten();
    }

    /**
     * Method to notify the user of the status of the game
     *
     * @param status The status of the game
     * @param player The player
     */
    public abstract void notifyView(Attributes.GameStatus status, Player player);

    /**
     * Here where we calculate the position where the piece should reside
     *
     * @param input The input form the player
     * @return The index of the selected piece
     */
    public int getMoveFromPosition(String input) {
        Matcher matcher = VALID_INPUT.matcher(input);
        matcher.matches();
        String fromIn = matcher.group(1);
        return MAPPER.map(fromIn);
    }

    /**
     * Here where we calculate the position to where the piece should move
     *
     * @param input The input form the player
     * @return The index of the destination
     */
    public int getMoveToPosition(String input) {
        Matcher matcher = VALID_INPUT.matcher(input);
        matcher.matches();
        String toIn = matcher.group(3);
        return MAPPER.map(toIn);
    }

    /**
     *
     */
    public void undoMove() {
        int fullFENsSize = game.getGameFENStrings().size();
        if (fullFENsSize <= 2 || Game.getCurrentPlayer().hasPlayerUndidAMove()) {
            return;
        }
        redidFENs = new ArrayList<>();
        redidFENs.add(
                game.getGameFENStrings().remove(game.getGameFENStrings().size() - 2)
        );
        redidFENs.add(
                game.getGameFENStrings().remove(game.getGameFENStrings().size() - 1)
        );
        Game.getBoard().setPiecesOnBoard(game.getGameFENStrings().get(
                game.getGameFENStrings().size() - 1
        ));
        Game.getCurrentPlayer().setHasPlayerUndidAMove(true);
    }

    /**
     *
     */
    public void redoMove() {
        if (!Game.getCurrentPlayer().hasPlayerUndidAMove()) {
            return;
        }
        game.getGameFENStrings().addAll(redidFENs);
        Game.getCurrentPlayer().setHasPlayerUndidAMove(false);
        Game.getCurrentPlayer().setHasPlayerRedidAMove(true);
        Game.getBoard().setPiecesOnBoard(game.getGameFENStrings().get(
                game.getGameFENStrings().size() - 1));
    }

    /**
     *
     */
    public void initServer() {
        try {
            serverSocket = new ServerSocket(23456,
                    8, InetAddress.getByName("localhost"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param ip
     * @param port
     */
    public void joinHostServer(String ip, String port) {
        System.out.println("s");
        try {
            socket = new Socket(ip, Integer.parseInt(port));
            dos = new DataOutputStream(socket.getOutputStream());
            dis = new DataInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("s");
    }

    /**
     *
     * @param port
     * @return
     */
    public boolean checkJoinLANSettings(String port) {
        return Integer.parseInt(port) < 1 || Integer.parseInt(port) > 65535;
    }

    /**
     *
     * @param piecesColor
     * @param undoAllowed
     * @param timerAllowed
     */
    public void initLANGameSettings(String piecesColor, String undoAllowed, String timerAllowed) {
        switch (piecesColor) {
            case "White":
                playerColor = Attributes.Color.WHITE;
                opponent = new HumanPlayer(Attributes.Color.BLACK);
                break;
            case "Black":
                playerColor = Attributes.Color.BLACK;
                opponent = new HumanPlayer(Attributes.Color.WHITE);
                break;
        }

        switch (undoAllowed) {
            case "Yes":
                allowRedo = true;
                break;
            case "No":
                allowRedo = false;
                break;
        }

        switch (timerAllowed) {
            case "Yes":
                allowTimer = true;
                break;
            case "No":
                allowTimer = false;
                break;
        }
    }

    /**
     *
     * @return
     */
    public boolean listenForServerRequest() {
        boolean accepted = false;
        Socket socket;
        try {
            socket = serverSocket.accept();
            dos = new DataOutputStream(socket.getOutputStream());
            dis = new DataInputStream(socket.getInputStream());
            accepted = true;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return accepted;
    }

    /**
     * @return historyCleared
     */
    public boolean isHistoryCleared() {
        return historyCleared;
    }

    /**
     * setter for historyCleared
     */
    public void setHistoryCleared(boolean historyCleared) {
        this.historyCleared = historyCleared;
    }

    /**
     * get timer Information from Clock to show on Interface
     *
     * @param time left time
     */
    public abstract void showTime(String time);

}
