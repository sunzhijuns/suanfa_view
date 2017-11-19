import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public class MazeData {
    public static final char ROAD = ' ';
    public static final char WALL = '#';

    private int N, M;// N行M列
    public char[][] maze;
    private int entranceX, entranceY;
    private int exitX, exitY;

    public boolean[][] visited;
    public boolean[][] path;
    public boolean[][] result;

    public MazeData(int N, int M) {
        if (N % 2 == 0 || M % 2 == 0) {
            throw new IllegalArgumentException("Our Maze Generalization Algorithm requires the width and height of the maze are odd numbers!");
        }
        this.N = N;
        this.M = M;

        maze = new char[N][M];
        visited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (i % 2 == 0 || j % 2 == 0) {
                    maze[i][j] = WALL;
                } else {
                    maze[i][j] = ROAD;
                }
                visited[i][j] = false;

            }
        }
        entranceX = 1;
        entranceY = 0;
        exitX = N - 2;
        exitY = M - 1;
        maze[entranceX][entranceY] = ROAD;
        maze[exitX][exitY] = ROAD;
    }

    public int N() {
        return N;
    }

    public int M() {
        return M;
    }

    public int getEntranceX() {
        return entranceX;
    }

    public int getEntranceY() {
        return entranceY;
    }

    public int getExitX() {
        return exitX;
    }

    public int getExitY() {
        return exitY;
    }

    public boolean inArea(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < M;
    }

    public void print() {
        System.out.println(N + " " + M);
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                System.out.print(maze[i][j]);
            }
            System.out.println();
        }
    }


}
