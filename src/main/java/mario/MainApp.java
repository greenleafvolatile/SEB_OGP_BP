package mario;

import mario.view.menu.MenuScreen;
import nl.han.ica.oopg.engine.GameEngine;
import nl.han.ica.oopg.sound.Sound;

public class MainApp extends GameEngine {

    public static final String MEDIA_URL = "src/main/java/mario/resources/";


    public static void main(String[] args) {

        MainApp app = new MainApp();
        app.runSketch();
    }

    @Override
    public void setupGame() {

        int screenWidth = 1024;
        int screenHeight = 768;

        size(screenWidth, screenHeight);

        Sound backgroundMusic = new Sound(this, MEDIA_URL.concat("media/sounds/background_loop.wav"));
        backgroundMusic.loop(-1);

        new MenuScreen(this);

    }

    @Override
    public void update() {}
}
