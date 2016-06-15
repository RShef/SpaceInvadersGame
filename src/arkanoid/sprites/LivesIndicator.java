package arkanoid.sprites;

import arkanoid.game.Counter;
import arkanoid.game.GameLevel;
import biuoop.DrawSurface;

import java.awt.Color;


/**
 * @author Roey Shefi & Oded Thaller
 * @version 1.0
 * @since 15/5/2016
 */
public class LivesIndicator implements Sprite {

    private Counter lives;

    /**
     * The constactor.
     *
     * @param lives the lives of the player.
     */
    public LivesIndicator(Counter lives) {
        this.lives = lives;
    }

    /**
     * The draw method.
     *
     * @param d the draw surface.
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.black);
        d.drawText(150, 15, this.toString(), 15);
    }

    /**
     * For checkstlye.
     *
     * @param dt is the time passed from previous frame.
     */
    @Override
    public void timePassed(double dt) {

    }

    /**
     * To string.
     *
     * @return the lives left as a string.
     */
    @Override
    public String toString() {
        return "Lives: " + this.lives.getValue();
    }

    /**
     * Adds the lives counter to the game.
     * <p/>
     *
     * @param g the game.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}

