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

import java.util.ArrayList;
import java.util.List;

public class Player extends AnimatedSpriteObject implements ICollidableWithTiles, ICollidableWithGameObjects {

    private final MainApp app;
    private final Sound jumpSound;

    private int walkingSpeed = 5;
    private boolean jump;
    private boolean onFloorTile;
    private List<Key> keys = new ArrayList<>();

    {
        this.keys.add(new Key(MainApp.LEFT));
        this.keys.add(new Key(MainApp.RIGHT));
    }

    public Player(MainApp app) {

        super(new Sprite(MainApp.MEDIA_URL.concat("sprites/characters/mario.png")), 7);
        this.jumpSound = new Sound(app, MainApp.MEDIA_URL.concat(("sounds/jump_11.wav")));
        this.app = app;
        initPlayer();

    }

    private void initPlayer() {
        this.setSpeed(this.walkingSpeed);
        this.setGravity(0.2f);
    }


    @Override
    public void keyPressed(int intValue, char charValue) {

        for (Key key : keys) {
            if (key.getKeyValue() == intValue) {
                key.setPressed(true);
            }
        }
        move(intValue);
    }

    private boolean isDoublekey() {

        for (Key key : keys) {
            if (key.isPressed()) {
                return true;
            }
        }
        return false;
    }

    private void move(int direction) {

        final int jumpingSpeed = 8;

        if (direction == app.RIGHT)  {

            setDirectionSpeed(90, this.walkingSpeed);
        } else if (direction == app.LEFT) {

            setDirectionSpeed(270, this.walkingSpeed);
        } else if (direction == MainApp.JUMP) {

            this.jump = true;

            if (onFloorTile) {

                if (isDoublekey()) {

                    for (Key key : keys) {

                        if (key.getKeyValue() == MainApp.RIGHT && key.isPressed()) {

                            this.setDirectionSpeed(30, jumpingSpeed);

                        } else if (key.getKeyValue() == MainApp.LEFT && key.isPressed()) {

                            this.setDirectionSpeed(320, jumpingSpeed);

                        }
                    }

                } else {

                    this.setDirectionSpeed(360, 8);
                }

                this.setCurrentFrameIndex(3); // Change sprite index to jump motion.

                this.onFloorTile = false;
                this.jumpSound.cue(0);
                this.jumpSound.play();
            }
        }

        if (onFloorTile) {
            this.nextFrame();
        }

    }

    @Override
    public void keyReleased(int intValue, char charValue) {

        for (Key key : keys) {
            if (key.getKeyValue() == intValue) {
                key.setPressed(false);
            }
        }
        if (intValue != MainApp.JUMP) this.setSpeed(0);

    }

    @Override
    public void gameObjectCollisionOccurred(List<GameObject> list) {

    }

    @Override
    public void tileCollisionOccurred(List<CollidedTile> collidedTiles) {

        for (CollidedTile tile : collidedTiles) {

            PVector tilePixelLocation = this.app.getTileMap().getTilePixelLocation(tile.getTile());
            PVector tileIndexLocation = this.app.getTileMap().getTileIndex(tile.getTile());

            if (tile.getTile() instanceof FloorTile || tile.getTile() instanceof LavaTile) {

                this.onFloorTile = true;

                if (jump) {
                    this.setCurrentFrameIndex(0);
                    jump = false;
                }

                switch (tile.getCollisionSide()) {

                    case LEFT:
                        setX(tilePixelLocation.x - width);
                        break;

                    case RIGHT:
                        setX(tilePixelLocation.x + this.app.getTileMap().getTileSize());
                        break;

                    case TOP:
                        setY(tilePixelLocation.y - height);
                        break;

                    case BOTTOM:
                        this.setY(tilePixelLocation.y + getHeight());
                        break;

                }
                // Prevents endless speed increasing
                this.setySpeed(0);
            }

            if (tile.getTile() instanceof KeyTile) {
                try {
                    this.app.getTileMap().setTile((int) tileIndexLocation.x, (int) tileIndexLocation.y, -1);
                } catch (TileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void update() {
        System.out.println(onFloorTile);

    }
}
