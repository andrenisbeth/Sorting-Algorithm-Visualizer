import javax.swing.SwingUtilities;
import algorithms.*;

public class SortController {
    private final SortPanel panel;
    // default delay in milliseconds between each animation step
    private int delayMs = 30;
    private Thread worker;

    // SortPanel is injected so the controller can push updates to the UI
    public SortController(SortPanel panel) {
        this.panel = panel;
    }

    // called by speed slider to adjust animation speed in Main
    public void setDelay(int ms) {
        this.delayMs = ms;
    }

    // Interrupts the worker thread if a sort is already running
    public void stop() {
        if (worker != null) worker.interrupt();
    }

    public void run(String algo, int[] arr) {
        // Stop any ongoing sort before starting a new one
        stop();
        // work on a copy so the original array is not modified
        int[] copy = arr.clone();

        // Callback passed to the algorithm, called after every comparison and swap
        SortStep cb = (a, comparing, swapping, sorted) -> {
            // Swing requires all UI updates to happen on the Event Dispatch Thread
            SwingUtilities.invokelater(() -> panel.update(a, comparing, swapping, sorted));
            try {
                // Use configurable delay between steps so the animation is visible
                Thread.sleep(delayMs);
            } catch (InterruptedException e) {
                // Re-interrupt so the thread knows to stop cleanly
                Thread.currentThread().interrupt();
            }
        }

        // Run algorithm on a background thread so the UI does not freeze
        worker = new Thread(() -> {
            switch(algo) {
                case "bubble" -> BubbleSort.sort(copy, cb);
                case "insertion" -> InsertionSort.sort(copy, cb);
                case "selection" -> SelectionSort.sort(copy, cb);
                case "merge" -> MergeSort.sort(copy, cb);
                case "quick" -> Quicksort.sort(copy, cb);
            }
        });
        worker.setDaemon(true);
        worker.start();
    }
}