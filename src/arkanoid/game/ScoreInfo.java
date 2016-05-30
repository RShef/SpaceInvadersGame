package arkanoid.game;

/**
 * @author Roey Shefi & Oded Thaller
 * @version 1.0
 * @since 29/05/2016
 */
public class ScoreInfo {

    private String name;
    private int score;

    public ScoreInfo(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public String getName() {
        return this.name;
    }

    public int getScore() {
        return this.score;
    }
}