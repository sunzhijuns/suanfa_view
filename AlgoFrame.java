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
//            AlgoVisHelper.setStrokeWidth(g2d,6);
//            AlgoVisHelper.drawLine(g2d,0,100,400,100);
            drawFractal(g2d, 0, canvasHeight / 2, canvasWidth + 0, canvasHeight / 2, 0);

        }

        private void drawFractal(Graphics2D g, int x1, int y1, int x2, int y2, int depth) {
            double w = Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
            if (w <= 3) {
                AlgoVisHelper.setColor(g, AlgoVisHelper.Indigo);
                AlgoVisHelper.drawLine(g, x1, y1, x2, y2);
                return;
            }
            if (data.getDepth() == depth) {
                AlgoVisHelper.setColor(g, AlgoVisHelper.Indigo);
                AlgoVisHelper.drawLine(g, x1, y1, x2, y2);
                return;
            }

            double x12 = x2 - x1;
            double y12 = y2 - y1;

            int Ax = x1 + 1 * (x2 - x1) / 3;
            int Ay = y1 + 1 * (y2 - y1) / 3;
            int Bx = x1 + 2 * (x2 - x1) / 3;
            int By = y1 + 2 * (y2 - y1) / 3;

            int Cx = x1 + (x2 - x1) / 2 + (int) (-y12 * Math.sin(Math.PI / 2) / 3);
            int Cy = y1 + (y2 - y1) / 2 + (int) (x12 * Math.sin(Math.PI / 2) / 3);

            drawFractal(g, x1 + 0, y1 + 0, Ax + 0, Ay + 0, depth + 1);
            drawFractal(g, Ax + 0, Ay + 0, Cx + 0, Cy + 0, depth + 1);
            drawFractal(g, Cx + 0, Cy + 0, Bx + 0, By + 0, depth + 1);
            drawFractal(g, Bx + 0, By + 0, x2 + 0, y2 + 0, depth + 1);


        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(canvasWidth, canvasHeight);
        }
    }


}
