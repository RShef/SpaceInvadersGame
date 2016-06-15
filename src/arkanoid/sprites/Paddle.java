package arkanoid.sprites;

import arkanoid.game.GameLevel;
import arkanoid.geometry.Velocity;
import arkanoid.geometry.Point;
import arkanoid.geometry.Rectangle;
import biuoop.DrawSurface;

import java.awt.Color;

import biuoop.KeyboardSensor;

/**
 * @author Roey Shefi & Oded Thaller
 * @version 1.0
 * @since 4/4/2016
 */
public class Paddle implements Sprite, Collidable {

    private Color color;
    private Rectangle rectangle;
    private KeyboardSensor keyboard;
    private int width;
    private int paddleSpeed;

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
        double x = currentVelocity.getDx();
        double y = currentVelocity.getDy();
        double speed = Math.round(Math.sqrt((y * y) + (x * x)));

        if (collisionPoint.onVerLine(this.rectangle)) {
            return (new Velocity(Math.round(x) * -1, Math.round(y)));
        } else if (collisionPoint.onHorLine(this.rectangle)) {
            /* Dividing the paddle in to five equal regions.
            this is to simplify code readability.*/
            double regionSize = Math.round(this.getCollisionRectangle().getWidth()) / 5;
            double regionOne = Math.round(this.getCollisionRectangle().getUpperLeft().getX()) + regionSize;
            double regionTwo = regionOne + regionSize;
            double regionThree = regionTwo + regionSize;
            double regionFour = regionThree + regionSize;

            if (Math.round(collisionPoint.getX()) <= regionOne) {
                Velocity v = Velocity.fromAngleAndSpeed(300, speed);
                return new Velocity(Math.round(v.getDy()) * -1, Math.round(v.getDx()) * -1);
            } else if (Math.round(collisionPoint.getX()) <= regionTwo) {
                Velocity v = Velocity.fromAngleAndSpeed(330, speed);
                return new Velocity(Math.round(v.getDy()) * -1, Math.round(v.getDx()) * -1);
                // In the third region of the paddle, the ball will go up with no horz change.
            } else if (Math.round(collisionPoint.getX()) <= regionThree) {
                // change the direction in 360 degrees
                return new Velocity(Math.round(x), Math.round(y) * (-1));
            }
            if (Math.round(collisionPoint.getX()) <= regionFour) {
                Velocity v = Velocity.fromAngleAndSpeed(30, speed);
                return new Velocity(Math.round(v.getDy()), Math.round(v.getDx()) * -1);
                // the fifth region.
            } else {
                Velocity v = Velocity.fromAngleAndSpeed(60, speed);
                return new Velocity(Math.round(v.getDy()), Math.round(v.getDx()) * -1);
            }
        } else {
            return (new Velocity(Math.round(x), Math.round(y)));
        }
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

}


