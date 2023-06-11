package Model;

import algorithms.mazeGenerators.Maze;
import algorithms.search.Solution;
import javafx.scene.input.KeyCode;

public interface IModel {

    void start();
    void stopServers();
    void generateMaze(int width,int height);
    Maze getMaze();
    Solution getSolution();
    void setMaze(Maze maze);
    void solve();

    int getCharacterPositionRow();
    int getCharacterPositionColumn();
    boolean moveCharacter(KeyCode move);
    void setPosition(int row,int col);


    void generateWithMaze(Maze mazeThatSend);

}
