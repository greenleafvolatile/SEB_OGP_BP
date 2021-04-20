package mario.enemies;

import mario.MainApp;
import mario.tiles.FloorTile;
import mario.tiles.LavaTile;
import nl.han.ica.oopg.collision.CollidedTile;
import nl.han.ica.oopg.collision.ICollidableWithTiles;
import nl.han.ica.oopg.objects.Sprite;
import processing.core.PVector;

import java.util.List;

public class FlyingTurtle extends Enemy {

    public FlyingTurtle(MainApp app) {
        super(app, new Sprite(MainApp.MEDIA_URL.concat("sprites/characters/flyingturtle.png")), 6);
        setMovementSpeed(2);
    }

    @Override
    public void update() {
        playAnimation();
        preventFlyingOffScreen();
    }

    /***
     * Decrease animation speed based on frame count
     */
    private void playAnimation() {
        if (this.app.frameCount % 2 == 0) {
            nextFrame();
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
