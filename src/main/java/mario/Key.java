package mario;

public class Key {

    private int keyValue;
    private boolean pressed;

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
