public class Main {
    public static void main(String[] args){
        int N = 1000000000;
        double chance = 0.2;
        int playTime = 5;
        WinningPrize exp = new WinningPrize(chance, playTime, N);
        exp.run();

    }
}
