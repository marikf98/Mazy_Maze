package algorithms.mazeGenerators;

import java.io.Serializable;

public class EmptyMazeGenerator extends AMazeGenerator implements Serializable
{
    /**this class generates an empty maze - it is filled with 0*/
    @Override
    public Maze generate(int rows, int columns) {
        if(rows < 0 || columns < 0)
        {
            System.out.println("The size you entered is to small");
            return null;
        }
        int [][] emptyMaze = new int[rows][columns];
        for(int i = 0; i <rows; i++)
        {
           for(int j = 0; j < columns; j++)
           {
               emptyMaze[i][j] = 0;
           }
        }
        return new Maze(emptyMaze);
    }
}
