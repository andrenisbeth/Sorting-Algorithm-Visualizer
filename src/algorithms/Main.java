import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        int[] arr = {5, 3, 8, 1, 9, 2, 7, 4, 6};

        System.out.println("Bubble Sort");
        testSort("bubble", arr.clone());

        System.out.println("\nInsertion Sort");
        testSort("insertion", arr.clone());
        
        System.out.println("\nSelection Sort");
        testSort("selection", arr.clone());
        
        System.out.println("\nMerge Sort");
        testSort("merge", arr.clone());

        System.out.println("\nQuick Sort");
        testSort("quick", arr.clone());
    }

    private static void testSort(String algo, int[] arr) {
        System.out.println("Before: " + Arrays.toString(arr));

        SortStep cb = (a, comapring, swapping, sorted) -> {
            if (swapping.length > 0) {
                System.out.println("  swap  " + Arrays.toString(swapping) + " -> " + Arrays.toString(a));
            }
        };

        switch (algo) {
            case "bubble" -> BubbleSort.sort(arr, cb);
            case "insertion" -> InsertionSort.sort(arr, cb);
            case "selection" -> SelectionSort.sort(arr, cb);
            case "merge" -> MergeSort.sort(arr, cb);
            case "quick" -> Quicksort.sort(arr, cb);
        }

        System.out.println("After:  " + Arrays.toString(arr));
    }
}