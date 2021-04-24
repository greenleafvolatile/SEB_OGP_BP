package mario.ui;

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
public final class Button extends Image {

    private MouseListener listener;

    public Button(Sprite sprite, float width, float height) {

        super(sprite);
        this.width = width;
        this.height = height;
    }

    @Override
    public void mousePressed(int x, int y, int button) {

        if (x > this.x && x < this.x + this.width && y > this.y && y < this.y + this.height) {
            this.listener.mousePressed(x, y, button);
        }
    }

    public void addListener(MouseListener listener) {
        this.listener = listener;

    }

    public void update() {}

}