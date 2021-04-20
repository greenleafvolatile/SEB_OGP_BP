package mario.dashboard;

import mario.Key;
import mario.dashboard.DashboardHeart;
import nl.han.ica.oopg.dashboard.Dashboard;
import nl.han.ica.oopg.objects.TextObject;
import nl.han.ica.oopg.view.PGraphicsCreator;
import processing.core.PGraphics;

import java.util.ArrayList;
import java.util.List;

public final class GameDashboard extends Dashboard {

    private final int xMargin = 40;
    private final int yMargin = 20;

    private final List<DashboardHeart> hearts = new ArrayList<>();
    private final List<DashboardKey> keys = new ArrayList<>();

    private int fontSize;
    private PGraphics graphics;

    {
        final int numberOfHearts = 3;
        for (int i = 0; i < numberOfHearts; i++) hearts.add(new DashboardHeart());

        // for testing
        final int numberOfKeys = 3;
        for (int i = 0; i < numberOfKeys; i++) keys.add(new DashboardKey());

    }

    public GameDashboard(float x, float y, float width, float height) {

        super(x, y, width, height);
        graphics = new PGraphicsCreator().createPGraphics((int) width, (int) height);
        this.init();
    }

    private void init() {

        fontSize = 32;
        this.addLabel("Player:", xMargin, yMargin, fontSize, 255, 255, 255);
        this.addLabel("00:00:00", (int) this.width / 2  - 50, yMargin, fontSize, 255, 255, 255);
        this.addLabel("Keys:", (int) this.width - 110, yMargin, fontSize, 255, 255, 255);
        this.addHearts();
        this.addKeys();
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
        for (DashboardHeart dashboardHeart : this.hearts) {
            this.addGameObject(dashboardHeart, x, y);
            x += dashboardHeart.getWidth();
        }
    }

    public void removeHeart() {

        this.hearts.remove(hearts.size() - 1);
    }

    private void addKeys() {

        int x = (int) this.width - xMargin;
        int y = yMargin + fontSize;
        for (DashboardKey key : this.keys) {
            this.addGameObject(key, x, y);
            x -= key.getWidth();
        }
    }

    public void addkey() {

        this.keys.add(new DashboardKey());
        System.out.println(keys.size());
    }
}
