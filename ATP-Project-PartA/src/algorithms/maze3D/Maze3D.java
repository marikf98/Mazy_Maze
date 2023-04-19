package algorithms.maze3D;
public class Maze3D {
    private int [][][] maze;
    private Position3D[][][] positionMatrix;

    /*the builder for the 3D maze*/
    public Maze3D(int [][][] maze)
    {
        if(maze == null)
        {
            return;
        }
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
    /*give the Postion of a index*/
    public Position3D  getPosition3D(int i, int j, int z)
    {
        if(i < 0 || i >= maze.length || j < 0 || j >= maze[0].length || z < 0 || z >= maze[0][0].length)
        {
            return null;
        }
        if(positionMatrix[i][j][z] == null)
        {
            return null;
        }
        else
        {
            return positionMatrix[i][j][z];
        }
    }

    /*get the maze fuction*/
    public int[][][] getMaze() {
        return maze;
    }

    public void setMaze(int[][][] maze) {
        if(maze == null)
        {
            return;
        }
        this.maze = maze;
    }
    /*get a matrix of positon rather ther numbers*/

    public Position3D[][][] getPositionMatrix() {
        return positionMatrix;
    }
        /*set a new matrix of postion*/
    public void setPositionMatrix(Position3D[][][] positionMatrix) {

        if(positionMatrix == null)
        {
            return;
        }
        this.positionMatrix = positionMatrix;
    }
    //get tthe start of a maze
    public Position3D getStartPosition() {return positionMatrix[0][0][0];}
    //get the eand of a maze
    public Position3D getGoalPosition() {return  positionMatrix[0][maze[0].length - 1][maze[0][0].length-1];}



    //print the maze in a way that represnt a 3d maze
    public void printMaze3D() {
        for (int z = 0; z < this.maze.length; z++) {
            System.out.println("Level " + z + " of the maze:");
            for (int x = 0; x < this.maze[0].length; x++) {
                for (int y = 0; y < this.maze[0][0].length; y++) {
                    System.out.print(this.maze[x][y][z] == 1 ? "█" : " ");
                }
                System.out.println();
            }
            System.out.println();
        }
    }

    //get the value in a curret cell

    public int getCellValue(int depth, int row,int column) {

        if(depth < 0 || depth >= this.maze.length || row < 0 || row >= this.maze[0].length || column < 0 || column >= this.maze[0][0].length)
        {
            return -1;
        }
        return this.getMaze()[depth][row][column];
    }
}
