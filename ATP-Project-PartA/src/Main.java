import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MazeGenerator;
import algorithms.mazeGenerators.MyMazeGenerator;

public class Main {
    public static void main(String[] args) {
//        System.out.println("Hello world!");
//        int [][] maz = new int [10][10];
        Maze maze;
        MyMazeGenerator gen = new MyMazeGenerator();
        maze = gen.generate(5,5);
        maze.display();
    }
}