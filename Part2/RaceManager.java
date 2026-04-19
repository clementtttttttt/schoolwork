import javax.swing.*;
import java.awt.*;

/**
 * RaceManager provides a GUI for configuring and managing typing races.
 * Users can select a passage, configure the number of typists,
 * and enable/disable difficulty modifiers.
 */
public class RaceManager extends JFrame
{
    private JComboBox<String> passageSelector;
    private JSpinner typistCountSpinner;
    private JCheckBox autocorrectCheckbox;
    private JCheckBox caffeineCheckbox;
    private JCheckBox nightShiftCheckbox;
    private JTextArea customPassageCont;

    /**
     * Constructor for RaceManager.
     * Initializes and displays the race configuration window.
     */
    public RaceManager()
    {
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

        String[] passages = {
            "The Quick Brown Fox",
            "Lorem Ipsum",
            "Programming in Java",
            "Custom Passage"
        };
        passageSelector = new JComboBox<>(passages);
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

        SpinnerModel spinnerModel = new SpinnerNumberModel(3, 1, 10, 1);
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
        JButton cancelButton = new JButton("Cancel");

        startButton.addActionListener(e -> handleStartRace());
        cancelButton.addActionListener(e -> handleCancel());

        startButton.setPreferredSize(new Dimension(120, 35));
        cancelButton.setPreferredSize(new Dimension(120, 35));

        panel.add(startButton);
        panel.add(Box.createHorizontalStrut(10));
        panel.add(cancelButton);
        panel.add(Box.createHorizontalGlue());

        return panel;
    }

    /**
     * Handles the start race button action.
     */
    private void handleStartRace()
    {
        String selectedPassage = (String) passageSelector.getSelectedItem();
        int typistCount = (Integer) typistCountSpinner.getValue();
        boolean autocorrectEnabled = autocorrectCheckbox.isSelected();
        boolean caffeineEnabled = caffeineCheckbox.isSelected();
        boolean nightShiftEnabled = nightShiftCheckbox.isSelected();

        System.out.println("=== Race Configuration ===");
        System.out.println("Passage: " + selectedPassage);
        System.out.println("Typists: " + typistCount);
        System.out.println("Autocorrect: " + autocorrectEnabled);
        System.out.println("Caffeine Mode: " + caffeineEnabled);
        System.out.println("Night Shift: " + nightShiftEnabled);
    }

    /**
     * Handles the cancel button action.
     */
    private void handleCancel()
    {
        System.exit(0);
    }

    /**
     * Handles the configure typist button action.
     */
    private void handleConfigureTypist()
    {
        JDialog configDialog = new JDialog(this, "Configure Typist", true);
        configDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        configDialog.setLocationRelativeTo(this);
        configDialog.setResizable(false);
        
        // Get the number of typists from the main window
        int typistCount = (Integer) typistCountSpinner.getValue();

        // Create main panel for dialog with padding
        JPanel dialogPanel = new JPanel();
        dialogPanel.setLayout(new BoxLayout(dialogPanel, BoxLayout.Y_AXIS));
        dialogPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        // Typist Number Selection Section
        JPanel typistNumberPanel = createDialogTypistNumberPanel(typistCount);
        dialogPanel.add(typistNumberPanel);
        dialogPanel.add(Box.createVerticalStrut(15));

        // Typing Style Selection Section
        JPanel stylePanel = createDialogStylePanel();
        dialogPanel.add(stylePanel);
        dialogPanel.add(Box.createVerticalStrut(15));

        // Keyboard Type Selection Section
        JPanel keyboardPanel = createDialogKeyboardPanel();
        dialogPanel.add(keyboardPanel);
        dialogPanel.add(Box.createVerticalStrut(15));

        // Avatar Section
        JPanel avatarPanel = createDialogAvatarPanel();
        dialogPanel.add(avatarPanel);
        dialogPanel.add(Box.createVerticalStrut(15));

        // Progress Colour Section
        JPanel colourPanel = createDialogColourPanel();
        dialogPanel.add(colourPanel);
        dialogPanel.add(Box.createVerticalStrut(15));

        // Accessories Section
        JPanel accessoriesPanel = createDialogAccessoriesPanel();
        dialogPanel.add(accessoriesPanel);
        dialogPanel.add(Box.createVerticalStrut(15));

        // Attribute Impacts Section
        JPanel attributesPanel = createDialogAttributesPanel();
        dialogPanel.add(attributesPanel);
        dialogPanel.add(Box.createVerticalStrut(15));

        // Action Buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        buttonPanel.setAlignmentX(JPanel.LEFT_ALIGNMENT);

        JButton okButton = new JButton("OK");
        JButton cancelButton = new JButton("Cancel");

        okButton.setPreferredSize(new Dimension(100, 35));
        cancelButton.setPreferredSize(new Dimension(100, 35));

        okButton.addActionListener(e -> configDialog.dispose());
        cancelButton.addActionListener(e -> configDialog.dispose());

        buttonPanel.add(okButton);
        buttonPanel.add(Box.createHorizontalStrut(10));
        buttonPanel.add(cancelButton);
        buttonPanel.add(Box.createHorizontalGlue());

        dialogPanel.add(buttonPanel);

        JScrollPane scrollPane = new JScrollPane(dialogPanel);
        configDialog.add(scrollPane);
        configDialog.pack();
        configDialog.setVisible(true);
    }

