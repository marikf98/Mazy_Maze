package algorithms.maze3D;

import algorithms.mazeGenerators.Maze;
import algorithms.search.AState;
import algorithms.search.ISearchable;
import algorithms.search.MazeState;

import java.util.ArrayList;



import algorithms.mazeGenerators.Maze;

import java.util.ArrayList;

public class SearchableMaze3D implements ISearchable {
        private Maze3D maze;
        private boolean solved = false;
        private Maze3DState[][][] visitedMatrix;
        private int [][][] flagMatrix;


        public SearchableMaze3D(Maze3D maze) {
            this.maze = maze;
            this.solved = false;
            visitedMatrix = new Maze3DState[maze.getMaze().length][maze.getMaze()[0].length][maze.getMaze()[0][0].length];
            flagMatrix = new int [maze.getMaze().length][maze.getMaze()[0].length][maze.getMaze()[0][0].length];
            for(int i = 0; i < maze.getMaze().length; i++)
            {
                for(int j = 0; j < maze.getMaze()[0].length; j++)
                {
                    for(int z=0;z<maze.getMaze()[0][0].length;z++) {
                        visitedMatrix[i][j][z] = new Maze3DState(maze.getPosition3D(i, j, z));
                        flagMatrix[i][j][z] = 0;
                    }
                }
            }
            flagMatrix[0][0][0] = 1;
        }

        public int getRowsLength() {return this.maze.getMaze().length;}
        public int getColumnsLength(){return this.maze.getMaze()[0].length;}
        public int getDepthLength(){return this.maze.getMaze()[0][0].length;}
        public void setUnvisited()
        {
            for(int i = 0; i < maze.getMaze().length; i++)
            {
                for(int j = 0; j < maze.getMaze()[0].length; j++) {
                    for (int z = 0; z < maze.getMaze()[0][0].length; z++) {
                        if (i == 0 && j == 0 && z==0) {
                            visitedMatrix[i][j][z].setUnVisited();
                            visitedMatrix[i][j][z].setPrev(null);
                            continue;
                        }
                        visitedMatrix[i][j][z].setUnVisited();
                        visitedMatrix[i][j][z].setPrev(null);
                        flagMatrix[i][j][z] = 0;

                    }
                }
            }
        }
        @Override
        public ArrayList<AState> getAllPossibleStates(Maze3DState mState)
        {
            ArrayList<AState> possibleMoves = new ArrayList<>();
            int row = mState.getRow();
            int column = mState.getColumn();
            int depth =mState.getDepth();
            int counter = 0;

            try
            {
                /** down **/
                if(maze.getCellValue(row+1,column,depth) == 0 && flagMatrix[row+1][column][depth] == 0)
                {
                    possibleMoves.add(visitedMatrix[row+1][column][depth]);
                    flagMatrix[row+1][column][depth] = 1;

                }
            }
            catch (ArrayIndexOutOfBoundsException ignored)
            {}

            try
            {
                /** up **/
                if(maze.getCellValue(row-1,column,depth) == 0 && flagMatrix[row-1][column][depth] == 0)

                {
                    possibleMoves.add(visitedMatrix[row-1][column][depth]);
                    flagMatrix[row-1][column][depth] = 1;
                }
            }
            catch (ArrayIndexOutOfBoundsException ignored)
            {}

            try
            {
                /** left **/
                if(maze.getCellValue(row,column-1,depth) == 0 && flagMatrix[row][column-1][depth] == 0)

                {
                    possibleMoves.add(visitedMatrix[row][column-1][depth]);
                    flagMatrix[row][column-1][depth] = 1;
                }
            }
            catch (ArrayIndexOutOfBoundsException ignored)
            {}

            try
            {
                /** right **/
                if(maze.getCellValue(row,column+1,depth) == 0 && flagMatrix[row][column+1][depth] == 0)
                {
                    possibleMoves.add(visitedMatrix[row][column+1][depth]);
                    flagMatrix[row][column+1][depth] = 1;

                }

            }
            catch (ArrayIndexOutOfBoundsException ignored)
            {}
            try
            {
                /** up **/
                if (maze.getCellValue(row, column + 1, depth) == 0 && flagMatrix[row][column][depth + 1] == 0) {
                    possibleMoves.add(visitedMatrix[row][column][depth + 1]);
                    flagMatrix[row][column][depth + 1] = 1;

                }
            }
            catch (ArrayIndexOutOfBoundsException ignored)
            {}
            try
            {
                /** down **/
                if (maze.getCellValue(row, column + 1, depth) == 0 && flagMatrix[row][column][depth - 1] == 0) {
                    possibleMoves.add(visitedMatrix[row][column][depth - 1]);
                    flagMatrix[row][column][depth - 1] = 1;

                }
            }
            catch (ArrayIndexOutOfBoundsException ignored)
            {}




            return possibleMoves;
        }

        public void setSolved(boolean solved) {
            this.solved = solved;
        }

        public boolean isSolved() {
            return solved;
        }

        @Override
        public AState getStart() {return visitedMatrix[0][0][0];}

        @Override
        public AState getGoal() {
            return visitedMatrix[maze.getMaze().length-1][maze.getMaze()[0].length][0];
        }


    }



