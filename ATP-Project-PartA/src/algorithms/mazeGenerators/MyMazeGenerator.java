package algorithms.mazeGenerators;

import java.lang.reflect.Array;
import java.util.*;

public class MyMazeGenerator extends AMazeGenerator{
    private int originalRows;
    private int originalColumns;
    private Maze maze;
    @Override
    public Maze generate(int rows, int columns) {
        if(rows < 3 || columns < 3)
        {
            System.out.println("The size you entered is to small");
            return null;
        }

        this.originalRows = rows;
        this.originalColumns = columns;
        if(rows % 2 == 0)
        {
            rows ++;
        }
        if(columns % 2 == 0)
        {
            columns ++;
        }

        Random rand = new Random();
        int [][] grid = new int[rows][columns];
        Maze maze = new Maze(grid);
        this.maze = maze;
        maze.setCellValue(0,0,0);
        Position[][] locations = new Position[rows][columns];
        locations[0][0] = new Position(0,0);


        for(int i = 0; i < rows; i++)
        {
            for(int j = 0; j < columns; j ++)
            {

                maze.setCellValue(i,j,1);
                locations[i][j] = new Position(i,j);
            }
        }
        int random;
        int current_row = 1;
        int current_column = 1;
        maze.setCellValue(current_row,current_column,0);

        ArrayList<Position> neighbours = allNeighbours(current_row,current_column, 1);
        ArrayList<Position> tempList;

        int visited = 1;
        Position tempPos;
        int nearestRow;
        int nearestColumn;
        Position tempPosition;
        ArrayList<Position> unvisited;

        int rowsWhile = (int)Math.floor(rows / 2);
        int columnsWhile = (int)Math.floor(columns / 2);


        while(visited < rowsWhile * columnsWhile)
        {
            random = rand.nextInt(neighbours.size());
            tempPos = neighbours.get(random);
            current_row = tempPos.getRowIndex();
            current_column = tempPos.getColumnIndex();
            visited += 1;
            this.maze.setCellValue(current_row, current_column,0);
            neighbours.remove(random);
            tempList = allNeighbours(current_row, current_column,0);
            tempPosition = tempList.get(0);
            nearestRow = tempPosition.getRowIndex();
            nearestColumn = tempPosition.getColumnIndex();
            this.maze.setCellValue((int)Math.floor((current_row + nearestRow) / 2),(int)Math.floor((current_column + nearestColumn) / 2),0);
            unvisited = allNeighbours(current_row, current_column, 1);
            Set<Position> set = new HashSet<>(neighbours);
            set.addAll(unvisited);
            neighbours = new ArrayList<>(set);
            Collections.reverse(neighbours);

        }


        int [][] tempGrid = copyGrid(grid);
        maze = new Maze(tempGrid);
        maze.setCellValue(0,0,0);
        maze.setCellValue(originalRows - 1, originalColumns - 1, 0);
        if(rand.nextInt(2) + 1 == 1)
        {
            maze.setCellValue(0,1,0);
        }
        else
        {
            maze.setCellValue(1,0,0);
        }
        return maze;

    }

    private int[][] copyGrid(int [][] grid)
    {
        int [][] copyGrid = new int[originalRows][originalColumns];
        for(int i = 0; i < originalRows; i++)
        {
            for(int j = 0; j < originalColumns; j++)
            {
                copyGrid[i][j] = grid[i][j];
            }
        }
        return copyGrid;
    }

    private ArrayList<Position> allNeighbours(int row, int column, int value)
    {
        ArrayList<Position> neighbours = new ArrayList<Position>();

        if(row > 1 && (this.maze.getCellValue(row - 2, column) == value))
        {
            neighbours.add(new Position(row - 2, column));
        }

        if((row < this.maze.getRowsLength() - 2) && (this.maze.getCellValue(row + 2, column) == value))
        {
            neighbours.add(new Position(row + 2, column));
        }

        if(column > 1 && (this.maze.getCellValue(row, column - 2) == value))
        {
            neighbours.add(new Position(row, column - 2));
        }

        if((column < this.maze.getColumnsLength() - 2) && (this.maze.getCellValue(row,column + 2) == value))
        {
            neighbours.add(new Position(row, column + 2));
        }

        Collections.shuffle(neighbours);
        return neighbours;
    }

    // use the same logic as the allNeighbours function but adjust it for a 3d maze




}
