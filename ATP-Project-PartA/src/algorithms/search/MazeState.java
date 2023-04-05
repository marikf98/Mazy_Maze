package algorithms.search;

import algorithms.mazeGenerators.Position;

public class MazeState extends AState{
    private int row;
    private int column;

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

    }

    @Override
    public void getDiagonalMove() {

    }
}
