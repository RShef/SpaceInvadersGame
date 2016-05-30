package arkanoid.game;

import arkanoid.animation.Animation;
import arkanoid.animation.AnimationRunner;
import arkanoid.animation.CountdownAnimation;
import arkanoid.geometry.Velocity;
import arkanoid.levels.LevelInformation;
import arkanoid.listeners.BallRemover;
import arkanoid.listeners.BlockRemover;
import arkanoid.listeners.HitListener;
import arkanoid.geometry.Point;
import arkanoid.geometry.Rectangle;
import arkanoid.listeners.ScoreTrackingListener;
import arkanoid.sprites.Collidable;
import arkanoid.sprites.Paddle;
import arkanoid.sprites.Sprite;
import arkanoid.sprites.Block;
import arkanoid.sprites.Ball;
import arkanoid.sprites.ScoreIndicator;
import arkanoid.sprites.LivesIndicator;
import arkanoid.sprites.LevelNameIndicator;
import arkanoid.sprites.SpriteCollection;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import biuoop.DrawSurface;

import java.awt.Color;

/**
 * @author Roey Shefi & Oded Thaller
 * @version 1.0
 * @since 4/4/2016
 */

public class GameLevel implements Animation {
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private GUI gui;
    private Counter blocks;
    private Counter score;
    private Counter balls;
    private Counter lives;
    private boolean running;
    private Paddle p;
    private KeyboardSensor key;
    private LevelInformation l;
    private AnimationRunner runner;

    /**
     * Instantiates a new game level.
     *
     * @param l      the level information
     * @param runner animation runner
     * @param key    a key sensor
     * @param gui    GUI
     * @param lives  current lives
     * @param score  current score
     */
    public GameLevel(LevelInformation l, AnimationRunner runner, KeyboardSensor key, GUI gui,
                     Counter lives, Counter score) {
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.blocks = new Counter();
        this.score = score;
        this.balls = new Counter();
        this.lives = lives;
        this.running = false;
        this.l = l;
        this.runner = runner;
        this.key = key;
        this.gui = gui;
    }

    /**
     * Adds a Collidable object to the game.
     * <p>
     *
     * @param a the Collidable to add to the game.
     */
    public void addCollidable(Collidable a) {
        this.environment.addCollidable(a);
    }

    /**
     * Adds a Sprite object to the game.
     * <p>
     *
     * @param s the sprite to add.
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * Removes a collidable from the game.
     * <p>
     *
     * @param c the collidable object to remove.
     */
    public void removeCollidable(Collidable c) {
        this.environment.getCollidables().remove(c);
    }

    /**
     * Removes a sprite from the game.
     * <p>
     *
     * @param s the sprite to remove.
     */
    public void removeSprite(Sprite s) {
        this.sprites.getSprites().remove(s);
    }

    /**
     * Makes game's borders.
     *
     * @param hl a hit listener
     */
    public void makeBorders(HitListener hl) {
        // upper border
        new Block(new Point(0, 20), 800, 20, 0, Color.gray).addToGame(this);
        // bottom border - "Death Block"
        Block b = new Block(new Point(0, 600), 800, 20, 0, Color.white);
        // adding BallRemover as a listener of the bottom block.
        b.addToGame(this);
        b.addHitListener(hl);
        // left border
        new Block(new Point(0, 20), 20, 600, 0, Color.gray).addToGame(this);
        // right border
        new Block(new Point(780, 20), 20, 600, 0, Color.gray).addToGame(this);
    }

    /**
     * Makes the ball for the game.
     * <p>
     *
     * @param x            x value for ball's location.
     * @param y            y value for ball's location.
     * @param size         size of ball.
     * @param color        ball's color.
     * @param environment1 the game environment.
     * @param v            ball's velocity
     */
    public void makeBall(int x, int y, int size, Color color, GameEnvironment environment1, Velocity v) {
        Ball ball = new Ball(x, y, size, color);
        ball.setVelocity(v);
        ball.addToGame(this);
        this.balls.increase(1);
        ball.setEnvironment(environment1);
    }

    /**
     * Makes the paddle for the game.
     * <p>
     *
     * @param w paddle width
     * @param s paddle speed
     */
    public void makePaddle(int w, int s) {
        this.p = new Paddle(new Rectangle(new Point((800 - w) / 2, 560), w, 15), Color.YELLOW, key, w, s);
        p.addToGame(this);

    }

    /**
     * Makes the information bar at the top of the screen.
     * <p>
     * Displays lives, score and level name.
     */
    public void makeInfoBar() {
        // Add score to bar.
        ScoreIndicator si = new ScoreIndicator(this.score);
        si.addToGame(this);
        // Add lives left to bar.
        LivesIndicator li = new LivesIndicator(this.lives);
        li.addToGame(this);
        // Add level name to bar.
        LevelNameIndicator ln = new LevelNameIndicator(this.l.levelName());
        ln.addToGame(this);
    }

    /**
     * Initializes the game.
     * <p>
     */
    public void initialize() {
        // Adding the listeners.
        BlockRemover br = new BlockRemover(this, this.blocks);
        BallRemover bar = new BallRemover(this, this.balls);

        this.addSprite(this.l.getBackground());
        // Creating the game field.
        new Block(new Point(0, 0), 800, 20, 0, Color.white).addToGame(this);
        ScoreTrackingListener stl = new ScoreTrackingListener(this.score);
        makeBorders(bar);
        makeInfoBar();

        // adding the listeners.
        for (Block b : this.l.blocks()) {
            b.addToGame(this);
            b.addHitListener(br);
            b.addHitListener(stl);
            this.blocks.increase(1);
        }

    }

    /**
     * Creates the balls and the paddle.
     * <p>
     */
    public void createBallsAndPaddle() {
        for (Velocity v : this.l.initialBallVelocities()) {
            makeBall(400, 550, 5, Color.white, this.environment, v);
        }
        makePaddle(this.l.paddleWidth(), this.l.paddleSpeed());
    }

    /**
     * Runs one turn.
     * <p>
     */
    public void playOneTurn() {
        this.createBallsAndPaddle();
        this.runner.run(new CountdownAnimation(2000, 3, this.sprites));
        this.running = true;
        this.runner.run(this);
    }

    /**
     * @return if the loop is running.
     */
    public boolean shouldStop() {
        return !this.running;
    }

    /**
     * Showing only one frame.
     * <p>
     *
     * @param d  the draw surface.
     * @param dt is the time passed from previous frame.
     */
    public void doOneFrame(DrawSurface d, double dt) {
        this.sprites.drawOn(d);
        this.sprites.notifyAllTimePassed(dt);

        biuoop.KeyboardSensor k = this.gui.getKeyboardSensor();
        if (k.isPressed("p")) {
            this.runner.run(new PauseScreen(k));
        }
        // timing
        if (this.blocks.getValue() == 0) {
            this.score.increase(100);
            this.running = false;
        }
        // If there are no balls, decrease a life from the player.
        if (this.balls.getValue() == 0) {
            this.p.removeFromGame(this);
            this.lives.decrease(1);
            this.running = false;
        }

    }

    /**
     * returns the lives.
     * <p>
     *
     * @return Lives.
     */
    public int livesLeft() {
        return this.lives.getValue();
    }

    /**
     * Returns blocks left.
     * <p>
     *
     * @return blocks left.
     */
    public int blocksLeft() {
        return this.blocks.getValue();
    }

}
