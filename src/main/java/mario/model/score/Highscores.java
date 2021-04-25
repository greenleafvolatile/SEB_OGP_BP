package mario.model.score;


import mario.model.score.Score;
import nl.han.ica.oopg.persistence.FilePersistence;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Highscores {

    private static final FilePersistence fileHandler = new FilePersistence("/main/java/mario/media/highscores/highscores.txt");

    private Highscores(){}

    public static List<Score> loadHighscores() {

        List<Score> highscores = new ArrayList<>();

        String[] saveData = fileHandler.loadDataStringArray("\n");

        for (String string : saveData)  {

            String[] highscore = string.split(" ");
            highscores.add(new Score(highscore[0], highscore[1]));

        }

        Collections.sort(highscores);

        return highscores;
    }

    private static void saveHighscores(List<Score> highscores) {

        List<String> scores = new ArrayList<>();

        for (Score highscore: highscores) {
            String score = highscore.toString();
            scores.add(score);
        }

        String[] result = new String[scores.size()];
        fileHandler.saveData(scores.toArray(result), "\n");
    }

    public static void addHighscore(Score score) {

        List<Score> highscores = loadHighscores();

        if (isHighscore(score) && !highscores.contains(score)) {


            for (int i = highscores.size() - 1; i > 0; i--) {
                if(score.compareTo(highscores.get(i)) < 0) {
                    highscores.add(i, score);
                    break;
                }
            }

            highscores.remove(highscores.size() - 1);
            saveHighscores(highscores);

        }

    }

    private static boolean isHighscore(Score score) {

        List<Score> highscores = loadHighscores();
        if (score.compareTo(highscores.get(highscores.size() - 1)) < 0) {
            return true;
        }
        return false;
    }
}
