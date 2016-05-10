package arkanoid.game;

import arkanoid.listeners.BlockRemover;
import arkanoid.Counter;
import arkanoid.listeners.HitListener;
import arkanoid.geometry.Point;
import arkanoid.geometry.Rectangle;
import arkanoid.listeners.ScoreTrackingListener;
import arkanoid.sprites.*;
import biuoop.GUI;
import biuoop.Sleeper;
import biuoop.DrawSurface;

import java.awt.Color;

/**
 * @author Roey Shefi & Oded Thaller
 * @version 1.0
 * @since 4/4/2016
 */

public class Game {
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private GUI gui;
    private Counter blocks;
    private Counter score;

    /**
     * Instantiates a new game object.
     * <p>
     */
    public Game() {
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.blocks = new Counter();
        this.score = new Counter();
    }

    /**
     * Adds a Collidable object to the game.
     * <p>
     * @param a the Collidable to add to the game.
     */
    public void addCollidable(Collidable a) {
        this.environment.addCollidable(a);
    }

    /**
     * Adds a Sprite object to the game.
     * <p>
     * @param s the sprite to add.
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * Removes a collidable from the game.
     * <p>
     * @param c the collidable object to remove.
     */
    public void removeCollidable(Collidable c) {
        this.environment.getCollidables().remove(c);
    }

    /**
     * Removes a sprite from the game.
     * <p>
     * @param s the sprite to remove.
     */
    public void removeSprite(Sprite s) {
        this.sprites.getSprites().remove(s);
    }

    /**
     * Makes to Borders of the game.
     * <p>
     */
    public void makeBorders() {
        // upper border
        new Block(new Point(0, 20), 800, 10, 0, Color.gray).addToGame(this);
        // bottom border
        new Block(new Point(0, 590), 800, 10, 0, Color.gray).addToGame(this);
        // left border
        new Block(new Point(0, 20), 10, 600, 0, Color.gray).addToGame(this);
        // right border
        new Block(new Point(790, 20), 10, 600, 0, Color.gray).addToGame(this);
    }

    /**
     * Makes the blocks for the game.
     * <p>
     * @param x x value of the top left corner of the block.
     * @param y y value of the top left corner of the block.
     * @param numBlocks number of blocks per row.
     * @param hitPoints hit Points for blocks.
     * @param color block's color.
     */
    public void makeBlocks(double x, double y, int numBlocks, int hitPoints, Color color, HitListener hl,
                           ScoreTrackingListener stl) {
        for (int i = 0; i < numBlocks; i++) {
            Block b = new Block(new Point(x - (i * 50), y), 50, 20, hitPoints, color);
            b.addToGame(this);
            b.addHitListener(hl);
            b.addHitListener(stl);
            this.blocks.increase(1);
        }
    }

    /**
     * Makes the ball for the game.
     * <p>
     * @param x x value for ball's location.
     * @param y y value for ball's location.
     * @param size size of ball.
     * @param color ball's color.
     * @param environment1 the game environment.
     */
    public void makeBall(int x, int y, int size, Color color, GameEnvironment environment1) {
        Ball ball = new Ball(x, y, size, color);
        ball.addToGame(this);
        ball.setEnvironment(environment1);
        ball.setVelocity(-4, -4);
    }

    /**
     * Makes the paddle for the game.
     * <p>
     */
    public void makePaddle() {
        biuoop.KeyboardSensor key = this.gui.getKeyboardSensor();
        Paddle p = new Paddle(new Rectangle(new Point(170, 570), 50, 20), Color.RED, key, 10, 800, 5);
        p.addToGame(this);

    }

    /**
     * Makes the information bar at the top of the screen.
     * <p>
     * Displays lives, score and level name.
     */
    public void makeInfoBar() {
        ScoreIndicator si = new ScoreIndicator(this.score);
        si.addToGame(this);
    }

    /**
     * Initializes the game.
     * <p>
     */
    public void initialize() {
        Color[] colors = {Color.gray, Color.red, Color.yellow, Color.blue, Color.pink, Color.green};
        this.environment = new GameEnvironment();
        this.gui = new GUI("Game", 800, 600);
        BlockRemover br = new BlockRemover(this, this.blocks);
        makeBall(180, 350, 5, Color.BLACK, environment);
        makeBall(200, 350, 5, Color.BLACK, environment);
        new Block(new Point(0, 0), 800, 20, 0, Color.white).addToGame(this);
        ScoreTrackingListener stl = new ScoreTrackingListener(this.score);
        makeBorders();
        makeInfoBar();
        makeBlocks(340, 50, 7, 2, colors[0], br, stl);
        for (int i = 1; i < 6; i++) {
            makeBlocks(340, 50 + (i * 20), 7 - i, 1, colors[i], br, stl);
        }
        makePaddle();


    }

    /**
     * Runs the animation loop.
     * <p>
     */
    public void run() {
        int framesPerSecond = 60;
        int millisecondsPerFrame = 1000 / framesPerSecond;
        Sleeper sleeper = new Sleeper();
        while (true) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = this.gui.getDrawSurface();
            this.sprites.drawAllOn(d);
            this.gui.show(d);
            this.sprites.notifyAllTimePassed();
            // timing
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                sleeper.sleepFor(milliSecondLeftToSleep);
            }
            if (this.blocks.getValue() == 0) {
                this.gui.close();
                this.score.increase(100);
                return;
            }
        }
    }
}
