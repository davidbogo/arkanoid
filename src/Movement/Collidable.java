package movement;

import gameelements.Ball;
import geometry.Point;
import geometry.Rectangle;

/**
 * The interface Movement.Collidable.
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
     *
     * @param hitter            the object that hit the collidable
     * @param collisionPoint    the collision point
     * @param currentVelocity   the current velocity
     * @return the velocity
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}
