package algorithms.mazeGenerators;
/**this interface represents the general MazeGenerator and all the generators implement it **/
public interface IMazeGenerator {
    public Maze generate(int rows, int columns);
    public long measureAlgorithmTimeMillis(int rows, int columns);

}
