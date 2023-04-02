import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MazeGenerator;
import algorithms.mazeGenerators.MyMazeGenerator;
import algorithms.mazeGenerators.SimpleMazeGenerator;

public class Main {
    public static void main(String[] args) {
//        System.out.println("Hello world!");
//        int [][] maz = new int [10][10];
        Maze maze;
        MyMazeGenerator gen = new MyMazeGenerator();
        SimpleMazeGenerator simple = new SimpleMazeGenerator();
//        maze = gen.generate(10,10);
//        maze.display();
        maze = simple.generate(5,5);
        maze.display();
    }
}