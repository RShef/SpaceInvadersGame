package arkanoid.sprites;

import arkanoid.geometry.Velocity;
import arkanoid.geometry.Point;
import arkanoid.geometry.Rectangle;

/**
 * @author Roey Shefi & Oded Thaller
 * @version 1.0
 * @since 3/30/2016
 */
public interface Collidable {

    /**
     * returns the rectangle.
     * <p>
     *
     * @return the rectangle.
     */
    Rectangle getCollisionRectangle();

    /**
     * Notifies the object it has been hit
     * <p>
     * reduces one hit and returns new velocity based on collision.
     *
     * @param collisionPoint  the point of collision.
     * @param currentVelocity the current velocity of the ball.
     * @param hitter          the hitter ball.
     * @return new Velocity
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}

