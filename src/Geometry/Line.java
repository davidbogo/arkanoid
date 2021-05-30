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
     * Gets intersection point.
     *
     * @param other the other
     * @return the intersection point
     */
    Point getIntersectionPoint(Line other) {
        double otherXStart = other.start().getX();
        double otherYStart = other.start().getY();
        double otherXEnd = other.end().getX();
        double otherYEnd = other.end().getY();
        x1 = start().getX();
        x2 = end().getX();
        y1 = start().getY();
        y2 = end().getY();

        if (Math.min(otherXStart, otherXEnd) > Math.max(x1, x2)
            || Math.max(otherXStart, otherXEnd) < Math.min(x1, x2)
            || Math.min(otherYStart, otherYEnd) > Math.max(y1, y2)
            || Math.max(otherYStart, otherYEnd) < Math.min(y1, y2)) {
            return null;
        }
        double intersectX = 0.0;
        double intersectY = 0.0;
        double nLine1 = 0.0;
        double nLine2 = 0;
        double inclineLine1 = 0;
        double inclineLine2 = 0;
        if (x1 == x2) {
            intersectX = x1;
            inclineLine2 = ((otherYStart - otherYEnd) / (otherXStart - otherXEnd));
            nLine2 = (otherYStart - ((inclineLine2) * otherXStart));
            intersectY = ((intersectX * inclineLine2) + nLine2);
        } else if (otherXEnd == otherXStart) {
            inclineLine1 = ((y1 - y2) / (x1 - x2));
            nLine1 = (y1 - ((inclineLine1) * x1));
            intersectX = otherXEnd;
            intersectY = ((intersectX * inclineLine1) + nLine1);
        } else {
            inclineLine1 = ((y1 - y2) / (x1 - x2));
            inclineLine2 = ((otherYStart - otherYEnd) / (otherXStart - otherXEnd));
            nLine1 = (y1 - ((inclineLine1) * x1));
            nLine2 = (otherYStart - ((inclineLine2) * otherXStart));
            intersectX = ((nLine2 - nLine1) / (inclineLine1 - inclineLine2));
            intersectY = ((intersectX * inclineLine1) + nLine1);
            if (x1 > intersectX && x2 > intersectX
                    || x1 < intersectX && x2 < intersectX
                    || y1 > intersectY && y2 > intersectY
                    || y1 < intersectY && y2 < intersectY
                    || otherXStart > intersectX && otherXEnd > intersectX
                    || otherXStart < intersectX && otherXEnd < intersectX
                    || otherYStart > intersectY && otherYEnd > intersectY
                    || otherYStart < intersectY && otherYEnd < intersectY) {
                return null;
            }
        }
        return new Point(intersectX, intersectY);
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
        Line upLine = new Line(rect.getUpperLeft(), rect.getUpperRight());
        Line downLine = new Line(rect.getLowerLeft(), rect.getLowerRight());
        Line leftLine = new Line(rect.getUpperLeft(), rect.getLowerLeft());
        Line rightLine = new Line(rect.getUpperRight(), rect.getLowerRight());
        Point winnerUpOrDown;
        Point winnerLeftOrRight;
        if (intersectionWith(upLine).distance(start) < intersectionWith(downLine).distance(start)) {
            winnerUpOrDown = intersectionWith(upLine);
        } else if (intersectionWith(upLine).distance(start) > intersectionWith(downLine).distance(start)) {
            winnerUpOrDown = intersectionWith(downLine);
        } else {
            if (isIntersecting(upLine)) {
                winnerUpOrDown = intersectionWith(upLine);
            } else {
                winnerUpOrDown = null;
            }
        }
            if (intersectionWith(leftLine).distance(start) < intersectionWith(rightLine).distance(start)) {
                winnerLeftOrRight = intersectionWith(leftLine);
            } else if (intersectionWith(leftLine).distance(start) > intersectionWith(rightLine).distance(start)) {
                winnerLeftOrRight = intersectionWith(rightLine);
            } else {
                if (isIntersecting(leftLine)) {
                    winnerLeftOrRight = intersectionWith(leftLine);
                } else {
                    winnerLeftOrRight = null;
                }
            }
            if (winnerLeftOrRight != null && winnerUpOrDown != null) {
                if (start.distance(winnerLeftOrRight) < start.distance(winnerUpOrDown)) {
                    return winnerLeftOrRight;
                } else if (start.distance(winnerLeftOrRight) > start.distance(winnerUpOrDown)) {
                    return winnerUpOrDown;
                } else {
                    return winnerLeftOrRight;
                }
            } else if (winnerLeftOrRight == null && winnerUpOrDown != null) {
                return winnerUpOrDown;
            } else if (winnerLeftOrRight != null && winnerUpOrDown == null) {
                return winnerLeftOrRight;
            } else {
                return null;
            }
    }

    /**
     * Paddle is containing point boolean.
     *
     * @param point the point
     * @return the boolean
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

}
