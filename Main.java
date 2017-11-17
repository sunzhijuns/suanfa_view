public class Main {
    public static void main(String[] args) {
        int sceneWidth = 1200;
        int sceneHeight = 800;
        int N = 10;
//        AlgoVisualizer vis = new AlgoVisualizer(sceneWidth,sceneHeight,N);
        AlgoVisualizer vis = new AlgoVisualizer(sceneWidth,sceneHeight,N, HeapSortData.Type.Identical);
        vis.start();


    }
}
