package mario.view.game.sprites;

import mario.MainApp;
import mario.ui.Image;
import nl.han.ica.oopg.objects.Sprite;

/**
 * The type Key sprite.
 */
public class KeySprite extends Image {

    /**
     * Instantiates a new Key sprite.
     */
    public KeySprite() {
        super(new Sprite(MainApp.MEDIA_URL.concat("media/sprites/items/hud_keyYellow.png")));
    }

}
