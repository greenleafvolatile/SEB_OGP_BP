package super_mario_speed_runner;

import nl.han.ica.oopg.objects.TextObject;

public class Game {

    private MainApp app;

    public Game(MainApp app) {
        this.app = app;
        //System.out.println("GAME");

        TextObject to = new TextObject("Game", 40);
        to.setForeColor(255, 255, 255, 255);
        app.addGameObject(to, 100, 300);
    }
}
