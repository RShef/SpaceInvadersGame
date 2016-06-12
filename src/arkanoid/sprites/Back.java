package arkanoid.sprites;

import biuoop.DrawSurface;


/**
 * @author Roey Shefi & Oded Thaller
 * @version 1.0
 * @since 19/5/2016
 */
public class Back implements Sprite {
    /**
     * The constactor.
     */
    private SpriteCollection spc;

    /**
     * The constactor.
     *
     * @param spc the sprite collection.
     */
    public Back(SpriteCollection spc) {
        this.spc = spc;
    }

    /**
     * Draws the background.
     * <p/>
     *
     * @param d the draw surface.
     */
    @Override
    public void drawOn(DrawSurface d) {
        for (Sprite s : this.spc.getSprites()) {
            s.drawOn(d);
        }

    }

    /**
     * Rubbish.
     *
     * @param dt is the time passed from previous frame.
     */
    @Override
    public void timePassed(double dt) {

    }
}
