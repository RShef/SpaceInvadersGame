package arkanoid.sprites;

import arkanoid.geometry.*;
import biuoop.DrawSurface;
import biuoop.Sleeper;

import java.awt.*;

/**
 * @author Roey Shefi & Oded Thaller
 * @version 1.0
 * @since 19/5/2016
 */
public class EndScreen implements Sprite {
    public EndScreen () {}

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.black);
        d.drawRectangle(0,0,600,800);
        d.drawText(150, 150, "Game Over :(", 100);

    }

    @Override
    public void timePassed() {

    }
}
