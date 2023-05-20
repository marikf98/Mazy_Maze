package algorithms.search;

import algorithms.mazeGenerators.Maze;

import java.io.Serializable;
import java.util.ArrayList;
/**this is an adapter class for the maze, it receives a maze instance and creates a searchable maze instance,
 * the class implements the ISearchable interface
 * maze - the maze that is adapted
 * solved - hold if the maze been solved
 * visitedMatrix - holds all the location that where already discovered
 * flagMatrix - a helper matrix for marking discovered nodes
 * SearchableMaze(Maze maze) - the constructor of this class , it receives a maze to adapt and initializes all the filed
 * setUnvisited() - this function resets all the maze after it has been searched and initializes it back to its original state
 * getAllPossibleStates(AState state) - this function receives a state of the maze and returns a list of all the neighbours that can be reached from this state
 * all the other function are setter and getter functions**/
public class SearchableMaze implements ISearchable, Serializable {
    private Maze maze;
    private boolean solved = false;
    private MazeState[][] visitedMatrix;
    private int [][] flagMatrix;


    public SearchableMaze(Maze maze)
    {
        if(maze == null){return;}
        this.maze = maze;
        this.solved = false;
        visitedMatrix = new MazeState[maze.getRowsLength()][maze.getColumnsLength()];
        flagMatrix = new int [maze.getRowsLength()][maze.getColumnsLength()];
        for(int i = 0; i < maze.getRowsLength(); i++)
        {
            for(int j = 0; j < maze.getColumnsLength(); j++)
            {
                visitedMatrix[i][j] = new MazeState(maze.getPosition(i,j));
                flagMatrix[i][j] = 0;
            }
        }
        flagMatrix[0][0] = 1;
    }

    public int getRowsLength() {return this.maze.getRowsLength();}
    public int getColumnsLength(){return this.maze.getColumnsLength();}
    public void setUnvisited()
    {
        for(int i = 0; i < maze.getRowsLength(); i++)
        {
            for(int j = 0; j < maze.getColumnsLength(); j++)
            {
                if(i == 0 && j == 0)
                {
                    visitedMatrix[i][j].setUnVisited();
                    visitedMatrix[i][j].setPrev(null);
                    continue;
                }
                visitedMatrix[i][j].setUnVisited();
                visitedMatrix[i][j].setPrev(null);
                flagMatrix[i][j] = 0;

            }
        }
    }


