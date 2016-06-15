package arkanoid.animation;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

/**
 * @author Roey Shefi & Oded Thaller
 * @version 1.0
 * @since 17/05/2016
 */
public class AnimationRunner {

    private GUI gui;
    private int framesPerSecond;
    private Sleeper sleeper;

    /**
     * Constructor for the animation runner.
     *
     * @param frames per second.
     * @param gui1   the gui.
     */
    public AnimationRunner(int frames, GUI gui1) {
        this.framesPerSecond = frames;
        this.sleeper = new Sleeper();
        this.gui = gui1;

    }

    /**
     * Runs the animation.
     *
     * @param animation the animation.
     */
    public void run(Animation animation) {
        // time for each frame.
        int millisecondsPerFrame = 1000 / this.framesPerSecond;
        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = this.gui.getDrawSurface();
            // Sending 1f because java is stupid.
            animation.doOneFrame(d, 1f / this.framesPerSecond);
            this.gui.show(d);
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                this.sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }

    }

    /**
     * See return.
     *
     * @return the gui.
     */
    public GUI getGui() {
        return this.gui;
    }
}

