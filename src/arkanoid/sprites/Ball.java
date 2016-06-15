package arkanoid.sprites;

import arkanoid.game.GameLevel;
import arkanoid.game.GameEnvironment;
import arkanoid.geometry.Point;
import arkanoid.geometry.Velocity;
import arkanoid.geometry.Line;
import biuoop.DrawSurface;
import java.awt.Color;

/**
 * @author Roey Shefi & Oded Thaller
 * @version 1.1
 * @since 01.03.2016
 */

public class Ball implements Sprite {

    private Point center;
    private int radius;
    private Color color;
    private Velocity velocity;
    private GameEnvironment environment;

    // constructors

    /**
     * Instantiate Ball.
     * <p>
     *
     * @param center center point.
     * @param r      radius.
     * @param color  color
     */
    public Ball(Point center, int r, Color color) {
        this.center = center;
        this.radius = r;
        this.color = color;
    }

    /**
     * Instantiate Ball.
     * <p/>
     *
     * @param x     center point x location.
     * @param y     center point y location.
     * @param r     radius.
     * @param color color
     */
    public Ball(int x, int y, int r, Color color) {
        this.center = new Point(x, y);
        this.radius = r;
        this.color = color;
    }

    // Getters

    /**
     * Get ball's center x location.
     * <p/>
     *
     * @return center x location.
     */
    public double getX() {
        return this.center.getX();
    }

    /**
     * Get ball's center y location.
     * <p/>
     *
     * @return center y location.
     */
    public double getY() {
        return this.center.getY();
    }

    /**
     * Get ball's size.
     * <p/>
     *
     * @return radius.
     */
    public int getSize() {
        return this.radius;
    }

    /**
     * Get ball's color.
     * <p/>
     *
     * @return color.
     */
    public Color getColor() {
        return this.color;
    }

    /**
     * Get ball's Velocity.
     * <p/>
     *
     * @return Velocity.
     */
    public Velocity getVelocity() {
        return this.velocity;
    }

    /**
     * Set ball's Velocity.
     * <p/>
     *
     * @param v Velocity.
     */
    public void setVelocity(Velocity v) {
        this.velocity = new Velocity(v.getDx(), v.getDy());
    }


    // Setters

    /**
     * Get ball's Game Environment.
     * <p/>
     *
     * @return Environment.
     */
    public GameEnvironment getEnvironment() {
        return this.environment;
    }

    /**
     * Set ball's Environment.
     * <p/>
     *
     * @param environment1 Game Environment.
     */
    public void setEnvironment(GameEnvironment environment1) {
        this.environment = environment1;
    }

    /**
     * Set ball's Velocity.
     * <p/>
     *
     * @param dx Angle.
     * @param dy speed.
     */
    public void setVelocity(double dx, double dy) {
        this.velocity = new Velocity(dx, dy);
    }

    // Methods

    /**
     * Draw ball on GUI.
     * <p/>
     *
     * @param surface draw surface.
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillCircle((int) this.center.getX(), (int) this.center.getY(), this.radius);
        surface.setColor(Color.black);
        surface.drawCircle((int) this.center.getX(), (int) this.center.getY(), this.radius);
    }

    /**
     * Notify the ball of time passed by moving.
     *
     * @param dt the speed.
     */
    public void timePassed(double dt) {
        this.moveOneStep(dt);
    }

    /**
     * Computes the ball's trajectory.
     * <p/>
     *
     * @param dt is the time passed from previous frame.
     * @return trajectory Line
     */
    public Line findTrajectory(double dt) {
        Point end = new Point(this.getX() + this.velocity.getDx() * dt, this.getY() + this.velocity.getDy() * dt);
        return new Line(this.center, end);
    }

    /**
     * Moves the ball one step according to current velocity.
     * <p/>
     * If ball will hit an obstacle, it will change direction.
     *
     * @param dt is the time passed from previous frame.
     */
    public void moveOneStep(double dt) {
        Line trajectory = findTrajectory(dt);
        CollisionInfo collision = this.environment.getClosestCollision(trajectory);
        if (collision != null) {
            this.center = findNearHitPoint(collision.collisionPoint());
            this.setVelocity(collision.collisionObject().hit(this, collision.collisionPoint(), this.velocity));
        } else {
            this.center = this.getVelocity().applyToPoint(this.center, dt);
        }
    }

    /**
     * Finds a point that is very close to the point of collision.
     * <p/>
     *
     * @param collisionPoint the Collision Info
     * @return the closest point the collision
     */
    public Point findNearHitPoint(Point collisionPoint) {
        double x;
        double y;
        if (this.velocity.getDx() > 0) {
            x = collisionPoint.getX() - 2;
        } else {
            x = collisionPoint.getX() + 2;
        }
        if (this.velocity.getDy() > 0) {
            y = collisionPoint.getY() - 2;
        } else {
            y = collisionPoint.getY() + 2;
        }
        return new Point(x, y);
    }

    /**
     * Adds the ball both as Collidable and as Sprite.
     * <p/>
     *
     * @param g the game environment.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

    /**
     * Removes the ball from the game.
     *
     * @param g - the game environment.
     */
    public void removeFromGame(GameLevel g) {
        g.removeSprite(this);
    }
}