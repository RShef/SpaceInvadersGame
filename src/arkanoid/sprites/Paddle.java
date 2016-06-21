package arkanoid.sprites;

import arkanoid.game.GameLevel;
import arkanoid.geometry.Velocity;
import arkanoid.geometry.Point;
import arkanoid.geometry.Rectangle;
import arkanoid.listeners.HitListener;
import arkanoid.listeners.HitNotifier;
import biuoop.DrawSurface;

import java.awt.Color;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import biuoop.KeyboardSensor;

/**
 * @author Roey Shefi & Oded Thaller
 * @version 1.0
 * @since 4/4/2016
 */
public class Paddle implements Sprite, Collidable, HitNotifier {

    private Color color;
    private Rectangle rectangle;
    private KeyboardSensor keyboard;
    private int width;
    private int paddleSpeed;
    private boolean shoot = true;
    private java.util.List<HitListener> hitListeners;


    /**
     * The constructor.
     * <p/>
     * All param are given by the Game class.
     *
     * @param rec         - the rectangle.
     * @param color       - the color.
     * @param k           - the  keyboard.
     * @param width       - the width of the border.
     * @param paddleSpeed - the paddles speed.
     */
    public Paddle(Rectangle rec, Color color, KeyboardSensor k, int width, int paddleSpeed) {
        this.rectangle = rec;
        this.keyboard = k;
        this.paddleSpeed = paddleSpeed;
        this.width = width;
        this.color = color;
        this.hitListeners = new LinkedList<>();
    }

    /**
     * Moving the paddle right.
     *
     * @param dt is the time passed from previous frame.
     *           <p/>
     */
    public void moveRight(double dt) {
        // Get and Set the new rec based on the paddle speed.
        Rectangle rec = this.getCollisionRectangle();
        //Checking to see if the paddle had reached the border.
        if (rec.getUpperLeft().getX() + dt * this.paddleSpeed + rec.getWidth() > 780) {
            this.rectangle = new Rectangle(new Point(780 - this.width, rec.getUpperLeft().getY()),
                    rec.getWidth(), rec.getHeight());
        } else {
            // Set A new rectangle for the Paddle right from the previous paddle.
            this.rectangle = new Rectangle(new Point(rec.getUpperLeft().getX() + dt * (this.paddleSpeed),
                    rec.getUpperLeft().getY()), rec.getWidth(), rec.getHeight());
        }
    }

    /**
     * Moving the paddle left.
     *
     * @param dt is the time passed from previous frame.
     *           <p/>
     */
    public void moveLeft(double dt) {
        // Get and Set the new rec based on the paddle speed.
        Rectangle rec = this.getCollisionRectangle();
        //Checking to see if the paddle had reached the border.
        if (rec.getUpperLeft().getX() - dt * (this.paddleSpeed) < 20) {
            this.rectangle = new Rectangle(new Point(20, rec.getUpperLeft().getY()), rec.getWidth(),
                    rec.getHeight());
        } else { // Set A new rectangle for the Paddle left from the previous paddle.
            this.rectangle = new Rectangle(new Point(rec.getUpperLeft().getX() - (dt * (this.paddleSpeed)),
                    rec.getUpperLeft().getY()), rec.getWidth(), rec.getHeight());
        }
    }

    /**
     * Receive to velocity after collision.
     * <p/>
     *
     * @param collisionPoint  the point of collision.
     * @param currentVelocity the current velocity of the ball.
     * @param hitter          the hitting ball.
     * @return the new velocity.
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        if (collisionPoint.onHorLine(this.rectangle)) {
            this.notifyHit(hitter);
        }
        return currentVelocity;
    }

    /**
     * Draw and fill the Paddle  on the Surface given from Game.
     * <p/>
     *
     * @param d the drawsurface.
     */
    public void drawOn(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.drawRectangle((int) this.rectangle.getUpperLeft().getX(),
                (int) this.rectangle.getUpperLeft().getY(),
                (int) this.rectangle.getWidth(),
                (int) this.rectangle.getHeight());

        d.setColor(this.color);
        d.fillRectangle((int) this.rectangle.getUpperLeft().getX(),
                (int) this.rectangle.getUpperLeft().getY(),
                (int) this.rectangle.getWidth(),
                (int) this.rectangle.getHeight());
    }

    /**
     * Implementing the interface method, changing the direction of the paddle based on stroke.
     * <p/>
     *
     * @param dt the speed.
     */
    public void timePassed(double dt) {
        if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            this.moveRight(dt);
        }
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            this.moveLeft(dt);
        }
    }

    /**
     * Return the Rectangle that was collided.
     * <p/>
     *
     * @return The new rectangle of the Paddle.
     */
    public Rectangle getCollisionRectangle() {
        return (this.rectangle);
    }

    /**
     * addToGame() -- add this paddle to the game.
     * <p/>
     *
     * @param g - the Game surface.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }

    /**
     * Removes the padle from the game.
     *
     * @param g - the game env.
     */
    public void removeFromGame(GameLevel g) {
        g.removeCollidable(this);
        g.removeSprite(this);
    }

    /**
     * Creates a ball the player will shoot.
     * <p>
     * @return Ball shot
     */
    public Ball shoot() {
        this.shoot = false;
        return new Ball(this.rectangle.getMiddle(),2,Color.red);
    }

    /**
     * Add hl as a listener to hit events.
     * <p/>
     *
     * @param hl a hit listener
     */
    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    /**
     * Remove hl from the list of listeners.
     * <p/>
     *
     * @param hl a hit listener
     */
    @Override
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }

    /**
     * Notifies all listeners that the block has been hit.
     * <p/>
     *
     * @param hitter the hitting Ball.
     */
    private void notifyHit(Ball hitter) {
        // Makes a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }
}


