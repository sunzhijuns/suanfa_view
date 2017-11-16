

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;

public class AlgoVisualizer {
    private int DELAY = 40;
    private long count = 0;

    private MergeSortData data;   // 数据
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
        setData(-1, -1, -1);
//        mergeSort(0, data.N() - 1);
        for (int sz = 1; sz < data.N(); sz *= 2) {
            for (int i = 0; i < data.N() - sz; i+= sz + sz) {
                // [i, i+sz-1], [i+sz, i+sz + sz -1]
                merge(i, i + sz - 1, Math.min(i + sz + sz-1 , data.N() - 1));
            }
        }
        setData(0,data.N()-1,data.N()-1);
    }

    private void mergeSort(int l, int r){

        if (l >= r){
            return;
        }
        setData(l, r, -1);

        int mid = l + (r - l)/2;
        mergeSort(l, mid);
        mergeSort(mid + 1, r);
        merge(l, mid, r);
    }
    // [l, mid], [mid+1, r]
    private void merge(int l, int mid, int r){
        int[] aux = Arrays.copyOfRange(data.numbers, l, r + 1);
        int i = l, j = mid + 1;
        setData(l, r, -1);
        for (int k = l; k < r + 1; k++) {

            if (i > mid){ // 左边完了
                data.numbers[k] = aux[j-l]; j++;
            }else if (j > r){ // 右边完了
                data.numbers[k] = aux[i-l]; i++;
            }else if (aux[i-l] <= aux[j-l]){
                data.numbers[k] = aux[i-l]; i++;
            }else{
                data.numbers[k] = aux[j-l]; j++;
            }
            setData(l, r, k);
        }
    }
    private void setData(int l, int r, int mergeIndex) {
        data.l = l;
        data.r = r;
        data.mergeIndex = mergeIndex;
        frame.render(data);
        AlgoVisHelper.pause(DELAY);
    }

    public AlgoVisualizer(int sceneWidth, int sceneHeight, long N) {
        // 初始化数据
        this.N = N;
        this.sceneWidth = sceneWidth;
        this.sceneHeight = sceneHeight;
        data = new MergeSortData((int)N, sceneHeight);
    }

    public void start() {
        // 初始化视图
        EventQueue.invokeLater(() -> {
            frame = new AlgoFrame("MergeSort", sceneWidth, sceneHeight);
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
