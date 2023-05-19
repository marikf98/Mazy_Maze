package Server;

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

            ArrayList<Integer> al = (ArrayList<Integer>) fromClient.readObject();
            MyMazeGenerator myMazeGenerator=new MyMazeGenerator();
            Maze maze =myMazeGenerator.generate(al.get(0),al.get(1));










            toClient.writeObject(al);
            toClient.flush();

            fromClient.close();
            toClient.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
