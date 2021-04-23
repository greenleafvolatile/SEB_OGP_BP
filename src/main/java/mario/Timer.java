package mario;

import java.util.concurrent.TimeUnit;

public class Timer {

    private double start;
    private double end;
    private boolean active = false;

    private final MainApp app;

    public Timer(MainApp app) {
        this.app = app;
        this.start = app.millis();
    }

    public void start() {
        this.start = this.app.millis();
        this.active = true;
    }

    public void end() {
        this.end = getSeconds();
        this.active = false;
    }

    public double getSeconds() {
        return (this.app.millis() - this.start) / 1000;
    }

    public String getFormatedTime() {
        int secs = (int) getSeconds();
        return String.format("%02d:%02d:%02d", secs / 3600, (secs % 3600) / 60, secs % 60);
    }

    public double getElapsedSeconds() {
        return this.end;
    }

    public String getFormatedFinalTime() {
        int secs = (int) getElapsedSeconds();
        return String.format("%02d:%02d:%02d", secs / 3600, (secs % 3600) / 60, secs % 60);
    }

    public boolean isActive() {
        return active;
    }
}

