/**
 * Enum representing different typing styles and input methods.
 * Each style has a user-friendly name displayed in the UI.
 */
public enum TypingStyle
{
    TOUCH_TYPIST("Touch Typist", 1, 0),
    HUNT_AND_PECK("Hunt & Peck", -2, 1),
    PHONE_THUMBS("Phone Thumbs", -1, 0),
    VOICE_TO_TEXT("Voice-to-Text",-1, -2);

    private final String displayName;
	private int burnOutBuff, accuracyPenalty;

    /**
     * Constructor for TypingStyle enum.
     *
     * @param displayName the user-friendly name to display in the UI
     */
    TypingStyle(String displayName, int bob, int ap)
    {
        this.displayName = displayName;
        burnOutBuff = bob;
        accuracyPenalty = ap;
    }
    
    int getAccuracyBuff(){
		return accuracyPenalty;
	}
	
	int getBurnOutBuff(){
		return burnOutBuff;
	}

    /**
     * Returns the user-friendly name of this typing style.
     *
     * @return the display name
     */
    @Override
    public String toString()
    {
        return displayName;
    }
}
