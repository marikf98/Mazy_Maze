package algorithms.search;

import java.util.ArrayList;
/**
 * this class represents a solution to a search problem
 * pathToGoal - is a list that holds all the nodes of the solution path
 * getSolutionPath - returns the list of the solution
 * print - prints the solution**/
public class Solution {
    private ArrayList<AState> pathToGoal;

    public Solution(ArrayList<AState> pathToGoal)
    {
        if(pathToGoal == null){return;}
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
