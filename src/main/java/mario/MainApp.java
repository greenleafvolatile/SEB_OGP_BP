package mario;

import nl.han.ica.oopg.engine.GameEngine;
import nl.han.ica.oopg.persistence.FilePersistence;

import java.util.ArrayList;
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


        Highscore newScore = new Highscore("Micha", "00:05"); // Die maak je aan wanneer een speler met alle vijf sleutels bij de deur is.
        List<Highscore> highscores = HighScores.getHighscores(); // Vervolgens haal je alle highscores uit de file.
        System.out.println(highscores);
        if (!highscores.contains(newScore)) {
            highscores.add(newScore); // Je voegt de nwe highscore toe aan de highscores maar alleen als die score er nog niet in zit.
        }
        HighScores.saveData(highscores); // je saved de geupdate highscores terug naar een file.
        List<Highscore> updatedHighscores = HighScores.getHighscores();
        System.out.println(updatedHighscores);





        new StateManager(this);
    }

    @Override
    public void update() {}
}
