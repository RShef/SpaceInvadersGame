package arkanoid.animation;

import arkanoid.game.Counter;
import biuoop.DrawSurface;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * @author Roey Shefi & Oded Thaller
 * @version 1.0
 * @since 15/5/2016
 */

public class PauseScreen implements Animation {

    private Counter score;
    private Counter lives;
    private String levelName;

    /**
     * Instantiates a new Pause Screen.
     * <p>
     */
    public PauseScreen(Counter score, Counter lives, String levelName) {
        this.score = score;
        this.lives = lives;
        this.levelName = levelName;
    }

    /**
     * Does on frame.
     *
     * @param d  the draw surface.
     * @param dt is the time passed from previous frame.
     */
    public void doOneFrame(DrawSurface d, double dt) {

        Image back = null;
        try {
            back = ImageIO.read(new File("resources/background_images/ripples.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        d.drawImage(0, 0, back);

        d.drawText(300, 50, "Level Name: " + this.levelName, 20);
        d.drawText(300, 100, "Score: " + String.valueOf(this.score.getValue()), 20);
        d.drawText(300, 150, "Lives: " + String.valueOf(this.lives.getValue()), 20);
        d.setColor(Color.blue);
        d.fillCircle(400, 300, 110);
        d.setColor(Color.black);
        d.fillRectangle(340, 250, 50, 100);
        d.fillRectangle(410, 250, 50, 100);

        String s = "Press space to continue";
        d.drawText(220, 500, s, 32);
    }

    /**
     * When to stop.
     *
     * @return when to stop.
     */
    public boolean shouldStop() {
        return false;
    }
}