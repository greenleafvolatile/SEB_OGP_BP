package mario.enemies;

import mario.MainApp;
import nl.han.ica.oopg.objects.Sprite;

public class Goomba extends Enemy {

    public Goomba(MainApp app) {
        super(app, new Sprite(MainApp.MEDIA_URL.concat("sprites/characters/goomba.png")), 8);
        setMovementSpeed(5);
    }

    @Override
    public void update() {
        nextFrame();
    }


}
