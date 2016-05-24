package arkanoid.game;

import arkanoid.geometry.Line;
import arkanoid.geometry.Point;
import arkanoid.geometry.Rectangle;
import arkanoid.sprites.Collidable;
import arkanoid.sprites.CollisionInfo;

import java.util.ArrayList;

/**
 * @author Roey Shefi & Oded Thaller
 * @version 1.0
 * @since 3/30/2016
 */
public class GameEnvironment {

    private ArrayList<Collidable> collidables;

    /**
     * Instantiate Game Environment.
     * <p>
     * A game environment is collection of Collidables.
     */
    public GameEnvironment() {
        this.collidables = new ArrayList<>();
    }

    /**
     * Get environment's collidables.
     * <p>
     * @return collidables
     */
    public ArrayList<Collidable> getCollidables() {
        return this.collidables;
    }

    /**
     * Add a Collidable object to the Game Environment.
     * <p>
     * @param c a Collidable object
     */
    public void addCollidable(Collidable c) {
        this.collidables.add(c);
    }

    /**
     * Get the Collision Info of the closest collision to occur.
     * <p>
     * @param trajectory trajectory of the ball
     * @return Collision Info
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        for (Collidable collidable : collidables) {
            Rectangle collisionShape = collidable.getCollisionRectangle();
            Point closestPoint = trajectory.closestIntersectionToStartOfLine(collisionShape);
            if (closestPoint != null) {
                return new CollisionInfo(closestPoint, collidable);
            }
        }
        return null;
    }

}