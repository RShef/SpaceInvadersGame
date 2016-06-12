package arkanoid.animation;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * @author Roey Shefi & Oded Thaller
 * @version 1.0
 * @since 30/05/2016
 */

public class KeyPressStoppableAnimation implements Animation {

    private KeyboardSensor sensor;
    private String key;
    private Animation animation;
    private boolean stop;
    private boolean isAlreadyPressed;

    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        this.sensor = sensor;
        this.key = key;
        this.animation = animation;
        this.stop = false;
        this.isAlreadyPressed = true;
    }

    /**
     * Displays a num from to countdown.
     * <p/>
     *
     * @param d is the draw surface.
     */
    @Override
    public void doOneFrame(DrawSurface d, double dt) {
        this.animation.doOneFrame(d, dt);
        if (this.sensor.isPressed(key)) {
            if (!this.isAlreadyPressed) {
                this.stop = true;
            } else {
                this.isAlreadyPressed = false;
            }
        }
    }

    /**
     * When to stop.
     * <p/>
     *
     * @return if it reached zero.
     */
    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
