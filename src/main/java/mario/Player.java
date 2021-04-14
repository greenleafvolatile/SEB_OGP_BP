package mario;

import mario.tiles.FloorTile;
import nl.han.ica.oopg.collision.CollidedTile;
import nl.han.ica.oopg.collision.ICollidableWithGameObjects;
import nl.han.ica.oopg.collision.ICollidableWithTiles;
import nl.han.ica.oopg.exceptions.TileNotFoundException;
import nl.han.ica.oopg.objects.AnimatedSpriteObject;
import nl.han.ica.oopg.objects.GameObject;
import nl.han.ica.oopg.objects.Sprite;
import processing.core.PVector;

import java.util.List;

public class Player extends AnimatedSpriteObject implements ICollidableWithTiles, ICollidableWithGameObjects {

    private MainApp app;

    public Player(MainApp app) {
        super(new Sprite(MainApp.MEDIA_URL.concat("sprites/characters/mario.png")), 7);
        this.app = app;

        final float gravity = 0.2f;
        this.setGravity(gravity);
    }

    @Override
    public void keyPressed(int keyCode, char key) {
        final int speed = 5;
        if (keyCode == app.RIGHT) {
            setDirectionSpeed(90, speed);
        } else if (keyCode == app.LEFT) {
            setDirectionSpeed(270, speed);
        } else if (key == ' ') {
            setDirectionSpeed(360, 5);
        }
    }

    @Override
    public void keyReleased(int keyCode, char key) {
        super.keyReleased(keyCode, key);
        if (keyCode == app.RIGHT) {
            setDirectionSpeed(0, 0);
        } else if (keyCode == app.LEFT) {
            setDirectionSpeed(0, 0);
        } else if (key == ' ') {
            setDirectionSpeed(0, 0);
        }
    }

    @Override
    public void gameObjectCollisionOccurred(List<GameObject> list) {

    }

    @Override
    public void tileCollisionOccurred(List<CollidedTile> collidedTiles) {

        PVector vector;

        for (CollidedTile tile : collidedTiles) {
            if (tile.getTile() instanceof FloorTile) {

                try {
                    vector = app.getTileMap().getTilePixelLocation(tile.getTile());
                    setY(vector.y - getHeight());

                } catch (TileNotFoundException e) {
                    e.printStackTrace();
                }

                setySpeed(0);
            }
        }
    }

    @Override
    public void update() {

    }
}
