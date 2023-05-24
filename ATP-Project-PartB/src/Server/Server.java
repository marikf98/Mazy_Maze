package Server;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    private int port;
    private int listeningIntervalMS;
    private IServerStrategy strategy;
    private boolean stop;
    private ExecutorService executor;
    private Configurations configurations;


    public Server(int port, int listeningIntervalMS, IServerStrategy strategy) {
        this.port = port;
        this.listeningIntervalMS = listeningIntervalMS;
        this.strategy = strategy;
        this.configurations = Configurations.getInstance();
    }

    public void start(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                running();
            }
        }).start();
    }


    public void running() {
        try {
            this.executor = Executors.newFixedThreadPool(configurations.getThreadPoolSize());
            ServerSocket serverSocket = new ServerSocket(port);
            serverSocket.setSoTimeout(listeningIntervalMS);
            while (!stop)
            {
                try
                {
                    Socket clientSocket = serverSocket.accept();
                    executor.execute(new Runnable() {
                        @Override
                        public void run() {
                            handClient(clientSocket);
                        }
                    });
                }
                catch (Exception ex)
                {
                    System.out.println(ex.getMessage());
                }
            }
            executor.shutdown();
            serverSocket.close();
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());

        }

    }
    private void handClient(Socket ClientSoc)
    {
        try
        {
            strategy.applyStrategy(ClientSoc.getInputStream(),ClientSoc.getOutputStream());
            ClientSoc.close();
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());

        }

    }
    public void stop(){stop=true;}
}
