import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * ConfigureTypistDialog provides a GUI for configuring individual typist settings.
 * Users can select typing style, keyboard type, avatar, progress colour,
 * accessories, and view attribute impacts.
 */
public class ConfigureTypistDialog extends JDialog
{
	
	
	private Typist[] racers;
	JSpinner numberSpinner;
	JTextField avatarField;
	JComboBox<TypingStyle> styleSelector;
	
    /**
     * Constructor for ConfigureTypistDialog.
     * Initializes and displays the typist configuration dialog.
     *
     * @param parent the parent frame
     * @param maxTypists the maximum number of typists
     */
    public ConfigureTypistDialog(JFrame parent, int maxTypists, Typist[] r)
    {
        super(parent, "Configure Typist", true);
        racers = r;
        
        
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(parent);
        setResizable(false);

        // Create main panel for dialog with padding
        JPanel dialogPanel = new JPanel();
        dialogPanel.setLayout(new BoxLayout(dialogPanel, BoxLayout.Y_AXIS));
        dialogPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        // Typist Number Selection Section
        JPanel typistNumberPanel = createDialogTypistNumberPanel(maxTypists);
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

        okButton.addActionListener(e -> dispose());
        cancelButton.addActionListener(e -> dispose());

        buttonPanel.add(okButton);
        buttonPanel.add(Box.createHorizontalStrut(10));
        buttonPanel.add(cancelButton);
        buttonPanel.add(Box.createHorizontalGlue());

        dialogPanel.add(buttonPanel);

        JScrollPane scrollPane = new JScrollPane(dialogPanel);
        add(scrollPane);
        pack();
        
        loadTypistsData();
    }
    
    /**
     * Loads params of typists into the config dialog
     *
     */
     private void loadTypistsData(){
		 
		int currentIdx = (Integer)numberSpinner.getValue() - 1; //1-based indexing to 0-based indexing
		Typist curr = racers[currentIdx];
		
		avatarField.setText(Character.toString(curr.getSymbol()));
		styleSelector.setSelectedItem(curr.getTypistBuffs().getTypingStyle());
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
        numberSpinner = new JSpinner(spinnerModel);
        numberSpinner.setPreferredSize(new Dimension(50, 25));
        numberSpinner.setMaximumSize(new Dimension(50, 25));
        numberSpinner.addChangeListener(e -> loadTypistsData());

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

        styleSelector = new JComboBox<>(TypingStyle.values());
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

        avatarField = new JTextField(5);
        avatarField.setPreferredSize(new Dimension(50, 25));
        avatarField.setMaximumSize(new Dimension(25, 25));
		avatarField.addKeyListener(new KeyAdapter(){
			public void keyTyped(KeyEvent e){
				if (avatarField.getText().length() >= 1) // limit textfield to 3 characters
					e.consume(); 

			}
		});


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
}
