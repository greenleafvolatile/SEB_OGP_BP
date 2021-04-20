package mario.enemies;

import mario.MainApp;
import nl.han.ica.oopg.objects.Sprite;

public class FlyingTurtle extends Enemy {

    public FlyingTurtle(MainApp app) {
        super(app, new Sprite(MainApp.MEDIA_URL.concat("sprites/characters/flyingturtle.png")), 6);
        setxSpeed(2);
    }

    @Override
    public void update() {
        playAnimation();
        preventFlyingOffScreen();
    }

    /***
     * Decrease animation speed based on frame count
     */
    @Override
    public void playAnimation() {
        if (this.app.frameCount % 2 == 0) {
            super.playAnimation();
        }
    }

    /***
     * Prevents flying offscreen
     */
    private void preventFlyingOffScreen() {
        if (getX() <= 0) {
            setDirection(90);
        } else if (getX() >= this.app.getTileMap().getMapWidth()) {
            setDirection(270);
        }
    }

}
