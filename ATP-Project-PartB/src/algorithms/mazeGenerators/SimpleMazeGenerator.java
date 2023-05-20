package algorithms.mazeGenerators;

import java.io.Serializable;
import java.util.Random;

public class SimpleMazeGenerator extends AMazeGenerator implements Serializable
{
    /** this generator creates a simple maze with a random number of passages and a random amount of walls**/

    /**the generate function receives the size of the maze and creates a random maze **/
    public Maze generate(int rows, int columns) {
        if(rows < 2 || columns < 2)
        {
            System.out.println("The size you entered is to small");
            return null;
        }
        int [][] grid = new int[rows][columns];
        Random rand = new Random();
        int numOfPassages;
        int counter = 0;
        int index;
        /**at first we initialize each even index row to 0 and the odd index row to 1**/
        Maze maze = new Maze(grid);
        for(int i = 0; i <rows; i++)
        {
            for(int j = 0; j < columns; j++)
            {
                if(i % 2 == 0)
                {
                    maze.setCellValue(i,j,0);
                }
                else
                {
                    maze.setCellValue(i,j,1);
                }
            }
        }
        /**now we create a random number of passages and walls for each odd row**/
        for(int i = 1; i <rows; i+=2)
        {
            numOfPassages = rand.nextInt(columns - 1) + 1;
            while(counter < numOfPassages)
            {
                index = rand.nextInt(numOfPassages);
                if(maze.getCellValue(i,index) == 1)
                {
                    maze.setCellValue(i,index, 0);
                    counter ++;
                }
            }
            counter = 0;
        }
        maze.setCellValue(rows-1,columns-1,0);
        return maze;
    }
}
