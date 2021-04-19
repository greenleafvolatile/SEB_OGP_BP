package mario.enemies;

import mario.MainApp;
import mario.tiles.FloorTile;
import mario.tiles.LavaTile;
import nl.han.ica.oopg.collision.CollidedTile;
import nl.han.ica.oopg.collision.ICollidableWithTiles;
import nl.han.ica.oopg.objects.Sprite;
import processing.core.PVector;

import java.util.List;

public class Goomba extends Enemy implements ICollidableWithTiles {

    private final MainApp app;

    public Goomba(MainApp app) {
        super(new Sprite(MainApp.MEDIA_URL.concat("sprites/characters/goomba.png")), 8);
        setMovementSpeed(5);

        this.app = app;
    }

    @Override
    public void update() {
        nextFrame();
    }

    @Override
    public void tileCollisionOccurred(List<CollidedTile> collidedTiles) {

        for (CollidedTile tile : collidedTiles) {

            PVector tilePixelLocation = this.app.getTileMap().getTilePixelLocation(tile.getTile());

            if (tile.getTile() instanceof FloorTile || tile.getTile() instanceof LavaTile) {

                switch (tile.getCollisionSide()) {

                    case LEFT:
                        setX(tilePixelLocation.x - width);
                        setDirection(270);
                        break;

                    case RIGHT:
                        setX(tilePixelLocation.x + this.app.getTileMap().getTileSize());
                        setDirection(90);
                        break;

                    case TOP:
                        setY(tilePixelLocation.y - height);
                        break;
                }
            }
        }
    }
}
