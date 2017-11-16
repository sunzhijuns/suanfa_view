public class InsertionSortData {
    private int[] numbers;
    public int orderedIndex = -1;// [0 ... orderedIndex) 有序区间
    public int currentIndex = -1;// 正在处理的元素

    public InsertionSortData(int N, int randomBound) {
        numbers = new int[N];
        for (int i = 0; i < N; i++) {
            numbers[i] = (int)(Math.random() * randomBound) + 1;
//            numbers[i] = (int)((double)(i + 1)/N * randomBound);
//            numbers[i] = (int)((double)(N - i)/N * randomBound);
        }
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

    public void swap(int i, int j) {
        int t = numbers[i];
        numbers[i] = numbers[j];
        numbers[j] = t;
    }
}
