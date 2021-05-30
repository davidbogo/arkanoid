import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;

import java.awt.Color;

/**
 * The type Paddle.
 */
public class Paddle implements Sprite, Collidable {
    private biuoop.KeyboardSensor keyboard;
    private Rectangle paddleBlock;
    private Color color;
    private GUI gui;
    private int margin;

    /**
     * Instantiates a new Paddle.
     *
     * @param rrec    the rrec
     * @param ccolor  the ccolor
     * @param ggui    the ggui
     * @param mmargin the mmargin
     */
    public Paddle(Rectangle rrec, Color ccolor, GUI ggui, int mmargin) {
        paddleBlock = rrec;
        color = ccolor;
        gui = ggui;
        margin = mmargin;
        keyboard = gui.getKeyboardSensor();
    }

    /**
     * Instantiates a new Paddle.
     *
     * @param x      the x
     * @param y      the y
     * @param width  the width
     * @param height the height
     * @param color  the color
     * @param gui    the gui
     * @param margin the margin
     */
    public Paddle(double x, double y, double width,
                  double height, Color color, GUI gui, int margin) {
        this.paddleBlock = new Rectangle((new Point(x, y)), width, height);
        this.color = color;
        this.gui = gui;
        this.margin = margin;
        this.keyboard = gui.getKeyboardSensor();
    }

    /**
     * Move left.
     */
    public void moveLeft() {
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)
                &&  paddleBlock.getUpperLeft().getX() > margin + 1) {
                    paddleBlock = new Rectangle(new Point(
                    paddleBlock.getUpperLeft().getX() - 1,
                    paddleBlock.getUpperLeft().getY()),
                    paddleBlock.getWidth(),
                    paddleBlock.getHeight());

        }
    }

    /**
     * Move right.
     */
    public void moveRight() {
        if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)
                && paddleBlock.getUpperLeft().getX() + paddleBlock.getWidth()
                < gui.getDrawSurface().getWidth() - margin) {
            paddleBlock = new Rectangle(new Point(
                    paddleBlock.getUpperLeft().getX() + 1,
                    paddleBlock.getUpperLeft().getY()),
                    paddleBlock.getWidth(),
                    paddleBlock.getHeight());
        }
    }


    /**
     * time passed.
     */
    public void timePassed() {
            moveLeft();
            moveRight();
        }

    /**
     *
     * @param d for drawsurface.
     */
        public void drawOn(DrawSurface d) {
            d.setColor(color);
            d.fillRectangle(
            (int) Math.round(paddleBlock.getUpperLeft().getX()),
            (int) Math.round(paddleBlock.getUpperLeft().getY()),
            (int) Math.round(paddleBlock.getWidth()),
            (int) Math.round(paddleBlock.getHeight()));
            d.setColor(Color.BLACK);
            d.drawRectangle(
            (int) Math.round(paddleBlock.getUpperLeft().getX()),
            (int) Math.round(paddleBlock.getUpperLeft().getY()),
            (int) Math.round(paddleBlock.getWidth()),
            (int) Math.round(paddleBlock.getHeight()));
        }

    /**
     *
     * @return paddle.
     */
    public Rectangle getCollisionRectangle() {
            return paddleBlock;
        }

    /**
     * Hit by region velocity.
     *
     * @param collisionPoint  the collision point
     * @param currentVelocity the current velocity
     * @return the velocity
     */
    public Velocity hitByRegion(Point collisionPoint,
                                 Velocity currentVelocity) {
        double regionLength = this.paddleBlock.getWidth() / 5;
        Line leftRegion = new Line(this.paddleBlock.getUpperLeft().getX(),
                this.paddleBlock.getUpperLeft().getY(),
                this.paddleBlock.getUpperLeft().getX() + regionLength,
                this.paddleBlock.getUpperLeft().getY());
        Line middleLeftRegion = new Line(
                this.paddleBlock.getUpperLeft().getX() + regionLength,
                this.paddleBlock.getUpperLeft().getY(),
                this.paddleBlock.getUpperLeft().getX() + 2 * regionLength,
                this.paddleBlock.getUpperLeft().getY());
        Line middleRegion = new Line(
                this.paddleBlock.getUpperLeft().getX() + 2 * regionLength,
                this.paddleBlock.getUpperLeft().getY(),
                this.paddleBlock.getUpperLeft().getX() + 3 * regionLength,
                this.paddleBlock.getUpperLeft().getY());
        Line middleRightRegion = new Line(
                this.paddleBlock.getUpperLeft().getX() + 3 * regionLength,
                this.paddleBlock.getUpperLeft().getY(),
                this.paddleBlock.getUpperLeft().getX() + 4 * regionLength,
                this.paddleBlock.getUpperLeft().getY());
        Line rightRegion = new Line(
                this.paddleBlock.getUpperLeft().getX() + 4 * regionLength,
                this.paddleBlock.getUpperLeft().getY(),
                this.paddleBlock.getUpperLeft().getX() + 5 * regionLength,
                this.paddleBlock.getUpperLeft().getY());
        if (leftRegion.paddleIsContainingPoint(collisionPoint)) {
            return Velocity.fromAngleAndSpeed(300, currentVelocity.getSpeed());
        }
        if (middleLeftRegion.paddleIsContainingPoint(collisionPoint)) {
            return Velocity.fromAngleAndSpeed(330, currentVelocity.getSpeed());
        }
        if (middleRegion.paddleIsContainingPoint(collisionPoint)) {
            return new Velocity(currentVelocity.getHorizontalSpeed(),
                    -currentVelocity.getVerticalSpeed());
        }
        if (middleRightRegion.paddleIsContainingPoint(collisionPoint)) {
            return Velocity.fromAngleAndSpeed(30, currentVelocity.getSpeed());
        }
        if (rightRegion.paddleIsContainingPoint(collisionPoint)) {
            return Velocity.fromAngleAndSpeed(60, currentVelocity.getSpeed());
        }
        return currentVelocity;
    }

    /**
     *
     * @param collisionPoint  the collision point.
     * @param currentVelocity the current velocity.
     * @return the speed after hit.
     */
    public Velocity hit(Point collisionPoint, Velocity currentVelocity) {
        if (paddleBlock.getUpLine().paddleIsContainingPoint(collisionPoint)) {
            return hitByRegion(collisionPoint, currentVelocity);
        }
        if (paddleBlock.getLeftLine().paddleIsContainingPoint(collisionPoint)) {
            return Velocity.fromAngleAndSpeed(290, currentVelocity.getSpeed());
        }
        if (paddleBlock.getRightLine().paddleIsContainingPoint(collisionPoint)) {
            return Velocity.fromAngleAndSpeed(80, currentVelocity.getSpeed());
        }
        if (paddleBlock.getDownLine().paddleIsContainingPoint(collisionPoint)) {
            return Velocity.fromAngleAndSpeed(0, currentVelocity.getSpeed());
        }
        return currentVelocity;
    }

    /**
     * Add to game.
     *
     * @param g the g
     */
// Add this paddle to the game.
    public void addToGame(Game g) {
        g.addCollidable(this);
        g.addSprite(this);
    }
}