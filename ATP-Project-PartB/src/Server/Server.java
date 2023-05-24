package Server;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * This is the server class**/
public class Server {
    private int port;
    private int listeningIntervalMS;
    private IServerStrategy strategy;
    private boolean stop;
    private ExecutorService executor;
    private Configurations configurations;

    /**The constructor receives a port a listening interval and a strategy that the server will implement **/
    public Server(int port, int listeningIntervalMS, IServerStrategy strategy) {
        this.port = port;
        this.listeningIntervalMS = listeningIntervalMS;
        this.strategy = strategy;
        this.configurations = Configurations.getInstance(); // and here we give the server the instance of the singleton of the configurations class
    }

    /**This method starts the server by creating its thread**/
    public void start()
    {
        new Thread(new Runnable() {
            @Override
            public void run() {
                running();
            }
        }).start();
    }


    /**
     * This is the function that runs in the server thread**/
    public void running() {
        try {
            this.executor = Executors.newFixedThreadPool(configurations.getThreadPoolSize()); // here we create the thread pool for all the connections that the server can get
            ServerSocket serverSocket = new ServerSocket(port);
            serverSocket.setSoTimeout(listeningIntervalMS);
            while (!stop)
            {
                try
                {
                    Socket clientSocket = serverSocket.accept(); // connect the client to the server
                    executor.execute(new Runnable() {
                        @Override
                        public void run() {
                            handClient(clientSocket);// here we apply the strategy to the connected client
                        }
                    });
                }
                catch (Exception ex)
                {
                    System.out.println(ex.getMessage());
                }
            }
            executor.shutdown(); // here we close all the threads
            serverSocket.close();// and here we close the socket of the server
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());

        }

    }
    /**This is the handleClient function that applies the server strategy to each client that is connected to the server**/
    private void handClient(Socket ClientSoc)
    {
        try
        {
            strategy.applyStrategy(ClientSoc.getInputStream(),ClientSoc.getOutputStream()); // here we apply the strategy to the client
            ClientSoc.close();
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());

        }

    }
    public void stop(){stop=true;}
}
