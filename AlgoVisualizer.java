import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AlgoVisualizer {
    private int DELAY = 200;
    private long count = 0;

    private InsertionSortData data;   // 数据
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
        setData(0, -1);
        for (int i = 0; i < data.N(); i++) {
            int e = data.get(i);
            int j = i;
            setData(i, i);
            for (; j > 0 && data.get(j - 1) > e; j--) {
                data.set(j, data.get(j - 1));
                setData(i + 1, j-1);

            }
            data.set(j, e);
            setData(i + 1, j);
        }
        setData(data.N(), -1);
    }

    private void setData(int orderedIndex, int currentIndex) {
        data.orderedIndex = orderedIndex;
        data.currentIndex = currentIndex;
        frame.render(data);
        AlgoVisHelper.pause(DELAY);
    }
    public AlgoVisualizer(int sceneWidth, int sceneHeight, long N){
        this(sceneWidth, sceneHeight, N, InsertionSortData.Type.Default);
    }

    public AlgoVisualizer(int sceneWidth, int sceneHeight, long N, InsertionSortData.Type dataType) {
        // 初始化数据
        this.N = N;
        this.sceneWidth = sceneWidth;
        this.sceneHeight = sceneHeight;
        data = new InsertionSortData((int) N, sceneHeight, dataType);
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
