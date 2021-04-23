package mario.ui;

import nl.han.ica.oopg.objects.GameObject;
import processing.core.PGraphics;


public final class TextField extends GameObject {

    private final float fontSize;

    private String playerName = "Player";
    private KeyListener keyListener;

    public TextField(int width, int height) {

        this.width = width;
        this.height = height;
        this.fontSize = height * .8f;
    }

    @Override
    public void draw(PGraphics graphics) {

        graphics.textSize(this.fontSize);
        graphics.fill(255, 255, 255);
        graphics.rect(this.x, this.y, this.width, this.height);
        graphics.fill(0, 0, 0);

        if (graphics.textWidth(this.playerName) < this.width) { // Dit zou in een ternary statement kunnen.

            graphics.text(this.playerName, x, this.y + this.height - this.fontSize);

        } else {

            playerName = playerName.substring(0, playerName.length() - 1);
        }
    }

    @Override
    public void update() {

    }

    @Override
    public void keyPressed(int keyIntValue, char keyCharValue) {

        this.keyListener.keyPressed(keyIntValue, keyCharValue);
    }

    public void addKeyListener(KeyListener keyListener) {

        this.keyListener = keyListener;
    }

    public void addChar(char ch) {

        this.playerName += ch;
    }

    public void removeChar() {

        if (this.playerName.length() > 0) {
            playerName = playerName.substring(0, playerName.length() - 1);
        }
    }

    public String getPlayerName() {
        return this.playerName;
    }
}
