package tutorials;

import nl.han.ica.oopg.alarm.Alarm;
import nl.han.ica.oopg.alarm.IAlarmListener;

import java.util.Random;

public class LightningBallSpawner implements IAlarmListener {

    private TutorialWorld world;
    private Random random;
    private int discsPerSecond;


    public LightningBallSpawner(TutorialWorld world, int discsPerSecond) {
        this.world = world;
        random = new Random();
        this.discsPerSecond = discsPerSecond;
        startAlarm();
    }

    private void startAlarm() {
        Alarm alarm = new Alarm("New Disc", 1 / discsPerSecond);
        alarm.addTarget(this);
        alarm.start();
    }

    @Override
    public void triggerAlarm(String s) {
        LightningBall lightningBall = new LightningBall(world);
        world.addGameObject(lightningBall, random.nextInt(world.width), 50);
        startAlarm();
    }


}
