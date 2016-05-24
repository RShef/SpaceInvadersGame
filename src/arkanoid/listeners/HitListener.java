package arkanoid.listeners;

import arkanoid.sprites.Ball;
import arkanoid.sprites.Block;

public interface HitListener {

    /**
     * Called whenever the relevant object is hit.
     * <p/>
     *
     * @param beingHit the object being hit.
     * @param hitter   the ball that's hitting.
     */
    void hitEvent(Block beingHit, Ball hitter);
}
