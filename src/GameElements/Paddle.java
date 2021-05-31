package gameelements;

import java.awt.Color;

import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import miscellaneous.Game;
import miscellaneous.Sprite;
import movement.Collidable;
import movement.Velocity;
import biuoop.KeyboardSensor;
import biuoop.DrawSurface;
import biuoop.GUI;

/**
 * The type GameElements.Paddle.
 */
public class Paddle implements Sprite, Collidable {
    private Rectangle rectangle;
    private Color color;
    private KeyboardSensor keyboard;
    private GUI gui;
    private int margin;


    /**
     * Instantiates a new GameElements.Paddle.
     *
     * @param rec       the rec
     * @param col       the color
     * @param guiParam  the gui
     * @param marg      the margin
     */
    public Paddle(Rectangle rec, Color col, GUI guiParam, int marg) {
        rectangle = rec;
        color = col;
        gui = guiParam;
        margin = marg;
        keyboard = gui.getKeyboardSensor();
    }

    /**
     * Instantiates a new GameElements.Paddle.
     *
     * @param upperLeft the upper left
     * @param width     the width
     * @param height    the height
     * @param col       the color
     * @param guiParam  the gui
     * @param marg      the margin
     */
    public Paddle(Point upperLeft, double width, double height,
                  Color col, GUI guiParam, int marg) {
        rectangle = new Rectangle(upperLeft, width, height);
        color = col;
        gui = guiParam;
        margin = marg;
        keyboard = gui.getKeyboardSensor();
    }

    /**
     * Instantiates a new GameElements.Paddle.
     *
     * @param x         the x
     * @param y         the y
     * @param width     the width
     * @param height    the height
     * @param col       the color
     * @param guiParam  the gui
     * @param marg      the margin
     */
    public Paddle(double x, double y, double width,
                  double height, Color col, GUI guiParam, int marg) {
        rectangle = new Rectangle(x, y, width, height);
        color = col;
        gui = guiParam;
        margin = marg;
        keyboard = gui.getKeyboardSensor();
    }

    /**
     * this method moves the paddle to the left.
     */
    public void moveLeft() {
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY) &&
            rectangle.getUpperLeft().getX() > margin + 1) {
            rectangle = new Rectangle(
                    rectangle.getUpperLeft().getX() - 1,
                    rectangle.getUpperLeft().getY(),
                    rectangle.getWidth(),
                    rectangle.getHeight());
        }
    }

    /**
     * Move right.
     */
    public void moveRight() {
        if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY) &&
                    rectangle.getUpperLeft().getX() + rectangle.getWidth() <
                    gui.getDrawSurface().getWidth() - margin) {
           rectangle = new Rectangle(
                    rectangle.getUpperLeft().getX() + 1,
                    rectangle.getUpperLeft().getY(),
                    rectangle.getWidth(),
                    rectangle.getHeight());
        }
    }

    /**
     * this method notifies the paddle that a time unit has passed.
     */
    public void timePassed() {
        moveLeft();
        moveRight();
    }

    /**
     * this method draws the paddle on given DrawSurface.
     * @param d the DrawSurface to draw on.
     */
    public void drawOn(DrawSurface d) {
        d.setColor(color);
        d.fillRectangle(
                (int) Math.round(rectangle.getUpperLeft().getX()),
                (int) Math.round(rectangle.getUpperLeft().getY()),
                (int) Math.round(rectangle.getWidth()),
                (int) Math.round(rectangle.getHeight()));
    }
    /**
     * this method returns the rectangle that defines the paddle.
     * @return the rectangle that defines the paddle.
     */
    public Rectangle getCollisionRectangle() {
        return rectangle;
    }

    /**
     * this method gets collision point and velocity
     * and returns a new velocity according to the hit location on the block.
     *
     * @param collisionPoint  the collision point.
     * @param currentVelocity the current velocity.
     * @return the velocity according to the hit location on the block
     */
    public Velocity hitBySegment(Point collisionPoint,
                                 Velocity currentVelocity) {
        double segmentLength = rectangle.getWidth() / 5;
        Line leftSegment = new Line(rectangle.getUpperLeft().getX(),
                rectangle.getUpperLeft().getY(),
                rectangle.getUpperLeft().getX() + segmentLength,
                rectangle.getUpperLeft().getY());
        Line middleLeftSegment = new Line(
                rectangle.getUpperLeft().getX() + segmentLength,
                rectangle.getUpperLeft().getY(),
                rectangle.getUpperLeft().getX() + 2 * segmentLength,
                rectangle.getUpperLeft().getY());
        Line middleSegment = new Line(
                rectangle.getUpperLeft().getX() + 2 * segmentLength,
                rectangle.getUpperLeft().getY(),
                rectangle.getUpperLeft().getX() + 3 * segmentLength,
                rectangle.getUpperLeft().getY());
        Line middleRightSegment = new Line(
                rectangle.getUpperLeft().getX() + 3 * segmentLength,
                rectangle.getUpperLeft().getY(),
                rectangle.getUpperLeft().getX() + 4 * segmentLength,
                rectangle.getUpperLeft().getY());
        Line rightSegment = new Line(
                rectangle.getUpperLeft().getX() + 4 * segmentLength,
                rectangle.getUpperLeft().getY(),
                rectangle.getUpperLeft().getX() + 5 * segmentLength,
                rectangle.getUpperLeft().getY());
        if (leftSegment.containsPoint(collisionPoint)) {
            return Velocity.fromAngleAndSpeed(300, currentVelocity.getSpeed());
        }
        if (middleLeftSegment.containsPoint(collisionPoint)) {
            return Velocity.fromAngleAndSpeed(330, currentVelocity.getSpeed());
        }
        if (middleSegment.containsPoint(collisionPoint)) {
            return new Velocity(currentVelocity.getDx(), -currentVelocity.getDy());
        }
        if (middleRightSegment.containsPoint(collisionPoint)) {
            return Velocity.fromAngleAndSpeed(30, currentVelocity.getSpeed());
        }
        if (rightSegment.containsPoint(collisionPoint)) {
            return Velocity.fromAngleAndSpeed(60, currentVelocity.getSpeed());
        }

        return currentVelocity;
    }

    /**
     * this method gets collision point and velocity
     * and returns a new velocity according to the hit properties.
     * @param collisionPoint the collision point.
     * @param currentVelocity the current velocity.
     * @param hitter the hitter.
     * @return the velocity according to the hit properties.
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        Velocity result = currentVelocity;
        if (rectangle.getTop().containsPoint(collisionPoint)) {
            result = hitBySegment(collisionPoint, currentVelocity);
        } else if (rectangle.getLeft().containsPoint(collisionPoint)) {
            result = Velocity.fromAngleAndSpeed(290, currentVelocity.getSpeed());
        } else if (rectangle.getRight().containsPoint(collisionPoint)) {
            result = Velocity.fromAngleAndSpeed(80, currentVelocity.getSpeed());
        } else if (rectangle.getBottom().containsPoint(collisionPoint)) {
            result = Velocity.fromAngleAndSpeed(0, currentVelocity.getSpeed());
        }
        return result;
    }

    /**
     * this method adds the paddle to a game.
     *
     * @param game the game.
     */
    public void addToGame(Game game) {
        game.addCollidable(this);
        game.addSprite(this);
    }

    /**
     * this method removes the paddle from the game.
     *
     * @param game the game.
     */
    public void removeFromGame(Game game) {
        game.removeCollidable(this);
        game.removeSprite(this);
    }
}
