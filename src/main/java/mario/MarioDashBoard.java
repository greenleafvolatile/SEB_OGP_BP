package mario;

import nl.han.ica.oopg.dashboard.Dashboard;
import nl.han.ica.oopg.objects.TextObject;
import processing.core.PGraphics;

import java.util.ArrayList;

public class MarioDashBoard extends Dashboard {

    final int xMargin = 40;
    final int yMargin = 20;

    private int fontSize;
    private PGraphics graphics;
    private ArrayList<Heart> hearts = new ArrayList<>();

    {
        final int numberOfHearts = 3;
        for (int i = 0; i < numberOfHearts; i++) hearts.add(new Heart());
    }

    public MarioDashBoard(float x, float y, float width, float height) {

        super(x, y, width, height);
        this.init();
    }

    private void init() {

        fontSize = 32;
        this.addLabel("Player:", xMargin, yMargin, fontSize, 255, 255, 255);
        this.addLabel("00:00:00", (int) this.width / 2  - 50, yMargin, fontSize, 255, 255, 255);
        this.addLabel("Keys:", (int) this.width - 110, yMargin, fontSize, 255, 255, 255);
        this.addHearts();
    }


    private void addLabel(String text, int xPos, int yPos, int fontSize, int red, int green, int blue) {

        final int alpha = 255;
        TextObject textObject = new TextObject(text, fontSize);
        textObject.setForeColor(red, green, blue, alpha);
        this.addGameObject(textObject, xPos, yPos);
    }

    private void addHearts() {

        int x = xMargin;
        int y = yMargin + fontSize;
        for (Heart heart : hearts) {
            this.addGameObject(heart, x, y);
            x += heart.getWidth();
        }
    }

    private void removeHeart() {

        hearts.remove(hearts.size() - 1);
    }


}
