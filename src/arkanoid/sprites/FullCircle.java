package arkanoid.sprites;

import biuoop.DrawSurface;

import java.awt.Color;

/**
 * @author Roey Shefi & Oded Thaller
 * @version 1.0
 * @since 19/5/2016
 **/

public class FullCircle implements Sprite {
    private int r;
    private int x;
    private int y;
    private Color c;

    /**
     * The costructur.
     *
     * @param x the x.
     * @param y the y.
     * @param r the radios.
     * @param c the color.
     */
    public FullCircle(int x, int y, int r, Color c) {
        this.x = x;
        this.y = y;
        this.r = r;
        this.c = c;

    }

    @Override
    /**
     * The draw on class.
     */
    public void drawOn(DrawSurface d) {
        d.setColor(this.c);
        d.drawCircle(this.x, this.y, this.r);
        d.fillCircle(this.x, this.y, this.r);
        d.setColor(Color.black);
    }

    @Override
    /**
     * The time passed class.
     */
    public void timePassed() {
    }
}