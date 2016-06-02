package arkanoid.game;

import arkanoid.animation.Animation;
import arkanoid.animation.AnimationRunner;

/**
 * @author Roey Shefi & Oded Thaller
 * @version 1.0
 * @since 01/06/2016
 */

public class Tasks {

    /**
     * Implements and runs a High scores screnn animation task.
     * <p>
     * @param runner an animation runner
     * @param animation high scores animation
     * @return task
     */
    public Task showHighScores(final AnimationRunner runner, final Animation animation) {

        return new Task<Void>() {

            @Override
            public Void run() {
                runner.run(animation);
                return null;
            }
        };
    }

    /**
     * Implements and runs a task to start a new game.
     * <p>
     * @param g the game flow object
     * @return new game task
     */
    public Task runLevels(final GameFlow g) {

        return new Task<Void>() {

            @Override
            public Void run() {
                g.resetScoreAndLives();
                g.runLevels(g.getLevels());
                return null;
            }
        };
    }

    /**
     * Implements a task to quit the game.
     * <p>
     * @return quit task
     */
    public Task quit() {

        return new Task() {

            @Override
            public Void run() {
                System.exit(0);
                return null;
            }
        };
    }
}
