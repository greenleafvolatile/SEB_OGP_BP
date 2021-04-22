package mario;

public class Timer {

    private long start = 0;
    private long end;

    public void start() {
        this.start = System.nanoTime();
    }

    public void end() {
        this.end = System.nanoTime();
    }

    public int getTimeInSeconds() {
        long elapsedTime = this.end - this.start;
        return (int) elapsedTime / 1_000_000_000;
    }

    public String getFormatedTime() {
        int secs = getTimeInSeconds();
        return String.format("%02d:%02d:%02d", secs / 3600, (secs % 3600) / 60, secs % 60);
    }
}
