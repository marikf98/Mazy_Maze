package algorithms.maze3D;

public interface IMazeGenerator3D {
    /**This is an intrface to be use by any 3d genrator */
    Maze3D generate(int depth, int row, int column);
    long measureAlgorithmTimeMillis( int depth, int row,int column);
}
