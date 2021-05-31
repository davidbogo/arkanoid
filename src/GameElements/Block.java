package gameelements;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import eventhandlers.HitListener;
import geometry.Point;
import geometry.Rectangle;
import miscellaneous.Game;
import miscellaneous.HitNotifier;
import miscellaneous.Sprite;
import movement.Collidable;
import movement.Velocity;
import biuoop.DrawSurface;

/**
 * this class represents a block.
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private Rectangle rectangle;
    private Color color;
    private int hits;
    private List<HitListener> hitListeners;


    /**
     * construct a block from a rectangle, color and initial number of hits.
     *
     * @param rec           the rectangle that defines the block.
     * @param col           the rectangle color.
     * @param hitsParam     number of hits to remove the block.
     */
    public Block(Rectangle rec, Color col, int hitsParam) {
        rectangle = rec;
        color = col;
        hits = hitsParam;
        hitListeners = new ArrayList<HitListener>();
    }

    /**
     * construct a block from an upper left point, width, height,
     * color and initial number of hits.
     *
     * @param upperLeft the block's upper left point.
     * @param width     the block's width.
     * @param height    the block's height.
     * @param col       the block's color.
     * @param hitsParam number of hits to remove the block.
     */
    public Block(Point upperLeft, double width,
                 double height, Color col, int hitsParam) {
        rectangle = new Rectangle(upperLeft, width, height);
        color = col;
        hits = hitsParam;
        hitListeners = new ArrayList<HitListener>();

    }

    /**
     * construct a block from two coordinates,
     * width, height, color and initial number of hits.
     *
     * @param x         the x coordinate of the initial location of the block's upper left corner.
     * @param y         the y coordinate of the initial location of the block's upper left corner.
     * @param width     the block's width.
     * @param height    the block's height.
     * @param col       the block's color.
     * @param hitsParam number of hits to remove the block.
     */
    public Block(double x, double y, double width,
                 double height, Color col, int hitsParam) {
        rectangle = new Rectangle(x, y, width, height);
        color = col;
        hits = hitsParam;
        hitListeners = new ArrayList<HitListener>();

    }

    /**
     * this method returns the rectangle that defines the block.
     * @return the rectangle that defines the block.
     */
    public Rectangle getCollisionRectangle() {
        return rectangle;
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
     * Gets hits.
     *
     * @return the hits
     */
    public int getHits() {
        return hits;
    }

    /**
     * @param hitter the hitting ball
     * @param collisionPoint  the collision point
     * @param currentVelocity the current velocity
     * @return boolean.
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        boolean topOrBottomHit = false;
        boolean leftOrRightHit = false;
        if (rectangle.getTop().isContainingPoint(collisionPoint) ||
            rectangle.getBottom().isContainingPoint(collisionPoint)) {
            topOrBottomHit = true;
        }
        if (rectangle.getLeft().isContainingPoint(collisionPoint) ||
            rectangle.getRight().isContainingPoint(collisionPoint)) {
            leftOrRightHit = true;
        }
        if (topOrBottomHit || leftOrRightHit) {
            if (hits > 0) {
                hits--;
            }
        }

        this.notifyHit(hitter);

        if (topOrBottomHit && leftOrRightHit) {
            return new Velocity(-currentVelocity.getDx(), -currentVelocity.getDy());
        }
        if (topOrBottomHit) {
            return new Velocity(currentVelocity.getDx(), -currentVelocity.getDy());
        }
        if (leftOrRightHit) {
            return new Velocity(-currentVelocity.getDx(), currentVelocity.getDy());
        }

        return currentVelocity;
    }

    /**
     * this method draws the block on given DrawSurface.
     * @param surface the DrawSurface to draw on.
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(color);
        surface.fillRectangle(
                (int) Math.round(rectangle.getUpperLeft().getX()),
                (int) Math.round(rectangle.getUpperLeft().getY()),
                (int) Math.round(rectangle.getWidth()),
                (int) Math.round(rectangle.getHeight()));
        surface.setColor(Color.BLACK);
        surface.drawRectangle(
                (int) Math.round(rectangle.getUpperLeft().getX()),
                (int) Math.round(rectangle.getUpperLeft().getY()),
                (int) Math.round(rectangle.getWidth()),
                (int) Math.round(rectangle.getHeight()));

    }

    /**
     * this method notifies the block that a time unit has passed.
     */
    public void timePassed() {

    }

    /**
     * this method adds the block to a game.
     *
     * @param game the game.
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
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
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