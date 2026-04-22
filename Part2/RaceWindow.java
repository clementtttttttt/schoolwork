import javax.swing.*;
import java.awt.*;


/**
 * RaceWindow provides the real juicy typing race bits and graphs.
 */
public class RaceWindow extends JFrame
{
	TypingRace tr;
	
	JTextPane [] typistTracks;
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


		JPanel raceFieldPanel = createRacingField(); 
		mainPanel.add(raceFieldPanel);

		add(mainPanel);
		
		pack();
		setVisible(true);
	}
	
	
	JPanel createRacingField(){
		Typist[] arr = tr.getTypists();
		typistTracks = new JTextPane[arr.length];
		
		JPanel ret = new JPanel();
        ret.setLayout(new BoxLayout(ret, BoxLayout.Y_AXIS));

		for(int i=0; i<arr.length; ++i){
			JPanel a = new JPanel();	
			a.setLayout(new BoxLayout(a, BoxLayout.X_AXIS));

			typistTracks[i] = new JTextPane(){
				public boolean getScrollableTracksViewportWidth()
				{
					return getParent().getSize().width > 150;
				}
				
			};
			typistTracks[i].setText(tr.getPassage());
			typistTracks[i].setFocusable(false);
			
			a.add(typistTracks[i]);
			
			JLabel nameLabel = new JLabel(arr[i].getName());
			nameLabel.setPreferredSize(new Dimension(120, 25));

			a.add(nameLabel);
			ret.add(a);
			ret.add(Box.createVerticalStrut(15));
			
		
		}
		return ret;
	}

}
