package mario;

import java.util.concurrent.TimeUnit;

public class Timer {

    private long start;
    private long end;

    private MainApp app;

    public Timer(MainApp app) {
        this.app = app;
        this.start = app.millis();
    }

    private boolean active = false;

    public void start() {
        this.start = this.app.millis();
        this.active = true;
    }

    public void end() {
        this.end = this.app.millis();
        this.active = false;
    }

    public double getSeconds() {
        return (double) (this.app.millis() - this.start) / 1000;
    }

    public String getFormatedTime() {
        int secs = (int) getSeconds();
        return String.format("%02d:%02d:%02d", secs / 3600, (secs % 3600) / 60, secs % 60);
    }

    public boolean isActive() {
        return active;
    }
}
