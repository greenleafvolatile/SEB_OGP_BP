package mario.model.enemy;

import mario.MainApp;
import nl.han.ica.oopg.objects.Sprite;

/**
 * The type Piranhas plant.
 */
public class PiranhasPlant extends Enemy {

    /**
     * Instantiates a new Piranhas plant.
     *
     * @param app the app
     */
    public PiranhasPlant(MainApp app) {
        super(app, new Sprite(MainApp.MEDIA_URL.concat("media/sprites/characters/plant.png")), 176);
    }

    @Override
    public void playAnimation() {
        if (this.app.frameCount % 2 == 0) {
            super.playAnimation();
        }
    }
}
