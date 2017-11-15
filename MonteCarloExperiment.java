import java.awt.*;

public class MonteCarloExperiment {
    private int squareSize;
    private int N;
    private int outputInterval = 10000;

    public void setOutputInterval(int outputInterval) {
        if (outputInterval <= 0){
            throw new IllegalArgumentException("outputInterval must be larger than zero");
        }
        this.outputInterval = outputInterval;
    }

    public MonteCarloExperiment(int squareSize, int n) {
        if (squareSize <= 0 || n <= 0) {
            throw new IllegalArgumentException("squareSize and n must larger than zero");
        }
        this.squareSize = squareSize;
        N = n;
    }

    public void run() {
        Circle circle = new Circle(squareSize / 2, squareSize / 2, squareSize / 2);
        MonteCarloPiData data = new MonteCarloPiData(circle);
        double sum = 0;
        int count = 0;
        for (int i = 0; i < N; i++) {
            if (i >= outputInterval && i % outputInterval == 0) {
                double estimatePi = data.estimatePi();
                sum += data.estimatePi();
                count++;
                System.out.println("平均值："+ sum/count + ";"+"第" + i + "次的估计值：" + estimatePi );

            }
            int x = (int) (Math.random() * squareSize);
            int y = (int) (Math.random() * squareSize);
            data.addPoint(new Point(x, y));

        }
    }

    public static void main(String[] args) {
        int squareSize = 800;
        int N = 1000000;
        MonteCarloExperiment exp = new MonteCarloExperiment(squareSize, N);
        exp.setOutputInterval(10000);
        exp.run();

    }
}
