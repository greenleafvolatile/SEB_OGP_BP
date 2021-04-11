package Super_Mario_Speed_Runner;

import processing.core.PApplet;

/**
 * This class is the root class for all ui components.
 *
 * @author: Daan Pol en Christiaan Wiggers
 * @version: 1
 * @since: 11-04-2021
 *
 */
public abstract class UIComponent {

    private final int xPos, yPos;

    public UIComponent(int xPos, int yPos) {

        this.xPos = xPos;
        this.yPos = yPos;

    }

    /**
     * This is a method for the sake of mouse processing.
     * @param xPos the x-position of the mouse pointer.
     * @param yPos the y-position of the mouse pointer.
     * @return true if the mouse pointer is inside the shape of the ui component else false.
     */
    protected abstract boolean contains(int xPos, int yPos);


    /**
     * This method draws the component.
     */
    protected abstract void draw(PApplet app);

}
