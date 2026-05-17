public class BubbleSort {

    public static void sort(int[] arr, SortStep cb) {
        int n = arr.length;
        boolean[] sortedFlags = new boolean[n];

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - 1 - i; j++) {
                cb.step(arr, new int[]{j, j+1}, new int[]{}, sortedIndices(sortedFlags));
                if (arr[j] > arr[j + 1]) {
                    int tmp = arr[j]; arr[j] = arr[j + 1]; arr[j + 1] = tmp;
                    cb.step(arr, new int[]{}, new int[]{j, j+1}, sortedIndices(sortedFlags));
                }
            }
            sortedFlags[n - 1 - i] = true;
        }
        sortedFlags[0] = true;
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