

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class AlgoVisualizer {
    private static final int[][] d = new int[][]{{-1,0},{0,1},{1,0},{0,-1}};

    private int DELAY = 10;
    private int blockSize = 8;

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



        setData();

    }

    private void setData() {
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
