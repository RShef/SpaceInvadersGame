package arkanoid.levels;

import arkanoid.game.LevelInformation;
import arkanoid.geometry.Point;
import arkanoid.geometry.Velocity;
import arkanoid.sprites.*;
import arkanoid.geometry.Line;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Roey Shefi & Oded Thaller
 * @version 1.0
 * @since 17/05/2016
 */
public class Level1 implements LevelInformation {
    private int numOfBalls;

    public Level1() {
        this.numOfBalls = 1;
    }

    /**
     * Returns the number of balls for the level.
     * <p>
     *
     * @return number of balls for the level.
     */
    public int numberOfBalls() {
        return this.numOfBalls;
    }

    /**
     * See return.
     *
     * @return a List of Velocities for the balls on level1.
     */
    public List<Velocity> initialBallVelocities() {
        List<Velocity> v = new ArrayList<>();
        v.add(new Velocity(5, 5));
        return v;
    }

    /**
     * See return.
     * <p>
     *
     * @return Paddle speed.
     */
    public int paddleSpeed() {
        return 7;
    }

    /**
     * See return.
     * <p>
     *
     * @return Paddle width.
     */
    public int paddleWidth() {
        return 75;
    }

    /**
     * Returns the current level name.
     * <p>
     *
     * @return the level name.
     */
    public String levelName() {
        return "Direct Hit";
    }

    /**
     * Creates a SpriteCollection of all the sprites of the level, then creates "Back" class.
     * <p>
     *
     * @return the background for level 1.
     */
    public Sprite getBackground() {
        SpriteCollection s = new SpriteCollection();
        s.addSprite(new Block(new Point(0, 0), 800, 600, 0, Color.BLACK));
        s.addSprite(new Circle(400, 150, 50, Color.blue));
        s.addSprite(new Circle(400, 150, 75, Color.blue));
        s.addSprite(new Circle(400, 150, 100, Color.blue));
        s.addSprite(new Line(400, 0, 400, 260, Color.blue));
        s.addSprite(new Line(290, 150, 510, 150, Color.blue));
        return new Back(s);
    }

    /**
     * Creates the Blocks of level1.
     * <p>
     *
     * @return the list of starting block of level1.
     */
    public List<Block> blocks() {
        List<Block> b = new ArrayList<>();
        b.add(new Block(new Point(385, 135), 30, 30, 1, Color.red));
        return b;
    }

    /**
     * See return.
     * <p>
     *
     * @return number Of Blocks To Remove.
     */
    public int numberOfBlocksToRemove() {
        return 1;
    }
}
