package spaceinvaders.listeners;

import spaceinvaders.game.Counter;
import spaceinvaders.sprites.Ball;
import spaceinvaders.sprites.Sprite;

/**
 * @author Roey Shefi & Oded Thaller
 * @version 1.0
 * @since 10/05/2016
 */

public class ScoreTrackingListener implements HitListener {

    private Counter currentScore;

    /**
     * Constructor.
     *
     * @param scoreCounter the counter that counts the score.
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    /**
     * Adds to the score of the game.
     *
     * @param beingHit the object being hit.
     * @param hitter   the ball that's hitting.
     */
    public void hitEvent(Sprite beingHit, Ball hitter) {
        this.currentScore.increase(100);
    }
}