import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AlgoVisualizer {
    private int DELAY = 1000;
    private long count = 0;

    private HeapSortData data;   // 数据
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
        setData(data.N(), -1);
        for (int i = (data.N() - 1 - 1) / 2; i >= 0; i--) {
            shiftDown(data.N(), i);
        }
        for (int i =  data.N() - 1; i >0; i--) {
            data.swap(0, i);
            shiftDown(i, 0);
            setData(i,-1);

        }
        setData(0,-1);
    }
    private void shiftDown(int n, int i){
        setData(n,i);
        while(2*i + 1 < n){
            int j = 2*i+1;
            setData(n,j);
            if (2*i+2 < n){
                if (data.get(2*i+2) >= data.get(2*i + 1)){
                    j = 2*i+2;
                    setData(n,j);
                }
            }
            if (data.get(i) >= data.get(j)){
                setData(n,i);
                break;
            }
            setData(n,i);
            data.swap(i,j);
            setData(n,i);
            i = j;

        }


    }
    private void setData(int heapIndex, int curIndex) {
        data.heapIndex = heapIndex;
        data.curIndex = curIndex;
        frame.render(data);
        AlgoVisHelper.pause(DELAY);
    }
    public AlgoVisualizer(int sceneWidth, int sceneHeight, long N){
        this(sceneWidth, sceneHeight, N, HeapSortData.Type.Default);
    }

    public AlgoVisualizer(int sceneWidth, int sceneHeight, long N, HeapSortData.Type dataType) {
        // 初始化数据
        this.N = N;
        this.sceneWidth = sceneWidth;
        this.sceneHeight = sceneHeight;
        data = new HeapSortData((int) N, sceneHeight, dataType);
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
