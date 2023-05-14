package algorithms.search;

import algorithms.mazeGenerators.IMazeGenerator;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class BestFirstSearchTest {

    @Test
    void getName() throws Exception{
        BestFirstSearch best = new BestFirstSearch();
        assertEquals("This is Best first search algorithm", best.getName());
    }

    @Test
    void testTime() throws Exception
    {
        Maze maze;
        MyMazeGenerator gen = new MyMazeGenerator();
        maze = gen.generate(1000,1000);
        SearchableMaze BESTsearchableMaze = new SearchableMaze(maze);
        BestFirstSearch BESTsearcher = new BestFirstSearch();
        long startTime = System.currentTimeMillis();
        Solution BESTsolution = BESTsearcher.solve(BESTsearchableMaze);
        long endTime = System.currentTimeMillis();
        long elapsedTime = endTime - startTime;
        assertTrue(elapsedTime < 60000);
    }

    @Test
    void testNull() throws Exception
    {
        BestFirstSearch BESTsearcher = new BestFirstSearch();
        assertNull(BESTsearcher.solve(null));
    }

    @Test
    void testZero() throws Exception
    {
        BestFirstSearch BESTsearcher = new BestFirstSearch();
        assertEquals(BESTsearcher.getNumberOfNodesEvaluated(), 0);
    }

    @Test
    void testPath() throws Exception
    {
        IMazeGenerator mg = new MyMazeGenerator();
        Maze maze = mg.generate(30, 30);
        SearchableMaze searchableMaze = new SearchableMaze(maze);
        Solution solution = (new BestFirstSearch()).solve(searchableMaze);
        ArrayList<AState> solutionPath = solution.getSolutionPath();
        AState curr;
        AState prev;
        String currLocation;
        String prevLocation;
        String[] split;
        int currRow;
        int currColumn;
        int prevRow;
        int prevColumn;
        boolean invalidLocationFlag = false;
        boolean invalidPathFlag = false;
        for (int i = 0; i < solutionPath.size(); i++)
        {
            if(i + 1 == solutionPath.size())
            {
                break;
            }

            curr = solutionPath.get(i + 1);
            prev = solutionPath.get(i);
            currLocation = curr.getLocation();
            split =  currLocation.replaceAll("[{}]", "").split(",");
            currRow = Integer.parseInt(split[0].trim());
            currColumn = Integer.parseInt(split[1].trim());
            if(maze.getCellValue(currRow,currColumn) == 1)
            {
                invalidLocationFlag = true;
                break;
            }
            prevLocation = prev.getLocation();
            split = prevLocation.replaceAll("[{}]", "").split(",");
            prevRow = Integer.parseInt(split[0].trim());
            prevColumn = Integer.parseInt(split[1].trim());

            if((prevRow == currRow + 1) || (prevRow == currRow - 1) || (prevRow == currRow))
            {
                if((prevColumn == currColumn) || (prevColumn == currColumn + 1) || (prevColumn == currColumn - 1))
                {}
                else
                {
                    invalidPathFlag = true;
                }
            }
            else
            {
                invalidPathFlag = true;
            }
        }

        assertFalse(invalidPathFlag || invalidLocationFlag);

    }

    @Test
    void testLessNodesThenBFS() throws Exception
    {
        IMazeGenerator mg = new MyMazeGenerator();
        Maze maze = mg.generate(30, 30);
        SearchableMaze searchableMaze = new SearchableMaze(maze);
        BestFirstSearch bestSearcher = new BestFirstSearch();
        BreadthFirstSearch breadthSearcher = new BreadthFirstSearch();

        Solution solutionBest = bestSearcher.solve(searchableMaze);
        Solution solutionBreadth = breadthSearcher.solve(searchableMaze);
        assertTrue(bestSearcher.getNumberOfNodesEvaluated() < breadthSearcher.getNumberOfNodesEvaluated());
    }
}