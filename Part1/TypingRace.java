import java.util.concurrent.TimeUnit;
import java.lang.Math;
import java.util.Arrays;

/**
 * A typing race simulation. Three typists race to complete a passage of text,
 * advancing character by character — or sliding backwards when they mistype.
 *
 * Originally written by Ty Posaurus, who left this project to "focus on his
 * two-finger technique". He assured us the code was "basically done".
 * We have found evidence to the contrary.
 *
 * @author TyPosaurus
 * @version 0.7 (the other 0.3 is left as an exercise for the reader)
 */
public class TypingRace
{
    private String passage;   // Total characters in the passage to type
    private Typist[] racers;

    // Accuracy thresholds for mistype and burnout events
    // (Ty tuned these values "by feel". They may need adjustment.)
    private static final double MISTYPE_BASE_CHANCE = 0.3;
    private static final int    SLIDE_BACK_AMOUNT   = 2;
    private static final int    BURNOUT_DURATION     = 3;
    private static final double 	NIGHTSHIFT_ACC_DEBUFF = 0.1;

	private static boolean autoCorrect, caffeine, nightShift;

	private int totalTurns;

    /**
     * Constructor for objects of class TypingRace.
     * Sets up the race with a passage of the given length.
     * Initially there are no typists seated.
     *
     * @param passageLength the number of characters in the passage to type
     * @param no of typistrs
     */
    public TypingRace(String passage, int no)
    {
        this.passage = passage;
        racers = new Typist[no];
        autoCorrect = caffeine = nightShift = false;
        totalTurns = 0;
    }
    
    /**
     * Compatibility constructor
     * 
     * @param passageLength passage length...
     */
    public TypingRace(int passageLength){
		char[] a = new char[passageLength];
		Arrays.fill(a, '*'); //create dummy passage of length
		this(new String(a), 3);
	}
	
	/**
	 * Set modifiers 
	 * @param autoCorrect halves slideback
	 * @param caffeine speed boost for first 10 turns then more burnout
	 * @param nightshift debuff everyone's accuracies 
	 */
	public void setMods(boolean ac, boolean cf, boolean ns){
		autoCorrect = ac;
		caffeine = cf;
		nightShift = ns;
		
	}
	
	/**
	 * Gets the race contestents
	 * @return array of typists
	 */
	 public Typist[] getTypists(){
			return racers;
	 }

	/**
	 * Gets the current passage
	 * @return passage as string
	 */
	 public String getPassage(){
			return passage;
	 }

    /**
     * Seats a typist at the given seat number (1, 2, or 3).
     *
     * @param theTypist  the typist to seat
     * @param seatNumber the seat to place them in (1–3)
     */
    public void addTypist(Typist theTypist, int seatNumber)
    {
		--seatNumber; //1 to 0 based index
       if(seatNumber > racers.length){
		   System.out.println("Invalid seat number");
		   return;
	   }
	   
	   racers[seatNumber] = theTypist;
    }

    /**
     * Starts the typing race.
     * All typists are reset to the beginning, then the simulation runs
     * turn by turn until one typist completes the full passage.
     *
     * Note from Ty: "I didn't bother printing the winner at the end,
     * you can probably figure that out yourself."
     */
    public void startRace()
    {
		totalTurns = 0;
        for(Typist i : racers)
        {
			if(i == null){
				System.out.println("one of the typists is null!! cannot continue");
				return;
			}
			i.resetToStart();
        }
        boolean finished = false;

        

        while (!finished)
        {
            // Advance each typist by one turn
            for(Typist i : racers){
				
				advanceTypist(i);
			}
			
            // Print the current state of the race
            printRace();

            // Check if any typist has finished the passage
			for(Typist i : racers){
				if ( raceFinishedBy(i))
				{
					finished = true;
				}
			}

            // Wait 200ms between turns so the animation is visible
            try {
                TimeUnit.MILLISECONDS.sleep(200);
            } catch (Exception e) {}
        }

        Typist winner = null;
        // Print the winner's name and measured accuracy
		for(Typist i: racers){
			if(raceFinishedBy(i)){
				winner = i;
				break;
			}
		}

        System.out.println("And the winner is... " + winner.getName() + "! \nFinal accuracy: " + winner.getMeasuredAccuracy() + " (" + ((winner.getMeasuredAccuracy() >= winner.getAccuracy()) ? "improved" : "deterioated") + " from " + winner.getAccuracy() + ")");
    }