    public ArrayList<AState> getAllPossibleStates(AState state)
    {
        if(state == null){return null;}
        MazeState mState = (MazeState) state;
        ArrayList<AState> possibleMoves = new ArrayList<>();
        int row = mState.getRow();
        int column = mState.getColumn();
        int counter = 0;


        try
        {
            /** up **/
            if(maze.getCellValue(row-1,column) == 0 && flagMatrix[row-1][column] == 0)

            {
                possibleMoves.add(visitedMatrix[row-1][column]);
                flagMatrix[row-1][column] = 1;
            }
        }
        catch (ArrayIndexOutOfBoundsException ignored)
        {}

        /** right & up **/
        try
        {
            /** go right and up **/
            if(maze.getCellValue(row,column+1) == 0 && maze.getCellValue(row-1,column+1) == 0 && flagMatrix[row-1][column+1] == 0)

            {
                possibleMoves.add(visitedMatrix[row-1][column+1]);
                flagMatrix[row-1][column+1] = 1;
                visitedMatrix[row-1][column+1].setDiagonalMove();
                counter ++;
            }
        }
        catch (ArrayIndexOutOfBoundsException ignored)
        {}

        try
        {
            /** go up and right **/
            if(maze.getCellValue(row-1,column) == 0 && maze.getCellValue(row-1,column+1) == 0 && counter == 0 && flagMatrix[row-1][column+1] == 0)
            {
                possibleMoves.add(visitedMatrix[row-1][column+1]);
                flagMatrix[row-1][column+1] = 1;
                visitedMatrix[row-1][column+1].setDiagonalMove();
            }
        }
        catch (ArrayIndexOutOfBoundsException ignored)
        {}
        counter = 0;


        try
        {
            /** right **/
            if(maze.getCellValue(row,column+1) == 0 && flagMatrix[row][column+1] == 0)
            {
                possibleMoves.add(visitedMatrix[row][column+1]);
                flagMatrix[row][column+1] = 1;

            }
        }
        catch (ArrayIndexOutOfBoundsException ignored)
        {}

        /** right & down **/
        try
        {
            /** go right and down **/
            if(maze.getCellValue(row,column+1) == 0 && maze.getCellValue(row+1,column+1) == 0 && flagMatrix[row+1][column+1] == 0)
            {
                possibleMoves.add(visitedMatrix[row+1][column+1]);
                flagMatrix[row+1][column+1] = 1;
                visitedMatrix[row+1][column+1].setDiagonalMove();
                counter ++;
            }
        }
        catch (ArrayIndexOutOfBoundsException ignored)
        {}

        try
        {
            /** go down and right **/
            if(maze.getCellValue(row+1,column) == 0 && maze.getCellValue(row+1,column+1) == 0 && counter == 0 && flagMatrix[row+1][column+1] == 0)
            {
                possibleMoves.add(visitedMatrix[row+1][column+1]);
                flagMatrix[row+1][column+1] = 1;
                visitedMatrix[row+1][column+1].setDiagonalMove();
            }
        }
        catch (ArrayIndexOutOfBoundsException ignored)
        {}
        counter = 0;

        try
        {
            /** down **/
            if(maze.getCellValue(row+1,column) == 0 && flagMatrix[row+1][column] == 0)
            {
                possibleMoves.add(visitedMatrix[row+1][column]);
                flagMatrix[row+1][column] = 1;

            }
        }
        catch (ArrayIndexOutOfBoundsException ignored)
        {}

        /** left & down **/
        try
        {
            /** go left and down **/
            if(maze.getCellValue(row,column-1) == 0 && maze.getCellValue(row+1,column-1) == 0 && flagMatrix[row+1][column-1] == 0)
            {
                possibleMoves.add(visitedMatrix[row+1][column-1]);
                flagMatrix[row+1][column-1] = 1;
                visitedMatrix[row+1][column-1].setDiagonalMove();
                counter ++;
            }
        }
        catch (ArrayIndexOutOfBoundsException ignored)
        {}

        try
        {
            /** go down and left **/
            if(maze.getCellValue(row+1,column) == 0 && maze.getCellValue(row+1,column-1) == 0 && counter == 0 && flagMatrix[row+1][column-1] == 0)
            {
                possibleMoves.add(visitedMatrix[row+1][column-1]);
                flagMatrix[row+1][column-1] = 1;
                visitedMatrix[row+1][column-1].setDiagonalMove();
            }
        }
        catch (ArrayIndexOutOfBoundsException ignored)
        {}
        counter =0;



        try
        {
            /** left **/
            if(maze.getCellValue(row,column-1) == 0 && flagMatrix[row][column-1] == 0)

                {
                possibleMoves.add(visitedMatrix[row][column-1]);
                flagMatrix[row][column-1] = 1;
            }
        }
        catch (ArrayIndexOutOfBoundsException ignored)
        {}




        /** left & up **/
        try
        {
            /** go left and up **/
            if(maze.getCellValue(row,column-1) == 0 && maze.getCellValue(row-1,column-1) == 0 && flagMatrix[row-1][column-1] == 0)

            {
                possibleMoves.add(visitedMatrix[row-1][column-1]);
                counter ++;
                flagMatrix[row-1][column-1] = 1;
                visitedMatrix[row-1][column-1].setDiagonalMove();
            }
        }
        catch (ArrayIndexOutOfBoundsException ignored)
        {}


        try
        {
            /** go up and left **/
            if(maze.getCellValue(row-1,column) == 0 && maze.getCellValue(row-1,column-1) == 0 &&  counter == 0 && flagMatrix[row-1][column-1] == 0)
            {
                possibleMoves.add(visitedMatrix[row-1][column-1]);
                flagMatrix[row-1][column-1] = 1;
                visitedMatrix[row-1][column-1].setDiagonalMove();
            }
        }
        catch (ArrayIndexOutOfBoundsException ignored)
        {}
        counter = 0;

        return possibleMoves;
    }

    public void setSolved(boolean solved) {
        this.solved = solved;
    }

    public boolean isSolved() {
        return solved;
    }

    @Override
    public AState getStart() {return visitedMatrix[0][0];}

    @Override
    public AState getGoal() {
        return visitedMatrix[maze.getRowsLength()-1][maze.getColumnsLength()-1];
    }
}
