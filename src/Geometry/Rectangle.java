package geometry;

import geometry.Point;
import geometry.Line;
import java.util.ArrayList;

/**
 * The type Rectangle.
 */
public class Rectangle {
    private double width;
    private double height;
    private Point upperLeft;
    private Point lowerRight;
    private Point upperRight;
    private Point lowerLeft;

    /**
     * Instantiates a new Rectangle.
     *
     * @param upLeft     the upper left
     * @param wdth       the width
     * @param hght       the height
     */
// Create a new rectangle with location and width/height.
    public Rectangle(Point upLeft, double wdth, double hght) {
        width = wdth;
        height = hght;
        upperLeft = upLeft;
        lowerRight = new Point(upperLeft.getX() + width, upperLeft.getY() + height);
        lowerLeft = new Point(upperLeft.getX(), lowerRight.getY());
        upperRight = new Point(lowerRight.getX(), upperLeft.getY());
    }

    /**
     * Instantiates a new Rectangle.
     *
     * @param x         the left x
     * @param y         the top y
     * @param wdth      the width
     * @param hght      the height
     */
    public Rectangle(double x, double y, double wdth, double hght) {
        width = wdth;
        height = hght;
        upperLeft = new Point(x, y);
        lowerRight = new Point(upperLeft.getX() + width, upperLeft.getY() + height);
        lowerLeft = new Point(upperLeft.getX(), lowerRight.getY());
        upperRight = new Point(lowerRight.getX(), upperLeft.getY());
    }

        /**
         * Intersection points java . util . list.
         *
         * @param line the line
         * @return the java . util . list
         */
// Return a (possibly empty) List of intersection points
    // with the specified line.
    public java.util.List<Point> intersectionPoints(Line line) {
        Line upLine = new Line(getUpperLeft(), getUpperRight());
        Line downLine = new Line(getLowerLeft(), getLowerRight());
        Line leftLine = new Line(getUpperLeft(), getLowerLeft());
        Line rightLine = new Line(getUpperRight(), getLowerRight());
        java.util.List<Point> intersections = new ArrayList<Point>();
        if (line.isIntersecting(upLine)) {
            intersections.add(line.intersectionWith(upLine));
        }
        if (line.isIntersecting(downLine)) {
            intersections.add(line.intersectionWith(downLine));
        }
        if (line.isIntersecting(leftLine)) {
            intersections.add(line.intersectionWith(leftLine));
        }
        if (line.isIntersecting(rightLine)) {
            intersections.add(line.intersectionWith(rightLine));
        }
        if (intersections.isEmpty()) {
            return null;
        } else {
            return intersections;
        }
    }

    /**
     * Gets width.
     *
     * @return the width
     */
// Return the width and height of the rectangle
    public double getWidth() {
        return width;
    }

    /**
     * Gets height.
     *
     * @return the height
     */
    public double getHeight() {
        return height;
    }

    /**
     * Gets upper left.
     *
     * @return the upper left
     */
// Returns the upper-left point of the rectangle.
    public Point getUpperLeft() {
        return new Point(upperLeft.getX(), upperLeft.getY());
    }

    /**
     * Gets lower right.
     *
     * @return the lower right
     */
    public Point getLowerRight() {
        return new Point(lowerRight.getX(), lowerRight.getY());
    }

    /**
     * Gets upper right.
     *
     * @return the upper right
     */
    public Point getUpperRight() {
        return new Point(upperRight.getX(), upperRight.getY());
    }

    /**
     * Gets lower left.
     *
     * @return the lower left
     */
    public Point getLowerLeft() {
        return new Point(lowerLeft.getX(), lowerLeft.getY());
    }

    /**
     * Gets up line.
     *
     * @return the up line
     */
    public Line getUpLine() {
        return new Line(upperLeft, upperRight);
    }

    /**
     * Gets down line.
     *
     * @return the down line
     */
    public Line getDownLine() {
        return new Line(lowerLeft, lowerRight);
    }

    /**
     * Gets left line.
     *
     * @return the left line
     */
    public Line getLeftLine() {
        return new Line(upperLeft, lowerLeft);
    }

    /**
     * Gets right line.
     *
     * @return the right line
     */
    public Line getRightLine() {
        return new Line(upperRight, lowerRight);
    }
}