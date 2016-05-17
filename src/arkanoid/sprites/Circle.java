package arkanoid.sprites;

import biuoop.DrawSurface;
import arkanoid.game.*;
import arkanoid.geometry.*;
import biuoop.DrawSurface;
import java.awt.Color;
/**
 * Created by RoyShefi on 17/05/2016.
 *
 */
public class Circle implements Sprite {
    private int r;
    private int x;
    private int y;
    private Color c;

    public Circle (int x, int y, int r, Color c) {
        this.x = x;
        this.y = y;
        this.r = r;
        this.c = c;

    }
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(this.c);
        d.drawCircle(this.x,this.y,this.r);
        d.setColor(Color.black);
    }

    @Override
    public void timePassed() {
    }
}
