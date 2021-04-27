package mario.model.score;

import mario.MainApp;
import nl.han.ica.oopg.persistence.FilePersistence;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This is a utility class for loading, adding and saving highscores from and to a file.
 *
 * @author Christiaan Wiggers en Daan Pol
 * @version 1.1
 * @since 20-04-2021
 */
public class Highscores {

    private static final FilePersistence fileHandler = new FilePersistence("main/java/mario/resources/data/highscores/highscores.txt");

    /**
     * A private constructor. This class is a utility class that should not be instantiated.
     */
    private Highscores(){}

    /**
     * This method loads the highscores from a file.
     * @return a list of scores.
     */
    public static List<Score> loadHighscores() {

        final List<Score> highscores = new ArrayList<>();
        final String[] saveData = fileHandler.loadDataStringArray("\n");

        for (String string : saveData)  {
            String[] highscore = string.split(" ");
            highscores.add(new Score(highscore[0], highscore[1]));
        }

        Collections.sort(highscores);
        return highscores;
    }

    /**
     * This method saves a list of scores to a file.
     * @param highscores a list of scores.
     */
    private static void saveHighscores(List<Score> highscores) {

        final List<String> scores = new ArrayList<>();

        for (Score highscore: highscores) {
            String score = highscore.toString();
            scores.add(score);
        }

        String[] result = new String[scores.size()];
        fileHandler.saveData(scores.toArray(result), "\n");
    }

    /**
     * This method saves a highscore to file.
     * @param score the score.
     */
    public static void addHighscore(Score score) {

        final List<Score> highscores = loadHighscores();

        // Check if score is a highscore AND check if score does
        // not already exist as a highscore.
        if (isHighscore(score) && !highscores.contains(score)) {

            for (int i = 0; i < highscores.size() - 1; i++) {

                if(score.compareTo(highscores.get(i)) < 0) {
                    highscores.add(i, score);
                    break;
                }
            }

            highscores.remove(highscores.size() - 1);
            saveHighscores(highscores);
        }
    }

    /**
     * This method checks to see if a score ia a highscore.
     * @param score the score to check
     * @return boolean true if a highscore else false.
     */
    private static boolean isHighscore(Score score) {

        final List<Score> highscores = loadHighscores();

        return score.compareTo(highscores.get(highscores.size() - 1)) < 0;
    }
}
