public class Main {
    public static void main(String[] args) {
//        int sceneWidth = 1200;
//        int sceneHeight = 800;
//        int N = 25;
////        AlgoVisualizer vis = new AlgoVisualizer(sceneWidth,sceneHeight,N);
//        AlgoVisualizer vis = new AlgoVisualizer(sceneWidth,sceneHeight,N, HeapSortData.Type.Identical);
//        vis.start();
        String mazeFile = "maze_101_101.txt";
        MazeData data = new MazeData(mazeFile);
        data.print();


    }
}
