package arkanoid.sprites;

import biuoop.DrawSurface;

/**
 * @author Roey Shefi & Oded Thaller
 * @version 1.0
 * @since 3/31/2016
 */

public interface Sprite {

    /**
     * Draws the sprite on the screen.
     * <p>
     *
     * @param d the draw surface.
     */
    void drawOn(DrawSurface d);

    /**
     * Notify the Sprite that time has passed.
     * <p>
     */
    void timePassed();

}
