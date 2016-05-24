package arkanoid.geometry;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Roey Shefi & Oded Thaller
 * @version 1.0
 * @since 28.03.2016
 */
public class Rectangle {
    private Line horizontalUp;
    private Line horizontalDown;
    private Line verticalLeft;
    private Line verticalRight;
    private double width;
    private double height;
    private Point leftCorner;
    private Point lowLeft;
    private Point lowRight;
    private Point upperRight;

    // Constructor

    /**
     * Instantiate a new rectangle with location and width/height.
     * <p/>
     *
     * @param upperLeft point of the rectangle.
     * @param width     of the rectangle.
     * @param height    of the rectangle.
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.leftCorner = upperLeft;
        this.width = width;
        this.height = height;
        makePoints();
        makeEdges();
    }

    // Accessors

    /**
     * Return the width of the rectangle.
     * <p/>
     *
     * @return width of the rectangle.
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * Return the height of the rectangle.
     * <p/>
     *
     * @return height of the rectangle.
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * Returns the upper-left point of the rectangle.
     * <p/>
     *
     * @return upper-left point of the rectangle.
     */
    public Point getUpperLeft() {
        return this.leftCorner;
    }

    /**
     * Returns the lower-left point of the rectangle.
     * <p/>
     *
     * @return lower-left point of the rectangle.
     */
    public Point getLowRight() {
        return this.lowRight;
    }

    /**
     * Returns rectangle's upper horizontal line.
     *
     * @return horizontalUp line
     */
    public Line getHorizontalUp() {
        return this.horizontalUp;
    }

    /**
     * Returns rectangle's lower horizontal line.
     *
     * @return horizontalDown line
     */
    public Line getHorizontalDown() {
        return this.horizontalDown;
    }

    /**
     * Returns rectangle's left vertical line.
     *
     * @return verticalLeft line
     */
    public Line getVerticalLeft() {
        return this.verticalLeft;
    }

    /**
     * Returns rectangle's right vertical line.
     *
     * @return verticalRight line
     */
    public Line getVerticalRight() {
        return this.verticalRight;
    }

    // Methods

    /**
     * Return a (possibly empty) List of intersection points with the specified line.
     * <p/>
     *
     * @param line of which to check intersection points with.
     * @return a list of said points.
     */
    public List<Point> intersectionPoints(Line line) {
        List<Point> intrPoints = new ArrayList<>();
        if (horizontalDown.intersectionWith(line) != null) {
            intrPoints.add(horizontalDown.intersectionWith(line));
        }
        if (horizontalUp.intersectionWith(line) != null) {
            intrPoints.add(horizontalUp.intersectionWith(line));
        }
        if (verticalRight.intersectionWith(line) != null) {
            intrPoints.add(verticalRight.intersectionWith(line));
        }
        if (verticalLeft.intersectionWith(line) != null) {
            intrPoints.add(verticalLeft.intersectionWith(line));
        }
        return intrPoints;
    }

    /**
     * Finds and assignees points ot the rectangle from the width, height and upper left point.
     * <p/>
     */
    public void makePoints() {
        this.lowLeft = new Point(this.leftCorner.getX(), this.leftCorner.getY() + this.height);
        this.lowRight = new Point(this.leftCorner.getX() + this.width, this.leftCorner.getY() + this.height);
        this.upperRight = new Point(this.leftCorner.getX() + this.width, this.leftCorner.getY());
    }

    /**
     * Creates lines that consist the rectangle.
     * <p/>
     */
    public void makeEdges() {
        this.horizontalUp = new Line(this.leftCorner, this.upperRight);
        this.horizontalDown = new Line(this.lowLeft, this.lowRight);
        this.verticalRight = new Line(this.upperRight, this.lowRight);
        this.verticalLeft = new Line(this.leftCorner, this.lowLeft);
    }

    /**
     * Returns a list of the 4 points consisting the rectangle.
     * <p/>
     *
     * @return points, a list of the 4 points consisting the rectangle.
     */
    public List<Point> points() {
        List<Point> points = new ArrayList<>();
        points.add(leftCorner);
        points.add(lowRight);
        points.add(upperRight);
        points.add(lowLeft);
        return points;
    }

    /**
     * Returns a list of the 4 lines consisting the rectangle.
     * <p/>
     *
     * @return points, a list of the 4 points consisting the rectangle.
     */
    public List<Line> lines() {
        List<Line> lines = new ArrayList<>();
        lines.add(horizontalDown);
        lines.add(horizontalUp);
        lines.add(verticalLeft);
        lines.add(verticalRight);
        return lines;
    }

}