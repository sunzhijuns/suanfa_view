public class Main {
    public static void main(String[] args) {
        int sceneWidth = 1200;
        int sceneHeight = 800;
        int N = 200;
        AlgoVisualizer vis = new AlgoVisualizer(sceneWidth,sceneHeight,N, InsertionSortData.Type.NearlyOrdered);
        vis.start();


    }
}
