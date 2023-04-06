package algorithms.search;

import java.io.Serializable;
import java.util.*;

public abstract class ASearchingAlgorithm implements ISearchingAlgorithm {
    protected int numberOfNodesEvaluated;
    protected Collection<AState> collection;
//    protected PriorityQueue<AState> collection;

    public ASearchingAlgorithm() {
        this.numberOfNodesEvaluated = 0;
        this.collection = new PriorityQueue<>();
    }
//    public ASearchingAlgorithm() {
//        this.numberOfNodesEvaluated = 0;
//    }

//    public void setCollection(Collection<AState> collection){
//        this.collection = collection;
//        this.collection = new LinkedList<AState>();
//    }

    public abstract Solution solve(ISearchable searchable);

    public int getNumberOfNodesEvaluated (){return numberOfNodesEvaluated;}

    public ArrayList<AState> getPath(AState endState)
    {
        ArrayList<AState> solution = new ArrayList<>();
        while(endState != null)
        {
            solution.add(endState);
            endState = endState.getPrev();
        }
        Collections.reverse(solution);
        return solution;
    }
}
