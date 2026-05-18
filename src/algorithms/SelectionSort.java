package algorithms;

public class SelectionSort {

    public static void sort(int[] arr, SortStep cb) {
        int n = arr.length;
        boolean[] sortedFlags = new boolean[n];

        for(int i = 0; i < n - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < n; j++) {
                cb.step(arr, new int[]{j, minIdx}, new int[]{}, sortedIndices(sortedFlags));
                if (arr[j] < arr[minIdx]) {
                    minIdx = j;
                }
            }
            int tmp = arr[i]; arr[i] = arr[minIdx]; arr[minIdx] = tmp;
            sortedFlags[i] = true;
            cb.step(arr, new int[]{}, new int[]{i, minIdx}, sortedIndices(sortedFlags));
        }
        sortedFlags[n - 1] = true;
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