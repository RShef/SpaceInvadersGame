package arkanoid.animation;

import arkanoid.animation.Animation;
import biuoop.DrawSurface;

/**
 * @author Roey Shefi & Oded Thaller
 * @version 1.0
 * @since 15/5/2016
 */

public class PauseScreen implements Animation {

    /**
     * Instantiates a new Pause Screen.
     * <p>
     */
    public PauseScreen() {
    }

    /**
     * Does on frame.
     *
     * @param d  the draw surface.
     * @param dt is the time passed from previous frame.
     */
    public void doOneFrame(DrawSurface d, double dt) {
        d.drawText(10, d.getHeight() / 2, "paused -- press space to continue", 32);
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