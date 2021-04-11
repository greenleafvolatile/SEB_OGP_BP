package Super_Mario_Speed_Runner;

import processing.core.PApplet;

public class UIButton extends UIComponent {

    private final int buttonWidth, buttonHeight, buttonColor, textColor;
    private final String buttonText;

    public UIButton(int xPos, int yPos, int buttonWidth, int buttonHeight, int buttonColor, int textColor, String buttonText) {

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




}
