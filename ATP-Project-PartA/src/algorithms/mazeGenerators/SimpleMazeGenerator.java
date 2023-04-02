package algorithms.mazeGenerators;

import java.util.Random;

public class SimpleMazeGenerator extends AMazeGenerator{
    /** check if thats what needed maybe its not supposed to be complitly random **/
    public Maze generate(int rows, int columns) {
        int [][] grid = new int[rows][columns];
        Random rand = new Random();
        int numOfPassages;
        int counter = 0;
        int index;
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
