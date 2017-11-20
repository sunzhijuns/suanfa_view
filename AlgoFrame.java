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
    private MineSweeperData data;

    public void render(MineSweeperData data) {
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
            int w = canvasWidth/data.M();
            int h = canvasHeight/data.N();
            for (int i = 0; i < data.N(); i++) {
                for (int j = 0; j < data.M(); j++) {
                    if (data.isMine(i,j)){
                        AlgoVisHelper.putImage(g2d,j*w,i*h,MineSweeperData.mineImageURL);

                    }else{
                        AlgoVisHelper.putImage(g2d,j*w,i*h,MineSweeperData.blockImageURL);
                    }
                }
            }
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(canvasWidth, canvasHeight);
        }
    }


}
