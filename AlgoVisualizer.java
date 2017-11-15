import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;
import java.util.LinkedList;

public class AlgoVisualizer {
    private int DELAY = 40;
    private long count = 0;

    MonteCarloPiData data;    // 数据
    private AlgoFrame frame; //视图
    private int sceneWidth;
    private int sceneHeight;
    private long N;

    private boolean isAnimated = true;
    //  动画逻辑
    private void run() {

        while (true) {
            // 绘制数据

            if (!isAnimated){
                long startTime = System.currentTimeMillis();
                frame.render(data);
                long endTime = System.currentTimeMillis();
//                System.out.println("绘制耗时 : " + (endTime-startTime) + "ms" );
            }
            AlgoVisHelper.pause(DELAY);



            if (isAnimated){

                if (data.getPointsNumber() < N){
                    long startTime = System.currentTimeMillis();
                    for (int i = 0; i < 100; i++) {
                        int x = (int)(Math.random() * frame.getCanvasWidth());
                        int y = (int)(Math.random() * frame.getCanvasHeight());
                        Point p = new Point(x, y);
                        data.addPoint(p);
                    }

                    System.out.println("加入" + data.getPointsNumber() + "个点后，PI的估计值为:" + data.estimatePi());
                    long endTime = System.currentTimeMillis();
                    System.out.println("更新耗时 : " + (endTime-startTime) + "ms" );
                }


            }


        }
    }

    public AlgoVisualizer(int sceneWidth, int sceneHeight, long N) {
        if (sceneWidth != sceneHeight){
            throw new IllegalArgumentException("this demo must be run in a squre window");
        }
        // 初始化数据
        this.N= N;
        this.sceneWidth = sceneWidth;
        this.sceneHeight = sceneHeight;
        Circle circle = new Circle(sceneWidth/2, sceneHeight/2, sceneHeight/2);
        data = new MonteCarloPiData(circle);

        // 初始化视图
        EventQueue.invokeLater(() -> {
            frame = new AlgoFrame("Get PI", sceneWidth, sceneHeight);
            frame.addKeyListener(new AlgoKeyListener());
            new Thread(() -> {
                run();
            }).start();
        });
    }

    public void start() {

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
