package arkanoid.animation;

import arkanoid.game.Counter;
import biuoop.DrawSurface;
import java.awt.Color;

/**
 * @author Roey Shefi & Oded Thaller
 * @version 1.0
 * @since 15/5/2016
 */

public class EndScreen implements Animation {

    private Counter score;
    private boolean win;

    /**
     * Instantiates a new End Screen.
     *
     * @param score the current score
     * @param win   the win condition
     */
    public EndScreen(Counter score, boolean win) {
        this.score = score;
        this.win = win;
    }

    /**
     * Does one frame.
     * <p>
     *
     * @param d  the draw surface.
     * @param dt is the time passed from previous frame.
     */
    public void doOneFrame(DrawSurface d, double dt) {
        if (this.win) {
            d.setColor(Color.green);
            d.fillRectangle(0, 0, 800, 600);
            d.setColor(Color.black);
            d.drawText(300, 200, "You Win!", 50);
        } else {
            d.setColor(Color.red);
            d.fillRectangle(0, 0, 800, 600);
            d.setColor(Color.black);
            d.drawText(250, 200, "Game Over...", 50);
        }
        d.drawText(200, 400, "Your score is: " + this.score.getValue(), 50);
    }

    /**
     * When to stop.
     * <p>
     *
     * @return when to stop.
     */
    public boolean shouldStop() { return false; }
}