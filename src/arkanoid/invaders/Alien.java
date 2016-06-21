package arkanoid.invaders;

import arkanoid.game.GameLevel;
import arkanoid.geometry.Point;
import arkanoid.geometry.Rectangle;
import arkanoid.geometry.Velocity;
import arkanoid.listeners.HitListener;
import arkanoid.listeners.HitNotifier;
import arkanoid.sprites.Ball;
import arkanoid.sprites.Collidable;
import arkanoid.sprites.Sprite;
import biuoop.DrawSurface;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Roey Shefi & Oded Thaller
 * @version 1.0
 * @since 15/06/2016
 */

public class Alien implements Collidable, Sprite, HitNotifier {

    private Rectangle b;
    private int hits;
    private java.util.List<HitListener> hitListeners;

    /**
     * Instantiates a new alien.
     * <p>
     * @param upperLeft upper left point
     * @param width width
     * @param height height
     */
    public Alien (Point upperLeft, double width, double height) {
        this.b = new Rectangle(upperLeft, width, height);
        this.hits = 1;
        this.hitListeners = new LinkedList<>();
    }

    /**
     * returns the rectangle.
     * <p/>
     *
     * @return the rectangle.
     */
    @Override
    public Rectangle getCollisionRectangle() {
        return this.b;
    }

    /**
     * Sets the alien's rectangle.
     * <p>
     * @param rect the new rectangle
     */
    public void setCollisionRectangle(Rectangle rect) {
        this.b = rect;
    }

    /**
     * Notifies the object it has been hit
     * <p/>
     * reduces one hit and returns new velocity based on collision.
     *
     * @param hitter          the hitter ball.
     * @param collisionPoint  the point of collision.
     * @param currentVelocity the current velocity of the ball.
     * @return new Velocity
     */
    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        if (this.hits > 0) {
            this.hits--;
        }
        // If the shot hits the corner.
        if (this.b.points().contains(collisionPoint) && collisionPoint.getY() > 2 && collisionPoint.getY() > 2) {
            collisionPoint.setY(collisionPoint.getY() - 2);
            this.notifyHit(hitter);
        }
        // if shot hits alien
        if (collisionPoint.getY() == this.b.getUpperLeft().getY()
                || Math.round(collisionPoint.getY()) == Math.round(this.b.getLowRight().getY())) {
            this.notifyHit(hitter);
        }
        return currentVelocity;
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
     * Draws the sprite on the screen.
     * <p/>
     *
     * @param d the draw surface.
     */
    @Override
    public void drawOn(DrawSurface d) {
        Image image = null;
        try {
            image = ImageIO.read(new File("resources/images/alien.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        d.drawImage((int) this.b.getUpperLeft().getX(), (int) this.b.getUpperLeft().getY(), image);
    }

    /**
     * Notify the Sprite that time has passed.
     *
     * @param dt is the time passed from previous frame.
     *           <p/>
     */
    @Override
    public void timePassed(double dt) {
    }

    /**
     * Moves the swarm one step in a given direction.
     * <p>
     * @param dx the x change
     * @param dy the y change
     */
    public void moveOneStep(double dx, double dy) {
        double x = this.getCollisionRectangle().getUpperLeft().getX() + dx;
        double y = this.getCollisionRectangle().getUpperLeft().getY() + dy;

        this.b = new Rectangle(new Point(x, y), this.getCollisionRectangle().getWidth(),
                this.getCollisionRectangle().getHeight());
    }

    /**
     * Adds the block both as Collidable and as Sprite.
     * <p/>
     *
     * @param g the game environment.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }

    /**
     * Removes a block from the game.
     * <p/>
     *
     * @param g the game.
     */
    public void removeFromGame(GameLevel g) {
        g.removeCollidable(this);
        g.removeSprite(this);
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

    /**
     * Gest current hit points.
     * <p>
     * @return hits
     */
    public int getHits() {
        return this.hits;
    }

    public Ball shoot() {
        Point p = new Point(this.b.getHorizontalDown().middle().getX(),
                this.b.getHorizontalDown().middle().getY() + 10);
        return new Ball(p,3,Color.yellow);
    }
}
