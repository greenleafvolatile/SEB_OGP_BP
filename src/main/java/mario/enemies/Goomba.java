package mario.enemies;

import mario.MainApp;
import mario.tiles.FloorTile;
import mario.tiles.LavaTile;
import nl.han.ica.oopg.collision.CollidedTile;
import nl.han.ica.oopg.collision.ICollidableWithTiles;
import nl.han.ica.oopg.objects.Sprite;
import processing.core.PVector;

import java.util.List;

public class Goomba extends Enemy {

    public Goomba(MainApp app) {
        super(app, new Sprite(MainApp.MEDIA_URL.concat("sprites/characters/goomba.png")), 8);
        setMovementSpeed(5);
    }

    @Override
    public void update() {
        nextFrame();
    }


}
