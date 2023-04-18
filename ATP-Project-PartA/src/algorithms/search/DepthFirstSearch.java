package algorithms.search;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

public class DepthFirstSearch extends ASearchingAlgorithm{
    private Stack<AState> collection;

    public DepthFirstSearch() {
        super();
        this.collection = new Stack<>();
    }

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
                //delete cast
                neighbours = maze.getAllPossibleStates( temp);
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

    @Override
    public String getName() {
        return "This is Depth first algorithm";
    }
}
