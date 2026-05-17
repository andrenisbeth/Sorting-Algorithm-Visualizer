public class Quicksort {
   
    public static void sort(int[] arr) {
        qs(arr, 0, arr.length - 1);
    }

    private static void qs(int[] arr, int lo, int hi) {
        if (lo >= hi) return;
        int p = partition(arr, lo, hi);
        qs(arr, lo, p-1);
        qs(arr, p+1, hi);
    }

    private static int partition(int[] arr, int lo, int hi) {
        int pivot = arr[hi];
        int i = lo-1;

        for (int j = lo; j < hi; j++) {
            if (arr[j] <= pivot) {
                i++;
                int tmp = arr[i]; arr[i] = arr[j]; arr[j] = tmp;
            }
        }

        int tmp = arr[i+1]; arr[i+1] = arr[hi]; arr[hi] = tmp;
        return i + 1;
    }
}
