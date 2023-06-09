package algorithms.maze3D;

public class Position3D {
    /**this class represents a position in the maze**/
    private int row;
    private int column;
    private int depth;
    /**the get function for each field of the class**/
    public int getDepthIndex() {
        return depth;
    }

    public void setDepthIndex(int depth) {
        this.depth = depth;
    }


    public Position3D(int depth,int row, int column) {
        if(depth < 0 || row < 0 || column < 0)
        {
            return;
        }
        this.row = row;
        this.column = column;
        this.depth=depth;
    }
    public int getRowIndex() {return row;}
    public int getColumnIndex() {return column;}

    /**the to string, equals and the hashcode function for the position class**/
    @Override
    public String toString() {
        return "{" +depth+","+ row + "," + column+ "}" ;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position3D position = (Position3D) o;
        return row == position.row && column == position.column && depth==position.depth;
    }

}
