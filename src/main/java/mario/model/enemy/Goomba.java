package mario.model.enemy;

import mario.MainApp;
import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.sound.Sound;

/**
 * The type Goomba.
 */
public class Goomba extends Enemy implements IMakeSound{

    /**
     * Instantiates a new Goomba.
     *
     * @param app the app
     */
    public Goomba(MainApp app) {
        super(app, new Sprite(MainApp.MEDIA_URL.concat("sprites/characters/goomba.png")), 8);
        setxSpeed(5);
    }

    public void playSound() {
        Sound sound = new Sound(app, MainApp.MEDIA_URL.concat("sounds/goomba.wav"));
        sound.cue(0);
        sound.play();
    }

}
