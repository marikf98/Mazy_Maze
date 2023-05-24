package Client;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
/**This is the Client class**/
public class Client {
    private InetAddress serverIP;
    private int serverPort;
    private IClientStrategy strategy;

    /**This is the constructor of the client class
     * it receives a serverIp a port and a
     * strategy - the strategy will determine what the client will do **/
    public Client(InetAddress serverIP, int serverPort, IClientStrategy strategy) {
        this.serverIP = serverIP;
        this.serverPort = serverPort;
        this.strategy = strategy;
    }

    /**This method establishes communication with the server it creates a new socket and connects to its
     * and then sends that info to the strategy class **/
    public void communicateWithServer (){
        try
        {
            Socket serverSocket = new Socket(serverIP, serverPort);
            System.out.println("connected to server - IP = " + serverIP + ", Port = " + serverPort);
            strategy.clientStrategy(serverSocket.getInputStream(), serverSocket.getOutputStream());
        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
    }


}
