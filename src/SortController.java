import javax.swing.SwingUtilities;
import algorithms.*;

public class SortController {
    private final SortPanel panel;
    private Thread worker;

    public SortController(SortPanel panel) {
        this.panel = panel;
    }

    public void stop() {
        if (worker != null) worker.interrupt();
    }

    public void run(String algo, int[] arr) {
        stop();
        int[] copy = arr.clone();

        worker = new Thread(() -> {
            switch(algo) {
                case "bubble" -> BubbleSort.sort(copy, null);
                case "insertion" -> InsertionSort.sort(copy, null);
                case "selection" -> SelectionSort.sort(copy, null);
                case "merge" -> MergeSort.sort(copy, null);
                case "quick" -> Quicksort.sort(copy, null);
            }
        });
        worker.setDaemon(true);
        worker.start();
    }
}