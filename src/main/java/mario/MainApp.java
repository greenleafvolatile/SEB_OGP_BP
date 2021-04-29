package mario;

import mario.view.menu.MenuScreen;
import nl.han.ica.oopg.engine.GameEngine;
import nl.han.ica.oopg.sound.Sound;

/**
 * The Main app.
 */
public class MainApp extends GameEngine {

    /**
     * The file path constant MEDIA_URL.
     */
    public static final String MEDIA_URL = "src/main/java/mario/resources/";


    /**
     * The entry point of the application.
     *
     * @param args the input arguments
     */
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
