package gameelements;

import java.awt.Color;

import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import levels.GameLevel;
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
    private int speed;
    private int horizontalBound;
    private int verticalBound;
    private KeyboardSensor keyboard;
    private GUI gui;
    private int margin;


    /**
     * Instantiates a new GameElements.Paddle.
     *
     * @param rec           the rec
     * @param col           the color
     * @param paddleSpeed   the speed
     * @param gui           the gui
     * @param marg          the margin
     */
    public Paddle(Rectangle rec, Color col, int paddleSpeed, GUI gui, int marg) {
        this.rectangle = rec;
        this.color = col;
        this.speed = paddleSpeed;
        this.gui = gui;
        this.margin = marg;
        this.keyboard = gui.getKeyboardSensor();
    }

    /**
     * Instantiates a new GameElements.Paddle.
     *
     * @param upperLeft     the upper left
     * @param width         the width
     * @param height        the height
     * @param col           the color
     * @param paddleSpeed   the speed
     * @param gui           the gui
     * @param marg          the margin
     */
    public Paddle(Point upperLeft, double width, double height, Color col, int paddleSpeed, GUI gui, int marg) {
        this.rectangle = new Rectangle(upperLeft, width, height);
        this.color = col;
        this.speed = paddleSpeed;
        this.gui = gui;
        this.margin = marg;
        this.keyboard = gui.getKeyboardSensor();
    }

    /**
     * Instantiates a new GameElements.Paddle.
     *
     * @param x             the x
     * @param y             the y
     * @param width         the width
     * @param height        the height
     * @param col           the color
     * @param paddleSpeed   the speed
     * @param gui           the gui
     * @param marg          the margin
     */
    public Paddle(double x, double y, double width,
                  double height, Color col, int paddleSpeed, GUI gui, int marg) {
        this.rectangle = new Rectangle(x, y, width, height);
        this.color = col;
        this.speed = paddleSpeed;
        this.gui = gui;
        this.margin = marg;
        this.keyboard = gui.getKeyboardSensor();
    }

    /**
     * Sets bounds.
     *
     * @param hBound the h bound
     * @param vBound the v bound
     */
    public void setBounds(int hBound, int vBound) {
        this.horizontalBound = hBound;
        this.verticalBound = vBound;
    }

    /**
     * this method moves the paddle to the left.
     */
    public void moveLeft() {
        if (this.keyboard.isPressed(KeyboardSensor.LEFT_KEY)
                && this.rectangle.getUpperLeft().getX() > this.margin) {
                int newX;
                if (this.rectangle.getUpperLeft().getX() - this.margin >= this.speed) {
                    newX = (int) Math.round(this.rectangle.getUpperLeft().getX()) - this.speed;
                } else {
                    newX = this.margin;
                }
            this.rectangle = new Rectangle(
                    newX,
                    this.rectangle.getUpperLeft().getY(),
                    this.rectangle.getWidth(),
                    this.rectangle.getHeight());
        }
    }

    /**
     * Move right.
     */
    public void moveRight() {
        if (this.keyboard.isPressed(KeyboardSensor.RIGHT_KEY)
                && this.rectangle.getUpperLeft().getX() + this.rectangle.getWidth() + this.margin
                < this.horizontalBound) {
            int newX;
            if (this.rectangle.getUpperLeft().getX() + this.rectangle.getWidth() + this.margin + this.speed
                    < this.horizontalBound) {
                newX = (int) Math.round(this.rectangle.getUpperLeft().getX()) + this.speed;
            } else {
                newX = (int) Math.round(
                        this.horizontalBound) - this.margin - (int) Math.round(this.rectangle.getWidth());
            }
            this.rectangle = new Rectangle(
                    newX,
                    this.rectangle.getUpperLeft().getY(),
                    this.rectangle.getWidth(),
                    this.rectangle.getHeight());
        }
    }
    /**
     * this method notifies the paddle that a time unit has passed.
     */
    public void timePassed() {
        this.moveLeft();
        this.moveRight();
    }
    /**
     * this method draws the paddle on given DrawSurface.
     * @param d the DrawSurface to draw on.
     */
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        d.fillRectangle(
                (int) Math.round(this.rectangle.getUpperLeft().getX()),
                (int) Math.round(this.rectangle.getUpperLeft().getY()),
                (int) Math.round(this.rectangle.getWidth()),
                (int) Math.round(this.rectangle.getHeight()));
    }
    /**
     * this method returns the rectangle that defines the paddle.
     * @return the rectangle that defines the paddle.
     */
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
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
        double segmentLength = this.rectangle.getWidth() / 5;
        Line leftSegment = new Line(this.rectangle.getUpperLeft().getX(),
                this.rectangle.getUpperLeft().getY(),
                this.rectangle.getUpperLeft().getX() + segmentLength,
                this.rectangle.getUpperLeft().getY());
        Line middleLeftSegment = new Line(
                this.rectangle.getUpperLeft().getX() + segmentLength,
                this.rectangle.getUpperLeft().getY(),
                this.rectangle.getUpperLeft().getX() + 2 * segmentLength,
                this.rectangle.getUpperLeft().getY());
        Line middleSegment = new Line(
                this.rectangle.getUpperLeft().getX() + 2 * segmentLength,
                this.rectangle.getUpperLeft().getY(),
                this.rectangle.getUpperLeft().getX() + 3 * segmentLength,
                this.rectangle.getUpperLeft().getY());
        Line middleRightSegment = new Line(
                this.rectangle.getUpperLeft().getX() + 3 * segmentLength,
                this.rectangle.getUpperLeft().getY(),
                this.rectangle.getUpperLeft().getX() + 4 * segmentLength,
                this.rectangle.getUpperLeft().getY());
        Line rightSegment = new Line(
                this.rectangle.getUpperLeft().getX() + 4 * segmentLength,
                this.rectangle.getUpperLeft().getY(),
                this.rectangle.getUpperLeft().getX() + 5 * segmentLength,
                this.rectangle.getUpperLeft().getY());
        if (leftSegment.isContainingPoint(collisionPoint)) {
            return Velocity.fromAngleAndSpeed(300, currentVelocity.getSpeed());
        }
        if (middleLeftSegment.isContainingPoint(collisionPoint)) {
            return Velocity.fromAngleAndSpeed(330, currentVelocity.getSpeed());
        }
        if (middleSegment.isContainingPoint(collisionPoint)) {
            return new Velocity(currentVelocity.getDx(),
                    -currentVelocity.getDy());
        }
        if (middleRightSegment.isContainingPoint(collisionPoint)) {
            return Velocity.fromAngleAndSpeed(30, currentVelocity.getSpeed());
        }
        if (rightSegment.isContainingPoint(collisionPoint)) {
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
        if (this.rectangle.getTop().isContainingPoint(collisionPoint)) {
            result = hitBySegment(collisionPoint, currentVelocity);
        } else if (this.rectangle.getLeft().isContainingPoint(collisionPoint)) {
            result = Velocity.fromAngleAndSpeed(290, currentVelocity.getSpeed());
        } else if (this.rectangle.getRight().isContainingPoint(collisionPoint)) {
            result = Velocity.fromAngleAndSpeed(80, currentVelocity.getSpeed());
        } else if (this.rectangle.getBottom().isContainingPoint(collisionPoint)) {
            result = Velocity.fromAngleAndSpeed(0, currentVelocity.getSpeed());
        }
        return result;
    }

    /**
     * this method adds the paddle to a game.
     *
     * @param game the game.
     */
    public void addToGame(GameLevel game) {
        game.addCollidable(this);
        game.addSprite(this);
    }

    /**
     * this method removes the paddle from the game.
     *
     * @param game the game.
     */
    public void removeFromGame(GameLevel game) {
        game.removeCollidable(this);
        game.removeSprite(this);
    }

    /**
     * Put balls in the middle.
     */
    public void putBallsInTheMiddle() {
        double x = this.horizontalBound / 2 - this.rectangle.getWidth() / 2;
        double y = this.verticalBound - this.rectangle.getHeight() - 10;
        Point middle = new Point(x, y);
        this.rectangle = new Rectangle(middle, this.rectangle.getWidth(),
                this.rectangle.getHeight());
    }
}
