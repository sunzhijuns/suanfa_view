import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

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

    public int getCanvasWidth() {
        return canvasWidth;
    }

    public int getCanvasHeight() {
        return canvasHeight;
    }
    // 自己的数据
    private MonteCarloPiData data;

    public void render(MonteCarloPiData data) {
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
            Circle circle = data.getCircle();
            AlgoVisHelper.setStrokeWidth(g2d, 3);
            AlgoVisHelper.setColor(g2d, AlgoVisHelper.Blue);
            AlgoVisHelper.strokeCircle(g2d, circle.getX(),circle.getY(), circle.getR());
            for (int i = 0; i < data.getPointsNumber(); i++) {
                Point p = data.getPoint(i);
                if (circle.contain(p)){
                    AlgoVisHelper.setColor(g2d,AlgoVisHelper.Red);
                }
                else{
                    AlgoVisHelper.setColor(g2d,AlgoVisHelper.Green);
                }
                AlgoVisHelper.fillCircle(g2d, p.x, p.y, 3);
            }
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(canvasWidth, canvasHeight);
        }
    }
}
