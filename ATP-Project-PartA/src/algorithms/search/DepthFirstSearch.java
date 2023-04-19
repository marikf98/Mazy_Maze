package algorithms.search;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;
/**this is the DepthFirstSearch class which extends the ASearchingAlgorithm class
 *solve(ISearchable maze) - this function solves the maze using the DFS algorithm **/
public class DepthFirstSearch extends ASearchingAlgorithm{
    private Stack<AState> collection;

    public DepthFirstSearch() {
        super();
        this.collection = new Stack<>();
    }

    /**
     *  first we initialize the start position and the goal position and add the start position to the stack, we iterate over the stack and
     *  add the neighbors of the current position to the stack,
     * each iteration we check if the state that we got from the stack is the goal node if it is we break the loop
     * during the iteration we set the cost of getting to each node
     * at the end we reset the maze and return a Solution object that holds the path to the solution of the maze
     * for each state that we check we increment the number of nodes visited by one **/
    @Override
    public Solution solve(ISearchable maze) {
        if(maze == null)
        {
            System.out.println("You need to supply a valid searchable");
            return null;
        }
        AState startNode = maze.getStart();
        AState goalNode = maze.getGoal();
        collection.push(startNode);
        AState temp = startNode;
        AState prev = null;
        ArrayList<AState> neighbours = null;

        while(!collection.empty())
        {
            prev = temp;
            temp = collection.pop();
            if(!temp.isVisited())
            {
                temp.setVisited();
                numberOfNodesEvaluated++;
                if(temp.equals(goalNode))
                {
                    goalNode.setPrev(prev);
                    temp.setPrev(prev);
                    break;
                }

                neighbours = maze.getAllPossibleStates(temp);
                for(AState state: neighbours)
                {
                    state.setPrev(temp);
                    if(state.getDiagonalMove())
                    {
                        state.setCost(temp.getCost() + 15);
                    }
                    else
                    {
                        state.setCost(temp.getCost() + 10);
                    }

                }
                Collections.reverse(neighbours);
                for(AState neighbour : neighbours)
                {
                    collection.push(neighbour);
                }
            }


        }

        startNode.setPrev(null);
        Solution sol = new Solution(getPath(temp));
        resetMaze(maze);
        return sol;
    }
    /** a getter function for the name**/

    @Override
    public String getName() {
        return "This is Depth first algorithm";
    }
}
