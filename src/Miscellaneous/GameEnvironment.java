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
 * The type Miscellaneous.Game environment.
 */
public class GameEnvironment {
    private List<Collidable> collidableList;

    /**
     * construct a game environment from a list of collidables.
     */
    public GameEnvironment() {
        collidableList = new ArrayList<Collidable>();
    }

    /**
     * this method adds a given collidable object to the collection.
     *
     * @param c the given collidable object.
     */
    public void addCollidable(Collidable c) {
        collidableList.add(c);
    }

    /**
     * this method gets a trajectory line and
     * returns the closest collision information
     * (collision point & collision object).
     * if there is no collision, returns null.
     *
     * @param trajectory the given line.
     * @return collision information, null if there isn't any.
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        if (collidableList.isEmpty()) {
            return null;
        }
        int i = 0;
        while ((trajectory.closestIntersectionToStartOfLine(collidableList.get(i).getCollisionRectangle()) == null) &&
               (i < collidableList.size() - 1)) {
            i++;
        }
        Point closest = trajectory.closestIntersectionToStartOfLine(collidableList.get(i).getCollisionRectangle());
        int collisionIndex = i;
        for (int j = i; j < collidableList.size(); ++j) {
            if (trajectory.closestIntersectionToStartOfLine(
                    collidableList.get(j).getCollisionRectangle()) != null) {
                if (closest.distance(trajectory.start())
                        > trajectory.closestIntersectionToStartOfLine(
                        collidableList.get(j).getCollisionRectangle()).
                        distance(trajectory.start())) {
                    closest = trajectory.closestIntersectionToStartOfLine(
                            collidableList.get(j).getCollisionRectangle());
                    collisionIndex = j;
                }
            }
        }
        return new CollisionInfo(closest, collidableList.get(collisionIndex));
    }

    /**
     * this method gets a ball and checks if it got stuck in
     * one of the collidable objects. if so, resets it's location
     * to the bottom of the screen.
     *
     * @param ball the given ball.
     */
    public void checkForStuckBall(Ball ball) {
        Random random = new Random();
        if (collidableList.isEmpty()) {
            return;
        }
        for (int i = 0; i < collidableList.size(); i++) {
            if (collidableList.get(i).getCollisionRectangle().isContainPoint(ball.getCenter())) {
                ball.setCenter(350 + random.nextInt(100), 560);
            }
        }
    }
    /**
     * this method removes a given collidable object from the collection.
     * @param c the given collidable object.
     */
    public void removeCollidable(Collidable c) {
        collidableList.remove(c);
    }
}
