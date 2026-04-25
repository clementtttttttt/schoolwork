/**
 * RaceHistory stores the results of a single typist's performance in a race.
 * Contains information about position, words per minute, measured accuracy, and burnouts.
 */
public class RaceHistory
{
	private int position;
	private int wpm;
	private int burnouts;
	private double measuredAccuracy;
	
	public static int getBestWPM(RaceHistory[] in){
		int best = 0;
		
		for(RaceHistory i : in){
			if(i.wpm > best){
				best = i.wpm;
			}
		}
		return best;
	}	

	/**
	 * Constructor for RaceHistory.
	 * 
	 * @param position the finishing position (1 for first place, 2 for second, etc.)
	 * @param wpm the words per minute achieved
	 * @param burnouts the number of burnout events
	 * @param measuredAccuracy the measured accuracy as a decimal between 0.0 and 1.0
	 */
	public RaceHistory(int position, int wpm, int burnouts, double measuredAccuracy)
	{
		this.position = position;
		this.wpm = wpm;
		this.burnouts = burnouts;
		this.measuredAccuracy = measuredAccuracy;
	}

	/**
	 * Returns string that shows results in human readable manner
	 * 
	 * @param racer object
	 */
	public String toString(Typist in){
		
		String resultFormatString = "%s: WPM %d REALACC%% %.2f(%+.2f) BRNS %d"; 
		return String.format(resultFormatString, in.getName(), wpm, measuredAccuracy, measuredAccuracy - in.getAccuracy(), burnouts);
		
	}

	/**
	 * Returns the finishing position.
	 * 
	 * @return the position
	 */
	public int getPosition()
	{
		return position;
	}

	/**
	 * Sets the finishing position.
	 * 
	 * @param position the new position
	 */
	public void setPosition(int position)
	{
		this.position = position;
	}

	/**
	 * Returns the words per minute achieved.
	 * 
	 * @return the WPM
	 */
	public int getWPM()
	{
		return wpm;
	}

	/**
	 * Sets the words per minute.
	 * 
	 * @param wpm the new WPM value
	 */
	public void setWPM(int wpm)
	{
		this.wpm = wpm;
	}

	/**
	 * Returns the number of burnout events.
	 * 
	 * @return the burnout count
	 */
	public int getBurnouts()
	{
		return burnouts;
	}

	/**
	 * Sets the number of burnout events.
	 * 
	 * @param burnouts the new burnout count
	 */
	public void setBurnouts(int burnouts)
	{
		this.burnouts = burnouts;
	}

	/**
	 * Returns the measured accuracy.
	 * 
	 * @return the measured accuracy as a decimal between 0.0 and 1.0
	 */
	public double getMeasuredAccuracy()
	{
		return measuredAccuracy;
	}

	/**
	 * Sets the measured accuracy.
	 * 
	 * @param measuredAccuracy the new measured accuracy
	 */
	public void setMeasuredAccuracy(double measuredAccuracy)
	{
		this.measuredAccuracy = measuredAccuracy;
	}
}