    /**
     * Creates the typist number selection panel for the configuration dialog.
     *
     * @param maxTypists the maximum number of typists (from main window)
     * @return a JPanel containing the typist number spinner
     */
    private JPanel createDialogTypistNumberPanel(int maxTypists)
    {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        panel.setAlignmentX(JPanel.LEFT_ALIGNMENT);
        panel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 25));

        JLabel numberLabel = new JLabel("Typist Number:");
        numberLabel.setPreferredSize(new Dimension(120, 25));

        SpinnerModel spinnerModel = new SpinnerNumberModel(1, 1, maxTypists, 1);
        JSpinner numberSpinner = new JSpinner(spinnerModel);
        numberSpinner.setPreferredSize(new Dimension(50, 25));
        numberSpinner.setMaximumSize(new Dimension(50, 25));

        panel.add(numberLabel);
        panel.add(Box.createHorizontalStrut(10));
        panel.add(numberSpinner);
        panel.add(Box.createHorizontalGlue());

        return panel;
    }

    /**
     * Creates the typing style selection panel for the configuration dialog.
     *
     * @return a JPanel containing the typing style selector
     */
    private JPanel createDialogStylePanel()
    {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        panel.setAlignmentX(JPanel.LEFT_ALIGNMENT);
        panel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 25));

        JLabel styleLabel = new JLabel("Typing Style:");
        styleLabel.setPreferredSize(new Dimension(120, 25));

        JComboBox<TypingStyle> styleSelector = new JComboBox<>(TypingStyle.values());
        styleSelector.setPreferredSize(new Dimension(200, 25));
        styleSelector.setMaximumSize(new Dimension(200, 25));

        panel.add(styleLabel);
        panel.add(Box.createHorizontalStrut(10));
        panel.add(styleSelector);
        panel.add(Box.createHorizontalGlue());

        return panel;
    }

    /**
     * Creates the keyboard type selection panel for the configuration dialog.
     *
     * @return a JPanel containing the keyboard type selector
     */
    private JPanel createDialogKeyboardPanel()
    {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        panel.setAlignmentX(JPanel.LEFT_ALIGNMENT);
        panel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 25));

        JLabel keyboardLabel = new JLabel("Keyboard Type:");
        keyboardLabel.setPreferredSize(new Dimension(120, 25));

        JComboBox<KeyboardType> keyboardSelector = new JComboBox<>(KeyboardType.values());
        keyboardSelector.setPreferredSize(new Dimension(200, 25));
        keyboardSelector.setMaximumSize(new Dimension(200, 25));

        panel.add(keyboardLabel);
        panel.add(Box.createHorizontalStrut(10));
        panel.add(keyboardSelector);
        panel.add(Box.createHorizontalGlue());

        return panel;
    }

    /**
     * Creates the avatar input panel for the configuration dialog.
     *
     * @return a JPanel containing the avatar text field
     */
    private JPanel createDialogAvatarPanel()
    {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        panel.setAlignmentX(JPanel.LEFT_ALIGNMENT);

        JLabel avatarLabel = new JLabel("Avatar:");
        avatarLabel.setPreferredSize(new Dimension(120, 25));

        JTextField avatarField = new JTextField(1);
        avatarField.setPreferredSize(new Dimension(50, 25));
        avatarField.setMaximumSize(new Dimension(25, 25));

        panel.add(avatarLabel);
        panel.add(Box.createHorizontalStrut(10));
        panel.add(avatarField);
        panel.add(Box.createHorizontalGlue());

        return panel;
    }

    /**
     * Creates the progress colour picker panel for the configuration dialog.
     *
     * @return a JPanel containing the colour picker button
     */
    private JPanel createDialogColourPanel()
    {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        panel.setAlignmentX(JPanel.LEFT_ALIGNMENT);

        JLabel colourLabel = new JLabel("Progress Colour:");
        colourLabel.setPreferredSize(new Dimension(120, 25));

        JButton colourButton = new JButton("Select Colour");
        colourButton.setPreferredSize(new Dimension(150, 25));
        colourButton.addActionListener(e -> {
            Color selectedColour = JColorChooser.showDialog(null, "Choose Progress Colour", Color.BLUE);
            if (selectedColour != null)
            {
                colourButton.setBackground(selectedColour);
                colourButton.setOpaque(true);
            }
        });

        panel.add(colourLabel);
        panel.add(Box.createHorizontalStrut(10));
        panel.add(colourButton);
        panel.add(Box.createHorizontalGlue());

        return panel;
    }

    /**
     * Creates the accessories section panel for the configuration dialog.
     *
     * @return a JPanel containing accessory checkboxes
     */
    private JPanel createDialogAccessoriesPanel()
    {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createTitledBorder("Accessories"));
        panel.setAlignmentX(JPanel.LEFT_ALIGNMENT);

        JCheckBox wristSupportCheckbox = new JCheckBox("Wrist Support");
        JCheckBox energyDrinkCheckbox = new JCheckBox("Energy Drink");
        JCheckBox noiseCancellingCheckbox = new JCheckBox("Noise-Cancelling Headphones");

        wristSupportCheckbox.setAlignmentX(JPanel.LEFT_ALIGNMENT);
        energyDrinkCheckbox.setAlignmentX(JPanel.LEFT_ALIGNMENT);
        noiseCancellingCheckbox.setAlignmentX(JPanel.LEFT_ALIGNMENT);

        panel.add(wristSupportCheckbox);
        panel.add(Box.createVerticalStrut(8));
        panel.add(energyDrinkCheckbox);
        panel.add(Box.createVerticalStrut(8));
        panel.add(noiseCancellingCheckbox);
        panel.add(Box.createVerticalGlue());

        return panel;
    }

    /**
     * Creates the attribute impacts display panel for the configuration dialog.
     *
     * @return a JPanel containing attribute impact labels
     */
    private JPanel createDialogAttributesPanel()
    {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createTitledBorder("Attribute Impacts"));
        panel.setAlignmentX(JPanel.LEFT_ALIGNMENT);

        JPanel accuracyPanel = new JPanel();
        accuracyPanel.setLayout(new BoxLayout(accuracyPanel, BoxLayout.X_AXIS));
        accuracyPanel.setAlignmentX(JPanel.LEFT_ALIGNMENT);
        JLabel accuracyLabel = new JLabel("Accuracy: ");
        JLabel accuracyValueLabel = new JLabel("");
        accuracyPanel.add(accuracyLabel);
        accuracyPanel.add(accuracyValueLabel);
        accuracyPanel.add(Box.createHorizontalGlue());

        JPanel speedPanel = new JPanel();
        speedPanel.setLayout(new BoxLayout(speedPanel, BoxLayout.X_AXIS));
        speedPanel.setAlignmentX(JPanel.LEFT_ALIGNMENT);
        JLabel speedLabel = new JLabel("Speed: ");
        JLabel speedValueLabel = new JLabel("");
        speedPanel.add(speedLabel);
        speedPanel.add(speedValueLabel);
        speedPanel.add(Box.createHorizontalGlue());

        JPanel burnoutPanel = new JPanel();
        burnoutPanel.setLayout(new BoxLayout(burnoutPanel, BoxLayout.X_AXIS));
        burnoutPanel.setAlignmentX(JPanel.LEFT_ALIGNMENT);
        JLabel burnoutLabel = new JLabel("Burnout Risk: ");
        JLabel burnoutValueLabel = new JLabel("");
        burnoutPanel.add(burnoutLabel);
        burnoutPanel.add(burnoutValueLabel);
        burnoutPanel.add(Box.createHorizontalGlue());

        panel.add(accuracyPanel);
        panel.add(Box.createVerticalStrut(8));
        panel.add(speedPanel);
        panel.add(Box.createVerticalStrut(8));
        panel.add(burnoutPanel);
        panel.add(Box.createVerticalGlue());

        return panel;
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
