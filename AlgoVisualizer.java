import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AlgoVisualizer {
    private int DELAY = 200;
    private long count = 0;

    private FractalData data;   // 数据
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
        setData();
    }

    private void setData() {
        frame.render(data);
        AlgoVisHelper.pause(DELAY);
    }

    public AlgoVisualizer(int depth) {
        // 初始化数据
        this.sceneWidth = (int)Math.pow(3,depth);
        this.sceneHeight = (int)Math.pow(3,depth);
        data = new FractalData(depth);
    }

    public void start() {
        // 初始化视图
        EventQueue.invokeLater(() -> {
            frame = new AlgoFrame("Fractal", sceneWidth, sceneHeight);
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
