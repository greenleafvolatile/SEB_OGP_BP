package mario.dashboard;

import mario.MainApp;
import nl.han.ica.oopg.dashboard.Dashboard;
import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.objects.SpriteObject;

public class DashboardKey extends SpriteObject {

    public DashboardKey() {

        super(new Sprite(MainApp.MEDIA_URL.concat("/sprites/items/hud_keyYellow.png")));
    }

    public void update() {

    }
}
