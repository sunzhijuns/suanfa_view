import java.util.Arrays;

public class MergeSort {
    // 算法类不允许产生任何实例
    private MergeSort(){}
    // 将arr[l ... mid] 和 arr[mid+1 ... r] 两部分归并
    private static void merge(Comparable[] arr, Comparable[] aux, int l, int mid, int r){
        System.arraycopy(arr, l, aux, l, r-l+1);
        // i指向左半部分l， j 指向右半部分mid+1;
        int i = l,j=mid+1;
        for (int k = l; k <= r ; k++) {
            if (i > mid){ // 左半部分处理完了
                arr[k] = aux[j]; j ++;
            }else if (j > r){ // 右半部分处理完了
                arr[k] = aux[i]; i++;
            }else if (aux[i].compareTo(aux[j]) <= 0){ // 左边<=右边
                arr[k] = aux[i]; i++;
            }else{
                arr[k] = aux[j]; j ++;
            }
        }
    }
    // 递归，对arr[l ... r] 进行归并排序
    private static void sort(Comparable[] arr,Comparable[] aux, int l, int r, int depth){
        System.out.print(repeatCharacters('-', depth * 2));
        System.out.println("Deal with [" + l + ", " + r + "]");
        if (l >= r){
            return ;
        }
        int mid = l +  (r - l) / 2;
        sort(arr,aux,    l,     mid, depth + 1);
        sort(arr,aux,  mid+1, r,   depth + 1);
        merge(arr, aux ,l, mid, r);
        System.out.println(repeatCharacters('-', depth * 2));
    }

    private static String repeatCharacters(char character, int length){
        StringBuilder s = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            s.append(character);
        }
        return s.toString();
    }
    public static void sort(Comparable[] arr){
        int n = arr.length;
        Comparable[] aux = new Comparable[n];
        sort(arr,aux, 0, n-1, 0);
    }

    public static void main(String[] args){
        int N = 7;
        Integer[] arr = new Integer[N];
        for (int i = 0; i < N; i++) {
            arr[i] = new Integer(N - i);
        }
        MergeSort.sort(arr);

    }


}
