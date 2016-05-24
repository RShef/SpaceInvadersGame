package arkanoid.game;

import arkanoid.Counter;
import arkanoid.animation.Animation;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import java.awt.Color;

/**
 * @author Roey Shefi & Oded Thaller
 * @version 1.0
 * @since 15/5/2016
 */

public class EndScreen implements Animation {

    private KeyboardSensor keyboard;
    private Counter score;
    private boolean win;
    private boolean stop;

    /**
     * Inits a new End Screen.
     * <p>
     * @param k a key sensor.
     * @param score the current score.
     * @param win win or lose.
     */
    public EndScreen(KeyboardSensor k, Counter score, boolean win) {
        this.keyboard = k;
        this.score = score;
        this.win = win;
        this.stop = false;
    }

    /**
     * Does one frame.
     * <p/>
     *
     * @param d the draw surface.
     */
    public void doOneFrame(DrawSurface d) {
        if (this.win) {
            d.setColor(Color.green);
            d.fillRectangle(0, 0, 800, 600);
            d.setColor(Color.black);
            d.drawText(300, 200, "You Win!", 50);
        } else {
            d.setColor(Color.red);
            d.fillRectangle(0, 0, 800, 600);
            d.setColor(Color.black);
            d.drawText(250, 200, "Game Over...", 50);
        }
        d.drawText(200, 400, "Your score is: " + this.score.getValue(), 50);
        if (this.keyboard.isPressed(KeyboardSensor.SPACE_KEY)) {
            this.stop = true;
        }
    }

    /**
     * When to stop.
     * <p/>
     *
     * @return when to stop.
     */
    public boolean shouldStop() {
        return this.stop;
    }
}