/**
 * Write a description of class Typist here.
 *
 * Starter code generously abandoned by Ty Posaurus, your predecessor,
 * who typed with two fingers and considered that "good enough".
 * He left a sticky note: "the slide-back thing is optional probably".
 * It is not optional. Good luck.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

import java.lang.Math;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Arrays;

public class Typist
{
	
	public enum Sponsors{
		

			NONE("None", "", 0,  (p,t) -> {return false;}), 
			EAGLECOMPUTERS("EagleComputers", "50 moneys if first place", 50, (p, t) -> {return p == 1;}), 
			MACROHARD("MacroHardware","200 moneys if first place using touch screen", 200, (p,t) -> {return (p == 1) && t.getTypistBuffs().getKeyboardType() == KeyboardType.TOUCHSCREEN;}), 
			PROPRIETARYAI("ProprietaryAI","200 moneys if better than second place, using voice input", 200, (p,t) -> {return (p <= 2) && t.getTypistBuffs().getTypingStyle() == TypingStyle.VOICE_TO_TEXT;}), 
			PEAR("PearPc","400 moneys if third place with mechanical keyboard", 50, (p,t) -> {return p == 3 && t.getTypistBuffs().getKeyboardType() == KeyboardType.MECHANICAL;}), 
			HP("HingeProblems Corp","50 moneys if second place", 50, (p,t) -> {return p == 2;}), 
			MSI("MultipleSeriousIssues inc","300 money if first place using hunt and peck", 300, (p,t) -> {return p == 3 && t.getTypistBuffs().getTypingStyle() == TypingStyle.HUNT_AND_PECK;});
			
			
			String displayName;
			String displayDeal;
			int b;

			
			public interface CriteriaMetIf{
				boolean isMet(int pos, Typist t);
			}
			
			CriteriaMetIf f;
			
			Sponsors(String dn, String deal, int bonus, CriteriaMetIf func){
				displayName = dn;
				displayDeal = deal;
				f = func;
				b = bonus;
			}
			
			public int getBonus(){
				return b;
			}
			
			public boolean isMet(int p, Typist t){
				return f.isMet(p, t);
			}
			
			@Override 
			public String toString(){
				
				return this.displayName;
				
			}
			
			public String getDisplayDeal(){
				return displayDeal;
			}
	};
	
    // Fields of class Typist
    // Hint: you will need six fields. Think carefully about their types.
    // One of them tracks how far along the passage the typist has reached.
    // Another tracks whether the typist is currently burnt out.
    // A third tracks HOW MANY turns of burnout remain (not just whether they are burnt out).
    // The remaining three should be fairly obvious.

    String name;
    char avatar;
    int progress;
    boolean isBurntOut;
    int burnOutTurnsRemaining;
    double accuracy;
    int correctAttempts;
    int totalAttempts;
    int totalBurnouts;
    int money, points;
    
    ArrayList<RaceHistory> history;
    
    Color progressColor;
    
    TypistBuffs buffs;

    boolean justMistyped;

    // Constructor of class Typist
    /**
     * Constructor for objects of class Typist.
     * Creates a new typist with a given symbol, name, and accuracy rating.
     *
     * @param typistSymbol  a single Unicode character representing this typist (e.g. '①', '②', '③')
     * @param typistName    the name of the typist (e.g. "TURBOFINGERS")
     * @param typistAccuracy the typist's accuracy rating, between 0.0 and 1.0
     */
    public Typist(char typistSymbol, String typistName, double typistAccuracy)
    {
        avatar = typistSymbol;
        name = typistName;
        setAccuracy(typistAccuracy);
        correctAttempts = 0;
        totalAttempts = 0;
        justMistyped = false;
        buffs = new TypistBuffs();
        progressColor = Color.blue;
        
        history = new ArrayList<RaceHistory>();
        
        totalBurnouts = 0;
        
        money = 0;
        
        points = 0;
    }

    // Methods of class Typist

    /**
     * Returns progress colour of typist
     *
     * @return Color object that represent the progress bar color of the typist
     */
    public Color getProgressColour()
    {
        return progressColor;
    }

	/**
	 * Adds history entry and add points 
	 * @param history entry
	 */
	public void addHistoryEntryAndAddPointsAndMoney(RaceHistory in ){
		history.add(in);
		points += in.getPoints();
		money += in.getMoneys(this);
	}

	/**
	 * sorts array of typists by progress
	 * @param racers array to be sorted.
	 */
	public static void sortRacersByMoney(Typist[] racers){
		Arrays.sort(racers, new Comparator<Typist>() { 
		public int compare(Typist a, Typist b) {
			return -Integer.compare(a.getMoney(), b.getMoney()); //sort in reverse order
		}
		}); //sort racers array by progress so we can find positions of racers
	
	}


	/**
	 * sorts array of typists by progress
	 * @param racer array to be sorted
	 */
	public static void sortRacersByProgress(Typist[] racers){
		Arrays.sort(racers, new Comparator<Typist>() { 
		public int compare(Typist a, Typist b) {
			return -Integer.compare(a.getProgress(), b.getProgress()); //sort in reverse order
		}
		}); //sort racers array by progress so we can find positions of racers
	
	}


	/**
	 * sorts array of typists by progress
	 */
	public static void sortRacersByPoints(Typist[] racers){
		Arrays.sort(racers, new Comparator<Typist>() { 
		public int compare(Typist a, Typist b) {
			return -Integer.compare(a.getPoints(), b.getPoints()); //make sure it sorts in correct order
		}
		}); //sort racers array by points so we can find positions of racers
	
	}

    /**
     * Sets the progress colour of the typist.
     *
     * @param newColour the new progress colour
     */
    public void setProgressColour(Color newColour)
    {
        progressColor = newColour;
    }

    /**
     * Sets this typist into a burnout state for a given number of turns.
     * A burnt-out typist cannot type until their burnout has worn off.
     *
     * @param turns the number of turns the burnout will last
     */
    public void burnOut(int turns)
    {
        if (!isBurntOut)
        {
			++totalBurnouts;
            burnOutTurnsRemaining = turns;
            isBurntOut = true;
            justMistyped = false;
        }
    }

    /**
     * Reduces the remaining burnout counter by one turn.
     * When the counter reaches zero, the typist recovers automatically.
     * Has no effect if the typist is not currently burnt out.
     */
    public void recoverFromBurnout()
    {
        if (burnOutTurnsRemaining > 0)
        {
            --burnOutTurnsRemaining;
            if (burnOutTurnsRemaining == 0)
            {
                isBurntOut = false;
            }
        }
    }

    /**
     * Returns the typist's accuracy rating.
     *
     * @return accuracy as a double between 0.0 and 1.0
     */
    public double getAccuracy()
    {
        return accuracy;
    }
    
    /**
     * Returns the typist's money amount
     *
     * @return moneys as integer
     */
    public int getMoney()
    {
        return money;
    }
    
    
    /**
     * Returns best WPM in history
     * 
     * @return int represenitng WPM
     */
    public int getBestWPM(){
		int best = 0;
		for(RaceHistory i : history){
			if(i.getWPM() > best){
				best = i.getWPM();
			}
		}
		return best;
	}	
    
        /**
     * Returns TypistBuffs object for Typist
     *
     * @return TypistBuffs object
     */
     
     public TypistBuffs getTypistBuffs(){
		 return this.buffs;
	 }

    /**
     * Returns the typist's current progress through the passage.
     * Progress is measured in characters typed correctly so far.
     * Note: this value can decrease if the typist mistypes.
     *
     * @return progress as a non-negative integer
     */
    public int getProgress()
    {
        return progress;
    }

    /**
     * Returns the name of the typist.
     *
     * @return the typist's name as a String
     */
    public String getName()
    {
        return name;
    }

    /**
     * Returns the points of the typist.
     *
     * @return typist points as int
     */
    public int getPoints()
    {
        return points;
    }

    /**
     * Sets the name of the typist.
     *
     * @param newName the new name for the typist
     */
    public void setName(String newName)
    {
        name = newName;
    }

    /**
     * Returns the character symbol used to represent this typist.
     *
     * @return the typist's symbol as a char
     */
    public char getSymbol()
    {
        return avatar;
    }

    /**
     * Returns the number of turns of burnout remaining.
     * Returns 0 if the typist is not currently burnt out.
     *
     * @return burnout turns remaining as a non-negative integer
     */
    public int getBurnoutTurnsRemaining()
    {
        return burnOutTurnsRemaining;
    }

    /**
     * Returns the total number of burnouts so far
     *
     * @return total burnOuts
     */
    public int getTotalBurnouts()
    {
        return totalBurnouts;
    }

    /**
     * Returns the measured accuracy of the typist during the race.
     * Measured accuracy is calculated as correct attempts divided by total attempts.
     * Returns 0.0 if no attempts have been made.
     *
     * @return measured accuracy as a double between 0.0 and 1.0
     */
    public double getMeasuredAccuracy()
    {
        if (totalAttempts == 0)
        {
            return 0.0;
        }
        return (double) correctAttempts / totalAttempts;
    }

    /**
     * Resets the typist to their initial state, ready for a new race.
     * Progress returns to zero, burnout is cleared entirely.
     */
    public void resetToStart()
    {
        progress = 0;
        burnOutTurnsRemaining = 0;
        isBurntOut = false;
        correctAttempts = 0;
        totalAttempts = 0;
        justMistyped = false;
    
		totalBurnouts = 0;

	}

    /**
     * Returns true if this typist is currently burnt out, false otherwise.
     *
     * @return true if burnt out
     */
    public boolean isBurntOut()
    {
        return isBurntOut;
    }

    /**
     * Advances the typist forward by one character along the passage.
     * Should only be called when the typist is not burnt out.
     */
    public void typeCharacter()
    {
        if (!isBurntOut)
        {
            ++progress;
            ++correctAttempts;
            ++totalAttempts;
            justMistyped = false;
        }
    }

    public boolean hasJustMistyped()
    {
        return justMistyped;
    }

    /**
     * Moves the typist backwards by a given number of characters (a mistype).
     * Progress cannot go below zero — the typist cannot slide off the start.
     *
     * @param amount the number of characters to slide back (must be positive)
     */
    public void slideBack(int amount)
    {
        if (amount < 0)
        {
            return;
        }
        progress = Math.max(progress - amount, 0);
        ++totalAttempts; // can slideBack multiple progresses for only 1 mistype
        justMistyped = true;
    }

    /**
     * Sets the accuracy rating of the typist.
     * Values below 0.0 should be set to 0.0; values above 1.0 should be set to 1.0.
     *
     * @param newAccuracy the new accuracy rating
     */
    public void setAccuracy(double newAccuracy)
    {
        accuracy = Math.clamp(newAccuracy, 0, 1);
    }

    /**
     * Sets the symbol used to represent this typist.
     *
     * @param newSymbol the new symbol character
     */
    public void setSymbol(char newSymbol)
    {
        avatar = newSymbol;
    }

    public static void main(String[] in)
    {
        Typist a = new Typist('a', "", 0.0);

        System.out.println("-- progress below zero test");
        System.out.println("init progress: " + a.getProgress());
        a.slideBack(99);
        System.out.println("new progress: " + a.getProgress());

        System.out.println("-- burnout test");
        a.burnOut(1);
        System.out.println("init burnout: " + a.isBurntOut + " " + a.burnOutTurnsRemaining);
        a.recoverFromBurnout();
        System.out.println("new burnout: " + a.isBurntOut + " " + a.burnOutTurnsRemaining);

        System.out.println("-- resetToStart test ");
        a.typeCharacter();
        a.typeCharacter();
        a.typeCharacter();
        a.burnOut(99);

        System.out.println("init progress: " + a.getProgress());
        System.out.println("init burnout: " + a.isBurntOut + " " + a.burnOutTurnsRemaining);
        a.resetToStart();
        System.out.println("new progress: " + a.getProgress());
        System.out.println("new burnout: " + a.isBurntOut + " " + a.burnOutTurnsRemaining);

        System.out.println("--- setAccuracy clamp test (attempt to set to 99)");
        a.setAccuracy(99);
        System.out.println(a.getAccuracy());

        System.out.println("--- typeCharacter forward movement test");
        System.out.println("init progress: " + a.getProgress());
        a.typeCharacter();
        System.out.println("new progress: " + a.getProgress());
    }
}
