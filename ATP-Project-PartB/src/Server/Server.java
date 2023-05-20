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

//
//    public Server(int port, int listeningIntervalMS, IServerStrategy strategy) {
//        this.port = port;
//        this.listeningIntervalMS = listeningIntervalMS;
//        this.strategy = strategy;
//
//        this.executor = Executors.newFixedThreadPool(Configurations.getInstance().getThreadPoolSize() );
//    }
//
//    public void start() {
//        try {
//            ServerSocket serverSocket = new ServerSocket(port);
//            serverSocket.setSoTimeout(listeningIntervalMS);
//            System.out.println("Starting server at port = " + port);
//
//            while (!stop) {
//                try {
//                    Socket clientSocket = serverSocket.accept();
//                    System.out.println("Client accepted: " + clientSocket.toString());
//
////                    executor.execute(() -> {
////                        try {
////                            strategy.applyStrategy(clientSocket.getInputStream(), clientSocket.getOutputStream());
////                            clientSocket.close();
////                        } catch (IOException e) {
////                            e.printStackTrace();
////                        }
////                    });
//                    new Thread(() ->{
//                        handleClient(clientSocket);
//                    }).start();
//                } catch (SocketTimeoutException e) {
//                    System.out.println("Socket timeout");
//                }
//            }
//
//            executor.shutdown(); // Shutdown the thread pool gracefully after stopping the server
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void stop() {
//        stop = true;
//    }
//
//    private void handleClient(Socket clientSocket)
//    {
//        try
//        {
//            strategy.applyStrategy(clientSocket.getInputStream(), clientSocket.getOutputStream());
//            clientSocket.close();
//
//        }
//        catch (IOException e)
//        {
//           e.printStackTrace();
//        }
//    }

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
                Runing();
            }
        }).start();
    }


    public void Runing() {
        try {
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
                            HandClient(clientSocket);
                        }
                    });
                }
                catch (Exception ex)
                {}
            }
            executor.shutdown();
            serverSocket.close();
        }
        catch (Exception ex)
        {}

    }
    private void HandClient(Socket ClientSoc)
    {
        try
        {
            strategy.applyStrategy(ClientSoc.getInputStream(),ClientSoc.getOutputStream());
            ClientSoc.close();
        }
        catch (Exception ex)
        {}

    }
    public void stop(){stop=true;}
}
