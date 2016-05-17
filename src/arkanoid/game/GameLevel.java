package arkanoid.game;

import arkanoid.geometry.Velocity;
import arkanoid.listeners.BallRemover;
import arkanoid.listeners.BlockRemover;
import arkanoid.Counter;
import arkanoid.listeners.HitListener;
import arkanoid.geometry.Point;
import arkanoid.geometry.Rectangle;
import arkanoid.listeners.ScoreTrackingListener;
import arkanoid.sprites.*;
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
    private AnimationRunner runner;
    private boolean running;
    private Paddle p;
    private KeyboardSensor key;
    private LevelInformation l;


    /**
     * Instantiates a new game object.
     * <p>
     */
    public GameLevel(LevelInformation l) {
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.blocks = new Counter();
        this.score = new Counter();
        this.balls = new Counter();
        this.lives = new Counter();
        this.running = false;
        this.p = new Paddle();
        this.l = l;

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
    public void makeBorders(HitListener hl) {
        // upper border
        new Block(new Point(0, 20), 800, 20, 0, Color.gray).addToGame(this);
        // bottom border
        Block b = new Block(new Point(0, 600), 800, 20, 0, Color.white);
        // adding BallRemover to be a listener of the bottom block.
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
     * @param x x value for ball's location.
     * @param y y value for ball's location.
     * @param size size of ball.
     * @param color ball's color.
     * @param environment1 the game environment.
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
     */
    public void makePaddle(int w, int s) {
        biuoop.KeyboardSensor key = this.gui.getKeyboardSensor();
        this.p = new Paddle(new Rectangle(new Point((800 - w)/2, 560), w, 15), Color.YELLOW, key, w, s);
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
        this.environment = new GameEnvironment();
        this.gui = new GUI("Arkanoid", 800, 600);
        this.runner = new AnimationRunner(60,this.gui);

        // Adding the listeners.
        BlockRemover br = new BlockRemover(this, this.blocks);
        BallRemover bar = new BallRemover(this, this.balls);

        this.addSprite(this.l.getBackground());
        // Creating the game field.
        new Block(new Point(0, 0), 800, 20, 0, Color.white).addToGame(this);
        ScoreTrackingListener stl = new ScoreTrackingListener(this.score);
        makeBorders(bar);
        makeInfoBar();

        for (Block b : this.l.blocks()) {
            b.addToGame(this);
            b.addHitListener(br);
            b.addHitListener(stl);
            this.blocks.increase(1);

        }

        this.lives.increase(7);
    }

    /**
     * Creates the balls and the paddle.
     */
    public void createBallsAndPaddle () {
        for (Velocity v : this.l.initialBallVelocities()) {
            makeBall(180, 350, 5, Color.black, this.environment,v);
        }
        makePaddle(this.l.paddleWidth(),this.l.paddleSpeed());
    }

    /**
     * Runs one turn.
     * <p>
     */
    public void playOneTurn() {

        this.createBallsAndPaddle();
        this.runner.run(new CountdownAnimation(2000,3,this.sprites));
        this.running = true;
        this.runner.run(this);

    }

    /**
     * Runs the loop.
     */
    public void run () {
        while (this.lives.getValue() != -1) {
            playOneTurn();
            this.lives.decrease(1);
        }
        this.gui.close();
    }

    /**
     *
     * @return if the loop is running.
     */
    public boolean shouldStop() { return !this.running; }

    /**
     * Shoing only one frame.
     * @param d
     */
    public void doOneFrame (DrawSurface d) {
        this.sprites.drawOn(d);
        this.sprites.notifyAllTimePassed();

        biuoop.KeyboardSensor key = this.gui.getKeyboardSensor();
        if (key.isPressed("p")) {
            this.runner.run(new PauseScreen(key));
        }
        // timing
        if (this.blocks.getValue() == 0) {
            this.gui.close();
            this.score.increase(100);
            this.running = false;
        }
        if (this.balls.getValue() == 0) {
            this.p.removeFromGame(this);
            this.running = false;
        }
    }

}
