package geometry;

/**
 * The type Geometry.Point.
 */
public class Point {
    private double x;
    private double y;

    /**
     * Instantiates a new Geometry.Point.
     *
     * @param xParam    the x
     * @param yParam    the y
     */
// constructor
    public Point(double xParam, double yParam) {
        x = xParam;
        y = yParam;
    }

    /**
     * Distance double.
     *
     * @param other     the other point
     * @return the distance to the other point
     */
    public double distance(Point other) {
        double deltaX = x - other.x;
        double deltaY = y - other.y;
        return Math.sqrt(deltaX * deltaX + deltaY * deltaY);
    }

    /**
     * Equals boolean.
     *
     * @param other     the other point
     * @return true is the points are equal, false otherwise
     */
    public boolean equals(Point other) {
        if ((other.x == x) && (other.y == x)) {
            return true;
        }
        return false;
    }

    /**
     * Gets x.
     *
     * @return the x coordinate of the point
     */
    public double getX() {
        return x;
    }

    /**
     * Gets y.
     *
     * @return the y coordinate of the point
     */
    public double getY() {
        return y;
    }
}
