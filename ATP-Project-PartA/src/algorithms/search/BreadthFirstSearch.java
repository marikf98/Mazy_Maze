package algorithms.search;

import java.util.*;

public class BreadthFirstSearch extends ASearchingAlgorithm{
    //protected Queue<AState> collection;
    public BreadthFirstSearch() {
        super();
//        this.collection = (Queue<AState>)this.collection;
        this.collection =  new LinkedList<AState>();
    }

    @Override
    public Solution solve(ISearchable maze) {
        AState startNode = maze.getStart();
        AState goalNode = maze.getGoal();
        collection.add(startNode);
        AState temp = startNode;
        AState prev = null;
        ArrayList<AState> solution = new ArrayList<>();

        while(collection.size() != 0) // maybe this should consider adding queue.size() != 0 && !maze.isSolved()
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
                possibleMoves = maze.getAllPossibleStates((MazeState) temp);
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

//        while(temp != null)
//        {
//            solution.add(temp);
//            temp = temp.getPrev();
//        }
//
//        Collections.reverse(solution);
//        return new Solution(solution);
        return new Solution(getPath(temp));
    }


    @Override
    public String getName()
    {
        return "This is Breadth first search algorithm";
    }

}
