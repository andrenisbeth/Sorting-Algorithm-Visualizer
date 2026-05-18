public class SelectionSort {

    public static void sort(int[] array) {

        int n = array.length;

        for (int i = 0; i < n - 1; i++) {

            int indexMin = i;

            for (int j = i + 1; j < n; j++) {

                if (array[j] < array[indexMin]) {
                    indexMin = j;
                }
            }

            int tempValHolder = array[i];
            array[i] = array[indexMin];
            array[indexMin] = tempValHolder;
        }
    }
}