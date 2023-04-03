package algorithms.search;

public abstract class ASearchingAlgorithm implements ISearchingAlgorithm {
    private int numberOfNodesEvaluated;

    public abstract Solution solve(ISearchable searchable);

    public int getNumberOfNodesEvaluated (){return numberOfNodesEvaluated;}
}
