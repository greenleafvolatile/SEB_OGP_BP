package mario;

import mario.tiles.FloorTile;
import nl.han.ica.oopg.collision.CollidedTile;
import nl.han.ica.oopg.collision.ICollidableWithGameObjects;
import nl.han.ica.oopg.collision.ICollidableWithTiles;
import nl.han.ica.oopg.exceptions.TileNotFoundException;
import nl.han.ica.oopg.objects.AnimatedSpriteObject;
import nl.han.ica.oopg.objects.GameObject;
import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.sound.Sound;
import processing.core.PVector;
import tutorials.TutorialWorld;

import java.util.List;
import java.util.logging.Logger;

public class Player extends AnimatedSpriteObject implements ICollidableWithTiles, ICollidableWithGameObjects {

    private final MainApp app;
    private final Sound jumpSound;

    private int speed = 1;
    private float gravity;

    public Player(MainApp app) {

        super(new Sprite(MainApp.MEDIA_URL.concat("sprites/characters/mario.png")), 7);
        this.app = app;
        this.jumpSound = new Sound(app, MainApp.MEDIA_URL.concat("sounds/jump_11.wav"));
        this.gravity = 0.6f;
        this.initPlayer();
    }

    private void initPlayer() {

        this.setGravity(gravity);
        this.setSpeed(speed);
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
                    vector = this.app.getTileMap().getTilePixelLocation(tile.getTile());
                    this.setY(vector.y - getHeight());

                } catch (TileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void update() {
        //System.out.println("Speed: " + this.speed);
        //System.out.println("Gravity: " +  this.gravity);
        //System.out.println("yPos: " + this.getY());

    }

    @Override
    public void keyPressed(int keyCode, char key) {

        if (keyCode == TutorialWorld.RIGHT) {
            setDirectionSpeed(90, speed);
        }

        if (keyCode == TutorialWorld.LEFT) {
            setDirectionSpeed(270, speed);
        }

        if (key == ' ') {
            setDirectionSpeed(360, 5);
            this.jumpSound.cue(0);
            this.jumpSound.play();
        }
    }

    @Override
    public void keyReleased(int keyCode, char key) {
        this.setSpeed(0);
    }
}
