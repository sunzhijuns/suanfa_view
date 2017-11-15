public class ThreeGatesExperiment {
    private int N;

    public ThreeGatesExperiment(int n) {
        if (n <= 0){
            throw new IllegalArgumentException("n must be larger than 0!");
        }
        N = n;
    }

    public boolean play(boolean changeDoor){
        // 0  1  2
        int correctChoice = (int)(Math.random() * 3);
        int playerChoice = (int)(Math.random() * 3);
        if (playerChoice == correctChoice){
            return changeDoor ? false : true;
        }else{
            return changeDoor ? true : false;
        }
    }

    public void run(boolean changeDoor){
        int wins = 0;
        for (int i = 0; i < N; i++) {
            if (play(changeDoor)){
                wins++;
            }
        }
        System.out.println(changeDoor ? "Change" : "Not Change");
        System.out.println("winning rate: " + (double)wins/N);
    }
}
