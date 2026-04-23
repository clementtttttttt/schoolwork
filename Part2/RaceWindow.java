import javax.swing.*;
import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.text.Highlighter;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.BadLocationException;


/**
 * RaceWindow provides the real juicy typing race bits and graphs.
 */
public class RaceWindow 
{
	TypingRace tr;
	private int race_ticks;
	private final int RACE_TICK_PERIOD_MS = 16;
	
	private JFrame win;

	Timer raceSched;

	JTextPane [] typistTracks;
	RaceWindow(TypingRace in){
		tr = in;
		
		win = new JFrame("RACE");
        win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        win.setLocationRelativeTo(null);
        win.setResizable(false);

        // Create main panel with padding
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new FlowLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));


		JPanel raceFieldPanel = createRacingField(); 
		mainPanel.add(raceFieldPanel);

		win.add(mainPanel);
		
		win.pack();
		
		race_ticks = 0;
		

	}
	
	public void start(){
		
		win.setVisible(true);
		raceSched = new Timer(RACE_TICK_PERIOD_MS, new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				race();
			}
		
		});
		raceSched.start(); //starts the race
		
		

	}
	
	private void race(){
		Typist[] racers = tr.getTypists();
		
		if(race_ticks % 1 == 0){
			for(int i=0; i<racers.length; ++i){
				Typist currT = racers[i];
				tr.advanceTypist(currT);
				
				int prog = currT.getProgress();
				
				if(prog >= (tr.getPassage().length()-1)){ //race ended
					handleRaceEnded(currT);
				}
				
				JTextPane currTT = typistTracks[i];
				
				
				StringBuilder a = new StringBuilder(tr.getPassage());
				a.setCharAt(prog, currT.getSymbol());
				currTT.setText(a.toString());
				
				currTT.setCaretPosition(prog);
				
				

				
				Highlighter.HighlightPainter painter = new DefaultHighlighter.DefaultHighlightPainter(currT.getProgressColour());
				Highlighter h = currTT.getHighlighter();
				h.removeAllHighlights(); //clear old highlight
				try{
					h.addHighlight(0, prog, painter);
				}catch(Exception e){
					
				}
				
				
			}
		}
		
		
		++race_ticks;
	}
	
	private void handleRaceEnded(Typist winner){
			System.out.println("RACE ENDED!!!!!");
			
			raceSched.stop();
			win.setVisible(false);
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
			
			Font font = new Font(Font.MONOSPACED, Font.PLAIN, 15);
			typistTracks[i].setFont(font);
			
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
