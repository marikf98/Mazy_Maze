package algorithms.maze3D;

public interface IMazeGenerator3D {
    Maze3D generate(int depth, int row, int column);
    long measureAlgorithmTimeMillis( int row, int column,int depth);
}
