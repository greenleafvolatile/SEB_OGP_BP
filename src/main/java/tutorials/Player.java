package tutorials;

import nl.han.ica.oopg.collision.CollidedTile;
import nl.han.ica.oopg.collision.ICollidableWithTiles;
import nl.han.ica.oopg.exceptions.TileNotFoundException;
import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.objects.SpriteObject;
import nl.han.ica.oopg.sound.Sound;
import processing.core.PVector;
import tutorials.tiles.FloorTile;

import java.util.List;

public class Player extends SpriteObject implements ICollidableWithTiles {

    private TutorialWorld world;
    private Sound sound;

    public Player(TutorialWorld world, Sound sound) {
        // Met `.concat()` plak je 2 strings aan elkaar.
        // De methode returned een nieuwe String terug.
        super(new Sprite(TutorialWorld.MEDIA_URL.concat("/sprites/characters/player.png")));
        this.world = world;
        this.sound = sound;
        initPlayer();
    }

    public void initPlayer() {
        final float gravity = 0.2f;
        this.setSpeed(1);
        this.setGravity(gravity);
    }

    @Override
    public void update() { // Dit is een goede plek om beweging van een karakter bij te houden.

        //System.out.println(this.getSpeed());
    }

    @Override
    public void keyPressed(int keyCode, char key) {
        final int speed = 1;
        if (keyCode == TutorialWorld.RIGHT) {
            setDirectionSpeed(90, speed);
        } else if (keyCode == TutorialWorld.LEFT) {
            setDirectionSpeed(270, speed);
        } else if (key == ' ') {
            setDirectionSpeed(360, 5);
            sound.cue(0);
            sound.play();
        }

    }

    @Override
    public void keyReleased(int keyCode, char key) {
        this.setSpeed(0);
    }

    @Override
    public void tileCollisionOccurred(List<CollidedTile> collidedTiles) {

        PVector vector;

        for (CollidedTile ct : collidedTiles) {
            if (ct.getTile() instanceof FloorTile) {

                try {
                    vector = world.getTileMap().getTilePixelLocation(ct.getTile());
                    setY(vector.y - getHeight());

                } catch (TileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}