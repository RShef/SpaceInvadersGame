package arkanoid.sprites;

import arkanoid.game.GameLevel;
import arkanoid.listeners.HitListener;
import arkanoid.listeners.HitNotifier;
import arkanoid.geometry.Velocity;
import arkanoid.geometry.Point;
import arkanoid.geometry.Rectangle;
import biuoop.DrawSurface;

import java.awt.Color;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Roey Shefi & Oded Thaller
 * @version 1.0
 * @since 3/30/2016
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private Rectangle b;
    private int hits;
    private Color color;
    private List<HitListener> hitListeners;

    /**
     * Instantiate block.
     * <p/>
     *
     * @param upperLeft - upper point.
     * @param width     of the rectangle.
     * @param height    of the rectangle.
     * @param hits      number of block's hit points.
     * @param color     block's color.
     */
    public Block(Point upperLeft, double width, double height, int hits, Color color) {
        this.b = new Rectangle(upperLeft, width, height);
        this.hits = hits;
        this.color = color;
        this.hitListeners = new LinkedList<>();
    }

    /**
     * Return the rectangle.
     * <p/>
     *
     * @return - the rectangle.
     */
    public Rectangle getCollisionRectangle() {
        return b;
    }

    /**
     * Returns this object's current hit points.
     * <p/>
     *
     * @return hit points
     */
    public int getHitPoints() {
        return this.hits;
    }

    /**
     * Notifies the object it has been hit
     * <p/>
     * reduces one hit and returns new velocity based on collision.
     *
     * @param collisionPoint  the point of collision.
     * @param currentVelocity the current velocity of the ball.
     * @return new Velocity
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        if (this.hits > 0) {
            this.hits--;
        }
        // If the ball hits the corner.
        if (this.b.points().contains(collisionPoint) && collisionPoint.getY() > 2 && collisionPoint.getY() > 2) {
            collisionPoint.setY(collisionPoint.getY() - 2);
            this.notifyHit(hitter);
            return new Velocity(Math.round(currentVelocity.getDx()) * -1, Math.round(currentVelocity.getDy()) * -1);
        }
        // if ball hits horizontal object, reverse vertical direction
        if (collisionPoint.getY() == this.b.getUpperLeft().getY()
                || Math.round(collisionPoint.getY()) == Math.round(this.b.getLowRight().getY())) {
            this.notifyHit(hitter);
            return new Velocity(Math.round(currentVelocity.getDx()), Math.round(currentVelocity.getDy()) * -1);
            // if ball hits vertical line, reverse horizontal direction
        } else if (Math.round(collisionPoint.getX()) == Math.round(this.b.getUpperLeft().getX())
                || Math.round(collisionPoint.getX()) == Math.round(this.b.getLowRight().getX())) {
            this.notifyHit(hitter);
            return new Velocity(Math.round(currentVelocity.getDx()) * -1, Math.round(currentVelocity.getDy()));
        } else {
            return null;
        }
    }

    /**
     * Draw Block on GUI.
     * <p/>
     *
     * @param surface draw surface.
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillRectangle((int) this.b.getUpperLeft().getX(), (int) this.b.getUpperLeft().getY(),
                (int) this.b.getWidth(), (int) this.b.getHeight());
        surface.setColor(Color.black);
        surface.drawRectangle((int) this.b.getUpperLeft().getX(), (int) this.b.getUpperLeft().getY(),
                (int) this.b.getWidth(), (int) this.b.getHeight());
        surface.setColor(Color.white);
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
     * Notifies block of time passed.
     * <p/>
     */
    public void timePassed() {
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
