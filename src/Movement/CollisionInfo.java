package movement;

import geometry.Point;

/**
 * The type Collision info.
 */
public class CollisionInfo {
    private Point collisionPoint;
    private Collidable collisionObject;

    /**
     * Instantiates a new Collision info.
     *
     * @param colPoint  the col point
     * @param colObject the col object
     */
    public CollisionInfo(Point colPoint, Collidable colObject) {
        collisionPoint = colPoint;
        collisionObject = colObject;
    }

    /**
     * Collision point point.
     *
     * @return the point
     */
    public Point collisionPoint() {
        return collisionPoint;
    }
    /**
     * Collision object collidable.
     *
     * @return the collidable
     */
    public Collidable collisionObject() {
        return collisionObject;
    }
}