package mario.ui;

import nl.han.ica.oopg.objects.GameObject;
import processing.core.PGraphics;


/**
 * This class represents a textfield.
 *
 * @author Christiaan Wiggers en Daan Pol
 * @version 1.1
 * @since 20-04-2021
 */
public class TextField extends GameObject {

    private final float fontSize;

    private String inputString = "";
    private KeyAdapter keyAdapter;

    /**
     * The constructor
     * @param width the width of the field.
     * @param height the height of the field.
     */
    public TextField(int width, int height) {
        this.width = width;
        this.height = height;
        this.fontSize = height * .8f;
    }

    @Override
    public void draw(PGraphics graphics) {
        graphics.textSize(this.fontSize);
        // All text fields have a white background.
        graphics.fill(255, 255, 255);
        graphics.rect(this.x, this.y, this.width, this.height);
        // All text fields have black letters.
        graphics.fill(0, 0, 0);

        if (graphics.textWidth(this.inputString) < this.width) {
            graphics.text(this.inputString, x, this.y + this.height - this.fontSize);
        }else {
            inputString = inputString.substring(0, inputString.length() - 1);
        }
    }

    @Override
    public void update() {

    }

    @Override
    public void keyPressed(int keyIntValue, char keyCharValue) {
        this.keyAdapter.keyPressed(keyIntValue, keyCharValue);
    }

    /**
     * Adds a keyAdapter object to the text field.
     * @param keyAdapter a keyAdapter object.
     */
    public void addKeyListener(KeyAdapter keyAdapter) {
        this.keyAdapter = keyAdapter;
    }


    /**
     * This method adds a character to the inputString.
     * @param ch a character.
     */
    public void addChar(char ch) {
        this.inputString += ch;
    }

    /**
     * This methods removed a character from the inputString.
     */
    public void removeChar() {
        if (this.inputString.length() > 0) {
            inputString = inputString.substring(0, inputString.length() - 1);
        }
    }

    public String getInputValue() {

        return inputString;
    }
}
