package algorithms.mazeGenerators;

import java.lang.reflect.Array;
import java.util.*;
/**This is the MyMazeGenerator class the extends the AMazeGenerator
 * generate(int rows, int columns) - generates the maze using prims algorithm
 * copyGrid,allNeighbours are helper function for the generate function **/
public class MyMazeGenerator extends AMazeGenerator{
    private int originalRows;
    private int originalColumns;
    private Maze maze;
    @Override
    public Maze generate(int rows, int columns) {
        if(rows < 2 || columns < 2) // the maze has to be o size of at least 2*2
        {
            System.out.println("The size you entered is to small");
            return null;
        }

        this.originalRows = rows;
        this.originalColumns = columns;
        //for the generation part the maze has to be of an odd size
        if(rows % 2 == 0)
        {
            rows ++;
        }

        if(columns % 2 == 0)
        {
            columns ++;
        }

        Random rand = new Random();
        /** we initialize a grid of the new size and set it to the maze
         * then we initialize a 2d array of positions and fill the grid with 1 **/
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
        /** then we initialize the start position and add all its neighbours to a list **/
        int random;
        int currentRow = 1;
        int currentColumn = 1;
        maze.setCellValue(currentRow,currentColumn,0);

        ArrayList<Position> neighbours = allNeighbours(currentRow,currentColumn, 1);
        ArrayList<Position> tempList;

        int visited = 1;
        Position tempPos;
        int nearestRowIndex;
        int nearestColumnIndex;
        Position tempPosition;
        ArrayList<Position> unvisited;

        int rowsWhile = (int)Math.floor(rows / 2);
        int columnsWhile = (int)Math.floor(columns / 2);

        /**we iterate using a while loop until the amount of visited nodes is bigger then the floor division of the rows thea is multiplied by the number of columns floor division by 2**/
        while(visited < rowsWhile * columnsWhile)
        {
            random = rand.nextInt(neighbours.size());
            tempPos = neighbours.get(random); // we choose a random neighbour to grow the maze to it
            currentRow = tempPos.getRowIndex();
            currentColumn = tempPos.getColumnIndex();
            visited += 1;
            this.maze.setCellValue(currentRow, currentColumn,0); // we set that location to be 0
            neighbours.remove(random); // remove it from the list of the neighbours
            tempList = allNeighbours(currentRow, currentColumn,0);
            /**then we select a random neighbour of the neighbour we found earlier and set it to be 0**/
            tempPosition = tempList.get(0);
            nearestRowIndex = tempPosition.getRowIndex();
            nearestColumnIndex = tempPosition.getColumnIndex();
            this.maze.setCellValue((int)Math.floor((currentRow + nearestRowIndex) / 2),(int)Math.floor((currentColumn + nearestColumnIndex) / 2),0);
            unvisited = allNeighbours(currentRow, currentColumn, 1); // we update  the neighbours list
            Set<Position> set = new HashSet<>(neighbours);
            set.addAll(unvisited);
            neighbours = new ArrayList<>(set);
            Collections.reverse(neighbours);
        }
        /**at the end we edit the maze back to the needed size and set the start an end position**/
        int [][] tempGrid = copyGrid(grid);
        maze = new Maze(tempGrid);
        maze.setCellValue(0,0,0);
        maze.setCellValue(originalRows - 1, originalColumns - 1, 0);

        if(rand.nextInt(2) + 1 == 1) {maze.setCellValue(0,1,0);}

        else {maze.setCellValue(1,0,0);}

        if(rand.nextInt(2) + 1 == 1) {maze.setCellValue(maze.getRowsLength() - 1,maze.getColumnsLength() - 2,0);}

        else {maze.setCellValue(maze.getRowsLength() - 2,maze.getColumnsLength() - 1,0);}

        return maze;

    }
    /**this is a helper function for copying the grid**/
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

    /**
     * @param row row index
     * @param column column index
     * @param value the value we want to be compared to 1 -wall 0 a passage
     * @return all the neighbours of the given position that has the same value as the given value
     */
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

        Collections.shuffle(neighbours);// we shuffle the list to increase the randomness of the generation
        return neighbours;
    }




}
