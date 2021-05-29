package gameelements;

import biuoop.DrawSurface;
import geometry.Point;
import geometry.Rectangle;

import java.awt.Color;

/**
 * Solid block class.
 */
public class SolidBlock extends Block {
    private Color color;
    private Color stroke;

    /**
     * construct a block from a rectangle, color and initial number of hits.
     *
     * @param rec           the rectangle that defines the block.
     * @param color         the rectangle color.
     * @param strokeColor   the stroke color.
     * @param hits          the hits number.
     */
    public SolidBlock(Rectangle rec, Color color, Color strokeColor, int hits) {
        super(rec, hits);
        this.color = color;
        this.stroke = strokeColor;
    }

    /**
     * construct a block from an upper left point, width, height,
     * color and initial number of hits.
     *
     * @param upperLeft     the block's upper left point.
     * @param width         the block's width.
     * @param height        the block's height.
     * @param color         the rectangle color.
     * @param strokeColor   the stroke color.
     * @param hits          the hits
     */
    public SolidBlock(Point upperLeft, double width,
                 double height, Color color, Color strokeColor, int hits) {
        super(upperLeft, width, height, hits);
        this.color = color;
        this.stroke = strokeColor;
    }

    /**
     * construct a block from two coordinates,
     * width, height, color and initial number of hits.
     *
     * @param x             the x coordinate of the initial location of the block's upper left corner.
     * @param y             the y coordinate of the initial location of the block's upper left corner.
     * @param width         the block's width.
     * @param height        the block's height.
     * @param color         the rectangle color.
     * @param strokeColor   the stroke color.
     * @param hits          the hits
     */
    public SolidBlock(double x, double y, double width, double height, Color color, Color strokeColor, int hits) {
        super(x, y, width, height, hits);
        this.color = color;
        this.stroke = strokeColor;
    }

    /**
     * this method draws the block on given DrawSurface.
     * @param surface the DrawSurface to draw on.
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillRectangle(
                (int) Math.round(this.getRectangle().getUpperLeft().getX()),
                (int) Math.round(this.getRectangle().getUpperLeft().getY()),
                (int) Math.round(this.getRectangle().getWidth()),
                (int) Math.round(this.getRectangle().getHeight()));
        if (this.stroke != null) {
            surface.setColor(this.stroke);
            surface.drawRectangle(
                    (int) Math.round(this.getRectangle().getUpperLeft().getX()),
                    (int) Math.round(this.getRectangle().getUpperLeft().getY()),
                    (int) Math.round(this.getRectangle().getWidth()),
                    (int) Math.round(this.getRectangle().getHeight()));
        }
    }

    /**
     * this method returns the block's color.
     *
     * @return the block's color.
     */
    public Color getColor() {
        return this.color;
    }
}
