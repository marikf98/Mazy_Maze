package algorithms.search;

import java.util.Comparator;

/**This abstract lass represents a state in the search problem. it has the location as a String, a boolean visited filled
 * so we can know it was searched already, and the cost of the move to this location, and the prev state that will hold the state that discovered this one **/
public abstract class AState {
    private boolean visited;
    private AState prev;
    private int cost;
    private String location;
    private boolean m_isDiagonalMove;
    private boolean isGoal;

/** the constructor for the class**/
    public AState(String location) {
        this.location = location;
        this.prev = null;
        this.visited = false;
    }

    public void setGoal(boolean goal) {
        this.isGoal = goal;
    }

    /**getter function for the visited field**/
    public boolean isVisited() {
        return visited;
    }
    /** this setter lets us set that we visited this state**/
    public void setVisited() {
        this.visited = true;
    }
    /** this setter lets us reset this state**/

    public void setUnVisited(){this.visited = false;}
    /**getter and setter for the prev field**/
    public AState getPrev() {
        return prev;
    }

    public void setPrev(AState prev) {
        if(this.prev == null)
        {
            this.prev = prev;
        }
        if(prev == null)
        {
            this.prev = null;
        }
    }
    /**setter and getter for the cost field**/
    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }
    /**getter and setter for the location field**/
    public String getLocation() {
        return location;
    }

    /**this function checks if two states are equal comparing by their location and visited field**/
    public boolean equals(AState other)
    {
        if(this == other){return true;}

        if(!(this.visited == other.isVisited()))
        {
            return false;
        }
        return this.location.equals(other.getLocation());

    }

    /**abstract setter and getter function for the diagonal field**/
    public abstract void setDiagonalMove();
    public abstract boolean getDiagonalMove();

    @Override
    public String toString() {
        return location;
    }
}
