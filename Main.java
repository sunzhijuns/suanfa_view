

import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String[] args) {
	// write your code here
        System.out.println(1111);
        int sceneWidth = 1000;
        int sceneHeight = 1200;
        int N = 100;

        AlgoVisualizer algoVisualizer = new AlgoVisualizer(sceneWidth, sceneHeight, N);
        algoVisualizer.start();



    }
}
