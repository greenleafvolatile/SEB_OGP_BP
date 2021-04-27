package mario.model.enemy;

import mario.MainApp;
import nl.han.ica.oopg.objects.Sprite;

/**
 * The type Goomba.
 */
public class Goomba extends Enemy {

    /**
     * Instantiates a new Goomba.
     *
     * @param app the app
     */
    public Goomba(MainApp app) {
        super(app, new Sprite(MainApp.MEDIA_URL.concat("media/sprites/characters/goomba.png")), 8);
        setxSpeed(5);
    }




}
