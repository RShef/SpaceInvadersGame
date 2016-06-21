package spaceinvaders.animation;

import spaceinvaders.game.Counter;
import spaceinvaders.sprites.SpriteCollection;
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
     * Instantiates a new CountDown animation.
     *
     * @param numOfSeconds number of seconds to animate
     * @param countFrom    number to start counting from
     * @param gameScreen   game screen to appear on
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
     * @param d  is the draw surface.
     * @param dt is the time passed from previous frame.
     */
    public void doOneFrame(DrawSurface d, double dt) {
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