package algorithms.search;

import java.util.ArrayList;

public interface ISearchable {
    public AState getStart();
    public AState getGoal();
//    public ArrayList<AState> getAllPossibleStates(MazeState mState);
    public ArrayList<AState> getAllPossibleStates(AState mState);
    public void resetMaze(ISearchable maze);

}
