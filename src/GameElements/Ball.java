package gameelements;

import geometry.Line;
import geometry.Point;
import miscellaneous.Game;
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
    private GameEnvironment gameEnvironment;

    /**
     * construct bounded ball from two coordinates, radius and color.
     *
     * @param x                 the x coordinate of the initial location of the ball's center.
     * @param y                 the y coordinate of the initial location of the ball's center.
     * @param r                 the ball's radius.
     * @param col               the ball's color.
     * @param gameEnv           the game environment
     */
    public Ball(int x, int y, int r, Color col, GameEnvironment gameEnv) {
        center = new Point((double) x, (double) y);
        radius = r;
        color = col;
        velocity = new Velocity(0, 0);
        gameEnvironment = gameEnv;
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
     * this method sets the ball's velocity.
     * @param dx the change in position on the x axis.
     * @param dy the change in position on the y axis.
     */
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
    public void addToGame(Game game) {
        game.addSprite(this);
    }
    /**
     * this method removes the ball from the game's sprite list.
     * @param game the game that is currently played.
     */
    public void removeFromGame(Game game) {
        game.removeSprite(this);
    }
}
