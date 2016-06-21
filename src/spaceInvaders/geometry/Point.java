package spaceinvaders.geometry;

/**
 * @author Roey Shefi & Oded Thaller
 * @version 1.0
 * @since 01.03.2016
 */

public class Point {

    private double x;
    private double y;

    /**
     * Instantiate point.
     * <p>
     *
     * @param x - point x.
     * @param y - point y.
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * distance -- return the distance of this point to the other point.
     * <p>
     *
     * @param other - the point to be compared to.
     * @return - the distance.
     */
    public double distance(Point other) {
        return Math.sqrt(Math.pow(this.x - other.getX(), 2) + Math.pow(this.y - other.getY(), 2));
    }


    /**
     * For coding style.
     * <p>
     *
     * @return - 0.
     */
    public int hashCode() {
        return 0;
    }

    /**
     * Return the x and y values of this point.
     * <p>
     *
     * @return - x or y as asked.
     */
    public double getX() {
        return x;
    }

    /**
     * Sets X.
     * <p>
     *
     * @param x1 the x.
     */
    public void setX(double x1) {
        this.x = x1;
    }

    /**
     * Return the x and y values of this point.
     * <p>
     *
     * @return - x or y as asked.
     */
    public double getY() {
        return y;
    }

    /**
     * Sets Y.
     * <p>
     *
     * @param y1 the y.
     */
    public void setY(double y1) {
        this.y = y1;
    }

    /**
     * returns true if the point is on the given line, false otherwise.
     * <p>
     *
     * @param l a line
     * @return true or false
     */
    public boolean isOnLine(Line l) {
        return (l.end().getY() - this.y) / (l.end().getX() - this.x) == l.slope();
    }

    /**
     * Returns true if a point is the horizontal line of a given rectangle, false otherwise.
     * <p>
     *
     * @param rec a rectangle.
     * @return true or false.
     */
    public boolean onHorLine(Rectangle rec) {
        return (this.isOnLine(rec.getHorizontalUp()) || this.isOnLine(rec.getHorizontalDown()));
    }


}