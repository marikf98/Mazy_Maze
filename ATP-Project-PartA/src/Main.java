import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;
import algorithms.mazeGenerators.SimpleMazeGenerator;
import algorithms.search.*;

public class Main {
    public static void main(String[] args) {
        Maze maze;
        MyMazeGenerator gen = new MyMazeGenerator();
//        SimpleMazeGenerator simple = new SimpleMazeGenerator();
        maze = gen.generate(1000,1000);
//        maze = simple.generate(10,10);
        maze.print();
        System.out.println(maze.getStartPosition());
        System.out.println(maze.getGoalPosition());
        SearchableMaze BFSsearchableMaze = new SearchableMaze(maze);
        SearchableMaze DFSsearchableMaze = new SearchableMaze(maze);
        SearchableMaze BESTsearchableMaze = new SearchableMaze(maze);
        BreadthFirstSearch BFSsearcher = new BreadthFirstSearch();
        DepthFirstSearch DFSsearcher = new DepthFirstSearch();
        BestFirstSearch BESTsearcher = new BestFirstSearch();
        Solution BFSsolution = BFSsearcher.solve(BFSsearchableMaze);
        Solution DFSsolution = DFSsearcher.solve(DFSsearchableMaze);
        Solution BESTsolution = BESTsearcher.solve(BESTsearchableMaze);
        System.out.println();
    }
}
