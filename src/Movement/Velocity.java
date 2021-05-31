package movement;

import geometry.Point;

/**
 * this class represents a Movement.Velocity by specifying
 * the change in position on the `x` and the `y` axes.
 */
public class Velocity {
    private double dx;
    private double dy;
    /**
     * construct Movement.Velocity object from Cartesian representation.
     * @param dxParam   the change in position on the x axis.
     * @param dyParam   the change in position on the y axis.
     */
    public Velocity(double dxParam, double dyParam) {
        dx = dxParam;
        dy = dyParam;
    }

    /**
     * "constructor" - construct Movement.Velocity object from Polar representation.
     * @param angle     the angle of the vector.
     * @param speed     the length of the vector.
     * @return Movement.Velocity object.
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double angleRad = Math.toRadians(angle - 90.0);
        double dx = Math.cos(angleRad) * speed;
        double dy = Math.sin(angleRad) * speed;
        return new Velocity(dx, dy);
    }

    /**
     * this method returns the velocity's change in position on the x axis.
     * @return the velocity's change in position on the x axis.
     */
    public double getDx() {
        return dx;
    }

    /**
     * this method returns the velocity's change in position on the y axis.
     * @return the velocity's change in position on the y axis.
     */
    public double getDy() {
        return dy;
    }

    /**
     * this method returns the "speed" (length) from the
     * polar representation of this velocity.
     * @return the "speed" (length) from the
     * polar representation of this velocity.
     */
    public double getSpeed() {
        return Math.sqrt(dx * dx + dy * dy);
    }

    /**
     * this method moves a point in two dimensions.
     * @param p     a point with position (x,y).
     * @return      the new point with position (x+dx, y+dy).
     */
    public Point applyToPoint(Point p) {
        double newX = p.getX() + dx;
        double newY = p.getY() + dy;
        return new Point(newX, newY);
    }
}
