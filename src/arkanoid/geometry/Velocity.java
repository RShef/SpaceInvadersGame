package arkanoid.geometry;

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

    /**
     * Instantiate Velocity.
     * <p/>
     *
     * @param angle angle on x-axis.
     * @param speed speed on y-axis.
     * @return Velocity.
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double dx = Math.cos(Math.toRadians(angle)) * speed;
        double dy = Math.sqrt(Math.pow(speed, 2) - Math.pow(dx, 2));
        return new Velocity(dx, dy);

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
     * @param p Point.
     * @return new Point after movement.
     */
    public Point applyToPoint(Point p) {
        return new Point(p.getX() + this.dx, p.getY() + this.dy);
    }
}
