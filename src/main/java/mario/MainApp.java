package mario;

import mario.model.score.Highscores;
import mario.model.score.Score;
import mario.view.menu.MenuView;
import nl.han.ica.oopg.engine.GameEngine;
import nl.han.ica.oopg.sound.Sound;

import java.util.List;

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

        Sound backgroundMusic = new Sound(this, MEDIA_URL.concat("sounds/background_loop.WAV"));
        backgroundMusic.loop(-1);

        new MenuView(this);

    }

    @Override
    public void update() {}
}
