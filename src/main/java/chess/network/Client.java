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
     * port number
     */
    private int portNr;

    /**
     * client of the network game
     * @param host host of the game
     * @param port port number
     */
    public Client(String host, int port) {
        hostName = host;
        portNr = port;
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
            socket = new Socket(InetAddress.getByName(hostName), portNr);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
