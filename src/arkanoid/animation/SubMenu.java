package arkanoid.animation;

/**
 * @author Roey Shefi & Oded Thaller
 * @version 1.0
 * @since 11/06/2016
 */

import biuoop.DrawSurface;

/**
 * The class.
 */
public class SubMenu implements Animation {
    /**
     * Doing one frame method.
     *
     * @param d  is the draw surface.
     * @param dt is the time passed from previous frame.
     */
    @Override
    public void doOneFrame(DrawSurface d, double dt) {
    }

    /**
     * When to stop.
     *
     * @return if to stop.
     */
    @Override
    public boolean shouldStop() {
        return false;
    }
}