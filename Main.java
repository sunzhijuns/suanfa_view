public class Main {
    public static void main(String[] args) {
        int sceneWidth = 909;
        int sceneHeight = 909;
        String mazeFile = "maze_101_101.txt";
        AlgoVisualizer vis = new AlgoVisualizer(sceneWidth,sceneHeight,mazeFile);
        vis.start();

    }
}
