/**
 * Enum representing different typing styles and input methods.
 * Each style has a user-friendly name displayed in the UI.
 */
public enum TypingStyle
{
    TOUCH_TYPIST("Touch Typist"),
    HUNT_AND_PECK("Hunt & Peck"),
    PHONE_THUMBS("Phone Thumbs"),
    VOICE_TO_TEXT("Voice-to-Text");

    private final String displayName;

    /**
     * Constructor for TypingStyle enum.
     *
     * @param displayName the user-friendly name to display in the UI
     */
    TypingStyle(String displayName)
    {
        this.displayName = displayName;
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
