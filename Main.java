public class Main {
    public static void main(String[] args){
        int N = 100000000;
        ThreeGatesExperiment exp = new ThreeGatesExperiment(N);
        exp.run(true);
        System.out.println();
        exp.run(false);
    }
}
