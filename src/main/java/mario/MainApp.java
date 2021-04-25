package mario;

import mario.model.score.Highscores;
import mario.model.score.Score;
import mario.view.menu.MenuView;
import nl.han.ica.oopg.engine.GameEngine;

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

        new MenuView(this);

        List<Score> highScores = Highscores.loadHighscores();
        System.out.println(highScores);
        Score one = new Score("Test", "00:01");
        Highscores.addHighscore(one);
        List<Score> updated = Highscores.loadHighscores();
        System.out.println(updated);
    }

    @Override
    public void update() {}
}
