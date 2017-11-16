import java.util.Arrays;

public class InsertionSortData {
    private int[] numbers;
    public int orderedIndex = -1;// [0 ... orderedIndex) 有序区间
    public int currentIndex = -1;// 正在处理的元素

    public enum Type{
        Default,
        NearlyOrdered
    }

    public InsertionSortData(int N, int randomBound, Type dataType) {
        numbers = new int[N];
        for (int i = 0; i < N; i++) {
            numbers[i] = (int)(Math.random() * randomBound) + 1;
//            numbers[i] = (int)((double)(i + 1)/N * randomBound);
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
    public InsertionSortData(int N, int randomBound){
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
