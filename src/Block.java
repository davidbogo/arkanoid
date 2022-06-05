import biuoop.DrawSurface;

import java.awt.Color;


/**
 * The type Block.
 */
public class Block implements Collidable, Sprite {
    private Point upLeft;
    private Point upRight;
    private Point lowLeft;
    private Point lowRight;
    private Line upLine;
    private Line lowLine;
    private Line leftLine;
    private Line rightLine;
    private Rectangle blockShape;
    private Color color;

    /**
     * Instantiates a new Block.
     *
     * @param rect the rect
     */
    public Block(Rectangle rect) {
        upLeft = rect.getUpperLeft();
        upRight = rect.getUpperRight();
        lowLeft = rect.getLowerLeft();
        lowRight = rect.getLowerRight();
        upLine = new Line(upLeft, upRight);
        lowLine = new Line(lowLeft, lowRight);
        leftLine = new Line(upLeft, lowLeft);
        rightLine = new Line(upRight, lowRight);
        blockShape = rect;
        color = Color.BLUE;
    }

    /**
     * Instantiates a new Block.
     *
     * @param x      the x
     * @param y      the y
     * @param width  the width
     * @param height the height
     */
    public Block(double x, double y, double width,
                 double height) {
        blockShape = new Rectangle(new Point(x, y), width, height);
        upLeft = blockShape.getUpperLeft();
        upRight = blockShape.getUpperRight();
        lowLeft = blockShape.getLowerLeft();
        lowRight = blockShape.getLowerRight();
        upLine = new Line(upLeft, upRight);
        lowLine = new Line(lowLeft, lowRight);
        leftLine = new Line(upLeft, lowLeft);
        rightLine = new Line(upRight, lowRight);
        color = Color.BLUE;
    }

    /**
     * Instantiates a new Block.
     *
     * @param rect       the rect
     * @param blockColor the block color
     */
    public Block(Rectangle rect, Color blockColor) {
        upLeft = rect.getUpperLeft();
        upRight = rect.getUpperRight();
        lowLeft = rect.getLowerLeft();
        lowRight = rect.getLowerRight();
        upLine = new Line(upLeft, upRight);
        lowLine = new Line(lowLeft, lowRight);
        leftLine = new Line(upLeft, lowLeft);
        rightLine = new Line(upRight, lowRight);
        blockShape = rect;
        color = blockColor;
    }
    /**
     *
     * @return block shape.
     */
    public Rectangle getCollisionRectangle() {
        return blockShape;
    }

    /**
     *
     * @param collisionPoint for the collision point
     * @param currentVelocity for the speed
     * @return Velocity after hit.
     */
   public Velocity hit(Point collisionPoint, Velocity currentVelocity) {
       boolean flipHorizontalSpeed = false;
       boolean flipVerticalSpeed = false;
       if (upLine.isContainingPoint(collisionPoint)
               || lowLine .isContainingPoint(collisionPoint)) {
           flipVerticalSpeed = true;
       }
       if (leftLine.isContainingPoint(collisionPoint)
               || rightLine.isContainingPoint(collisionPoint)) {
           flipHorizontalSpeed = true;
       }
       if (flipHorizontalSpeed && flipVerticalSpeed) {
           return new Velocity(-currentVelocity.getHorizontalSpeed(),
                   -currentVelocity.getVerticalSpeed());
       } else {
           if (flipHorizontalSpeed) {
               return new Velocity(-currentVelocity.getHorizontalSpeed(),
                       currentVelocity.getVerticalSpeed());
           }
           if (flipVerticalSpeed) {
               return new Velocity(currentVelocity.getHorizontalSpeed(),
                       -currentVelocity.getVerticalSpeed());
           }
       }
       return currentVelocity;
   }
    /**
     *
     * @param d for drawSurface.
     */
    public void drawOn(DrawSurface d) {
        d.setColor(color);
        d.fillRectangle(
                (int) Math.round(blockShape.getUpperLeft().getX()),
                (int) Math.round(blockShape.getUpperLeft().getY()),
                (int) Math.round(blockShape.getWidth()),
                (int) Math.round(blockShape.getHeight()));
        d.setColor(Color.BLACK);
        d.drawRectangle(
                (int) Math.round(blockShape.getUpperLeft().getX()),
                (int) Math.round(blockShape.getUpperLeft().getY()),
                (int) Math.round(blockShape.getWidth()),
                (int) Math.round(blockShape.getHeight()));
    }

    /**
     * timePassed.
     */
    public void timePassed() {

    }

    /**
     * Add to game.
     *
     * @param game the game
     */
    public void addToGame(Game game) {
        game.addCollidable(this);
        game.addSprite(this);
    }
}
