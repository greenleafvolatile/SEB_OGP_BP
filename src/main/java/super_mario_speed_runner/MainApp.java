package super_mario_speed_runner;

import nl.han.ica.oopg.engine.GameEngine;
import nl.han.ica.oopg.view.View;

public class MainApp extends GameEngine {

    public static void main(String[] args) {
        MainApp app = new MainApp();

        app.runSketch();
    }


    @Override
    public void setupGame() {
        int screenWidth = 1024;
        int screenHeight = 768;

        size(screenWidth, screenHeight);

        View view = new View(screenWidth, screenHeight);
        setView(view);
    }

    @Override
    public void update() {

    }
}
