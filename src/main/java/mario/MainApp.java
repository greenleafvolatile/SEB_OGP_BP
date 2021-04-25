package mario;

import mario.view.menu.MenuView;
import nl.han.ica.oopg.engine.GameEngine;

public class MainApp extends GameEngine {

    public static String MEDIA_URL = "src/main/java/mario/media/";


    public static void main(String[] args) {

        MainApp app = new MainApp();
        app.runSketch();
    }

    @Override
    public void setupGame() {

        int screenWidth = 1024;
        int screenHeight = 768;

        size(screenWidth, screenHeight);

        new MenuView(this);
    }

    @Override
    public void update() {}
}
