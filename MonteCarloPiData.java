import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class MonteCarloPiData {
    private Circle circle;
    private int insideCircle = 0;
    private ArrayList<Double> result;
    private LinkedList<Point> points;

    public MonteCarloPiData(Circle circle) {
        this.circle = circle;
        points = new LinkedList<Point>();
        result = new ArrayList<Double>();
    }

    public Circle getCircle() {
        return circle;
    }

    public Point getPoint(int i) {
        return points.get(i);
    }
    public int getPointsNumber(){
        return points.size();
    }

    public void addPoint(Point p){
        points.add(p);
        if (circle.contain(p)){
            insideCircle++;
        }
        if (insideCircle > 10000 && points.size() % 100 == 0){
            result.add(estimatePi());
        }
    }
    public double meanPi(){
        double sum = 0.0;
        if (result.size() == 0){
            return sum;
        }
        for (int i = 0; i < result.size(); i++) {
            sum+=result.get(i);
        }
        return sum/result.size();
    }

    public double estimatePi(){
        if (points.size() == 0){
            return 0.0;
        }
        int circleArea = insideCircle;
        int squareArea = points.size();
        return (double)circleArea*4/squareArea;
    }

}
