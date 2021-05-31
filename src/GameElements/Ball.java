package gameelements;

import geometry.Line;
import geometry.Point;
import miscellaneous.Game;
import miscellaneous.GameEnvironment;
import miscellaneous.Sprite;
import movement.Velocity;
import movement.CollisionInfo;
import biuoop.DrawSurface;


import java.awt.Color;
/**
 * The type Ball.
 */
public class Ball implements Sprite {
    private Point center;
    private int radius;
    private java.awt.Color color;
    private Velocity ballVelocity;
    private int screenWidth;
    private int screenHeight;
    private GameEnvironment gameEnvironment;

    /**
     * Instantiates a new Ball.
     *
     * @param x             the x
     * @param y             the y
     * @param r             the r
     * @param col           the color
     * @param width         the width
     * @param height        the height
     * @param environment   the environment
     */
    public Ball(double x, double y, int r, java.awt.Color col, int width,
                int height, GameEnvironment environment) {
        center = new Point(x, y);
        radius = r;
        color = col;
        screenWidth = width;
        screenHeight = height;
        gameEnvironment = environment;
        ballVelocity = new Velocity(0, 0);
    }


    /**
     * Gets x.
     *
     * @return the x
     */
// accessors
    public int getX() {
        return (int) Math.round(center.getX());
    }

    /**
     * Gets y.
     *
     * @return the y
     */
    public int getY() {
        return (int) Math.round(center.getY());
    }

    /**
     * Current ball location point.
     *
     * @return the point
     */
    public Point currentBallLocation() {
        return new Point(center.getX(), center.getY());
    }

    /**
     * Gets size.
     *
     * @return the size
     */
    public int getSize() {
        return radius;
    }

    /**
     * Gets color.
     *
     * @return the color
     */
    public java.awt.Color getColor() {
        return color;
    }

    /**
     * Draw on.
     *
     * @param surface the surface
     */
// draw the ball on the given DrawSurface
    public void drawOn(DrawSurface surface) {
        surface.setColor(color);
        surface.fillCircle(getX(), getY(), radius);
    }

    /**
     * Sets velocity.
     *
     * @param v the velocity
     */
    public void setVelocity(Velocity v) {
        ballVelocity = v;
    }

    /**
     * Gets velocity.
     *
     * @return the velocity
     */
    public Velocity getVelocity() {
        return ballVelocity;
    }

    /**
     * Returns ball's trajectory
     *
     * @return the line
     */
    public Line trajectory() {
        Point startOfTrajectory = currentBallLocation();
        Point endOfTrajectory = ballVelocity.applyToPoint(currentBallLocation());
        return new Line(startOfTrajectory, endOfTrajectory);
    }

    /**
     * Move one step.
     */
    public void moveOneStep() {
        CollisionInfo closestCollision = gameEnvironment.getClosestCollision(this.trajectory());
        if (closestCollision != null) {
            if (closestCollision.collisionObject() != null && closestCollision.collisionPoint() != null) {
                Velocity newVelocity = closestCollision.collisionObject().hit(
                        this,
                        closestCollision.collisionPoint(),
                        ballVelocity);
                setVelocity(newVelocity);
            }
        }
        center = getVelocity().applyToPoint(center);
/*
        double nextBallLocationX;
        double nextBallLocationY;
        double nextHorizontalSpeed = ballVelocity.getHorizontalSpeed();
        double nextVerticalSpeed = ballVelocity.getVerticalSpeed();
        Point projectedBallLocation = ballVelocity.applyToPoint(currentBallLocation());
        Line traject = trajectory();
        CollisionInfo closestCollision = gameEnvironment.getClosestCollision(traject);
        if (closestCollision != null) {
            if ((closestCollision.collisionObject() != null) && (closestCollision.collisionPoint() != null)) {
                Line lineOfClosestCollision = new Line(closestCollision.collisionPoint(),
                        closestCollision.collisionPoint());
                movement.Collidable collisionObject = closestCollision.collisionObject();
                nextHorizontalSpeed = closestCollision.collisionObject().
                        hit(this, closestCollision.collisionPoint(), getVelocity()).getHorizontalSpeed();
                nextVerticalSpeed = closestCollision.collisionObject().
                        hit(this, closestCollision.collisionPoint(), getVelocity()).getVerticalSpeed();
                if (lineOfClosestCollision.isIntersecting(collisionObject.getCollisionRectangle().getUpLine())) {
                    nextBallLocationX = closestCollision.collisionPoint().getX();
                    nextBallLocationY = closestCollision.collisionPoint().getY() - radius;
                } else if (lineOfClosestCollision.isIntersecting(
                        collisionObject.getCollisionRectangle().getDownLine())) {
                    nextBallLocationX = closestCollision.collisionPoint().getX();
                    nextBallLocationY = closestCollision.collisionPoint().getY() + radius;
                } else if (lineOfClosestCollision.isIntersecting(
                        collisionObject.getCollisionRectangle().getLeftLine())) {
                    nextBallLocationX = closestCollision.collisionPoint().getX() - radius;
                    nextBallLocationY = closestCollision.collisionPoint().getY();
                } else {
                    nextBallLocationX = closestCollision.collisionPoint().getX() + radius;
                    nextBallLocationY = closestCollision.collisionPoint().getY();
                }
            }
        }
        if (projectedBallLocation.getX() >= (xBound + screenWidth - radius)) {
            nextBallLocationX = (xBound + screenWidth - radius);
            nextHorizontalSpeed = -nextHorizontalSpeed;
        } else if (projectedBallLocation.getX() <= (xBound + radius)) {
            nextBallLocationX = (xBound + radius);
            nextHorizontalSpeed = -nextHorizontalSpeed;
        } else {
            nextBallLocationX = (projectedBallLocation.getX());
        }
        if (projectedBallLocation.getY() >= (yBound + screenHeight - radius)) {
            nextBallLocationY = (yBound + screenHeight - radius);
            nextVerticalSpeed = -nextVerticalSpeed;
        } else if (projectedBallLocation.getY() <= (yBound + radius)) {
            nextBallLocationY = (yBound + radius);
            nextVerticalSpeed = -nextVerticalSpeed;
        } else {
            nextBallLocationY = (projectedBallLocation.getY());
        }

        center = new Point(nextBallLocationX, nextBallLocationY);
        if ((nextHorizontalSpeed != ballVelocity.getHorizontalSpeed()) ||
            (nextVerticalSpeed != ballVelocity.getVerticalSpeed())) {
            ballVelocity = new Velocity(nextHorizontalSpeed, nextVerticalSpeed);
        }
*/
    }

    /**
     * timePassed.
     */
    public void timePassed() {
        moveOneStep();
    }

    /**
     * Add to game.
     *
     * @param game the game
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
