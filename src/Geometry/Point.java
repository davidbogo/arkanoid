package geometry;

/**
 * The type Point.
 */
public class Point {
    private double x;
    private double y;

    /**
     * Instantiates a new Point.
     *
     * @param xParam the x
     * @param yParam the y
     */
// constructor
    public Point(double xParam, double yParam) {
        x = xParam;
        y = yParam;
    }

    /**
     * Distance double.
     *
     * @param other the other
     * @return the double
     */
// distance -- return the distance of this point to the other point
    public double distance(Point other) {
       double distanceS = (other.getX() - getX()) * (other.getX() - getX()) +
                          (other.getY() - getY()) * (other.getY() - getY());
       return Math.sqrt(distanceS);
    }

    /**
     * Equals boolean.
     *
     * @param other the other
     * @return the boolean
     */
// equals -- return true is the points are equal, false otherwise
    public boolean equals(Point other) {
        if ((other.getX() == getX()) && (other.getY() == getY())) {
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
        return x;
    }

    /**
     * Gets y.
     *
     * @return the y
     */
    public double getY() {
        return y;
    }
}
