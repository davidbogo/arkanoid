package movement;

import gameelements.Ball;
import geometry.Point;
import geometry.Rectangle;

/**
 * The interface movement.Collidable.
 */
public interface Collidable {
    /**
     * Gets collision rectangle.
     *
     * @return the collision rectangle
     */
    Rectangle getCollisionRectangle();

    /**
     * Hit velocity.
     * Notify the object that we collided with at collisionPoint with
     * a given velocity.
     *
     * @param hitter the hitting ball
     * @param collisionPoint  the collision point
     * @param currentVelocity the current velocity
     * @return the new velocity after the hit
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}
