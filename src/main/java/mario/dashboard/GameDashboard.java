package mario.dashboard;

import mario.MainApp;
import mario.TimeFormatter;
import mario.Timer;
import nl.han.ica.oopg.dashboard.Dashboard;
import nl.han.ica.oopg.objects.TextObject;
import nl.han.ica.oopg.view.PGraphicsCreator;
import processing.core.PGraphics;

import java.util.ArrayList;
import java.util.List;

/**
 * A dashboard.
 *
 */
public class GameDashboard extends Dashboard {

    private final int xMargin = 40;
    private final int yMargin = 20;
    private final List<HeartSprite> hearts = new ArrayList<>();
    private final String playerName;

    private int fontSize;
    private int numberOfKeysShown = 0;
    private MainApp app;
    private Timer timer;
    private TextObject time;
    private PGraphics graphics;

    {
        final int numberOfHearts = 3;
        for (int i = 0; i < numberOfHearts; i++) {
            hearts.add(new HeartSprite());
        }
    }

    /**
     * Instantiates a new Game dashboard.
     *
     * @param app        the app
     * @param x          the x
     * @param y          the y
     * @param width      the width
     * @param height     the height
     * @param playerName the player name
     */
    public GameDashboard(MainApp app, float x, float y, float width, float height, String playerName) {

        super(x, y, width, height);
        this.playerName = playerName;
        this.graphics = new PGraphicsCreator().createPGraphics((int) width, (int) height);
        this.app = app;
        this.timer = new Timer(this.app);
        this.init();
    }

    private void init() {

        this.fontSize = 32;
        this.graphics.textSize(fontSize);

        String keysLabelText = "keys:";

        this.addLabel("Player:" + this.playerName, xMargin, yMargin, fontSize, 255, 255, 255);
        this.addLabel(keysLabelText, (int) (this.width - xMargin - this.graphics.textWidth(keysLabelText)), yMargin, fontSize, 255, 255, 255);

        this.createTimeLabel();

        this.addHearts();

        this.timer.startTimer();

    }

    private void createTimeLabel() {
        this.time = new TextObject(TimeFormatter.format(this.timer.getElapsedTime()), 32);
        this.time.setForeColor(255, 255, 255, 255);
        this.addGameObject(time, (int) (this.width / 2 - graphics.textWidth(TimeFormatter.format(this.timer.getElapsedTime())) / 2), yMargin);
    }

    @Override
    public void update() {
        this.time.setText(TimeFormatter.format(this.timer.getElapsedTime()));
    }


    private void addLabel(String text, int xPos, int yPos, int fontSize, int red, int green, int blue) {

        final int alpha = 255;

        TextObject textObject = new TextObject(text, fontSize);
        textObject.setForeColor(red, green, blue, alpha);

        this.addGameObject(textObject, xPos, yPos);
    }

    private void addHearts() {

        final int y = yMargin + fontSize;

        int x = xMargin;

        for (HeartSprite heartSprite : this.hearts) {

            this.addGameObject(heartSprite, x, y + (int) (heartSprite.getHeight() / 2f));
            x += heartSprite.getWidth();
        }
    }

    /**
     * Remove heart.
     */
    public void removeHeart() {

        if (this.hearts.size() > 0) {

            this.deleteGameObject(hearts.get(hearts.size() - 1));
            this.hearts.remove(this.hearts.size() - 1);
        }
    }

    /**
     * Gets number of hearts.
     *
     * @return the number of hearts
     */
    public int getNumberOfHearts() {
        return this.hearts.size();
    }


    /**
     * Addkey.
     */
    public void addkey() {

        this.numberOfKeysShown++;
        KeySprite key = new KeySprite();
        this.addGameObject(key, (int) (this.width - xMargin - key.getWidth() * numberOfKeysShown), (int) (yMargin + fontSize + key.getHeight() / 2f));
    }

    /**
     * Gets timer.
     *
     * @return the timer
     */
    public Timer getTimer() {
        return this.timer;
    }



}
