package arkanoid.sprites;

import arkanoid.geometry.Point;

/**
 * @author Roey Shefi & Oded Thaller
 * @version 1.0
 * @since 3/30/2016
 */
public class CollisionInfo {

    private Point collisionPoint;
    private Collidable collisionObject;

    /**
     * Instantiate a new Collision Info.
     * <p/>
     *
     * @param collisionPoint  the point of collision.
     * @param collisionObject the object of collision.
     */
    public CollisionInfo(Point collisionPoint, Collidable collisionObject) {
        this.collisionPoint = collisionPoint;
        this.collisionObject = collisionObject;
    }

    /**
     * Gets the point of collision.
     * <p/>
     *
     * @return the point of collision.
     */
    public Point collisionPoint() {
        return this.collisionPoint;
    }

    /**
     * Gets the object of collision.
     * <p/>
     *
     * @return the object of collision.
     */
    public Collidable collisionObject() {
        return this.collisionObject;
    }

}