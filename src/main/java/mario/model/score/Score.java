package mario.model.score;

/**
 * This class represents a score in the game consisting
 * of a name and a time.
 *
 * @author Christiaan Wiggers en Daan Pol
 * @version 1.0
 * @since 20-04-2021
 *
 */
public class Score implements Comparable<Score>{

    private final String name;
    private final String time;

    /**
     * The constructor
     * @param name the player's name
     * @param time the time it took the player to finish the game.
     */
    public Score(String name, String time) {
        this.name = name;
        this.time = time;
    }

    @Override
    public int compareTo(Score highscore) {
        return this.time.compareTo(highscore.time);
    }

    @Override
    public String toString() {
        return this.name + " " + this.time;
    }

    @Override
    public boolean equals(Object object)  {

        if (this == object) {
            return true;
        }

        if (object == null) {
            return false;
        }

        if (object.getClass() != this.getClass()) {
            return false;
        }

        Score score = (Score) object;

        return this.name.equals(score.name) && this.time.equals(score.time);

    }

    public String getName() {
        return this.name;
    }

    public String getTime() {
        return this.time;
    }
}
