package algorithms.search;

public interface ISearchingAlgorithm {
    Solution solve(ISearchable domain);

    public String getName();

    public int getNumberOfNodesEvaluated();

    public void resetMaze(ISearchable maze);
}
