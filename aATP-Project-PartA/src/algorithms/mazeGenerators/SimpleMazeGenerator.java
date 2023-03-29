package algorithms.mazeGenerators;

import java.util.Random;

public class SimpleMazeGenerator extends AMazeGenerator{
    /** check if thats what needed maybe its not supposed to be complitly random **/
    public Maze generate(int rows, int columns) {
        int [][] emptyMaze = new int[rows][columns];
        Random random = new Random();
        for(int i = 0; i <rows; i++)
        {
            for(int j = 0; j < columns; j++)
            {
                emptyMaze[i][j] = random.nextInt(2) + 1;
            }
        }
        return new Maze(emptyMaze);
    }
}
