package algorithms.maze3D;

import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.Position;

public class Maze3D {
    private int [][][] maze;
    private Position3D[][][] positionMatrix;

    public Maze3D(int [][][] maze)
    {
        this.maze = maze;
        this.positionMatrix = new Position3D[maze.length][maze[0].length][maze[0][0].length];
        for(int i = 0; i < maze.length; i++)
        {
            for(int j = 0; j < maze[0].length; j++) {
                for (int z = 0; z < maze[0].length; z++) {
                    positionMatrix[i][j][z] = new Position3D(i, j,z);
                }
            }
        }
    }
    public Position3D  getPosition3D(int i, int j, int z){
        return positionMatrix[i][j][z];

    }
    public int[][][] getMaze() {
        return maze;
    }

    public void setMaze(int[][][] maze) {
        this.maze = maze;
    }

    public Position3D[][][] getPositionMatrix() {
        return positionMatrix;
    }

    public void setPositionMatrix(Position3D[][][] positionMatrix) {
        this.positionMatrix = positionMatrix;
    }
    public Position3D getStartPosition() {return positionMatrix[0][0][0];}

    public Position3D getGoalPosition() {return  positionMatrix[maze.length - 1][maze[0].length - 1][0];}

    public void printMaze3D() {
        for (int z = 0; z < this.maze[0][0].length; z++) {
            System.out.println("Level " + z + " of the maze:");
            for (int x = 0; x < this.maze.length; x++) {
                for (int y = 0; y < this.maze[x].length; y++) {
                    System.out.print(this.maze[x][y][z] == 1 ? "█" : " ");
                }
                System.out.println();
            }
            System.out.println();
        }
    }

    public int getCellValue(int row, int column,int depth) {
        return this.getMaze()[row][column][depth];
    }
}
