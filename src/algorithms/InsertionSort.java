package algorithms;

public class InsertionSort {

    public static void sort(int[] arr, SortStep cb) {
        int n = arr.length;
        boolean[] sortedFlags = new boolean[n];
        sortedFlags[0] = true;

        for (int i = 1; i < n; i++) {
            int key = arr[i];
            int j = i-1;
            cb.step(arr, new int[]{i}, new int[]{}, sortedIndices(sortedFlags));
            while (j >= 0 && arr[j] > key) {
                arr[j+1] = arr[j];
                cb.step(arr, new int[]{}, new int[]{j+1}, sortedIndices(sortedFlags));
                j--;
            }
            arr[j+1] = key;
            sortedFlags[i] = true;
            cb.step(arr, new int[]{}, new int[]{j+1}, sortedIndices(sortedFlags));
        }
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