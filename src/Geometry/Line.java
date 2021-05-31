package geometry;

import java.util.List;

/**
 * The type Geometry.Line.
 */
public class Line {
    private Point start;
    private Point end;

    /**
     * constructor from two points.
     *
     * @param start the first point that defines the line.
     * @param end   the second point that defines the line.
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    /**
     * constructor from four coordinates.
     *
     * @param x1 the x coordinate of the first point that defines the line.
     * @param y1 the y coordinate of the first point that defines the line.
     * @param x2 the x coordinate of the second point that defines the line.
     * @param y2 the y coordinate of the second point that defines the line.
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
    }

    /**
     * this method measures the length of this line.
     *
     * @return the length of this line.
     */
    public double length() {
        return this.start.distance(this.end);
    }

    /**
     * this method calculates the middle point of this line.
     *
     * @return the middle point of this line.
     */
    public Point middle() {
        double midX = (this.start.getX() + this.end.getX()) / 2;
        double midY = (this.start.getY() + this.end.getY()) / 2;
        Point middle = new Point(midX, midY);
        return middle;
    }

    /**
     * this method returns the first point that defines the line.
     *
     * @return the first point that defines the line.
     */
    public Point start() {
        return this.start;
    }

    /**
     * this method returns the second point that defines the line.
     *
     * @return the second point that defines the line.
     */
    public Point end() {
        return this.end;
    }

    /**
     * this method checks if this line containing a given point.
     *
     * @param point the point to check about.
     * @return true if the is in the line, false otherwise.
     */
    public boolean isContainingPoint(Point point) {
        double thisMaxX = Math.max(this.start.getX(), this.end.getX());
        double thisMinX = Math.min(this.start.getX(), this.end.getX());
        double thisMaxY = Math.max(this.start.getY(), this.end.getY());
        double thisMinY = Math.min(this.start.getY(), this.end.getY());
        return point.getX() >= thisMinX && point.getX() <= thisMaxX
                && point.getY() >= thisMinY && point.getY() <= thisMaxY;
    }

    /**
     * this method checks if two lines are intersecting.
     *
     * @param other the other line to check intersection with.
     * @return true if the two lines are intersecting, false otherwise.
     */
    public boolean isIntersecting(Line other) {
        double aOther = other.end.getY() - other.start.getY();
        double bOther = other.start.getX() - other.end.getX();
        double cOther = aOther * other.start.getX()
                + bOther * other.start.getY();
        double aThis = this.end.getY() - this.start.getY();
        double bThis = this.start.getX() - this.end.getX();
        double cThis = aThis * this.start.getX() + bThis * this.start.getY();
        double det = aThis * bOther - aOther * bThis;
        if (det == 0) {
            // Lines are parallel - no intersection
            return false;
        } else {
            double intersectionX = (bOther * cThis - bThis * cOther) / det;
            double intersectionY = (aThis * cOther - aOther * cThis) / det;
            Point intersection = new Point(intersectionX, intersectionY);
            return (this.isContainingPoint(intersection)
                    && other.isContainingPoint(intersection));
        }
    }

    /**
     * this method calculating intersection point of two lines.
     *
     * @param other the other line to check intersection with.
     * @return the intersection point if the lines intersect, null otherwise.
     */
    public Point intersectionWith(Line other) {
        double aOther = other.end.getY() - other.start.getY();
        double bOther = other.start.getX() - other.end.getX();
        double cOther = aOther * other.start.getX()
                + bOther * other.start.getY();
        double aThis = this.end.getY() - this.start.getY();
        double bThis = this.start.getX() - this.end.getX();
        double cThis = aThis * this.start.getX() + bThis * this.start.getY();
        double det = aThis * bOther - aOther * bThis;
        if (det == 0) {
            // Lines are parallel - no intersection
            return null;
        } else {
            double intersectionX = (bOther * cThis - bThis * cOther) / det;
            double intersectionY = (aThis * cOther - aOther * cThis) / det;
            Point intersection =
                    new Point(intersectionX, intersectionY);
            if (this.isContainingPoint(intersection)
                    && other.isContainingPoint(intersection)) {
                return intersection;
            } else {
                return null;
            }
        }
    }

    /**
     * this method checks if two lines are the same.
     *
     * @param other another line.
     * @return true if the lines are equal, false otherwise.
     */
    public boolean equals(Line other) {
        return ((this.start.equals(other.start) && this.end.equals(other.end))
                || (this.start.equals(other.end) && this.end.equals(other.start)));
    }

    /**
     * This method checks if a point i between this line x values.
     *
     * @param p is the point.
     * @return true if its between the values false otherwise
     */
    public boolean isBetweenX(Point p) {
        double xMin = Math.min(this.start.getX(), this.end.getX());
        double xMax = Math.max(this.start.getX(), this.end.getX());
        return (p.getX() <= xMax + 0.0001 && p.getX() >= xMin - 0.0001);
    }

    /**
     * This method checks if a point is between  this line y values.
     *
     * @param p is the point.
     * @return true if its between the values false otherwise
     */
    public boolean isBetweenY(Point p) {
        double yMin = Math.min(this.start.getY(), this.end.getY());
        double yMax = Math.max(this.start.getY(), this.end.getY());
        return (p.getY() <= yMax + 0.0001 && p.getY() >= yMin - 0.0001);
    }

    /**
     * Closest intersection to start of line point.
     *
     * @param rect the rect
     * @return the point
     */
// If this line does not intersect with the rectangle, return null.
    // Otherwise, return the closest intersection point to the
    // start of the line.
    public Point closestIntersectionToStartOfLine(Rectangle rect) {

        List<Point> intrsPoints = rect.intersectionPoints(this);
        if (!intrsPoints.isEmpty()) {
            if (intrsPoints.size() == 1) {
                return intrsPoints.get(0);
            } else {
                if (intrsPoints.size() == 2) {
                    double a = this.start.distance(intrsPoints.get(0));
                    double b = this.start.distance(intrsPoints.get(1));
                    if (a > b) {
                        return intrsPoints.get(1);
                    } else {
                        return intrsPoints.get(0);
                    }
                } else {
                    for (Point point:intrsPoints) {
                        double a = this.start.distance(intrsPoints.get(0));
                        double b = this.start.distance(point);
                        Point closest = intrsPoints.get(0);
                        if (a > b) {
                            closest = point;
                        }
                        return closest;
                    }
                }
            }
        }
        return null;
    }
}
