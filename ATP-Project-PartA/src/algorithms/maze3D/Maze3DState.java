package algorithms.maze3D;
import algorithms.search.AState;

public class Maze3DState extends AState {
        private int row;
        private int column;

        private int depth;
        private boolean m_isDiagonalMove;


        // a builder for the state of a maze
        public Maze3DState(Position3D location)
        {
            super(location.toString());
            this.row = location.getRowIndex();
            this.column = location.getColumnIndex();
            this.depth= location.getDepthIndex();
        }
        //return the row
        public int getRow() {
            return row;
        }
            //return the column
        public int getColumn(){return column;}
         //return the depth
        public int getDepth() {
        return depth;
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


