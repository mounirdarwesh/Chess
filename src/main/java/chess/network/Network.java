package chess.network;

import chess.controller.LANGameController;
import java.io.*;
import java.net.Socket;

/**
 * class of the network option
 * @author Gr.45
 */
public abstract class Network {
    /**
     * controller of the LANGame
     */
    protected LANGameController lanGameController;

    /**
     * printing to a text-output stream
     */
    protected PrintWriter out;

    /**
     * reader of the buffer
     */
    protected BufferedReader in;

    /**
     * Network socket
     */
    protected Socket socket;

    /**
     * the Received Message
     */
    protected String received;

    /**
     * abstract method of run the game
     */
    public abstract void run();

    /**
     * getter of the streams
     * @throws IOException if there is no Sting
     */
    public void getStreams() throws IOException {
        out = new PrintWriter(socket.getOutputStream());
        out.flush();
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    /**
     * close the connection
     */
    public void closeConnection() {
        try {
            if (out != null) {
                out.close();
            }
            if (in != null) {
                in.close();
            }
            if (socket != null) {
                socket.close();
            }
        } catch (IOException ignored) {}
    }

    /**
     * process incoming data
     * @throws IOException throw a exception if no message delivered
     */
    public void processIncomingData() throws IOException {
        try {
            received = in.readLine();
            System.out.println(received);
            String[] requests = received.split(" ");
            handleRequests(requests);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * handle the incoming requests
     * @param requests the request
     */
    protected void handleRequests(String[] requests) {
        switch (requests[0]) {
            case "settings":
                handleSettingsRequests(requests);
                break;
            case "move":
                lanGameController.setLanMove(requests[1]);
                break;
            case "undo":
                if(requests.length == 1) {
                    lanGameController.setLanUndo(true);
                    lanGameController.setLanUndoIndex(1);
                } else {
                    lanGameController.setLanUndoIndex(Integer.parseInt(requests[1]));
                }
            case "redo":
                if(requests.length == 1) {
                    lanGameController.setLanRedo(true);
                    lanGameController.setLanUndoIndex(1);
                } else {
                    lanGameController.setLanRedoIndex(Integer.parseInt(requests[1]));
                }
        }
    }

    /**
     * handle the requests of settings
     * @param requests setting request
     */
    protected void handleSettingsRequests(String[] requests) {
        if(requests[1].equals("1")) {
            lanGameController.setGameSettings(0, "0");
        }
        lanGameController.setGameSettings(1, requests[2]);
        lanGameController.setGameSettings(2, requests[3]);
        lanGameController.setGameSettings(9, requests[4]);
    }

    /**
     * send data
     * @param data the data to send
     */
    public void sendData(String data) {
        System.out.println(data);
        out.println(data);
        out.flush();
    }

    /**
     * controller of the connection
     * @param lanGameController  lanGameController
     */
    public void connectController(LANGameController lanGameController){
        this.lanGameController = lanGameController;
    }

}
