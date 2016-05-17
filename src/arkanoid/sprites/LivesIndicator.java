package arkanoid.sprites;

import arkanoid.Counter;
import arkanoid.game.GameLevel;
import biuoop.DrawSurface;

import java.awt.*;

/**
 * @author Roey Shefi & Oded Thaller
 * @version 1.0
 * @since 15/5/2016
 */
public class LivesIndicator implements Sprite {
    private Counter lives;
    public LivesIndicator (Counter lives) { this.lives = lives;}

    @Override
    public void drawOn ( DrawSurface d) {
    d.setColor(Color.black);
    d.drawText(150, 15, this.toString(), 15);
}
    @Override
    public void timePassed() {

    }

    @Override
    public String toString() {
        return "Lives: " + this.lives.getValue();
    }

    /**
     * Adds the lives counter to the game.
     * <p>
     * @param g the game.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}

