package spaceinvaders.listeners;

import spaceinvaders.sprites.Ball;
import spaceinvaders.sprites.Sprite;

/**
 * @author Roey Shefi & Oded Thaller
 * @version 1.0
 * @since 17/05/2016
 */
public interface HitListener {

    /**
     * Called whenever the relevant object is hit.
     * <p>
     *
     * @param beingHit the object being hit.
     * @param hitter   the ball that's hitting.
     */
    void hitEvent(Sprite beingHit, Ball hitter);
}
