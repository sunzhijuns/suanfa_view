import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AlgoVisualizer {
    private int DELAY = 2000;
    private long count = 0;

    private SelectionSortData data;   // 数据
    private AlgoFrame frame; //视图
    private int sceneWidth;
    private int sceneHeight;
    private long N;

    private boolean isAnimated = true;
    //  动画逻辑
    private void run() {
            // 绘制数据
//            if (!isAnimated) {
//                long startTime = System.currentTimeMillis();
//                long endTime = System.currentTimeMillis();
////                System.out.println("绘制耗时 : " + (endTime-startTime) + "ms" );
//            }
        frame.render(data);
        AlgoVisHelper.pause(DELAY);
        for (int i = 0; i < data.N(); i++) {
            int minIndex = i;
            for (int j = i + 1; j < data.N(); j++) {
                if (data.get(j) < data.get(minIndex)){
                    minIndex = j;
                }
            }
            data.swap(i, minIndex);
                frame.render(data);
                AlgoVisHelper.pause(DELAY);
        }
        frame.render(data);
        AlgoVisHelper.pause(DELAY);
    }

    public AlgoVisualizer(int sceneWidth, int sceneHeight, long N) {
        // 初始化数据
        this.N= N;
        this.sceneWidth = sceneWidth;
        this.sceneHeight = sceneHeight;
        data = new SelectionSortData((int)N, sceneHeight);
    }

    public void start() {
        // 初始化视图
        EventQueue.invokeLater(() -> {
            frame = new AlgoFrame("SelectionSort", sceneWidth, sceneHeight);
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
            System.out.println(e.getPoint());

        }
    }


    private class AlgoKeyListener extends KeyAdapter {
        @Override
        public void keyReleased(KeyEvent e) {
            if (e.getKeyChar() == ' '){
                isAnimated = !isAnimated;
            }

        }
    }
}
