import javax.swing.*;

/**
 * TypistStatistics provides a dialog for viewing individual typist statistics.
 * Allows selection of a typist by number and displays their personal best WPM.
 */
public class TypistStatistics
{
    private JDialog dialog;
    private Typist[] racers;
    private JSpinner typistSelector;
    private JLabel bestWPMLabel;
	private JLabel historyL;
    /**
     * Constructor for TypistStatistics.
     * Creates a dialog with a number selector and best WPM display.
     *
     * @param parent the parent JFrame
     * @param typistCount the number of typists in the race
     * @param typistArray the array of Typist objects
     */
    public TypistStatistics(JFrame parent, int typistCount, Typist[] typistArray)
    {
        racers = typistArray;
        
        dialog = new JDialog(parent, "Typist Statistics", true);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setLocationRelativeTo(parent);
        dialog.setResizable(false);

        // Create main panel with padding
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        // Create typist selector section
        JPanel selectorPanel = new JPanel();
        selectorPanel.setLayout(new BoxLayout(selectorPanel, BoxLayout.X_AXIS));
        selectorPanel.setAlignmentX(JPanel.LEFT_ALIGNMENT);

        JLabel selectorLabel = new JLabel("Select Typist:");
        selectorLabel.setPreferredSize(new java.awt.Dimension(100, 25));

        SpinnerModel spinnerModel = new SpinnerNumberModel(1, 1, typistCount, 1);
        typistSelector = new JSpinner(spinnerModel);
        typistSelector.setPreferredSize(new java.awt.Dimension(50, 25));
        typistSelector.addChangeListener(e -> update());



        selectorPanel.add(selectorLabel);
        selectorPanel.add(Box.createHorizontalStrut(10));
        selectorPanel.add(typistSelector);
        selectorPanel.add(Box.createHorizontalGlue());

        mainPanel.add(selectorPanel);
        mainPanel.add(Box.createVerticalStrut(15));

        // Create best WPM display section
        JPanel statsPanel = new JPanel();
        statsPanel.setLayout(new BoxLayout(statsPanel, BoxLayout.X_AXIS));
        statsPanel.setAlignmentX(JPanel.LEFT_ALIGNMENT);

        JLabel wpmLabel = new JLabel("Personal Best WPM:");
        wpmLabel.setPreferredSize(new java.awt.Dimension(150, 25));

        bestWPMLabel = new JLabel("0");
        bestWPMLabel.setPreferredSize(new java.awt.Dimension(100, 25));

        statsPanel.add(wpmLabel);
        statsPanel.add(Box.createHorizontalStrut(10));
        statsPanel.add(bestWPMLabel);
        statsPanel.add(Box.createHorizontalGlue());

        mainPanel.add(statsPanel);
        mainPanel.add(Box.createVerticalStrut(15));

        // Create button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        buttonPanel.setAlignmentX(JPanel.LEFT_ALIGNMENT);

        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(e -> dialog.dispose());

        buttonPanel.add(Box.createHorizontalGlue());
        buttonPanel.add(closeButton);

        //add jlabel that displays race history
        
        historyL = new JLabel();
        mainPanel.add(historyL);

        mainPanel.add(buttonPanel);
        
        

        
        // Add main panel to dialog
        dialog.add(mainPanel);
        

        dialog.pack();

        // Initialize display with first typist
        update();
    }

    /**
     * Updates all labels based on the currently selected typist.
     */
    private void update()
    {
        int selectedTypistNumber = (Integer) typistSelector.getValue();
        int typistIndex = selectedTypistNumber - 1;
        
        if (typistIndex >= 0 && typistIndex < racers.length)
        {
            int bestWPM = racers[typistIndex].getBestWPM();
            bestWPMLabel.setText(Integer.toString(bestWPM));
        }
        
        String s = "<html>Race history: <br>";
        for(RaceHistory r : racers[typistIndex].getHistory()){
			s += r.toString(racers[typistIndex]);
			s += " <br> ";
		}
		s += "</html>";
		
		historyL.setText(s);
		
		dialog.pack();
    }

    /**
     * Shows the statistics dialog.
     */
    public void show()
    {
        dialog.setVisible(true);
    }
}
