package mario.ui;

import mario.MainApp;
import nl.han.ica.oopg.objects.GameObject;
import processing.core.PApplet;
import processing.core.PGraphics;
import processing.core.PImage;

/**
 * This method represents a button that the
 * user can click on with the left mouse button.
 *
 * @author Christiaan Wiggers & Daan Pol.
 * @version 1.0
 * @since 04-11-2021
 *
 */
public final class UIButton extends GameObject implements IClickable {

    private MainApp app;
    private PImage buttonImage;
    private int buttonWidth, buttonHeight;

    public UIButton(MainApp app, String pathToImage, int buttonWidth, int buttonHeight) {

        this.app = app;
        this.buttonImage = app.loadImage(pathToImage);
        this.buttonWidth = buttonWidth;
        this.buttonHeight = buttonHeight;

    }
    public void draw(PGraphics graphics) {

        graphics.image(buttonImage, this.x, this.y, buttonWidth, buttonHeight);
    }

    public PImage getButtonImage() {
        return this.buttonImage;
    }

    public int getButtonWidth() {
        return this.buttonWidth;
    }

    @Override
    public boolean contains(float mouseX, float mouseY ) {

        return mouseX >= this.x && mouseX <= this.x + this.buttonWidth && mouseY >= this.y && mouseY <= this.y+ this.buttonHeight;
    }

    public void update(){}




}
