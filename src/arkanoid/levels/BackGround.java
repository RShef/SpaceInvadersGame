package arkanoid.levels;

import arkanoid.geometry.Point;
import arkanoid.geometry.Velocity;
import arkanoid.sprites.Block;
import arkanoid.sprites.Sprite;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

/**
 * @author Roey Shefi & Oded Thaller
 * @version 1.0
 * @since 05/06/2016
 */


public class BackGround implements LevelInformation {
    private int numOfBalls;
    private int paddleWidth;
    private int paddleSpeed;
    private int numberOfBlocksToRemove;
    private String ballVelocities;
    private int blocksStartX;
    private int blocksStartY;
    private int rowHeight;
    private String back;
    private List<Block> blockList;
    private String levelName;

    /**
     * The constractor.
     *
     * @param m is the tree map of the back ground.
     */
    public BackGround(TreeMap m) {
        this.paddleWidth = Integer.parseInt((String) m.get("paddle_width"));
        this.paddleSpeed = Integer.parseInt((String) m.get("paddle_speed"));
        this.numOfBalls = 1;
        this.levelName = (String) m.get("level_name");
        this.numberOfBlocksToRemove = Integer.parseInt((String) m.get("num_blocks"));
        this.ballVelocities = (String) m.get("ball_velocities");
        this.rowHeight = Integer.parseInt((String) m.get("row_height"));
        this.blocksStartX = Integer.parseInt((String) m.get("blocks_start_x"));
        this.blocksStartY = Integer.parseInt((String) m.get("blocks_start_y"));
        this.back = (String) m.get("background");
        this.initialBallVelocities();
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
     * @return a List of Velocities for the balls on level.
     */
    public List<Velocity> initialBallVelocities() {
        List<Velocity> v = new ArrayList<>();
        String[] t = this.ballVelocities.split(" ");
        String[] y;
        // Returning multiple velocity.
        for (int i = 0; i < t.length; i++) {
            y = t[i].split(",");
            v.add(Velocity.fromAngleAndSpeed(Integer.parseInt(y[0]), Integer.parseInt(y[1])));
        }
        this.numOfBalls = v.size();
        return v;
    }

    /**
     * See return.
     * <p>
     *
     * @return Paddle speed.
     */
    public int paddleSpeed() {
        return this.paddleSpeed;
    }

    /**
     * See return.
     * <p>
     *
     * @return Paddle width.
     */
    public int paddleWidth() {
        return this.paddleWidth;
    }

    /**
     * Returns the current level name.
     * <p>
     *
     * @return the level name.
     */
    public String levelName() {
        return this.levelName;
    }

    /**
     * Returns the back block.
     * <p>
     *
     * @return the background for level.
     */
    @Override
    public Sprite getBackground() {
        return new Block(new Point(0, 0), 800, 600, 0, this.back);
    }

    /**
     * Returns a cloned list of blocks.
     *
     * @return Returns a cloned list of blocks.
     */
    public List<Block> blocks() {
        List<Block> blocks = new ArrayList<>();
        for (Block bs : this.blockList) {
            blocks.add(bs.clone());
        }
        return blocks;
    }

    /**
     * Sets the block list.
     *
     * @param bl - the block list.
     */
    public void setBlocks(List<Block> bl) {
        this.blockList = bl;
    }

// to change.

    /**
     * See return.
     * <p>
     *
     * @return number Of Blocks To Remove.
     */
    public int numberOfBlocksToRemove() {
        return 1;
    }

    /**
     * See return.
     * <p>
     *
     * @return the first blocks X value on the row.
     */
    public int getStartingX() {
        return this.blocksStartX;
    }

    /**
     * See return.
     * <p>
     *
     * @return he first blocks Y value on the row.
     */
    public int getStartingY() {
        return this.blocksStartY;
    }

    /**
     * See return.
     *
     * @return the row height.
     */
    public int getRowHeight() {
        return this.rowHeight;
    }

}