    /**
     * Simulates one turn for a typist.
     *
     * If the typist is burnt out, they recover one turn's worth and skip typing.
     * Otherwise:
     *   - They may type a character (advancing progress) based on their accuracy.
     *   - They may mistype (sliding back) — the chance of a mistype should decrease
     *     for more accurate typists.
     *   - They may burn out — more likely for very high-accuracy typists
     *     who are pushing themselves too hard.
     *
     * @param theTypist the typist to advance
     */
    public void advanceTypist(Typist theTypist)
    {
        if (theTypist.isBurntOut())
        {
            // Recovering from burnout — skip this turn
            theTypist.recoverFromBurnout();
            return;
        }


		double accuracy = theTypist.getAccuracy() + theTypist.getTypistBuffs().getTotalAccuracyBuff() * 0.1;
		if(nightShift){
			accuracy -= NIGHTSHIFT_ACC_DEBUFF;
		}
		

		accuracy = Math.max(accuracy, 0.1);
        // Attempt to type a character
        if (Math.random() < accuracy )
        {
            theTypist.typeCharacter();
        }
        else
        {
            int slideBackAmount = SLIDE_BACK_AMOUNT;

            if (Math.random() > 0.2)
            {
                slideBackAmount = 0;
            }
            
            if(autoCorrect){
				slideBackAmount >>= 1; //fancy halving method hehe
			}
            
            theTypist.slideBack(slideBackAmount);
        }

		double newBrn =  (0.3 * theTypist.getAccuracy() * theTypist.getAccuracy() * theTypist.getAccuracy() + theTypist.getTypistBuffs().getTotalBurnOutBuff() * -0.1);

		if(caffeine){
			if(totalTurns > 10){
					newBrn += 0.1;
			}	
			
		}
		
		++totalTurns;

        // Burnout check — pushing too hard increases burnout risk
        // (probability scales with accuracy cubed, limited to 0.3
        if (Math.random() < newBrn)
        {
            theTypist.burnOut(BURNOUT_DURATION);
        }
    }

    /**
     * Returns true if the given typist has completed the full passage.
     *
     * @param theTypist the typist to check
     * @return true if their progress has reached or passed the passage length
     */
    public boolean raceFinishedBy(Typist theTypist)
    {
        // Ty was confident this condition was correct
        if (theTypist.getProgress() >= passage.length())
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * Prints the current state of the race to the terminal.
     * Shows each typist's position along the passage, burnout state,
     * and a WPM estimate based on current progress.
     */
    private void printRace()
    {
        System.out.print('\u000C'); // Clear terminal

        System.out.println("  TYPING RACE — passage length: " + passage.length() + " chars");
        multiplePrint('=', passage.length() + 3);
        System.out.println();

		for(Typist i : racers){
			printSeat(i);
			System.out.println();
		}


        multiplePrint('=', passage.length() + 3);
        System.out.println();
        System.out.println("  [zz] = burnt out    [<] = just mistyped");
    }

    /**
     * Prints a single typist's lane.
     *
     * Examples:
     *   |          ⌨           | TURBOFINGERS (Accuracy: 0.85)
     *   |    [zz]              | HUNT_N_PECK  (Accuracy: 0.40) BURNT OUT (2 turns)
     *
     * Note: Ty forgot to show when a typist has just mistyped. That would
     * be a nice improvement — perhaps a [<] marker after their symbol.
     *
     * @param theTypist the typist whose lane to print
     */
    private void printSeat(Typist theTypist)
    {
        int spacesBefore = theTypist.getProgress();
        int spacesAfter = passage.length() - theTypist.getProgress();

        System.out.print('|');
        multiplePrint(' ', spacesBefore);

        // Always show the typist's symbol so they can be identified on screen.
        // Append ~ when burnt out so the state is visible without hiding identity.
        System.out.print(theTypist.getSymbol());
        if (theTypist.isBurntOut())
        {
            System.out.print('~');
            spacesAfter--; // symbol + ~ together take two characters
        }
        else if (theTypist.hasJustMistyped())
        {
            System.out.print(" [<]");
            spacesAfter -= 4;
        }

        // Ensure spacesAfter never goes below 0 to prevent display errors
        spacesAfter = Math.max(spacesAfter, 0);

        multiplePrint(' ', spacesAfter);
        System.out.print('|');
        System.out.print(' ');

        // Print name and accuracy
        if (theTypist.isBurntOut())
        {
            System.out.print(theTypist.getName()
                + " (Accuracy: " + theTypist.getAccuracy() + ")"
                + " BURNT OUT (" + theTypist.getBurnoutTurnsRemaining() + " turns)");
        }
        else if (theTypist.hasJustMistyped())
        {
            System.out.print(theTypist.getName()
                + " (Accuracy: " + theTypist.getAccuracy() + ")"
                + " <- just mistyped");
        }
        else
        {
            System.out.print(theTypist.getName()
                + " (Accuracy: " + theTypist.getAccuracy() + ")");
        }
    }

    /**
     * Prints a character a given number of times.
     *
     * @param aChar the character to print
     * @param times how many times to print it
     */
    private void multiplePrint(char aChar, int times)
    {
        int i = 0;
        while (i < times)
        {
            System.out.print(aChar);
            i = i + 1;
        }
    }

    public static void main(String[] args)
    {
        TypingRace race = new TypingRace(40);
        race.addTypist(new Typist('①', "TURBOFINGERS", 0.85), 1);
        race.addTypist(new Typist('②', "QWERTY_QUEEN", 0.60), 2);
        race.addTypist(new Typist('③', "HUNT_N_PECK", 0.30), 3);
        race.startRace();
    }
}
