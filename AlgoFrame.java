import javax.swing.*;
import java.awt.*;

public class AlgoFrame extends JFrame {
    private int canvasWidth;
    private int canvasHeight;

    AlgoFrame(String title, int canvasWidth, int canvasHeight) {
        super(title);
        this.canvasWidth = canvasWidth;
        this.canvasHeight = canvasHeight;
        setVisible(true);
//        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        AlgoCanvas canvas = new AlgoCanvas();
        setContentPane(canvas);
        pack();
    }

    AlgoFrame(String title) {
        this(title, 1024, 768);
    }

    public int getCanvasWidth() {
        return canvasWidth;
    }

    public int getCanvasHeight() {
        return canvasHeight;
    }

    // 自己的数据
    private QuickSortData data;

    public void render(QuickSortData data) {
        this.data = data;
        repaint();
    }

    private class AlgoCanvas extends JPanel {
        public AlgoCanvas() {
            super(true);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            // 抗锯齿
            RenderingHints hints = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.addRenderingHints(hints);

            // 具体绘制
            int w = canvasWidth / data.N();
            for (int i = 0; i < data.N(); i++) {
                if (i >= data.l && i <= data.r) {
                    AlgoVisHelper.setColor(g2d, AlgoVisHelper.Green);
                } else {
                    AlgoVisHelper.setColor(g2d, AlgoVisHelper.Grey);
                }
                if (i == data.curPivot) {
                    AlgoVisHelper.setColor(g2d, AlgoVisHelper.Indigo);
                }
                if (i >= data.l + 1 && i <= data.curL) {
                    AlgoVisHelper.setColor(g2d, AlgoVisHelper.LightBlue);
                }
                if (i >= data.curR && i <= data.r) {
                    AlgoVisHelper.setColor(g2d, AlgoVisHelper.LightBlue);
                }
                if (data.fixedPivot[i]) {
                    AlgoVisHelper.setColor(g2d, AlgoVisHelper.Red);
                }


                AlgoVisHelper.fillRectangle(g2d, i * w, canvasHeight - data.get(i), w - 1, data.get(i));
            }
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(canvasWidth, canvasHeight);
        }
    }


}
