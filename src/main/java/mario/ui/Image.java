package mario.ui;

import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.objects.SpriteObject;
import processing.core.PGraphics;

public class Image extends SpriteObject {

    public Image(Sprite sprite) {
        super(sprite);
    }

    public Image(Sprite sprite, float width, float height) {
        this(sprite);
        this.width = width;
        this.height = height;
    }

    @Override
    public void draw(PGraphics graphics) {
        graphics.image(this.getImage(), this.x, this.y, this.width, this.height);
    }

    @Override
    public void update() {

    }
}
