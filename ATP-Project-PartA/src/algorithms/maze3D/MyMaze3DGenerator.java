package algorithms.maze3D;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class MyMaze3DGenerator extends AMaze3DGenerator {

    private int[][][] maze;
    private int sizeX;
    private int sizeY;
    private int sizeZ;
    private Random rand;

    public Maze3D generate(int row, int column, int depth) {
        this.sizeX = row;
        this.sizeY = column;
        this.sizeZ = depth;
        this.rand = new Random();
        this.maze = new int[sizeX][sizeY][sizeZ];
        Maze3D maze3D = new Maze3D(maze);
        //init the maze wiith walls all over

        for (int x = 0; x < sizeX; x++) {
            for (int y = 0; y < sizeY; y++) {
                for (int z = 0; z < sizeZ; z++) {
                    maze[x][y][z] = 1;
                }
            }
        }
        Position3D start = new Position3D(0, 0, 0);
        Position3D end = new Position3D(sizeX - 1, sizeY - 1, 0);
        generateDFS(start, this.maze);
        return new Maze3D(maze);


    }

    private void generateDFS(Position3D curr, int[][][] maze) {
        if (maze[curr.getRowIndex()][curr.getColumnIndex()][curr.getDepthIndex()] == 0) {
            return;
        }

        maze[curr.getRowIndex()][curr.getColumnIndex()][curr.getDepthIndex()] = 0;
        int[][] directions = {{0, 1, 0}, {0, -1, 0}, {1, 0, 0}, {-1, 0, 0}, {0, 0, 1}, {0, 0, -1}};

        List<int[]> directionsList = Arrays.asList(directions);
        Collections.shuffle(directionsList);

        for (int[] direction : directionsList) {
            int newX = curr.getRowIndex() + direction[0];
            int newY = curr.getColumnIndex() + direction[1];
            int newZ = curr.getDepthIndex() + direction[2];


            if (newX < 0 || newX >= sizeX || newY < 0 || newY >= sizeY || newZ < 0 || newZ >= sizeZ) {
                continue;
            }

            //carve a path between walls

            if (maze[newX][newY][newZ] == 1) {
                int wallX = (newX + curr.getRowIndex()) / 2;
                int wallY = (newY + curr.getColumnIndex()) / 2;
                int wallZ = (newZ + curr.getDepthIndex()) / 2;
                maze[wallX][wallY][wallZ] = 0;

                generateDFS(new Position3D(newX, newY, newZ), maze);

            }

        }
    }
}



