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
     *
     * @param dx the change in position on the x axis.
     * @param dy the change in position on the y axis.
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * "constructor" - construct Movement.Velocity object from Polar representation.
     *
     * @param angle the angle of the vector.
     * @param speed the length of the vector.
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
     *
     * @return the velocity's change in position on the x axis.
     */
    public double getDx() {
        return this.dx;
    }

    /**
     * this method returns the velocity's change in position on the y axis.
     *
     * @return the velocity's change in position on the y axis.
     */
    public double getDy() {
        return this.dy;
    }

    /**
     * this method returns the "speed" (length) from the
     * polar representation of this velocity.
     *
     * @return the "speed" (length) from the polar representation of this velocity.
     */
    public double getSpeed() {
        return Math.sqrt(this.dx * this.dx + this.dy * this.dy);
    }

    /**
     * this method moves a point in two dimensions.
     *
     * @param p a point with position (x,y).
     * @return a new point with position (x+dx, y+dy).
     */
    public Point applyToPoint(Point p) {
        double newX = p.getX() + this.dx;
        double newY = p.getY() + this.dy;
        return new Point(newX, newY);
    }
}
