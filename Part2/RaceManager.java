import javax.swing.*;
import java.awt.*;


/**
 * RaceManager provides a GUI for configuring and managing typing races.
 * Users can select a passage, configure the number of typists,
 * and enable/disable difficulty modifiers.
 */
public class RaceManager extends JFrame
{
    private JComboBox<Passages> passageSelector;
    private JSpinner typistCountSpinner;
    private JCheckBox autocorrectCheckbox;
    private JCheckBox caffeineCheckbox;
    private JCheckBox nightShiftCheckbox;
    private JTextArea customPassageCont;

	private static final int MAX_TYPISTS = 6;

	public RaceManager(){
		this(new Typist[MAX_TYPISTS]);
	}

    /**
     * Constructor for RaceManager.
     * Initializes and displays the race configuration window.
     */
    public RaceManager(Typist[] in)
    {
		
		racers = in;
		for(int i=0;i<racers.length; ++i){
			racers[i] = new Typist('@', "Typosaur " + i, 1);
		}
		
		
        setTitle("Typing Race Manager");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // Create main panel with padding
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        // Passage Selection Section
        JPanel passagePanel = createPassagePanel();
        mainPanel.add(passagePanel);
        mainPanel.add(Box.createVerticalStrut(15));
       

        // Typist Count Section
        JPanel typistPanel = createTypistPanel();
        mainPanel.add(typistPanel);
        mainPanel.add(Box.createVerticalStrut(15));

        // Difficulty Section
        JPanel difficultyPanel = createDifficultyPanel();
        mainPanel.add(difficultyPanel);
        mainPanel.add(Box.createVerticalStrut(15));

        // Action Buttons
        JPanel buttonPanel = createButtonPanel();
        mainPanel.add(buttonPanel);
        

        // Add main panel to frame
        add(mainPanel);
        pack();
        setVisible(true);
    }

    /**
     * Creates the passage selection panel.
     *
     * @return a JPanel containing passage selector controls
     */
    private JPanel createPassagePanel()
    {
        JPanel mPanel = new JPanel();
        mPanel.setLayout(new BoxLayout(mPanel, BoxLayout.Y_AXIS));
        mPanel.setBorder(BorderFactory.createTitledBorder("Passage Selection"));
        mPanel.setAlignmentX(JPanel.LEFT_ALIGNMENT);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        panel.setAlignmentX(JPanel.LEFT_ALIGNMENT);

        JLabel label = new JLabel("Select Passage:");
        label.setPreferredSize(new Dimension(120, 25));


        passageSelector = new JComboBox<>(Passages.values());
        passageSelector.setPreferredSize(new Dimension(200, 25));

        panel.add(label);
        panel.add(Box.createHorizontalStrut(10));
        panel.add(passageSelector);
        panel.add(Box.createHorizontalGlue());

        mPanel.add(panel);
        
        JLabel taLabel = new JLabel("Input custom passage below", SwingConstants.LEFT);
        taLabel.setAlignmentX(JPanel.LEFT_ALIGNMENT);
        mPanel.add(Box.createVerticalStrut(10));
        mPanel.add(taLabel);
        
        customPassageCont = new JTextArea(5, 30);
        customPassageCont.setAlignmentX(JPanel.LEFT_ALIGNMENT);
        customPassageCont.setLineWrap(true);
        customPassageCont.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(customPassageCont);
        scrollPane.setAlignmentX(JPanel.LEFT_ALIGNMENT);
        mPanel.add(scrollPane);

        return mPanel;
    }

    /**
     * Creates the typist count configuration panel.
     *
     * @return a JPanel containing typist count controls
     */
    private JPanel createTypistPanel()
    {
        JPanel mPanel = new JPanel();
        mPanel.setLayout(new BoxLayout(mPanel, BoxLayout.Y_AXIS));
        mPanel.setBorder(BorderFactory.createTitledBorder("Typist Configuration"));
        mPanel.setAlignmentX(JPanel.LEFT_ALIGNMENT);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        panel.setAlignmentX(JPanel.LEFT_ALIGNMENT);

        JLabel label = new JLabel("Number of Typists:");
        label.setPreferredSize(new Dimension(120, 25));

        SpinnerModel spinnerModel = new SpinnerNumberModel(3, 2, MAX_TYPISTS, 1);
        typistCountSpinner = new JSpinner(spinnerModel);
        typistCountSpinner.setPreferredSize(new Dimension(50, 25));

        panel.add(label);
        panel.add(Box.createHorizontalStrut(10));
        panel.add(typistCountSpinner);
        panel.add(Box.createHorizontalGlue());

        mPanel.add(panel);
        mPanel.add(Box.createVerticalStrut(10));

        JButton configureTypistButton = new JButton("Configure Typists");
        configureTypistButton.setAlignmentX(JPanel.LEFT_ALIGNMENT);
        configureTypistButton.addActionListener(e -> handleConfigureTypist());
        mPanel.add(configureTypistButton);

        return mPanel;
    }

