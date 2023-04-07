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
        AState startNode = maze.getStart();
        AState goalNode = maze.getGoal();
//        startNode.setVisited();
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
                neighbours = maze.getAllPossibleStates((MazeState) temp);
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

//        ArrayList<AState> inReverse = new ArrayList<>();
//        for(int i = 0; i <=numberOfNodesEvaluated; i++)
//        {
//            inReverse.add(temp);
//            temp = temp.getPrev();
//        }
//        Collections.reverse(inReverse);
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
