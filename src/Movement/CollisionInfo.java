package movement;

import geometry.Point;

/**
 * The type Collision info.
 */
public class CollisionInfo {
    private Point collisionPoint;
    private Collidable collisionObject;

    /**
     * construct a collisionInfo object from
     * collision point and collision object.
     *
     * @param collisionPoint  the collision point.
     * @param collisionObject the collision object.
     */
    public CollisionInfo(Point collisionPoint, Collidable collisionObject) {
        this.collisionPoint = collisionPoint;
        this.collisionObject = collisionObject;
    }
    // the point at which the collision occurs.

    /**
     * this method returns the collision point from this collisionInfo.
     *
     * @return the collision point from this collisionInfo.
     */
    public Point collisionPoint() {
        return this.collisionPoint;
    }

    /**
     * this method returns the collision object from this collisionInfo.
     *
     * @return the collision object from this collisionInfo.
     */
    public Collidable collisionObject() {
        return this.collisionObject;
    }
}
