package chess.network;

import java.io.IOException;
import java.net.ServerSocket;
/**
 * class of the server or host of the network game
 * @author Gr.45
 */
public class Server extends Network {

    /**
     * server socket
     */
    private ServerSocket serverSocket;

    /**
     * listener for the port number
     */
    private final int listenPortNr;
    /**
     * port number of the server
     * @param listen_Port_Nr listener for the port number
     */
    public Server(int listen_Port_Nr) {
        listenPortNr = listen_Port_Nr;
    }



    @Override
    public void run() {
        try {
            serverSocket = new ServerSocket(listenPortNr, 1);
            try {
                waitForConnection();
                getStreams();
                sendData(lanGameController.getLanSettings());
            }
            catch (IOException e){
                e.printStackTrace();
            }
        } catch (IOException ignored) {}
    }

    /**
     * wait for a connection
     */
    private void waitForConnection() throws IOException {
        socket = serverSocket.accept();
    }

    /**
     * close the Connection
     */
    @Override
    public void closeConnection() {
        super.closeConnection();
        try {
            serverSocket.close();
        } catch (IOException ignored) {}
    }
}