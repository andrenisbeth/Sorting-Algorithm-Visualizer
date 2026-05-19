import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.Random;

public class Main {

    private int startingArraySize = 100;
    private int startingArrayMinimumValue = 1;

    private int[] activeArray;

    private SortController activeSortController;
    private SortPanel activeSortPanel;

    private final String[] algoNames = { "Bubble Sort", "Insertion Sort", "Selection Sort", "Merge Sort",
            "Quick Sort" };
    private final String[] algoKeys = { "bubble", "insertion", "selection", "merge", "quick" };

    private String selectedAlgoKey = "selection";

    public static void main(String[] args) {
        Main app = new Main();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                app.initializeWindow();
            }
        };
        SwingUtilities.invokeLater(runnable);
    }

    private void initializeWindow() {

        activeArray = generateArray();

        activeSortPanel = new SortPanel();

        activeSortController = new SortController(activeSortPanel);

        JPanel activeInputControls = renderInputControls();
        JMenuBar activeAlgorithmMenuBar = renderAlgorithmMenu();

        JFrame window = new JFrame();
        window.setTitle("Sorting Algorithm Visualiser");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setLocationRelativeTo(null);
        window.setSize(800, 600);

        window.setJMenuBar(activeAlgorithmMenuBar);

        window.add(activeSortPanel, BorderLayout.CENTER);
        window.add(activeInputControls, BorderLayout.SOUTH);

        window.setVisible(true);

        activeSortPanel.update(activeArray, new int[] {}, new int[] {}, new int[] {});

    }

    private JPanel renderInputControls() {
        JPanel activeSpeedBar = renderSpeedBar();
        JPanel activeButtons = renderButtons();

        JPanel inputControls = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 8));
        inputControls.add(activeSpeedBar);
        inputControls.add(activeButtons);

        return inputControls;
    }

    private JMenuBar renderAlgorithmMenu() {
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Algorithm");

        for (int i = 0; i < algoNames.length; i++) {
            JMenuItem item = new JMenuItem(algoNames[i]);
            final String key = algoKeys[i];
            item.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    selectedAlgoKey = key;
                }
            });
            menu.add(item);
        }

        menuBar.add(menu);
        return menuBar;
    }

    private JPanel renderSpeedBar() {
        JSlider activeSpeedBar = new JSlider(5, 400, 40);
        activeSpeedBar.setInverted(true);
        activeSpeedBar.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                activeSortController.setDelay(activeSpeedBar.getValue());
            }
        });

        JPanel panel = new JPanel();
        panel.add(new JLabel("Speed:"));
        panel.add(activeSpeedBar);
        return panel;
    }

    private JPanel renderButtons() {
        JButton activeStartButton = new JButton("Start");
        JButton activeStopButton = new JButton("Stop");
        JButton activeShuffleButton = new JButton("Shuffle");

        activeShuffleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                activeSortController.stop();
                activeArray = generateArray();
                activeSortPanel.update(activeArray, new int[] {}, new int[] {}, new int[] {});
            }
        });

        activeStartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                activeSortController.run(selectedAlgoKey, activeArray);
            }
        });

        activeStopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                activeSortController.stop();
            }
        });

        JPanel activeButtonPanel = new JPanel();
        activeButtonPanel.add(activeStartButton);
        activeButtonPanel.add(activeStopButton);
        activeButtonPanel.add(activeShuffleButton);
        return activeButtonPanel;
    }

    private int[] generateArray() {
        Random rng = new Random();
        int[] outputArray = new int[startingArraySize];
        int currentPillarValue;

        for (int i = 0; i < startingArraySize; i++) {
            currentPillarValue = startingArrayMinimumValue + rng.nextInt(startingArraySize);
            outputArray[i] = currentPillarValue;
        }
        return outputArray;
    }
}