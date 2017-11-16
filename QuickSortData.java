import java.util.Arrays;

public class QuickSortData {
    private int[] numbers;
    public int l, r;
    public int curPivot;
    public int curElement;
    public boolean[] fixedPivot;

    public enum Type{
        Default,
        NearlyOrdered
    }

    public QuickSortData(int N, int randomBound, Type dataType) {
        numbers = new int[N];
        fixedPivot = new boolean[N];
        for (int i = 0; i < N; i++) {
            fixedPivot[i] = false;
//            numbers[i] = (int)(Math.random() * randomBound) + 1;
            numbers[i] = (int)((double)(i + 1)/N * randomBound);
//            numbers[i] = (int)((double)(N - i)/N * randomBound);
        }
        if (dataType == Type.NearlyOrdered){
            Arrays.sort(numbers);
            int swapTime = (int)(N * 0.02);
            for (int i = 0; i < swapTime; i++) {
                int a = (int)(Math.random() * N);
                int b = (int)(Math.random() * N);
                swap(a,b);
            }
        }
    }
    public QuickSortData(int N, int randomBound){
        this(N, randomBound, Type.Default);
    }

    public int N() {
        return numbers.length;
    }

    public int get(int index) {
        if (index < 0 || index >= numbers.length) {
            throw new IllegalArgumentException("Invalid index to access Sort Data");
        }
        return numbers[index];
    }
    public void set(int index, int value){
        if (index < 0 || index >= numbers.length) {
            throw new IllegalArgumentException("Invalid index to access Sort Data");
        }
        numbers[index] = value;
    }

    public void swap(int i, int j) {
        int t = numbers[i];
        numbers[i] = numbers[j];
        numbers[j] = t;
    }
}
