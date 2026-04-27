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
	long startTime;
	
	JFrame rmWin;
	
	public void start(JFrame rmw){
		
		rmWin = rmw;
		
		for(Typist i : tr.getTypists()){
			i.resetToStart();
		}
		
		win.setVisible(true);
		raceSched = new Timer(RACE_TICK_PERIOD_MS, new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				raceTick();
			}
		
		});
		
		startTime = System.currentTimeMillis() ;

		raceSched.start(); //starts the race
		
		

	}
	
	private void raceTick (){
		Typist[] racers = tr.getTypists();
		
		if(race_ticks % 1 == 0){
			for(int i=0; i<racers.length; ++i){
				Typist currT = racers[i];
				tr.advanceTypist(currT);
				
				int prog = currT.getProgress();
				
				if(prog >= (tr.getPassage().length())){ //race ended
					handleRaceEnded(currT, System.currentTimeMillis()  - startTime);
					return;
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
	
	private int countWords(String a){
		String trim = a.trim();
		if (trim.isEmpty())
			return 0;
		return trim.split("\\s+").length; // separate string around spaces
	}
	
	private void handleRaceEnded(Typist winner, long msecs){
			
			raceSched.stop();
			
			JDialog rd = new JDialog(win, "Results", true);

			rd.setLocationRelativeTo(win);
			rd.setResizable(false);
			
			
			JPanel resultsPanel = new JPanel();
			resultsPanel.setLayout(new BoxLayout(resultsPanel, BoxLayout.Y_AXIS));
			resultsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));


			
			JLabel winf = new JLabel("Winner: " + winner.getName());
			resultsPanel.add(winf);


			
			Typist[] racers = tr.getTypists();
			
			Typist.sortRacersByProgress(racers);

			int pos = 1;
			for(Typist i : racers){
				double wordsTyped = ((double)countWords(tr.getPassage())) * ((double)i.getProgress() / (double)tr.getPassage().length());
				long wpm = Math.round(wordsTyped / ((double)msecs / 1000/ 60));
				
				RaceHistory r = new RaceHistory(pos, (int)wpm, i.getTotalBurnouts(), i.getMeasuredAccuracy());
				resultsPanel.add(new JLabel(r.toString(i)));
				i.addHistoryEntryAndAddPointsAndMoney(r);
				
				++pos;
			}
			
			resultsPanel.add(Box.createVerticalStrut(15));
			
			JPanel buttonPanel = new JPanel();
			buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
			buttonPanel.setAlignmentX(JPanel.CENTER_ALIGNMENT);
			
			JButton okButton = new JButton("OK");
			okButton.setPreferredSize(new Dimension(100, 35));
			okButton.addActionListener(e -> rd.dispose());
			
			buttonPanel.add(Box.createHorizontalGlue());
			buttonPanel.add(okButton);
			buttonPanel.add(Box.createHorizontalGlue());
			
			resultsPanel.add(buttonPanel);

			rd.add(resultsPanel);

			rd.pack();

			rd.setVisible(true);
			
			win.setVisible(false);
			
			rmWin.setVisible(true);
			
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
			
			Font font = new Font(Font.SANS_SERIF, Font.PLAIN, 15);
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
