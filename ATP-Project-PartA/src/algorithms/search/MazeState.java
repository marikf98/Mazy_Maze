package algorithms.search;

import algorithms.mazeGenerators.Position;
/**this class represents a state of the maze and extends the AState class
 * row, column - represent the location of the state on the maze
 * m_isDiagonalMove - represent if the move to this state has been done diagonally
 * all the other function are the constructor and the getter and setters of the class
 * all the function in this class are getters setters and the constructor*/

public class MazeState extends AState
{
    private int row;
    private int column;
    private boolean m_isDiagonalMove;

    public MazeState(Position location)
    {
        super(location.toString());
        this.row = location.getRowIndex();
        this.column = location.getColumnIndex();
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    @Override
    public void setDiagonalMove() {
        this.m_isDiagonalMove = true;
    }

    @Override
    public boolean getDiagonalMove() {
        return this.m_isDiagonalMove;
    }

}
