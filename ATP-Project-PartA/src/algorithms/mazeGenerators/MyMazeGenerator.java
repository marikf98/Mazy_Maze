package algorithms.mazeGenerators;

import java.lang.reflect.Array;
import java.util.*;

public class MyMazeGenerator extends AMazeGenerator{
    @Override
    public Maze generate(int rows, int columns) {
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
//                if(i == 0 && j == 0)
//                {
//                    continue;
//                }
                maze.setCellValue(i,j,1);
                locations[i][j] = new Position(i,j);
            }
        }
        int random;
//        ArrayList<Position> forest = new ArrayList<>();
//        ArrayList<ArrayList<Position>> forest = new ArrayList<ArrayList<Position>>();
//        for(int i = 1; i < rows - 1; i += 2)
//        {
//            for(int j = 1; j < columns - 1; j += 2)
//            {
////                forest.add(new Position(i,j));
//                ArrayList<Position> tempList = new ArrayList<Position>();
//                tempList.add(new Position(i,j));
//                forest.add(tempList);
//                maze.setCellValue(i,j,0);
//            }
//        }
//
//        ArrayList<Position> edges = new ArrayList<>();
//
//        for(int i = 2; i < rows - 1; i += 2)
//        {
//            for(int j = 1; j < columns - 1; j += 2)
//            {
//                edges.add(new Position(i,j));
//            }
//        }
//
//        for(int i = 1; i < rows - 1; i += 2)
//        {
//            for(int j = 2; j < columns - 1; j += 2)
//            {
//                edges.add(new Position(i,j));
//            }
//        }
//
//        Collections.shuffle(edges);
//
//        int ce_row;
//        int ce_col;
//        int tree1;
//        int tree2;
//        Position newTree;
//        ArrayList<Position> temp1;
//        ArrayList<Position> temp2;
//
//        while(forest.size() > 1)
//        {
//            ce_row = edges.get(0).getRowIndex();
//            ce_col = edges.get(0).getColumnIndex();
//
//            edges.remove(0);
//            tree1 = 0;
//            tree2 = 0;
//
//            if(ce_row % 2 == 0)
//            {
//                for(int i = 0; i < forest.size(); i++)
//                {
//                    ArrayList<Position> innerList = forest.get(i);
//                    for(int j = 0; j < innerList.size(); j++)
//                    {
//                        Position pos = innerList.get(j);
//                        if(pos.equals(new Position(ce_row -1, ce_col)))
//                        {
//                            tree1 += i;
//                        }
//                        if(pos.equals(new Position(ce_row + 1, ce_col)))
//                        {
//                            tree2 += i;
//                        }
//                    }
//                }
//            }
//            else
//            {
//                for(int i = 0; i < forest.size(); i++)
//                {
//                    ArrayList<Position> innerList = forest.get(i);
//                    for(int j = 0; j < innerList.size(); j++)
//                    {
//                        Position pos = innerList.get(j);
//                        if(pos.equals(new Position(ce_row, ce_col - 1)))
//                        {
//                            tree1 += i;
//                        }
//                        if(pos.equals(new Position(ce_row, ce_col + 1)))
//                        {
//                            tree2 += i;
//                        }
//                    }
//                }
//            }
//            System.out.println("tree1 = " + tree1 + " tree2 = " + tree2);
////            if(tree1 == -1)
////            {
////                tree1 = 0;
////            }
////            if(tree2 == -1)
////            {
////                tree2 = 0;
////            }
//            if( tree1 != tree2 )
//            {
////                newTree = new Position(forest.get(tree1).getRowIndex() + forest.get(tree2).getRowIndex(), forest.get(tree1).getColumnIndex() + forest.get(tree2).getColumnIndex());
//                ArrayList<Position> positionsList = new ArrayList<Position>();
//                ArrayList<Position> tree1List = forest.get(tree1);
//                ArrayList<Position> tree2List = forest.get(tree2);
//                positionsList.addAll(tree1List);
//                positionsList.addAll(tree2List);
//                //doubleTempList.add(forest.get(tree1).get(0));
////                temp1 = tree1List;
////                temp2 = tree2List;
//                forest.remove(tree1List);
//                forest.remove(tree2List);
//                forest.add(positionsList);
//                maze.setCellValue(ce_row,ce_col,0);
//
//            }
//        }
        for(int i = 0; i < rows; i++)
        {
            for(int j = 0; j < columns; j++)
            {
                if(checkNeighbours(i,j,maze))
                {
                    if(!((i-2) < 0 || (j-2) < 0))
                    {
                        random = rand.nextInt(2);
                        if (random == 0)
                        {
                            maze.setCellValue(locations[i - 1][j].getRowIndex(), locations[i - 1][j].getColumnIndex(), 0);
                        }
                        else
                        {
                            maze.setCellValue(locations[i][j - 1].getRowIndex(), locations[i][j - 1].getColumnIndex(), 0);
                        }

                        maze.setCellValue(locations[i][j].getRowIndex(), locations[i][j].getColumnIndex(), 0);
                    }


                    if((j-2<0) && !(i - 2 < 0))
                    {
                        maze.setCellValue(locations[i-1][j].getRowIndex(),locations[i-1][j].getColumnIndex(),0);
                        maze.setCellValue(locations[i][j].getRowIndex(), locations[i][j].getColumnIndex(), 0);
                    }
                    if(!((j - 2 < 0) || (i - 2 < 0)))
//                    if((j - 2 < 0) && !(i - 2 < 0))
                    {
                        maze.setCellValue(locations[i][j-1].getRowIndex(),locations[i][j-1].getColumnIndex(),0);
                        maze.setCellValue(locations[i][j].getRowIndex(), locations[i][j].getColumnIndex(), 0);
                    }
                }
            }
        }

        if(rows % 2 == 0 && columns % 2 == 0)
        {
            random = rand.nextInt(2);
            if(random == 0)
            {
                maze.setCellValue(rows-2,columns-1,0);
            }
            if(random == 1)
            {
                maze.setCellValue(rows-1,columns-2,0);
            }
            maze.setCellValue(rows-1,columns-1,0);
        }

        maze.setCellValue(rows - 1,columns - 1,0);
        return maze;
    }

    public Boolean checkNeighbours(int i, int j, Maze maze)
    {
        try
        {if(maze.getCellValue(i-1,j-1) == 0) {return false;}}
        catch (ArrayIndexOutOfBoundsException ignored)
        {}

        try
        {if(maze.getCellValue(i-1,j) == 0) {return false;}}
        catch (ArrayIndexOutOfBoundsException ignored)
        {}

        try
        {if(maze.getCellValue(i-1,j+1) == 0) {return false;}}
        catch (ArrayIndexOutOfBoundsException ignored)
        {}

        try
        {if(maze.getCellValue(i,j-1) == 0) {return false;}}
        catch (ArrayIndexOutOfBoundsException ignored)
        {}

        try
        {if(maze.getCellValue(i,j+1) == 0) {return false;}}
        catch (ArrayIndexOutOfBoundsException ignored)
        {}

        try
        {if(maze.getCellValue(i+1,j-1) == 0) {return false;}}
        catch (ArrayIndexOutOfBoundsException ignored)
        {}

        try
        {if(maze.getCellValue(i+1,j) == 0) {return false;}}
        catch (ArrayIndexOutOfBoundsException ignored)
        {}

        try
        {if(maze.getCellValue(i+1,j+1) == 0) {return false;}}
        catch (ArrayIndexOutOfBoundsException ignored)
        {}

        return true;


    }

}

