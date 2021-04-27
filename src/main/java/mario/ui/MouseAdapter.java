package mario.ui;

import nl.han.ica.oopg.userinput.IMouseInput;

/**
 * An abstract class for receiving mouseEvents.
 *
 * @author Christiaan Wiggers en Daan Pol
 * @version 1.0
 * @since 24-04-2021
 */
public abstract class MouseAdapter implements IMouseInput {

    protected MouseAdapter() {}

    @Override
    public void mousePressed(int i, int i1, int i2) {}

    @Override
    public void mouseReleased(int i, int i1, int i2) {}

    @Override
    public void mouseClicked(int i, int i1, int i2) {}

    @Override
    public void mouseMoved(int i, int i1) {}

    @Override
    public void mouseDragged(int i, int i1, int i2) {}

    @Override
    public void mouseWheel(int i) {}
}
