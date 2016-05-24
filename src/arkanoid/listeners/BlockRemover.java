package arkanoid.listeners;

import arkanoid.Counter;
import arkanoid.game.GameLevel;
import arkanoid.sprites.Ball;
import arkanoid.sprites.Block;

/**
 * @author Roey Shefi & Oded Thaller
 * @version 1.0
 * @since 09/05/2016
 */
public class BlockRemover implements HitListener {

    private GameLevel game;
    private Counter removedBlocks;

    public BlockRemover(GameLevel game, Counter removedBlocks) {
        this.game = game;
        this.removedBlocks = removedBlocks;
    }

    /**
     * Checks if a hit block is out of hit points. if so, removes it from the game.
     * <p>
     * @param beingHit the object being hit.
     * @param hitter the ball that's hitting.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        if (beingHit.getHitPoints() == 0) {
            beingHit.removeFromGame(this.game);
            this.removedBlocks.decrease(1);
        }
    }
}
