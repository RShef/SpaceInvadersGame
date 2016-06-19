package arkanoid.listeners;

import arkanoid.sprites.Ball;
import arkanoid.sprites.Block;
import arkanoid.sprites.Sprite;

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
