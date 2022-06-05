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
     * @param x the x
     * @param y the y
     */
// constructor
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Distance double.
     *
     * @param other the other
     * @return the double
     */
// distance -- return the distance of this point to the other point
    public double distance(Point other) {
        double deltaX = this.x - other.x;
        double deltaY = this.y - other.y;
        return Math.sqrt(deltaX * deltaX + deltaY * deltaY);
    }

    /**
     * Equals boolean.
     *
     * @param other the other
     * @return the boolean
     */
// equals -- return true is the points are equal, false otherwise
    public boolean equals(Point other) {
        if ((other.x == this.x) && (other.y == this.x)) {
            return true;
        }
        return false;
    }

    /**
     * Gets x.
     *
     * @return the x
     */
// Return the x and y values of this point
    public double getX() {
        return this.x;
    }

    /**
     * Gets y.
     *
     * @return the y
     */
    public double getY() {
        return this.y;
    }
}
