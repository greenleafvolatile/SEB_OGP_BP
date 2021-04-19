package mario.enemies;

import mario.MainApp;
import mario.tiles.FloorTile;
import mario.tiles.LavaTile;
import nl.han.ica.oopg.collision.CollidedTile;
import nl.han.ica.oopg.collision.ICollidableWithTiles;
import nl.han.ica.oopg.objects.Sprite;
import processing.core.PVector;

import java.util.List;

public class FlyingTurtle extends Enemy implements ICollidableWithTiles {

    private final MainApp app;

    public FlyingTurtle(MainApp app) {
        super(new Sprite(MainApp.MEDIA_URL.concat("sprites/characters/flyingturtle.png")), 6);
        this.app = app;

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
