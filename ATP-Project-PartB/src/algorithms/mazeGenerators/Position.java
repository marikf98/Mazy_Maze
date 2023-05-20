package algorithms.mazeGenerators;

import java.io.Serializable;
import java.util.Objects;
/**this class represents a position in the maze**/
public class Position  implements Serializable
{
    private int row;
    private int column;

    public Position(int row, int column) {
        if(row < 0 || column < 0){return;}
        this.row = row;
        this.column = column;
    }
    /**the get function for each field of the class**/
    public int getRowIndex() {return row;}
    public int getColumnIndex() {return column;}
    /**the to string, equals and the hashcode function for the position class**/
    @Override
    public String toString() {
        return "{" + row + "," + column + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return row == position.row && column == position.column;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, column);
    }
}
