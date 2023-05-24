package algorithms.mazeGenerators;

import java.io.Serializable;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Maze implements Serializable {
    /** this is the Maze class, it represents a maze**/
    private int [][] maze;/** the maze is represented by a 2D array of ints**/
    private Position [][] positionMatrix;/** the position matrix is a 2D array of positions that also represents a maze**/

    /**this is the constructor of the Maze class, it receives a 2D array of ints and initializes the maze and position matrix**/
    public Maze(int [][] maze)
    {
        if(maze == null){return;}
        this.maze = maze;
        this.positionMatrix = new Position[maze.length][maze[0].length];
        for(int i = 0; i < maze.length; i++)
        {
            for(int j = 0; j < maze[0].length; j++)
            {
                positionMatrix[i][j] = new Position(i,j);
            }
        }
    }

    /**This constructor gets a byte array in a format that toByteArray() method creates and creates a maze with data from the byte array data**/
    public Maze(byte[] bytes)
    {
        if(bytes == null) {return;}
        int xSize = bytes[bytes.length - 2];
        int ySize = bytes[bytes.length - 1];
        this.maze = new int[xSize][ySize];
        this.positionMatrix = new Position[xSize][ySize];
        int counter = 0;
        for(int i = 0; i < xSize; i ++)
        {
            for(int j = 0; j < ySize; j ++)
            {
                maze[i][j] = bytes[counter];
                positionMatrix[i][j] = new Position(i,j);
                counter++;
            }
        }
    }

    public  void setIn (int i, int j)
    {
        this.maze[i][j] = 2;
    }

    public void setOut(int i, int j)
    {
        this.maze[i][j] = 3;
    }

    /**this function lets us get a value from a cell in the maze where 1 is a wall and 0 is a path**/
    public int getCellValue (int i, int j)
    {
        if(i < 0 || j < 0 || i >= maze.length || j >= maze[0].length){return -1;}
        return maze[i][j];
    }
    /**this function lets us set a value to a cell in the maze where 1 is a wall and 0 is a path**/
    public void setCellValue (int i, int j, int val)
    {
        if(i < 0 || j < 0 || i >= maze.length || j >= maze[0].length){return;}
        this.maze[i][j] = val;
    }

    /**this function returns the number of rows in the maze**/
    public int getRowsLength(){return maze.length;}

    /**this function returns the number of columns in the maze**/
    public int getColumnsLength(){return maze[0].length;}

    /**this function returns the position of a cell in the maze from a given index**/
    public Position getPosition(int i, int j)
    {
        if(i < 0 || j < 0 || i >= maze.length || j >= maze[0].length){return null;}
        return positionMatrix[i][j];
    }

    /**this function prints the maze**/
    public void print() {
        System.out.print("╔");
        for (int j = 0; j < maze[0].length * 2 + 1; j++)
        {
            System.out.print("═");
        }

        System.out.println("╗");

        for (int i = 0; i < maze.length; i++)
        {
            System.out.print("║ ");

            for (int j = 0; j < maze[0].length; j++)
            {
                if (i == 0 && j == 0)
                {
                    System.out.print("S ");
                }

                else if (i == maze.length - 1 && j == maze[0].length - 1)
                {
                    System.out.print("E ");
                }

                else
                {
                    System.out.print(maze[i][j] + " ");
                }
            }

            System.out.println("║");
        }

        System.out.print("╚");

        for (int j = 0; j < maze[0].length * 2 + 1; j++)
        {
            System.out.print("═");
        }

        System.out.println("╝");
    }

    /**this function returns the start position of the maze**/
    public Position getStartPosition() {return positionMatrix[0][0];}

    /**this function returns the goal position of the maze**/
    public Position getGoalPosition() {return  positionMatrix[maze.length - 1][maze[0].length - 1];}

    /**This function converts the maze into a 1D byte array**/
    public byte[] toByteArray()
    {
        byte[] compressedMaze = new byte[maze.length*maze[0].length + 6];
        int[] flattedMaze = Stream.of(maze).flatMapToInt(IntStream::of).toArray();
        for(int i = 0; i < flattedMaze.length; i++)
        {
            compressedMaze[i] = (byte) flattedMaze[i];
        }

        compressedMaze[compressedMaze.length - 6] = 0; /**start x coordinate**/
        compressedMaze[compressedMaze.length - 5] = 0;/**start y coordinate**/
        compressedMaze[compressedMaze.length - 4] = (byte) (maze.length - 1);/**end x coordinate**/
        compressedMaze[compressedMaze.length - 3] = (byte) (maze[0].length - 1);/**end y coordinate**/
        compressedMaze[compressedMaze.length - 2] = (byte) maze.length; /**Will hold the X size of the maze**/
        compressedMaze[compressedMaze.length - 1] = (byte) maze[0].length;/**Will hold the Y size of the maze**/
        return compressedMaze;
    }

}
