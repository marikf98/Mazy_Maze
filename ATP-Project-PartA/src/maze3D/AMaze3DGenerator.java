package maze3D;

public abstract class AMaze3DGenerator implements IMazeGenerator3D {
    public abstract Maze3D generate(int depth, int row, int column);
    public abstract long measureAlgorithmTimeMillis(int depth, int row, int column);
}
