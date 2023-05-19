package Server;

import IO.SimpleCompressorOutputStream;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

public class ServerStrategySolveSearchProblem implements IServerStrategy {
    @Override
    public void applyStrategy(InputStream inFromClient, OutputStream outToClient) {
        try {
            ObjectInputStream fromClient = new ObjectInputStream(inFromClient);
            ObjectOutputStream toClient = new ObjectOutputStream(outToClient);

            Maze  al = (int[]) fromClient.readObject();
            MyMazeGenerator myMazeGenerator=new MyMazeGenerator();
            Maze maze =myMazeGenerator.generate(al[0],al[1]);
            SimpleCompressorOutputStream simpleCompressorOutputStream=new SimpleCompressorOutputStream(outToClient);
            simpleCompressorOutputStream.write(maze.toByteArray());
            toClient.flush();
            fromClient.close();
            toClient.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    }
}
