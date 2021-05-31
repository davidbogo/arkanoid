package movement;

import geometry.Point;
import geometry.Line;
import movement.CollisionInfo;
import movement.Collidable;

/**
 * The type Movement.Velocity
 */
// Velocity specifies the change in position on the `x` and the `y` axes.
public class Velocity {
    private double horizontalSpeed;
    private double verticalSpeed;

    /**
     * Instantiates a new Velocity.
     *
     * @param dx the dx speed
     * @param dy the dy speed
     */
// constructor
    public Velocity(double dx, double dy) {
        horizontalSpeed = dx;
        verticalSpeed = dy;
    }

    /**
     * Gets horizontal speed.
     *
     * @return the horizontal speed
     */
    public double getHorizontalSpeed() {
        return horizontalSpeed;
    }

    /**
     * Gets vertical speed.
     *
     * @return the vertical speed
     */
    public double getVerticalSpeed() {
        return verticalSpeed;
    }

    /**
     * Apply to point point.
     *
     * @param p the point
     * @return the new point location
     */
// Take a point with position (x,y) and return a new point
    // with position (x+dx, y+dy)
    public Point applyToPoint(Point p) {
        double newX = p.getX() + horizontalSpeed;
        double newY = p.getY() + verticalSpeed;
        return new Point(newX, newY);
    }

    /**
     * From angle and speed velocity.
     *
     * @param angle the angle
     * @param speed the speed
     * @return the velocity in x and y speed
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double angleRad = Math.toRadians(angle - 90.0);
        double dx = Math.cos(angleRad) * speed;
        double dy = Math.sin(angleRad) * speed;
        return new Velocity(dx, dy);
    }

    /**
     * Gets speed.
     *
     * @return the speed
     */
    public double getSpeed() {
        return Math.sqrt(horizontalSpeed * horizontalSpeed + verticalSpeed * verticalSpeed);
    }
}
