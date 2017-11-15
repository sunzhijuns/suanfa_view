

import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String[] args) {
	// write your code here
        System.out.println(1111);
        int sceneWidth = 800;
        int sceneHeight = 800;
        int N = 2000000;

        AlgoVisualizer algoVisualizer = new AlgoVisualizer(sceneWidth, sceneHeight, N);
        algoVisualizer.start();



    }
}
