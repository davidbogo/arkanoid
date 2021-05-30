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
     * @param upperLeft1 the upper left 1
     * @param width1     the width 1
     * @param height1    the height 1
     */
// Create a new rectangle with location and width/height.
    public Rectangle(Point upperLeft1, double width1, double height1) {
        width = width1;
        height = height1;
        upperLeft = upperLeft1;
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
        return upperLeft;
    }

    /**
     * Gets lower right.
     *
     * @return the lower right
     */
    public Point getLowerRight() {
        return lowerRight;
    }

    /**
     * Gets upper right.
     *
     * @return the upper right
     */
    public Point getUpperRight() {
        return upperRight;
    }

    /**
     * Gets lower left.
     *
     * @return the lower left
     */
    public Point getLowerLeft() {
        return lowerLeft;
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