    /**
     * Creates the difficulty modifiers panel.
     *
     * @return a JPanel containing difficulty checkbox controls
     */
    private JPanel createDifficultyPanel()
    {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createTitledBorder("Difficulty"));
        panel.setAlignmentX(JPanel.LEFT_ALIGNMENT);

        autocorrectCheckbox = new JCheckBox("Autocorrect");
        caffeineCheckbox = new JCheckBox("Caffeine Mode");
        nightShiftCheckbox = new JCheckBox("Night Shift");

        autocorrectCheckbox.setAlignmentX(JPanel.LEFT_ALIGNMENT);
        caffeineCheckbox.setAlignmentX(JPanel.LEFT_ALIGNMENT);
        nightShiftCheckbox.setAlignmentX(JPanel.LEFT_ALIGNMENT);

        panel.add(autocorrectCheckbox);
        panel.add(Box.createVerticalStrut(8));
        panel.add(caffeineCheckbox);
        panel.add(Box.createVerticalStrut(8));
        panel.add(nightShiftCheckbox);
        panel.add(Box.createVerticalGlue());

        return panel;
    }

    /**
     * Creates the action buttons panel.
     *
     * @return a JPanel containing start and cancel buttons
     */
    private JPanel createButtonPanel()
    {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        panel.setAlignmentX(JPanel.LEFT_ALIGNMENT);

        JButton startButton = new JButton("Start Race");
        JButton cancelButton = new JButton("Exit");
        JButton saveButton = new JButton("Save");

        startButton.addActionListener(e -> handleStartRace());
        cancelButton.addActionListener(e -> handleCancel());
        saveButton.addActionListener(e -> handleSave());

        startButton.setPreferredSize(new Dimension(120, 35));
        cancelButton.setPreferredSize(new Dimension(120, 35));
        cancelButton.setPreferredSize(new Dimension(120, 35));

        panel.add(startButton);
        panel.add(Box.createHorizontalStrut(10));
        panel.add(cancelButton);
        panel.add(Box.createHorizontalStrut(10));
        panel.add(saveButton);
        panel.add(Box.createHorizontalGlue());

        return panel;
    }

    /**
     * Handles the start race button action.
     */
    private void handleStartRace()
    {
        String selectedPassage = ((Passages)passageSelector.getSelectedItem()).getPassage();
        int typistCount = (Integer) typistCountSpinner.getValue();
        boolean autocorrectEnabled = autocorrectCheckbox.isSelected();
        boolean caffeineEnabled = caffeineCheckbox.isSelected();
        boolean nightShiftEnabled = nightShiftCheckbox.isSelected();

		if(((Passages)passageSelector.getSelectedItem()).equals(Passages.CUSTOM)){
			selectedPassage = customPassageCont.getText();
		}

        TypingRace r = new TypingRace(selectedPassage, typistCount);
        
        for(int i=0; i<typistCount;++i){
			r.addTypist(racers[i] , i + 1);
		}
        
        r.setMods(autocorrectEnabled, caffeineEnabled, nightShiftEnabled);
        
        setVisible(false);
        
        RaceWindow rw = new RaceWindow(r);
        
    }

    /**
     * Handles the cancel button action.
     */
    private void handleCancel()
    {
        System.exit(0);
    }
    
    /**
     * Handles the save to file action.
     */
    private void handleSave(){
		 
	}
    
    private Typist[] racers;

    /**
     * Handles the configure typist button action.
     */
    private void handleConfigureTypist()
    {
        int typistCount = (Integer) typistCountSpinner.getValue();
        ConfigureTypistDialog dialog = new ConfigureTypistDialog(this, typistCount, racers);
        dialog.setVisible(true);
    }
    
    //dummy function for spec compliance...
    public static void startRaceGUI(){
		main(null);
	}
    

    /**
     * Main method to launch the RaceManager GUI.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(() -> new RaceManager());
    }
}
