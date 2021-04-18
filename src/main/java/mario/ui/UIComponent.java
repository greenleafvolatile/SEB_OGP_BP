package mario.ui;

import nl.han.ica.oopg.objects.GameObject;
import processing.core.PApplet;
import processing.core.PGraphics;

/**
 * This class is the root class for all ui components.
 *
 * @author: Daan Pol & Christiaan Wiggers
 * @version: 1.0
 * @since: 11-04-2021
 *
 */
public abstract class UIComponent extends GameObject {

    private final float xPos, yPos;

    /**
     * The UIComponent constructor.
     * @param xPos the x-position of the component.
     * @param yPos the y-position of the component.
     */
    protected UIComponent(float xPos, float yPos) {

        this.xPos = xPos;
        this.yPos = yPos;

    }



    /**
     * This method draws the component.
     */
    public abstract void draw(PGraphics graphics);


    protected float getxPos() {
        return this.xPos;
    }


    protected float getyPos() {
        return this.yPos;
    }

    public void update(){}

}
