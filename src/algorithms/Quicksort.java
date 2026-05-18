package algorithms;

public class Quicksort {
   
    public static void sort(int[] arr, SortStep cb) {
        qs(arr, 0, arr.length - 1, new boolean[arr.length], cb);
    }

    private static void qs(int[] arr, int lo, int hi, boolean[] sortedFlags, SortStep cb) {
        if (lo >= hi) {
            if (lo == hi) { sortedFlags[lo] = true; }
            return;
        }
        int p = partition(arr, lo, hi, sortedFlags, cb);
        qs(arr, lo, p-1, sortedFlags, cb);
        qs(arr, p+1, hi, sortedFlags, cb);
    }

    private static int partition(int[] arr, int lo, int hi, boolean[] sortedFlags, SortStep cb) {
        int pivot = arr[hi];
        int i = lo-1;

        for (int j = lo; j < hi; j++) {
            cb.step(arr, new int[]{j}, new int[]{hi}, sortedIndices(sortedFlags));
            if (arr[j] <= pivot) {
                i++;
                int tmp = arr[i]; arr[i] = arr[j]; arr[j] = tmp;
                cb.step(arr, new int[]{}, new int[]{i, j}, sortedIndices(sortedFlags));
            }
        }
        int tmp = arr[i+1]; arr[i+1] = arr[hi]; arr[hi] = tmp;
        sortedFlags[i+1] = true;
        cb.step(arr, new int[]{}, new int[]{i+1}, sortedIndices(sortedFlags));
        return i + 1;
    }

    private static int[] sortedIndices(boolean[] flags) {
        int count = 0;
        for (boolean b : flags) if (b) count++;
        int[] result = new int[count];
        int k = 0;
        for (int i = 0; i < flags.length; i++) if (flags[i]) result[k++] = i;
        return result;
    }
}
