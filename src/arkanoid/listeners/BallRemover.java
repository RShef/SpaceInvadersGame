package arkanoid.listeners;
import arkanoid.Counter;
import arkanoid.game.GameLevel;
import arkanoid.sprites.Ball;
import arkanoid.sprites.Block;

/**
 * @author Roey Shefi & Oded Thaller
 * @version 1.0
 * @since 15/05/2016
 */

public class BallRemover implements HitListener {
    private GameLevel game;
    private Counter removedBalls;

    public BallRemover(GameLevel game, Counter removedBalls) {
        this.game = game;
        this.removedBalls = removedBalls;
    }

    /**
     *  Removes the ball that hit the "death zone".
     *  <p>
     * @param death - the bottom block. "death zone".
     * @param hitter the ball that hit the "death zone" block.
     */
    public void hitEvent(Block death, Ball hitter) {
            hitter.removeFromGame(this.game);
            this.removedBalls.decrease(1);
    }
}