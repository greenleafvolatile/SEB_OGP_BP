package mario;

import mario.tiles.FloorTile;
import mario.tiles.KeyTile;
import mario.tiles.LavaTile;
import nl.han.ica.oopg.collision.CollidedTile;
import nl.han.ica.oopg.collision.CollisionSide;
import nl.han.ica.oopg.collision.ICollidableWithGameObjects;
import nl.han.ica.oopg.collision.ICollidableWithTiles;
import nl.han.ica.oopg.exceptions.TileNotFoundException;
import nl.han.ica.oopg.objects.AnimatedSpriteObject;
import nl.han.ica.oopg.objects.GameObject;
import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.sound.Sound;
import processing.core.PVector;

import java.util.List;

public class Player extends AnimatedSpriteObject implements ICollidableWithTiles, ICollidableWithGameObjects {

    private final MainApp app;
    private final float gravity;
    private final Sound jumpSound;

    private int movementSpeed = 5;

    public Player(MainApp app) {
        super(new Sprite(MainApp.MEDIA_URL.concat("sprites/characters/mario.png")), 7);
        this.app = app;
        this.jumpSound = new Sound(app, MainApp.MEDIA_URL.concat(("sounds/jump_11.wav")));
        this.gravity = 0.2f;
        this.setGravity(gravity);
        initPlayer();

    }

    private void initPlayer() {
        this.setSpeed(this.movementSpeed);
        this.setGravity(this.gravity);
    }

    @Override
    public void keyPressed(int keyCode, char key) {

        if (keyCode == app.RIGHT) {
            setDirectionSpeed(90, this.movementSpeed);
        } else if (keyCode == app.LEFT) {
            setDirectionSpeed(270, this.movementSpeed);
        } else if (key == ' ') {
            setDirectionSpeed(360, 5);
            this.jumpSound.cue(0);
            this.jumpSound.play();
        }
    }

    @Override
    public void keyReleased(int keyCode, char key) {
        this.setSpeed(0);

        /*if (keyCode == app.RIGHT) {
            setDirectionSpeed(0, 0);
        } else if (keyCode == app.LEFT) {
            setDirectionSpeed(0, 0);
        } else if (key == ' ') {
            setDirectionSpeed(0, 0);
        }*/
    }

    @Override
    public void gameObjectCollisionOccurred(List<GameObject> list) {

    }

    @Override
    public void tileCollisionOccurred(List<CollidedTile> collidedTiles) {

        PVector vector;

        for (CollidedTile tile : collidedTiles) {

            if (tile.getTile() instanceof FloorTile || tile.getTile() instanceof LavaTile) {

                switch (tile.getCollisionSide()) {

                    case LEFT:
                        vector = this.app.getTileMap().getTilePixelLocation(tile.getTile());
                        setX(vector.x - width);
                        break;

                    case RIGHT:
                        vector = this.app.getTileMap().getTilePixelLocation(tile.getTile());
                        setX(vector.x + this.app.getTileMap().getTileSize());
                        break;

                    case TOP:
                        vector = this.app.getTileMap().getTilePixelLocation(tile.getTile());
                        setY(vector.y - height);
                        break;

                    case BOTTOM:
                        vector = this.app.getTileMap().getTilePixelLocation(tile.getTile());
                        this.setY(vector.y + getHeight());
                        break;

                }

                // Prevents endless speed increasing
                this.setySpeed(0);

            }
            
            if (tile.getTile() instanceof KeyTile) {
                try {
                    vector = this.app.getTileMap().getTileIndex(tile.getTile());
                    this.app.getTileMap().setTile((int) vector.x, (int) vector.y, -1);

                } catch (TileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void update() {

    }
}
