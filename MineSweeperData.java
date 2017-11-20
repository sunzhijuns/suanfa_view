public class MineSweeperData {
    public static final String blockImageURL = "resources/block.png";
    public static final String flagImageURL = "resources/flag.png";
    public static final String mineImageURL = "resources/mine.png";
    public static String numberImageURL(int num){
        if (num < 0 || num > 8){
            throw new IllegalArgumentException("No such a number image!");
        }
        return "resources/" + num + ".png";
    }
    private int N;
    private int M;
    private int mineNumber;
    private boolean[][] mines;

    private void generateMines(int mineNumber){
        for (int i = 0; i < mineNumber; i++) {
            int x = i / M;
            int y = i % M;
            mines[x][y] = true;
        }
        int swapTimes = M * N;
        for (int i = 0; i < swapTimes; i++) {
            int x1 = (int)(Math.random() * N);
            int y1 = (int)(Math.random() * M);
            int x2 = (int)(Math.random() * N);
            int y2 = (int)(Math.random() * M);
            swap(x1,y1,x2,y2);
        }
    }
    private void swap(int x1, int y1, int x2, int y2){
        boolean t = mines[x1][y1];
        mines[x1][y1] = mines[x2][y2];
        mines[x2][y2] = t;
    }
    public MineSweeperData(int n, int m, int mineNumber) {
        if (n <= 0 || m <= 0){
            throw new IllegalArgumentException("Mine sweeper size is invalid");
        }
        if (mineNumber < 0 || mineNumber > n * m){
            throw new IllegalArgumentException("Mine number is invalid");
        }
        N = n;
        M = m;
        this.mineNumber = mineNumber;
        mines = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                mines[i][j] = false;
            }
        }
        generateMines(mineNumber);
    }

    public int N(){ return N;}
    public int M(){ return M;}
    public boolean isInArea(int x, int y){
        return x >= 0 && x < N && y >= 0 && y < M;
    }
    public boolean isMine(int x, int y){
        if (!isInArea(x,y)){
            throw new IllegalArgumentException("Out of index in isMine function!");
        }
        return mines[x][y];
    }
}
