package algorithms.search;

import java.io.Serializable;
import java.util.*;
/**this abstract class is the parent of all searching algorithms it uses an abstract collection so each searcher can override it with the type it needs, it is initialised as a priority queue **/
public abstract class ASearchingAlgorithm implements ISearchingAlgorithm, Serializable
{
    protected int numberOfNodesEvaluated;
    protected Collection<AState> collection;

    public ASearchingAlgorithm() {
        this.numberOfNodesEvaluated = 0;
        this.collection = new PriorityQueue<>();
    }
    /**this method is the main algorithm that each searcher will override, it takes a searchable object and returns a solution object **/
    public abstract Solution solve(ISearchable searchable);

    /**this method returns the number of nodes that where evaluated**/
    public int getNumberOfNodesEvaluated (){return numberOfNodesEvaluated;}

    /**this method returns the path of the solution, the path is a list of states**/
    public ArrayList<AState> getPath(AState endState)
    {
        if(endState == null){return null;}

        ArrayList<AState> solution = new ArrayList<>();
        while(endState != null)
        {
            solution.add(endState);
            endState = endState.getPrev();
        }
        Collections.reverse(solution);
        return solution;
    }
    /**this method resets the searchable to its original state after it has been solved**/
    public void resetMaze(ISearchable maze)
    {
        if(maze == null){return;}
        (maze).setUnvisited();
    }
}
