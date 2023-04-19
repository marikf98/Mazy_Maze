package algorithms.maze3D;

public class Position3D {
    private int row;
    private int column;
    private int depth;

    public int getDepthIndex() {
        return depth;
    }

    public void setDepthIndex(int depth) {
        this.depth = depth;
    }


    public Position3D(int depth,int row, int column) {
        this.row = row;
        this.column = column;
        this.depth=depth;
    }
    public int getRowIndex() {return row;}
    public int getColumnIndex() {return column;}


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
