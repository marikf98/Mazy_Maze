package algorithms.mazeGenerators;

import java.awt.*;
import java.util.Arrays;

public class Maze {
    /** Maze indexing :
     * 0 - pass
     * 1 - wall
     * 2 - Starting point (IN)
     * 3 - exit point (OUT)**/
    private int [][] maze;

    public Maze(int [][] maze)
    {
        this.maze = maze;
    }

    public  void setIn (int i, int j)
    {
        this.maze[i][j] = 2;
    }

    public void setOut(int i, int j)
    {
        this.maze[i][j] = 3;
    }

    public int getCellValue (int i, int j)
    {
        return maze[i][j];
    }

    public void setCellValue (int i, int j, int val)
    {
        this.maze[i][j] = val;
    }
    public int getRowsLength(){return maze.length;}

    public int getColumnsLength(){return maze[0].length;}

    public void display() {
        int width = maze[0].length;
        int height = maze.length;
        for(int i = 0; i < height; i++)
        {
            for(int j = 0; j < width; j++)
            {
                System.out.println(maze[i][j]);
            }
            System.out.println();
        }

    }


    public void printMaze() {
        for (int[] row : maze) {
            for (int cell : row) {
                if (cell == 0) {
                    System.out.print(" ");
                } else {
                    System.out.print("X");
                }
            }
            System.out.println();
        }
    }
}
