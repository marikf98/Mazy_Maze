package algorithms.search;

import java.util.ArrayList;
/**this if the interface that each searchable need to implement for it to be eligible to be searched using the search algorithms
 * getStart - returns the start location of the searchable
 * getGoal - returns the goal location of the searchable
 * getAllPossibleStates - will return all the possible states that can be reached from the current state
 * setUnvisited - lets reset the searchable**/
public interface ISearchable {
    public AState getStart();
    public AState getGoal();
//    public ArrayList<AState> getAllPossibleStates(MazeState mState);
    public ArrayList<AState> getAllPossibleStates(AState mState);
    public void setUnvisited();

}
