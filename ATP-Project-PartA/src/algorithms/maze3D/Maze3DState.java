package algorithms.maze3D;

import algorithms.mazeGenerators.Position;

public class Maze3DState extends AState {

        private int row;
        private int column;

        private int depth;
        private boolean m_isDiagonalMove;

        public Maze3DState(Position3D location)
        {
            super(location.toString());
            this.row = location.getRowIndex();
            this.column = location.getColumnIndex();
            this.depth= location.getDepthIndex();
        }

        public int getRow() {
            return row;
        }

        public int getColumn(){return column;}

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


