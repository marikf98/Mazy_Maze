package Server;

import IO.SimpleCompressorOutputStream;
import algorithms.mazeGenerators.*;
import algorithms.search.*;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class ServerStrategySolveSearchProblem implements IServerStrategy {

    private final String tempDirectoryPath = System.getProperty("java.io.tmpdir");
    private volatile AtomicInteger solutionCounter = new AtomicInteger(0);
    private File mazes = new File(tempDirectoryPath + "/Mazes");

    @Override
    public void applyStrategy(InputStream inFromClient, OutputStream outToClient)
    {
        try {
            ObjectInputStream fromClient = new ObjectInputStream(inFromClient);
            ObjectOutputStream toClient = new ObjectOutputStream(outToClient);

            Maze maze = (Maze) fromClient.readObject();
            SearchableMaze searchableMaze = new SearchableMaze(maze);

            String generator = Configurations.getInstance().getMazeSearchingAlgorithm();
            ASearchingAlgorithm searcher = null;
            if(generator.equals("BestFirstSearch"))
            {
                searcher = new BestFirstSearch();
            }
            else if (generator.equals("BreadthFirstSearch"))
            {
                searcher = new BreadthFirstSearch();
            }
            else
            {
                searcher = new DepthFirstSearch();
            }

            //the soultion to the maze
            Solution solution = searcher.solve(searchableMaze);
            ByteArrayOutputStream byteArray = new ByteArrayOutputStream();

            toClient.writeObject(solution);
            toClient.flush();
            fromClient.close();
            toClient.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void saveSolution(byte[] maze, Solution solution)
    {
        try {
            /**first we write the maze in a byte array form to the mazes files**/
            FileOutputStream  writer = new FileOutputStream (tempDirectoryPath + "/Mazes");
            writer.write(maze);
            writer.close();
            /**now we will write the solution to a new file**/
            File newSol = new File(tempDirectoryPath + "/Solutions" + solutionCounter.get()); // create a new file for the solution
            writer = new FileOutputStream(newSol);
            ObjectOutputStream objectWriter = new ObjectOutputStream(writer);
            objectWriter.writeObject(solution);
            objectWriter.close();
            writer.close();
            solutionCounter.getAndAdd(1);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    /**This function looks for a solution in the files,
     * if it finds a match by the maze array it returns its solution, otherwise it returns null**/
    private Solution getSolution(byte[] maze){
        AtomicInteger counter = new AtomicInteger(0); // we use atomic integer here so it will support multithreads
        try
        {
            FileInputStream file = new FileInputStream(tempDirectoryPath + "/Mazes");
            Scanner scanner = new Scanner(file);
            while(scanner.hasNextLine())
            {
                if(Arrays.toString(maze).equals(scanner.nextLine()))
                {
                    FileInputStream reader = new FileInputStream()
                }
                counter++;
            }
        }
        catch(FileNotFoundException e)
        {
            e.printStackTrace();
        }
        return null;
    }

}
