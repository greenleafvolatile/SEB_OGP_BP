package mario.model;

/**
 * This class represents a key on
 * a keyboard.
 *
 * @author Christiaan Wiggers en Daan Pol
 * @version 1.0
 * @since 20-04-2021
 */
public class Key {

    private final int keyValue;
    private boolean pressed;

    /**
     * The constructor
     * @param keyValue the UTF-8 value of the key.
     */
    public Key(int keyValue) {
        this.keyValue = keyValue;
    }

    public int getKeyValue() {
        return this.keyValue;
    }

    public void setPressed(boolean value) {
        this.pressed = value;
    }

    public boolean isPressed() {
        return pressed;
    }
}
