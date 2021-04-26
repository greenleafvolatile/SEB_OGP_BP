package mario.ui;

import nl.han.ica.oopg.objects.Sprite;

/**
 * This method represents a button that the
 * user can click on with a mouse button.
 *
 * @author Christiaan Wiggers & Daan Pol.
 * @version 1.0
 * @since 04-11-2021
 *
 */
public class Button extends Image {

    private MouseAdapter listener;

    /**
     * The constructor
     * @param sprite the image of the button.
     * @param width the with of the button.
     * @param height the height of the button.
     */
    public Button(Sprite sprite, float width, float height) {
        super(sprite, width, height);
    }

    @Override
    public void mousePressed(int x, int y, int button) {

        // Check if the x-position and the y-position of the mouse cursor are inside the button area.
        if (x > this.x && x < this.x + this.width && y > this.y && y < this.y + this.height) {
            this.listener.mousePressed(x, y, button);
        }
    }

    /**
     * This method adds a mouseListener to the button.
     * @param listener a mouseListener.
     */
    public void addListener(MouseAdapter listener) {
        this.listener = listener;
    }

    public void update() {}
}