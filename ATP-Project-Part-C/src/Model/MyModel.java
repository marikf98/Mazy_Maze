package Model;

import Client.Client;
import IO.MyDecompressorInputStream;
import Client.IClientStrategy;
import Server.*;
import algorithms.mazeGenerators.Maze;
import algorithms.search.Solution;
import javafx.scene.input.KeyCode;

import javax.xml.transform.SourceLocator;
import java.io.*;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Observable;


public class MyModel extends Observable implements IModel
{

    private Server m_mazeGeneratorServer;

    private Server m_mazeSolveServer;

    private Solution m_solution;
    private Maze m_maze;

    private int m_characterPositionRow;
    private int m_characterPositionCol;
    
    public static String playerDirection = "right";

    public MyModel() {
        this.m_mazeGeneratorServer = new Server(5400,1000, new ServerStrategyGenerateMaze());
        this.m_mazeSolveServer = new Server(5401,1000, new ServerStrategySolveSearchProblem());
    }

    @Override
    public Maze getMaze() {
        return m_maze;
    }

    @Override
    public Solution getSolution() {
        return m_solution;
    }

    @Override
    public void setMaze(Maze maze) {
        this.m_maze = maze;
        setChanged();
        notifyObservers();
    }

    public void setSolution(Solution solution)
    {
        m_solution = solution;
        setChanged();
        notifyObservers();
    }

    @Override
    public void start() {
        m_mazeGeneratorServer.start();
        m_mazeSolveServer.start();
    }

    @Override
    public void stopServers() {
        m_mazeGeneratorServer.stop();
        m_mazeSolveServer.stop();
    }

    @Override
    public int getCharacterPositionRow() {
        return m_characterPositionRow;
    }

    @Override
    public int getCharacterPositionColumn() {
        return m_characterPositionCol;
    }

    @Override
    public void setPosition(int row, int col) {
        m_characterPositionRow = row;
        m_characterPositionCol = col;
        setChanged();
        notifyObservers();
    }

    public void generateMazeWithServers(int row, int col) {
        try{
            Client client = new Client(InetAddress.getLocalHost(), 5400, new IClientStrategy() {
                public void clientStrategy(InputStream inFromServer, OutputStream outToServer)
                {
                    try{
                        ObjectOutputStream toServer = new ObjectOutputStream(outToServer);
                        ObjectInputStream fromServer = new ObjectInputStream(inFromServer);
                        toServer.flush();
                        int[] mazeSize = new int[]{row,col};
                        toServer.writeObject(mazeSize);
                        toServer.flush();
                        byte[] compressedMaze = (byte[]) fromServer.readObject();
                        InputStream is = new MyDecompressorInputStream(new ByteArrayInputStream(compressedMaze));
                        byte[] decompressedMaze = new byte[(row*col) + 10];
                        is.read(decompressedMaze);
                        m_maze = new Maze(decompressedMaze);
                        setMaze(m_maze);

                        setPosition(m_maze.getStartPosition().getRowIndex(),m_maze.getStartPosition().getColumnIndex());
                    }


                    catch (Exception e)
                    {
                        throw new RuntimeException(e);
                    }
                }
            });
            client.communicateWithServer();

        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }

    }

    public void generateMaze(int row,int col)
    {
        generateMazeWithServers(row,col);
        setChanged();
        notifyObservers();
    }

    @Override
    public void solve() {
        try
        {
            Client client = new Client(InetAddress.getLocalHost(), 5401, new IClientStrategy()
            {
                public void clientStrategy(InputStream inFromServer, OutputStream outToServer)
                {
                    try{
                        ObjectOutputStream toServer = new ObjectOutputStream(outToServer);
                        ObjectInputStream fromServer = new ObjectInputStream(inFromServer);
                        toServer.flush();
                        toServer.writeObject(m_maze);
                        toServer.flush();
                        Solution solution = (Solution)fromServer.readObject();
                        setSolution(solution);
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                }
            });
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }


    @Override
    public boolean moveCharacter(KeyCode move) {
        int newRow = m_characterPositionRow;
        int newCol = m_characterPositionCol;

        switch (move) {
            case NUMPAD2:
            case DOWN:
                newRow++;
                playerDirection = "down";
                break;

            case NUMPAD8:
            case UP:
                newRow--;
                playerDirection = "up";
                break;

            case NUMPAD4:
            case LEFT:
                newCol--;
                playerDirection = "left";
                break;

            case NUMPAD6:
            case RIGHT:
                newCol++;
                playerDirection = "right";
                break;

            case NUMPAD3:
                if (canMove(m_characterPositionRow + 1, m_characterPositionCol + 1) && (canMove(m_characterPositionRow + 1, m_characterPositionCol) || canMove(m_characterPositionRow, m_characterPositionCol + 1))) {
                    newRow++;
                    newCol++;
                }
                playerDirection = "down";
                break;

            case NUMPAD1:
                if (canMove(m_characterPositionRow + 1, m_characterPositionCol - 1) && (canMove(m_characterPositionRow, m_characterPositionCol - 1) || canMove(m_characterPositionRow + 1, m_characterPositionCol))) {
                    newRow++;
                    newCol--;
                }
                playerDirection = "down";
                break;

            case NUMPAD9:
                if (canMove(m_characterPositionRow - 1, m_characterPositionCol + 1) && (canMove(m_characterPositionRow, m_characterPositionCol + 1) || canMove(m_characterPositionRow - 1, m_characterPositionCol))) {
                    newRow--;
                    newCol++;
                }
                playerDirection = "up";
                break;

            case NUMPAD7:
                if (canMove(m_characterPositionRow - 1, m_characterPositionCol - 1) && (canMove(m_characterPositionRow - 1, m_characterPositionCol) || canMove(m_characterPositionRow, m_characterPositionCol - 1))) {
                    newRow--;
                    newCol--;
                }
                playerDirection = "up";
                break;
        }

        if (canMove(newRow, newCol)) {
            m_characterPositionRow = newRow;
            m_characterPositionCol = newCol;
            setChanged();
            notifyObservers();
            return true;
        }

        return false;
    }

    private boolean canMove(int row, int col)
    {
        return (m_maze.getCellValue(row,col) == 0);
    }
    

    @Override
    public void generateWithMaze(Maze mazeThatSend) {
        setMaze(mazeThatSend);
        setPosition(mazeThatSend.getStartPosition().getRowIndex(),mazeThatSend.getStartPosition().getColumnIndex());
        setChanged();
        notifyObservers();
    }
}
