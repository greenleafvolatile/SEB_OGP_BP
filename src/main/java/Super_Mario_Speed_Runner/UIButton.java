package Super_Mario_Speed_Runner;

import processing.core.PApplet;

/**
 * This method represent a button that the
 * user can click on the left moues button.
 *
 * @author Christiaan Wiggers & Daan Pol.
 * @version 1.0
 * @since 04-11-2021
 *
 */
public class UIButton extends UIComponent {

    private final float buttonWidth, buttonHeight, buttonColor, textColor;
    private final String buttonText;

    /**
     * The UIButton constructor
     * @param xPos the x-position of the button.
     * @param yPos the y-position of the button.
     * @param buttonWidth the width of the button.
     * @param buttonHeight the height of the button.
     * @param buttonColor the color of the button.
     * @param textColor the color of the text on the button.
     * @param buttonText the text on the button.
     */
    public UIButton(float xPos, float yPos, float buttonWidth, float buttonHeight, float buttonColor, float textColor, String buttonText) {

        super(xPos, yPos);
        this.buttonWidth = buttonWidth;
        this.buttonHeight = buttonHeight;
        this.buttonColor = buttonColor;
        this.textColor = textColor;
        this.buttonText = buttonText;

    }

    @Override
    public void draw(PApplet app) {

    }

    @Override
    public boolean contains(float mouseX, float mouseY ) {

        return mouseX >= super.getxPos() && mouseX <= super.getxPos() + this.buttonWidth && mouseY >= super.getyPos() && mouseY <= super.getyPos() + this.buttonHeight;
    }




}
