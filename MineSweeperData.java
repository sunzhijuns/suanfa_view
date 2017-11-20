import java.util.LinkedList;

public class MineSweeperData {
    public static final String blockImageURL = "resources/block.png";
    public static final String flagImageURL = "resources/flag.png";
    public static final String mineImageURL = "resources/mine.png";

    public static String numberImageURL(int num) {
        if (num < 0 || num > 8) {
            throw new IllegalArgumentException("No such a number image!");
        }
        return "resources/" + num + ".png";
    }

    private int N;
    private int M;
    private int mineNumber;
    private boolean[][] mines;
    private int[][] numbers;
    public boolean[][] open;
    public boolean[][] flags;


    private void generateMines(int mineNumber) {
        for (int i = 0; i < mineNumber; i++) {
            int x = i / M;
            int y = i % M;
            mines[x][y] = true;
        }
        for (int i = N * M - 1; i >= 0; i--) {
            int iX = i / M;
            int iY = i % M;
            int randomNumber = (int) (Math.random() * (i + 1));
            int randX = randomNumber / M;
            int randY = randomNumber % M;
            swap(iX, iY, randX, randY);
        }
    }

    private void swap(int x1, int y1, int x2, int y2) {
        boolean t = mines[x1][y1];
        mines[x1][y1] = mines[x2][y2];
        mines[x2][y2] = t;
    }

    public MineSweeperData(int n, int m, int mineNumber) {
        if (n <= 0 || m <= 0) {
            throw new IllegalArgumentException("Mine sweeper size is invalid");
        }
        if (mineNumber < 0 || mineNumber > n * m) {
            throw new IllegalArgumentException("Mine number is invalid");
        }
        N = n;
        M = m;
        this.mineNumber = mineNumber;
        mines = new boolean[N][M];
        open = new boolean[N][M];
        flags = new boolean[N][M];
        numbers = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                mines[i][j] = false;
                open[i][j] = false;
                flags[i][j] = false;
                numbers[i][j] = 0;
            }
        }
        generateMines(mineNumber);
        calculateNumbers();
    }

    public int N() {
        return N;
    }

    public int M() {
        return M;
    }

    public int getNumber(int i, int j) {
        if (!isInArea(i,j)){
            throw new IllegalArgumentException("Out of index in getNumber function!");
        }
        return numbers[i][j];
    }

    public boolean isInArea(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < M;
    }

    public boolean isMine(int x, int y) {
        if (!isInArea(x, y)) {
            throw new IllegalArgumentException("Out of index in isMine function!");
        }
        return mines[x][y];
    }
    public void open(int x, int y){
        if (!isInArea(x,y)){
            throw new IllegalArgumentException("Out of index in open function!");
        }
        if (mines[x][y]){
            throw new IllegalArgumentException("Have mine open function!");
        }
        LinkedList<Position> queue = new LinkedList<Position>();
        if (numbers[x][y] == 0){
            queue.addLast(new Position(x,y));
        }
        open[x][y] = true;

        while (!queue.isEmpty()){
            Position pos = queue.removeFirst();
            x = pos.getX();
            y = pos.getY();
            for (int i = x-1; i <= x+1; i++) {
                for (int j = y-1; j <= y+1; j++) {
                    int newX = i;
                    int newY = j;
                    if (isInArea(newX,newY)) {
                        if(!open[newX][newY] && numbers[newX][newY] == 0){
                            queue.addLast(new Position(newX, newY));
                        }
                        open[newX][newY] = true;
                    }
                }
            }
        }
    }

    private void calculateNumbers() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (mines[i][j]) {
                    numbers[i][j] = -1;
                } else {
                    numbers[i][j] = 0;
                    for (int k1 = i - 1; k1 <= i + 1; k1++)
                        for (int k2 = j - 1; k2 <= j + 1; k2++)
                            if (isInArea(k1, k2) && mines[k1][k2])
                                numbers[i][j]++;

                }
            }
        }
    }
}
