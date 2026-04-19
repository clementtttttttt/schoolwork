/**
 * Enum representing different keyboard types for typing.
 * Each type has a user-friendly name displayed in the UI.
 */
public enum KeyboardType
{
    MECHANICAL("Mechanical"),
    MEMBRANE("Membrane"),
    TOUCHSCREEN("Touchscreen"),
    STENOGRAPHY("Stenography");

    private final String displayName;

    /**
     * Constructor for KeyboardType enum.
     *
     * @param displayName the user-friendly name to display in the UI
     */
    KeyboardType(String displayName)
    {
        this.displayName = displayName;
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
