/**
 * The type Point.
 */
public class Point {
    private double x;
    private double y;
    private double distance;

    /**
     * Instantiates a new Point.
     *
     * @param x1 the x 1
     * @param y1 the y 1
     */
// constructor
    public Point(double x1, double y1) {
        x = x1;
        y = y1;
        distance = 0;
    }

    /**
     * Distance double.
     *
     * @param other the other
     * @return the double
     */
// distance -- return the distance of this point to the other point
    public double distance(Point other) {
       double distanceS =  (other.getX() - getX()) * (other.getX() - getX())
               + (other.getY() - getY()) * (other.getY() - getY());
       distance = Math.sqrt(distanceS);
        return distance;
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
