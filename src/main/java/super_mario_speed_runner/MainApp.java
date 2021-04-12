package super_mario_speed_runner;

import nl.han.ica.oopg.engine.GameEngine;
import nl.han.ica.oopg.view.View;

public class MainApp extends GameEngine {

    public static String MEDIA_URL = "src/main/java/super_mario_speed_runner/media/";

    public static void main(String[] args) {
        MainApp app = new MainApp();

        app.runSketch();
    }


    @Override
    public void setupGame() {
        int screenWidth = 1024;
        int screenHeight = 768;

        size(screenWidth, screenHeight);

        Player player = new Player(this);
        addGameObject(player, 0, 0);

        View view = new View(screenWidth, screenHeight);
        setView(view);
    }

    @Override
    public void update() {

    }
}
