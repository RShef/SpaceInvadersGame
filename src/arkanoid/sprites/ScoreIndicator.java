package arkanoid.sprites;

import arkanoid.Counter;
import arkanoid.game.Game;
import biuoop.DrawSurface;

import java.awt.*;

/**
 * @author Roey Shefi & Oded Thaller
 * @version 1.0
 * @since 10/05/2016
 */

public class ScoreIndicator implements Sprite {

    private Counter score;

    public ScoreIndicator(Counter score) {
        this.score = score;
    }

    /**
     * Draws the sprite on the screen.
     * <p/>
     * @param d the draw surface.
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.black);
        d.drawText(370, 15, this.toString(), 15);
    }

    /**
     * Notify the Sprite that time has passed.
     * <p/>
     */
    @Override
    public void timePassed() {

    }

    @Override
    public String toString() {
        return "Score: " + this.score.getValue();
    }

    /**
     * Adds the score counter to the game.
     * <p>
     * @param g the game.
     */
    public void addToGame(Game g) {
        g.addSprite(this);
    }
}
