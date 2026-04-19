/**
 * Enum representing different keyboard types for typing.
 * Each type has a user-friendly name displayed in the UI.
 */
public enum KeyboardType
{
    MECHANICAL("Mechanical", 1, 0),
    MEMBRANE("Membrane", 0, 0),
    TOUCHSCREEN("Touchscreen", -1, -1),
    STENOGRAPHY("Stenography", 2,  -1);

    private final String displayName;
	private int speedBonus, accuracyPenalty;


    /**
     * Constructor for KeyboardType enum.
     *
     * @param displayName the user-friendly name to display in the UI
     */
    KeyboardType(String displayName, int sb, int accPenalty)
    {
        this.displayName = displayName;
		speedBonus = sb;
		accuracyPenalty = accPenalty;
    }

    int getAccuracyPenalty(){
		return accuracyPenalty;
	}
	
	int getSpeedBonus(){
		return speedBonus;
	}

    /**
     * Returns the user-friendly name of this keyboard type.
     *
     * @return the display name
     */
    @Override
    public String toString()
    {
        return displayName;
    }
}
