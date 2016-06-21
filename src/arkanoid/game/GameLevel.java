package arkanoid.game;

import arkanoid.animation.CountdownAnimation;
import arkanoid.animation.PauseScreen;
import arkanoid.animation.Animation;
import arkanoid.animation.AnimationRunner;
import arkanoid.animation.KeyPressStoppableAnimation;
import arkanoid.invaders.Swarm;
import arkanoid.levels.LevelInformation;
import arkanoid.listeners.*;
import arkanoid.geometry.Point;
import arkanoid.geometry.Rectangle;
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
import java.util.Random;

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
    private Swarm swarm;
    private Counter aliens;
    private LifeRemover lifeRemover;
    private Long time;
    private Long time2;

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
        this.aliens = new Counter();
        this.lifeRemover = new LifeRemover(this);
        this.time = System.currentTimeMillis();
        this.time2 = System.currentTimeMillis();
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
     * Makes the paddle for the game.
     * <p>
     *
     * @param w paddle width
     * @param s paddle speed
     */
    public void makePaddle(int w, int s) {
        this.p = new Paddle(new Rectangle(new Point((800 - w) / 2, 560), w, 15), Color.YELLOW, key, w, s);
        p.addToGame(this);
        p.addHitListener(this.lifeRemover);
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
     **/
    public void initialize(double swarmSpeed) {
        // Adding the listeners.
        AlienRemover ar = new AlienRemover(this, this.aliens);
        BlockRemover br = new BlockRemover(this, this.blocks);
        BallRemover bar = new BallRemover(this, this.balls);
        ScoreTrackingListener stl = new ScoreTrackingListener(this.score);

        addSprite(this.l.getBackground());
        // Creating the game field.
        Block infoBar = new Block(new Point(0, 0), 800, 20, 0, Color.white);
        infoBar.addToGame(this);
        infoBar.addHitListener(bar);
        makeInfoBar();
        this.swarm = new Swarm(50, 50, 10, 5, swarmSpeed);
        this.swarm.addToGame(this);
        this.swarm.addHitListeners(ar, stl, bar);
        this.aliens.increase(50);
        // adding the listeners.
        for (Block b : this.l.blocks()) {
            b.addToGame(this);
            b.addHitListener(br);
            b.addHitListener(bar);
            this.blocks.increase(1);
        }

    }

    /**
     * Creates the balls and the paddle.
     * <p>
     */
    public void createBallsAndPaddle() {
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
        this.swarm.timePassed(dt);
        Long currTime = System.currentTimeMillis();

        // if "p" is pressed, show pause screen
        KeyboardSensor k = this.gui.getKeyboardSensor();
        if (k.isPressed("p")) {
            PauseScreen ps = new PauseScreen(this.score, this.lives, this.l.levelName());
            this.runner.run(new KeyPressStoppableAnimation(this.key, "space", ps));
        }

        // player's shooter
        long x = currTime - this.time;
        if (k.isPressed(KeyboardSensor.SPACE_KEY) && x > 350 ) {
            Ball b = this.p.shoot();
            b.setVelocity(1,-500);
            b.addToGame(this);
            b.setEnvironment(this.environment);
            this.time = currTime;
        }

        // Aliens shot
        long y = currTime - this.time2;
        if (y > 550) {
            Random rand = new Random();
            int randCol = rand.nextInt(10);
            if (this.swarm.getLowestInCol(randCol) != null) {
                Ball badBall = this.swarm.getLowestInCol(randCol).shoot();
                badBall.setVelocity(-1,500);
                badBall.addToGame(this);
                badBall.setEnvironment(this.environment);
                this.time2 = currTime;
            }
        }

        // if all aliens are dead
        if (aliensLeft() == 0) {
            this.score.increase(500);
            this.running = false;
            return;
        }

        // If swarm reaches shields or player is hit, decrease a life from the player.
        if (this.swarm.getLowest().getCollisionRectangle().getLowRight().getY() >= 480 || this.lifeRemover.isHit()) {
            this.p.removeFromGame(this);
            this.lifeRemover.setHit();
            this.swarm.resetPosAndSpeed();
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
     * Returns aliens left.
     * <p>
     *
     * @return aliens left.
     */
    public int aliensLeft() {
        return this.aliens.getValue();
    }

}
