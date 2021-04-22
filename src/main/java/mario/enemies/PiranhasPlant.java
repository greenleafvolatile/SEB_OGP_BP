package mario.enemies;

import mario.MainApp;
import nl.han.ica.oopg.objects.Sprite;

public class PiranhasPlant extends Enemy {

    public PiranhasPlant(MainApp app, Sprite sprite, int totalFrames) {
        super(app, new Sprite(MainApp.MEDIA_URL.concat("sprites/characters/plant.png")), 62);
    }
}
