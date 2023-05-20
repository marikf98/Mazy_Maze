package Server;

import IO.MyCompressorOutputStream;
import IO.SimpleCompressorOutputStream;
import algorithms.mazeGenerators.*;

import java.io.*;
import java.util.ArrayList;



//implements the IServerStrategy interface,
// indicating that it defines the specific behavior .
public class ServerStrategyGenerateMaze implements IServerStrategy {


    @Override
    public void applyStrategy(InputStream inFromClient, OutputStream outToClient) {

        try {
            ObjectInputStream fromClient = new ObjectInputStream(inFromClient);
            ObjectOutputStream toClient = new ObjectOutputStream(outToClient);

            String generator = Configurations.getInstance().getMazeGeneratingAlgorithm();
            AMazeGenerator mazeGen = null;
            if(generator.equals("MyMazeGenerator"))
            {
                mazeGen = new MyMazeGenerator();
            }
            else if (generator.equals("SimpleMazeGenerator"))
            {
                mazeGen = new SimpleMazeGenerator();
            }
            else
            {
                mazeGen = new EmptyMazeGenerator();
            }

            int[] input = (int[]) fromClient.readObject();

            //this size of the maze is the one that been pass
            Maze maze = mazeGen.generate(input[0],input[1]);
            ByteArrayOutputStream outByteArray = new ByteArrayOutputStream();
            MyCompressorOutputStream myCompressorOutputStream = new MyCompressorOutputStream(outByteArray);
            myCompressorOutputStream.write(maze.toByteArray());
            toClient.flush();
            //close the connection

            toClient.writeObject(outByteArray.toByteArray());
            toClient.flush();
            // Flush the output stream

            // Close the connection
            fromClient.close();
            toClient.close();

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
