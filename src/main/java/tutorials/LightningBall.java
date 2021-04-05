package tutorials;

import nl.han.ica.oopg.collision.ICollidableWithGameObjects;
import nl.han.ica.oopg.objects.GameObject;
import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.objects.SpriteObject;

import java.util.List;

public class LightningBall extends SpriteObject implements ICollidableWithGameObjects {

    private TutorialWorld world;

    final int speed = 1;

    public LightningBall(TutorialWorld world) {
        super(new Sprite(TutorialWorld.MEDIA_URL.concat("sprites/tiles/platformPack_tile012.png")));
        this.setDirectionSpeed(90, speed);
        this.world = world;
    }




    @Override
    public void update() {
        if (x < 0) {
            setDirectionSpeed(90, speed);
        } else if (x > world.getWidth() - this.getWidth()){
            setDirectionSpeed(270, speed);
        }


    }


    @Override
    public void gameObjectCollisionOccurred(List<GameObject> list) {

        for (GameObject go : list) {
            if (go instanceof Player) {
                world.deleteGameObject(this);
            }
        }


    }
}
