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
     * @param upperLeft the rectangle upper left point.
     * @param width     the rectangle's width.
     * @param height    the rectangle's height.
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.width = width;
        this.height = height;
        this.upperLeft = new Point(upperLeft.getX(), upperLeft.getY());
        this.lowerRight = new Point(upperLeft.getX() + width,
                upperLeft.getY() + height);
        this.top = new Line(this.upperLeft.getX(), this.upperLeft.getY(),
                this.lowerRight.getX(), this.upperLeft.getY());
        this.bottom = new Line(this.upperLeft.getX(), this.lowerRight.getY(),
                this.lowerRight.getX(), this.lowerRight.getY());
        this.left = new Line(this.upperLeft.getX(), this.upperLeft.getY(),
                this.upperLeft.getX(), this.lowerRight.getY());
        this.right = new Line(this.lowerRight.getX(), this.upperLeft.getY(),
                this.lowerRight.getX(), this.lowerRight.getY());
    }

    /**
     * Instantiates a new Geometry.Rectangle.
     *
     * @param x      the x
     * @param y      the y
     * @param width  the width
     * @param height the height
     */
    public Rectangle(double x, double y, double width, double height) {
        this.width = width;
        this.height = height;
        this.upperLeft = new Point(x, y);
        this.lowerRight = new Point(upperLeft.getX() + width,
                upperLeft.getY() + height);
        this.top = new Line(this.upperLeft.getX(), this.upperLeft.getY(),
                this.lowerRight.getX(), this.upperLeft.getY());
        this.bottom = new Line(this.upperLeft.getX(), this.lowerRight.getY(),
                this.lowerRight.getX(), this.lowerRight.getY());
        this.left = new Line(this.upperLeft.getX(), this.upperLeft.getY(),
                this.upperLeft.getX(), this.lowerRight.getY());
        this.right = new Line(this.lowerRight.getX(), this.upperLeft.getY(),
                this.lowerRight.getX(), this.lowerRight.getY());
    }

    /**
     * Instantiates a new Geometry.Rectangle.
     *
     * @param upperLeft  the upper left
     * @param lowerRight the lower right
     */
    public Rectangle(Point upperLeft, Point lowerRight) {
        this.width = lowerRight.getX() - upperLeft.getX();
        this.height = lowerRight.getY() - upperLeft.getY();
        this.upperLeft = new Point(upperLeft.getX(), upperLeft.getY());
        this.lowerRight = new Point(lowerRight.getX(), lowerRight.getY());
        this.top = new Line(this.upperLeft.getX(), this.upperLeft.getY(),
                this.lowerRight.getX(), this.upperLeft.getY());
        this.bottom = new Line(this.upperLeft.getX(), this.lowerRight.getY(),
                this.lowerRight.getX(), this.lowerRight.getY());
        this.left = new Line(this.upperLeft.getX(), this.upperLeft.getY(),
                this.upperLeft.getX(), this.lowerRight.getY());
        this.right = new Line(this.lowerRight.getX(), this.upperLeft.getY(),
                this.lowerRight.getX(), this.lowerRight.getY());
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
        List<Point> intrsPoints = new ArrayList<Point>();

        for (int i = 0; i < 4; i++) {
            if (this.lineList().get(i).isIntersecting(line)) {
                Line l = this.lineList().get(i);
                if (i == 0 || i == 2) {
                    if (l.isBetweenX(l.intersectionWith(line))) {
                        intrsPoints.add(l.intersectionWith(line));
                    }
                } else {
                    if (l.isBetweenY(l.intersectionWith(line))) {
                        intrsPoints.add(l.intersectionWith(line));
                    }
                }
            }
        }
        return intrsPoints;
    }

    /**
     * Gets width.
     *
     * @return the width
     */
// Return the width and height of the rectangle
    public double getWidth() {
        return this.width;
    }

    /**
     * Gets height.
     *
     * @return the height
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * Gets lower right.
     *
     * @return the lower right
     */
    public Point getLowerRight() {
        return this.lowerRight;
    }


    /**
     * Gets upper left.
     *
     * @return the upper left
     */
// Returns the upper-left point of the rectangle.
    public Point getUpperLeft() {
        return this.upperLeft;
    }


    /**
     * Gets upper right.
     *
     * @return the upper right
     */
    public Point getUpperRight() {
        return new Point(this.getLowerRight().getX(),
                this.getUpperLeft().getY());
    }


    /**
     * Gets lower left.
     *
     * @return the lower left
     */
    public Point getLowerLeft() {
        return new Point(this.getUpperLeft().getX(),
                this.getLowerRight().getY());
    }

    /**
     * this method returns the rectangle's top line.
     *
     * @return the rectangle's top line.
     */
    public Line getTop() {
        return this.top;
    }

    /**
     * this method returns the rectangle's bottom line.
     *
     * @return the rectangle's bottom line.
     */
    public Line getBottom() {
        return this.bottom;
    }

    /**
     * this method returns the rectangle's left line.
     *
     * @return the rectangle's left line.
     */
    public Line getLeft() {
        return this.left;
    }

    /**
     * this method returns the rectangle's right line.
     *
     * @return the rectangle's right line.
     */
    public Line getRight() {
        return this.right;
    }


    /**
     * Draw rectangle.
     *
     * @param d the d
     * @param c the c
     */
    public void drawRectangle(DrawSurface d, Color c) {
        d.setColor(Color.RED);
        int x = (int) this.getUpperLeft().getX();
        int y = (int) this.getUpperLeft().getY();
        int wid = (int) this.getWidth();
        int hei = (int) this.getHeight();

        d.fillRectangle(x, y, wid, hei);
    }

    /**
     * This method makes the lines in the list and stores them.
     *
     * @return a list of line of the rectangle
     */
    public List<Line> lineList() {
        Line a = new Line(this.getUpperLeft(), this.getUpperRight());
        Line b = new Line(this.getUpperRight(), this.getLowerRight());
        Line c = new Line(this.getLowerLeft(), this.getLowerRight());
        Line d = new Line(this.getUpperLeft(), this.getLowerLeft());
        List<Line> rectangleLines = new ArrayList<Line>();
        rectangleLines.add(a);
        rectangleLines.add(b);
        rectangleLines.add(c);
        rectangleLines.add(d);

        return rectangleLines;
    }

    /**
     * this method checks if a given point is this rectangle area.
     *
     * @param point the given point.
     * @return true if the point is in the rectangle's area, false otherwise.
     */
    public boolean containsPoint(Point point) {
        return point.getX() >= this.getUpperLeft().getX()
                && point.getX() <= this.getUpperRight().getX()
                && point.getY() >= this.getUpperLeft().getY()
                && point.getY() <= this.getLowerLeft().getY();
    }
}