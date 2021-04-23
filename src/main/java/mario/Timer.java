package mario;

/**
 * The type Timer.
 */
public class Timer {

    private double start;
    private double end;

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
     * End timer.
     */
    public void endTimer() {
        this.end = getSeconds();
    }

    /**
     * Gets seconds.
     *
     * @return the seconds
     */
    public double getSeconds() {
        return (this.app.millis() - this.start) / 1000;
    }

    /**
     * Gets formated time as String MM:SS.
     *
     * @return the formated time
     */
    public String getFormatedTime() {
        int secs = (int) getSeconds();
        return String.format("%02d:%02d", (secs % 3600) / 60, secs % 60);
    }

    /**
     * Gets elapsed seconds.
     *
     * @return the elapsed seconds
     */
    public double getElapsedSeconds() {
        return this.end;
    }

    /**
     * Gets formated final time as String MM:SS.
     *
     * @return the formated final time
     */
    public String getFormatedFinalTime() {
        int secs = (int) getElapsedSeconds();
        return String.format("%02d:%02d", (secs % 3600) / 60, secs % 60);
    }
}

