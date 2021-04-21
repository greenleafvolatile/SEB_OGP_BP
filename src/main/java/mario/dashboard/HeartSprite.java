package mario.dashboard;

import mario.MainApp;
import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.objects.SpriteObject;

public class HeartSprite extends SpriteObject {

    public HeartSprite() {
        super(new Sprite(MainApp.MEDIA_URL.concat("/sprites/items/hud_heartFull.png")));
    }

    public void update() {

    }
}
