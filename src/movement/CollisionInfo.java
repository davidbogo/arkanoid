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
     * @param colPoint      the collision point.
     * @param colObj        the collision object.
     */
    public CollisionInfo(Point colPoint, Collidable colObj) {
        collisionPoint = colPoint;
        collisionObject = colObj;
    }

    /**
     * this method returns the collision point from this collisionInfo.
     *
     * @return the collision point from this collisionInfo.
     */
    public Point collisionPoint() {
        return collisionPoint;
    }

    /**
     * this method returns the collision object from this collisionInfo.
     *
     * @return the collision object from this collisionInfo.
     */
    public Collidable collisionObject() {
        return collisionObject;
    }
}
