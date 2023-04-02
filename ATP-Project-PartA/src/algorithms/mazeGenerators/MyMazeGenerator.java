package algorithms.mazeGenerators;

import java.util.*;

public class MyMazeGenerator extends AMazeGenerator{
    @Override
    public Maze generate(int rows, int columns) {
        Random rand = new Random();
        int [][] grid = new int[rows][columns];
        Maze maze = new Maze(grid);
        maze.setCellValue(0,0,0);
        Tuple[][] locations = new Tuple[rows][columns];
        locations[0][0] = new Tuple(0,0);
        for(int i = 0; i < rows; i++)
        {
            for(int j = 0; j < columns; j ++)
            {
                if(i == 0 && j == 0)
                {
                    continue;
                }
                maze.setCellValue(i,j,1);
                locations[i][j] = new Tuple(i,j);
            }
        }
        int random;
        for(int i = 0; i < rows; i++)
        {
            for(int j = 0; j < columns; j++)
            {
                if(checkNeighbours(i,j,maze))
                {
                    if(!((i-2) < 0 || (j-2) < 0))
                    {
                        random = rand.nextInt(2);
                        if (random == 0)
                        {
                            maze.setCellValue(locations[i - 1][j].first, locations[i - 1][j].second, 0);
                        }
                        else
                        {
                            maze.setCellValue(locations[i][j - 1].first, locations[i][j - 1].second, 0);
                        }

                        maze.setCellValue(locations[i][j].first, locations[i][j].second, 0);
                    }


                    if((j-2<0) && !(i - 2 < 0))
                    {
                        maze.setCellValue(locations[i-1][j].first,locations[i-1][j].second,0);
                        maze.setCellValue(locations[i][j].first, locations[i][j].second, 0);
                    }
                    if(!((j - 2 < 0) || (i - 2 < 0)))
//                    if((j - 2 < 0) && !(i - 2 < 0))
                    {
                        maze.setCellValue(locations[i][j-1].first,locations[i][j-1].second,0);
                        maze.setCellValue(locations[i][j].first, locations[i][j].second, 0);
                    }
                }
            }
        }

        if(rows % 2 == 0 && columns % 2 == 0)
        {
            random = rand.nextInt(2);
            if(random == 0)
            {
                maze.setCellValue(rows-2,columns-1,0);
            }
            if(random == 1)
            {
                maze.setCellValue(rows-1,columns-2,0);
            }
            maze.setCellValue(rows-1,columns-1,0);
        }

        maze.setCellValue(rows - 1,columns - 1,0);
        return maze;
    }

    public Boolean checkNeighbours(int i, int j, Maze maze)
    {
        try
        {if(maze.getCellValue(i-1,j-1) == 0) {return false;}}
        catch (ArrayIndexOutOfBoundsException e)
        {}

        try
        {if(maze.getCellValue(i-1,j) == 0) {return false;}}
        catch (ArrayIndexOutOfBoundsException e)
        {}

        try
        {if(maze.getCellValue(i-1,j+1) == 0) {return false;}}
        catch (ArrayIndexOutOfBoundsException e)
        {}

        try
        {if(maze.getCellValue(i,j-1) == 0) {return false;}}
        catch (ArrayIndexOutOfBoundsException e)
        {}

        try
        {if(maze.getCellValue(i,j+1) == 0) {return false;}}
        catch (ArrayIndexOutOfBoundsException e)
        {}

        try
        {if(maze.getCellValue(i+1,j-1) == 0) {return false;}}
        catch (ArrayIndexOutOfBoundsException e)
        {}

        try
        {if(maze.getCellValue(i+1,j) == 0) {return false;}}
        catch (ArrayIndexOutOfBoundsException e)
        {}

        try
        {if(maze.getCellValue(i+1,j+1) == 0) {return false;}}
        catch (ArrayIndexOutOfBoundsException e)
        {}

        return true;


    }

}

