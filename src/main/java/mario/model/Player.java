package mario.model;

import mario.MainApp;
import mario.model.enemy.Enemy;
import mario.model.map.MapLoader;
import mario.model.map.tiles.*;
import mario.view.end.EndScreen;
import nl.han.ica.oopg.collision.CollidedTile;
import nl.han.ica.oopg.collision.ICollidableWithGameObjects;
import nl.han.ica.oopg.collision.ICollidableWithTiles;
import nl.han.ica.oopg.exceptions.TileNotFoundException;
import nl.han.ica.oopg.objects.AnimatedSpriteObject;
import nl.han.ica.oopg.objects.GameObject;
import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.sound.Sound;
import nl.han.ica.oopg.tile.TileMap;
import processing.core.PVector;
import java.util.ArrayList;
import java.util.List;

/**
 * This class represents the player.
 */
public class Player extends AnimatedSpriteObject implements ICollidableWithTiles, ICollidableWithGameObjects {

    private static final int SPACE_BAR = 32;
    private static final float JUMPING_SPEED = 8;
    private static final float GROUND_SPEED = 5;

    private final Sound jumpSound;
    private final Sound keyPickup;
    private final MainApp app;
    private final List<Key> keys = new ArrayList<>();
    private final String name;

    private float airspeed = 4;
    private int keysCollected = 0;
    private int healthPoints = 3;
    private boolean jump;
    private boolean onFloorTile;
    private boolean successFull;

    public Player(MainApp app, String name) {
        super(new Sprite(MainApp.MEDIA_URL.concat("media/sprites/characters/mario.png")), 7);
        this.jumpSound = new Sound(app, MainApp.MEDIA_URL.concat(("media/sounds/jump_11.wav")));
        this.keyPickup = new Sound(app, MainApp.MEDIA_URL.concat("media/sounds/key_pickup.wav"));
        this.app = app;
        this.name = name;
        this.keys.add(new Key(LEFT));
        this.keys.add(new Key(RIGHT));
        this.initPlayer();
    }

    /**
     * This method initializes the player.
     */
    private void initPlayer() {
        this.setSpeed(GROUND_SPEED);
        this.setGravity(0.3f);
    }


    /**
     * This method removes one healthPoints point.
     */
    private void removeOneHealthPoint() {
        this.healthPoints--;
        if (this.healthPoints == 0) {
            this.app.setTileMap(new TileMap(64, this.app.getTileMap().getTileTypes(), MapLoader.loadEmptyMap()));
            new EndScreen(this.app, this);
            this.resetPlayer();
        }
    }

    @Override
    public void keyPressed(int intValue, char charValue) {
        this.setKeyPressed(intValue, true);

        switch (intValue) {

            case LEFT:
                this.setDirectionSpeed(270, jump ? this.airspeed : GROUND_SPEED);
                break;

            case RIGHT:

                this.setDirectionSpeed(90, jump ? this.airspeed : GROUND_SPEED);
                break;

            case SPACE_BAR:
                if (onFloorTile) {
                    this.jump = true;
                    this.jump();
                }
                break;

            default:
                // Log error to file.
        }
    }

    /**
     * When a key is pressed, this method sets the corresponding key to pressed
     * in they keys list.
     * @param intValue UTF-8 value of the key that was pressed.
     * @param pressed a boolean.
     */
    private void setKeyPressed(int intValue, boolean pressed) {

        for (Key key : keys) {
            if (key.getKeyValue() == intValue) {
                key.setPressed(pressed);
            }
        }
    }

    /**
     * The method allows the player to jump.
     */
    private void jump() {
        if (this.isKeyPressed()) {
            this.doDirectionalJump();
        } else {
            this.doVerticalJump();
        }

        this.setCurrentFrameIndex(3); // Change sprite index to jump motion.
        this.onFloorTile = false;
        this.jumpSound.cue(0);
        this.jumpSound.play();
    }

    /**
     * This method performs a vertical jump.
     */
    private void doVerticalJump() {
        this.setDirectionSpeed(360, JUMPING_SPEED);
    }


    /**
     * This method performs a directional jump.
     */
    private void doDirectionalJump() {
        for (Key key : keys) {
            if (key.getKeyValue() == RIGHT && key.isPressed()) {
                this.setDirectionSpeed(30, JUMPING_SPEED);

            } else if (key.getKeyValue() == LEFT && key.isPressed()) {
                this.setDirectionSpeed(320, JUMPING_SPEED);
            }
        }
    }

