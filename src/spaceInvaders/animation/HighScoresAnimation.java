package spaceinvaders.animation;

import spaceinvaders.game.HighScoresTable;
import biuoop.DrawSurface;

import javax.imageio.ImageIO;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

/**
 * @author Roey Shefi & Oded Thaller
 * @version 1.0
 * @since 30/05/2016
 */

public class HighScoresAnimation implements Animation {

    private HighScoresTable scores;

    /**
     * The constructor.
     *
     * @param scores the score.
     */
    public HighScoresAnimation(HighScoresTable scores) {
        this.scores = scores;
    }

    /**
     * Displays a num from to countdown.
     * <p/>
     *
     * @param d  is the draw surface.
     * @param dt is the diversion speed.
     */
    @Override
    public void doOneFrame(DrawSurface d, double dt) {

        Image back = null;
        try {
            back = ImageIO.read(new File("resources/background_images/blurred.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        d.drawImage(0, 0, back);

        d.drawText(110, 100, "High Scores", 100);
        int y = 200;
        for (int i = 0; i < this.scores.getHighScores().size(); i++) {
            d.drawText(110, y, (i + 1) + ". " + this.scores.getHighScores().get(i).getName(), 25);
            d.drawText(650, y, String.valueOf(this.scores.getHighScores().get(i).getScore()), 25);
            y += 35;
        }
    }

    /**
     * When to stop.
     * <p/>
     *
     * @return if it reached zero.
     */
    @Override
    public boolean shouldStop() {
        return false;
    }
}