public class ShuffleExp {
    private int N;
    private int n;
    private int m;
    // N 测试次数 n 总格子数 m 雷的数量
    public ShuffleExp(int N, int n, int m){
        if (N <= 0){
            throw new IllegalArgumentException("N must be > 0");
        }
        if (n < m){
            throw new IllegalArgumentException("n must be >= m");
        }
        this.N = N;
        this.n = n;
        this.m = m;
    }
    private void reset(int arr[]){
        for (int i = 0; i < m; i++) {
            arr[i] = 1;
        }
        for (int i = m; i < n; i++) {
            arr[i] = 0;
        }

    }
    private void shuffle(int arr[]){
//        for (int i = 0; i < m; i++) {
//            int j = (int)(Math.random() * n);
//            swap(arr,i,j);
//        }
//        for (int i = 0; i < n; i++) {
//            int j = (int)(Math.random() * (n-i)) + i;
//            swap(arr,i,j);
//        }
        for (int i = n-1; i >= 0; i--) {
            int j = (int)(Math.random() * (i+1));
            swap(arr,i,j);
        }

//        for (int i = 0; i < n*5; i++) {
//            int j = (int)(Math.random() * n);
//            int k = (int)(Math.random() * n);
//            swap(arr,k,j);
//        }
    }
    private void swap(int arr[], int i, int j){
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }
    public void run(){
        int arr[] = new int[n];
        int freq[] = new int[n];
        for (int i = 0; i < N; i++) {
            reset(arr);
            shuffle(arr);
            for (int j = 0; j < n; j++) {
                freq[j] += arr[j];
            }
        }
        for (int i = 0; i < n; i++) {
            System.out.println((double) freq[i] / N);
        }
    }
    public static void main(String[] args) {
        int N = 1000000;
        int n = 10;
        int m = 5;
        ShuffleExp exp = new ShuffleExp(N,n,m);
        exp.run();
    }
}
