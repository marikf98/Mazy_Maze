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

        public int getRowsLength() {return this.maze.getMaze()[0].length;}
        public int getColumnsLength(){return this.maze.getMaze()[0][0].length;}
        public int getDepthLength(){return this.maze.getMaze().length;}
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
        public ArrayList<AState> getAllPossibleStates(AState state)
        {
            Maze3DState mState = (Maze3DState) state;
            ArrayList<AState> possibleMoves = new ArrayList<>();
            int row = mState.getRow();
            int column = mState.getColumn();
            int depth =mState.getDepth();
            int counter = 0;

            try
            {
                /** down **/
                if(maze.getCellValue(depth,row+1,column) == 0 && flagMatrix[depth][row+1][column] == 0)
                {
                    possibleMoves.add(visitedMatrix[depth][row+1][column]);
                    flagMatrix[depth][row+1][column] = 1;

                }
            }
            catch (ArrayIndexOutOfBoundsException ignored)
            {}

            try
            {
                /** up **/
                if(maze.getCellValue(depth,row-1,column) == 0 && flagMatrix[depth][row-1][column] == 0)
                {
                    possibleMoves.add(visitedMatrix[depth][row-1][column]);
                    flagMatrix[depth][row-1][column] = 1;

                }
            }
            catch (ArrayIndexOutOfBoundsException ignored)
            {}

            try
            {
                /** left **/
                if(maze.getCellValue(depth,row,column-1) == 0 && flagMatrix[depth][row][column-1] == 0)
                {
                    possibleMoves.add(visitedMatrix[depth][row][column-1]);
                    flagMatrix[depth][row][column-1] = 1;

                }
            }
            catch (ArrayIndexOutOfBoundsException ignored)
            {}

            try
            {
                /** right **/
                if(maze.getCellValue(depth,row,column+1) == 0 && flagMatrix[depth][row][column+1] == 0)
                {
                    possibleMoves.add(visitedMatrix[depth][row][column+1]);
                    flagMatrix[depth][row][column+1] = 1;

                }

            }
            catch (ArrayIndexOutOfBoundsException ignored)
            {}
            try
            {
                /** out **/
                if (maze.getCellValue(depth-1, row, column-1) == 0 && flagMatrix[depth-1][row][column] == 0) {
                    possibleMoves.add(visitedMatrix[depth-1][row][column]);
                    flagMatrix[depth-1][row][column] = 1;

                }
            }
            catch (ArrayIndexOutOfBoundsException ignored)
            {}
            try
            {
                /** in **/
                if (maze.getCellValue(depth+1, row , column) == 0 && flagMatrix[depth+1][row][column] == 0) {
                    possibleMoves.add(visitedMatrix[depth+1][row][column]);
                    flagMatrix[depth+1][row][column] = 1;

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
            return visitedMatrix[0][maze.getMaze()[0].length-1][maze.getMaze()[0][0].length-1];
        }


    }



