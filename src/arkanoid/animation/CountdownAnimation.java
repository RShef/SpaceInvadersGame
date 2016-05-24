package arkanoid.animation;

import arkanoid.Counter;
import arkanoid.sprites.SpriteCollection;
import biuoop.Sleeper;
import biuoop.DrawSurface;
import java.awt.Color;

/**
 * @author Roey Shefi & Oded Thaller
 * @version 1.0
 * @since 15/5/2016
 */
public class CountdownAnimation implements Animation {

    private double numOfSeconds;
    private Counter countFrom;
    private SpriteCollection gameScreen;
    private double nu;

    /**
     * Initializes a countDown object.
     * <p>
     * @param numOfSeconds seconds to animate.
     * @param countFrom starting number.
     * @param gameScreen screen in background.
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen) {
        this.numOfSeconds = numOfSeconds;
        this.countFrom = new Counter();
        this.countFrom.increase(countFrom);
        this.gameScreen = gameScreen;
        this.nu = countFrom;
    }

    /**
     * Displays a num from to countdown.
     *
     * @param d is the draw surface.
     */
    public void doOneFrame(DrawSurface d) {
        this.gameScreen.drawOn(d);
        //this.gameScreen.notifyAllTimePassed();
        d.setColor(Color.YELLOW);
        d.drawText(370, 300, String.valueOf(this.countFrom.getValue()), 100);
        this.countFrom.decrease(1);
        Sleeper sl = new Sleeper();
        sl.sleepFor((long) numOfSeconds / (long) this.nu);

    }

    /**
     * When to stop.
     *
     * @return if it reached zero.
     */
    public boolean shouldStop() {
        return this.countFrom.getValue() == -1;
    }
}