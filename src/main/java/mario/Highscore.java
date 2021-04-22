package mario;

public class Highscore implements Comparable<Highscore>{

    private String name;
    private String time;

    public Highscore(String name, String time) {
        this.name = name;
        this.time = time;
    }

    @Override
    public int compareTo(Highscore highscore) {
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

        Highscore score = (Highscore) object;

        return this.name.equals(score.name) && this.time.equals(score.time);

    }
}
