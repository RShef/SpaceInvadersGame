package spaceinvaders.listeners;

import spaceinvaders.game.Counter;
import spaceinvaders.game.GameLevel;
import spaceinvaders.sprites.Ball;
import spaceinvaders.sprites.Block;
import spaceinvaders.sprites.Sprite;

/**
 * @author Roey Shefi & Oded Thaller
 * @version 1.0
 * @since 09/05/2016
 */
public class BlockRemover implements HitListener {

    private GameLevel game;
    private Counter removedBlocks;

    /**
     * The constructor.
     *
     * @param game          the game level.
     * @param removedBlocks the counter of removed balls.
     */
    public BlockRemover(GameLevel game, Counter removedBlocks) {
        this.game = game;
        this.removedBlocks = removedBlocks;
    }

    /**
     * Checks if a hit block is out of hit points. if so, removes it from the game.
     * <p>
     *
     * @param beingHit the object being hit.
     * @param hitter   the ball that's hitting.
     */
    public void hitEvent(Sprite beingHit, Ball hitter) {
        Block beingHit1 = (Block) beingHit;
        if (beingHit1.getHitPoints() == 0) {
            beingHit1.removeFromGame(this.game);
            this.removedBlocks.decrease(1);
        }
    }
}
