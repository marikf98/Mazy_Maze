package algorithms.search;

import algorithms.mazeGenerators.Maze;

import java.util.Comparator;
import java.util.PriorityQueue;

public class BestFirstSearch extends BreadthFirstSearch{
    public BestFirstSearch()
    {
        super();
        this.collection = new PriorityQueue<AState>(new StateComparator());
    }

    @Override
    public String getName()
    {
        return "This is Best first search algorithm";
    }

}

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

