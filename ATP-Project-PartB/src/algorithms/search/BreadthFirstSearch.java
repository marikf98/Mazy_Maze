package algorithms.search;

import java.util.*;
/**this is the BreadthFirstSearch class which extends the ASearchingAlgorithm class
 *solve(ISearchable maze) - this function solves the maze using the BFS algorithm **/
public class BreadthFirstSearch extends ASearchingAlgorithm{
    public BreadthFirstSearch() {
        super();
        this.collection =  new LinkedList<AState>();
    }
    /**
     *  first we initialize the start position and the goal position and add the start position to the queue, we iterate over the queue and
     *  add the neighbors of the current position to the queue,
     * each iteration we check if the state that we got from the queue is the goal node if it is we break the loop
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
                possibleMoves = maze.getAllPossibleStates(temp);
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

    /** a getter function for the name**/
    @Override
    public String getName()
    {
        return "This is Breadth first search algorithm";
    }

}
