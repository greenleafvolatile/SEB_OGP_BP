package mario.ui;

import mario.MainApp;
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
public final class UIButton extends UIComponent implements IClickable {

    private MainApp app;
    private PImage buttonImage;
    private float xPos, yPos, buttonWidth, buttonHeight;

    public UIButton(MainApp app, String pathToImage, float xPos, float yPos, float buttonWidth, float buttonHeight) {

        super(xPos, yPos);
        this.app = app;
        this.buttonImage = app.loadImage(pathToImage);
        this.buttonWidth = buttonWidth;
        this.buttonHeight = buttonHeight;

    }
    public void draw(PGraphics graphics) {

        buttonImage.resize((int) buttonWidth, (int) buttonHeight);
        graphics.image(buttonImage, this.xPos, this.yPos);
    }

    @Override
    public boolean contains(float mouseX, float mouseY ) {

        return mouseX >= this.xPos && mouseX <= this.xPos + this.buttonWidth && mouseY >= this.yPos && mouseY <= this.yPos + this.buttonHeight;
    }

    public void update(){}




}
