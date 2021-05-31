package miscellaneous;

import gameelements.Ball;
import geometry.Line;
import geometry.Point;
import movement.Collidable;
import movement.CollisionInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * The type Game environment.
 */
public class GameEnvironment {
    private java.util.List<Collidable> collidables;

    /**
     * Instantiates a new Game environment.
     */
    public GameEnvironment() {
        collidables = new ArrayList<Collidable>();
    }

    /**
     * Add collidable.
     *
     * @param c the c
     */
// add the given collidable to the environment.
    public void addCollidable(Collidable c) {
        collidables.add(c);
    }

    /**
     * Gets closest collision.
     *
     * @param trajectory the trajectory
     * @return the closest collision
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        java.util.List<Point> collisions = new ArrayList<Point>();
        java.util.List<Collidable> collideObjects = new ArrayList<Collidable>();
        java.util.List<Point> intersections;
        for (int i = 0; i < collidables.size(); i++) {
            intersections = collidables.get(i).getCollisionRectangle().intersectionPoints(trajectory);
            if (intersections != null) {
                if (intersections.size() != 0) {
                    for (int j = 0; j < intersections.size(); j++) {
                        collisions.add(intersections.get(j));
                        collideObjects.add(collidables.get(i));
                    }
                }
            }
        }
        if (collisions.isEmpty()) {
            return null;
        }
        Point closestCollision = collisions.get(0);
        Collidable closestCollideObject = collideObjects.get(0);
        for (int i = 1; i < collisions.size(); i++) {
            if (trajectory.start().distance(collisions.get(i)) < trajectory.start().distance(closestCollision)) {
                closestCollision = collisions.get(i);
                closestCollideObject = collideObjects.get(i);
            }
        }
        return new CollisionInfo(closestCollision, closestCollideObject);
    }

    /**
     * Gets collidables.
     *
     * @return the collidables
     */
    public List<Collidable> getCollidables() {
        return collidables;
    }

    /**
     * this method removes a given collidable object from the collection.
     * @param c the given collidable object.
     */
    public void removeCollidable(Collidable c) {
        this.collidables.remove(c);
    }
}