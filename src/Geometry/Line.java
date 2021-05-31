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
     * @param startParam    the first point that defines the line.
     * @param endParam      the second point that defines the line.
     */
    public Line(Point startParam, Point endParam) {
        start = startParam;
        end = endParam;
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
        start = new Point(x1, y1);
        end = new Point(x2, y2);
    }

    /**
     * this method measures the length of this line.
     *
     * @return the length of this line.
     */
    public double length() {
        return start.distance(end);
    }

    /**
     * this method calculates the middle point of this line.
     *
     * @return the middle point of this line.
     */
    public Point middle() {
        double midX = (start.getX() + end.getX()) / 2;
        double midY = (start.getY() + end.getY()) / 2;
        return new Point(midX, midY);
    }

    /**
     * this method returns the first point that defines the line.
     *
     * @return the first point that defines the line.
     */
    public Point start() {
        return start;
    }

    /**
     * this method returns the second point that defines the line.
     *
     * @return the second point that defines the line.
     */
    public Point end() {
        return end;
    }

    /**
     * this method checks if this line contains a given point.
     *
     * @param point the point to check about.
     * @return true if the is in the line, false otherwise.
     */
    public boolean isContainingPoint(Point point) {
        double lineMaxX = Math.max(start.getX(), end.getX());
        double lineMinX = Math.min(start.getX(), end.getX());
        double lineMaxY = Math.max(start.getY(), end.getY());
        double lineMinY = Math.min(start.getY(), end.getY());
        return (point.getX() >= lineMinX) && (point.getX() <= lineMaxX) &&
               (point.getY() >= lineMinY) && (point.getY() <= lineMaxY);
    }

    /**
     * this method calculates intersection point of two lines.
     *
     * @param other     the other line to check intersection with.
     * @return the intersection point if the lines intersect, null otherwise.
     */
    public Point intersectionWith(Line other) {
        double aOther = other.end.getY() - other.start.getY();
        double bOther = other.start.getX() - other.end.getX();
        double cOther = aOther * other.start.getX()
                + bOther * other.start.getY();
        double aThis = end.getY() - start.getY();
        double bThis = start.getX() - end.getX();
        double cThis = aThis * start.getX() + bThis * start.getY();
        double det = aThis * bOther - aOther * bThis;
        if (det == 0) {
            // Lines are parallel - no intersection
            return null;
        } else {
            double intersectionX = (bOther * cThis - bThis * cOther) / det;
            double intersectionY = (aThis * cOther - aOther * cThis) / det;
            Point intersection =
                    new Point(intersectionX, intersectionY);
            if (isContainingPoint(intersection) && other.isContainingPoint(intersection)) {
                return intersection;
            }
            return null;
        }
    }

    /**
     * this method checks if two lines intersect.
     *
     * @param other the other line to check intersection with.
     * @return true if the two lines are intersecting, false otherwise.
     */
    public boolean isIntersecting(Line other) {
        return (intersectionWith(other) == null) ? false : true;
    }

    /**
     * this method checks if two lines are the same.
     *
     * @param other another line.
     * @return true if the lines are equal, false otherwise.
     */
    public boolean equals(Line other) {
        return ((start.equals(other.start) && end.equals(other.end)) ||
                (start.equals(other.end) && end.equals(other.start)));
    }

    /**
     * This method checks if a point i between this line x values.
     *
     * @param p is the point.
     * @return true if its between the values false otherwise
     */
    public boolean isBetweenX(Point p) {
        double xMin = Math.min(start.getX(), end.getX());
        double xMax = Math.max(start.getX(), end.getX());
        return (p.getX() <= xMax + 0.0001 && p.getX() >= xMin - 0.0001);
    }

    /**
     * This method checks if a point is between  this line y values.
     *
     * @param p is the point.
     * @return true if its between the values false otherwise
     */
    public boolean isBetweenY(Point p) {
        double yMin = Math.min(start.getY(), end.getY());
        double yMax = Math.max(start.getY(), end.getY());
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
        List<Point> intersectionPoints = rect.intersectionPoints(this);
        if (!intersectionPoints.isEmpty()) {
            Point closest = intersectionPoints.get(0);
            double minDist = start.distance(closest);
            for (int curIntersection = 1; curIntersection < intersectionPoints.size(); curIntersection++) {
                Point curPoint = intersectionPoints.get(curIntersection);
                double curDist = start.distance(curPoint);
                if (curDist < minDist) {
                    closest = curPoint;
                    minDist = curDist;
                }
            }
            return closest;
        }
        return null;
    }
}
