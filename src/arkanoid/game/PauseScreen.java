package arkanoid.game;

import biuoop.KeyboardSensor;
import biuoop.DrawSurface;
/**
 * @author Roey Shefi & Oded Thaller
 * @version 1.0
 * @since 15/5/2016
 */

public class PauseScreen implements Animation {
    private KeyboardSensor keyboard;
    private boolean stop;
    public PauseScreen(KeyboardSensor k) {
        this.keyboard = k;
        this.stop = false;
    }

    /**
     * Does on frame.
     * @param d the draw surface.
     */
    public void doOneFrame(DrawSurface d) {
        d.drawText(10, d.getHeight() / 2, "paused -- press space to continue", 32);
        if (this.keyboard.isPressed(KeyboardSensor.SPACE_KEY)) { this.stop = true; }
    }

    /**
     * When to stop.
     * @return when to stop.
     */
    public boolean shouldStop() { return this.stop; }
}