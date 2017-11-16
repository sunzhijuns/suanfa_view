import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AlgoVisualizer {
    private int DELAY = 5;
    private long count = 0;

    private QuickSortData data;   // 数据
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
        setData(-1,-1,-1,-1,-1);
        quickSort(0, data.N() - 1);
        setData(-1,-1,-1,-1,-1);
    }
    private void quickSort(int l, int r){
        if (l > r){
            return;
        }
        if (l==r){
            setData(l,r,l,-1,-1);
            return;
        }
        setData(l,r,-1,-1,-1);
        int q = partition(l, r);
        quickSort(l, q - 1);
        quickSort(q + 1, r);
    }
    private int partition(int l, int r){
        int e = data.get(l);
        int j = l;
        setData(l,r,-1,l,-1);
        for (int i = l+1; i < r+1; i++) {
            setData(l,r,-1,l,i);
            if (e > data.get(i)){
                j++;
                data.swap(i,j);
                setData(l,r,-1,l,i);
            }
        }
        data.swap(l, j);
        setData(l, r, j, -1,-1);
        return j;
    }

    private void setData(int l, int r, int fixedPivot, int curPivot, int curElement) {
        data.l = l;
        data.r = r;
        if (fixedPivot >= 0){
            data.fixedPivot[fixedPivot] = true;
        }
        data.curPivot = curPivot;
        data.curElement = curElement;
        frame.render(data);
        AlgoVisHelper.pause(DELAY);
    }
    public AlgoVisualizer(int sceneWidth, int sceneHeight, long N){
        this(sceneWidth, sceneHeight, N, QuickSortData.Type.Default);
    }

    public AlgoVisualizer(int sceneWidth, int sceneHeight, long N, QuickSortData.Type dataType) {
        // 初始化数据
        this.N = N;
        this.sceneWidth = sceneWidth;
        this.sceneHeight = sceneHeight;
        data = new QuickSortData((int) N, sceneHeight, dataType);
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
