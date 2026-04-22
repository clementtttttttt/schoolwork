import javax.swing.*;
import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


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
        mainPanel.setLayout(new FlowLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));


		JPanel raceFieldPanel = createRacingField(); 
		mainPanel.add(raceFieldPanel);

		add(mainPanel);
		
		pack();
		setVisible(true);

		new Timer(16, new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				race();
			}
		
		}).start(); //starts the race
	}
	
	
	private void race(){
			System.out.println("Tick");
	}
	
	JPanel createRacingField(){
		Typist[] arr = tr.getTypists();
		typistTracks = new JTextPane[arr.length];
		
		JPanel ret = new JPanel();
        ret.setLayout(new BoxLayout(ret, BoxLayout.Y_AXIS));

		for(int i=0; i<arr.length; ++i){
			JPanel a = new JPanel();	
			a.setLayout(new BoxLayout(a, BoxLayout.X_AXIS));
			a.setMaximumSize(new Dimension(1000, 25));
			a.setAlignmentX(JPanel.LEFT_ALIGNMENT);

			typistTracks[i] = new JTextPane(){

				
			};
			typistTracks[i].setFocusable(false);
			typistTracks[i].setEditable(false);
			typistTracks[i].setText(tr.getPassage());
			typistTracks[i].setCaretPosition(0);
			
			JScrollPane sp = new JScrollPane(typistTracks[i],
			JScrollPane.VERTICAL_SCROLLBAR_NEVER,
			JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			sp.setPreferredSize(new Dimension(700, 25));
			sp.setMaximumSize(new Dimension(700, 25));
			sp.setFocusable(false);
			sp.setWheelScrollingEnabled(false);
			a.add(sp);

			
			JLabel nameLabel = new JLabel(arr[i].getName());
			nameLabel.setPreferredSize(new Dimension(120, 25));
			a.add(nameLabel);
			
			ret.add(a);
			ret.add(Box.createVerticalStrut(10));
		
		}
		return ret;
	}

}
