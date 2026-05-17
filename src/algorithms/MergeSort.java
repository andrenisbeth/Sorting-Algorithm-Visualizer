import java.util.Arrays;

public class MergeSort {

    public static void sort(int [] arr, SortStep cb) {
        if (arr.length <= 1) return;
        ms(arr, 0, arr.length - 1, new boolean[arr.length], cb);
    }

    private static void ms(int[] arr, int lo, int hi, boolean[] sortedFlags, SortStep cb) {
        if (lo >= hi) return;
        int mid = (lo + hi) / 2;
        ms(arr, lo, mid, sortedFlags, cb);
        ms(arr, mid + 1, hi, sortedFlags, cb);
        merge(arr, lo, mid, hi, sortedFlags, cb);
    }

    private static void merge(int[] arr, int lo, int mid, int hi, boolean[] sortedFlags, SortStep cb) {
        int[] left = Arrays.copyOfRange(arr, lo, mid + 1);
        int[] right = Arrays.copyOfRange(arr, mid + 1, hi + 1);

        int i = 0, j = 0, k = lo;
        while (i < left.length && j < right.length) {
            cb.step(arr, new int[]{lo + i, mid + 1 + j}, new int[]{}, sortedIndices(sortedFlags));
            if (left[i] <= right[j]) arr[k++] = left[i++];
            else arr[k++] = right[j++];
            cb.step(arr, new int[]{}, new int[]{k - 1}, sortedIndices(sortedFlags));
        }
        while (i < left.length) { arr[k++] = left[i++]; cb.step(arr, new int[]{}, new int[]{k-1}, sortedIndices(sortedFlags)); }
        while (j < right.length) { arr[k++] = right[j++]; cb.step(arr, new int[]{}, new int[]{k-1}, sortedIndices(sortedFlags)); }

        for (int x = lo; x <= hi; x++) sortedFlags[x] = true;
        cb.step(arr, new int[]{}, new int[]{}, sortedIndices(sortedFlags));
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