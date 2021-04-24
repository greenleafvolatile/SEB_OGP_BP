package mario;

/**
 * The type Timer.
 */
public class Timer {

    private int start;
    private int end;

    private final MainApp app;

    /**
     * Instantiates a new Timer.
     *
     * @param app the app
     */
    public Timer(MainApp app) {
        this.app = app;
    }

    /**
     * Start timer.
     */
    public void startTimer() {
        this.start = this.app.millis();
    }

    /**
     * Stop timer.
     */
    public void stopTimer() {
        this.end = this.app.millis();
    }

    public double getTotalElapsedTime() {
        return (this.end - this.start) / 1000f;
    }


    /**
     * Gets elapsed time.
     *
     * @return the elapsed time
     */
    public double getElapsedTime() {
        return (this.app.millis() - this.start) / 1000f;
    }


    /**
     * Gets formatted time in String format MM:SS.
     *
     * @param seconds the seconds
     * @return the formatted time
     */
    public String getFormattedTime(int seconds) {
        return String.format("%02d:%02d", (seconds % 3600) / 60, seconds % 60);
    }

}

