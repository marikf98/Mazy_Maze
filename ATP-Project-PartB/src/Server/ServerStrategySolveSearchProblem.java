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

    /**This function creates a search algorithm base on the configuration file and then solves the maze using that searcher**/
    @Override
    public void applyStrategy(InputStream inFromClient, OutputStream outToClient)
    {
        try {
            ObjectInputStream fromClient = new ObjectInputStream(inFromClient);
            ObjectOutputStream toClient = new ObjectOutputStream(outToClient);

            Maze maze = (Maze) fromClient.readObject(); // this is the maze that we need to solve
            Solution solution = getSolution(maze.toByteArray());// here we get what searcher we need to create
            if (solution == null)
            {
                SearchableMaze searchableMaze = new SearchableMaze(maze);
                String generator = Configurations.getInstance().getMazeSearchingAlgorithm();
                ASearchingAlgorithm searcher = null;
                if (generator.equals("BestFirstSearch"))
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
                //the solution to the maze
                solution = searcher.solve(searchableMaze);
                saveSolution(maze.toByteArray(), solution);
        }
            toClient.writeObject(solution);
            toClient.flush();
            fromClient.close();
            toClient.close();
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
    /**This function saves the solution in a new file
     * each solution file is linked to a maze in the mazes file**/
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
            System.out.println(e.getMessage());
        }
    }

    /**This function looks for a solution in the files,
     * if it finds a match by the maze array it returns its solution, otherwise it returns null**/
    private Solution getSolution(byte[] maze){
        AtomicInteger counter = new AtomicInteger(0); // we use atomic integer here so it will support multi-threads
        try
        {
            FileInputStream file = new FileInputStream(tempDirectoryPath + "/Mazes");
            Scanner scanner = new Scanner(file);
            while(scanner.hasNextLine())
            {
                if(Arrays.toString(maze).equals(scanner.nextLine()))
                {
                    FileInputStream foundFile = new FileInputStream(tempDirectoryPath + "/Solutions" + counter.get());
                    ObjectInputStream reader = new ObjectInputStream(foundFile);
                    Solution solution = (Solution) reader.readObject();
                    scanner.close();
                    reader.close();
                    foundFile.close();
                    return solution;
                }
                counter.getAndAdd(1);
            }
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
        return null;
    }

}
