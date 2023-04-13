package algorithms.search;

import java.util.ArrayList;

public class Solution {
    private ArrayList<AState> pathToGoal;

    public Solution(ArrayList<AState> pathToGoal) {
        this.pathToGoal = pathToGoal;
    }

    public ArrayList<AState> getSolutionPath() {
        return pathToGoal;
    }
    public void print()
    {
        for(AState state : this.pathToGoal)
        {
            System.out.println(state);
        }
    }
}
