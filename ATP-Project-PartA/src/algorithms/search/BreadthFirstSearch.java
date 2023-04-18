package algorithms.search;

import java.util.*;

public class BreadthFirstSearch extends ASearchingAlgorithm{
    public BreadthFirstSearch() {
        super();
        this.collection =  new LinkedList<AState>();
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
        collection.add(startNode);
        AState temp = startNode;
        AState prev = null;
        ArrayList<AState> solution = new ArrayList<>();

        while(collection.size() != 0)
        {
            prev = temp;

            temp = ((Queue<AState>)collection).poll();
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

                ArrayList<AState> possibleMoves = new ArrayList<>();
                //delete cast***
                possibleMoves = maze.getAllPossibleStates( (MazeState)temp);
                for(AState state: possibleMoves)
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
                collection.addAll(possibleMoves);
            }
        }

        startNode.setPrev(null);
        Solution sol = new Solution(getPath(temp));
        resetMaze(maze);
        return sol;
    }


    @Override
    public String getName()
    {
        return "This is Breadth first search algorithm";
    }

}
