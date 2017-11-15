public class WinningPrize {
    private double chance;
    private int playTime;
    private int N;

    public WinningPrize(double chance, int playTime, int n) {
        this.chance = chance;
        this.playTime = playTime;
        N = n;
    }
    public void run(){
        int wins = 0;
        for (int i = 0; i < N; i++) {
            if (play()){
                wins++;
            }
        }
        System.out.println("winning rate:" + (double)wins/N);
    }
    public boolean play(){
        for (int i = 0; i < playTime; i++) {
            if (Math.random() < chance){
                return true;
            }
        }
        return false;

    }
}
