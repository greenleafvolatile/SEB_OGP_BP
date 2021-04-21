package mario.dashboard;

import nl.han.ica.oopg.dashboard.Dashboard;
import nl.han.ica.oopg.objects.TextObject;
import nl.han.ica.oopg.view.PGraphicsCreator;
import processing.core.PGraphics;

import java.util.ArrayList;
import java.util.List;

public class GameDashboard extends Dashboard {

    private final int xMargin = 40;
    private final int yMargin = 20;

    private final List<HeartSprite> hearts = new ArrayList<>();
    private final List<KeySprite> keys = new ArrayList<>();

    private int fontSize;
    private int numberOfKeys = 0;

    private PGraphics graphics;

    {
        final int numberOfHearts = 3;
        for (int i = 0; i < numberOfHearts; i++) {
            hearts.add(new HeartSprite());
        }
    }

    public GameDashboard(float x, float y, float width, float height) {

        super(x, y, width, height);
        this.graphics = new PGraphicsCreator().createPGraphics((int) width, (int) height);
        this.init();
    }

    private void init() {

        this.fontSize = 32;
        this.graphics.textSize(fontSize);

        String timerLabelText = "00:00:00";
        String keysLabelText = "keys:";

        this.addLabel("Player:", xMargin, yMargin, fontSize, 255, 255, 255);
        this.addLabel(timerLabelText, (int) (this.width / 2 - this.graphics.textWidth(timerLabelText) / 2f), yMargin, fontSize, 255, 255, 255);
        this.addLabel(keysLabelText, (int) (this.width - xMargin - this.graphics.textWidth(keysLabelText)), yMargin, fontSize, 255, 255, 255);

        this.addHearts();
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

    public void removeHeart() {

        if (this.hearts.size() > 0) {

            this.deleteGameObject(hearts.get(hearts.size() - 1));
            this.hearts.remove(this.hearts.size() - 1);
        }
    }

    public int getNumberOfHearts() {
        return this.hearts.size();
    }


    public void addkey() {

        this.numberOfKeys++;
        KeySprite key = new KeySprite();
        this.addGameObject(key, (int) (this.width - xMargin - key.getWidth() * numberOfKeys), (int) (yMargin + fontSize + key.getHeight() / 2f));
    }
}
