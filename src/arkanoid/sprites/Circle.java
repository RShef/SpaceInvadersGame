package arkanoid.sprites;

import biuoop.DrawSurface;

import java.awt.Color;

/**
 * @author Roey Shefi & Oded Thaller
 * @version 1.0
 * @since 19/5/2016
 */

public class Circle implements Sprite {
    private int r;
    private int x;
    private int y;
    private Color c;

    /**
     * The constactor.
     *
     * @param x int point.
     * @param y int point.
     * @param r int point.
     * @param c int point.
     */
    public Circle(int x, int y, int r, Color c) {
        this.x = x;
        this.y = y;
        this.r = r;
        this.c = c;

    }

    /**
     * The draw on.
     *
     * @param d the draw surface.
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(this.c);
        d.drawCircle(this.x, this.y, this.r);
        d.setColor(Color.black);
    }

    /**
     * For check style.
     */
    @Override
    public void timePassed() {
    }
}
