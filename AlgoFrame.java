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
            drawFractal(g2d, 0, canvasHeight, canvasWidth, 0);

        }

        private void drawFractal(Graphics2D g, int Ax, int Ay, int side, int depth) {
            if (side <= 1) {
                AlgoVisHelper.setColor(g, AlgoVisHelper.Indigo);
                AlgoVisHelper.fillRectangle(g, Ax, Ay, 1, 1);
                return;
            }
            if (data.getDepth() == depth) {
                AlgoVisHelper.setColor(g, AlgoVisHelper.Indigo);
                int Bx = Ax + side;
                int By = Ay;
                int Cx = Ax + side / 2;
                int Cy = Ay - (int) (side * Math.sin(Math.PI / 3));
                AlgoVisHelper.fillTriangle(g, Ax, Ay, Bx, By, Cx, Cy);
                return;
            }

            int Bx = Ax + side;
            int By = Ay;
            int Cx = Ax + side / 2;
            int Cy = Ay - (int) (side * Math.sin(Math.PI / 3));
            side /= 2;

            drawFractal(g, Ax + 0, Ay + 0, side + 0, depth + 1);
            drawFractal(g, (Ax + Bx) / 2, (Ay + By) / 2, side + 0, depth + 1);
            drawFractal(g, (Ax + Cx) / 2, (Ay + Cy) / 2, side + 0, depth + 1);


        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(canvasWidth, canvasHeight);
        }
    }


}
