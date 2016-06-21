package spaceinvaders.listeners;

import spaceinvaders.game.Counter;
import spaceinvaders.game.GameLevel;
import spaceinvaders.invaders.Alien;
import spaceinvaders.sprites.Ball;
import spaceinvaders.sprites.Sprite;

/**
 * @author Roey Shefi & Oded Thaller
 * @version 1.0
 * @since 09/05/2016
 */
public class AlienRemover implements HitListener {

    private GameLevel game;
    private Counter removedAliens;

    /**
     * The constructor.
     *
     * @param game          the game level.
     * @param removedAliens the counter of removed balls.
     */
    public AlienRemover(GameLevel game, Counter removedAliens) {
        this.game = game;
        this.removedAliens = removedAliens;
    }

    /**
     * Checks if a hit block is out of hit points. if so, removes it from the game.
     * <p>
     *
     * @param beingHit the object being hit.
     * @param hitter   the ball that's hitting.
     */
    public void hitEvent(Sprite beingHit, Ball hitter) {
        Alien beingHit1 = (Alien) beingHit;
        if (beingHit1.getHits() == 0) {
            beingHit1.removeFromGame(this.game);
            this.removedAliens.decrease(1);
        }
    }
}
