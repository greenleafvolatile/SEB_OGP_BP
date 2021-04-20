package mario.enemies;

import mario.MainApp;
import mario.tiles.FloorTile;
import mario.tiles.LavaTile;
import nl.han.ica.oopg.collision.CollidedTile;
import nl.han.ica.oopg.collision.ICollidableWithTiles;
import nl.han.ica.oopg.objects.AnimatedSpriteObject;
import nl.han.ica.oopg.objects.Sprite;
import processing.core.PVector;

import java.util.List;

public abstract class Enemy extends AnimatedSpriteObject implements ICollidableWithTiles {

    protected MainApp app;

    public Enemy(MainApp app, Sprite sprite, int totalFrames) {
        super(sprite, totalFrames);
        this.app = app;
    }

    public void setMovementSpeed(int movementSpeed) {
        setxSpeed(movementSpeed);
    }

    @Override
    public final void tileCollisionOccurred(List<CollidedTile> collidedTiles) {

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
