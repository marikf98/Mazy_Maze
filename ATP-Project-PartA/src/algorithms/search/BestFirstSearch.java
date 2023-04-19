package algorithms.search;

import algorithms.mazeGenerators.Maze;

import java.util.Comparator;
import java.util.PriorityQueue;
/**this is the BestFirstSearch class which extends the BreadthFirstSearch class
 *it uses the same solve function as the BFS it uses a priority queue instead of a regular queue
 * the priority queue uses a comparator and will always choose a diagonal move over a straight move because a diagonal move costs 15 and the other one 10 **/
public class BestFirstSearch extends BreadthFirstSearch{
    public BestFirstSearch()
    {
        super();
        this.collection = new PriorityQueue<AState>(new StateComparator());
    }
    /** a getter function for the name**/

    @Override
    public String getName()
    {
        return "This is Best first search algorithm";
    }

}
/**this is the comparator class for the priority queue, it will choose the node that is more expensive to get to
 * **/
class StateComparator implements Comparator<AState>
{
    @Override
    public int compare(AState first, AState second) {
        if(first.getCost() < second.getCost())
        {
            return 1;
        }
        if(first.getCost() > second.getCost())
        {
            return -1;
        }
        else
        {
            return 0;
        }
    }

}

