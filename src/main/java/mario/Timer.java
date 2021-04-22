package mario;

import java.util.concurrent.TimeUnit;

public class Timer {

    private long start = System.nanoTime();
    private long end;

    private long start2;

    private MainApp app;

    public Timer(MainApp app) {
        this.app = app;
        this.start2 = 0;
    }

    private boolean active = false;

    public void start() {
        this.start = System.nanoTime();
        this.start2 = this.app.millis();
        this.active = true;
    }

    public void end() {
        this.end = System.nanoTime();
        this.active = false;
    }

    public long getElapsedTimeInSeconds() { ;
        return System.nanoTime() - this.start;
    }

    public double getSeconds() {
        return (double) this.start2 / 1000;
    }

    public double inSeconds() {
        return (double) TimeUnit.SECONDS.convert(getElapsedTimeInSeconds(), TimeUnit.NANOSECONDS);
    }

    public String getFormatedTime2() {
        int secs = (int) getSeconds();
        return String.format("%02d:%02d:%02d", secs / 3600, (secs % 3600) / 60, secs % 60);
    }

    public String getFormatedTime() {
        int secs = (int) inSeconds();
        return String.format("%02d:%02d:%02d", secs / 3600, (secs % 3600) / 60, secs % 60);
    }

    public boolean isActive() {
        return active;
    }

//        public static void main(String[] args) {
//        Timer time = new Timer();
//        time.start();
//
//        while(time.active) {
//            System.out.println(time.getFormatedTime());
//        }
//
//    }
}
