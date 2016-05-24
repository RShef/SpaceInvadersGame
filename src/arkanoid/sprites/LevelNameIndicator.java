package arkanoid.sprites;

import arkanoid.game.GameLevel;
import biuoop.DrawSurface;

import java.awt.Color;

/**
 * @author Roey Shefi & Oded Thaller
 * @version 1.0
 * @since 17/05/2016
 */

public class LevelNameIndicator implements Sprite {

    private String levelName;

    /**
     * The constructor.
     *
     * @param levelName of the current level.
     */
    public LevelNameIndicator(String levelName) {
        this.levelName = levelName;
    }

    /**
     * Draws the sprite on the screen.
     * <p/>
     *
     * @param d the draw surface.
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.black);
        d.drawText(550, 15, this.toString(), 15);
    }

    /**
     * Returns the level name string.
     *
     * @return the string to be display.
     */
    @Override
    public String toString() {
        return "Level Name: " + this.levelName;
    }

    /**
     * Notify the Sprite that time has passed.
     * <p/>
     */
    @Override
    public void timePassed() {

    }

    /**
     * Adds the score counter to the game.
     * <p/>
     *
     * @param g the game.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}
