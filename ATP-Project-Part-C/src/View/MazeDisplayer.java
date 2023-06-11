package View;
import algorithms.mazeGenerators.Maze;
import algorithms.search.AState;
import algorithms.search.MazeState;
import algorithms.search.Solution;
import eu.hansolo.tilesfx.addons.Switch;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;;

//import java.awt.*;
import java.io.File;
import java.io.FileInputStream;

import static Model.MyModel.playerDirection;

public class MazeDisplayer extends Canvas{

    protected Maze m_maze;
    private Solution m_solution;
    private int[][] m_mazeGrid;
    private int m_characterRow;
    private int m_characterCol;

    public MazeDisplayer()
    {
        widthProperty().addListener(event -> reRender());
        heightProperty().addListener(event -> reRender());
    }

    public void setM_maze(Maze m_maze) {
        this.m_maze = m_maze;
        for(int i = 0; i < m_maze.getRowsLength(); i++)
        {
            for(int j = 0; j < m_maze.getColumnsLength(); j++)
            {
                m_mazeGrid[i][j] = m_maze.getCellValue(i,j);
            }
        }
        reRender();
    }

    public void setM_solution(Solution m_solution)
    {
        this.m_solution = m_solution;
        showSolution();
    }

    public void setM_mazeGrid(int[][] m_mazeGrid) {
        this.m_mazeGrid = m_mazeGrid;
    }

    public void setM_row(int m_row) {
        this.m_characterRow = m_row;
    }

    public void setM_col(int m_col) {
        this.m_characterCol = m_col;
    }

    public void setCharacterPosition(int row, int col)
    {
        m_characterRow = row;
        m_characterCol = col;
        reRender();
    }

    @Override
    public boolean isResizable() {return true;}

    @Override
    public double prefWidth(double value) {
        return super.prefWidth(value);
    }

    @Override
    public double prefHeight(double value) {
        return super.prefHeight(value);
    }

    public void reRender()
    {
//        double canvasHeight = getHeight();
//        double canvasWidth = getWidth();

        double cellHeight = getHeight() / m_maze.getRowsLength();
        double cellWidth = getWidth() / m_maze.getColumnsLength();

        Image goalPic = new Image(new File("Images/goal cherry.png").toURI().toString());
        Image wall = new Image(new File("Images/wall.png").toURI().toString());
        Image character = new Image(new File(getCharacterMove()).toURI().toString());
        Image backGround = new Image(new File("Images/board background.png").toURI().toString());

        GraphicsContext graphicsContext = getGraphicsContext2D();
        graphicsContext.clearRect(0,0,getWidth(),getHeight());
        graphicsContext.drawImage(backGround,0,0,getWidth(),getHeight());

        for(int i = 0; i < m_maze.getRowsLength(); i++)
        {
            for(int j = 0; j < m_maze.getColumnsLength(); j++)
            {
                graphicsContext.drawImage(wall,j * cellWidth, i * cellHeight, cellWidth, cellHeight);
            }
        }

        graphicsContext.drawImage(goalPic,m_maze.getGoalPosition().getColumnIndex() * cellWidth, m_maze.getGoalPosition().getRowIndex() * cellHeight, cellWidth, cellHeight);
        graphicsContext.drawImage(character, m_characterCol * cellWidth, m_characterRow * cellHeight, cellWidth, cellHeight);
    }

    public String getCharacterMove()
    {
        String dir = playerDirection;
        int playerNum = ChoosePlayer.playerNum;
        switch(dir){
            case "up":
                return "Images/player"+ String.valueOf(playerNum) +"_up.png";
            case "down":
                return "Images/player"+ String.valueOf(playerNum) +"_down.png";
            case "left":
                return "Images/player"+ String.valueOf(playerNum) +"_left.png";
            case "right":
                return "Images/player"+ String.valueOf(playerNum) +"_right.png";
            default:
                return "Images/player"+ String.valueOf(playerNum) +"_up.png";
        }
    }

    public void showSolution()
    {
        Image packManFood = new Image(new File("Images/pacman food.png").toURI().toString());
        Image goal = new Image(new File("Images/goal cherry.png").toURI().toString());
        Image character = new Image(new File(getCharacterMove()).toURI().toString());

        double cellHeight = getHeight() / m_maze.getRowsLength();
        double cellWidth = getWidth() / m_maze.getColumnsLength();
        GraphicsContext graphicsContext = getGraphicsContext2D();

        for(int i = 0; i < m_solution.getSolutionPath().size(); i++)
        {
            int row = ((MazeState)(m_solution.getSolutionPath().get(i))).getRow();
            int col = ((MazeState)(m_solution.getSolutionPath().get(i))).getColumn();

            if(m_solution.getSolutionPath().size() - 1 == i)
            {
                graphicsContext.drawImage(goal, col * cellWidth, row * cellHeight, cellWidth, cellHeight);
            }
            else
            {
                if(i == 0)
                {
                    graphicsContext.drawImage(character, m_characterCol * cellWidth, m_characterRow * cellHeight, cellWidth, cellHeight);
                }
                else
                {
                    graphicsContext.drawImage(packManFood, col * cellWidth, row * cellHeight, cellWidth, cellHeight);
                }
            }
        }
    }


}
