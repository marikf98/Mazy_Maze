package algorithms.search;

import algorithms.mazeGenerators.Maze;

import java.util.ArrayList;

public class SearchableMaze implements ISearchable{
    private Maze maze;
    private boolean solved = false;
    private MazeState[][] visitedMatrix;
    private int [][] flagMatrix;


    public SearchableMaze(Maze maze) {
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


    public ArrayList<AState> getAllPossibleStates(MazeState mState)
    {
        ArrayList<AState> possibleMoves = new ArrayList<>();
        int row = mState.getRow();
        int column = mState.getColumn();
        int counter = 0;

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
        counter =0;

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
        counter =0;

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
