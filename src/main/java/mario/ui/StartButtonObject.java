package mario.ui;

import mario.MainApp;
import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.objects.SpriteObject;
import processing.core.PGraphics;

/**
 * This method represents a button that the
 * user can click on with a mouse button.
 *
 * @author Christiaan Wiggers & Daan Pol.
 * @version 1.0
 * @since 04-11-2021
 *
 */
public final class StartButtonObject extends SpriteObject {

    public StartButtonObject(float width, float height) {

        super(new Sprite(MainApp.MEDIA_URL.concat("/sprites/buttons/play_red.png")));
        this.width = width;
        this.height = height;

    }
    public void draw(PGraphics graphics) {

        graphics.image(this.getImage(), this.x, this.y, this.width, this.height);
    }

    @Override
    public void mousePressed(int x, int y, int button) {
        if (x > this.x && x < this.x + this.width && y > this.y && y < this.y + height) {
            System.out.println("Test");
        }
    }

    public void update() {}

}
