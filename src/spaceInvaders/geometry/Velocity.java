package spaceinvaders.geometry;

/**
 * @author Roey Shefi & Oded Thaller
 * @version 1.0
 * @since 01.03.2016
 */

public class Velocity {

    private double dx;
    private double dy;

    // constructors

    /**
     * Instantiate Velocity.
     * <p/>
     *
     * @param dx direction on x-axis.
     * @param dy direction on y-axis.
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }


    // accessors

    /**
     * Get direction on x-axis.
     * <p/>
     *
     * @return dx - direction on x-axis.
     */
    public double getDx() {
        return dx;
    }

    /**
     * Get direction on y-axis.
     * <p/>
     *
     * @return dy - direction on y-axis.
     */
    public double getDy() {
        return dy;
    }

    // methods

    /**
     * Apply velocity to a given Point.
     * <p/>
     *
     * @param p  Point.
     * @param dt the speed.
     * @return new Point after movement.
     */
    public Point applyToPoint(Point p, double dt) {
        return new Point(p.getX() + this.dx * dt, p.getY() + this.dy * dt);
    }
}
