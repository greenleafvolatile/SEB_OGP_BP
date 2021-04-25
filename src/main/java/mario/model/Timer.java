package mario.model;

import mario.MainApp;

/**
 * The type Timer.
 */
public class Timer {

    private long start;
    private long end;

    /**
     * Start timer.
     */
    public void startTimer() {
        this.start = System.nanoTime();
    }

    /**
     * Stop timer.
     */
    public void stopTimer() {
        this.end = System.nanoTime();
    }

    /**
     * Gets total elapsed time.
     *
     * @return the total elapsed time
     */
    public double getTotalElapsedTime() {
        return (this.end - this.start) / 1_000_000_000.0;
    }


    /**
     * Gets elapsed time.
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

