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
public class Level3 implements LevelInformation {

    private int numOfBalls;

    /**
     * Instantiates a new level 3.
     * <p>
     */
    public Level3() {
        this.numOfBalls = 2;
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
     * @return a List of Velocities for the balls on level3.
     */
    public List<Velocity> initialBallVelocities() {
        List<Velocity> v = new ArrayList<>();
        double x = 2;
        double y = -6;
        for (int i = 0; i < this.numOfBalls / 2; i++) {
            v.add(new Velocity(x + i, y + i));
            v.add(new Velocity((x + i) * -1, y + i));
        }
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
        return "Green 3";
    }

    /**
     * Creates a SpriteCollection of all the sprites of the level, then creates "Back" class.
     * <p>
     *
     * @return the background for level 3.
     */
    public Sprite getBackground() {
        SpriteCollection s = new SpriteCollection();
        s.addSprite(new Block(new Point(0, 0), 800, 600, 0, new Color(0, 153, 0)));
        s.addSprite(new Block(new Point(70, 400), 90, 200, 0, new Color(32, 32, 32)));

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 5; j++) {
                s.addSprite(new Block(new Point(80 + (15 * j), 420 + (25 * i)), 10, 20, 0, Color.white));
            }
        }

        s.addSprite(new Block(new Point(100, 350), 30, 50, 0, new Color(34, 34, 34)));
        s.addSprite(new Block(new Point(110, 150), 10, 200, 0, new Color(60, 60, 60)));
        s.addSprite(new FullCircle(115, 145, 10, Color.orange));
        s.addSprite(new FullCircle(115, 145, 5, Color.red));
        s.addSprite(new FullCircle(115, 145, 3, Color.white));

        return new Back(s);
    }

    /**
     * Creates the Blocks of level1.
     * <p>
     *
     * @return the list of starting block of level3.
     */
    public List<Block> blocks() {
        Color[] color = {Color.gray, Color.red, Color.YELLOW, Color.blue, Color.white};
        List<Block> b = new ArrayList<>();
        int j = 0;
        for (int k = 0; k < 5; k++) {
            int flag = 10 - k;
            int r = 0;
            for (int i = 0; i < flag; i++) {
                int hit = 1;
                b.add(new Block(new Point(737 - (r * 42.2), 200 + (k * 20)), 42.2, 20, hit, color[j]));
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
        return 40;
    }
}
