package spaceinvaders.game;

import java.io.Serializable;

/**
 * @author Roey Shefi & Oded Thaller
 * @version 1.0
 * @since 29/05/2016
 */
public class ScoreInfo implements Serializable {

    private String name;
    private int score;

    /**
     * The constructor.
     *
     * @param name  the name.
     * @param score the score.
     */
    public ScoreInfo(String name, int score) {
        this.name = name;
        this.score = score;
    }

    /**
     * Returns the name.
     *
     * @return Returns the name.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Returns the score.
     *
     * @return Returns the score.
     */
    public int getScore() {
        return this.score;
    }

}