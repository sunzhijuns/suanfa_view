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
    private FractalData data;

    public void render(FractalData data) {
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
            drawFractal(g2d, 0, 0, canvasWidth, canvasHeight, 0);

        }

        private void drawFractal(Graphics2D g, int x, int y, int w, int h, int depth) {
            if (data.getDepth() == depth) {
                AlgoVisHelper.setColor(g, AlgoVisHelper.Indigo);
                AlgoVisHelper.fillRectangle(g, x, y, w, h);
//                System.out.println("==depth");
                return;
            }
            if (w <= 1 || h <= 1) {
                AlgoVisHelper.setColor(g, AlgoVisHelper.Indigo);
                AlgoVisHelper.fillRectangle(g, x, y, Math.max(w, 1), Math.max(h, 1));
                System.out.println("<1");
                return;
            }

            int w_3 = w / 3;
            int h_3 = h / 3;
//            drawFractal(g, x + w_3 * 0, y + h_3 * 0, w_3, h_3, depth + 1);
//            drawFractal(g, x + w_3 * 2, y + h_3 * 0, w_3, h_3, depth + 1);
//            drawFractal(g, x + w_3 * 1, y + h_3 * 1, w_3, h_3, depth + 1);
//            drawFractal(g, x + w_3 * 0, y + h_3 * 2, w_3, h_3, depth + 1);
//            drawFractal(g, x + w_3 * 2, y + h_3 * 2, w_3, h_3, depth + 1);

            drawFractal(g, x + w_3 * 1, y + h_3 * 0, w_3, h_3, depth + 1);
            drawFractal(g, x + w_3 * 0, y + h_3 * 1, w_3, h_3, depth + 1);
            drawFractal(g, x + w_3 * 1, y + h_3 * 1, w_3, h_3, depth + 1);
            drawFractal(g, x + w_3 * 2, y + h_3 * 1, w_3, h_3, depth + 1);
            drawFractal(g, x + w_3 * 1, y + h_3 * 2, w_3, h_3, depth + 1);

        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(canvasWidth, canvasHeight);
        }
    }


}
