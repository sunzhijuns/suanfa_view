import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;

public class AlgoVisualizer {
    private int DELAY = 40;
    private long count = 0;
    private int[] money;     // 数据
    private AlgoFrame frame; //视图
    private int sceneWidth;
    private int sceneHeight;

    private boolean isAnimated = true;
    //  动画逻辑
    private void run() {
        while (true) {
            // 绘制数据
            frame.render(money);
            AlgoVisHelper.pause(DELAY);
            if (isAnimated) {
                // 更新
                long startTime = System.currentTimeMillis();
                for (int k = 0; k < 100; k++) {
                    for (int i = 0; i < money.length; i++) {
//                        if (money[i] > 0){
                            int j = (int)(Math.random() * money.length);
                            money[i] -= 1;
                            money[j] += 1;
//                        }
                    }
                }
                count += 50;
//                Arrays.sort(money);
                long endTime = System.currentTimeMillis();
                System.out.println("第" + count +"次更新， 更新需要, " + (endTime-startTime) + "ms");

            }
        }
    }

    public AlgoVisualizer(int sceneWidth, int sceneHeight, int N) {
        this.sceneWidth = sceneWidth;
        this.sceneHeight = sceneHeight;
        money = new int[N];
        for (int i = 0; i < N; i++) {
            money[i] = 100;
        }
    }

    public void start() {

        EventQueue.invokeLater(() -> {
            frame = new AlgoFrame("Welcome", sceneWidth, sceneHeight);
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
