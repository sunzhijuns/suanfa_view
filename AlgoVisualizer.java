import javafx.geometry.Pos;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Stack;

public class AlgoVisualizer {
    private static final int[][] d = new int[][]{{-1,0},{0,1},{1,0},{0,-1}};

    private int DELAY = 1;

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

        setData(-1,-1,false);
        Stack<Position> stack = new Stack<Position>();
        stack.push(new Position(data.getEntranceX(), data.getEntranceY(), null));
        data.visited[data.getEntranceX()][data.getEntranceY()] = true;
        while (!stack.empty()){
            Position pos = stack.pop();

            setData(pos.getX(), pos.getY() ,true);
            if (pos.getX() == data.getExitX() && pos.getY() == data.getExitY()){
                System.out.println("ok");
                findPath(pos);
                break;
            }
            for (int i = 0; i < 4; i++) {
                int newX = pos.getX() + d[i][0];
                int newY = pos.getY() + d[i][1];
                if (data.inArea(newX, newY) &&
                        !data.visited[newX][newY] &&
                        data.getMaze(newX, newY) == MazeData.ROAD){
                    stack.push(new Position(newX,newY, pos));
                    data.visited[newX][newY] = true;

                }
            }

        }
        setData(-1,-1,false);

    }
    private void findPath(Position cur){
        data.result[cur.getX()][cur.getY()] = true;
        while (cur.getPre() != null){
            cur = cur.getPre();
            data.result[cur.getX()][cur.getY()] = true;

        }

    }
    private void setData(int x, int y, boolean isPath) {
        if (data.inArea(x,y)){
            data.path[x][y] = isPath;
        }

        frame.render(data);
        AlgoVisHelper.pause(DELAY);

    }

    public AlgoVisualizer(int sceneWidth, int sceneHeight, String mazeFile) {
        // 初始化数据
        this.sceneWidth = sceneWidth;
        this.sceneHeight = sceneHeight;
        data = new MazeData(mazeFile);
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
