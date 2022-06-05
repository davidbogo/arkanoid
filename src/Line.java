/**
 * The type Line.
 */
public class Line {
    private Point start;
    private Point end;
    private double x1;
    private double x2;
    private double y1;
    private double y2;
    private Point middle;

    /**
     * Instantiates a new Line.
     *
     * @param start1 the start 1
     * @param end1   the end 1
     */
// constructors
    public Line(Point start1, Point end1) {
        start = start1;
        end = end1;
        x1 = start.getX();
        x2 = end.getX();
        y1 = start.getY();
        y2 = end.getY();
        middle = middle();
    }

    /**
     * Instantiates a new Line.
     *
     * @param x1a the x 1 a
     * @param y1a the y 1 a
     * @param x2a the x 2 a
     * @param y2a the y 2 a
     */
    public Line(double x1a, double y1a, double x2a, double y2a) {
        x1 = x1a;
        x2 = x2a;
        y1 = y1a;
        y2 = y2a;
        start = new Point(x1, y1);
        end = new  Point(x2, y2);
        middle = middle();
    }

    /**
     * Length double.
     *
     * @return the double
     */
// Return the length of the line
    public double length() {
       double length = start.distance(end);
       return length;
    }

    /**
     * Middle point.
     *
     * @return the point
     */
// Returns the middle point of the line
    public Point middle() {
        double xMiddle = ((start.getX() + end.getX()) / 2);
        double yMiddle = ((start.getY() + end.getY()) / 2);
        middle = new Point(xMiddle, yMiddle);
        return middle;
    }

    /**
     * Start point.
     *
     * @return the point
     */
// Returns the start point of the line
    public Point start() {
        return start;
    }

    /**
     * End point.
     *
     * @return the point
     */
// Returns the end point of the line
    public Point end() {
        return end;
    }

    /**
     * Is intersecting boolean.
     *
     * @param other the other
     * @return the boolean
     */
// Returns true if the lines intersect, false otherwise
    /*
    the idea is that if the lines intersect somewhere between the end and the start of the line
    there won't be situations where either Y or X range
    of the other line would be smaller or bigger than the range of the
    original line (range is the X and Y between start and end).
     */

    /**
     * Is intersecting boolean.
     *
     * @param other for Line
     * @return boolean boolean
     */
    public boolean isIntersecting(Line other) {
        if (getIntersectionPoint(other) == null) {
            return false;
        }
        return true;
    }

    /**
     * this method calculating intersection point of two lines.
     *
     * @param other the other line to check intersection with.
     * @return the intersection point if the lines intersect, null otherwise.
     */
    public Point getIntersectionPoint(Line other) {
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
            if (isInBounds(intersection)
                    && other.isInBounds(intersection)) {
                return intersection;
            } else {
                return null;
            }
        }
    }

    /**
     * Intersection with point.
     *
     * @param other the other
     * @return the point
     */
// Returns the intersection point if the lines intersect,
    // and null otherwise.
    public Point intersectionWith(Line other) {
        if (isIntersecting(other)) {
            return getIntersectionPoint(other);
        }
        return null;
    }

    /**
     * Equals boolean.
     *
     * @param other the other
     * @return the boolean
     */
// equals -- return true is the lines are equal, false otherwise
    public boolean equals(Line other) {
        if ((other.start().getX() == x1) && (other.end().getX() == x2)
            && (other.start().getY() == y1) && (other.end().getY() == y2)) {
            return true;
        }
        return false;
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
    Point[] point = new Point[4];
    Point closest;
    point[0] = intersectionWith(rect.getUpLine());
    point[1] = intersectionWith(rect.getDownLine());
    point[2] = intersectionWith(rect.getLeftLine());
    point[3] = intersectionWith(rect.getRightLine());
    int intersectionCounter = 0;
    for (int i = 0; i < 4; ++i) {
        if (point[i] != null) {
            intersectionCounter++;
        }
    }
    if (intersectionCounter == 0) {
        return null;
    } else {
        int i = 0;
        while (point[i] == null) {
            i++;
        }
        closest = point[i];
        if (intersectionCounter == 1) {
            return closest;
        } else {
            while (point[i] != null && i < 4) {
                if (closest.distance(start)
                        > point[i].distance(start)) {
                    closest = point[i];
                }
                i++;
            }
        }
    }
    return closest;
}

    /**
     * Paddle is containing point boolean.
     *
     * @param point the point
     * @return the boolean of whether the paddle is being hit
     */
    public boolean paddleIsContainingPoint(Point point) {
        if (point == null) {
            return false;
        }
        Point abovePaddle = new Point(start.getX(), start.getY() - 100);
        Line temporaryLine = new Line(abovePaddle, point);
        Point intersectionPoint = temporaryLine.intersectionWith(this);
        if (intersectionPoint != null) {
            if (intersectionPoint.getY() == point.getY()) {
                if (temporaryLine.intersectionWith(this).getX() == point.getX()) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Is in bounds boolean.
     *
     * @param point the point
     * @return the boolean
     */
    public boolean isInBounds(Point point) {
        double thisMaxX = Math.max(start.getX(), end.getX());
        double thisMinX = Math.min(start.getX(), end.getX());
        double thisMaxY = Math.max(start.getY(), end.getY());
        double thisMinY = Math.min(start.getY(), end.getY());
        return point.getX() >= thisMinX && point.getX() <= thisMaxX
                && point.getY() >= thisMinY && point.getY() <= thisMaxY;
    }

    /**
     * Is containing point boolean.
     *
     * @param point the point
     * @return the boolean
     */
    public boolean isContainingPoint(Point point) {
        if (point == null) {
            return false;
        }
        Line tmpLine = new Line(start, point);
        Point tmpPoint = tmpLine.intersectionWith(this);
        return tmpPoint == null && isInBounds(point);
    }

}
