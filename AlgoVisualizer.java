import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedList;

public class AlgoVisualizer {
    private int DELAY = 100;
    private long count = 0;
    private int blockSize = 32;

    private MineSweeperData data;   // 数据
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
//            }
        setData(false, -1, -1);

    }

    private void open(int x, int y){
        if (!data.isInArea(x,y)){
            throw new IllegalArgumentException("Out of index in open function!");
        }
        if (data.isMine(x,y)){
            throw new IllegalArgumentException("Have mine open function!");
        }
        LinkedList<Position> queue = new LinkedList<Position>();
        if (data.getNumber(x,y) == 0){
            queue.addLast(new Position(x,y));
        }
        data.open[x][y] = true;
        setData(false, -1,-1);
        while (!queue.isEmpty()){
            Position pos = queue.removeFirst();
            x = pos.getX();
            y = pos.getY();
            for (int i = x-1; i <= x+1; i++) {
                for (int j = y-1; j <= y+1; j++) {
                    int newX = i;
                    int newY = j;
                    if (data.isInArea(newX,newY)) {
                        if(!data.open[newX][newY] && data.getNumber(newX,newY) == 0){
                            queue.addLast(new Position(newX, newY));
                        }
                        data.open[newX][newY] = true;
                        setData(false, -1,-1);
                    }
                }
            }
        }
    }

    private void setData(boolean isLeftClicked, int x , int y) {
        if (data.isInArea(x,y)){
            if (isLeftClicked){
                if(!data.flags[x][y]){
                    if (data.isMine(x,y)){
                        System.out.println("Game Over");
                        data.open[x][y] = true;
                    }else{
                        open(x,y);
                    }
                }
            }else{
                data.flags[x][y] = !data.flags[x][y];
            }
        }
        frame.render(data);
        AlgoVisHelper.pause(DELAY);
    }

    public AlgoVisualizer(int N, int M, int mineNumber) {
        // 初始化数据
        this.sceneWidth = M * blockSize;
        this.sceneHeight = N * blockSize;
        data = new MineSweeperData(N, M, mineNumber);
    }

    public void start() {
        // 初始化视图
        EventQueue.invokeLater(() -> {
            frame = new AlgoFrame("Mine Sweeper", sceneWidth, sceneHeight);
            frame.addKeyListener(new AlgoKeyListener());
            frame.addMouseListener(new AlgoMouseListener());
            new Thread(() -> {
                run();
            }).start();
        });
    }

    private class AlgoMouseListener extends MouseAdapter {
        @Override
        public void mouseReleased(MouseEvent e) {
            e.translatePoint(-9, -38);
            System.out.println(e.getPoint());
            Point pos = e.getPoint();
            int w = frame.getCanvasWidth() / data.M();
            int h = frame.getCanvasHeight() / data.N();

            int x = pos.y / h;
            int y = pos.x / w;


            new Thread(() -> {
                if (SwingUtilities.isLeftMouseButton(e)) {
                    setData(true, x,y);
                } else {
                    setData(false, x,y);
                }
            }).start();

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
