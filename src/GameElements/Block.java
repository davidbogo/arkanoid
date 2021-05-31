package gameelements;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import eventhandlers.HitListener;
import geometry.Point;
import geometry.Line;
import geometry.Rectangle;
import miscellaneous.Game;
import miscellaneous.HitNotifier;
import miscellaneous.Sprite;
import movement.Collidable;
import movement.Velocity;
import gameelements.Ball;
import biuoop.DrawSurface;

/**
 * The type Block.
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private Rectangle blockShape;
    private Color color;
    private int hits;
    private List<HitListener> hitListeners;
    private Line upLine;
    private Line lowLine;
    private Line leftLine;
    private Line rightLine;

    /**
     * Instantiates a new Block.
     *
     * @param rect      the rect
     * @param hitsParam number of hits to remove the block
     */
    public Block(Rectangle rect, Color col, int hitsParam) {
        Point upLeft = rect.getUpperLeft();
        Point upRight = rect.getUpperRight();
        Point lowLeft = rect.getLowerLeft();
        Point lowRight = rect.getLowerRight();
        upLine = new Line(upLeft, upRight);
        lowLine = new Line(lowLeft, lowRight);
        leftLine = new Line(upLeft, lowLeft);
        rightLine = new Line(upRight, lowRight);
        blockShape = rect;
        color = col;
        hits = hitsParam;
        hitListeners = new ArrayList<HitListener>();
    }

    /**
     * Instantiates a new Block.
     *
     * @param x         the x
     * @param y         the y
     * @param width     the width
     * @param height    the height
     * @param col       the color
     * @param hitsParam number of hits to remove the block
     */
    public Block(double x, double y, double width,
                 double height, Color col, int hitsParam) {
        blockShape = new Rectangle(new Point(x, y), width, height);
        Point upLeft = blockShape.getUpperLeft();
        Point upRight = blockShape.getUpperRight();
        Point lowLeft = blockShape.getLowerLeft();
        Point lowRight = blockShape.getLowerRight();
        upLine = new Line(upLeft, upRight);
        lowLine = new Line(lowLeft, lowRight);
        leftLine = new Line(upLeft, lowLeft);
        rightLine = new Line(upRight, lowRight);
        color = col;
        hits = hitsParam;
        hitListeners = new ArrayList<HitListener>();
    }

    /**
     *
     * @return block shape.
     */
    public Rectangle getCollisionRectangle() {
        return blockShape;
    }

    /**
     * this method returns the block's color.
     *
     * @return the block's color.
     */
    public Color getColor() {
        return color;
    }

    /**
     *
     * @param collisionPoint for the collision point
     * @param currentVelocity for the speed
     * @return Velocity after hit.
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        Velocity newVelocity;
        Line intersectionWithBlockLine = new Line(collisionPoint, collisionPoint);
        if (intersectionWithBlockLine.isIntersecting(upLine) && intersectionWithBlockLine.isIntersecting(leftLine)
         || intersectionWithBlockLine.isIntersecting(upLine) && intersectionWithBlockLine.isIntersecting(rightLine)
         || intersectionWithBlockLine.isIntersecting(lowLine) && intersectionWithBlockLine.isIntersecting(leftLine)
         || intersectionWithBlockLine.isIntersecting(lowLine) && intersectionWithBlockLine.isIntersecting(rightLine)) {
           newVelocity = new Velocity(-currentVelocity.getHorizontalSpeed(), -currentVelocity.getVerticalSpeed());
        } else if (intersectionWithBlockLine.isIntersecting(upLine)) {
           newVelocity = new Velocity(currentVelocity.getHorizontalSpeed(), -currentVelocity.getVerticalSpeed());
        } else if (intersectionWithBlockLine.isIntersecting(lowLine)) {
           newVelocity = new Velocity(currentVelocity.getHorizontalSpeed(), -currentVelocity.getVerticalSpeed());
        } else if (intersectionWithBlockLine.isIntersecting(leftLine)) {
           newVelocity = new Velocity(-currentVelocity.getHorizontalSpeed(), currentVelocity.getVerticalSpeed());
        } else {
           newVelocity = new Velocity(-currentVelocity.getHorizontalSpeed(), currentVelocity.getVerticalSpeed());
        }
        if (hits > 0) {
            hits--;
        }
        notifyHit(hitter);

        return newVelocity;
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
     * Gets hits.
     *
     * @return the hits
     */
    public int getHits() {
        return hits;
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

    /**
     * Remove from game.
     *
     * @param game the game
     */
    public void removeFromGame(Game game) {
        game.removeCollidable(this);
        game.removeSprite(this);
    }

    /**
     * @param hitter the hitter.
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    /**
     * this method adds a given hit listener to the block's hit listeners list.
     * @param hl the given hit listener.
     */
    public void addHitListener(HitListener hl) {
        hitListeners.add(hl);
    }
    /**
     * this method removes a given hit listener
     * from the block's hit listeners list.
     * @param hl the given hit listener.
     */
    public void removeHitListener(HitListener hl) {
        hitListeners.remove(hl);
    }
}
