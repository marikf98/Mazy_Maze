package algorithms.mazeGenerators;

public class Maze {
    /** this is the Maze class, it represents a maze**/
    private int [][] maze;/** the maze is represented by a 2D array of ints**/
    private Position [][] positionMatrix;/** the position matrix is a 2D array of positions that also represents a maze**/

    /**this is the constructor of the Maze class, it receives a 2D array of ints and initializes the maze and position matrix**/
    public Maze(int [][] maze)
    {
        if(maze == null){return;}
        this.maze = maze;
        this.positionMatrix = new Position[maze.length][maze[0].length];
        for(int i = 0; i < maze.length; i++)
        {
            for(int j = 0; j < maze[0].length; j++)
            {
                positionMatrix[i][j] = new Position(i,j);
            }
        }
    }

    public  void setIn (int i, int j)
    {
        this.maze[i][j] = 2;
    }

    public void setOut(int i, int j)
    {
        this.maze[i][j] = 3;
    }

    /**this function lets us get a value from a cell in the maze where 1 is a wall and 0 is a path**/
    public int getCellValue (int i, int j)
    {
        if(i < 0 || j < 0 || i >= maze.length || j >= maze[0].length){return -1;}
        return maze[i][j];
    }
    /**this function lets us set a value to a cell in the maze where 1 is a wall and 0 is a path**/
    public void setCellValue (int i, int j, int val)
    {
        if(i < 0 || j < 0 || i >= maze.length || j >= maze[0].length){return;}
        this.maze[i][j] = val;
    }

    /**this function returns the number of rows in the maze**/
    public int getRowsLength(){return maze.length;}

    /**this function returns the number of columns in the maze**/
    public int getColumnsLength(){return maze[0].length;}

    /**this function returns the position of a cell in the maze from a given index**/
    public Position getPosition(int i, int j)
    {
        if(i < 0 || j < 0 || i >= maze.length || j >= maze[0].length){return null;}
        return positionMatrix[i][j];
    }

    /**this function prints the maze**/
    public void print() {
        System.out.print("╔");
        for (int j = 0; j < maze[0].length * 2 + 1; j++)
        {
            System.out.print("═");
        }

        System.out.println("╗");

        for (int i = 0; i < maze.length; i++)
        {
            System.out.print("║ ");

            for (int j = 0; j < maze[0].length; j++)
            {
                if (i == 0 && j == 0)
                {
                    System.out.print("S ");
                }

                else if (i == maze.length - 1 && j == maze[0].length - 1)
                {
                    System.out.print("E ");
                }

                else
                {
                    System.out.print(maze[i][j] + " ");
                }
            }

            System.out.println("║");
        }

        System.out.print("╚");

        for (int j = 0; j < maze[0].length * 2 + 1; j++)
        {
            System.out.print("═");
        }

        System.out.println("╝");
    }

    /**this function returns the start position of the maze**/
    public Position getStartPosition() {return positionMatrix[0][0];}

    /**this function returns the goal position of the maze**/
    public Position getGoalPosition() {return  positionMatrix[maze.length - 1][maze[0].length - 1];}

}
