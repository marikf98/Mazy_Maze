package algorithms.search;

import java.util.Comparator;

//public abstract class AState implements Comparator<AState> {
public abstract class AState {

    private boolean visited;
    private AState prev;
    private int cost;
    private String location;
    private boolean m_isDiagonalMove;


    public AState(String location) {
        this.location = location;
        this.prev = null;
        this.visited = false;
    }

    private boolean isGoal;

    //private String state;

    public void setGoal(boolean goal) {
        this.isGoal = goal;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited() {
        this.visited = true;
    }

    public void setUnVisited(){this.visited = false;}

    public AState getPrev() {
        return prev;
    }

    public void setPrev(AState prev) {
        if(this.prev == null)
        {
            this.prev = prev;
        }
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public String getLocation() {
        return location;
    }
    public boolean equals(AState other)
    {
        if(this == other){return true;}

        if(!(this.visited == other.isVisited()))
        {
            return false;
        }
        return this.location.equals(other.getLocation());

    }

//    public abstract int compare(AState first, AState second);

    public abstract void setDiagonalMove();
    public abstract boolean getDiagonalMove();

    @Override
    public String toString() {
        return location;

    }
}
