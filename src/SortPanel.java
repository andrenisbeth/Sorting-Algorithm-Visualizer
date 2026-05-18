import javax.swing.*;
import java.awt.*;

public class SortPanel extends JPanel {

    private int[] arr;
    private Color[] colorsArray;

    public void update(int[] arr, int[] comparing, int[] swapping, int[] sorted) {
        this.arr = arr.clone();
        this.colorsArray = new Color[arr.length];

        for (int i = 0; i < arr.length; i++) {
            colorsArray[i] = new Color(0x378ADD);
        }
        for (int i : sorted) {
            colorsArray[i] = new Color(0x1D9E75);
        }
        for (int i : swapping) {
            colorsArray[i] = new Color(0xE24B4A);
        }
        for (int i : comparing) {
            colorsArray[i] = new Color(0xEF9F27);
        }

        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (arr == null || arr.length == 0) {
            return;
        }

        int panelWidth = getWidth();
        int panelHeight = getHeight();

        int maxElementValInArray = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > maxElementValInArray) {
                maxElementValInArray = arr[i];
            }
        }

        int barWidth = panelWidth / arr.length;
        if (barWidth < 1) {
            barWidth = 1;
        }

        for (int i = 0; i < arr.length; i++) {
            int barHeight = (int) ((arr[i] / (double) maxElementValInArray) * (panelHeight - 20));
            int x = i * barWidth;
            int y = panelHeight - barHeight;

            g.setColor(colorsArray[i]);
            g.fillRect(x, y, barWidth - 1, barHeight);
        }
    }
}