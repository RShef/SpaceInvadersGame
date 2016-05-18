package arkanoid.geometry;
import arkanoid.sprites.Sprite;
import biuoop.DrawSurface;

import java.awt.*;
import java.util.List;

/**
 * @author Roey Shefi & Oded Thaller
 * @version 1.0
 * @since 01.03.2016
 */

public class Line implements Sprite {

    private Point start;
    private Point end;
    private Color c;

    // constructors

    /**
     * Instantiate Line.
     * <p>
     * @param start start point.
     * @param end   end point.
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    /**
     * Instantiate Line.
     * <p>
     * @param x1 start point x location.
     * @param y1 start point y location.
     * @param x2 end point x location.
     * @param y2 end point y location.
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
    }

    /**
     *
     * @param x1
     * @param y1
     * @param x2
     * @param y2
     * @param c
     */
    public Line (double x1, double y1, double x2, double y2, Color c) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
        this.c = c;
    }

    // accessors

    /**
     * Get line's length.
     * <p>
     *
     * @return The line's length.
     */
    public double length() {
        return start.distance(end);
    }

    /**
     * Get line's middle point.
     * <p>
     *
     * @return middle point.
     */
    public Point middle() {
        return new Point((this.start.getX() + this.end.getX()) / 2, (this.start.getY() + this.end.getY()) / 2);
    }

    /**
     * Get line's start point.
     * <p>
     *
     * @return start point.
     */
    public Point start() {
        return this.start;
    }

    /**
     * Get line's end point.
     * <p>
     *
     * @return end point.
     */
    public Point end() {
        return this.end;
    }

    // methods

    /**
     * Checks if line intersects with another line.
     * <p>
     *
     * @param other another line.
     * @return true if lines intersect, false otherwise.
     */
    public boolean isIntersecting(Line other) {
        return this.intersectionWith(other) != null;
    }

    /**
     * finds two lines' intersection point.
     * <p>
     *
     * @param other another line.
     * @return lines' intersection Point, null otherwise.
     */
    public Point intersectionWith(Line other) {

        double x, y, m1, m2, b1, b2;

        // if lines are parallel
        if (this.slope() == other.slope()) {
            return null;
        }

        // if one of the lines is vertical
        if (this.isVertical()) {
            return this.verticalIntersection(other);
        }

        if (other.isVertical()) {
            return other.verticalIntersection(this);
        }

        m1 = this.slope();
        m2 = other.slope();

        // find intercepts with y-axis
        b1 = this.start.getY() - m1 * this.start.getX();
        b2 = other.start.getY() - m2 * other.start.getX();

        // find intersection point values
        x = (b2 - b1) / (m1 - m2);
        y = m1 * x + b1;

        // Makes sure that the point is on both lines.
        if ((x >= this.start.getX() && x <= this.end.getX()) || (x <= this.start.getX() && x >= this.end.getX())) {
            if ((x >= other.start.getX() && x <= other.end.getX()) || (x <= other.start.getX() && x >= other.end.getX()
            )) {
                return new Point(x, y);
            }
        }
        return null;
    }

    /**
     * Returns the closest point of intersection to the given object.
     * <p>
     * if there are no intersection, returns null
     * <p>
     * @param rect the object
     * @return the closest point of intersection
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        List intrPoints = rect.intersectionPoints(this);
        if (intrPoints.size() == 0) {
            return null;
        }
        int j = 0;
        double max = 0;
        for (int i = 0; i < intrPoints.size(); i++) {
            double temp = this.start.distance((Point) intrPoints.get(i));
            if (temp > max) {
                max = temp;
                j = i;
            }
        }
        return (Point) intrPoints.get(j);
    }

    /**
     * Checks if two lines are equal.
     * <p>
     * @param other another line.
     * @return true if lines are equal, false otherwise.
     */
    public boolean equals(Line other) {
        return this.start.equals(other.start) && this.end.equals(other.end);
    }

    /**
     * Returns true if the line is vertical, false otherwise.
     * <p>
     * @return true of false
     */
    public boolean isVertical() {
        return this.end.getX() - this.start.getX() == 0;
    }

    /**
     * Finds the intersection point when one line is vertical.
     * <p>
     * @param other the intersecting line
     * @return the point of intersection
     */
    public Point verticalIntersection(Line other) {
        double x, y, m , b;

        x = this.start.getX();
        m = other.slope();
        b = other.start.getY() - m * other.start.getX();
        y = m * x + b;

        if ((y >= this.start.getY() && y <= this.end.getY()) || (y <= this.start.getY() && y >= this.end.getY())) {
            if (y >= other.start.getY() && y <= other.end.getY() || y <= other.start.getY() && y >= other.end.getY()) {
                return new Point(x, y);
            }
        }
        return null;
    }

    /**
     * Calculate line's slope.
     * <p>
     * @return line's slope.
     */
    public double slope() {
        return (this.end.getY() - this.start.getY()) / (this.end.getX() - this.start.getX());
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(this.c);
        d.drawLine((int)this.start.getX(),(int)this.start.getY(),(int)this.end.getX(),(int)this.end.getY());
    }

    @Override
    public void timePassed() {

    }
}