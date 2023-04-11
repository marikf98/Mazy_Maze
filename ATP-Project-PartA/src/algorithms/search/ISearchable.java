package algorithms.search;

import java.util.ArrayList;

public interface ISearchable {
    public AState getStart();
    public AState getGoal();
    public ArrayList<AState> getAllPossibleStates(MazeState mState);
}
