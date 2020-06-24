
package levels;

import java.awt.Color;
import biuoop.DrawSurface;
import geometry.Rectangle;
import miscellaneous.Sprite;

/**
 * This class represents the background of the first level.
 */
public class SolidBackground implements Sprite {
    private int imageWidth;
    private int imageHeight;
    private Color color;

    public SolidBackground(int width, int height, Color col) {
        this.imageWidth = width;
        this.imageHeight = height;
        this.color = col;
    }

    /**
     * this method draws the background on given DrawSurface.
     * @param d the DrawSurface to draw on.
     */
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        d.fillRectangle(0, 0, this.imageWidth, this.imageHeight);
    }

    /**
     * this method notifies the background that a time unit has passed.
     */
    public void timePassed() {
    }
}