package algorithms.search;

import algorithms.mazeGenerators.Position;

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

//    @Override
//    public int compareTo(AState first, AState second)
//    {
////        if(Integer.compare(first.getCost(), second.getCost()) == 1)
////        {
////            return -1;
////        }
////        if(Integer.compare(first.getCost(), second.getCost()) == -1)
////        {
////            return 1;
////        }
////        return 0;
////        return Integer.compare(first.getCost(), second.getCost());
//        if(first.getCost() < second.getCost())
//        {
//            return -1;
//        }
//        if(first.getCost() > second.getCost())
//        {
//            return 1;
//        }
//        else
//        {
//            return 0;
//        }
//    }


//    @Override
//    public int compare(AState first, AState second) {
//        if(first.getCost() < second.getCost())
//        {
//            return -1;
//        }
//        if(first.getCost() > second.getCost())
//        {
//            return 1;
//        }
//        else
//        {
//            return 0;
//        }
//    }
}
