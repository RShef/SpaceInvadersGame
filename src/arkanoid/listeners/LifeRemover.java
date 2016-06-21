package arkanoid.listeners;

import arkanoid.game.Counter;
import arkanoid.game.GameLevel;
import arkanoid.invaders.Alien;
import arkanoid.sprites.Ball;
import arkanoid.sprites.Block;
import arkanoid.sprites.Paddle;
import arkanoid.sprites.Sprite;

/**
 * @author Roey Shefi & Oded Thaller
 * @version 1.0
 * @since 09/05/2016
 */
public class LifeRemover implements HitListener {

    private GameLevel game;
    private boolean hit;

    /**
     * The constructor.
     *
     * @param game          the game level.
     */
    public LifeRemover(GameLevel game) {
        this.game = game;
        this.hit = false;
    }

    /**
     * Checks if a hit block is out of hit points. if so, removes it from the game.
     * <p>
     *
     * @param beingHit the object being hit.
     * @param hitter   the ball that's hitting.
     */
    public void hitEvent(Sprite beingHit, Ball hitter) {
        this.hit = true;
    }

    public boolean isHit() {
        return this.hit;
    }

    public void setHit() {
        this.hit = false;
    }
}
