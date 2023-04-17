package algorithms.maze3D;

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

    public Position3D getGoalPosition() {return  positionMatrix[maze.length - 1][maze[0].length - 1][maze[0][0].length - 1];}
}
