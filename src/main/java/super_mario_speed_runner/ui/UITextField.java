package super_mario_speed_runner.ui;

import processing.core.PApplet;



public final class UITextField extends UIComponent {

    private float fieldWidth, fieldHeight;
    private UICaret caret;

    public UITextField(float xPos, float yPos, float fieldWidth, float fieldHeight) {

        super(xPos, yPos);
        this.caret = new UICaret(xPos, yPos);
        this.fieldWidth = fieldWidth;
        this.fieldHeight = fieldHeight;


    }

    @Override
    public void draw(PApplet app) {


    }
}
