package mario;

/**
 * Used to get a formatted String representation of the time.
 */
public final class TimeFormatter {

    private TimeFormatter() {}

    /**
     * Gets a formatted String representation of the time.
     * @param time the time represented as a double value.
     * @return String a String representation of the time.
     */
    public static String format(double time) {

        int secs = (int) time;
        return String.format("%02d:%02d", (secs % 3600) / 60, secs % 60);
    }
}
