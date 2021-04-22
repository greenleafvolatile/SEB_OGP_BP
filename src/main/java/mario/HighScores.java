package mario;


import nl.han.ica.oopg.persistence.FilePersistence;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HighScores {

    private static final FilePersistence fileHandler = new FilePersistence("/main/java/mario/media/highscores/highscores.txt");

    public static List<Highscore> getHighscores() {

        List<Highscore> highscores = new ArrayList<>();

        String[] saveData = fileHandler.loadDataStringArray("\n");

        for (String string : saveData)  {

            String[] highscore = string.split(" ");
            highscores.add(new Highscore(highscore[0], highscore[1]));

        }

        Collections.sort(highscores);

        return highscores;
    }

    public static void saveData(List<Highscore> highscores) {

        List<String> scores = new ArrayList<>();

        for (Highscore highscore: highscores) {
            String score = highscore.toString();
            scores.add(score);
        }

        String[] result = new String[scores.size()];
        fileHandler.saveData(scores.toArray(result), "\n");
    }
}
