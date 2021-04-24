package mario.model.enemy;

import mario.MainApp;
import mario.model.map.tiles.FloorTile;
import nl.han.ica.oopg.collision.CollidedTile;
import nl.han.ica.oopg.collision.ICollidableWithTiles;
import nl.han.ica.oopg.objects.AnimatedSpriteObject;
import nl.han.ica.oopg.objects.Sprite;
import processing.core.PVector;

import java.util.List;

/**
 * The type Enemy.
 */
public abstract class Enemy extends AnimatedSpriteObject implements ICollidableWithTiles {

    /**
     * The App.
     */
    protected MainApp app;

    /**
     * Instantiates a new Enemy.
     *
     * @param app         the app
     * @param sprite      the sprite
     * @param totalFrames the total frames
     */
    public Enemy(MainApp app, Sprite sprite, int totalFrames) {
        super(sprite, totalFrames);
        this.app = app;
    }

    /**
     * Play animation.
     */
    public void playAnimation() {
        nextFrame();
    }

    @Override
    public void update() {
        playAnimation();
    }

    @Override
    public final void tileCollisionOccurred(List<CollidedTile> collidedTiles) {

        for (CollidedTile tile : collidedTiles) {

            PVector tilePixelLocation = this.app.getTileMap().getTilePixelLocation(tile.getTile());

            if (tile.getTile() instanceof FloorTile) {

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
