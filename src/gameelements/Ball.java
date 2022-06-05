package gameelements;

import geometry.Line;
import geometry.Point;
import levels.GameLevel;
import miscellaneous.GameEnvironment;
import miscellaneous.Sprite;
import movement.CollisionInfo;
import movement.Velocity;
import biuoop.DrawSurface;


import java.awt.Color;

/**
 * this class represents a ball.
 */
public class Ball implements Sprite {
    private Point center;
    private int radius;
    private Color color;
    private Velocity velocity;
    private boolean isBounded;
    private int horizontalBound;
    private int verticalBound;
    private GameEnvironment gameEnvironment;
    private long lastStuckTime;
    private long beforeLastStuckTime;

    /**
     * construct bounded ball from two coordinates, radius and color.
     *
     * @param x         the x coordinate of the initial location of the ball's center.
     * @param y         the y coordinate of the initial location of the ball's center.
     * @param r         the ball's radius.
     * @param col       the ball's color.
     * @param hBound    horizontal Bound of the ball.
     * @param vBound    vertical Bound of the ball.
     * @param gameEnv   the game environment
     */
    public Ball(int x, int y, int r, Color col,
                int hBound, int vBound, GameEnvironment gameEnv) {
        center = new Point((double) x, (double) y);
        radius = r;
        color = col;
        velocity = new Velocity(0, 0);
        isBounded = true;
        verticalBound = vBound;
        horizontalBound = hBound;
        gameEnvironment = gameEnv;
        lastStuckTime = 0;
        beforeLastStuckTime = 0;
    }

    /**
     * this method returns the rounded x coordinate of the ball's center.
     *
     * @return the rounded x coordinate of the ball's center.
     */
    public int getX() {
        return (int) Math.round(center.getX());
    }

    /**
     * this method returns the rounded y coordinate of the ball's center.
     *
     * @return the rounded y coordinate of the ball's center.
     */
    public int getY() {
        return (int) Math.round(center.getY());
    }

    /**
     * this method returns the ball's radius.
     *
     * @return the ball's radius.
     */
    public int getSize() {
        return radius;
    }

    /**
     * this method returns the ball's color.
     * @return the ball's color.
     */
    /**
     * this method returns the ball's center point.
     *
     * @return the ball's center point.
     */
    public Point getCenter() {
        return center;
    }

    /**
     * Gets color.
     *
     * @return the color
     */
    public Color getColor() {
        return color;
    }

    /**
     * this method draws the ball on given DrawSurface.
     *
     * @param surface the DrawSurface to draw on.
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(color);
        surface.fillCircle(getX(), getY(), radius);
        surface.setColor(Color.BLACK);
        surface.drawCircle(getX(), getY(), radius);
    }

    /**
     * this method sets the ball's velocity.
     *
     * @param v Movement.Velocity object to apply on the ball.
     */
    public void setVelocity(Velocity v) {
        velocity = v;
    }

    /**
     * this method sets a new center for the ball.
     *
     * @param x the x coordinate for the new center.
     * @param y the y coordinate for the new center.
     */
    public void setCenter(double x, double y) {
        center = new Point(x, y);
    }

    /**
     * Sets velocity.
     *
     * @param dx the dx
     * @param dy the dy
     */
    public void setVelocity(double dx, double dy) {
        velocity = new Velocity(dx, dy);
    }

    /**
     * this method returns the ball's velocity.
     *
     * @return the ball's velocity.
     */
    public Velocity getVelocity() {
        return velocity;
    }

    /**
     * Gets environment.
     *
     * @return the environment
     */
    public GameEnvironment getEnvironment() {
        return gameEnvironment;
    }

    /**
     * Trajectory line.
     *
     * @return the line
     */
    public Line trajectory() {
        Point startOfTrajectory = new Point(
                Math.floor(center.getX()), Math.floor(center.getY()));
        Point endOfTrajectory = new Point(
                Math.floor(center.getX() + velocity.getDx()),
                Math.floor(center.getY() + velocity.getDy()));
        return new Line(startOfTrajectory, endOfTrajectory);
    }

    /**
     * this method moves the ball's center according to it's velocity.
     */
    public void moveOneStep() {
        CollisionInfo closestCollision = gameEnvironment.getClosestCollision(trajectory());
        if (closestCollision != null) {
            if (closestCollision.collisionObject() != null && closestCollision.collisionPoint() != null) {
                Velocity newVelocity = closestCollision.collisionObject().hit(
                        this,
                        closestCollision.collisionPoint(),
                        velocity);
                setVelocity(newVelocity);
            }
        }
        center = velocity.applyToPoint(center);
    }

    /**
     * this method notifies the ball that a time unit has passed.
     */
    public void timePassed() {
        moveOneStep();
        gameEnvironment.checkForStuckBall(this);
    }

    /**
     * this method adds the ball to the game's sprite list.
     *
     * @param game the game that is currently played.
     */
    public void addToGame(GameLevel game) {
        game.addSprite(this);
    }

    /**
     * this method removes the ball from the game's sprite list.
     * @param game the game that is currently played.
     */
    public void removeFromGame(GameLevel game) {
        game.removeSprite(this);
    }

    /**
     * This method registers the time the ball got stuck and decides whether we can try to unstuck it by sending it
     * back from the current location to where it cam from or need to randomly move it to a new location.
     *
     * @return true if the ball needs to be moved to a new randomized location
     */
    public boolean registerStuckCondition() {
        boolean needToRandomize = false;
        long curTime = System.currentTimeMillis();
        if (beforeLastStuckTime != 0) {
            if (curTime - beforeLastStuckTime < 1000) {
                // This is the third time in less than a second that the ball got stuck.
                // We'll request the caller to move it to a new randomized location
                beforeLastStuckTime = 0;
                lastStuckTime = 0;
                needToRandomize = true;
            } else {
                beforeLastStuckTime = lastStuckTime;
                lastStuckTime = curTime;
            }
        } else if (lastStuckTime != 0) {
            if (curTime - lastStuckTime < 1000) {
                // This is the second time in less than a second that the ball got stuck.
                // We still treat this situation as recoverable
                beforeLastStuckTime = lastStuckTime;
            } else {
                beforeLastStuckTime = 0;
            }
            lastStuckTime = curTime;
        } else {
            lastStuckTime = curTime;
        }
        return needToRandomize;
    }
}