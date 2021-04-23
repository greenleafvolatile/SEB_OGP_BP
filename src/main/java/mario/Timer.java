package mario;

import java.util.concurrent.TimeUnit;

public class Timer {

    private double start;
    private double end;

    private final MainApp app;

    public Timer(MainApp app) {
        this.app = app;
    }

    public void startTimer() {
        this.start = this.app.millis();
    }

    public void endTimer() {
        this.end = getSeconds();
    }

    public double getSeconds() {
        return (this.app.millis() - this.start) / 1000;
    }

    public String getFormatedTime() {
        int secs = (int) getSeconds();
        return String.format("%02d:%02d", (secs % 3600) / 60, secs % 60);
    }

    public double getElapsedSeconds() {
        return this.end;
    }

    public String getFormatedFinalTime() {
        int secs = (int) getElapsedSeconds();
        return String.format("%02d:%02d", (secs % 3600) / 60, secs % 60);
    }
}

