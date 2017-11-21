public class FractalData {
    private int depth;

    public FractalData(int depth) {
        this.depth = depth;
    }
    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        if (depth>=0){
            this.depth = depth;
        }
    }
}
