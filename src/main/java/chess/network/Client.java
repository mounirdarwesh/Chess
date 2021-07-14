package chess.network;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

/**
 * Client side of a Network
 * @author Gr.45
 */
public class Client extends Network {

    /**
     * name of the host
     */
    private String hostName;


    /**
     * client of the network game
     * @param host host of the game
     */
    public Client(String host) {
        hostName = host;
    }

    @Override
    public void run() {
        try {
            connectToServer();
            getStreams();
            processIncomingData();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * method of the server connection
     */
    private void connectToServer() {
        try {
            socket = new Socket(InetAddress.getByName(hostName), 28513);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
