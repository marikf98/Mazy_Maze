package Server;

import IO.MyCompressorOutputStream;
import IO.SimpleCompressorOutputStream;
import algorithms.mazeGenerators.*;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;



//mplements the IServerStrategy interface,
// indicating that it defines the specific behavior .
public class ServerStrategyGenerateMaze implements IServerStrategy {


    @Override
    public void applyStrategy(InputStream inFromClient, OutputStream outToClient) {
        try {
            ObjectInputStream fromClient = new ObjectInputStream(inFromClient);
            ObjectOutputStream toClient = new ObjectOutputStream(outToClient);

            int[]  al = (int[]) fromClient.readObject();
            MyMazeGenerator myMazeGenerator=new MyMazeGenerator();
            //this size of the maze is the one that been pass
            Maze maze =myMazeGenerator.generate(al[0],al[1]);
            MyCompressorOutputStream simpleCompressorOutputStream=new MyCompressorOutputStream(outToClient);
            simpleCompressorOutputStream.write(maze.toByteArray());
            toClient.flush();
            //closse the connection
            fromClient.close();
            toClient.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
