package mario;

import mario.tiles.FloorTile;
import mario.tiles.KeyTile;
import mario.tiles.LavaTile;
import nl.han.ica.oopg.collision.CollidedTile;
import nl.han.ica.oopg.collision.ICollidableWithGameObjects;
import nl.han.ica.oopg.collision.ICollidableWithTiles;
import nl.han.ica.oopg.exceptions.TileNotFoundException;
import nl.han.ica.oopg.objects.AnimatedSpriteObject;
import nl.han.ica.oopg.objects.GameObject;
import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.sound.Sound;
import processing.core.PConstants;
import processing.core.PVector;

import java.util.ArrayList;
import java.util.List;

public final class Player extends AnimatedSpriteObject implements ICollidableWithTiles, ICollidableWithGameObjects {

    private static final int SPACE_BAR = 32;

    private final Sound jumpSound;
    private final MainApp app;
    private final List<Key> keys = new ArrayList<>();

    private int walkingSpeed = 5;
    private int jumpingSpeed = 8;

    private boolean jump;
    private boolean onFloorTile;

    public Player(MainApp app) {

        super(new Sprite(MainApp.MEDIA_URL.concat("sprites/characters/mario.png")), 7);
        this.jumpSound = new Sound(app, MainApp.MEDIA_URL.concat(("sounds/jump_11.wav")));
        this.app = app;
        this.keys.add(new Key(PConstants.LEFT));
        this.keys.add(new Key(PConstants.RIGHT));
        initPlayer();

    }

    private void initPlayer() {
        this.setSpeed(this.walkingSpeed);
        this.setGravity(0.2f);
    }


    @Override
    public void keyPressed(int intValue, char charValue) {

        this.setKeyPressed(intValue, true);

        switch (intValue) {

            case PConstants.LEFT  :
            case PConstants.RIGHT : move(intValue);
                                    break;
            case Player.SPACE_BAR  :jump();
                                    break;
        }
    }

    private void setKeyPressed(int intValue, boolean pressed) {

        for (Key key : keys) {
            if (key.getKeyValue() == intValue) {
                key.setPressed(pressed);
            }
        }
    }

    private boolean pressedDoubleKey() {

        for (Key key : keys) {
            if (key.isPressed()) {
                return true;
            }
        }
        return false;
    }

    private void jump() {

        if (this.onFloorTile && this.pressedDoubleKey()) {

            this.doDirectionalJump();

        } else {

            this.doVerticalJump();
        }

        this.setCurrentFrameIndex(3); // Change sprite index to jump motion.
        this.jump = true;
        this.onFloorTile = false;
        this.jumpSound.cue(0);
        this.jumpSound.play();
    }

    private void doVerticalJump() {

        this.setDirectionSpeed(360, jumpingSpeed);
    }


    public void doDirectionalJump() {

        for (Key key : keys) {

            if (key.getKeyValue() == PConstants.RIGHT && key.isPressed()) {

                this.setDirectionSpeed(30, jumpingSpeed);

            } else if (key.getKeyValue() == PConstants.LEFT && key.isPressed()) {

                this.setDirectionSpeed(320, jumpingSpeed);

            }
        }

    }

    private void move(int direction) {

        if (direction == PConstants.RIGHT)  {

            setDirectionSpeed(90, this.walkingSpeed);
        } else if (direction == PConstants.LEFT) {

            setDirectionSpeed(270, this.walkingSpeed);
        }
        this.nextFrame();
    }

    @Override
    public void keyReleased(int intValue, char charValue) {

        this.setKeyPressed(intValue, false);
        if (intValue != Player.SPACE_BAR) this.setSpeed(0);

    }

    @Override
    public void gameObjectCollisionOccurred(List<GameObject> list) {
        // Empty method.

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

    public void update() {
        // Empty method.

    }
}
