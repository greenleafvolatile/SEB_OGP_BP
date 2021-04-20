package mario;

import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.objects.SpriteObject;

public class Heart extends SpriteObject {

    public Heart () {
        super(new Sprite(MainApp.MEDIA_URL.concat("/sprites/items/hud_heartFull.png")));
    }

    public void update() {

    }
}
