package arkanoid.animation;

import biuoop.DrawSurface;

/**
 * @author Roey Shefi & Oded Thaller
 * @version 1.0
 * @since 17/05/2016
 */
public interface Animation {

    /**
     * Displays a num from to countdown.
     * <p>
     * @param d is the draw surface.
     */
    void doOneFrame(DrawSurface d);

    /**
     * When to stop.
     * <p>
     * @return if it reached zero.
     */
    boolean shouldStop();
}
