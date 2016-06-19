package arkanoid.levels;

import arkanoid.geometry.Velocity;

import java.util.List;

import arkanoid.invaders.Swarm;
import arkanoid.sprites.Sprite;
import arkanoid.sprites.Block;

/**
 * @author Roey Shefi & Oded Thaller
 * @version 1.0
 * @since 17/5/2016
 */

public interface LevelInformation {

    /**
     * Returns the number of balls for the level.
     * <p>
     *
     * @return number of balls for the level.
     */
    int numberOfBalls();

    /**
     * See return.
     * <p>
     *
     * @return a List of Velocities for the balls on level1.
     */
    List<Velocity> initialBallVelocities();

    /**
     * See return.
     * <p>
     *
     * @return Paddle speed.
     */
    int paddleSpeed();

    /**
     * See return.
     * <p>
     *
     * @return Paddle width.
     */
    int paddleWidth();

    /**
     * Returns the current level name.
     * <p>
     *
     * @return the level name.
     */
    String levelName();

    /**
     * Creates a SpriteCollection of all the sprites of the level, then creates "Back" class.
     * <p>
     *
     * @return the background for level 1.
     */
    Sprite getBackground();


    /**
     * Creates the Blocks of level1.
     * <p>
     *
     * @return the list of starting block of level1.
     */
    List<Block> blocks();

    /**
     * See return.
     * <p>
     *
     * @return number Of Blocks To Remove.
     */
    int numberOfBlocksToRemove();

    Swarm getSwerm ();
}

