package algorithms.mazeGenerators;

public class EmptyMazeGenerator extends AMazeGenerator{
    @Override
    public Maze generate(int rows, int columns) {
        int [][] emptyMaze = new int[rows][columns];
        for(int i = 0; i <rows; i++)
        {
           for(int j = 0; j < columns; j++)
           {
               emptyMaze[i][j] = 0;
           }
        }
        return new Maze(emptyMaze);
    }
}
