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
     * construct unbounded ball from point, radius and color.
     *
     * @param center  the initial location of the ball's center.
     * @param r       the ball's radius.
     * @param color   the ball's color.
     * @param gameEnv the game env
     */
    public Ball(Point center, int r, Color color, GameEnvironment gameEnv) {
        this.center = center;
        this.radius = r;
        this.color = color;
        this.velocity = new Velocity(0, 0);
        this.isBounded = false;
        this.gameEnvironment = gameEnv;
        this.lastStuckTime = 0;
        this.beforeLastStuckTime = 0;
    }

    /**
     * construct unbounded ball from two coordinates, radius and color.
     *
     * @param x       the x coordinate of the initial location of the ball's center.
     * @param y       the y coordinate of the initial location of the ball's center.
     * @param r       the ball's radius.
     * @param color   the ball's color.
     * @param gameEnv the game env
     */
    public Ball(int x, int y, int r, Color color, GameEnvironment gameEnv) {
        this.center = new Point((double) x, (double) y);
        this.radius = r;
        this.color = color;
        this.velocity = new Velocity(0, 0);
        this.isBounded = false;
        this.gameEnvironment = gameEnv;
        this.lastStuckTime = 0;
        this.beforeLastStuckTime = 0;
    }

    /**
     * construct bounded ball from point, radius and color.
     *
     * @param center          the initial location of the ball's center.
     * @param r               the ball's radius.
     * @param color           the ball's color.
     * @param horizontalBound the horizontal Bound of the ball.
     * @param verticalBound   the vertical Bound of the ball.
     * @param gameEnvironment the game environment
     */
    public Ball(Point center, int r, Color color,
                int horizontalBound, int verticalBound, GameEnvironment gameEnvironment) {
        this.center = center;
        this.radius = r;
        this.color = color;
        this.velocity = new Velocity(0, 0);
        this.isBounded = true;
        this.verticalBound = verticalBound;
        this.horizontalBound = horizontalBound;
        this.gameEnvironment = gameEnvironment;
        this.lastStuckTime = 0;
        this.beforeLastStuckTime = 0;
    }

    /**
     * construct bounded ball from two coordinates, radius and color.
     *
     * @param x               the x coordinate of the initial location of the ball's center.
     * @param y               the y coordinate of the initial location of the ball's center.
     * @param r               the ball's radius.
     * @param color           the ball's color.
     * @param horizontalBound the horizontal Bound of the ball.
     * @param verticalBound   the vertical Bound of the ball.
     * @param gameEnvironment the game environment
     */
    public Ball(int x, int y, int r, Color color,
                int horizontalBound, int verticalBound, GameEnvironment gameEnvironment) {
        this.center = new Point((double) x, (double) y);
        this.radius = r;
        this.color = color;
        this.velocity = new Velocity(0, 0);
        this.isBounded = true;
        this.verticalBound = verticalBound;
        this.horizontalBound = horizontalBound;
        this.gameEnvironment = gameEnvironment;
        this.lastStuckTime = 0;
        this.beforeLastStuckTime = 0;
    }

    /**
     * this method returns the rounded x coordinate of the ball's center.
     *
     * @return the rounded x coordinate of the ball's center.
     */
    public int getX() {
        return (int) Math.round(this.center.getX());
    }

    /**
     * this method returns the rounded y coordinate of the ball's center.
     *
     * @return the rounded y coordinate of the ball's center.
     */
    public int getY() {
        return (int) Math.round(this.center.getY());
    }

    /**
     * this method returns the ball's radius.
     *
     * @return the ball's radius.
     */
    public int getSize() {
        return this.radius;
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
        return this.center;
    }

    /**
     * Gets color.
     *
     * @return the color
     */
    public Color getColor() {
        return this.color;
    }

    /**
     * this method draws the ball on given DrawSurface.
     *
     * @param surface the DrawSurface to draw on.
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(color);
        surface.fillCircle(this.getX(), this.getY(), this.radius);
        surface.setColor(Color.BLACK);
        surface.drawCircle(this.getX(), this.getY(), this.radius);
    }

    /**
     * this method sets the ball's velocity.
     *
     * @param v Movement.Velocity object to apply on the ball.
     */
    public void setVelocity(Velocity v) {
        this.velocity = v;
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
        this.center = new Point(x, y);
    }

    /**
     * Sets velocity.
     *
     * @param dx the dx
     * @param dy the dy
     */
    public void setVelocity(double dx, double dy) {
        this.velocity = new Velocity(dx, dy);
    }

    /**
     * this method returns the ball's velocity.
     *
     * @return the ball's velocity.
     */
    public Velocity getVelocity() {
        return this.velocity;
    }

    /**
     * Gets environment.
     *
     * @return the environment
     */
    public GameEnvironment getEnvironment() {
        return this.gameEnvironment;
    }

    /**
     * Trajectory line.
     *
     * @return the line
     */
    public Line trajectory() {
        Point startOfTrajectory = new Point(
                Math.floor(this.center.getX()), Math.floor(this.center.getY()));
        Point endOfTrajectory = new Point(
                Math.floor(this.center.getX() + this.velocity.getDx()),
                Math.floor(this.center.getY() + this.velocity.getDy()));
        return new Line(startOfTrajectory, endOfTrajectory);
    }

    /**
     * this method moves the ball's center according to it's velocity.
     */
    public void moveOneStep() {
        CollisionInfo closestCollision = this.gameEnvironment.getClosestCollision(this.trajectory());
        if (closestCollision != null) {
            if (closestCollision.collisionObject() != null && closestCollision.collisionPoint() != null) {
                Velocity newVelocity = closestCollision.collisionObject().hit(
                        this,
                        closestCollision.collisionPoint(),
                        this.getVelocity());
                this.setVelocity(newVelocity);
            }
        }
        this.center = this.getVelocity().applyToPoint(this.center);
    }

    /**
     * this method notifies the ball that a time unit has passed.
     */
    public void timePassed() {
        this.moveOneStep();
        this.gameEnvironment.handleStuckBall(this);
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
     *
     * @param game the game that is currently played.
     */
    public void removeFromGame(GameLevel game) {
        game.removeSprite(this);
    }

    /**
     * This method registers the time the ball got stuck and decides whether we can try to unstuck it by sending it
     * back from the current location to where it cam from or need to randomly move it to a new location
     *
     * @return true if the ball needs to be moved to a new randomized location
     */
    public boolean RegisterStuckCondition() {
        boolean needToRandomize = false;
        long curTime = System.currentTimeMillis();
        if (this.beforeLastStuckTime != 0) {
            if (curTime - this.beforeLastStuckTime < 1000) {
                // This is the third time in less than a second that the ball got stuck.
                // We'll request the caller to move it to a new randomized location
                this.beforeLastStuckTime = 0;
                this.lastStuckTime = 0;
                needToRandomize = true;
            } else {
                this.beforeLastStuckTime = this.lastStuckTime;
                this.lastStuckTime = curTime;
            }
        } else if (this.lastStuckTime != 0) {
            if (curTime - this.lastStuckTime < 1000) {
                // This is the second time in less than a second that the ball got stuck.
                // We still treat this situation as recoverable
                this.beforeLastStuckTime = this.lastStuckTime;
            } else {
                this.beforeLastStuckTime = 0;
            }
            this.lastStuckTime = curTime;
        } else {
            this.lastStuckTime = curTime;
        }
        return needToRandomize;
    }
}
