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
        nextFrame();

        // Prevents flying offscreen
        if (getX() <= 0) {
            setDirection(90);
        } else if (getX() >= this.app.getTileMap().getMapWidth()) {
            setDirection(270);
        }

    }

}
