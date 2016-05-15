package arkanoid.game;

import arkanoid.Counter;
import arkanoid.sprites.SpriteCollection;
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

/**
 * Created by RoyShefi on 15/05/2016.
 */
public class AnimationRunner {
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private GUI gui;
    int framesPerSecond;
    Sleeper sleeper;


    public AnimationRunner(int frames, GUI gui1){
        this.framesPerSecond = frames;
        this.sleeper = new Sleeper();
        this.gui = gui1;

    }
    public void run (Animation animation) {
        int millisecondsPerFrame = 1000 / this.framesPerSecond;
            while (!animation.shouldStop()) {
                long startTime = System.currentTimeMillis(); // timing
                DrawSurface d = this.gui.getDrawSurface();

                animation.doOneFrame(d);

                this.gui.show(d);
                long usedTime = System.currentTimeMillis() - startTime;
                long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
                if (milliSecondLeftToSleep > 0) {
                    this.sleeper.sleepFor(milliSecondLeftToSleep);
                }
            }

        }
    }

