package mario.ui;

import nl.han.ica.oopg.userinput.IKeyInput;

/**
 * An abstract class for receiving Key events.
 *
 * @author Christiaan Wiggers en Daan Pol
 * @version 1.0
 * @since 20-04-2021
 */
public abstract class KeyAdapter implements IKeyInput {

    protected KeyAdapter() {}

    @Override
    public void keyPressed(int i, char c) {}

    @Override
    public void keyReleased(int i, char c) {}
}
