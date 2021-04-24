package mario;

public class TimeFormatter {

    private TimeFormatter() {}

    public static String format(double time) {

        int secs = (int) time;
        return String.format("%02d:%02d", (secs % 3600) / 60, secs % 60);
    }
}
