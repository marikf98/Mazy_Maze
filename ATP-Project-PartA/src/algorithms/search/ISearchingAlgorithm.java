package algorithms.search;
/**this if the interface that each searching algorithm need to implement for it to be eligible to be used on a searchable instance
 * solve - the function that will solve the search problem
 * get name - returns the name of the search algorithm
 * getAllPossibleStates - will return all the possible states that can be reached from the current state
 * setUnvisited - lets reset the searchable**/
public interface ISearchingAlgorithm {
    Solution solve(ISearchable domain);

    public String getName();

    public int getNumberOfNodesEvaluated();

    public void resetMaze(ISearchable maze);
}
