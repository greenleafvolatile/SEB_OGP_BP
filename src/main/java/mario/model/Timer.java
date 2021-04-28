package mario.model;

/**
 * The type Timer, based on System nanoTime.
 */
public class Timer {

    private long start;

    /**
     * Start timer.
     */
    public void startTimer() {
        this.start = System.nanoTime();
    }

    /**
     * Gets elapsed time in seconds.
     *
     * @return the elapsed time
     */
    public double getElapsedTime() {
        return (System.nanoTime() - this.start) / 1_000_000_000.0;
    }


    /**
     * Gets formatted time in string format MM:SS.
     *
     * @param seconds the seconds
     * @return the formatted time
     */
    public String formatToString(double seconds) {
        return String.format("%02d:%02d", ((int) seconds % 3600) / 60, (int) seconds % 60);
    }

}

