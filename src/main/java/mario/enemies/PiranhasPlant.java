package mario.enemies;

import mario.MainApp;
import nl.han.ica.oopg.objects.Sprite;

public class PiranhasPlant extends Enemy {

    public PiranhasPlant(MainApp app) {
        super(app, new Sprite(MainApp.MEDIA_URL.concat("sprites/characters/plant.png")), 176);
    }
}
