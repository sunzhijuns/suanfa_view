import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class MonteCarloPiData {
    private Circle circle;
    private int insideCircle = 0;
    private Points points;

    public MonteCarloPiData(Circle circle) {
        this.circle = circle;
        points = new Points();
    }

    public Circle getCircle() {
        return circle;
    }
    public int getPointsNumber(){
        return points.size();
    }

    public void addPoint(Point p){
        points.add(p);
        if (circle.contain(p)){
            insideCircle++;
        }
    }

    public double estimatePi(){
        if (points.size() == 0){
            return 0.0;
        }
        int circleArea = insideCircle;
        int squareArea = points.size();
        return (double)circleArea*4/squareArea;
    }


    class Points{
        Points(){
        }
        private int count = 0;
        public int size(){
            return count;
        }
        public void add(Point p){
            count++;
        }

    }

}
