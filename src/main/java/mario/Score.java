package mario;

public class Score implements Comparable<Score>{

    private String name;
    private String time;

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
}
