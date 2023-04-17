package algorithms.maze3D;

public abstract class AMaze3DGenerator implements IMazeGenerator3D {
    @Override
    public abstract Maze3D generate(int row, int column,int depth);
    @Override
    public  long measureAlgorithmTimeMillis( int rows, int columns,int depth){
        long start = System.currentTimeMillis();
        generate(rows, columns,depth);
        long finish = System.currentTimeMillis();
        return finish - start;
    }
}
