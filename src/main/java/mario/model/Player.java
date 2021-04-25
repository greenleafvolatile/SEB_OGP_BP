package mario.model;

import mario.MainApp;
import mario.model.enemy.Enemy;
import mario.model.map.MapLoader;
import mario.model.map.tiles.*;
import mario.model.score.Highscores;
import mario.model.score.Score;
import mario.view.end.EndView;
import mario.view.menu.MenuView;
import nl.han.ica.oopg.collision.CollidedTile;
import nl.han.ica.oopg.collision.ICollidableWithGameObjects;
import nl.han.ica.oopg.collision.ICollidableWithTiles;
import nl.han.ica.oopg.exceptions.TileNotFoundException;
import nl.han.ica.oopg.objects.AnimatedSpriteObject;
import nl.han.ica.oopg.objects.GameObject;
import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.sound.Sound;
import nl.han.ica.oopg.tile.TileMap;
import nl.han.ica.oopg.tile.TileType;
import processing.core.PVector;
import java.util.ArrayList;
import java.util.List;

public final class Player extends AnimatedSpriteObject implements ICollidableWithTiles, ICollidableWithGameObjects {

    private static final int SPACE_BAR = 32;

    private final Sound jumpSound;
    private final Sound keyPickup;

    private final MainApp app;
    private final List<Key> keys = new ArrayList<>();

    private String name = "";

    private final float jumpingSpeed = 8;
    private float groundSpeed = 5;
    private float airspeed = 4;

    private int keysCollected = 0;
    private int health = 3;

    private boolean jump;
    private boolean onFloorTile;
    private boolean successFull;

    public Player(MainApp app, String name) {
        super(new Sprite(MainApp.MEDIA_URL.concat("sprites/characters/mario.png")), 7);
        this.jumpSound = new Sound(app, MainApp.MEDIA_URL.concat(("sounds/jump_11.wav")));
        this.keyPickup = new Sound(app, MainApp.MEDIA_URL.concat("sounds/key_pickup.wav"));
        this.app = app;
        this.name = name;
        this.keys.add(new Key(LEFT));
        this.keys.add(new Key(RIGHT));
        initPlayer();
    }

    private void initPlayer() {
        this.setSpeed(this.groundSpeed);
        this.setGravity(0.3f);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHealth() {
        return health;
    }

    private void removeOneHealthPoint() {
        this.health--;
    }

    public int getKeysCollected() {
        return keysCollected;
    }

    @Override
    public void keyPressed(int intValue, char charValue) {

        this.setKeyPressed(intValue, true);

        switch (intValue) {

            case LEFT:

                setDirectionSpeed(270, jump ? this.airspeed : this.groundSpeed);
                break;

            case RIGHT:

                setDirectionSpeed(90, jump ? this.airspeed : this.groundSpeed);
                break;

            case SPACE_BAR:
                if (onFloorTile) {
                    this.jump = true;
                    jump();
                }
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

    private boolean isKeyPressed() {

        for (Key key : keys) {
            if (key.isPressed()) {
                return true;
            }
        }
        return false;
    }

    private void jump() {

        if (this.isKeyPressed()) {
            doDirectionalJump();
        } else {
            doVerticalJump();
        }

        this.setCurrentFrameIndex(3); // Change sprite index to jump motion.
        this.onFloorTile = false;
        this.jumpSound.cue(0);
        this.jumpSound.play();
    }

    private void doVerticalJump() {
        setDirectionSpeed(360, jumpingSpeed);
    }


    private void doDirectionalJump() {

        for (Key key : keys) {
            if (key.getKeyValue() == RIGHT && key.isPressed()) {
                setDirectionSpeed(30, jumpingSpeed);

            } else if (key.getKeyValue() == LEFT && key.isPressed()) {
                setDirectionSpeed(320, jumpingSpeed);
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

    private void resetPlayer() {
//
//        if (this.gameDashboard.getNumberOfHearts() == 0) {
//            // Show endgame screen.
//        }
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
                    removeOneHealthPoint();
                    resetPlayer();
                }

            }

        }

    }

    @Override
    public void tileCollisionOccurred(List<CollidedTile> collidedTiles) {

        for (CollidedTile tile : collidedTiles) {

            PVector tilePixelLocation = this.app.getTileMap().getTilePixelLocation(tile.getTile());
            PVector tileIndexLocation = this.app.getTileMap().getTileIndex(tile.getTile());

            if (tile.getTile() instanceof FloorTile || tile.getTile() instanceof LavaTile) {


                if (jump) {
                    this.setCurrentFrameIndex(0);
                    jump = false;
                    this.airspeed = 4; // Magic value. Variable voor aanmaken?

                }

                if (tile.getTile() instanceof LavaTile) {
                    removeOneHealthPoint();
                    resetPlayer();
                }

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

                }

            } else if (tile.getTile() instanceof KeyTile) {

                try {

                    this.app.getTileMap().setTile((int) tileIndexLocation.x, (int) tileIndexLocation.y, -1);
                    this.keysCollected++;
                    this.keyPickup.cue(0);
                    this.keyPickup.play();
                } catch (TileNotFoundException e) {
                    e.printStackTrace();
                }

            } else if (tile.getTile() instanceof BackgroundTile) {

                if (this.keysCollected >= 0)  {

                    this.app.setTileMap(new TileMap(64, this.app.getTileMap().getTileTypes(), MapLoader.loadEmptyMap()));
                    this.successFull = true;
                    this.app.updateGame();
                    System.out.println("Player: " + this.successFull);
                    new EndView(this.app, this.name, successFull);
                    break;
                }
            }
        }
    }


    public boolean isSucessfull() {
        return this.successFull;
    }

    public void update() {
        final double airSpeedReduction = 0.05f;

        if (jump) {
            this.airspeed -= this.airspeed >= 0 + airSpeedReduction ? airSpeedReduction : 0;
        }

        if (isKeyPressed() && onFloorTile && this.app.frameCount % 4 == 0) {
           this.nextFrame();
        }
    }
}
