package arkanoid.levels;

import arkanoid.geometry.Point;
import arkanoid.geometry.Velocity;
import arkanoid.sprites.Sprite;
import arkanoid.sprites.SpriteCollection;
import arkanoid.sprites.Block;
import arkanoid.sprites.FullCircle;
import arkanoid.sprites.Back;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Roey Shefi & Oded Thaller
 * @version 1.0
 * @since 17/05/2016
 */
public class Level4 implements LevelInformation {

    private int numOfBalls;

    /**
     * The constructor.
     */
    public Level4() {
        this.numOfBalls = 3;
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
     * <p>
     *
     * @return a List of Velocities for the balls on level4.
     */
    public List<Velocity> initialBallVelocities() {
        List<Velocity> v = new ArrayList<>();
        v.add(new Velocity(10, -50));
        v.add(new Velocity(-50, -30));
        v.add(new Velocity(50, -30));

        return v;
    }

    /**
     * See return.
     * <p>
     *
     * @return Paddle speed.
     */
    public int paddleSpeed() {
        return 200;
    }

    /**
     * See return.
     * <p>
     *
     * @return Paddle width.
     */
    public int paddleWidth() {
        return 100;
    }

    /**
     * Returns the current level name.
     * <p>
     *
     * @return the level name.
     */
    public String levelName() {
        return "Final Four";
    }

    /**
     * Creates a SpriteCollection of all the sprites of the level, then creates "Back" class.
     * <p>
     *
     * @return the background for level 3.
     */
    public Sprite getBackground() {
        SpriteCollection s = new SpriteCollection();
        s.addSprite(new Block(new Point(0, 0), 800, 600, 0, new Color(0, 102, 204)));
        s.addSprite(new FullCircle(200, 400, 30, Color.gray));
        s.addSprite(new FullCircle(170, 390, 30, new Color(160, 160, 160)));
        s.addSprite(new FullCircle(140, 405, 25, new Color(192, 192, 192)));
        s.addSprite(new FullCircle(160, 425, 30, new Color(192, 192, 192)));
        s.addSprite(new FullCircle(200, 440, 25, Color.gray));

        s.addSprite(new FullCircle(700, 450, 30, Color.gray));
        s.addSprite(new FullCircle(670, 440, 25, new Color(160, 160, 160)));
        s.addSprite(new FullCircle(640, 455, 25, new Color(192, 192, 192)));
        s.addSprite(new FullCircle(660, 475, 30, new Color(192, 192, 192)));
        s.addSprite(new FullCircle(700, 490, 20, Color.gray));

        return new Back(s);
    }

    /**
     * Creates the Blocks of level1.
     * <p>
     *
     * @return the list of starting block of level4.
     */
    public List<Block> blocks() {
        Color[] color = {Color.gray, Color.red, Color.YELLOW, Color.green, Color.white, Color.PINK, Color.CYAN};
        List<Block> b = new ArrayList<>();
        int j = 0;
        for (int k = 0; k < 7; k++) {
            int r = 0;
            for (int i = 0; i < 15; i++) {
                int hit = 1;
                if (j == 0) {
                    hit = 2;
                }
                b.add(new Block(new Point(20 + (r * 50.75), 100 + (k * 20)), 50.75, 20, hit, color[j]));
                r++;
            }
            j++;
        }
        return b;
    }

    /**
     * See return.
     * <p>
     *
     * @return number Of Blocks To Remove.
     */
    public int numberOfBlocksToRemove() {
        return 105;
    }
}
