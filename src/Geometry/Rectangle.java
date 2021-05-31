package geometry;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import biuoop.DrawSurface;

/**
 * The type Geometry.Rectangle.
 */
public class Rectangle {

    private Point lowerRight;
    private Point upperLeft;
    private double width;
    private double height;
    private Line top;
    private Line bottom;
    private Line left;
    private Line right;

    // Create a new rectangle with location and width/height.

    /**
     * construct a rectangle from an upper left point, width and height.
     *
     * @param upperLeftParam    the rectangle upper left point.
     * @param widthParam        the rectangle's width.
     * @param heightParam       the rectangle's height.
     */
    public Rectangle(Point upperLeftParam, double widthParam, double heightParam) {
        width = widthParam;
        height = heightParam;
        upperLeft = new Point(upperLeftParam.getX(), upperLeftParam.getY());
        lowerRight = new Point(upperLeft.getX() + width, upperLeft.getY() + height);
        top = new Line(upperLeft.getX(), upperLeft.getY(),
                lowerRight.getX(), upperLeft.getY());
        bottom = new Line(upperLeft.getX(), lowerRight.getY(),
                lowerRight.getX(), lowerRight.getY());
        left = new Line(upperLeft.getX(), upperLeft.getY(),
                upperLeft.getX(), lowerRight.getY());
        right = new Line(lowerRight.getX(), upperLeft.getY(),
                lowerRight.getX(), lowerRight.getY());
    }

    /**
     * Instantiates a new Geometry.Rectangle.
     *
     * @param x             the x
     * @param y             the y
     * @param widthParam    the width
     * @param heightParam   the height
     */
    public Rectangle(double x, double y, double widthParam, double heightParam) {
        width = widthParam;
        height = heightParam;
        upperLeft = new Point(x, y);
        lowerRight = new Point(upperLeft.getX() + width,upperLeft.getY() + height);
        top = new Line(upperLeft.getX(), upperLeft.getY(), lowerRight.getX(), upperLeft.getY());
        bottom = new Line(upperLeft.getX(), lowerRight.getY(), lowerRight.getX(), lowerRight.getY());
        left = new Line(upperLeft.getX(), upperLeft.getY(), upperLeft.getX(), lowerRight.getY());
        right = new Line(lowerRight.getX(), upperLeft.getY(), lowerRight.getX(), lowerRight.getY());
    }

    /**
     * Instantiates a new Geometry.Rectangle.
     *
     * @param upperLeftParam  the upper left
     * @param lowerRightParam the lower right
     */
    public Rectangle(Point upperLeftParam, Point lowerRightParam) {
        width = lowerRight.getX() - upperLeft.getX();
        height = lowerRight.getY() - upperLeft.getY();
        upperLeft = new Point(upperLeftParam.getX(), upperLeftParam.getY());
        lowerRight = new Point(lowerRightParam.getX(), lowerRightParam.getY());
        top = new Line(upperLeft.getX(), upperLeft.getY(), lowerRight.getX(), upperLeft.getY());
        bottom = new Line(upperLeft.getX(), lowerRight.getY(), lowerRight.getX(), lowerRight.getY());
        left = new Line(upperLeft.getX(), upperLeft.getY(), upperLeft.getX(), lowerRight.getY());
        right = new Line(lowerRight.getX(), upperLeft.getY(), lowerRight.getX(), lowerRight.getY());
    }

    /**
     * Intersection points java . util . list.
     *
     * @param line  the line
     * @return the list (possibly empty) of intersection points with the specified line.
     */
    public java.util.List<Point> intersectionPoints(Line line) {
        List<Point> intersectionPoints = new ArrayList<Point>();
        List<Line> rectSides = lineList();
        for (Line l : rectSides) {
            if (l.isIntersecting(line)) {
                Point curIntersection = l.intersectionWith(line);
                if (l.start().getY() == l.end().getY()) {
                    // top or bottom side of the  rectangle
                    if (l.isBetweenX(l.intersectionWith(line))) {
                        intersectionPoints.add(curIntersection);
                    }
                } else {
                    // left or right side of the rectangle
                    if (l.isBetweenY(l.intersectionWith(line))) {
                        intersectionPoints.add(curIntersection);
                    }
                }
            }
        }
        return intersectionPoints;
    }

    /**
     * Gets width.
     *
     * @return the width of the rectangle
     */
    public double getWidth() {
        return width;
    }

    /**
     * Gets height.
     *
     * @return the height of the rectangle
     */
    public double getHeight() {
        return height;
    }

    /**
     * Gets upper left.
     *
     * @return the upper left right corner of the rectangle
     */
    public Point getUpperLeft() {
        return upperLeft;
    }

    /**
     * Gets lower right.
     *
     * @return the lower right corner of the rectangle
     */
    public Point getLowerRight() {
        return lowerRight;
    }

    /**
     * Gets upper right.
     *
     * @return the upper right corner of the rectangle
     */
    public Point getUpperRight() {
        return new Point(getLowerRight().getX(), getUpperLeft().getY());
    }

    /**
     * Gets lower left.
     *
     * @return the lower left corner of the rectangle
     */
    public Point getLowerLeft() {
        return new Point(getUpperLeft().getX(), getLowerRight().getY());
    }

    /**
     * this method returns the rectangle's top line.
     *
     * @return the rectangle's top line.
     */
    public Line getTop() {
        return top;
    }

    /**
     * this method returns the rectangle's bottom line.
     *
     * @return the rectangle's bottom line.
     */
    public Line getBottom() {
        return bottom;
    }

    /**
     * this method returns the rectangle's left line.
     *
     * @return the rectangle's left line.
     */
    public Line getLeft() {
        return left;
    }

    /**
     * this method returns the rectangle's right line.
     *
     * @return the rectangle's right line.
     */
    public Line getRight() {
        return right;
    }

    /**
     * Draw rectangle.
     *
     * @param d         surface tor deaw on
     * @param c         the colr
     */
    public void drawRectangle(DrawSurface d, Color c) {
        d.setColor(Color.RED);
        int x = (int) getUpperLeft().getX();
        int y = (int) getUpperLeft().getY();
        int wid = (int) getWidth();
        int hei = (int) getHeight();

        d.fillRectangle(x, y, wid, hei);
    }

    /**
     * This method makes the lines in the list and stores them.
     *
     * @return list containing all sides of the rectangle
     */
    public List<Line> lineList() {
        List<Line> rectangleLines = new ArrayList<Line>();
        rectangleLines.add(new Line(getUpperLeft(), getUpperRight()));
        rectangleLines.add(new Line(getUpperRight(), getLowerRight()));
        rectangleLines.add(new Line(getLowerLeft(), getLowerRight()));
        rectangleLines.add(new Line(getUpperLeft(), getLowerLeft()));
        return rectangleLines;
    }

    /**
     * this method checks if a given point is this rectangle area.
     *
     * @param point the given point.
     * @return true if the point is in the rectangle's area, false otherwise.
     */
    public boolean isContainPoint(Point point) {
        return (point.getX() >= getUpperLeft().getX()) && (point.getX() <= getUpperRight().getX()) &&
               (point.getY() >= getUpperLeft().getY() && point.getY() <= getLowerLeft().getY());
    }
}
