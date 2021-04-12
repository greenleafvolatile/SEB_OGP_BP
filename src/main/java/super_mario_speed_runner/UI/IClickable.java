package super_mario_speed_runner.UI;

public interface IClickable {

    /**
     * This is a method for the sake of mouse processing.
     * @param mouseX the x-position of the mouse pointer.
     * @param mouseY the y-position of the mouse pointer.
     * @return true if the mouse pointer is inside the shape of the ui component else false.
     */
    boolean contains(float mouseX, float mouseY);

}
