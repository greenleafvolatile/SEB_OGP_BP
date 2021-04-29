package mario.view.game.sprites;

import mario.MainApp;
import mario.ui.Image;
import nl.han.ica.oopg.objects.Sprite;

public class KeySprite extends Image {

    public KeySprite() {
        super(new Sprite(MainApp.MEDIA_URL.concat("media/sprites/items/hud_keyYellow.png")));
    }
}
