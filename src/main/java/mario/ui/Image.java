package mario.ui;

import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.objects.SpriteObject;
import processing.core.PGraphics;


/**
 * This class represents an image.
 * @author Christiaan Wiggers en Daan Pol
 * @version 1.0
 * @since 20-04-2021
 */
public class Image extends SpriteObject {

    /**
     * Constructor #1
     * @param sprite the sprite for the image.
     */
    public Image(Sprite sprite) {
        super(sprite);
    }


    /**
     * Constructor #2
     * @param sprite the sprite for the image.
     * @param width the width of the image.
     * @param height the height of the image.
     */
    public Image(Sprite sprite, float width, float height) {
        this(sprite);
        this.width = width;
        this.height = height;
    }

    /**
     * Show the image AND resize the sprite to width and height.
     * @param graphics
     */
    @Override
    public void draw(PGraphics graphics) {
        graphics.image(this.getImage(), this.x, this.y, this.width, this.height);
    }

    @Override
    public void update() {

    }
}
