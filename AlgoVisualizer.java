

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Stack;

public class AlgoVisualizer {
    private static final int[][] d = new int[][]{{-1,0},{0,1},{1,0},{0,-1}};

    private int DELAY = 1000;
    private int blockSize = 16;

    private MazeData data;   // 数据
    private AlgoFrame frame; //视图
    private int sceneWidth;
    private int sceneHeight;

    private boolean isAnimated = true;

    //  动画逻辑
    private void run() {
        // 绘制数据
//            if (!isAnimated) {
//                long startTime = System.currentTimeMillis();
//                long endTime = System.currentTimeMillis();
////                System.out.println("绘制耗时 : " + (endTime-startTime) + "ms" );

        setData(-1,-1);
        go(data.getEntranceX(), data.getEntranceY() + 1);
        data.print();

        setData(-1,-1);

    }
    private void go(int x, int y){
        if (!data.inArea(x,y)){
            throw new IllegalArgumentException("out of area in go");
        }

        Stack<Position> stack = new Stack<Position>();
        stack.push(new Position(x,y,null));
        data.visited[x][y] = true;
        while (!stack.empty()){
            Position curPos = stack.pop();
            x = curPos.getX();
            y = curPos.getY();
            for (int i = 0; i < 4; i++) {
                int newX = x + d[i][0] * 2;
                int newY = y + d[i][1] * 2;
                if (data.inArea(newX, newY) && !data.visited[newX][newY]){
                    setData(x + d[i][0],y + d[i][1]);
                    stack.push(new Position(newX, newY, curPos));
                    data.visited[newX][newY] = true;
                }
            }
        }


    }

    private void setData(int x, int y) {
        if (data.inArea(x,y)){
            data.maze[x][y] = MazeData.ROAD;
        }
        frame.render(data);
        AlgoVisHelper.pause(DELAY);

    }

    public AlgoVisualizer(int N, int M) {
        // 初始化数据
        this.sceneWidth = blockSize * M;
        this.sceneHeight = blockSize * N;
        data = new MazeData(N, M);
    }

    public void start() {
        // 初始化视图
        EventQueue.invokeLater(() -> {
            frame = new AlgoFrame("Maze", sceneWidth, sceneHeight);
            frame.addKeyListener(new AlgoKeyListener());
            new Thread(() -> {
                run();
            }).start();
        });
    }

    private class AlgoMouseListener extends MouseAdapter {
        @Override
        public void mousePressed(MouseEvent e) {
            e.translatePoint(-9, -38);
            isAnimated = !isAnimated;
            System.out.println(e.getPoint());

        }
    }


    private class AlgoKeyListener extends KeyAdapter {
        @Override
        public void keyReleased(KeyEvent e) {
            if (e.getKeyChar() == ' ') {
                isAnimated = !isAnimated;
            }

        }
    }
}
