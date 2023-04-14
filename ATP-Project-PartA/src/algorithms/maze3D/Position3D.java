package algorithms.maze3D;

public class Position3D {
    private int row;
    private int column;

    public int getHigh() {
        return high;
    }

    public void setHigh(int high) {
        this.high = high;
    }

    private int high;

    public Position3D(int row, int column,int high) {
        this.row = row;
        this.column = column;
        this.high=high;
    }
    public int getRowIndex() {return row;}
    public int getColumnIndex() {return column;}

    @Override
    public String toString() {
        return "{" + row + "," + column + ","+high+"}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position3D position = (Position3D) o;
        return row == position.row && column == position.column && high==position.high;
    }

}
