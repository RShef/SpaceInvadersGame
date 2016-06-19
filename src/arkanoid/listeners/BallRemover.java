package arkanoid.listeners;

import arkanoid.game.Counter;
import arkanoid.game.GameLevel;
import arkanoid.sprites.Ball;
import arkanoid.sprites.Block;
import arkanoid.sprites.Sprite;

/**
 * @author Roey Shefi & Oded Thaller
 * @version 1.0
 * @since 15/05/2016
 */

public class BallRemover implements HitListener {
    private GameLevel game;
    private Counter removedBalls;

    /**
     * The constructor.
     *
     * @param game         the game level.
     * @param removedBalls the counter of removed balls.
     */
    public BallRemover(GameLevel game, Counter removedBalls) {
        this.game = game;
        this.removedBalls = removedBalls;
    }

    /**
     * Removes the ball that hit the "death zone".
     * <p>
     *
     * @param hitter the ball that hit the "death zone" block.
     */

    @Override
    public void hitEvent(Sprite beingHit, Ball hitter) {
        hitter.removeFromGame(this.game);
        this.removedBalls.decrease(1);
    }
}