package mario;

import nl.han.ica.oopg.engine.GameEngine;

public class MainApp extends GameEngine {

    public static String MEDIA_URL = "src/main/java/mario/media/";
    private StateManager stateManager;


    public static void main(String[] args) {

        MainApp app = new MainApp();
        app.runSketch();
    }

    @Override
    public void setupGame() {
        int screenWidth = 1024;
        int screenHeight = 768;
        size(screenWidth, screenHeight);

        this.stateManager = new StateManager(this);
        stateManager.drawState();
    }

    @Override
    public void update() {}
}