    @Override
    public void keyReleased(int intValue, char charValue) {

        this.setKeyPressed(intValue, false);


        if (intValue != Player.SPACE_BAR) { // If you set speed to 0 when jumping (pressing space bar) you need to keep space bar pressed down to gain height.
            this.setSpeed(0);
        }

    }

    /**
     * This methods resets the player.
     */
    private void resetPlayer() {
        this.healthPoints = 3;
        this.keysCollected = 0;
    }

    /**
     * This method moves the player to his
     * starting location.
     */
    private void moveToStart() {
        this.setSpeed(0);
        this.setX(508);
        this.setY(802);

    }

    @Override
    public void gameObjectCollisionOccurred(List<GameObject> list) {

        for (GameObject object : list) {

            if (object instanceof Enemy) {

                if (this.getY() + this.getHeight() <= object.getCenterY()) {
                    this.app.deleteGameObject(object);
                } else {
                    this.playerDies();
                }
            }
        }
    }

    @Override
    public void tileCollisionOccurred(List<CollidedTile> collidedTiles) {
        for (CollidedTile tile : collidedTiles) {

            PVector tilePixelLocation = this.app.getTileMap().getTilePixelLocation(tile.getTile());
            PVector tileIndexLocation = this.app.getTileMap().getTileIndex(tile.getTile());

            if (tile.getTile() instanceof FloorTile) {
                resetFromJump();
                parseCollisionSide(tile, tilePixelLocation);

            } else if (tile.getTile() instanceof LavaTile) {
                this.playerDies();
                break;

            } else if (tile.getTile() instanceof KeyTile) {
                try {
                    pickupKey(tileIndexLocation);
                } catch (TileNotFoundException e) {
                    e.printStackTrace();
                }

            } else if (tile.getTile() instanceof DoorTile && keysCollected == 5) {
                this.showEndScreen();
                break;
            }
        }
    }

    /**
     * This method shows the end screen.
     */
    private void showEndScreen() {
        this.app.setTileMap(new TileMap(64, this.app.getTileMap().getTileTypes(), MapLoader.loadEmptyMap()));
        this.successFull = true;
        this.app.updateGame();
        new EndScreen(this.app, this);
        this.resetPlayer();
    }

    /**
     * This method resets animation frame and airspeed after jump.
     */
    private void resetFromJump() {
        if (this.jump) {
            this.setCurrentFrameIndex(0);
            this.jump = false;
            this.airspeed = 4; // Reset airspeed to default value.
        }
    }

    /**
     * This method performs actions when player dies.
     */
    private void playerDies() {
        this.removeOneHealthPoint();
        this.moveToStart();
    }

    /**
     * This method performs key pickup actions.
     * @param tileIndexLocation the location of the key tile in the list.
     */
    private void pickupKey(PVector tileIndexLocation) {
        this.app.getTileMap().setTile((int) tileIndexLocation.x, (int) tileIndexLocation.y, -1); // Set the location of the key tile in the map to -1 indicating that is now empty.
        this.keysCollected++;
        this.keyPickup.cue(0);
        this.keyPickup.play();
    }

    /**
     * This method checks which side of a tile the player
     * collided with.
     * @param tile a tile
     * @param tilePixelLocation the location of a pixel in the tile.
     */
    private void parseCollisionSide(CollidedTile tile, PVector tilePixelLocation) {

        switch (tile.getCollisionSide()) {

            case LEFT:

                this.setX(tilePixelLocation.x - this.app.getTileMap().getTileSize());
                break;

            case RIGHT:
                this.setX(tilePixelLocation.x + this.app.getTileMap().getTileSize());
                break;

            case TOP:
                this.onFloorTile = true;
                this.setY(tilePixelLocation.y - this.height); // Top and bottom case very much the same. Move to method.
                this.setySpeed(0);
                break;

            case BOTTOM:
                this.setY(tilePixelLocation.y + this.height);
                this.setSpeed(0);
                break;
            default:
                // Log error to file.

        }
    }

    @Override
    public void update() {
        final double airSpeedReduction = 0.05f;

        if (jump) {
            this.airspeed -= this.airspeed >= 0 + airSpeedReduction ? airSpeedReduction : 0;
        }

        if (isKeyPressed() && onFloorTile && this.app.frameCount % 4 == 0) {
           this.nextFrame();
        }
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return healthPoints;
    }

    public boolean isSuccessFull() {
        return this.successFull;
    }

    public int getKeysCollected() {
        return keysCollected;
    }

    private boolean isKeyPressed() {
        for (Key key : keys) {
            if (key.isPressed()) {
                return true;
            }
        }
        return false;
    }
}
