package algorithms.mazeGenerators;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class KrusKalGenerator extends AMazeGenerator{
    private int originalRows;
    private int originalColumns;
    @Override
    public Maze generate(int rows, int columns) {
        this.originalRows = rows;
        this.originalColumns = columns;
        if(rows % 2 == 0)
        {
            rows ++;
        }
        if(columns % 2 == 0)
        {
            columns ++;
        }
        Random rand = new Random();
        int [][] grid = new int[rows][columns];
        Maze maze = new Maze(grid);
        maze.setCellValue(0,0,0);
        Position[][] locations = new Position[rows][columns];
        locations[0][0] = new Position(0,0);
        for(int i = 0; i < rows; i++)
        {
            for(int j = 0; j < columns; j ++)
            {

                maze.setCellValue(i,j,1);
                locations[i][j] = new Position(i,j);
            }
        }
        int random;
        ArrayList<ArrayList<Position>> forest = new ArrayList<ArrayList<Position>>();
        for(int i = 1; i < rows - 1; i += 2)
        {
            for(int j = 1; j < columns - 1; j += 2)
            {
                ArrayList<Position> tempList = new ArrayList<Position>();
                tempList.add(new Position(i,j));
                forest.add(tempList);
                maze.setCellValue(i,j,0);
            }
        }

        ArrayList<Position> edges = new ArrayList<>();

        for(int i = 2; i < rows - 1; i += 2)
        {
            for(int j = 1; j < columns - 1; j += 2)
            {
                edges.add(new Position(i,j));
            }
        }

        for(int i = 1; i < rows - 1; i += 2)
        {
            for(int j = 2; j < columns - 1; j += 2)
            {
                edges.add(new Position(i,j));
            }
        }

        Collections.shuffle(edges);

        int ce_row;
        int ce_col;
        int tree1;
        int tree2;
        Position newTree;
        ArrayList<Position> temp1;
        ArrayList<Position> temp2;

        while(forest.size() > 1)
        {
            ce_row = edges.get(0).getRowIndex();
            ce_col = edges.get(0).getColumnIndex();

            edges.remove(0);
            tree1 = 0;
            tree2 = 0;

            if(ce_row % 2 == 0)
            {
                for(int i = 0; i < forest.size(); i++)
                {
                    ArrayList<Position> innerList = forest.get(i);
                    for(int j = 0; j < innerList.size(); j++)
                    {
                        Position pos = innerList.get(j);
                        if(pos.equals(new Position(ce_row -1, ce_col)))
                        {
                            tree1 += i;
                        }
                        if(pos.equals(new Position(ce_row + 1, ce_col)))
                        {
                            tree2 += i;
                        }
                    }
                }
            }
            else
            {
                for(int i = 0; i < forest.size(); i++)
                {
                    ArrayList<Position> innerList = forest.get(i);
                    for(int j = 0; j < innerList.size(); j++)
                    {
                        Position pos = innerList.get(j);
                        if(pos.equals(new Position(ce_row, ce_col - 1)))
                        {
                            tree1 += i;
                        }
                        if(pos.equals(new Position(ce_row, ce_col + 1)))
                        {
                            tree2 += i;
                        }
                    }
                }
            }
            System.out.println("tree1 = " + tree1 + " tree2 = " + tree2);

            if( tree1 != tree2 )
            {
                ArrayList<Position> positionsList = new ArrayList<Position>();
                ArrayList<Position> tree1List = forest.get(tree1);
                ArrayList<Position> tree2List = forest.get(tree2);
                positionsList.addAll(tree1List);
                positionsList.addAll(tree2List);
                forest.remove(tree1List);
                forest.remove(tree2List);
                forest.add(positionsList);
                maze.setCellValue(ce_row,ce_col,0);

            }
        }

        return maze;
    }

}
