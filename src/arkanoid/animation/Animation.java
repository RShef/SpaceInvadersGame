package arkanoid.animation;

import biuoop.DrawSurface;

/**
 * @author Roey Shefi & Oded Thaller
 * @version 1.0
 * @since 17/05/2016
 */
public interface Animation {
    void doOneFrame(DrawSurface d);

    boolean shouldStop();
}
