package mario.view.game.sprites;

import mario.MainApp;
import mario.ui.Image;
import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.objects.SpriteObject;

public class KeySprite extends Image {

    public KeySprite() {

        super(new Sprite(MainApp.MEDIA_URL.concat("/sprites/items/hud_keyYellow.png")));
    }

    public void update() {

    }
}
