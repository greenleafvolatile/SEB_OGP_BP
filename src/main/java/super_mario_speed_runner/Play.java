package super_mario_speed_runner;

import nl.han.ica.oopg.objects.TextObject;

public class Play {

    private MainApp app;

    public Play(MainApp app) {
        this.app = app;
        System.out.println("PLAY");

        TextObject to = new TextObject("Hello!", 40);
        to.setForeColor(255, 255, 255, 255);
        app.addGameObject(to, 100, 100);
    }
}
