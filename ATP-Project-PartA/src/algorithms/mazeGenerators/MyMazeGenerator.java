package algorithms.mazeGenerators;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class MyMazeGenerator extends AMazeGenerator{
    @Override
    public Maze generate(int rows, int columns) {
        int [][] grid = new int[rows][columns];
        Maze maze = new Maze(grid);
//        /** At first the IN cell will be 0,0 if needed I will change
//         * for it to be selected at random**/
//        maze.setCellValue(0,0,2);
//        int remainingCells = rows * columns - 1; // minus one because we choose a starting position
//        while (remainingCells > 0)
//        {
//            walk(maze);
//
//            remainingCells --;
//        }
        for(int i = 0; i < rows; i ++ )
        {
            for (int j = 0; j < columns; j++)
            {
                maze.setCellValue(i,j,1);
            }
        }

        for(int i = 0; i < rows; i ++ )
        {
            for(int j =0; j <columns; j++)
            {
                List<> dirs = new ArrayList<>();
                int direction;

                if(i == 0 && j == 0)
                {
                    if (Math.random() < 0.5)
                    {
                        direction = 2;
                    } else
                    {
                        direction = 4;
                    }
                }
            }
        }

        return null;
    }

    public void walk(Maze maze)
    {}
}
