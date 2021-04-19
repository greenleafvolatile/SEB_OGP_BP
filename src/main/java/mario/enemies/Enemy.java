package mario.enemies;

import nl.han.ica.oopg.objects.AnimatedSpriteObject;
import nl.han.ica.oopg.objects.Sprite;

public class Enemy extends AnimatedSpriteObject {

    public Enemy(Sprite sprite, int totalFrames) {
        super(sprite, totalFrames);
    }

    public void setMovementSpeed(int movementSpeed) {
        setxSpeed(movementSpeed);
    }

    @Override
    public void update() {

    }
}
