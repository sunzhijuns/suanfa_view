public class Position {
    private int x, y;
    private Position pre;
    public Position(int x, int y, Position pre) {
        this.x = x;
        this.y = y;
        this.pre = pre;
    }

    public Position getPre() {
        return pre;
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }
}
