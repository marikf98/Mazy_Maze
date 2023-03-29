package algorithms.mazeGenerators;

public class Maze {
    /** Maze indexing :
     * 0 - pass
     * 1 - wall
     * 2 - Starting point (IN)
     * 3 - exit point (OUT)**/
    private int [][] maze;

    public Maze(int [][] maze)
    {
        this.maze = maze;
    }

    public  void setIn (int i, int j)
    {
        this.maze[i][j] = 2;
    }

    public void setOut(int i, int j)
    {
        this.maze[i][j] = 3;
    }

    public int getCellValue (int i, int j)
    {
        return maze[i][j];
    }

    public void setCellValue (int i, int j, int val)
    {
        this.maze[i][j] = val;
    }
}
