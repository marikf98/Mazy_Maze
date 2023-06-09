package algorithms.mazeGenerators;

import java.io.Serializable;

public abstract class AMazeGenerator implements IMazeGenerator, Serializable
{
    /**this is an abstract class that implements the IMazeGenerator interface,
     * all the generators extend this class, and implement the generate function**/
    @Override
    public abstract Maze generate(int rows, int columns);

    /**this function measures how long it takes the algorithm to generate a maze**/
    @Override
    public long measureAlgorithmTimeMillis(int rows, int columns) {
        if(rows < 2 || columns < 2)
        {
            return 0;
        }
        long start = System.currentTimeMillis();
        generate(rows, columns);
        long finish = System.currentTimeMillis();
        return finish - start;
    }
}
