import javax.swing.*;
import java.awt.*;


/**
 * RaceWindow provides the real juicy typing race bits and graphs.
 */
public class RaceWindow extends JFrame
{
	TypingRace tr;
	RaceWindow(TypingRace in){
		tr = in;
		
		setTitle("RACE");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // Create main panel with padding
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));






		pack();
		setVisible(true);
	}

}
