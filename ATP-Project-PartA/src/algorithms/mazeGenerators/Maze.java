package algorithms.mazeGenerators;

public class Maze {
    /** Maze indexing :
     * 0 - pass
     * 1 - wall
     * 2 - Starting point (IN)
     * 3 - exit point (OUT)**/
    private int [][] maze;
    private Position [][] positionMatrix;

    public Maze(int [][] maze)
    {
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

    public int getCellValue (int i, int j)
    {
        return maze[i][j];
    }

    public void setCellValue (int i, int j, int val)
    {
        this.maze[i][j] = val;
    }
    public int getRowsLength(){return maze.length;}

    public int getColumnsLength(){return maze[0].length;}

    public Position getPosition(int i, int j) {return positionMatrix[i][j];}
    public void print() {
//        System.out.print("╔");
//        for (int j = 0; j <  maze[0].length; j++)
//        {
//            System.out.print("═");
//        }
//        System.out.println("╗");

        for (int i = 0; i < maze.length; i++) {
//            System.out.print("║");
            for (int j = 0; j < maze[0].length; j++) {
                if (i == 0 && j == 0)
                {
                    System.out.print("S");
                    continue;
                }
                if (i == maze.length-1 && j == maze[0].length-1)
                {
                    System.out.print("E");
                }
                else
                {
                    System.out.print(" " + maze[i][j] + " ");
                }
            }
//            System.out.println("║");
        }

//        System.out.print("╚");
//        for (int j = 0; j < maze[0].length; j++) {
//            System.out.print("═");
//        }
//        System.out.println("╝");
    }


//    public Position getStartPosition() {return new Position(0,0);}
    public Position getStartPosition() {return positionMatrix[0][0];}

//    public Position getGoalPosition() {
//        return new Position(maze.length - 1,maze[0].length - 1);
//    }
    public Position getGoalPosition() {return  positionMatrix[maze.length - 1][maze[0].length - 1];}

}
